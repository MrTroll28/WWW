package me.kn.midterm1;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import me.kn.midterm1.util.JPAUtil;

@WebListener
public class Application implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JPAUtil.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAUtil.destroy();
    }
}
