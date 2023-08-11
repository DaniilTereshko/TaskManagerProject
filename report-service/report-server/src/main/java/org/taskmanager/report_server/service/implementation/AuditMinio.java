package org.taskmanager.report_server.service.implementation;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.multipart.MultipartFile;
import org.taskmanager.report_server.config.properties.MinioProperties;
import org.taskmanager.report_server.core.dto.Report;
import org.taskmanager.report_server.core.exception.ReportUploadException;

import java.io.InputStream;
import java.util.UUID;

public class AuditMinio {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public AuditMinio(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }
    public String upload(Report report){
        try {
            createBucket();
        } catch (Exception e){
            throw new ReportUploadException();
        }
        MultipartFile file = report.getFile();
        if(file.isEmpty() || file.getOriginalFilename() == null){
            throw  new ReportUploadException();
        }
        String fileName = generateFileName(file);
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e){
            throw new ReportUploadException();
        }
        saveReport(inputStream, fileName);
        return fileName;
    }

    private void createBucket(){
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket())
                .build());
        if(!found){
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .build());
        }
    }
    private String generateFileName(MultipartFile file){
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }
    private String getExtension(MultipartFile file){
        return file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    private void saveReport(InputStream inputStream, String filename){
        minioClient.putObject(PutObjectArgs.builder()
                        .stream(inputStream, inputStream.available(), -1)
                        .bucket(minioProperties.getBucket())
                        .object(filename)
                        .build());
    }
}
