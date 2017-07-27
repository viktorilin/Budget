package com.valsoft.controller;

import com.valsoft.dto.UserDto;
import com.valsoft.model.User;
import com.valsoft.service.IRoleService;
import com.valsoft.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class AuthenticationController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IUserService userService;

	@Autowired
	IRoleService roleService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "registration";
	}

	@RequestMapping(value = "/do_registration", method = RequestMethod.POST)
	public String doRegistration(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return "registration";
		}
		User user = new User();
		user.setNickName(userDto.getNickName());
		user.setFirstName(userDto.getFirstName());
		user.setSecondName(userDto.getSecondName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(roleService.findById((long) 2));
		user.setImage("default");
		userService.saveUser(user);
		return "redirect:/";
	}


}
