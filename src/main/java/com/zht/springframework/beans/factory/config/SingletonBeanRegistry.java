package com.zht.springframework.beans.factory.config;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
