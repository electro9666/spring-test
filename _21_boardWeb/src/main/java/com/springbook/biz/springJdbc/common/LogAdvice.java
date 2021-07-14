package com.springbook.biz.springJdbc.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect = Pointcut + Advice
public class LogAdvice {
	@Before("PointcutCommon.allPointcut()")
	public void printLog(){
		System.out.println("[���� �α�] ����Ͻ� ���� ���� �� ����");
	}
}
