package com.br.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.dao.Show;
import com.br.object.Users;
import com.br.service.GetMessageService;
import com.br.service.UsersService;

@Controller
public class ShowController  {
@Resource(name = "UsersService")
UsersService UsersService;  // I change this without impl
@Resource(name = "GetMessageService")
GetMessageService getMessageService;
private static final Logger log = LoggerFactory.getLogger(ShowController.class);

  
 
@RequestMapping(value={"/add"},method = {RequestMethod.GET})
public String add() {
	UsersService.add(new Users());
	return "redirect:/getMessage";
}
 
@RequestMapping("/home")
public String homepage(ModelMap model,HttpServletRequest request,HttpSession session) {
	String t1 = "";
  if(request.getSession().getAttribute("username")== null) {
	t1 = "Please Login before used";
	 model.put("mess",t1);
	return "welcome";}
  else if (!(boolean)request.getSession().getAttribute("admin")) {
	t1 = "Welcome, dear "+	request.getSession().getAttribute("username").toString();
  model.put("mess",t1);
	return "welcomelogin";}
  else if((boolean)request.getSession().getAttribute("admin")) 
	  t1 = "Welcome, admin "+	request.getSession().getAttribute("username").toString();
	  model.put("mess",t1);
		return "welcomeadmin";
  }


@RequestMapping("/logout")
public String logout(ModelMap model,HttpServletRequest request,HttpSession session) {
	request.getSession().removeAttribute("userID");
	request.getSession().removeAttribute("username");
	return "redirect:/home";
}

@RequestMapping("/views")
public @ResponseBody List<Show> shows(){
	return UsersService.shows();
}

@RequestMapping("/getMessage")
public String getMessage(ModelMap model) {
	model.put("message",getMessageService.getMessage());
	return "index";
}

@GetMapping(path="/sup")
public ModelAndView admlogin() {
    return new ModelAndView("LoginAdmin", "loginadmin", new Users());
}

@RequestMapping(path="/sup",method=RequestMethod.POST)
public String adminlogin(@Validated @ModelAttribute("loginadmin") Users users,
        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) {
	model.put("type","Login");
	String[] res = UsersService.verify(users,0);
    if(res[0].equals("Not")) {
    	model.put("errormess","This is not an admin account");
    	return "fail";}
    else if(res[0].equals("Fail")) {
    	model.put("errormess","Incorrect password");
	return "fail";}
    else{
    	request.getSession().setAttribute("username", users.getUsername());
    	request.getSession().setAttribute("userID", res[1]);
    	request.getSession().setAttribute("admin", true);
    return "redirect:/home";}
} 

@GetMapping(path="/login")
public ModelAndView showlogin() {
    return new ModelAndView("LoginName", "loginname", new Users());
}

@RequestMapping(path="/login",method=RequestMethod.POST)
public String login(@Validated @ModelAttribute("loginname") Users users,
        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) {
	model.put("type","Login");
	String[] res = UsersService.verify(users,1);
    if(res[0].equals("Not")) {
    	model.put("errormess","No such account exist");
    	return "fail";}
    else if(res[0].equals("Fail")) {
    	model.put("errormess","Incorrect password");
	return "fail";}
    else{
    	request.getSession().setAttribute("username", users.getUsername());
    	request.getSession().setAttribute("userID", res[1]);
    	request.getSession().setAttribute("admin", false);
    return "redirect:/home";}
} 

@GetMapping(path="/login2")
public ModelAndView showlogin2() {
    return new ModelAndView("LoginEmail", "loginemail", new Users());
}

@RequestMapping(path="/login2",method=RequestMethod.POST)
public String login2(@Validated @ModelAttribute("loginemail") Users users,
        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) {
	model.put("type","Login");
	String[] res = UsersService.verify(users,2);
    if(res[0].equals("Not")) {
	    model.put("errormess","No such account exist");
	    return "fail";}
    else if(res[0].equals("Fail")) {
    	model.put("errormess","Incorrect password");
	return "fail";}
    else{
    	request.getSession().setAttribute("username", res[0]);
    	request.getSession().setAttribute("userID", res[1]);
    	request.getSession().setAttribute("admin", false);
	return "redirect:/home";}
}

@GetMapping(path="/reg")
public ModelAndView showForm() {
    return new ModelAndView("register", "users", new Users());
}

@RequestMapping(path="/reg",method=RequestMethod.POST)
public String regusers(@Validated @ModelAttribute("users") Users users,
        BindingResult result, ModelMap model) {
	String res = UsersService.add(users);
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
		model.put("errormess","Username in used, please use another user name");
		return "fail";}
	else{
		model.put("type","Register");
		model.put("errormess","There's already an account with this email!");
		return "fail";}
}


}
