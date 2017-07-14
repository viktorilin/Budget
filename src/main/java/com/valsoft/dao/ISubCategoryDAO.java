package com.valsoft.dao;

import com.valsoft.model.SubCategory;

import java.util.List;

/**
 * Created by sanya on 09.07.2017.
 */
public interface ISubCategoryDAO {
	void saveSubCategory(SubCategory subCategory);
	SubCategory findById(Long id);
	void deleteSubCategoryById(Long id);
	List<SubCategory> findAllCategories();
	List<SubCategory> findByCategiryId(Long id);
}
