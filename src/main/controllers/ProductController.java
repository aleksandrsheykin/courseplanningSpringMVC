package main.controllers;

import main.models.pojo.User;
import main.services.*;
import main.utils.ErrorManager;
import main.utils.Options;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller
public class ProductController {

    private ProductService productService;
    private static Logger logger = Logger.getLogger(MainController.class);
    private ErrorManager error;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showProductPage(Model model) throws SQLException {
        ModelAndView mav = new ModelAndView();

        for (int replays=1; replays<=Options.REPLACE_COUNT; replays++)
            try {
                model.addAttribute("productList", productService.getAllProducts());
                break;
            } catch (SQLException e) {
                logger.error("SQLException in ProductController.showProductPage()");
                if (replays == Options.REPLACE_COUNT) {
                    error.setMsg("Oh sorry! Site crash, try again later");
                    mav.addObject("error", error);
                    mav.setViewName("error");
                    return mav;
                }
            }

        mav.setViewName("products");
        return mav;
    }
}
