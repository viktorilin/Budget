package com.valsoft.dao;

import com.valsoft.model.Action;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 18.06.2017.
 */
@Repository
public class ActionDAO extends AbstractDAO<Long, Action> implements IActionDAO {
	@Override
	public Action findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void saveAction(Action role) {
		persist(role);
	}

	public void deleteAction(Action action){
		getSession().delete(action);
	}

	@Override
	public void deleteActionById(Long id) {
		Query query = getSession().createSQLQuery("delete from ACTION where ACTION_ID = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	public void updateAction(Action action){
		update(action);
	}

	@Override
	public List<Action> findAllActions() {
		Criteria criteria = createEntityCriteria();
		return (List<Action>) criteria.list();
	}

	@Override
	public Long countByBudgetId(Long budget_id) {
		Query query = getSession().createQuery("select count (*) FROM Action as a WHERE a.budgetUser.budget.id = :budget_id");
		query.setLong("budget_id", budget_id);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Action> findByBudgetIdPages(Long budget_id, int firstResult, int maxResult) {
		Query query = getSession().createQuery("FROM Action WHERE budgetUser.budget.id = :budget_id ORDER BY id DESC");
		query.setLong("budget_id", budget_id);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}


	@Override
	public List<Action> findByUserBudget(Long user_budget_id){
		List<Action> actions = new ArrayList<>();
		Query query = getSession().createQuery("from Action  WHERE budget_user_id = :id ORDER BY id DESC");
		query.setLong("id",user_budget_id);
		return query.list();
	}

	@Override
	public List<Action> findByBudgetIdAndCategoreId(Long budget_id, Long category_id) {
		Query query = getSession().createQuery("FROM Action WHERE budgetUser.budget.id = :budget_id AND subCategory.category.id =:category_id ORDER BY id DESC");
		query.setLong("budget_id", budget_id);
		query.setParameter("category_id", category_id);
		return query.list();
	}

	@Override
	public List<Action> findByBudgetIdAndCategoreIdPage(Long budget_id, Long category_id, int firstResult, int maxresult) {
		Query query = getSession().createQuery(" FROM Action WHERE budgetUser.budget.id = :budget_id AND subCategory.category.id =:category_id ORDER BY id DESC");
		query.setLong("budget_id", budget_id);
		query.setParameter("category_id", category_id);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxresult);
		return query.list();
	}

	@Override
	public Long countByBudgetIdAndCategoreId(Long budget_id, Long category_id) {
		Query query = getSession().createQuery("select count (*) FROM Action WHERE budgetUser.budget.id = :budget_id AND subCategory.category.id =:category_id");
		query.setLong("budget_id", budget_id);
		query.setParameter("category_id", category_id);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Action> getAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId) {
		Query query = getSession().createQuery("from Action  WHERE budget_user_id = :id and subCategory.category.id =:category_id ORDER BY id DESC");
		query.setLong("id",user_budget_id);
		query.setParameter("category_id", categoryId);
		return query.list();
	}

	@Override
	public List<Action> getAllByUserBudgetAndCategoryIdPage(Long user_budget_id, Long categoryId, int firstResult, int maxResult) {
		Query query = getSession().createQuery("from Action  WHERE budget_user_id = :id and subCategory.category.id =:category_id ORDER BY id DESC");
		query.setLong("id",user_budget_id);
		query.setParameter("category_id", categoryId);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}

	@Override
	public Long countAllByUserBudgetAndCategoryId(Long user_budget_id, Long categoryId) {
		Query query = getSession().createQuery("select count (*)from Action  WHERE budget_user_id = :id and subCategory.category.id =:category_id");
		query.setLong("id",user_budget_id);
		query.setParameter("category_id", categoryId);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Action> findByBudgetId(Long budget_id) {
		Query query = getSession().createQuery("FROM Action WHERE budgetUser.budget.id = :budget_id ORDER BY id DESC");
		query.setLong("budget_id", budget_id);
		return query.list();
	}

	@Override
	public Long countByUserBudget(Long user_budget_id) {
		Query query = getSession().createQuery("select count(*) from Action  WHERE budget_user_id = :id");
		query.setLong("id",user_budget_id);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Action> getAllByUserBudgetPage(Long user_budget_id, int firstResult, int maxResult) {
		Query query = getSession().createQuery("from Action  WHERE budget_user_id = :id ORDER BY id DESC");
		query.setLong("id",user_budget_id);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}
}


