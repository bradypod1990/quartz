package com.feng.quartz.test;

import java.util.Date;
import java.util.TimeZone;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.feng.quartz.MyJobDetail;
import com.feng.quartz.SchedulerEngine;

public class MyTest {

	public static void main(String[] args) throws SchedulerException{
		testDayTrigger();
	}
	
	
	public static void testDayTrigger() throws SchedulerException {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("hello", "zoufeng");
		jobDataMap.put("name", "邹枫");
		JobDetail jobDetail = JobBuilder.newJob(MyJobDetail.class).setJobData(jobDataMap).withIdentity("firtJob", "firtJobGroup").build();
		ScheduleBuilder<DailyTimeIntervalTrigger> schedBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
													.startingDailyAt(TimeOfDay.hourAndMinuteAndSecondFromDate(new Date(),TimeZone.getDefault()))
													.endingDailyAfterCount(100)
													.withIntervalInSeconds(5);
		TriggerBuilder<? extends Trigger> triggerBuilder = TriggerBuilder.newTrigger().forJob(jobDetail)
															.withIdentity("firtTrigger", "firtGriggerGroup")
															.withSchedule(schedBuilder);
		
		Scheduler scheduler = SchedulerEngine.getInstance();
		scheduler.scheduleJob(jobDetail, triggerBuilder.build());
		SchedulerEngine.start();
	}
	

	public static void testjobKey() throws SchedulerException {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("hello", "zoufeng");
		jobDataMap.put("name", "邹枫");
		JobDetail jobDetail = JobBuilder.newJob(MyJobDetail.class).setJobData(jobDataMap).withIdentity("firtJob", "firtJobGroup").build();
		ScheduleBuilder<DailyTimeIntervalTrigger> schedBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
													.startingDailyAt(TimeOfDay.hourAndMinuteAndSecondFromDate(new Date(),TimeZone.getDefault()))
													.endingDailyAfterCount(100)
													.withIntervalInSeconds(5);
		TriggerBuilder<? extends Trigger> triggerBuilder = TriggerBuilder.newTrigger().forJob(jobDetail)
															.withIdentity("firtTrigger", "firtGriggerGroup")
															.withSchedule(schedBuilder);
		
		Scheduler scheduler = SchedulerEngine.getInstance();
		scheduler.scheduleJob(jobDetail, triggerBuilder.build());
		SchedulerEngine.start();
	}
}
