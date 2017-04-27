package main.services;

import main.models.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by admin on 21.04.2017.
 */
public interface UserService {

    User auth(String login, String password);
    User registration(String mail, String password, String firstName, String lastName, Integer limit);
    List<User> getAllUsers();
    User getUserById(int id);
    boolean userExist(String mail);
    HttpServletRequest sendErrorAndParameters(HttpServletRequest req, String errorMsg, String errorInputs);

}
