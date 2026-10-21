package activities.ec2first.services.impl;

import activities.ec2first.services.IS3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3Service implements IS3Service {

    private final S3Client s3Client;
    @Value("${aws.s3.bucket}")
    private String buckerName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        String key = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(buckerName)
                .key(key)
                .contentType(file.getContentType() != null
                    ? file.getContentType(): "application/octet-stream")
                .build();
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
            System.out.println("Upload Success: " + putObjectResponse.checksumSHA256());
        }
        catch(Exception e){
            System.out.println("Error uploading file:  " + e.getMessage());
        }
        return key;
    }

    public ListObjectsResponse getBucketObjects() {
        return s3Client.listObjects(ListObjectsRequest.builder().bucket(buckerName).build());
    }

}
