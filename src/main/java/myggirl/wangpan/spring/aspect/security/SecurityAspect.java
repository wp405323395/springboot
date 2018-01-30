package myggirl.wangpan.spring.aspect.security;

import myggirl.wangpan.service.AuthService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SecurityAspect {
    @Autowired
    AuthService authService;

    @Pointcut("execution(public * myggirl.wangpan.controller.GirlController.*(..))")
    public void pc(){}

    @Before("pc()")
    public void check(JoinPoint joinPoint){
        MethodSignature signature =  (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("拦截调用的方法名称是->"+method.getName());
        if("girlList".equals(method.getName())||
                "err".equals(method.getName())||
                "findOne".equals(method.getName())||
                "findByAge".equals(method.getName())||
                "addGirl".equals(method.getName())) {
        } else {
            authService.checkAccess();
        }

    }

}
