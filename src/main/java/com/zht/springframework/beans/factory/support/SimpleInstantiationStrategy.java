package com.zht.springframework.beans.factory.support;

import com.zht.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try{
            if(null != args){
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
            else return clazz.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            return null;
        }
    }
}
