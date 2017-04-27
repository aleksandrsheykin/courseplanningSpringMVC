package main.controllers.filters;

import main.models.pojo.User;
import main.services.UserService;
import main.services.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 22.04.2017.
 */
public class WhiteList implements Filter {
    public static UserService userService = new UserServiceImpl();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userLogin = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("userLogin");
        if (userLogin != null) {

            Integer userId = (Integer) ((HttpServletRequest) servletRequest).getSession().getAttribute("userId");
            User user = userService.getUserById(userId);
            servletRequest.setAttribute("userIsAdmin", user.getIsAdmin());
            servletRequest.setAttribute("userName", user.getFirstName()+" "+user.getLastName());

            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/");
        }
    }

    public void destroy() {

    }
}
