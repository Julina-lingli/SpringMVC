package com.xll.myspringmvc;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/systeminfo")
public class MyHttpServletBeanSystemInfo implements ApplicationContextAware,
        EnvironmentAware, BeanNameAware, BeanFactoryAware, InitializingBean {

//    private String systeminfo = "Hello!!!";
    private String systeminfo;
    private int id;

    private ApplicationContext applicationContext;
    private Environment environment;
    private String beanName;
    private BeanFactory beanFactory;

    public MyHttpServletBeanSystemInfo() {
        System.out.println("这是MyHttpServletBeanSystemInfo实现类构造器！！");
    }

    public String getSysteminfo() {
        return systeminfo;
    }

    public void setSysteminfo(String systeminfo) {
        System.out.println("【注入属性】setSysteminfo（）:" + systeminfo);
        this.systeminfo = systeminfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("【注入属性】setId（）:" + id);
        this.id = id;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryAware接口】调用setBeanFactory()：" +
                beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("【BeanNameAware接口】调用setBeanName()：" +
                beanName);
        this.beanName = beanName;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【ApplicationContextAware接口】调用setApplicationContext()"
                + applicationContext.getClass());
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("【EnvironmentAware接口】调用setEnvironment（）：" + environment.getClass());
        this.environment = environment;
    }
    @RequestMapping("/info")
    @Override
    public String toString() {
        return this.getClass().getName() + " [systeminfo:" + systeminfo + " id:" + id;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean接口】调用afterPropertiesSet()");

    }

    @RequestMapping("/env")
    public String environment(){
        StandardServletEnvironment sse = (StandardServletEnvironment)environment;
        Map<String, Object> envs = sse.getSystemEnvironment();

        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------++ System Environment ++-------------------------\n");

        List<String> list = new ArrayList<>();
        list.addAll(envs.keySet());

        for(int i = 0; i < 5 && i < list.size(); i++){
            String key = list.get(i);
            Object val = envs.get(key);
            sb.append(String.format("%s = %s\n", key, val.toString()));
        }

        Map<String, Object> props = sse.getSystemProperties();
        sb.append("\\n-------------------------++ System Properties ++-------------------------\n");
        list.clear();
        list.addAll(props.keySet());
        for(int i = 0; i < 5 && i < list.size(); i++){
            String key = list.get(i);
            Object val = props.get(key);
            sb.append(String.format("%s = %s \n", key, val.toString()));
        }

        return sb.toString();



    }

    @RequestMapping("/beans")
    public String listBeans(){
        ListableBeanFactory lbf = applicationContext;
        String[] beanNames = lbf.getBeanDefinitionNames();
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------++ Bean Info ++-------------------------\n");
        for (String b:beanNames) {
            Object bean = lbf.getBean(b);
            sb.append(String.format("beanName = %s\n", b));
            sb.append(String.format("beanClass = %s\n", bean.getClass().toString()));
            
        }
        return sb.toString();


    }
}
