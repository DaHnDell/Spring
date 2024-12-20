package com.kcanmin.member_post.vo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

// @Data
@Getter
@ToString
@Setter
// @EqualsAndHashCode(of = "uuid") // 오브젝트가 가지고 있는 메서드, Equals . 우리가 만든 판별식을 통해 동일 여부를 판단한다.
@Alias("attach")
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attach {
	// @Value("${upload.path}")
	private static String UPLOAD_PATH = "c:/upload";
	private String uuid;
	private String origin;
	private String path;
	private boolean image;
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
		String realName = uuid = uuid + ext;
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

	public static Attach fromFile(File file){
		return Attach.builder().uuid(file.getName()).build();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Attach && uuid.equals(((Attach)obj).uuid); // 직접 equals 메서드 오버라이딩. 추가적인 조건을 작성할 경우 더 사용할 수 있다. 
	}

	@Override
	public int hashCode() { // 어차피 uuid의 숫자 해쉬값만 비교하면 되니까,,,
		return uuid.hashCode();
	}

	
}
