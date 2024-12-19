package com.kcanmin.member_post.vo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Data
@Alias("attach")
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class Attach {
	// @Value("${upload.path}")
	private static String UPLOAD_PATH = "c:/upload";
	private String uuid;
	private String origin;
	private String path;
	private Boolean image;
	private Long pno;

	public Attach(MultipartFile file) {
		origin = file.getOriginalFilename();
		int dotIdx = origin.lastIndexOf(".");
		String ext = "";
		if (dotIdx != -1) {
			ext = origin.substring(dotIdx);
		}
		log.info(UPLOAD_PATH);
		uuid = UUID.randomUUID().toString();
		String realName = uuid + ext;
		path = getTodayStr();
		File parentPath = new File(UPLOAD_PATH, path);
		if (!parentPath.exists()) {
			parentPath.mkdirs();
			// s 안붙은 애들은 마지막 하나만 만들어주는 메서드라서 무조건 s 붙은걸로 써야 함.
		}
		try {
			File f = new File(parentPath, realName);
			file.transferTo(f);
			// mimetypecheck
			String mime = Files.probeContentType(f.toPath());
			image = mime != null && mime.startsWith("image");

			// thumbnailator
			if (image) {
				File thumb = new File(parentPath, "t_" + realName);
				Thumbnailator.createThumbnail(f, thumb, 100, 100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}

	public File toFile() {
		return new File(new File(UPLOAD_PATH, path), uuid);
	}
}
