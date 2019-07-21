package com.atguigu.maven.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.maven.entity.User;
import com.atguigu.maven.service.IUserService;
import com.atguigu.maven.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		User data = userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	@RequestMapping("/login.do")
	public ResponseResult<Void> handleLogin(String username,String password){
		User data = userService.login(username, password);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/{id}")
	public ResponseResult<Void> updateById(@PathVariable int id,
											@RequestParam(value="password",required=true)String password)
	{
		userService.updatePasswordById(id, password);
		return new ResponseResult<>(SUCCESS);
	}
	@DeleteMapping			
	public ResponseResult<Void> deleteById(@PathVariable int id){
		return null;
	}
}
