package com.bjpowernode.web.listener;


import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class TomcatListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("tomcat启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("tomcat销毁了");
        //这里如果Web应用拥有多个数据库的连接，可以一并关闭
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}