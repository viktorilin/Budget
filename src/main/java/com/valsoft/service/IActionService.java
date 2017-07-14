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

    List<Action> findByBudgetId(Long budget_id);

    void addNewAction(SubAction subAction, Long busget_user_id, Long subcategory_id);

    void deleteAction(Action action);

}
