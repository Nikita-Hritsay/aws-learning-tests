package activities.ec2first.services;

import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {

    String upload(MultipartFile file);

}
