package com.atguigu.maven.db;

import java.sql.SQLException;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTestCase {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void getConnection() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
	
}







