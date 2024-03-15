package com.zht.springframework.beans.factory;

public interface FactoryBean<T> {
    T getObject();

    Class<?> getObjectType();

    boolean isSingleton();
}
