package com.valsoft.controller;

import com.valsoft.model.*;
import com.valsoft.service.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by sanya on 28.06.2017.
 */
@RequestMapping(value = "/action")
@Controller
public class ActionControler {

	@Autowired
	IBudgetUserService budgetUserService;

	@Autowired
	IActionService actionService;

	@Autowired
	ICategoryService categoryService;

	@RequestMapping(value="/{budget}", method = RequestMethod.GET)
	public String getActions(@PathVariable("budget") long budgetId, Model model){
		BudgetUser budgetUser = budgetUserService.getByuserBudget(1l, budgetId);
		List<Action> actions = actionService.getAllByUserBudget(budgetUser.getId());
		model.addAttribute("actions", actions);
		model.addAttribute("budget_user", budgetUser.getId());
		return "actions";
	}

	@RequestMapping(value="/add/{budget_user}", method = RequestMethod.GET)
	public String getAddAction(@PathVariable("budget_user") long budget_user,  Model model){
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("budget_user", budget_user);
		return "addAction";
	}

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addAction(@RequestParam("budget_user") long budget_user,
							@RequestParam("title") String title,
							@RequestParam("category") long cat_id){
		BudgetUser budgetUser = new BudgetUser();
		budgetUser.setId(budget_user);
		Category category = new Category();
		category.setId(cat_id);

		Action action = new Action();
		action.setType(title);
		action.setBudgetUser(budgetUser);
		action.setCategory(category);

		actionService.saveAction(action);

		return "redirect:/action/"+budget_user;
	}

	@RequestMapping(value="/delete/{action_id}/budget/{budget_id}", method = RequestMethod.GET)
	public String deleteAction(@PathVariable("action_id") long action_id,
							   @PathVariable("budget_id") long budget_id,  Model model){
		actionService.deleteActionById(action_id);
		return "redirect:/action/"+budget_id;
	}

}
