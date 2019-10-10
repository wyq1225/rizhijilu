package com.newer.aspect;

import com.newer.pojo.RequestMessage;
import com.newer.respository.LoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

/**
 * 用于获取请求日志、并将请求日志写入MongoDB等切面
 */

@Component
@Aspect
public class LoggerAspect {
    private static final Logger log= LoggerFactory.getLogger(LoggerAspect.class);

    @Autowired
    private LoggerRepository rep;

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.newer.controller.*.*(..))")//controller 包下任意类任意方法任意参数和返回值
    public void weblog(){

    }

    @Before("weblog()")
    public void before(JoinPoint jp){
        //1.获取请求参数
        RequestAttributes ra= RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra= (ServletRequestAttributes) ra;
        HttpServletRequest request=sra.getRequest();
        //2.封装数据
        String id= UUID.randomUUID().toString();
        String url=request.getRequestURI();
        String method=request.getMethod();
        String signame=jp.getSignature().getName();
        String args= Arrays.toString(jp.getArgs());

        RequestMessage req=new RequestMessage(id, url, method, signame, args);
        //3.调用DAO将数据写入MongoDB
        log.info("开始将日志信息写入Mongodb,id:{}",id);
        rep.save(req);
        log.info("写入Mongodb完成");
    }



}
