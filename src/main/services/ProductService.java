package main.services;

import main.models.pojo.Plan;
import main.models.pojo.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 30.04.2017.
 */
public interface ProductService {

    List<Product> getAllProducts() throws SQLException;
}
