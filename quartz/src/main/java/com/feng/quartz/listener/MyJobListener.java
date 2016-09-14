package com.feng.quartz.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
	
	private Logger log = Logger.getLogger(MyJobListener.class);

	@Override
	public String getName() {
		return "myJobListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		log.debug("jobToBeExecuted");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		log.debug("jobExecutionVetoed");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		log.debug("jobWasExecuted");
	}

}
