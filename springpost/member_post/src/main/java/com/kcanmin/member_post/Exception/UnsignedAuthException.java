package com.kcanmin.member_post.Exception;


public class UnsignedAuthException extends RuntimeException{
  public UnsignedAuthException(String msg){
    super(msg);
  }
}