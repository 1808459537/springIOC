package com.zht.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.zht.springframework.beans.PropertyValue;
import com.zht.springframework.beans.PropertyValues;
import com.zht.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.zht.springframework.beans.factory.config.BeanDefinition;
import com.zht.springframework.beans.factory.config.BeanPostProcessor;
import com.zht.springframework.beans.factory.config.BeanReference;
import java.lang.reflect.Constructor;

public class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    //自定义对象，功能为封装java反射机制并创建对象
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    //模版模式的实现，实现父类中的抽象方法
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition ,Object args[]) {
        Object bean = null;
        try{
            //创建分两步：1类信息new对象（分配地址） 2对象填充（地址填值）
            //step1:
            bean = createBeanInstance(beanDefinition, beanName, args);

            //step2:
            applyPropertyValues(beanName, bean, beanDefinition);


        }catch (Exception e){}

        //bean创建完毕，由于该工厂分支为单例路线，所以把bean填充到单例容器中
        addSingleton(beanName, bean);

        //返回bean
        return bean;
    }


    // step1的外包工作
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try{
            //拿到bean的属性集合以及属性值
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue p:propertyValues.getPropertyValues()
                 ) {
                String name = p.getName();
                Object value = p.getValue();
                if(value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //填充属性，完整的bean诞生
                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (Exception e){

        }
    }
    //step2的外包工作
    private Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        //拿到构造器后外包出去
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor:declaredConstructors
             ) {
            if(args != null && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }

        // 反射机制的使用根据类信息创建对象，其中需要的参数只有构造器未知，上面的代码就是根据长度确定唯一的构造器
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }



    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
