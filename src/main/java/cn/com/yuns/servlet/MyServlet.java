package cn.com.yuns.servlet;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 直接实现 Servlet 定义自己的 Servlet
 *
 * @author wsq
 * @version MyServlet.java  2020/7/28  上午8:01 下午
 */
@Getter
@Setter
@WebServlet(name = "MyServlet", urlPatterns = "/my", initParams = {@WebInitParam(name = "name", value = "wsq"), @WebInitParam(name = "age", value = "18")})
public class MyServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        System.out.println("init MyServlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        return getServletConfig();
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String servletName = servletConfig.getServletName();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head></head>" +
                "<body>" + "hello From " + servletName + "<br>"
                + "ContentLength：" + request.getContentLength() + "<br>" +
                "ContentType：" + request.getContentType() + "<br>" +
                "AttributeNames：" + request.getAttributeNames() + "<br>" +
                "CharacterEncoding：" + request.getCharacterEncoding() + "<br>" +
                "getParameterNames：" + request.getParameterNames() + "<br>" +
                "getParameterMap：" + request.getParameterMap() + "<br>" +
                "getProtocol：" + request.getProtocol() + "<br>" +
                "getScheme：" + request.getScheme() + "<br>" +
                "getServerName：" + request.getServerName() + "<br>" +
                "getServerPort：" + request.getServerPort() + "<br>" +
                "getRemoteAddr：" + request.getRemoteAddr() + "<br>" +
                "getRemoteHost：" + request.getRemoteHost() + "<br>" +
                "getLocale：" + request.getLocale() + "<br>" +
                "getLocales：" + request.getLocales() + "<br>" +
                "isSecure：" + request.isSecure() + "<br>" +
                "getRemotePort：" + request.getRemotePort() + "<br>" +
                "getLocalName：" + request.getLocalName() + "<br>" +
                "getLocalAddr：" + request.getLocalAddr() + "<br>" +
                "getLocalPort：" + request.getLocalPort() + "<br>" +
                "isAsyncStarted：" + request.isAsyncStarted() + "<br>" +
                "isAsyncSupported：" + request.isAsyncSupported() + "<br>" +
                request.getParameter("id") +
                "<br>" +
                "<br>" +
                "servletConfig.getServletName()：" + servletConfig.getServletName() + "<br>" +
                "servletConfig.getInitParameter(\"name\")：" + servletConfig.getInitParameter("name") + "<br>" +
                "servletConfig.getInitParameterNames()：" + servletConfig.getInitParameterNames() + "<br>" +

                "</body></html>");
        writer.flush();
    }

    @Override
    public String getServletInfo() {
        return "My Servlet";
    }

    @Override
    public void destroy() {

    }
}
