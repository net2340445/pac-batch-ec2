package com.cainz.pacbatchec2.batch;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.cainz.pacbatchec2.dto.Consolidate;
import com.cainz.pacbatchec2.entity.PacAllInputEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Configuration
@EnableBatchProcessing
public class RunBatch {
    @Value("${bucket.name}")
    private String bucketName;

    @Value("${folder.name}")
    private String bucketFolderName;

    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    DynamoDBMapper mapper = new DynamoDBMapper(client);
    private static AmazonDynamoDB amazonDynamoDBClient = null;
    private static Table table = null;
    private static final String CSV_SEPARATOR = ",";

    private static final Logger log = LoggerFactory.getLogger(RunBatch.class);
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

        log.info(String.valueOf(LocalDateTime.now()));
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String timestamp = sdf.format(cl.getTime());
        RunBatch pacAllInputCsv = new RunBatch();
        try {
            pacAllInputCsv.test(bucketName, bucketFolderName);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(timestamp + " ERROR " + "Error occurred in PacAllInput CSV:" + e.getMessage());
        }
        log.info(String.valueOf(LocalDateTime.now()));

        log.info("!!!write end");
        return "true";
    }

    public void test(String bucketName, String bucketFolderName) throws IOException {


        List<Consolidate> conList = new ArrayList<>();
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String timestamp = sdf.format(cl.getTime());

        final AmazonS3 s3Client = AmazonS3Client.builder()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        S3Object s3Object = s3Client.getObject(bucketName, bucketFolderName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();


        try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {

            String data;
            while ((data = br.readLine()) != null) {

                data = data.replace("\"", "");
                String[] a = data.split(",");

                Consolidate consolidate = new Consolidate();
                consolidate.setMasterStoreCode(a[0]);
                consolidate.setMaintenanceStoreMode(a[1]);
                consolidate.setPromotionCode(a[2]);
                consolidate.setCode(a[3]);
                consolidate.setPromotionDescription(a[4]);
                consolidate.setPromotionStartDate(a[5]);
                consolidate.setPromotionStartTime(a[6]);
                consolidate.setPromotionEndDate(a[7]);
                consolidate.setPromotionEndTime(a[8]);
                consolidate.setTodaysUpdateFlag(a[9]);
                consolidate.setTodaysDeleteFlag(a[10]);
                consolidate.setMemberRewardLevelFlags(a[11]);
                consolidate.setRewardType(a[12]);
                consolidate.setRewardValue(a[13]);
                consolidate.setItemCode(a[14]);
                conList.add(consolidate);

            }

            log.info("size of the conlist :" + conList.size());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(timestamp + " ERROR " + "Error occurred in ConsolidatedCsv:" + e.getMessage());
            return;
        }


        RunBatch pacAllInputCsv = new RunBatch();
        pacAllInputCsv.pacAllInputCsv(conList);
    }

    public void pacAllInputCsv(List<Consolidate> conList) {


        List<PacAllInputEntity> pacAllInputList = new ArrayList<>();
        List<PacAllInputEntity> deleteFlagAllList = new ArrayList<>();


        for (Consolidate consolidate : conList) {
            if (consolidate.getCode().equals(" ")) {
                Map<String, AttributeValue> pkMap = new HashMap<>();
                pkMap.put(":v1", new AttributeValue().withS(consolidate.getMasterStoreCode() + consolidate.getMaintenanceStoreMode() + consolidate.getPromotionCode()));
                DynamoDBQueryExpression<PacAllInputEntity> deletePkItems = new DynamoDBQueryExpression<PacAllInputEntity>().withKeyConditionExpression("pk=:v1")
                        .withExpressionAttributeValues(pkMap);
                List<PacAllInputEntity> deletePkAllList = mapper.query(PacAllInputEntity.class, deletePkItems);

                for (PacAllInputEntity allDeletePkEntity : deletePkAllList) {

                    PacAllInputEntity pacAllEntityValue = new PacAllInputEntity();
                    pacAllEntityValue.setPk(allDeletePkEntity.getPk());
                    pacAllEntityValue.setSk(allDeletePkEntity.getSk());
                    pacAllEntityValue.setJan(allDeletePkEntity.getJan());
                    pacAllEntityValue.setRank(allDeletePkEntity.getRank());
                    pacAllEntityValue.setPoint(allDeletePkEntity.getPoint());
                    pacAllEntityValue.setPromotionDesc(allDeletePkEntity.getPromotionDesc());
                    pacAllEntityValue.setType(allDeletePkEntity.getType());
                    pacAllEntityValue.setSdt(allDeletePkEntity.getSdt());
                    pacAllEntityValue.setEdt(allDeletePkEntity.getEdt());
                    pacAllEntityValue.setTodaysUpdateFlag("1");
                    pacAllEntityValue.setTodaysDeleteFlag("1");
                    deleteFlagAllList.add(pacAllEntityValue);
                }

            } else {
                deleteFlagAllList.removeIf(x -> x.getPk().equals(consolidate.getMasterStoreCode() + consolidate.getMaintenanceStoreMode() + consolidate.getPromotionCode()) && x.getSk().equals(consolidate.getCode()));
                PacAllInputEntity pacAllInputCsv = new PacAllInputEntity();
                pacAllInputCsv.setPk(consolidate.getMasterStoreCode() + consolidate.getMaintenanceStoreMode() + consolidate.getPromotionCode());
                pacAllInputCsv.setSk(consolidate.getCode());
                pacAllInputCsv.setJan(consolidate.getItemCode());
                pacAllInputCsv.setRank(consolidate.getMemberRewardLevelFlags());
                pacAllInputCsv.setPoint(consolidate.getRewardValue());
                pacAllInputCsv.setPromotionDesc(consolidate.getPromotionDescription());
                pacAllInputCsv.setType(consolidate.getRewardType());
                pacAllInputCsv.setSdt(consolidate.getPromotionStartDate() + consolidate.getPromotionStartTime());
                pacAllInputCsv.setEdt(consolidate.getPromotionEndDate() + consolidate.getPromotionEndTime());
                pacAllInputCsv.setTodaysUpdateFlag(consolidate.getTodaysUpdateFlag());
                pacAllInputCsv.setTodaysDeleteFlag(consolidate.getTodaysDeleteFlag());
                pacAllInputList.add(pacAllInputCsv);
            }
        }
        pacAllInputList.addAll(deleteFlagAllList);
        writeToCSV(pacAllInputList);
        log.info("Created PacAllInput csv");
    }

    private static void writeToCSV(List<PacAllInputEntity> pacAllInputEntities) {
        log.info(String.valueOf(LocalDateTime.now()));
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String timestamp = sdf.format(cl.getTime());
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("PacAllInput.csv"), "UTF-8"));

            for (PacAllInputEntity pacAllInputEntity : pacAllInputEntities) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(pacAllInputEntity.getPk());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getSk());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getJan());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getRank());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getPoint());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getPromotionDesc());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getSdt());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getEdt());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getTodaysUpdateFlag());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pacAllInputEntity.getTodaysDeleteFlag());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(
                    timestamp + " ERROR " + "Error occurred while creating PacAllInput CSV:" + e.getMessage());
        }
        log.info(String.valueOf(LocalDateTime.now()));
    }
}
