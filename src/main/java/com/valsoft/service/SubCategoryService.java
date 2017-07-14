package com.valsoft.service;

import com.valsoft.dao.ISubCategoryDAO;
import com.valsoft.model.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sanya on 09.07.2017.
 */
@Service
@Transactional
public class SubCategoryService implements ISubCategoryService {

	@Autowired
	private ISubCategoryDAO dao;

	@Override
	public void save(SubCategory subCategory) {
		dao.saveSubCategory(subCategory);
	}

	@Override
	public SubCategory findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<SubCategory> findByCategiryId(Long id) {
		return dao.findByCategiryId(id);
	}
}
