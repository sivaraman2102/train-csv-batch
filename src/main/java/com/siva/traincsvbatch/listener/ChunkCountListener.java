package com.siva.traincsvbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;



public class ChunkCountListener extends ChunkListenerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(ChunkCountListener.class);
	
	@Override
	public void afterChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		super.afterChunk(context);
		log.info("A set of {} records were processed",
				context.getStepContext().getStepExecution().getReadCount());
	}

}
