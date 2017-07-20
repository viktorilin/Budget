package com.valsoft.controller;

import com.valsoft.model.Budget;
import com.valsoft.model.Role;
import com.valsoft.model.User;
import com.valsoft.service.IBudgetService;
import com.valsoft.service.IBudgetUserService;
import com.valsoft.service.IRoleService;
import com.valsoft.service.IUserService;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Asus on 28.06.2017.
 */
@Controller
@RequestMapping("/budget")
@ComponentScan("com.valsoft")
public class BudgetController {

    @Autowired
    private IBudgetUserService budgetUserService;
    @Autowired
    private IBudgetService budgetService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private String listBudget(ModelMap model)
            throws SQLException, IOException, ServletException {
        List<Budget> budgetList = budgetService.findAllBudgets();
        model.addAttribute("listBudgets", budgetList);
        return "BudgetList";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    private String showNewForm(ModelMap model)
            throws ServletException, IOException {
        model.addAttribute("budget", new Budget());
        return "BudgetForm";
    }

    @RequestMapping(value = "/edit/{budget_id}", method = RequestMethod.GET)
    private String showEditForm(ModelMap model,@PathVariable Long budget_id)
            throws SQLException, ServletException, IOException {
        Budget existingBudget = budgetService.findById(budget_id);
        model.addAttribute("budget", existingBudget);
        return "redirect:/budget/list";
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    private String insertBudget(@ModelAttribute("budget") Budget budget, ModelMap model)
            throws SQLException, IOException {
//        LocalDate localDate = new LocalDate();
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
//        String formattedDate = (String) model.get(formatter.print(localDate));
//        // LocalDate creationDate = Long.parseLong(request.getDateHeader("creationDate"));
//        // String creationDate = request.getParameter("creationDate");
//        //LocalDate creationDate = request.getDateHeader("creationDate");
//        String description = (String) model.get("description");
//        String name = (String) model.get("name");

//        Budget newBudget = new Budget();
//        newBudget.setDescription(description);
//        newBudget.setName(name);
//        newBudget.setCreationDate(localDate);
       // model.addAttribute("userList",userService.findAllUsers());
        User user = new User();
//        Role role = new Role();
//        role.setName("test");
        //roleService.saveRole(role);
        user.setNickName("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setFirstName("test");
        user.setImage("test");
        user.setSecondName("test");
     //   user.setRole(role);
        userService.saveUser(user);
        budget.setAdmin(user);
        budgetService.saveBudget(budget);
        return "redirect:/budget/list";
    }

    //
//    private void updateBudget(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        Long id = Long.parseLong(request.getParameter("id"));
//        //String title = request.getParameter("creationDate");
//        String description = request.getParameter("description");
//        String name = request.getParameter("name");
//        LocalDate localDate = new LocalDate();
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
//        String formattedDate = request.getParameter(formatter.print(localDate));
//        Budget budget = new Budget(id, description, name, localDate);
//        budgetService.updateBudget(budget);
//        response.sendRedirect("list");
//    }
//
    @RequestMapping(value = "/delete/{budget_id}", method = RequestMethod.GET)
    private String deleteBudget(@PathVariable Long budget_id, ModelMap model)
            throws SQLException, IOException {

        System.out.println("asdsadas");
        Budget budget = budgetService.findById(budget_id);
        budgetService.deleteBudget(budget);
        System.out.println(budget.getId());
        // budgetService.deleteBudgetById(id);
        return "redirect:/budget/list";

    }

    @RequestMapping(value = "/users/{budget_id}", method = RequestMethod.GET)
    private String showUsersBudget(@PathVariable Long budget_id, ModelMap model)
            throws SQLException, IOException {

        model.addAttribute("listBudgetUsers",userService.findByBudgetId(budget_id));
        return "BudgetUserList";

    }

    @RequestMapping(value = "/adminedBudget/{budget_id}", method = RequestMethod.GET)
    private String showAdminedBudget(@PathVariable Long user_id, ModelMap model)
            throws SQLException, IOException {
        model.addAttribute("listBudgetAdmined",userService.findById(user_id).getAdminedBudgets());
        return "AdninedBudgets";

    }

    //додавання юзерів в бюджет окремо


}
