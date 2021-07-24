package com.springbook.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

public interface MyMapper {

	@Select("SELECT 21")
	public String getTime();
	
	public String getTime3();
}
