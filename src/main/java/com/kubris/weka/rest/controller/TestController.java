package com.kubris.weka.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kubris.weka.scheduler.SchedulerTest;

@RestController
@RequestMapping("/rest")
public class TestController {
	
	@Autowired
	private SchedulerTest schedulerTest;
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/testSecurity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String testSecurity() {
		return "test";
	}
	
	@RequestMapping(value = "/schedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String schedule(@RequestParam int hour, @RequestParam int min) {
		schedulerTest.startTask(hour, min);
		return "test";
	}


}
