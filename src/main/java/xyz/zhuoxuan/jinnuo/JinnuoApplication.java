package xyz.zhuoxuan.jinnuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"xyz.zhuoxuan.jinnuo.mapper","xyz.zhuoxuan.jinnuo.log.dao"})
public class JinnuoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinnuoApplication.class, args);
	}

}
