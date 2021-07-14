package com.springbook.biz.springJdbc.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.springbook.biz.springJdbc..*Impl.*(..))")
	public void allPointcut() {
	}

	@Pointcut("execution(* com.springbook.biz.springJdbc..*Impl.get*(..))")
	public void getPointcut() {
	}
}
