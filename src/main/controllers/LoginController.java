package main.controllers;

import main.models.pojo.User;
import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 19.04.2017.
 */
@Controller
@SessionAttributes({"login", "isAdmin"})
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);
    private static UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "login", required = true) String login,
                              @RequestParam(value = "password", required = true) String password) {
        ModelAndView mav = new ModelAndView();

        logger.warn("LOOGINN=|"+login+"| PAASSS=|"+password+"|");
        User user = userService.auth(login, password);
        logger.warn("UUUUUSER="+user);
        if (user != null) {
            if (user.getIsAdmin()) {
                mav.addObject("isAdmin", 1);
            }
            mav.addObject("login", login);
            mav.setViewName("redirect:main");
        } else {
            mav.setViewName("redirect:login");
        }
        return mav;
    }

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = (String) req.getSession().getAttribute("userLogin");
        if (userLogin != null) {
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.auth(login, password);
        if (user != null) {
            req.getSession().setAttribute("userLogin", user.getMail());
            req.getSession().setAttribute("userId", user.getIdUser());
            if (user.getIsAdmin()) {
                req.getSession().setAttribute("isAdmin", "1");
            }
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            req.setAttribute("errorMsg", "failed login");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
    }*/

}
