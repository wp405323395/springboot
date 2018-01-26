package myggirl.wangpan.spring.aspect.security;

import myggirl.wangpan.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    AuthService authService;

    @Pointcut("execution(public * myggirl.wangpan.controller.GirlController.*(..))")
    public void pc(){}

    @Before("pc()")
    public void check(){
        authService.checkAccess();
    }

}
