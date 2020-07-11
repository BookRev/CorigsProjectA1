package com.br.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.br.object.Operation;
import com.br.object.Users;

public interface UsersDao {
     public String add(Users user);
     public String addad(Users admin,Users user);
     public void changepw(String username,String pw);
     public void changepw(int id,String pw);
     public void delete(Users user);
     public void delete(int id);
     public void view(Users admin);
     public void update(int id, String newbook);
     public void forgetpw(String email,int num);
     public Users verify(Users user);
     public Users adminverify(Users user);
     public Users verifybyemail(Users user);
     public ArrayList<Users> userinfo();
     public ArrayList<Operation> userinfo2(); 
     public String deleteuser(Users admin,Users user);
     public List<Show> shows(); //for testing the connection to database
}
