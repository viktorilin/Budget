package com.valsoft.controller;

import com.valsoft.model.Action;
import com.valsoft.model.Budget;
import com.valsoft.model.Role;
import com.valsoft.service.ActionService;
import com.valsoft.service.IActionService;
import com.valsoft.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
@ComponentScan("com.valsoft")
public class AppController {

	@Autowired
	IRoleService service;



	@Autowired
	MessageSource messageSource;

	@Autowired
	IActionService actionService;



	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listBudgets(ModelMap model) {
		List<Action> actions = actionService.getAllByUserId(3L);

		for(Action a : actions){
			System.out.println(a.getType());
		}

		return "index";
	}


	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newBudget(ModelMap model) {
		Budget budget = new Budget();
		model.addAttribute("budget", budget);
		model.addAttribute("edit", false);
		return "index";
	}

	@RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
	public String reg()
	{
		System.out.println("log");
		return "registration";
	}




}

