package com.siva.traincsvbatch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siva.traincsvbatch.listener.JobCompletionListener;

@RestController
public class DataController {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job trainCsvBatchJob;
	
	@RequestMapping("/start")	
	public ResponseEntity<Object> trainCsvBatchJob(@RequestParam String useridString) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException  {
		
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("USERID", new JobParameter(useridString.trim()));
		
        JobExecution jobExecution = jobLauncher.run(trainCsvBatchJob,new JobParameters(maps));
        
        return new ResponseEntity<>(jobExecution.getStatus(),HttpStatus.OK);
    }
	
	
	
	
}
