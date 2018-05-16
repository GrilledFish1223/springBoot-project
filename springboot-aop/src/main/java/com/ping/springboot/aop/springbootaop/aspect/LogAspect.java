package com.ping.springboot.aop.springbootaop.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import com.ping.springboot.aop.springbootaop.annotation.Action;
import org.springframework.stereotype.Component;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/16 16:07
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(com.ping.springboot.aop.springbootaop.annotation.Action)")
    public void log() {

    }

    /**
     * 前置通知
     */
    @Before("log()")
    public void doBeforeController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("action名称 " + action.value());
    }

    /**
     * 后置通知
     */
    @AfterReturning(pointcut = "log()", returning = "retValue")
    public void doAfterController(JoinPoint joinPoint, Object retValue) {
        System.out.println("retValue is:" + retValue);
    }
}
