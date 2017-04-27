package main.models.dao;

import main.models.connection.DBConnection;
import main.models.pojo.Product;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class ProductDaoImpl implements ProductDao {

    static {
        PropertyConfigurator.configure("log4j.properties");
    }
    private static Logger logger = Logger.getLogger(ProductDaoImpl.class);


    public List<Product> getAll() {
        Connection connection = DBConnection.initConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *"+
                    " from products");

            ResultSet result = preparedStatement.executeQuery();

            List<Product> listProduct = new ArrayList<Product>();
            while (result.next()) {
                listProduct.add(new Product(
                        result.getInt("product_id_"),
                        result.getString("product_name"),
                        result.getString("product_description"),
                        result.getInt("product_user_id"))

                );
            }
            return listProduct;

        } catch (SQLException e) {
            logger.warn("SQLException in Product.getAll()");
            return null;
        }
    }

    public Product get(int id) {
        Connection connection = DBConnection.initConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *"+
                    " from products where product_id=?");
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            return new Product(
                    result.getInt("product_id_"),
                    result.getString("product_name"),
                    result.getString("product_description"),
                    result.getInt("product_user_id")
            );

        } catch (SQLException e) {
            logger.warn("SQLException in Product.get()");
            return null;
        }
    }

    public boolean update(Product product) {
        Connection connection = DBConnection.initConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET(" +
                    " product_name, product_description, product_user_id)" +
                    " = (?, ?, ?) WHERE product_id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getUser_id());
            preparedStatement.setInt(4, product.getId_product());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            logger.warn("SQLException in Product.update()");
            return false;
        }
    }

    public boolean delete(Product product) {
        Connection connection = DBConnection.initConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from products " +
                    " WHERE product_id = ?");
            preparedStatement.setInt(1, product.getId_product());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            logger.warn("SQLException in Product.delete()");
            return false;
        }
    }

    public boolean insert(Product product) {
        Connection connection = DBConnection.initConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert products (" +
                    " product_id, product_name, product_description, product_user_id)" +
                    " = (?, ?, ?, ?)");
            preparedStatement.setInt(1, product.getId_product());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getUser_id());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            logger.warn("SQLException in Product.insert()");
            return false;
        }
    }
}
