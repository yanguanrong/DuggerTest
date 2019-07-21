package com.atguigu.maven.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atguigu.maven.entity.User;
import com.atguigu.maven.service.exception.DuplicateKeyException;
import com.atguigu.maven.service.exception.InsertException;
import com.atguigu.maven.service.exception.PasswordNotMatchException;
import com.atguigu.maven.service.exception.UserNotFoundExceptiojn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private IUserService iUserService;
	
	@Test
	public void reg() {
		User user = new User();
		Date now = new Date();

		user.setUsername("abcdeeeeel");
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
		try {
			User data = iUserService.reg(user);
			System.err.println(data);
		} catch (InsertException e) {
			System.err.println(e.getMessage());
		} catch (DuplicateKeyException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void login() {
		try {
			User user = iUserService.login("abcdeeeeel", "1234");
			System.err.println(user);
		} catch (UserNotFoundExceptiojn e) {
			System.err.println(e.getMessage());
		} catch (PasswordNotMatchException e) {
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void updatePassword() {
		User user = iUserService.updatePasswordById(1,"23211111");
		System.out.println(user);
	}
}
