package com.springbook.biz;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 파일 변경 감지 - mapper.xml
 */
@Component
public class InitExampleBean implements DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(InitExampleBean.class);

	@Autowired
	private Environment env;

	@PostConstruct
	public void init() {
		log.info("init", env.getActiveProfiles());
	}

	@Override
	public void destroy() throws Exception {
	}
}
