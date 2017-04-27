package main.models.dao;

import main.models.pojo.Plan;
import main.models.pojo.Product;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface PlanDao {
    public List<Plan> getAll(boolean joinProduct);
    public Plan get(int id);
    public boolean update(Plan plan);
    public boolean delete(Plan plan);
    public boolean delete(Integer id);
    public boolean insert(Plan plan);
}