package com.valsoft.service;

import com.valsoft.dao.ActionDAO;
import com.valsoft.dao.IActionDAO;
import com.valsoft.model.Action;
import com.valsoft.model.BudgetUser;
import com.valsoft.model.SubAction;
import com.valsoft.model.SubCategory;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Asus on 19.06.2017.
 */
@Service
@Transactional
public class ActionService implements IActionService {

    @Autowired
    private IActionDAO dao;

    @Override
    public Action findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void saveAction(Action action) {
        dao.saveAction(action);
    }

    @Override
    public void updateAction(Action action) {
        dao.updateAction(action);
    }

    @Override
    public List<Action> getAllByUserBudget(Long user_budget_id){
        return dao.findByUserBudget(user_budget_id);
    }

    @Override
    public List<Action> findByBudgetId(Long budget_id) {
        return dao.findByBudgetId(budget_id);
    }

    @Override
    public List<Action> findByBudgetIdAndCategoreId(Long budget_id, Long category_id) {
        return dao.findByBudgetIdAndCategoreId(budget_id, category_id);
    }

    @Override
    public List<Action> findByBudgetIdAndCategoreIdPage(Long budget_id, Long category_id, int firstResult, int maxResult) {
        return dao.findByBudgetIdAndCategoreIdPage(budget_id, category_id, firstResult, maxResult);
    }

    @Override
    public Long countByBudgetIdAndCategoreId(Long budget_id, Long category_id) {
        return dao.countByBudgetIdAndCategoreId(budget_id, category_id);
    }

    @Override
    public long countByBudgetId(Long budget_id) {
        return dao.countByBudgetId(budget_id);
    }

    @Override
    public List<Action> findByBudgetIdPages(Long budget_id, int firstResult, int maxResult) {
        return dao.findByBudgetIdPages(budget_id,firstResult,maxResult);
    }

    @Override
    public List<Action> getAllByUserBudgetPage(Long user_budget_id, int firstResult, int maxResult) {
        return dao.getAllByUserBudgetPage(user_budget_id, firstResult, maxResult);
    }

    @Override
    public Long countByUserBudget(Long user_budget_id) {
        return dao.countByUserBudget(user_budget_id);
    }

    @Override
    public List<Action> getAllByUserBudgetAndCategoryIdPage(Long user_budget_id, Long categoryId, int firstResult, int maxResult) {
        return dao.getAllByUserBudgetAndCategoryIdPage(user_budget_id,categoryId,firstResult,maxResult);
    }

    @Override
    public Long countAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId) {
        return dao.countAllByUserBudgetAndCategoryId(user_budget_id, categoryId);
    }


    public List<Action> getAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId){
        return dao.getAllByUserBudgetAndCategoryId(user_budget_id, categoryId);
    }

    @Override
    public void addNewAction(SubAction subAction, Long busget_user_id, Long subcategory_id) {
        BudgetUser budgetUser = new BudgetUser();
        budgetUser.setId(busget_user_id);

        SubCategory subCategory = new SubCategory();
        subCategory.setId(subcategory_id);

        Action action = new Action();
        action.setSubCategory(subCategory);
        action.setBudgetUser(budgetUser);
        action.getSubActions().add(subAction);
        subAction.setAction(action);
        action.setCreateDate(new LocalDate());
        saveAction(action);
    }

    @Override
    public void deleteAction(Action action) {
        dao.deleteAction(action);
    }

    @Override
    public void deleteActionById(Long id) {
        dao.deleteActionById(id);
    }

    @Override
    public List<Action> findAllActions() {
        return dao.findAllActions();
    }
}
