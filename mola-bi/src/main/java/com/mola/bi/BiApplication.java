package com.mola.bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * BI数据分析
 *
 * @author hatim
 */
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.mola.bi.dao")
public class BiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiApplication.class, args);
    }

}
