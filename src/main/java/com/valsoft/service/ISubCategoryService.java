package com.valsoft.service;

import com.valsoft.model.SubCategory;

import java.util.List;

/**
 * Created by sanya on 09.07.2017.
 */
public interface ISubCategoryService {
	void save(SubCategory subCategory);
	SubCategory findById(Long id);
	List<SubCategory> findByCategiryId(Long id);
}
