package com.kcanmin.member_post.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kcanmin.member_post.mapper.AttachMapper;
import com.kcanmin.member_post.vo.Attach;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@EnableScheduling
@AllArgsConstructor
public class FileDeleteTask {
  private AttachMapper mapper;
  
  @Scheduled(cron = "0 37 14 * * *")
  public void deleteFiles(){
    String path = "2024/12/20";
    List<Attach> files = new ArrayList<>(Arrays.asList(new File("c:/upload", path).listFiles()).stream().map(Attach::fromFile).toList()); 
    List<Attach> dbs = new ArrayList<>(mapper.selectListByPath(path));
    List<Attach> thumbs = dbs.stream().filter(Attach::isImage).peek(a->a.setUuid("t_"+a.getUuid())).toList(); 
    dbs.addAll(thumbs);
    files.removeAll(dbs);

    // files.stream().map(Attach::toFile).forEach(File::delete);
    
    files.stream().peek(a-> a.setPath(path)).map(Attach::toFile)
    // .forEach(File::delete);
    .forEach((file)->{file.delete();});
    log.info(files.size() + " 개의 파일을 삭제하였습니다.");
  }
}
