package com.atguigu.maven.mapper;

import org.apache.ibatis.annotations.Param;

import com.atguigu.maven.entity.User;

/**
 * 处理用户数据的持久层
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 返回受影响的行数
	 */
	Integer addnew(User user);
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return
	 */
	User findByUsername(String username);
	/**
	 * 根据用户id查询用户数据
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	
	Integer updatePassword(
            @Param("uid") Integer uid,
            @Param("password") String password
    );
}
