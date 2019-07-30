package com.xll.myspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyspringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringmvcApplication.class, args);

        new Thread(new Runnable(){
            @Override
            public void run() {

            }
        }).start();
    }

}
