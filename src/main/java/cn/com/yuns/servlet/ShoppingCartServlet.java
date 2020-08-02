package cn.com.yuns.servlet;

import cn.com.yuns.servlet.VO.Product;
import cn.com.yuns.servlet.VO.ShoppingItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author wsq
 * @version ShoppingCartServlet.java  2020/7/29  上午8:37 下午
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/products", "/viewProductDetails"
        , "/addToCart", "/viewCart"})
public class ShoppingCartServlet extends HttpServlet {

    private static final long serialVersionUID = 292L;

    private static final String CART_ATTRIBUTE = "cart";

    private List<Product> products = new ArrayList<>();

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    public void init() throws ServletException {
        products.add(new Product(1, "长虹电视机", "中国长虹电视机", 999F));
        products.add(new Product(2, "格里空调", "中国格里空调", 789F));
        products.add(new Product(3, "华为手机", "中国华为手机", 6989F));
        products.add(new Product(4, "小米手机", "中国小米手机", 3688F));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith("products")) {
            sendProductList(response);
        } else if (uri.endsWith("viewProductDetails")) {
            sendProductList(response);
        } else if (uri.endsWith("viewCart")) {
            showCart(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = 0;
        int quantity = 0;
        try {
            productId = Integer.valueOf(request.getParameter("id"));
            quantity = Integer.valueOf(request.getParameter("quantity"));
        } catch (Exception e) {

        }
        Product product = getProduct(productId);
        if (product != null && product.getPrice() >= 0) {
            ShoppingItem shoppingItem = new ShoppingItem(product, quantity);
            HttpSession httpSession = request.getSession();
            List<ShoppingItem> cart = (List<ShoppingItem>) httpSession.getAttribute(CART_ATTRIBUTE);
            if (cart == null) {
                cart = new ArrayList<>();
                httpSession.setAttribute(CART_ATTRIBUTE, cart);
            }
            cart.add(shoppingItem);
        }
        sendProductList(response);
    }

    private void sendProductList(HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Products</title>" +
                "</head><body><h2>Products</h2>");
        writer.println("<u1>");
        for (Product product : products) {
            writer.println("<li>" + product.getName() + "("
                    + currencyFormat.format(product.getPrice())
                    + ") (" + "<a href='viewProductDetails?id="
                    + product.getId() + "'>Details</a>)");
            writer.println("</ul>");
            writer.println("<a href='viewCart'>View Cart</a>");
            writer.println("</body></html>");
        }
    }

    private void sendProductDetails(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int productId = 0;
        try {
            productId = Integer.parseInt(
                    request.getParameter("id"));
        } catch (NumberFormatException e) {
            Product product = getProduct(productId);
            if (product != null) {
                writer.println("<html><head>"
                        + "<title>Product Details</title></head>"
                        + "<body><h2>Product Details</h2>"
                        + "<form method='post' action='addToCart'>");
                writer.println("<input type='hidden' name='id'"
                        + "value='" + productId + "'/>");
                writer.println("<table>");
                writer.println("<tr><td>Name:</td><td>"
                        + product.getName() + "</td></tr>");
                writer.println("<tr><td>Description:</td><td>"
                        + product.getDescription() + "</td></tr>");
                writer.println("<tr>" + "<tr>"
                        + "<td><input name='quantity' /></td>"
                        + "<td><input type= 'submit' value='Buy'/>"
                        + "</td>"
                        + "</tr>");
                writer.println("<tr><td colspan='2'>"
                        + "<a href='products'>Product List</a>"
                        + "</td></tr>");
                writer.println("</table>");
                writer.println("</ form></body>");
            } else {
                writer.println("No product found");
            }
        }
    }


    private Product getProduct(Integer productId) {
        for (Product product : products) {
            if (productId.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Shopping Cart</title></head>");
        writer.println("<body><a href='products'>Product List</a>");
        HttpSession session = request.getSession();
        List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
        if (cart != null) {
            writer.println("<table>");
            writer.println("<tr><td style= 'width: 150px '>Quantity"
                    + "</td>"
                    + "<td style='width: 150px'>Product</td>"
                    + "<td style='width:150px'>Price</td>"
                    + "<td>Amount</td></tr>");
            double total = 0.0;
            for (ShoppingItem shoppingItem : cart) {
                Product product = shoppingItem.getProduct();
                int quantity = shoppingItem.getQuantity();
                if (quantity != 0) {
                    float price = product.getPrice();
                    writer.println("<tr>");
                    writer.println("<td>" + quantity + "</td>");
                    writer.println("<td>" + product.getName()
                            + "</td>");
                    writer.println("<td>"
                            + currencyFormat.format(price)
                            + "</td>");
                    double subtotal = price * quantity;
                    writer.println("<td>"
                            + currencyFormat.format(subtotal)
                            + "</td>");
                    total += subtotal;
                    writer.println("</tr>");
                }
            }
            writer.println("<tr><td colspan='4' "
                    + "style='text-align:right'>"
                    + "Total:"
                    + currencyFormat.format(total)
                    + "</td></tr>");
            writer.println("</tab1e>");
        }
        writer.println("</table></body></html>");
    }


}
