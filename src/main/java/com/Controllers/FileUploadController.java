package com.Controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ContactServices.ContactService;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    @Autowired
    private ContactService contactService;

    private static final List<String> SUPPORTED_FILE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("entityName") String entityName,
            @RequestParam("entityId") String entityId) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }

        // Get the file extension
        String fileExtension = getFileExtension(file.getOriginalFilename());

        // Check if the file extension is supported
        if (!isSupportedFileExtension(fileExtension)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unsupported file type. Only JPG, JPEG, and PNG files are allowed.");
        }

        if (!file.isEmpty() && isSupportedFileExtension(fileExtension)) {
            contactService.addFile(file, entityId, entityName);
        }
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private boolean isSupportedFileExtension(String extension) {
        return SUPPORTED_FILE_EXTENSIONS.contains(extension.toLowerCase());
    }

    // Endpoint for file retrieval
    @GetMapping("/files/{uri}")
    public ResponseEntity<byte[]> getFile(@PathVariable String uri) {
        // Implement file retrieval logic here
        // Placeholder for now
        return ResponseEntity.status(HttpStatus.OK).body(new byte[0]);
    }
}
