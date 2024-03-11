package com.zht.springframework.beans.factory.support;

import com.zht.springframework.core.io.Resource;
import com.zht.springframework.core.io.ResourceLoader;
// 代替手动装配的类
public interface BeanDefinitionReader {
    // 持有容器
    BeanDefinitionRegistry getRegistry();

    // 持有资源加载的手段
    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);
    void loadBeanDedinitions(Resource...resources);
    void loadBeanDefinitions(String location);
    void loadBeanDefinitions(String... locations);
}
