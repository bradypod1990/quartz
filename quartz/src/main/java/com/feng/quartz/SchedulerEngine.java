package com.feng.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.feng.quartz.listener.MyJobListener;
import com.feng.quartz.listener.MySchedulerListener;
import com.feng.quartz.listener.MyTriggerListener;

public class SchedulerEngine {

	private Scheduler scheduler;
	private static SchedulerEngine schedulerEngine;
	private static Logger log = Logger.getLogger(SchedulerEngine.class);
	
	private SchedulerEngine(){};
	private SchedulerEngine(Scheduler scheduler){
		this.scheduler = scheduler;
	};
	
	public static SchedulerEngine getInstance() throws SchedulerException {
		if(schedulerEngine == null) {
			synchronized (SchedulerEngine.class) {
				SchedulerFactory factory = new StdSchedulerFactory();
				Scheduler sch = factory.getScheduler();
				sch.getListenerManager().addSchedulerListener(new MySchedulerListener());
				sch.getListenerManager().addJobListener(new MyJobListener());
				sch.getListenerManager().addTriggerListener(new MyTriggerListener());
				schedulerEngine = new SchedulerEngine(sch);
			}
		}
		
		return schedulerEngine;
	}
	
	/**
	 * 关闭任务引擎
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public boolean shutdown() throws SchedulerException {
		if (scheduler != null) {
			synchronized (scheduler) {
				if (!scheduler.isShutdown()) {
					scheduler.clear();
					scheduler.shutdown(true);
					scheduler = null;
				}
			}

		}
		return true;
	}
	
	public void addJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
		JobKey jobKey = jobDetail.getKey();
		TriggerKey triggerKey = trigger.getKey();
		
		//if exits, delete
		if(scheduler.checkExists(jobKey)) {
			scheduler.interrupt(jobKey);
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(jobKey);
		}
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public void start() throws SchedulerException {
		if(scheduler == null) {
			synchronized (scheduler) {
				getInstance();
			}
		}
		if(scheduler.isStarted() && !scheduler.isShutdown()) {
			log.info("任务调度器正在运行中");
			return;
		}
		scheduler.start();
	}
}
