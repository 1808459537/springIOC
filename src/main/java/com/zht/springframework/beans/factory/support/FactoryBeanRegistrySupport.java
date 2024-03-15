package com.zht.springframework.beans.factory.support;

import com.zht.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String ,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object =this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ?object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName){
        if(factoryBean.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object == null){
                object =  doGetObjectFromFactoryBean(factoryBean , beanName);
                this.factoryBeanObjectCache.put(beanName , object);
            }
            return (object != NULL_OBJECT ?object : null);
        }
        else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            return factory.getObject();
        } catch (Exception e) {
            System.out.println("com.zht.springframework.beans.factory.support.FactoryBeanRegistrySupport" + "出错");
            return null;
        }
    }

}
