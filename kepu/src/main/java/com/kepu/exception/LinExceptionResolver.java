package com.kepu.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class LinExceptionResolver implements HandlerExceptionResolver {

	/**
	 * （非 Javadoc）
	 * <p>Title: resolveException</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex 系统 抛出的异常
	 * @return
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	/*public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//handler就是处理器适配器要执行Handler对象（只有method）
		
//		解析出异常类型
//		如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
		
		//上边代码变为
		LinOverAllException linOverAllException = null;
		if(ex instanceof LinOverAllException){
			linOverAllException = (LinOverAllException)ex;
		}else{
			linOverAllException = new LinOverAllException("系统崩溃了，程序员哥哥正在修复");
		}
		
		//错误信息
		String message = linOverAllException.getMessage();
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		
		//指向错误页面
		modelAndView.setViewName("error");

		
		return modelAndView;
	}*/
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//handler就是处理器适配器要执行Handler对象（只有method）
		
//		解析出异常类型
//		如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
		
		//上边代码变为
		/*LinOverAllException linOverAllException = null;
		if(ex instanceof LinOverAllException){
			linOverAllException = (LinOverAllException)ex;
		}else{
			linOverAllException = new LinOverAllException("系统错误");
		}*/
		String message = ex.getMessage();
		request.setAttribute("message", message);
		request.setAttribute("ex", ex);
		try {
			request.getRequestDispatcher("/user/login2.action").forward(request, response);
			return null;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
