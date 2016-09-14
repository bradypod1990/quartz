package com.feng.quartz.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.quartz.Trigger.CompletedExecutionInstruction;

public class MyTriggerListener implements TriggerListener {

	private Logger log = Logger.getLogger(MyTriggerListener.class);
	
	@Override
	public String getName() {
		return "myTriggerListener";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		log.debug("triggerFired");
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		log.debug("vetoJobExecution");
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		log.debug("triggerMisfired");

	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		log.debug("triggerComplete");

	}

}
