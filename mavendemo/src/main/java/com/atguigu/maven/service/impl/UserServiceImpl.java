package com.atguigu.maven.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.atguigu.maven.entity.User;
import com.atguigu.maven.mapper.UserMapper;
import com.atguigu.maven.service.IUserService;
import com.atguigu.maven.service.exception.DuplicateKeyException;
import com.atguigu.maven.service.exception.InsertException;
import com.atguigu.maven.service.exception.PasswordNotMatchException;
import com.atguigu.maven.service.exception.UserNotFoundExceptiojn;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired(required = false)
	private UserMapper userMapper;

	@Override
	public User reg(User user) throws InsertException,DuplicateKeyException{
		//根据尝试注册的用户名查询用户数据
		User data = findByUsername(user.getUsername());
		//根据查询到的数据是否为null
		if (data == null) {
			//是 ：用户不存在 允许注册 则执行注册
			//加密 获取随机的UUID作为盐值
			String salt = UUID.randomUUID().toString();
			//获取加密后的密码
			String md5Password = getMdPassword(user.getPassword(), salt);
			//将盐值封装到user中
			user.setSalt(salt);
			//将加密后的密码封装到user中
			user.setPassword(md5Password);
			//执行注册
			addnew(user);
			return user;
		}

		//否：用户已被占用 抛出DuplicateKeyException
		throw new DuplicateKeyException("用户名已被占用");
	}
	@Override
	public User login(String username,String password)throws UserNotFoundExceptiojn,PasswordNotMatchException{
		//根据参数Username查询用户数据
		User data = findByUsername(username);
		//判断用户是否为null
		if (data == null) {
			//是：为null,用户名不存在 则抛出UserNotFoundException
			throw new UserNotFoundExceptiojn("用户不存在");
		}
		String salt = data.getSalt();
		//否：非null 根据用户名找到了数据 取出盐值
		//对参数password执行加密
		String md5password = getMdPassword(password, salt);
		//判断密码是否匹配
		if (md5password.equals(data.getPassword())) {
			//是 匹配，密码正确 则判断是否被删除
			if (data.getIsDelete() == 1) {
				// 是 ；已被删除 则抛出UserNotFoundException
				throw new UserNotFoundExceptiojn("用户已删除");
			}
			//否 ： 没被删除 则登录成功 将第一步查询的用户数据中的盐值和密码设为null
			data.setSalt(null);
			data.setPassword(null);
			//返回用户数据
			return data;
		}
		//否 密码不匹配 抛出passwordNotMacthException
		throw new PasswordNotMatchException("输入的密码不正确 请重新输入");
	}
	@Override
	public User updatePasswordById(Integer id,String password)throws UserNotFoundExceptiojn,InsertException{
		//根据参数Username查询用户数据
		User data = findById(id);
		//判断用户是否为null
		if (data == null) {
			//是：为null,用户名不存在 则抛出UserNotFoundException
			throw new UserNotFoundExceptiojn("用户不存在");
		}
		password = getMdPassword(password, data.getSalt());
		udatePassword(id, password);
		return data;
	}
	//=====================private============================
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @throws InsertException 插入用户数据时 发生未知异常
	 */
	private void addnew(User user) throws InsertException{
		Integer rows = userMapper.addnew(user);
		if (rows != 1) {
			throw new InsertException("插入用户数据时 发生未知异常");
		}
	}
	/**
	 * 根据用户名 查询用户数据
	 * @param username 用户名
	 * @return 返回的用户数据
	 */
	private User findByUsername(String username) {
		User user = userMapper.findByUsername(username);
		return user;
	}
	private User findById(Integer id) {
		User user = userMapper.findById(id);
		return user;
	}
	private void udatePassword(Integer id,String password) {
		Integer rows =  userMapper.updatePassword(id, password);
		if (rows != 1) {
			throw new InsertException("更新密码时发生 未知错误");
		}
	}
	/**
	 * 对密码进行md5加密
	 * @param srcPassword 用户输入的密码
	 * @param salt 盐
	 * 
	 * @return 加密后的密码
	 */
	private String getMdPassword(String srcPassword,String salt) {
		String str = salt+srcPassword+salt;
		for (int i = 0; i < 10; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}
	
}
