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
	public List<Action> findByUserBudget(Long user_budget_id){
		List<Action> actions = new ArrayList<>();
		Query query = getSession().createQuery("from Action  WHERE budget_user_id = :id ORDER BY id DESC");
		query.setLong("id",user_budget_id);
		return query.list();
	}

	@Override
	public List<Action> findByBudgetId(Long budget_id) {
		Query query = getSession().createQuery("FROM Action WHERE budgetUser.budget.id = :budget_id ORDER BY id DESC");
		query.setLong("budget_id", budget_id);
		return query.list();
	}
}


