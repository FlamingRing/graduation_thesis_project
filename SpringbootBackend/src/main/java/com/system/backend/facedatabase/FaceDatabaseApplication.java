package com.system.backend.facedatabase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.system.backend.facedatabase.mapper")  //扫描自定义的mapper接口 自动通过spring容器创建其对象
public class FaceDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceDatabaseApplication.class, args);
	}

}
