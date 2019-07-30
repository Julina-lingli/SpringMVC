package com.xll.myspringmvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(beanName.equals("myHttpServletBeanSystemInfo")){
            System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！"
                    + beanName);
            System.out.println(((MyHttpServletBeanSystemInfo)bean).toString());
        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("myHttpServletBeanSystemInfo")){
            System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！"
                    + beanName);
        }

        return bean;
    }

    public MyBeanPostProcessor() {
        System.out.println("这是BeanPostProcessor实现类构造器！！" + this.getClass());

    }


}
