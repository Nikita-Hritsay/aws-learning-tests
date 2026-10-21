package activities.ec2first.services;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;

public interface IS3Service {

    String upload(MultipartFile file);
    ListObjectsResponse getBucketObjects();

}
