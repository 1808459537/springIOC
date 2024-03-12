package com.zht.springframework.beans.factory;

import org.springframework.beans.factory.Aware;

public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader classLoader);
}
