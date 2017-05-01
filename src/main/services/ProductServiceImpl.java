package main.services;

import main.models.dao.ProductDao;
import main.models.dao.ProductDaoImpl;
import main.models.dao.UserDao;
import main.models.dao.UserDaoImpl;
import main.models.pojo.Product;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 01.05.2017.
 */
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private static ProductDao productDao = new ProductDaoImpl();

    public List<Product> getAllProducts() throws SQLException {
        try {
            return productDao.getAll();
        } catch (SQLException e) {
            logger.warn("SQLException in ProductServiceImpl.getAllProducts()");
            throw e;
        }
    }
}
