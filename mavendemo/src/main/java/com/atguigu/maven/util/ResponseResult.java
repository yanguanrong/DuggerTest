package com.atguigu.maven.util;
/**
 * 服务端向客户端响应结果的类型
 * @author Administrator
 *
 * @param <T>
 */

import java.io.Serializable;

public class ResponseResult<T> implements Serializable{

	private static final long serialVersionUID = 5674600495113131799L;
	private Integer state;
	private String message;
	private T date;
	public ResponseResult() {
		super();
	}
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}
	public ResponseResult(Integer state,String message) {
		super();
		setState(state);
		setMessage(message);
	}
	public ResponseResult(Integer state,Exception e) {
		super();
		setState(state);
		setMessage(e.getMessage());
	}
	public ResponseResult(Integer state,T date) {
		super();
		setState(state);
		setDate(date);
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getDate() {
		return date;
	}
	public void setDate(T date) {
		this.date = date;
	}
	
}
