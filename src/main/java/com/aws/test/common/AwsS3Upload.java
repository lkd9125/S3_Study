package com.aws.test.common;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AwsS3Upload {
    
    @Value("${s3.AccessToken}")
    private String s3_AccessToken;

    @Value("${s3.AccessSecretToken}")
    private String s3_AccessSecretToken;

    @Value("${s3.Bucket}")
    private String s3_Bucket;

    private AmazonS3 s3Client;

    private Regions clientRegion = Regions.AP_NORTHEAST_2;

    @Bean
    private void createS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(s3_AccessToken, s3_AccessSecretToken);
        this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
              .withRegion(clientRegion).build();
     }

    public void upload(File file, String key){
        log.info("AwsS3Upload - upload()");


        uploadToS3(new PutObjectRequest(s3_Bucket, key, file));
    } 

    private void uploadToS3(PutObjectRequest putObjectRequest) {
        log.info("AwsS3Upload - uploadToS3()");
        try {
           this.s3Client.putObject(putObjectRequest);
           log.info("upload complete -> {}", putObjectRequest.getKey());
        } catch (AmazonServiceException e) {
           e.printStackTrace();
        } catch (SdkClientException e) {
           e.printStackTrace();
        } catch (Exception e) {
           e.printStackTrace();
        }
     }
}
