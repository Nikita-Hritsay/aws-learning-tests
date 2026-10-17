package activities.ec2first.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @PostMapping
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
