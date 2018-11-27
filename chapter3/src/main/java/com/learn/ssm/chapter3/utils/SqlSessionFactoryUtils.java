package com.learn.ssm.chapter3.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.learn.ssm.chapter3.mapper.RoleMapper;
import com.learn.ssm.chapter3.mapper.RoleMapper2;
import com.learn.ssm.chapter3.pojo.Role;

public class SqlSessionFactoryUtils {

	private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;

	private static SqlSessionFactory sqlSessionFactory = null;

	private SqlSessionFactoryUtils() {
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		synchronized (LOCK) {
			if (sqlSessionFactory != null) {
				return sqlSessionFactory;
			}
			String resource = "mybatis-config.xml";
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return sqlSessionFactory;
		}
	}
	
	
	// 使用代码创建SqlSessionFactory
	public static SqlSessionFactory getSqlSessionFactory2() {
		synchronized (LOCK) {
			// 数据库连接池信息
			PooledDataSource dataSource = new PooledDataSource();
			dataSource.setDriver("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("ssm");
			dataSource.setPassword("MySQL79139");
			dataSource.setUrl("jdbc:mysql://localhost:3306/ssm");
			dataSource.setDefaultAutoCommit(false);
			// 采用MyBatis的JDBC事务方式
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			// 创建Configuration对象
			Configuration configuration = new Configuration(environment);
			// 注册MyBatis上下文别名
			configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
			// 加入映射器
			configuration.addMapper(RoleMapper.class);
			configuration.addMapper(RoleMapper2.class);
			// 使用SqlSessionFactoryBuilder构建SqlSessionFactory
			sqlSessionFactory = 
			    new SqlSessionFactoryBuilder().build(configuration);
			return sqlSessionFactory; 	
		}
	}

	public static SqlSession openSqlSession() {
		if (sqlSessionFactory == null) {
			getSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}
}