package com.kcanmin.member_post.Exception;

public class NotMyPostException extends RuntimeException{
  public NotMyPostException(String msg){
    super(msg);
  }
}
