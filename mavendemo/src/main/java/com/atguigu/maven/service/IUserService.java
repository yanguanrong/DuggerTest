package com.atguigu.maven.service;

import com.atguigu.maven.entity.User;
import com.atguigu.maven.service.exception.DuplicateKeyException;
import com.atguigu.maven.service.exception.InsertException;
import com.atguigu.maven.service.exception.PasswordNotMatchException;
import com.atguigu.maven.service.exception.UserNotFoundExceptiojn;

public interface IUserService {
	/**
	 * 注册
	 * @param user 用户数据
	 * @return
	 * @throws InsertException 插入数据时 发生未知异常 
	 * @throws DuplicateKeyException 用户名已经被占用
	 */
	User reg(User user)throws InsertException,DuplicateKeyException;
	/**
	 * 登录
	 * @param username
	 * @param passoword
	 * @return
	 * @throws UserNotFoundExceptiojn
	 * @throws PasswordNotMatchException
	 */
	User login(String username, String passoword)throws UserNotFoundExceptiojn,PasswordNotMatchException;
	
	User updatePasswordById(Integer id, String password)throws UserNotFoundExceptiojn,InsertException;
}
