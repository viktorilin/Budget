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

    Long countByBudgetId(Long budget_id);

    List<Action> findByBudgetIdPages(Long budget_id, int firstResult, int maxResult);

    List<Action> findByUserBudget(Long user_budget_id);

    List<Action> findByBudgetIdAndCategoreId(Long budget_id, Long category_id);

    List<Action> findByBudgetIdAndCategoreIdPage(Long budget_id, Long category_id, int firstResult, int maxresult);

    Long countByBudgetIdAndCategoreId(Long budget_id, Long category_id);

    List<Action> getAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId);

    List<Action> getAllByUserBudgetAndCategoryIdPage(Long user_budget_id, Long categoryId, int firstResult, int maxResult);

    Long countAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId);

    List<Action> findByBudgetId(Long budget_id);

    Long countByUserBudget(Long user_budget_id);

    List<Action> getAllByUserBudgetPage(Long user_budget_id, int firstResult, int maxResult);

    Double getSumSubAction(Long actionId);


}

