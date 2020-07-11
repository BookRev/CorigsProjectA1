package com.br.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import com.br.object.Books;
import com.br.object.Users;

public interface Bookdao {
public Books searchbyisbn(Users user,Books book) throws MalformedURLException, ProtocolException, IOException;
public String[] searchbybarcode(Books book);
public ArrayList<Books> getfavorite(Users user)throws MalformedURLException, ProtocolException, IOException;
public ArrayList<Books> gethistory(Users user) throws MalformedURLException, ProtocolException, IOException;
Books searchbyisbn2(Users user,Books book) throws MalformedURLException, ProtocolException, IOException;
String[] addhistory(Users user, Books book, boolean isfav);
public void addnewbook(ArrayList<String> as,Long isbn,int isexist);
public void addfavorite(Users user,Books book);
}
