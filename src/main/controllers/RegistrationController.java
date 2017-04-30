package main.controllers;

import main.models.pojo.User;
import main.services.UserService;
import main.services.UserServiceImpl;
import main.utils.ErrorManager;
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
import java.sql.SQLException;

/**
 * Created by admin on 19.04.2017.
 */
@Controller
@SessionAttributes("users")
public class RegistrationController {
    private static Logger logger = Logger.getLogger(RegistrationController.class);
    private static UserService userService = new UserServiceImpl();
    private ErrorManager error = new ErrorManager("");

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

        boolean isReplay = false;
        replay:
        {
            try {
                if (userService.userExist(mail)) {
                    //userService.sendErrorAndParametersMVC(req, "User with this mail already exist", "mail", model);
                    error = new ErrorManager("User with this mail already exist");
                    mav.addObject("error", error);
                    mav.addObject("firstName", firstName);
                    mav.addObject("lastName", lastName);
                    mav.addObject("limit", limit);
                    mav.setViewName("registration");
                } else {
                    User newUser = userService.registration(mail, password, firstName, lastName, limit);
                    mav.addObject("user", newUser);
                    mav.setViewName("redirect:main");
                }
            } catch (SQLException e) {
                logger.error("SQLException in RegistrationController.registration()");
                if (!isReplay) {
                    isReplay = true;
                    break replay;
                }
                //error = new ErrorManager("Oh sorry! Registration error, try again later");
                error.setMsg("Oh sorry! Registration error, try again later");
                mav.setViewName("redirect:error");
            }
        }

        return mav;
    }

/*    @ModelAttribute(value = "error")
    public ErrorManager addError() {
        return error;
    }*/

}
