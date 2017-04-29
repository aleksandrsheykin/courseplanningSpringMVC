package main.controllers;

import main.models.pojo.User;
import main.services.PlanService;
import main.services.PlanServiceImpl;
import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    public static PlanService planService = new PlanServiceImpl();
    public static UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        model.addAttribute("planList", planService.getAllPlans());
        return "main";
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
