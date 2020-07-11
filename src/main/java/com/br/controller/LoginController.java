package com.br.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Collections;

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
import org.springframework.web.servlet.ModelAndView;

import com.br.service.GetMessageService;
import com.br.service.UsersService;
import com.br.object.Books;
import com.br.object.Users;
import com.br.service.BookService;
 
@Controller 
public class LoginController {
	@Resource(name = "UsersService")
	UsersService UsersService;  // I change this without impl
	@Resource(name = "GetMessageService")
	GetMessageService getMessageService;
	@Resource(name = "BookService")
	BookService BookService;
	
	
	@GetMapping(path="/search")
	public ModelAndView searching1() {
	    return new ModelAndView("Search", "search", new Books());
	}

	@RequestMapping(path="/search",method=RequestMethod.POST)
	public String searching2(@Validated @ModelAttribute("search") Books book,
			@Validated @ModelAttribute("back") Books book2,Users user,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		book = BookService.getrev(user, book);
	    if(book.getIsbn()==0) {
	    	model.put("type","Search");
	    	model.put("errormess","No result is found by this isbn");
	    	return "fail";}
	    model.put("res",book);
	    model.put("reviews", book.getReviews());
	    model.put("rates", book.getRateofReviews());
	    return "review";
	}
	
	//@GetMapping(path="/history")
	//public String history1(ModelMap model) {
	//    return "history1";
	//}

	@RequestMapping(path="/history",method=RequestMethod.GET)
	public String history2(Users user, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		ArrayList<Books> bs = BookService.gethistory(user);
	    if(bs.get(0).getIsbn()==0) {
	    	model.put("type","History");
	    	model.put("errormess","No history yet");
	    	return "fail";}
	    Collections.reverse(bs);
	    Books[] bs2 = bs.toArray(new Books[bs.size()]);
	    model.put("title","History");
	    model.put("myhistory", bs2);
	    return "history2";
	}
	
	@RequestMapping(path="/history1",method=RequestMethod.POST)
	public String searchinghist(@Validated @ModelAttribute("choosen") Books book,
			@Validated @ModelAttribute("back") Books book2,Users user,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		book = BookService.getrev(user, book);
	    if(book.getIsbn()==0) {
	    	model.put("type","Search");
	    	model.put("errormess","No result is found by this isbn");
	    	return "fail";}
	    model.put("res",book);
	    model.put("reviews", book.getReviews());
	    model.put("rates", book.getRateofReviews());
	    return "review";
	}
	
	@RequestMapping("/favorite")
	public String viewfavorite(Users user, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException {
		if(request.getSession().getAttribute("username")== null)
			   return "redirect:/home";
			user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
			ArrayList<Books> bs = BookService.getfav(user);
		    if(bs.get(0).getIsbn()==0) {
		    	model.put("type","History");
		    	model.put("errormess","No favorite book yet");
		    	return "fail";}
		    Books[] bs2 = bs.toArray(new Books[bs.size()]);
		    model.put("title","Favorite");
		    model.put("myhistory", bs2);
		    return "history2";
	}
	
	
	@RequestMapping("/newfavorite")
	public String addfavorite(@Validated @ModelAttribute("back") Books book,BindingResult result,ModelMap model,HttpServletRequest request,HttpSession session) {
		if(request.getSession().getAttribute("username")== null)
			return "redirect:/home";
		Users user = new Users();
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		BookService.addfav(user, book);
		model.put("message","add favorite success");
		return "index";
	}
	
	
}
