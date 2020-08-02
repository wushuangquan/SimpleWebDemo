package cn.com.yuns.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wsq
 * @version AsyncDispatcherServlet.java  2020/7/31  上午7:06 下午
 */
@WebServlet(name = "AsyncDispatcherServlet", urlPatterns = {"/asyncDispatcher"}, asyncSupported = true)
public class AsyncDispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 2992L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final AsyncContext asyncContext = request.startAsync();
        request.setAttribute("mainThread", Thread.currentThread().getName());
        //5000 毫秒
        asyncContext.setTimeout(5000);
        //以下模拟长时间的任务--这个长时间的任务不是在主线程中进行的，是在别的线程中进行的
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request.setAttribute("workThread", Thread.currentThread().getName());
                // 任务结束之后必须调用  asyncContext.dispatch 或 asyncContext.complete()
                //要不然，即使任务执行完毕也会等到设置的超时时间之后才结束
                asyncContext.dispatch("threadNames.jsp");
            }
        });

    }
}
