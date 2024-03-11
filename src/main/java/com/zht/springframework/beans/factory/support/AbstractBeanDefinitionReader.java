package com.zht.springframework.beans.factory.support;

import com.zht.springframework.core.io.DefaultResourceLoader;
import com.zht.springframework.core.io.Resource;
import com.zht.springframework.core.io.ResourceLoader;



public class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private  ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {

    }

    @Override
    public void loadBeanDedinitions(Resource... resources) {

    }

    @Override
    public void loadBeanDefinitions(String location) {

    }

    @Override
    public void loadBeanDefinitions(String... locations) {

    }
}
