package com.feng.quartz;

import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.rules.Timeout;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;


/**
 * 串行脚本服务JOB
 *
 */
@DisallowConcurrentExecution
public class MyJobDetail implements InterruptableJob {

	private static final Logger log =Logger.getLogger(MyJobDetail.class);
	Thread excutorThread; 
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("start->      "+ System.currentTimeMillis()/1000);
		excutorThread = Thread.currentThread();
		JobDataMap jobDataMap =  context.getMergedJobDataMap();	
		if(jobDataMap != null) {
			for(Entry<String, Object> entry : jobDataMap.entrySet()) {
				System.out.println(entry.getKey() + " value:  " + entry.getValue());
			}
		}
		try {
			TimeUnit.SECONDS.sleep(8L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.debug("Hello excute------------------------------");
		System.out.println("end->      "+ System.currentTimeMillis()/1000);
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		if(excutorThread != null) {
			log.info("中断任务:"+excutorThread.getName());
			excutorThread.interrupt();
		}
	}

}
