package com.springbook.biz.transaction.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.springbook.biz.transaction..*Impl.*(..))")
	public void allPointcut() {
	}

	@Pointcut("execution(* com.springbook.biz.transaction..*Impl.get*(..))")
	public void getPointcut() {
	}
}
