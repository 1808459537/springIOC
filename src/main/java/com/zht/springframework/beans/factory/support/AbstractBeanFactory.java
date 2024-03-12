package com.zht.springframework.beans.factory.support;
import com.zht.springframework.beans.factory.config.BeanDefinition;
import com.zht.springframework.beans.factory.config.BeanPostProcessor;
import com.zht.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.zht.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public ClassLoader getBeanClassLoader(){
        return classLoader;
    }
    @Override
    public Object getBean(String name) {
        return doGetBean(name ,null);
    }

    @Override
    public Object getBean(String name , Object ...args) {
      return doGetBean(name ,args);
    }



    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected  <T>T  doGetBean(String name, Object[] args){
        Object bean = getSingleton(name);
        if(bean != null){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name , beanDefinition , args);
    };

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition ,Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

}
