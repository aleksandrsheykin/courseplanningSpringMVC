package main.services;

import main.models.dao.PlanDao;
import main.models.dao.PlanDaoImpl;
import main.models.pojo.Plan;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class PlanServiceImpl implements PlanService {

    public static PlanDao planDao = new PlanDaoImpl();

    public List<Plan> getAllPlans() {
        return planDao.getAll(true);
    }

    public boolean deletePlanById(Integer id) {
        return planDao.delete(id);
    }
}
