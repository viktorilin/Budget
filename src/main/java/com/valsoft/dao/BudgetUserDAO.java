package com.valsoft.dao;

import com.valsoft.model.BudgetUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Asus on 18.06.2017.
 */
@Repository
public class BudgetUserDAO extends AbstractDAO<Long, BudgetUser> implements IBudgetUserDAO {
    @Override
    public BudgetUser findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void saveBudgetUser(BudgetUser budgetUser) {
        persist(budgetUser);
    }

    @Override
    public void deleteBudgetUserById(Long id) {
        Query query = getSession().createSQLQuery("delete from BUDGET_USER where BUDGET_USER_ID = :id");
        query.setLong("BUDGET_USER_ID",id);
        query.executeUpdate();
    }

    @Override
    public List<BudgetUser> findAllBudgetUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<BudgetUser>) criteria.list();
    }

    @Override
    public List<BudgetUser> getAllByBudgetId(Long budgetId) {
        Query query = getSession().createQuery("SELECT * FROM budget_user  where budget.id = :id");
        query.setParameter("id", budgetId);
        return query.list() ;
//        Query query = getSession().createQuery("from BudgetUser  WHERE budget.id = :id ORDER BY id DESC");
//        query.setLong("id",budgetId);
//        return query.list();
    }
}
