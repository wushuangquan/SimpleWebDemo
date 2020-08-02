package cn.com.yuns.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 以注解的方式申明监听器
 *
 * @author wsq
 * @version AppListener.java  2020/7/30  上午8:32 上午
 */
@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Map<String, String> countries = new HashMap<>();
        countries.put("ca", "China");
        countries.put("jp", "Japan");
        countries.put("am", "American");
        servletContext.setAttribute("countries", countries);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
