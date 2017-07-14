package com.valsoft.dao;

import com.valsoft.model.Action;

import java.util.List;

/**
 * Created by Asus on 18.06.2017.
 */
public interface IActionDAO {
    Action findById(Long id);

    void saveAction(Action action);

    void deleteActionById(Long id);

    void deleteAction(Action action);

    void updateAction(Action action);

    List<Action> findAllActions();

    List<Action> findByUserBudget(Long user_budget_id);

    List<Action> findByBudgetId(Long budget_id);
}

