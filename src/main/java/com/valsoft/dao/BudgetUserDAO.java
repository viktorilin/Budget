package com.valsoft.dao;

import com.valsoft.model.BudgetUser;
import com.valsoft.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.metamodel.binding.HibernateTypeDescriptor;
import org.hibernate.type.LongType;
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

   public BudgetUser findByUserBudget(Long user_id, Long  budget_id){
        Query query = getSession().createQuery("from BudgetUser WHERE budget_id = :p_budget_id and user_id = :p_user_id");
        query.setParameter("p_budget_id", budget_id);
        query.setParameter("p_user_id", user_id);
        return (BudgetUser )query.uniqueResult();
   }

    @Override
    public Long getBudgetUserID(Long userId, Long budgetId) {
        SQLQuery query = getSession().createSQLQuery("SELECT budget_user_id FROM budget_user WHERE budget_id = :p_budget_id AND user_id = :p_user_id");
        query.setParameter("p_budget_id", budgetId);
        query.setParameter("p_user_id", userId);
        query.addScalar("budget_user_id", new LongType());
       return (Long) query.uniqueResult();
    }

}
