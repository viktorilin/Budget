package com.valsoft.dao;

import com.valsoft.model.SubAction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Asus on 18.06.2017.
 */
@Repository
public class SubActionDAO extends AbstractDAO<Long, SubAction> implements ISubActionDAO {
    @Override
    public SubAction findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void saveSubAction(SubAction subAction) {
        persist(subAction);
    }

    @Override
    public void deleteSubActionById(Long id) {
        SubAction subAction = new SubAction();
        subAction.setId(id);
        getSession().delete(subAction);
    }

    @Override
    public List<SubAction> findAllSubActions() {
        Criteria criteria = createEntityCriteria();
        return (List<SubAction>) criteria.list();
    }

    @Override
    public Integer getCountSubcategories(Long subcat_id) {

        SQLQuery query = getSession().createSQLQuery("SELECT count(*) as count FROM sub_action WHERE action_id = :p_action_id ");
        query.setParameter("p_action_id", subcat_id);
        query.addScalar("count", new IntegerType());
        return (Integer) query.uniqueResult();
    }
}
