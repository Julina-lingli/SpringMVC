package com.xll.myspringmvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MyInstantationBeanPostProcessor
        extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantationBeanPostProcessor() {
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！" +
                "--实例化bean时的扩展接口");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanName.equals("myHttpServletBeanSystemInfo")) {
            System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法："
                    + beanName);
        }

        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(beanName.equals("myHttpServletBeanSystemInfo")){
            System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInstantiation方法："
                    + beanName);
        }

        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if(beanName.equals("myHttpServletBeanSystemInfo")){
            System.out.println("InstantiationAwareBeanPostProcessor调用postProcessProperties方法："
                    + beanName);
        }

        return super.postProcessProperties(pvs, bean, beanName);
    }
}
