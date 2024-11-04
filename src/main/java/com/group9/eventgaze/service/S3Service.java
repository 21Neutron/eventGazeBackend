package com.group9.eventgaze.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3client;

    public final String bucketName= "eventgazeeventimages";

    public String uploadFile(String keyname, MultipartFile file) throws IOException{

        File convertedFile = convertMultiParttoFIle(file);
        s3client.putObject(new PutObjectRequest(bucketName, keyname, convertedFile));
        convertedFile.delete();
        return s3client.getUrl(bucketName,keyname).toString();
    }

    private File convertMultiParttoFIle(MultipartFile file) throws  IOException{
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
