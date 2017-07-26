package com.valsoft.dao;

import com.valsoft.model.Budget;


import java.util.List;

/**
 * Created by Asus on 18.06.2017.
 */
public interface IBudgetDAO {
    Budget findById(Long id);

    void saveBudget(Budget budget);

    void deleteBudgetById(Long id);

    void deleteBudget(Budget budget);

    List<Budget> findAllBudgets();

    List<Budget> findAllByAdminId(Long adminId);
}
