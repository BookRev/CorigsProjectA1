package com.br.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
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
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.br.object.Books;
import com.br.object.Page;
import com.br.object.Users;
import com.br.service.BookService;
import com.br.dao.Barcode;
 
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
	
	@GetMapping(path="/search2")
	public ModelAndView searchingbar() {
	    return new ModelAndView("uploadsearch", "search", new Books());
	}
	
	@GetMapping(path="/search3")
	public ModelAndView searchingname() {
	    return new ModelAndView("Searchname", "title", new Books());
	}
	
	
	@RequestMapping(path="/search3",method=RequestMethod.POST)
	public String searchingname(@Validated @ModelAttribute("title") Books book,
			@Validated @ModelAttribute("search") Books book2,@Validated @ModelAttribute("page0") Page pages,
			@Validated @ModelAttribute("page2") Page pages2,Users user,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException, NotFoundException, FormatException, ChecksumException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
       // book.setIsbn(Long.parseLong(book.getSisbn().replaceAll("[^0-9]", "")));
        String title = book.getBookname();
        ArrayList<Books> bookinfo = BookService.searchbyname(title, 0);
	    if(bookinfo.get(0).getIsbn()==0) {
	    	model.put("type","Search");
	    	model.put("errormess","No result is found by this title");
	    	return "fail";}
	    model.put("founded",bookinfo.get(0).getIsbn());
	    model.put("page",1);
	    Long allpage = bookinfo.get(0).getIsbn()/10+1;
	    if(allpage > 100)
	    model.put("allpage", 100);
	    else
	    model.put("allpage", allpage);
	    model.put("searchtitle", title);
	    bookinfo.remove(0);
        model.put("ret", bookinfo);
	    return "searchresult";
	}
	
	@RequestMapping(path="/search4",method=RequestMethod.POST)
	public String searchingname(@Validated @ModelAttribute("title") Books book,
			@Validated @ModelAttribute("search") Books book2,
			@Validated @ModelAttribute("page0") Page pages,
			@Validated @ModelAttribute("page1") Page pages1,
			@Validated @ModelAttribute("page2") Page pages2, BindingResult result, BindingResult result2,
			Users user,
	         ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException, NotFoundException, FormatException, ChecksumException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		int currentpage = 0;
		try {
		pages.setPageno(Integer.parseInt(pages.getSpageno()));
		}catch (Exception ee) {
			model.put("type","Page number");
	    	model.put("errormess","Did you type an invalid page number?");
	    	return "fail";
		}
		int total = pages.getTotal();
		if(total >100)
			total = 100;
		if(pages.getType().equals("next"))
		 currentpage= pages.getPageno()+1;
		if(pages.getType().equals("previous"))
			 currentpage= pages.getPageno()-1;
		if(pages.getType().equals("jump"))
			currentpage = pages.getPageno();
		if(currentpage>total)
			currentpage = total;
		if(currentpage<1)
			currentpage = 1;
		currentpage = currentpage -1;
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
       // book.setIsbn(Long.parseLong(book.getSisbn().replaceAll("[^0-9]", "")));
        String title = pages.getTitle();
        ArrayList<Books> bookinfo = BookService.searchbyname(title, currentpage);
	    model.put("founded",bookinfo.get(0).getIsbn());
	    model.put("page",currentpage+1);
	    model.put("searchtitle", title);
	    model.put("allpage", total);
	    bookinfo.remove(0);
        model.put("ret", bookinfo);
	    return "searchresult";
	}
	
	
	@RequestMapping(path="/search2",method=RequestMethod.POST)
	public String searchingbar2(@Validated @ModelAttribute("search") Books book,
			Users user, BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException, NotFoundException, FormatException, ChecksumException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		try {	
		   String readr = Barcode.decodeCode(book.getBarpic());
			long readisbn = Long.parseLong(readr);
			model.put("isbn", readisbn);
			return "uploadsearch2";}
		catch(Exception ee) {
			model.put("type","Scanning");
	    	model.put("errormess","No isbn is found in this pic");
	    	return "fail";
		}
		}

	@RequestMapping(path="/search",method=RequestMethod.POST)
	public String searching2(@Validated @ModelAttribute("search") Books book,
			@Validated @ModelAttribute("back") Books book2,Users user,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException, NotFoundException, FormatException, ChecksumException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
		try {
        book.setIsbn(Long.parseLong(book.getSisbn().replaceAll("[^0-9]", "")));
		book = BookService.getrev(user, book);}
		catch(Exception ee)
		{model.put("type","Search");
    	model.put("errormess","Did you type an invalid isbn?");
    	return "fail";}
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
	    model.put("lasthist", bs2[0]);
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
		    	model.put("type","Favorite");
		    	model.put("errormess","No favorite book yet");
		    	return "fail";}
		    Books[] bs2 = bs.toArray(new Books[bs.size()]);
		    Books lastfav = bs2[bs2.length-1];
		    model.put("title","Favorite");
		    model.put("myhistory", bs2);
		    model.put("lastfav", lastfav);
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
		return "redirect:/favorite";
	}
	
	@RequestMapping(path="/delbook",method=RequestMethod.POST)
	public String delbook(@Validated @ModelAttribute("delbook") Books book,
			Users user,
	        BindingResult result, ModelMap model,HttpServletRequest request,HttpSession session) throws MalformedURLException, ProtocolException, IOException {
		if(request.getSession().getAttribute("username")== null)
		   return "redirect:/home";
		user.setId(Integer.parseInt(request.getSession().getAttribute("userID").toString()));
	    BookService.deletebook(user, book);
	    if(book.getFavorhist().equals("Favorite"))
	    	return "redirect:/favorite";
	    else
	    	return "redirect:/history";
	}
	

}
