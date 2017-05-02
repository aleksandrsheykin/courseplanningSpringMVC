package main.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by admin on 22.04.2017.
 */
@Controller
@SessionAttributes("user")
public class LogoutController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected String logout(WebRequest req, SessionAttributeStore store, SessionStatus status) throws ServletException, IOException {

        logger.debug("1111");
        status.setComplete();
        store.cleanupAttribute(req, "user");
        return "redirect:/";
    }
/*
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");

        resp.sendRedirect(req.getContextPath() + "/");
    }*/

/*    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("userLogin");
        req.getSession().removeAttribute("isAdmin");

        resp.sendRedirect(req.getContextPath() + "/");
    }*/
}
