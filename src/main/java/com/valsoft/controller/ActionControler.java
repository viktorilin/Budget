package com.valsoft.controller;

import com.valsoft.model.*;
import com.valsoft.service.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sanya on 28.06.2017.
 */
@Controller
public class ActionControler {

	private static final int MAX_RESULT = 5;

	@Autowired
	private IUserService userService;

	@Autowired
	private IBudgetService budgetService;

	@Autowired
	private IBudgetUserService budgetUserService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ISubCategoryService subCategoryService;

	@Autowired
	private IActionService actionService;

	@Autowired
	private ISubActionService subActionService;

	@Transactional
	@RequestMapping(value = "/budget/{budget_id}/actions", method = RequestMethod.GET)
	public String getAllActions(
			@RequestParam(value = "page", required = false,defaultValue = "1") int page,
			@PathVariable("budget_id") Long budget_id, Model model){
		//test(1l);
		List<Action> actions = actionService.findByBudgetIdPages(budget_id, page==1?0:(page-1)*MAX_RESULT+1, MAX_RESULT);
		List<Double> cost = new ArrayList<>();
		for(Action a:actions){
			cost.add(actionService.getSumSubAction(a.getId()));
		}
		model.addAttribute("type", "all");
		model.addAttribute("isCategory", "false");
		model.addAttribute("budget_user", budgetUserService.getByuserBudget(2l, budget_id));
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("actions", actions);
		model.addAttribute("pages",(int)Math.ceil(actionService.countByBudgetId(budget_id)/MAX_RESULT+1));
		model.addAttribute("cost", cost);
		return "actions";
	}

	@RequestMapping(value="/budget/{budget_user_id}/addaction")
	public String getAddUserForm(@PathVariable("budget_user_id") Long budget_user_id, Model model){
		model.addAttribute("subaction", new SubAction());
		model.addAttribute("categories", categoryService.findAllCategories());
		return "addAction";
	}

	@RequestMapping(value = "/addaction/submit", method = RequestMethod.POST)
	public String saveAction(@ModelAttribute("subaction") SubAction subAction,
							 @RequestParam("budget_user") Long 	budget_user,
							 @RequestParam(value = "subcategory", required = false) Long subcategory){
		actionService.addNewAction(subAction, budget_user, subcategory);
		return String.format("redirect:/budgetuser/%d/actions", budget_user);
	}

	@RequestMapping(value="/budgetuser/{budget_user_id}/actions", method = RequestMethod.GET)
	public String getActions(
			@RequestParam(value = "page" ,required = false, defaultValue = "1") int page,
			@PathVariable("budget_user_id") Long budgetUserId, Model model){
		List<Action> actions = actionService.getAllByUserBudgetPage(budgetUserId,page==1?0:(page-1)*MAX_RESULT+1, MAX_RESULT);
		model.addAttribute("type", "my");
		model.addAttribute("isCategory", "false");
		model.addAttribute("actions", actions);
		model.addAttribute("pages",(int)Math.ceil(actionService.countByUserBudget(budgetUserId)/MAX_RESULT+1));
		BudgetUser budgetUser = budgetUserService.findById(budgetUserId);
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("budget_user", budgetUser);
		model.addAttribute("budget_id", budgetUser.getBudget().getId());
		List<Double> cost = new ArrayList<>();
		for(Action a:actions){
			cost.add(actionService.getSumSubAction(a.getId()));
		}
		model.addAttribute("cost", cost);
		return "actions";
	}


	@RequestMapping(value = "/action/{action_id}/subactions", method = RequestMethod.GET)
	public String getSubaction(@PathVariable("action_id") Long action_id, Model model){
		Action action = actionService.findById(action_id);
		model.addAttribute("action", action);
		model.addAttribute("budget_user_id", budgetUserService.getBudgetUserID(2l, action.getBudgetUser().getBudget().getId()));
		model.addAttribute("user_id", 2l);
		return "subactions";
	}

	@RequestMapping(value = "/subactions/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addSubAction(@RequestParam("action_id") Long action_id,
											@RequestParam("description") String description,
											@RequestParam("amount") Double amount){
		Map<String, String> result = new HashMap<>();
		try{
			Long id = subActionService.addNewSubaction(action_id, description, amount);
			result.put("status", "true");
			result.put("id", id.toString());
		}
		catch (Exception ex){
			ex.printStackTrace();
			result.put("status", "false");
		}
		return result;
	}

	@RequestMapping(value = "/action/{action_id}/delete", method = RequestMethod.GET)
	public String deleteAction(@PathVariable("action_id") Long action_id){
		Action action = actionService.findById(action_id);
		actionService.deleteAction(action);
		return String.format("redirect:/budgetuser/%d/actions", action.getBudgetUser().getId());
	}

	@RequestMapping(value = "/action/{action_id}/subaction/{subact_id}/delete", method = RequestMethod.GET)
	public String deleteSubAction(@PathVariable("action_id") Long action_id,
								  @PathVariable("subact_id") Long subaction_id){
		if(subActionService.getCountSubaction(action_id) <= 1){
			return String.format("redirect:/action/%d/delete", action_id);
		}
		else{
			subActionService.deleteSubActionById(subaction_id);
		}
		return String.format("redirect:/action/%d/subactions", action_id);
	}

	@RequestMapping(value = "/category/{cat_id}/subcategories", method = RequestMethod.GET)
	@ResponseBody
	public  Map<Long, String> getSubcategory(@PathVariable("cat_id") Long cat_id){
		Map<Long, String> subcategories = new HashMap<>();
		for(SubCategory subCategory : subCategoryService.findByCategiryId(cat_id)){
			subcategories.put(subCategory.getId(), subCategory.getName());
		}
		return subcategories;
	}



	@Transactional
	@RequestMapping(value = "/budget/{budget_id}/actions/category/{category}", method = RequestMethod.GET)
	public String getAllActionsByCategory(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@PathVariable("category") long category_id,
			@PathVariable("budget_id") Long budget_id, Model model){
		List<Action> actions = actionService.findByBudgetIdAndCategoreIdPage(budget_id, category_id, page==1?0:(page-1)*MAX_RESULT+1, MAX_RESULT);
		model.addAttribute("type", "all");
		model.addAttribute("isCategory", "true");
		model.addAttribute("budget_user", budgetUserService.getByuserBudget(2l, budget_id));
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("actions", actions);
		model.addAttribute("pages",(int)Math.ceil(actionService.countByBudgetIdAndCategoreId(budget_id, category_id)/MAX_RESULT+1));
		model.addAttribute("category_id",category_id);
		List<Double> cost = new ArrayList<>();
		for(Action a:actions){
			cost.add(actionService.getSumSubAction(a.getId()));
		}
		model.addAttribute("cost", cost);
		return "actions";
	}


	@RequestMapping(value="/budgetuser/{budget_user_id}/actions/category/{category}", method = RequestMethod.GET)
	public String getActionsByCategory(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@PathVariable("category") Long categoryId,
			@PathVariable("budget_user_id") Long budgetUserId, Model model){
		List<Action> actions =  actionService. getAllByUserBudgetAndCategoryIdPage(budgetUserId, categoryId,page==1?0:(page-1)*MAX_RESULT+1, MAX_RESULT);
		model.addAttribute("category_id",categoryId);
		model.addAttribute("isCategory", "true");
		model.addAttribute("type", "my");
		model.addAttribute("actions",actions);
		BudgetUser budgetUser = budgetUserService.findById(budgetUserId);
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("budget_user", budgetUser);
		model.addAttribute("budget_id", budgetUser.getBudget().getId());
		model.addAttribute("pages",(int)Math.ceil(actionService.countAllByUserBudgetAndCategoryId(budgetUserId, categoryId)/MAX_RESULT+1));
		List<Double> cost = new ArrayList<>();
		for(Action a:actions){
			cost.add(actionService.getSumSubAction(a.getId()));
		}
		model.addAttribute("cost", cost);
		return "actions";
	}














	@Transactional
	private void test(Long budgetUserId){
		Role role = new Role();
		role.setName("ADMIN");

		roleService.saveRole(role);

		role = new Role();
		role.setName("USER");
		roleService.saveRole(role);

		User user = new User();
		user.setImage("none");
		user.setSecondName("secondname");
		user.setFirstName("firstname");
		user.setPassword("12345");
		user.setNickName("user");
		user.setEmail("user@mail.ru");
		user.setRole(role);
		userService.saveUser(user);
		user = new User();
		user.setImage("none");
		user.setSecondName("secondname2");
		user.setFirstName("firstname2");
		user.setPassword("12345");
		user.setNickName("user2");
		user.setEmail("user2@mail.ru");
		user.setRole(role);

		userService.saveUser(user);

		user = userService.findById(1l);

		Budget budget = new Budget();
		budget.setAdmin(user);
		budget.setDescription("my description");
		budget.setName("First budget");
		budget.setCreationDate(new LocalDate());


		BudgetUser budgetUser = new BudgetUser();
		budgetUser.setUser(user);
		budgetUser.setBudget(budget);

		budget.getBudgetUsers().add(budgetUser);

		user = userService.findById(2l);
		budgetUser = new BudgetUser();
		budgetUser.setUser(user);
		budgetUser.setBudget(budget);
		budget.getBudgetUsers().add(budgetUser);

		budgetService.saveBudget(budget);

		/*Budget budget = budgetService.findById(1l);

		BudgetUser budgetUser = new BudgetUser();
		budgetUser.setUser(user);
		budgetUser.setBudget(budget);

		budgetUserService.saveBudgetUser(budgetUser);*/

		//System.out.println("Budget after save: \n" + budget);

		Category category = new Category();
		category.setName("Add money");
		category.getSubCategories().add(new SubCategory(category, "stipend"));
		category.getSubCategories().add(new SubCategory(category, "salary"));
		category.getSubCategories().add(new SubCategory(category, "market"));
		categoryService.saveCategory(category);

		category = new Category();
		category.setName("Get money");
		category.getSubCategories().add(new SubCategory(category, "shopping"));
		category.getSubCategories().add(new SubCategory(category, "utilities"));
		category.getSubCategories().add(new SubCategory(category, "entertaiment"));
		categoryService.saveCategory(category);


		category = categoryService.findById(1l);

		SubCategory subCategory = subCategoryService.findById(5l);

		System.out.println(subCategory);

		budgetUser = new BudgetUser();
		budgetUser.setId(2l);



		Action action = new Action();
		action.setCreateDate(new LocalDate());
		action.setBudgetUser(budgetUser);
		action.setSubCategory(subCategory);
		action.getSubActions().add(new SubAction(action, 50d, "go to the cinema", "picture"));
		action.getSubActions().add(new SubAction(action, 100d, "some description", "picture"));

		actionService.saveAction(action);


		actionService.deleteAction(action);

		/*Action action = actionService.findById(3l);
		System.out.println(action);

		SubAction subAction = new SubAction();
		subAction.setAction(action);
		subAction.setPicture("none");
		subAction.setAmount(345d);
		subAction.setDescription("some description");

		subActionService.saveSubAction(subAction);*/
	}
}
