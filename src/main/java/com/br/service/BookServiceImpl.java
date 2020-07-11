package com.br.service;

import java.awt.print.Book;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dao.Bookdao;
import com.br.dao.UsersDao;
import com.br.object.Books;
import com.br.object.Users;

@Service("BookService")
public class BookServiceImpl implements BookService{
	@Autowired
	private Bookdao BookDao; 
	@Autowired
	private UsersDao Userdao;
	@Override
	public Books getrev(Users user,Books books) throws MalformedURLException, ProtocolException, IOException {
		books = BookDao.searchbyisbn(user, books);
		if(books.getIsbn() != 0)
		return books;
		else {
			books.setBookname("Not Found");
			return books;
		}
		
	}
	@Override
	public ArrayList<Books> gethistory(Users user) throws MalformedURLException, ProtocolException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Books> ret = BookDao.gethistory(user);
		if(ret.size()==0) {
			Books tmp = new Books();
			tmp.setBookname("No History");
			tmp.setIsbn(0);
			ret.add(tmp);
		}
		return ret; 
	}
	@Override
	public void addfav(Users user, Books books) {
		// TODO Auto-generated method stub
		BookDao.addfavorite(user, books);
	}
	@Override
	public ArrayList<Books> getfav(Users user) throws MalformedURLException, ProtocolException, IOException {
		ArrayList<Books> ret = BookDao.getfavorite(user);
		if(ret.size()==0) {
			Books tmp = new Books();
			tmp.setBookname("No Favorite");
			tmp.setIsbn(0);
			ret.add(tmp);
		}
		return ret; 
	}
	
}
