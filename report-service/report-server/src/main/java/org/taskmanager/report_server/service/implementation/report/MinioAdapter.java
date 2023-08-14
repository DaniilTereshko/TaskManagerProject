package org.taskmanager.report_server.service.implementation.report;

import io.minio.*;
import io.minio.errors.*;
import org.apache.commons.compress.utils.IOUtils;
import org.taskmanager.report_client.core.dto.FileDTO;
import org.taskmanager.report_server.config.properties.MinioProperties;
import org.taskmanager.report_server.core.exception.DownloadFileException;
import org.taskmanager.report_server.core.exception.ReportUploadException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MinioAdapter {
    private static final String DOWNLOAD_ERROR = "При загрузке файла произошла ошибка";
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public MinioAdapter(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }
    public String upload(File report) throws ReportUploadException{
        try {
            createBucket();
        } catch (Exception e){
            throw new ReportUploadException();
        }
        if(!report.exists()){
            throw new ReportUploadException();
        }
        String fileName = report.getName();
        try (InputStream inputStream = new FileInputStream(report)) {
            this.saveReport(inputStream, fileName);
        } catch (Exception e) {
            throw new ReportUploadException();
        }
        return fileName;
    }
    public FileDTO download(String fileName){
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(this.minioProperties.getBucket())
                    .object(fileName)
                    .build();
            InputStream is = this.minioClient.getObject(args);
            byte[] content = IOUtils.toByteArray(is);
            is.close();
            return new FileDTO(fileName, content);
        } catch (Exception e) {
            throw new DownloadFileException(DOWNLOAD_ERROR);
        }
    }

    private void createBucket() throws Exception {
        boolean found = this.minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(this.minioProperties.getBucket())
                .build());
        if(!found){
            this.minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(this.minioProperties.getBucket())
                    .build());
        }
    }
    private void saveReport(InputStream inputStream, String filename) throws Exception {
        this.minioClient.putObject(PutObjectArgs.builder()
                        .stream(inputStream, inputStream.available(), -1)
                        .bucket(this.minioProperties.getBucket())
                        .object(filename)
                        .build());
    }
}
