package cn.ywrby.config;


import cn.ywrby.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 标识该类为配置类
@Configuration
public class MvcConfig implements WebMvcConfigurer { //继承WebMvcConfigurer以实现对SpringMVC组件的控制

    //在SpringMVC容器中注册拦截器
    @Bean //使用在方法上，标注将该方法返回值存储到Spring容器中
    public TestInterceptor testInterceptor(){
        return new TestInterceptor();
    }

    //将拦截器添加到SpringMVC拦截器链中，复写该方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor()).addPathPatterns("/*");
    }
}
