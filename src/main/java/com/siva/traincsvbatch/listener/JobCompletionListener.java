package com.siva.traincsvbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
@ Component
public class JobCompletionListener implements JobExecutionListener {

    private static final Logger log =
            LoggerFactory.getLogger(JobCompletionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("BATCH JOB COMPLETED SUCCESSFULLY");

        }
    }

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		log.info("BATCH JOB STARTED WITH {} ID AND STARTED AT {}",jobExecution.getJobId(),jobExecution.getStartTime());
	}
   
}
