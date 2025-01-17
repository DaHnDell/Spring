package com.kcanmin.club.controller;
import com.kcanmin.club.entity.Attach;
import com.kcanmin.club.entity.dto.AttachDTO;
import com.kcanmin.club.service.S3Service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/file")
@Log4j2
public class FileController {

  @Autowired
  private S3Service s3Service;

  @Value("${aws.s3.bucket-name}")
  private String bucketName;

  @Value("${aws.s3.region}")
  private String region;

  @PostMapping("/upload")
  public ResponseEntity <?> uploadFile(@RequestParam("file") List<MultipartFile> files) {
    List<AttachDTO> list = new ArrayList<>();  
    for(MultipartFile file : files){
    try {
        // Extract file details
        String origin = file.getOriginalFilename();
        String path = path();
        String uuid = UUID.randomUUID().toString();
        String ext = "";
        int idx = origin.lastIndexOf(".");
        if(idx > 0) {
          ext = origin.substring(idx+1);
        }
        String fileName = uuid + "." + ext;
        String mimeType = file.getContentType();
        byte[] content = file.getBytes();
        String key = "uploads/" + path + "/" + fileName; // Customize key structure if needed

        // Upload file to S3
        //String s3Path = s3Service.uploadFile(key, content, mimeType);

        // Prepare response data
        // Map<String, Object> responseData = new HashMap<>();
        // responseData.put("fileName", origin);
        // responseData.put("fileSize", file.getSize());
        // responseData.put("fileType", mimeType);
        // // responseData.put("s3Path", s3Path);
        // responseData.put("s3Key", key);
        // responseData.put("uploadTime", ZonedDateTime.now().toString());

        // s3 upload
        s3Service.uploadFile(key, content, mimeType);

        // https://kcanmin-bucket.s3.ap-northeast-2.amazonaws.com/uploads/images.jpg
        AttachDTO dto = AttachDTO.builder()
          .uuid(uuid)
          .origin(origin)
          .image(mimeType != null && mimeType.startsWith("image/"))
          .path(path)
          .size(file.getSize())
          .mime(mimeType)
          .fileName(fileName)
          .ext(ext)
          .url(String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key))
        .build();
        list.add(dto);
        // Return success response
        // return ResponseEntity.ok(Map.of(
        //   "status", "success",
        //   "message", "File uploaded successfully",
        //   "data", responseData
        // ));
      } catch (Exception e) {
        // Handle errors and return error response
        // return ResponseEntity.status(500).body(Map.of(
        //   "status", "error",
        //   "message", "File upload failed",
        //   "error", e.getMessage()
        // ));
        log.error(e.getMessage());
      }
    }
    return ResponseEntity.ok().body(list);
  }
  private String path() {
    return new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
  }

}