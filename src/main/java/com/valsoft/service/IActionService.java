package com.valsoft.service;

import com.valsoft.model.Action;
import com.valsoft.model.SubAction;

import java.util.List;

/**
 * Created by Asus on 19.06.2017.
 */
public interface IActionService {
    Action findById(Long id);

    void saveAction(Action action);

    void updateAction(Action action);

    void deleteActionById(Long id);

    List<Action> findAllActions();

    List<Action> getAllByUserBudget(Long user_budget_id);

    List<Action> getAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId);

    List<Action> findByBudgetId(Long budget_id);

    List<Action> findByBudgetIdAndCategoreId(Long budget_id, Long category_id);

    List<Action> findByBudgetIdAndCategoreIdPage(Long budget_id, Long category_id, int firstResult, int maxResult);

    Long countByBudgetIdAndCategoreId(Long budget_id, Long category_id);

    long countByBudgetId(Long budget_id);

    List<Action> findByBudgetIdPages(Long budget_id, int firstResult, int maxResult);

    List<Action> getAllByUserBudgetPage(Long user_budget_id, int firstResult, int maxResult );

    Long countByUserBudget(Long user_budget_id);

    public List<Action> getAllByUserBudgetAndCategoryIdPage(Long user_budget_id, Long categoryId, int firstResult, int maxResult);

    Long countAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId);


    void addNewAction(SubAction subAction, Long busget_user_id, Long subcategory_id);

    void deleteAction(Action action);

    Double getSumSubAction(Long actionId);

}
