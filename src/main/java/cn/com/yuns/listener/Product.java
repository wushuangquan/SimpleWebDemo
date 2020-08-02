package cn.com.yuns.listener;

import lombok.Data;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author wsq
 * @version Product.java  2020/7/30  上午11:13 上午
 */
@WebListener
@Data
public class Product implements HttpSessionBindingListener {

    private String id;

    private String name;

    private double price;

    /**
     * 绑定时调用该方法
     *
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        System.out.println(attributeName + " valueBound");
    }

    /**
     * 解绑时调用该方法
     *
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        System.out.println(attributeName + " valueUnbound");
    }
}
