package com.zht.springframework.beans.factory.config;

import com.zht.springframework.beans.PropertyValues;

public class BeanDefinition {
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;
    private String destoryMethodName;

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;
    private boolean prototype = false;


    public void setScope(String scope) {
        this.scope = scope;
        singleton = scope.equals(SCOPE_SINGLETON);
        prototype = scope.equals(SCOPE_PROTOTYPE);
    }

    public boolean isSingleton(){
        return singleton;
    }
    public String getScope() {
        return scope;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestoryMethodName() {
        return destoryMethodName;
    }

    public void setDestoryMethodName(String destoryMethodName) {
        this.destoryMethodName = destoryMethodName;
    }

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass(){
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
