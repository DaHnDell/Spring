package com.kcanmin.member_post.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisInIt {
	private static MybatisInIt init = new MybatisInIt();
	public static MybatisInIt getInstance() {
		return init;
	}

	public SqlSessionFactory sqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new MybatisInIt().sqlSessionFactory());
	}
}
