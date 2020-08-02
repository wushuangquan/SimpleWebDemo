package cn.com.yuns.servlet;

import cn.com.yuns.servlet.VO.CustomerVO;

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
 * @author wsq
 * @version CustomerServlet.java  2020/7/29  上午4:59 下午
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer", "/editCustomer", "/updateCustomer"})
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 54L;

    List<CustomerVO> customers = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setId(1);
        customerVO.setName("Donald D.");
        customerVO.setCity("Miami");
        customers.add(customerVO);
        CustomerVO customer = new CustomerVO();
        customer.setId(2);
        customer.setName("Mickey M.");
        customer.setCity("Orlando");
        customers.add(customer);
    }

    private void sendCustomerList(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Customers</title></head>" +
                "<body><h2>Customers </h2>");
        writer.println("<ul>");
        for (CustomerVO customer : customers) {
            writer.println("<li>" + customer.getName()
                    + "(" + customer.getCity() + ") ("
                    + "<a href='editCustomer?id=" + customer.getId()
                    + "'>edit</a>)");
            writer.println("</ul>");
            writer.println("</body></html>");
        }
    }

    private CustomerVO getCustomer(Integer customerId) {
        for (CustomerVO customerVO : customers) {
            if (customerId.equals(customerVO.getId())) {
                return customerVO;
            }
        }
        return null;
    }

    private void sendEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int customerId = 0;
        try {
            customerId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {

        }
        CustomerVO customerVO = getCustomer(customerId);
        if (customerVO != null) {
            writer.println("<html><head>" +
                    "<title>Edit Customer</title></head>"
                    + "<body><h2>Edit Customer</h2>"
                    + "<form method='post' "
                    + "action= 'updateCustomer'>");
            writer.println("<input type= 'hidden' name='id' value='"
                    + customerId +
                    "'/>");
            writer.println("<table>");
            writer.println("<tr><td>Name:</td><td>"
                    + "<input name= ' name' value='" +
                    customerVO.getName().replaceAll("'", "&#39;")
                    + "/></td></tr>");
            writer.println("<tr><td>City:</td><td>"
                    + "<input name='city' value='" +
                    customerVO.getCity().replaceAll("'", "&#39;")
                    + "'/></td></tr>");
            writer.println("<tr>"
                    + "<td colspan='2' style='text-align:right'>"
                    + "<input type='submit' value='Update' /></td>"
                    + "</tr>");
            writer.println("<tr><td colspan='2'>"
                    + "<a href=' customer '>Customer List</a>"
                    + "</td></tr>");
            writer.println("</table>");
            writer.println("</form></body>");
        } else {
            writer.println("No customer found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith("/customer")) {
            sendCustomerList(response);
        } else if (uri.endsWith("/editCustomer")) {
            sendEditCustomerForm(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = 0;
        try {
            customerId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {

        }
        CustomerVO customerVO = getCustomer(customerId);
        if (customerVO != null) {
            customerVO.setName(request.getParameter("name"));
            customerVO.setCity(request.getParameter("city"));
        }
        sendCustomerList(response);
    }
}
