package main.controllers;

import main.models.pojo.User;
import main.utils.ErrorManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 22.04.2017.
 */
@Controller
public class ErrorController {
    private static Logger logger = Logger.getLogger(ErrorController.class);
    private ErrorManager error = new ErrorManager("");

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView showErrorPage(@ModelAttribute("error") String inputError, Model model) throws IOException {
        ModelAndView mav = new ModelAndView();
        error.setMsg(inputError);
        model.addAttribute("error", error);
        mav.setViewName("error");
        return mav;
    }

    /*@ModelAttribute(value = "error")  ???
    public ErrorManager addError() {
        logger.debug("addError");
        return error;
    }*/
}
