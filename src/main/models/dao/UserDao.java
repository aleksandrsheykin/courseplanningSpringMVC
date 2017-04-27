package main.models.dao;

import main.models.pojo.User;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface UserDao {
    List<User> getAll();
    User getUser(int id);
    User getUser(String mail);
    boolean update(User user);
    boolean userExist(String mail);
    boolean delete(User user);
    boolean insert(String firsName, String lastName, String mail, String password, Integer limit, boolean isAdmin, Integer idUser, boolean isBlocked);
    User insert(String firsName, String lastName, String mail, String password, Integer limit);
    User findUserByLoginAndPassword(String login, String password);
    User auth(String login, String password);
}