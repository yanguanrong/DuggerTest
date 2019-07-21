package com.atguigu.maven.comtroller;
/**
 * 当前项目中所有控制器类的基类
 * @author Administrator
 *
 */

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.maven.service.exception.DuplicateKeyException;
import com.atguigu.maven.service.exception.InsertException;
import com.atguigu.maven.service.exception.PasswordNotMatchException;
import com.atguigu.maven.service.exception.ServiceException;
import com.atguigu.maven.service.exception.UserNotFoundExceptiojn;
import com.atguigu.maven.util.ResponseResult;

public class BaseController {
	/**
	 * 正确响应的代码
	 */
	public static final Integer SUCCESS = 200;
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		Integer state = null;
		if (e instanceof DuplicateKeyException) {
			// 400-违反了Unique约束的异常
			state = 400;
		}
		else if (e instanceof InsertException) {
			//500-插入数据类型
			state = 500;
		}
		else if (e instanceof UserNotFoundExceptiojn) {
			//401-用户不存在
			state = 401;
		}
		else if (e instanceof PasswordNotMatchException) {
			//402-用户密码不匹配
			state = 402;
		}
		return new ResponseResult<Void>(state,e);
	}
}
