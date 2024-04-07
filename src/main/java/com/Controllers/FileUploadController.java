package com.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/upload")
public class FileUploadController {
    // Define endpoint for uploading a single file
    @PostMapping("/single")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // Implement file upload logic here
        return "File uploaded successfully!";
    }

    // Define endpoint for uploading multiple files
    @PostMapping("/multiple")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        // Implement file upload logic here
        return "Files uploaded successfully!";
    }

}
