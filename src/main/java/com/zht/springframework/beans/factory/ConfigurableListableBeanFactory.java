package com.zht.springframework.beans.factory;

import com.zht.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.zht.springframework.beans.factory.config.BeanDefinition;
import com.zht.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName);
    void preInstantiateSingletons();
}
