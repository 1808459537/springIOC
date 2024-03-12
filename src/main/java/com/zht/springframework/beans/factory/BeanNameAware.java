package com.zht.springframework.beans.factory;

import org.springframework.beans.factory.Aware;

public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
