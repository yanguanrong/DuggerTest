package com.atguigu.maven.mapper;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atguigu.maven.entity.User;
import com.atguigu.maven.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	@Autowired(required = false)
	private UserMapper userMapper;
	@Test
	public void addnew() {
		Date now = new Date();
		User user = new User();
		user.setUsername("xxx000000");
		user.setPassword("1234");
		user.setGender(1);
		user.setPhone("13800138001");
		user.setEmail("root@tedu.cn");
		user.setSalt("Hello,MD5!");
		user.setIsDelete(0);
		user.setCreatedUser("Admin");
		user.setModifiedUser("Admin");
		user.setCreatedTime(now);
		user.setModifiedTime(now);
		Integer rows = userMapper.addnew(user);
		System.err.println(rows);
	}
	@Test
	public void updatePassword() {
		Integer rows = userMapper.updatePassword(1,"2323");
		System.out.println(rows);
	}
}
