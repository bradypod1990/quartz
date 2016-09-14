package com.feng.quartz;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.feng.quartz.listener.MyJobListener;
import com.feng.quartz.listener.MySchedulerListener;
import com.feng.quartz.listener.MyTriggerListener;

public class SchedulerEngine {

	private static Scheduler scheduler;
	private static Logger log = Logger.getLogger(SchedulerEngine.class);
	
	
	public static Scheduler getInstance() throws SchedulerException {
		if(scheduler == null) {
			synchronized (SchedulerEngine.class) {
				SchedulerFactory factory = new StdSchedulerFactory();
				scheduler = factory.getScheduler();
				scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());
				scheduler.getListenerManager().addJobListener(new MyJobListener());
				scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
			}
		}
		
		return scheduler;
	}
	
	/**
	 * 关闭任务引擎
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean shutdown() throws SchedulerException {
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
	
	public static void start() throws SchedulerException {
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
