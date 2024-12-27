package com.kcanmin.guestbook.aop;

import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Component
public class Log4Controller {
  @Before("execution(* com.kcanmin.guestbook.controller..*(..))") // 맨 앞의 별은 모든 반환 타입.  
  public void log(JoinPoint jp) {
    String className = jp.getTarget().getClass().getSimpleName();
    String methodName = jp.getSignature().getName();
    Object[] args = jp.getArgs();
    String paramTypes = "";
    paramTypes = String.join(",", Stream.of(args).map(a->a != null ? a.getClass().getSimpleName() : "null").toList());
    String str = String.format("%s.%s(%s)", className, methodName, paramTypes);
    log.info("Controller log start--------------------------------------------------------------");
    log.info(str);
    Stream.of(args).filter(o-> {
      String name = o.getClass().getName(); 
      return name.contains("com.kcanmin") || name.contains("java.lang");
    }).forEach(log::info);
    log.info("Controller log end----------------------------------------------------------------");
  } 


}
