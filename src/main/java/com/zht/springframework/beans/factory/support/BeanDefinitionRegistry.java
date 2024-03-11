package com.zht.springframework.beans.factory.support;

import com.zht.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    // BeanDefinition容器接口
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    public BeanDefinition getBeanDefinition(String beanName);
    boolean containsBeanDefinition(String beanName);
    String[] getBeanDefinitionNames();
}
