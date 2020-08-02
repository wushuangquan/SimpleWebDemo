package cn.com.yuns.servlet;

import cn.com.yuns.listener.MyAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用异步监听器监听
 *
 * @author wsq
 * @version AsyncListenerServlet.java  2020/7/31  上午7:52 下午
 */
@WebServlet(name = "AsyncListenerServlet", urlPatterns = {"asyncListener"})
public class AsyncListenerServlet extends HttpServlet {

    private static final long serialVersionUID = 2399L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(5000);
        //添加异异步监听器
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String greeting = "hi form Listener";
                System.out.println("Wait ......");
                request.setAttribute("greeting", greeting);
                asyncContext.dispatch("/test.jsp");
            }
        });
    }
}

