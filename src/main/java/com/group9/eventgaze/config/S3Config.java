package com.group9.eventgaze.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

        @Value("${AWS.ACCESS.KEY.ID}")
        private String accessKey;

        @Value("${AWS.SECRET.ACCESS.KEY}")
        private String secretKey;

        @Value("${AWS.REGION}")
        private String region;

        @Bean
        public S3Client s3Client() {
            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

            return S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                    .build();
        }
}
