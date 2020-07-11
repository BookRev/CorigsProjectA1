package com.br.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.br.dao.Show;
import com.br.object.Books;
import com.br.object.Operation;
import com.br.object.Users;
import com.br.service.GetMessageService;
import com.br.service.UsersService;

@Controller
public class AdminController {
	@Resource(name = "UsersService")
	UsersService UsersService;  // I change this without impl
	@Resource(name = "GetMessageService")
	GetMessageService getMessageService;
	
	
	@GetMapping(path="/del")
	public String del1(ModelMap model,HttpServletRequest request,HttpSession session) {
		if(request.getSession().getAttribute("username")== null)
			   return "redirect:/home";
			else if(!(boolean)request.getSession().getAttribute("admin"))
			{
				model.put("type","Admin");
		    	model.put("errormess","Please login as an admin and access");
		    	return "fail";
			}
		ArrayList<Users> us = UsersService.getuserinfo();
		Users[] us2 = us.toArray(new Users[us.size()]);
		model.put("userinfo", us2);
	    return "showuser";
	}

	@RequestMapping(path="/del",method=RequestMethod.POST)
	public String del2(@Validated @ModelAttribute("choosen") Users user,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		else if(!(boolean)request.getSession().getAttribute("admin"))
		{
			model.put("type","Admin");
	    	model.put("errormess","Please login as an admin and access");
	    	return "fail";
		}
		Users admin = new Users();
		admin.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		String delres = UsersService.delete(admin,user);
		model.put("message","delete success");
		return "index";
		//return "redirect";
	}
	
	@GetMapping(path="/addadmin")
	public ModelAndView showForm() {
	    return new ModelAndView("addadmin", "newadmin", new Users());
	}

	@RequestMapping(path="/addadmin",method=RequestMethod.POST)
	public String regusers(@Validated @ModelAttribute("newadmin") Users users,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) {
		if(request.getSession().getAttribute("username")== null)
			   return "redirect:/home";
			else if(!(boolean)request.getSession().getAttribute("admin"))
			{
				model.put("type","Admin");
		    	model.put("errormess","Please login as an admin and access");
		    	return "fail";
			}
		Users admin = new Users();
		admin.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		String res = UsersService.addad(admin,users);
		model.put("username",users.getUsername());
		model.put("email",users.getEmail());
		int pwl = users.getPassword().length();
		String s1 = "";
		for(int i=0;i<pwl;i++)
			s1 += "*";
		model.put("password",s1);
		if(res.equals("finish"))
		return "regsucc";
		else if(res.equals("cusername")) {
			model.put("type","Register");
			model.put("errormess","Adminname in used, please use another admin name");
			return "fail";}
		else{
			model.put("type","Register");
			model.put("errormess","There's already an account with this email!");
			return "fail";}
	}
	
	@RequestMapping("/shows")
	public @ResponseBody List<Show> shows(HttpServletRequest request,HttpSession session){
		Users admin = new Users();
		admin.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		UsersService.view(admin);
		return UsersService.shows();
	}

	@GetMapping(path="/adminhistory")
	public String adminhistory(ModelMap model,HttpServletRequest request,HttpSession session) {
		if(request.getSession().getAttribute("username")== null)
			   return "redirect:/home";
			else if(!(boolean)request.getSession().getAttribute("admin"))
			{
				model.put("type","Admin");
		    	model.put("errormess","Please login as an admin and access");
		    	return "fail";
			}
		ArrayList<Operation> us = UsersService.getadmininfo();
		Operation[] us2 = us.toArray(new Operation[us.size()]);
		model.put("admininfo", us2);
	    return "showop";
	}
	
}
