package com.br.service;

import java.awt.print.Book;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import com.br.object.Books;
import com.br.object.Users;

public interface BookService {
public Books getrev(Users user,Books books) throws MalformedURLException, ProtocolException, IOException;
public ArrayList<Books> gethistory(Users user) throws MalformedURLException, ProtocolException, IOException;
public void addfav(Users user, Books books);
public ArrayList<Books> getfav(Users user) throws MalformedURLException, ProtocolException, IOException;
}
