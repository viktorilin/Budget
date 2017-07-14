package com.valsoft.dao;

import com.valsoft.model.SubCategory;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sanya on 09.07.2017.
 */
@Repository
public class SubCategoryDAO extends AbstractDAO<Long, SubCategory> implements ISubCategoryDAO {
	@Override
	public void saveSubCategory(SubCategory subCategory) {
		persist(subCategory);
	}

	@Override
	public SubCategory findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void deleteSubCategoryById(Long id) {
		Query query = getSession().createSQLQuery("delete from SubCategory where id = :id");
		query.setLong("id",id);
		query.executeUpdate();
	}

	@Override
	public List<SubCategory> findAllCategories() {
		Criteria criteria = createEntityCriteria();
		return (List<SubCategory>) criteria.list();
	}

	@Override
	public List<SubCategory> findByCategiryId(Long id) {
		Query query = getSession().createQuery("from SubCategory where category_id = :id");
		query.setLong("id", id);
		return query.list();
	}
}
