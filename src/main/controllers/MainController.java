package main.controllers;

import main.models.pojo.User;
import main.services.PlanService;
import main.services.PlanServiceImpl;
import main.services.UserService;
import main.services.UserServiceImpl;
import main.utils.ErrorManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);
    private ErrorManager error;

    public static PlanService planService = new PlanServiceImpl();
    public static UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showLoginPage(Model model) throws SQLException {
        try {
            model.addAttribute("planList", planService.getAllPlans());
        } catch (SQLException e) {
            logger.error("SQLException in RegistrationController.registration()");
            error = new ErrorManager("Oh sorry! Error, try again later");
            return "error";
        }
        return "main";
    }

    @ModelAttribute(value = "error")
    public ErrorManager addError() {
        return error;
    }

/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer deleteId = Integer.parseInt(req.getParameter("deleteId"));
        if (deleteId != null && deleteId > 0) {
            planService.deletePlanById(deleteId);
        }
        resp.sendRedirect(req.getContextPath() + "/main");
    }*/

}
