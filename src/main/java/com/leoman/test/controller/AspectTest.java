//package com.leoman.test.controller;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.aop.ThrowsAdvice;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class AspectTest{
//
//	public AspectTest(){
//		System.out.println("��ʼ������...");
//	}
//
//	//ǰ��֪ͨ
//	@Before(value="execution(* com.leoman.test.controller.UserController.user*(..))")
//	public void doBefore(JoinPoint jp){
//		//JoinPointȡֵʾ��
//		Object[] obj = jp.getArgs();
//		for(int i=0;i<obj.length;i++){
//			System.out.println("����"+i+":"+obj[i]);
//		}
//		System.out.println("ִ����ǰ��֪ͨ");
//	}
//}
