package com.ldb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        System.setProperty("druid.mysql.usePingMethod", "false");//关闭druid的ping检测,提高性能
        System.setProperty("spring.devtools.restart.enabled", "false"); //关闭热部署,提高性能
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("LDBMS START!");
    }
}
