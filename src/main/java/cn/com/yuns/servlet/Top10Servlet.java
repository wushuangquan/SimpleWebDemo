package cn.com.yuns.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 网址重写是一种 session 追踪技术，需要将一个或多个Token作为一个查询字符串添加到一个URL中，
 * Token的格式一般是键值对—适用于 Token不必在多个URL中携带；
 *
 * @author wsq
 * @version Top10Servlet.java  2020/7/29  上午8:54 上午
 */
@WebServlet(name = "Top10Servlet",urlPatterns = {"/top10"})
public class Top10Servlet extends HttpServlet {

    private List<String> londonAttractions;

    private List<String> parisAttractions;

    @Override
    public void init() throws ServletException {
        londonAttractions = new ArrayList<>(10);
        londonAttractions.add("londonAttractionOne");
        londonAttractions.add("londonAttractionTwo");
        londonAttractions.add("londonAttractionThree");
        londonAttractions.add("londonAttractionFore");
        londonAttractions.add("londonAttractionFive");
        londonAttractions.add("londonAttractionSix");
        londonAttractions.add("londonAttractionSeven");
        londonAttractions.add("londonAttractionEight");
        londonAttractions.add("londonAttractionNine");
        londonAttractions.add("londonAttractionTen");
        parisAttractions = new ArrayList<>(10);
        parisAttractions.add("parisAttractionOne");
        parisAttractions.add("parisAttractionTwo");
        parisAttractions.add("parisAttractionThree");
        parisAttractions.add("parisAttractionFore");
        parisAttractions.add("parisAttractionFive");
        parisAttractions.add("parisAttractionSix");
        parisAttractions.add("parisAttractionSeven");
        parisAttractions.add("parisAttractionEight");
        parisAttractions.add("parisAttractionNine");
        parisAttractions.add("parisAttractionTen");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        if (city != null && (city.equals("london") || city.equals("paris"))) {
            showAttractions(request, response, city);
        } else {
            showMainPage(request, response);
        }
    }

    private void showMainPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head><title>Top 10 Tourist Attractions</title></head>" +
                "<body>Please select a City：<br>" +
                "<a href='?city=london'>London</a><br>" +
                "<a href='?city=paris'>Paris</a>" +
                "</body></html>");
    }

    private void showAttractions(HttpServletRequest request, HttpServletResponse response, String city) throws IOException {
        int page = 1;
        String pageParam = request.getParameter("page");
        if (null != pageParam) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (Exception e) {

            }
            if (page > 2) {
                page = 1;
            }
        }
        List<String> attractions = null;
        if (city.equals("london")) {
            attractions = londonAttractions;
        } else {
            attractions = parisAttractions;
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head><title>Top 10 Tourist Attractions</title></head>");
        writer.print("<body><a href='top10'>Select City</a>");
        writer.print("<hr/>Page " + page + "</hr><br>");
        int start = page * 5 - 5;
        for (int i = start; i < start + 5; i++) {
            writer.println(attractions.get(i)+"<br>");
        }
        writer.print("<hr style='color:blue' />" +
                "<a href='?city="+city+
                "&page=1'>Page 1</a>");
        writer.println("&nbsp; <a href='?city=" +
                city+ "&page=2'>Page 2</a>");
        writer.println("</body></html>");
    }
}
