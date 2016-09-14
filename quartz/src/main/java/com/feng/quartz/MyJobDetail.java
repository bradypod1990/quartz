package com.feng.quartz;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
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
		excutorThread = Thread.currentThread();
		JobDataMap jobDataMap =  context.getMergedJobDataMap();	
		if(jobDataMap != null) {
			for(Entry<String, Object> entry : jobDataMap.entrySet()) {
				System.out.println(entry.getKey() + " value:  " + entry.getValue());
			}
		}
		log.debug("Hello excute------------------------------");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		if(excutorThread != null) {
			log.info("中断任务:"+excutorThread.getName());
			excutorThread.interrupt();
		}
	}

}
