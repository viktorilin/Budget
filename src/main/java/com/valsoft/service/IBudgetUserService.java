package com.valsoft.service;

import com.valsoft.model.BudgetUser;

import java.util.List;

/**
 * Created by Asus on 19.06.2017.
 */
public interface IBudgetUserService {
    BudgetUser findById(Long id);

    void saveBudgetUser(BudgetUser budgetUser);

    void updateBudgetUser(BudgetUser budgetUser);

    void deleteBudgetUserById(Long id);

    List<BudgetUser> findAllBudgetUsers();

    BudgetUser getByuserBudget(Long user_id, Long budget_id);

    Long getBudgetUserID(Long user_id, Long budget_id);
}
