package main.controllers;

import main.models.pojo.User;
import main.services.PlanService;
import main.services.PlanServiceImpl;
import main.services.UserService;
import main.services.UserServiceImpl;
import main.utils.ErrorManager;
import main.utils.Options;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 26.04.2017.
 */
public class ProductController {

    public static UserService userService = new UserServiceImpl();
    private static Logger logger = Logger.getLogger(MainController.class);
    private ErrorManager error;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView showProductPage(Model model) throws SQLException {
        ModelAndView mav = new ModelAndView();
        int replays = 0;
        while (replays < Options.REPLACE_COUNT)
            try {
                //model.addAttribute("planList", planService.getAllPlans());
                break;
            } catch (SQLException e) {
                replays++;
                logger.error("SQLException in LoginController.registration()");
                if (replays == Options.REPLACE_COUNT) {
                    error.setMsg("Oh sorry! Site crash, try again later");
                    mav.addObject("error", error);
                    mav.setViewName("error");
                    return mav;
                }
            }
        mav.setViewName("main");
        return mav;
    }
}
