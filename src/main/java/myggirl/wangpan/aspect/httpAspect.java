package myggirl.wangpan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class httpAspect {
    private final static Logger logger = LoggerFactory.getLogger(httpAspect.class);
    @Pointcut("execution(public * myggirl.wangpan.controller.GirlController.*(..))")
    public void pc(){}

    @Before("pc()")
    public void logUrl(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("开始请求");
        logger.info("url={}",request.getRequestURL());

    }

    @After("pc()")
    public void enRequest() {
        logger.info("请求结束");
    }

    //请求返回log
    @AfterReturning(returning = "object", pointcut = "pc()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }

}
