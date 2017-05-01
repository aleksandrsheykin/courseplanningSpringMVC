package main.models.dao;

import main.models.pojo.Product;
import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface ProductDao {
    List<Product> getAll() throws SQLException;
    Product get(int id);
    boolean update(Product product);
    boolean delete(Product product);
    boolean insert(Product product);
}