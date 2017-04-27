package main.services;

import main.models.pojo.Plan;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface PlanService {

    public List<Plan> getAllPlans();
    public boolean deletePlanById(Integer id);
}
