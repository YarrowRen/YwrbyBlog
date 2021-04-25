package cn.ywrby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 扫描该包下所有mybatis业务mapper接口，传入参数是接口所在包路径
@MapperScan("cn.ywrby.mapper")
// @SpringBootApplication注解表明该类是一个SpringBoot应用
@SpringBootApplication
public class YwrbyBlogApplication {
    public static void main(String[] args) {
        //run方法 启动SpringBoot应用
        SpringApplication.run(YwrbyBlogApplication.class, args);
    }
}
