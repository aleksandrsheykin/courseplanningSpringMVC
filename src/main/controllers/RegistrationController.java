package main.controllers;

import main.models.pojo.User;
import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
@SessionAttributes("user")
public class RegistrationController {
    private static Logger logger = Logger.getLogger(RegistrationController.class);
    private static UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            resp.sendRedirect(req.getContextPath() + "/main");
        }

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "mail", required = true) String mail,
                                     @RequestParam(value = "password", required = true) String password,
                                     @RequestParam(value = "firstName", required = true) String firstName,
                                     @RequestParam(value = "lastName", required = true) String lastName,
                                     @RequestParam(value = "limit", required = true) Integer limit,
                                     HttpServletRequest req, Model model) {
        ModelAndView mav = new ModelAndView();

        if (userService.userExist(mail)) {
            //userService.sendErrorAndParametersMVC(req, "User with this mail already exist", "mail", model);

            model.addAttribute("firstName", req.getParameter("firstName"));
            logger.debug("11111 "+req.getParameter("firstName"));
            //mav.addObject("firstName", req.getParameter("firstName"));

            mav.setViewName("redirect:registration");
        } else {
            User newUser = userService.registration(mail, password, firstName, lastName, limit);

            if (newUser != null) {
                mav.addObject("user", newUser);
                mav.setViewName("redirect:main");
            } else {
                userService.sendErrorAndParametersMVC(req, "Oh sorry! Registration error, try again later", "", model);
                mav.setViewName("redirect:registration");
            }
        }

        return mav;
    }

/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userService.userExist(req.getParameter("mail"))) {
            userService.sendErrorAndParameters(req, "User with this mail already exist", "mail");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            dispatcher.forward(req, resp);
        } else {
            User newUser = userService.registration(req.getParameter("mail"),
                    req.getParameter("password"),
                    req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    Integer.valueOf(req.getParameter("limit")));

            if (newUser != null) {
                req.getSession().setAttribute("userLogin", newUser.getMail());
                req.getSession().setAttribute("userId", newUser.getIdUser());
                resp.sendRedirect(req.getContextPath() + "/main");
            } else {
                userService.sendErrorAndParameters(req, "Oh sorry! Registration error, try again later", "");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }*/
}
