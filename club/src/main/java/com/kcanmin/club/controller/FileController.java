package com.kcanmin.club.controller;

import com.kcanmin.club.entity.Attach;
import com.kcanmin.club.service.S3Service;

// import com.kcanmin.club.service.AttachService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

  @Autowired
  private S3Service s3Service;

  @PostMapping("/upload")
  public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      // Extract file details
      String fileName = file.getOriginalFilename();
      String key = "uploads/" + fileName; // Customize key structure if needed
      String mimeType = file.getContentType();
      byte[] content = file.getBytes();

      // Upload file to S3
      //String s3Path = s3Service.uploadFile(key, content, mimeType);

      // Prepare response data
      Map<String, Object> responseData = new HashMap<>();
      responseData.put("fileName", fileName);
      responseData.put("fileSize", file.getSize());
      responseData.put("fileType", mimeType);
      // responseData.put("s3Path", s3Path);
      responseData.put("s3Key", key);
      responseData.put("uploadTime", ZonedDateTime.now().toString());

      // s3 upload
      s3Service.uploadFile(key, content, mimeType);

      // Return success response
      return ResponseEntity.ok(Map.of(
        "status", "success",
        "message", "File uploaded successfully",
        "data", responseData
      ));
    } catch (Exception e) {
      // Handle errors and return error response
      return ResponseEntity.status(500).body(Map.of(
        "status", "error",
        "message", "File upload failed",
        "error", e.getMessage()
      ));
    }
  }

}