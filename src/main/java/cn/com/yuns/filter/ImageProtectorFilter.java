package cn.com.yuns.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ImageProtectorFilter", urlPatterns = {"*.png", "*.jpg", "*.gif","*.jpeg"})
public class ImageProtectorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ImageProtectorFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String referer = httpServletRequest.getHeader("referer");
        System.out.println("referer：" + referer);
        if (referer != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new ServletException("Image not Available");
        }
    }

}
