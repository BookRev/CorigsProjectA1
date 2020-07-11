package com.br.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dao.GetMessageDao;
import com.br.dao.Show;
import com.br.dao.UsersDao;
import com.br.object.Operation;
import com.br.object.Users;

@Service("UsersService")
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao UsersDao; 


	public String add(Users user) { 
		// TODO Auto-generated method stub
		return UsersDao.add(user);
	}
	
	public String addad(Users admin,Users user) { 
		// TODO Auto-generated method stub
		return UsersDao.addad(admin,user);
	}

	public void view(Users admin) {
		UsersDao.view(admin);
	}
	public void changepw(String username, String pw) {
		// TODO Auto-generated method stub
		UsersDao.changepw(username, pw);
	}

	public void changepw(int id, String pw) {
		// TODO Auto-generated method stub
		UsersDao.changepw(id,pw);
	}

	public String delete(Users admin,Users user) {
		// TODO Auto-generated method stub
		return UsersDao.deleteuser(admin,user);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		UsersDao.delete(id);
	}

	public void update(int id, String newbook) {
		// TODO Auto-generated method stub
		UsersDao.update(id, newbook);
	}

	public void forgetpw(String email, int num) {
		// TODO Auto-generated method stub
		UsersDao.forgetpw(email, num);
	}

	public List<Show> shows() {
		// TODO Auto-generated method stub
		return UsersDao.shows();
	}

	@Override
	public String[] verify(Users user, int type) {
		Users user2 = new Users();
		if(type == 1) 
		user2 = UsersDao.verify(user);
		else if(type == 2)
		user2 = UsersDao.verifybyemail(user);
		else if(type == 0)
		user2 = UsersDao.adminverify(user);
		if(user2.getId()==0)
			return new String[]{"Not"};
		else {
			if(user2.getPassword().equals(user.getPassword()))
				return new String[]{user2.getUsername(),user2.getId()+""};
			else
				return new String[]{"Fail"};
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Users> getuserinfo() {
		// TODO Auto-generated method stub
		return UsersDao.userinfo();
	}

	@Override
	public ArrayList<Operation> getadmininfo() {
		// TODO Auto-generated method stub
		return UsersDao.userinfo2();
	}

}
