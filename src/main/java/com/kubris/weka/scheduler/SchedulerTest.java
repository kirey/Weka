package com.kubris.weka.scheduler;

import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Scheduler for test
 * @author paunovicm
 *
 */
@Component
public class SchedulerTest {
	
	final static Logger logger = Logger.getLogger(SchedulerTest.class);
	private ThreadPoolTaskScheduler scheduler;
	private Runnable task;
	private Date nextExecution;
	private int taskDone;
	
	public SchedulerTest() {
		task = new Runnable() {


			public void run() {
				// add the fake user in Security context
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("user", "123");
				SecurityContextHolder.getContext().setAuthentication(authentication);
				//Task from constructor
				try{
					taskDone = 1;
					logger.info("---------------------scheduler works!!!!------------------");
					System.out.println("---------------------scheduler works!!!!------------------");
					taskDone = 2;
				}catch (Exception e) {
					taskDone = 3;
					
				}
			}
		};
		taskDone = 0;
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(1);
		scheduler.initialize();
	}
	
	
	public void startTask(int hour, int min) {
		shutDown();
		scheduler.setPoolSize(1);
		scheduler.initialize();
		scheduler.schedule(task, getCronTrigger(hour, min));
	}
	
	
	public CronTrigger getCronTrigger(int hour, int min) {
		
		String hourString = String.valueOf(hour);
		String minString = String.valueOf(min);
		String cronExpression = "0 min h * * ?";
		cronExpression = cronExpression.replaceFirst("h", hourString);
		cronExpression = cronExpression.replaceFirst("min", minString);
		
		CronTrigger cronTrigger = new CronTrigger(cronExpression, TimeZone.getTimeZone("CET")){
			@Override
		    public Date nextExecutionTime(TriggerContext triggerContext) {
				Date date =  super.nextExecutionTime(triggerContext);
				nextExecution = new Date(date.getTime());
				return date;
			}
		};
		
		return cronTrigger;
	}
	
	public boolean isShutDown(){
		return scheduler.getScheduledThreadPoolExecutor().isShutdown();
	}
	
	public void shutDown() {
		scheduler.getScheduledThreadPoolExecutor().shutdownNow();
	}

	public ThreadPoolTaskScheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(ThreadPoolTaskScheduler scheduler) {
		this.scheduler = scheduler;
	}


	public Date getNextExecution() {
		return nextExecution;
	}


	public void setNextExecution(Date nextExecution) {
		this.nextExecution = nextExecution;
	}


	public int getTaskDone() {
		return taskDone;
	}


	public void setTaskDone(int taskDone) {
		this.taskDone = taskDone;
	}

}
