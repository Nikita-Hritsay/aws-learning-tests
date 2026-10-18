package activities.ec2first.controllers;

import activities.ec2first.services.IS3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
@AllArgsConstructor
public class FileController {
    private final IS3Service s3Service;

    @PostMapping
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        String key = s3Service.upload(file);
        return new ResponseEntity<>(key, HttpStatus.ACCEPTED);
    }

}
