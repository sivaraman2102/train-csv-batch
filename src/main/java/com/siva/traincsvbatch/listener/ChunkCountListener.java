package com.siva.traincsvbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.ChunkListener;



public class ChunkCountListener implements ChunkListener {
	
	private static final Logger log = LoggerFactory.getLogger(ChunkCountListener.class);
	
	@Override
	public void afterChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		log.info("A set of {} records were processed",
				context.getStepContext().getStepExecution().getReadCount());
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

}
