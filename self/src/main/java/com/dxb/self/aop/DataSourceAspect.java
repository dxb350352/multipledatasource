package com.dxb.self.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Pointcut("@within(com.dxb.self.aop.DataSource) || @annotation(com.dxb.self.aop.DataSource)")
    public void pointCut() {
    }

    //    @Before(value = "pointCut()")
    private void doBefore(JoinPoint jp) {
        DataSource dataSource = null;
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Class cls = method.getDeclaringClass();
        if (method.isAnnotationPresent(DataSource.class)) {
            dataSource = method.getAnnotation(DataSource.class);
        } else if (cls.isAnnotationPresent(DataSource.class)) {
            dataSource = AnnotationUtils.findAnnotation(cls, DataSource.class);
        }
        if (dataSource != null) {
            DataSourceContextHolder.setDataSource(dataSource.value().getKey());
        }
    }

    /**
     * 类上所有方法都加注解
     * 同一个@Aspect类下的相同方法(@Before)执行顺序是方法名大小排序，没有用到@Order
     *
     * @param dataSource
     */
    @Before("pointCut() && @within(dataSource)")
    public void doBefore1Within(DataSource dataSource) {
        DataSourceContextHolder.setDataSource(dataSource.value().getKey());
    }

    /**
     * 删除类的注解，用方法的注解
     *
     * @param dataSource
     */
    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore2Annotation(DataSource dataSource) {
        DataSourceContextHolder.clear();
        DataSourceContextHolder.setDataSource(dataSource.value().getKey());
    }


    @After("pointCut()")
    private void doAfter() {
        DataSourceContextHolder.clear();
    }

}
