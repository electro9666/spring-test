package com.springbook.biz.springJdbc.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

//import com.springbook.biz.springJdbc.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice {
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
//		if (returnObj instanceof UserVO) {
//			UserVO user = (UserVO) returnObj;
//			if (user.getRole().equals("Admin")) {
//				System.out.println(user.getName() + " �α���(Admin)");
//			}
//		}
		System.out.println("[���� ó��] " + method + "() �޼ҵ� ���ϰ� : " + returnObj.toString());
	}
}