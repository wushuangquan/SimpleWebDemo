package cn.com.yuns.listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * 与其他Web监听器不同的是，它没有用@WebListener标注  AsyncListener 的实现。
 *
 * @author wsq
 * @version MyAsyncListener.java  2020/7/31  上午7:44 下午
 */
//不需要  @WebListener 标注
public class MyAsyncListener implements AsyncListener {

    /**
     * 异步操作完成时调用这个方法
     *
     * @param asyncEvent
     * @throws IOException
     */
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onComplete");
    }

    /**
     * 异步操作超时时调用这个方法
     *
     * @param asyncEvent 方法的参数都是 AsyncEvent 事件，可以通过调用 getAsyncContext，
     *                   getSuppliedRequest 和getSuppliedResponse 方法从中获得相关的 AsyncContext，
     *                   ServletRequest，ServletResponse实例
     * @throws IOException
     */
    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onTimeout");
    }

    /**
     * 异步操作失败时调用这个方法
     *
     * @param asyncEvent
     * @throws IOException
     */
    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onError");
    }

    /**
     * 刚启动一个异步操作时调用这个方法
     *
     * @param asyncEvent
     * @throws IOException
     */
    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("onStartAsync");
    }
}
