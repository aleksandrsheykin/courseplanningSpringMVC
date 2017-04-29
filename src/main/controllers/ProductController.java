package main.controllers;

import main.models.pojo.User;
import main.services.PlanService;
import main.services.PlanServiceImpl;
import main.services.UserService;
import main.services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 26.04.2017.
 */
public class ProductController extends HttpServlet {

    public static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) ((HttpServletRequest) req).getSession().getAttribute("userId");
        User user = null;
        try {
            user = userService.getUserById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("userIsAdmin", user.getIsAdmin());
        req.setAttribute("userName", user.getFirstName()+" "+user.getLastName());

        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
