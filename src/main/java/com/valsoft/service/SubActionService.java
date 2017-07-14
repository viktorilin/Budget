package com.valsoft.service;

import com.valsoft.dao.ISubActionDAO;
import com.valsoft.dao.SubActionDAO;
import com.valsoft.model.Action;
import com.valsoft.model.SubAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Asus on 19.06.2017.
 */
@Service("subActionService")
@Transactional
public class SubActionService implements ISubActionService {

    @Autowired
    private ISubActionDAO dao;

    @Override
    public SubAction findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void saveSubAction(SubAction subAction) {
        dao.saveSubAction(subAction);
    }

    @Override
    public void updateSubAction(SubAction subAction) {
        SubAction entity = dao.findById(subAction.getId());
        if(entity!=null){
       //     entity.setAction(subAction.getAction());
      //      entity.setCategory(subAction.getCategory());
            entity.setDescription(subAction.getDescription());
            entity.setPicture(subAction.getPicture());
        }
    }

    @Override
    public void deleteSubActionById(Long id) {
        dao.deleteSubActionById(id);
    }

    @Override
    public List<SubAction> findAllSubActions() {
        return dao.findAllSubActions();
    }

    @Override
    public Long addNewSubaction(Long action_id, String description, Double amount) {
        Action action = new Action();
        action.setId(action_id);

        SubAction subAction = new SubAction();
        subAction.setAction(action);
        subAction.setDescription(description);
        subAction.setAmount(amount);
        subAction.setPicture("none");
        dao.saveSubAction(subAction);
        return subAction.getId();
    }

    @Override
    public Integer getCountSubaction(Long action_id) {
        return dao.getCountSubcategories(action_id);
    }
}
