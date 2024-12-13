package com.kcanmin.jdbc.service;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.jdbc.dao.MemberDao;
import com.kcanmin.jdbc.dao.PostDao;
import com.kcanmin.jdbc.dao.ReplyDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {
  private MemberDao memberdao;
  private PostDao postDao;
  private ReplyDao replyDao;
  private TransactionDefinition transactionDefinition;
  private DataSourceTransactionManager transactionManager;

  // public void quitMember(String id){
  //   TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

  //   try{
  //     replyDao.updateToWriterNull(id);
  //     postDao.updateToWriterNull(id);
  //     memberdao.delete(id);
  //   }catch(DataAccessException dae){
  //     transactionManager.rollback(null);
  //   }
 
  //   transactionManager.commit(null);
  // }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
  public void quitMember(String id){
    replyDao.updateToWriterNull(id);
    postDao.updateToWriterNull(id);
    memberdao.delete(id);
  }

}