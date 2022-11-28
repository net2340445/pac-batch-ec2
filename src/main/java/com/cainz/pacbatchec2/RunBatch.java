package com.cainz.pacbatchec2;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class RunBatch {
    private static final Logger log = LoggerFactory.getLogger(RunBatch.class);
    private static AmazonDynamoDB amazonDynamoDBClient = null;
    private static Table table = null;
    @Autowired
    private JobBuilderFactory jobBuilders;
    @Autowired
    private StepBuilderFactory stepBuilders;

    @Component
    static class MyBatchConfigurer extends DefaultBatchConfigurer {
        @Override
        public void setDataSource(DataSource dataSource) {
        }
    }

    @Bean
    public String reader() throws IOException {
        log.info("!!!reader start");

        amazonDynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.AP_NORTHEAST_1).build();

        List<String> tabs=amazonDynamoDBClient.listTables().getTableNames();
        String size= String.valueOf(tabs.size());
        log.info(size);
        System.out.println(size);

        File dataFile = new File("data.json");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        FileOutputStream os = new FileOutputStream(dataFile);
        os.write("[{\"city\":\"Tokyo\",\"name\":\"andre\"},".getBytes());
        os.write("{\"city\":\"Tokyo\",\"name\":\"andre2\"}]".getBytes());
        os.flush();
        os.close();
        log.info("!!!write end");
        return "true";
    }

    @Bean
    public Job demoJob() throws Exception {
        log.info("!!!write start");
        File dataFile = new File("data.json");

        JsonItemReader<Map> reader = new JsonItemReaderBuilder<Map>().name("reader")
                .resource(new FileSystemResource(dataFile))
                .jsonObjectReader(new JacksonJsonObjectReader<>(Map.class))
                .saveState(true).build();

        FlatFileItemWriter<String> writer = new FlatFileItemWriterBuilder<String>().name("writer")
                .resource(new FileSystemResource("target/output.txt")).lineAggregator(new PassThroughLineAggregator<>()).build();

        Step step = stepBuilders.get("step1").<Map, String>chunk(10).reader(reader).writer(writer)
                .processor((ItemProcessor<Map, String>) (v) -> "__" + v.toString())
                .build();
        log.info("!!!write end");
        return jobBuilders.get("job").start(step).build();
    }
}
