package com.mola.authx;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.mola.authx.dao")
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class AuthxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthxApplication.class, args);
    }

}
