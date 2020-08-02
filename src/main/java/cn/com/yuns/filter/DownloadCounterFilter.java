package cn.com.yuns.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wsq
 * @version DownloadCounterFilter.java  2020/7/30  上午9:50 下午
 */
@WebFilter(filterName = "DownloadCounterFilter", urlPatterns = {"/*"})
public class DownloadCounterFilter implements Filter {

    ExecutorService executors = Executors.newSingleThreadExecutor();

    Properties downloadLog;

    File logFile;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DownloadCounterFilter");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        logFile = new File(appPath, "downloadLog.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadLog = new Properties();
        try {
            downloadLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final String uri = httpServletRequest.getRequestURI();
        executors.execute(new Runnable() {
            @Override
            public void run() {
                String propertie = downloadLog.getProperty(uri);
                if (null == propertie) {
                    downloadLog.setProperty(uri, "1");
                } else {
                    int count = 0;
                    try {
                        count = Integer.parseInt(propertie);
                    } catch (Exception e) {
                    }
                    count++;
                    downloadLog.setProperty(uri, Integer.toString(count));
                    try {
                        downloadLog.store(new FileWriter(logFile), "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        executors.shutdown();
    }
}
