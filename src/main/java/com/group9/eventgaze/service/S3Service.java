package com.group9.eventgaze.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    @Autowired
    private  S3Client s3client;

    public final String bucketName= "eventgazeeventimages";

    public String uploadFile(String keyName, MultipartFile file) throws IOException{

        File convertedFile = convertMultiParttoFIle(file);
       PutObjectRequest putObjectRequest = PutObjectRequest.builder()
               .bucket(bucketName)
               .key(keyName)
               .build();

       s3client.putObject(putObjectRequest,RequestBody.fromFile(convertedFile));

       convertedFile.delete();
       return s3client.utilities().getUrl(b -> b.bucket(bucketName).key(keyName)).toString();
    }

    private File convertMultiParttoFIle(MultipartFile file) throws  IOException{
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
