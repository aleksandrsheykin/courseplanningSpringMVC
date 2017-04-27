package main.models.dao;

import main.models.pojo.Product;
import main.models.pojo.User;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface ProductDao {
    public List<Product> getAll();
    public Product get(int id);
    public boolean update(Product product);
    public boolean delete(Product product);
    public boolean insert(Product produc);
}