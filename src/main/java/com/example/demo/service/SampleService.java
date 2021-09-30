package com.example.demo.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SequenceEntity;
import com.example.demo.repository.SampleRepository;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class SampleService {

	@Autowired
	SampleRepository repository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HikariDataSource datasource;
	
	String SEQUENCE_NAME_COLUMN = "my_seq";
	long INCREMENT_BY = 1;
	
	public SequenceEntity select(String sequenceName) {
		return repository.select(sequenceName);
	}
	
	public void update(SequenceEntity sequenceEntity) {
		repository.update(sequenceEntity);
    }
	
	@Transactional
	public String sync() {
		System.out.println(getNext());
		System.out.println(getNext());
		System.out.println(getNext());
		System.out.println(getNext());
		//throw new NoSuchElementException();
		return "Success";
	}
	
	@Transactional
	public String async() {
		return "Success";
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test1() throws SQLTimeoutException {
		getNext();
		update(123);
		throw new SQLTimeoutException();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test2() {
		getNext();
		update(123);
		throw new NoSuchElementException();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test3() throws SQLException {
		getNext();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		update(123);
		throw new SQLException();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test4(){
		getNext();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test5(){
		jdbcTemplate.setQueryTimeout(1);
		jdbcTemplate.execute("Update sequences SET next_value = 789 WHERE name = \"my_seq\"");
		return ""+jdbcTemplate.getQueryTimeout();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test6() {
		getNext();
		update(123);
		error();
		return "test6";
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String test7() {
		getNext();
		return "test7";
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String inner() {
		throw new NoSuchElementException();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String outer() {
		getNext();
		update(123);
		inner();
		update(456);
		return "outer";
	}
	
	public String set() throws SQLException {

//		String temp = "set";
//		try (Connection connection =
//		    DriverManager.getConnection("jdbc:cloudspanner:/projects/tribal-mapper-326407/instances/test/databases/test")) 
//		{			
//		  try (Statement statement = connection.createStatement()) {
//			statement.execute("SET STATEMENT_TIMEOUT = '3s'");
//		  }
//		}
//		return temp;
		
		String temp = datasource.getDataSourceClassName() + " " + datasource.getDriverClassName();
		try (Connection connection = datasource.getConnection()) 
		{			
		  try (Statement statement = connection.createStatement()) {
			statement.execute("SET STATEMENT_TIMEOUT = '3s'");
		  }
		}
		return temp;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String show() throws SQLException {

		String temp = "null";
		try (Connection connection = datasource.getConnection()) 
		{			
		  try (Statement statement = connection.createStatement()) {
			try (ResultSet rs = statement.executeQuery("SELECT name, next_value FROM sequences WHERE name = \"my_seq\"")) {
			  while (rs.next()) {
			    temp = "Connected to Cloud Spanner at " + rs.getString(1) + rs.getString(2);
			    System.out.println(temp);
			  }
			}
			statement.execute("Update sequences SET next_value = 100 WHERE name = \"my_seq\"");
			statement.execute("SET STATEMENT_TIMEOUT = '1ms'");
		    try (ResultSet rs = statement.executeQuery("SHOW VARIABLE STATEMENT_TIMEOUT")) {
		      while (rs.next()) {
		    	temp = "Connected to Cloud Spanner at " + rs.getString(1);
		        System.out.println(temp);
		      }
		    }
		    statement.execute("Update sequences SET next_value = 200 WHERE name = \"my_seq\"");
		  }
		}
		return temp;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String show2() throws SQLException {

		String temp = "null";
		try (Connection connection = datasource.getConnection()) 
		{			
		  try (Statement statement = connection.createStatement()) {
			try (ResultSet rs = statement.executeQuery("SELECT name, next_value FROM sequences WHERE name = \"my_seq\"")) {
			  while (rs.next()) {
			    temp = "Connected to Cloud Spanner at " + rs.getString(1) + rs.getString(2);
			    System.out.println(temp);
			  }
			}
			statement.setQueryTimeout(1);
			statement.execute("Update sequences SET next_value = 100 WHERE name = \"my_seq\"");
		    try (ResultSet rs = statement.executeQuery("SHOW VARIABLE STATEMENT_TIMEOUT")) {
		      while (rs.next()) {
		    	temp = "Connected to Cloud Spanner at " + rs.getString(1);
		        System.out.println(temp);
		      }
		    }
		    statement.execute("Update sequences SET next_value = 200 WHERE name = \"my_seq\"");
		  }
		}
		return temp;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String show3() throws SQLException {

		String temp = "null";
		try (Connection connection = datasource.getConnection()) 
		{			
		  try (Statement statement = connection.createStatement()) {
			//statement.setQueryTimeout(1);
			try (ResultSet rs = statement.executeQuery("SELECT name, next_value FROM sequences WHERE name = \"my_seq\"")) {
			  while (rs.next()) {
			    temp = "Connected to Cloud Spanner at " + rs.getString(1) + rs.getString(2);
			    System.out.println(temp);
			  }
			}
			statement.execute("Update sequences SET next_value = 789 WHERE name = \"my_seq\"");
		  }
		}
		return temp;
	}
	
	public long getNext() {
		SequenceEntity sequenceEntity = repository.select(SEQUENCE_NAME_COLUMN);
		if(sequenceEntity == null) {
			throw new NoSuchElementException();
		}
		long value = sequenceEntity.getNext_value();
		sequenceEntity.setNext_value(value + INCREMENT_BY);
		repository.update(sequenceEntity);
		return value;
	}
	
	public long update(int value) {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName(SEQUENCE_NAME_COLUMN);
		sequenceEntity.setNext_value(value);
		repository.update(sequenceEntity);
		return value;
	}
	
	public long error() {
		SequenceEntity sequenceEntity = repository.select(SEQUENCE_NAME_COLUMN);
		if(sequenceEntity == null) {
			throw new NoSuchElementException();
		}
		long value = sequenceEntity.getNext_value();
		sequenceEntity.setName("error");
		sequenceEntity.setNext_value(value + INCREMENT_BY);
		System.out.println(sequenceEntity.getName());
		repository.update(sequenceEntity);
		return value;
	}
}
