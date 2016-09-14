package com.feng.quartz.listener;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class MySchedulerListener implements SchedulerListener {

	private Logger log = Logger.getLogger(MySchedulerListener.class);
	
	@Override
	public void jobScheduled(Trigger trigger) {
		log.debug("trigger:" + trigger.getCalendarName());
	}

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		log.debug("jobUnscheduled:" + triggerKey.getName());

	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		log.debug("triggerFinalized:" + trigger.getCalendarName());

	}

	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		log.debug("triggerPaused:" + triggerKey.getName());

	}

	@Override
	public void triggersPaused(String triggerGroup) {
		log.debug("triggersPaused:" + triggerGroup);

	}

	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		log.debug("triggerResumed:" + triggerKey.getName());

	}

	@Override
	public void triggersResumed(String triggerGroup) {
		log.debug("triggersResumed: triggerGroup->" + triggerGroup);

	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		log.debug("jobAdded:" + jobDetail.getClass());

	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		log.debug("jobDeleted:" + jobKey.getName());

	}

	@Override
	public void jobPaused(JobKey jobKey) {
		log.debug("jobPaused:" + jobKey.getName());

	}

	@Override
	public void jobsPaused(String jobGroup) {
		log.debug("jobsPaused: jobGroup->" +jobGroup);

	}

	@Override
	public void jobResumed(JobKey jobKey) {
		log.debug("jobResumed:" +jobKey.getName());

	}

	@Override
	public void jobsResumed(String jobGroup) {
		log.debug("jobsResumed: jobGroup->" +jobGroup);

	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		log.debug("schedulerError: " +msg);
	}

	@Override
	public void schedulerInStandbyMode() {
		log.debug("schedulerInStandbyMode");

	}

	@Override
	public void schedulerStarted() {
		log.debug("schedulerStarted");

	}

	@Override
	public void schedulerStarting() {
		log.debug("schedulerStarting");

	}

	@Override
	public void schedulerShutdown() {
		log.debug("schedulerShutdown");

	}

	@Override
	public void schedulerShuttingdown() {
		log.debug("schedulerShuttingdown");

	}

	@Override
	public void schedulingDataCleared() {
		log.debug("schedulingDataCleared");

	}

}
