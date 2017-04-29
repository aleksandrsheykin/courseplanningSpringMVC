package main.services;

import main.models.dao.UserDao;
import main.models.dao.UserDaoImpl;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by admin on 21.04.2017.
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private static UserDao userDao = new UserDaoImpl();

    public User auth(String login, String password) {
        User user = userDao.auth(login, password);
        if (user == null) {
            return null;
        }

        return user;
    }

    public User registration(String mail, String password, String firstName, String lastName, Integer limit) {
        User user = userDao.insert(firstName, lastName, mail, password, limit);
        return user;
    }

    public User getUserById(int id) {
        return userDao.getUser(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public boolean userExist(String mail) {
        return userDao.userExist(mail);
    }

    public HttpServletRequest sendErrorAndParameters(HttpServletRequest req, String errorMsg, String errorInputs) {
        req.setAttribute("firstName", req.getParameter("firstName"));
        if (errorInputs.contains("firstName")) req.setAttribute("firstNameError", "1");

        req.setAttribute("lastName", req.getParameter("lastName"));
        if (errorInputs.contains("lastName")) req.setAttribute("lastNameError", "1");

        req.setAttribute("limit", req.getParameter("limit"));
        if (errorInputs.contains("limit")) req.setAttribute("limitError", "1");

        req.setAttribute("mail", req.getParameter("mail"));
        if (errorInputs.contains("mail")) req.setAttribute("mailError", "1");

        req.setAttribute("errorMsg", errorMsg);
        return req;
    }

    public HttpServletRequest sendErrorAndParametersMVC(HttpServletRequest req, String errorMsg, String errorInputs, Model model) {
        model.addAttribute("firstName", req.getParameter("firstName"));
        if (errorInputs.contains("firstName")) model.addAttribute("firstNameError", "1");

        model.addAttribute("lastName", req.getParameter("lastName"));
        if (errorInputs.contains("lastName")) model.addAttribute("lastNameError", "1");

        model.addAttribute("limit", req.getParameter("limit"));
        if (errorInputs.contains("limit")) model.addAttribute("limitError", "1");

        model.addAttribute("mail", req.getParameter("mail"));
        if (errorInputs.contains("mail")) model.addAttribute("mailError", "1");

        model.addAttribute("errorMsg", errorMsg);
        return req;
    }
}
