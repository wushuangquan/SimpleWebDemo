package cn.com.yuns.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 不使用 @WebServlet 而是使用 部署描述符 web.xml 注意注解形式是没有 <load-on-startup> 属性的，只有部署描述符有
 *
 * @author wsq
 * @version SimpleServlet.java  2020/7/28  上午10:17 下午
 */
@WebServlet
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head>WelcomeServlet</head><br>" +
                "<body>Welcome Servlet</body></html>");
    }
}
