package com.siva.traincsvbatch.config;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.siva.traincsvbatch.listener.ChunkCountListener;
import com.siva.traincsvbatch.listener.JobCompletionListener;
import com.siva.traincsvbatch.models.TrainInfo;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	ChunkCountListener chunkCountListener;
	
	private static final Logger log =
            LoggerFactory.getLogger(BatchConfig.class);

	@Bean
    public FlatFileItemReader<TrainInfo> reader() {
		log.info("Inside Item Reader");
        return new FlatFileItemReaderBuilder<TrainInfo>().name("userItemReader")
                .resource(new ClassPathResource("train_info.csv")).delimited()
                .names(new String[] {"train_No","train_Name","source_Station_Name","destination_Station_Name","days"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TrainInfo>() {
                    {
                        setTargetType(TrainInfo.class);
                    }
                }).build();
    }
	
	
	 @Bean
	 public MongoItemWriter<TrainInfo> writer(MongoTemplate mongoTemplate) {
		 log.info("Inside Item Writer");
	       return new MongoItemWriterBuilder<TrainInfo>().template(mongoTemplate)
	       .build();
	 }
	 
	 @Bean
	 public Job trainCsvBatchJob(JobCompletionListener listener, Step step1) {
	        return jobBuilderFactory.get("trainCsvBatchJob")
	                .incrementer(new RunIdIncrementer())
	                .listener(listener)
	                .start(step1)
	                .build();
	  }

	    @Bean
	    public Step step1(FlatFileItemReader<TrainInfo> itemReader, MongoItemWriter<TrainInfo> itemWriter) throws Exception {
	        return stepBuilderFactory.get("step1")
	                .<TrainInfo, TrainInfo> chunk(10)
	                .reader(itemReader)
	                .writer(itemWriter)
	                .listener(chunkCountListener)
	                .build();
	    }
}
