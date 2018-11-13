package servlet;


import com.alibaba.fastjson.JSON;
import entity.Cart;
import entity.User;
import service.CartService;
import serviceImpl.CartServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(value = "/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 6335181478887045288L;
    private CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String userId = user.getUserId();
        Cart cart = cartService.findByUserId(userId);
        if (cart != null) {
            String res = JSON.toJSONString(cart);
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter out = response.getWriter()) {
                out.write(res);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String userId = user.getUserId();
        Cart cart = cartService.findByUserId(userId);
        Map<String, Integer> book = cart.getBook();
        String bookId = request.getParameter("bookId");
        if (book.containsKey(bookId)) {
            Integer num = book.get(bookId) + 1;
            book.put(bookId, num);
        } else {
            book.put(bookId, 1);
        }
    }
}
