package com.valsoft.dao;

import com.valsoft.model.Action;
import com.valsoft.model.Budget;
import com.valsoft.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Asus on 18.06.2017.
 */
public class BudgetDAO extends AbstractDAO<Long, Budget> implements IBudgetDAO {
    @Override
    public Budget findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void saveBudget(Budget budget) {
     persist(budget);
    }

    @Override
    public void deleteBudgetById(Long id) {

        Query query = getSession().createSQLQuery("delete from USER where BUDGET_ID = :id");
        query.setLong("BUDGET_ID",id);
        query.executeUpdate();
    }

    @Override
    public List<Budget> findAllBudgets() {
        Criteria criteria = createEntityCriteria();
        return (List<Budget>) criteria.list();
    }
}
