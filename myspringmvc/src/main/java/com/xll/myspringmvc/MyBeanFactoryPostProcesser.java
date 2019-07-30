package com.xll.myspringmvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class MyBeanFactoryPostProcesser implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcesser() {
        System.out.println("这是BeanFactoryPostProcesser实现类构造器！！" + this.getClass());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        Iterator beanIterator = beanFactory.getBeanNamesIterator();
        while (beanIterator.hasNext()){
            System.out.println("getBeanNamesIterator: " + beanIterator.next());
        }
        BeanDefinition bd = beanFactory.getBeanDefinition("myHttpServletBeanSystemInfo");
        System.out.println("BeanFactory bd BeanClassName:" + bd.getBeanClassName());
        System.out.println("BeanFactory bd FactoryBeanName:" + bd.getFactoryBeanName());
        System.out.println("BeanFactory bd InitMethodName:" + bd.getInitMethodName());
        System.out.println("BeanFactory bd FactoryMethodName:" + bd.getFactoryMethodName());
        System.out.println("BeanFactory bd getPropertyValues:" + bd.getPropertyValues().toString());
        System.out.println("BeanFactory bd get systeminfo:" + bd.getPropertyValues().getPropertyValue("systeminfo"));

        bd.getPropertyValues().addPropertyValue("systeminfo", "Hello,Spring!!");



    }
}
