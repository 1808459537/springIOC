package com.zht.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.zht.springframework.beans.factory.DisposableBean;
import com.zht.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean ;
    private final String beanNaem;
    private String destoryMethodName;

    public DisposableBeanAdapter(Object bean, String beanNaem , BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanNaem = beanNaem;
        destoryMethodName = beanDefinition.getDestoryMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        if(StrUtil.isNotEmpty(destoryMethodName) && !(bean instanceof DisposableBean && "destory".equals(this.destoryMethodName))){
            Method method = bean.getClass().getMethod(destoryMethodName);
            method.invoke(bean);
        }
    }
}
