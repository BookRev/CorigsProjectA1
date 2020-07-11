package com.br.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.br.object.Operation;
import com.br.object.Users;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao{
	@Autowired
	JdbcTemplate jdbcTemplateObject;

    
	public String add(Users user) {
		String SQL = "INSERT IGNORE INTO USER (email,username,password) VALUES(?,?,?);";
	//	jdbcTemplateObject.update( SQL, new Object[]{"224@g.com","Zara", 11,126632} );
			if(isUserExists(user))
				return "cusername";
			else if(isUserExists2(user))
				return "cemail";
			else{
			jdbcTemplateObject.update( SQL, new Object[]{user.getEmail(),user.getUsername(),user.getPassword()} );	
			return "finish"; 
			} 
	} 
	
	public String addad(Users admin,Users user) {
		String SQL = "INSERT IGNORE INTO ADMIN (adminemail,adminname,adminpassword) VALUES(?,?,?);";
	//	jdbcTemplateObject.update( SQL, new Object[]{"224@g.com","Zara", 11,126632} );
			if(isAdminExists(user))
				return "cusername";
			else if(isAdminExists2(user))
				return "cemail";
			else{
			jdbcTemplateObject.update( SQL, new Object[]{user.getEmail(),user.getUsername(),user.getPassword()} );	
			addadminhistory(admin,user,"addadmin");
			return "finish"; 
			} 
	} 
	
	public void view(Users admin) {
		Users user = new Users();
		user.setEmail("");
		user.setId(0);
		user.setPassword("");
		user.setUsername("");
		addadminhistory(admin,user,"view");
	}

	public void changepw(String username, String pw) {
		// TODO Auto-generated method stub
		
	}

	public void changepw(int id, String pw) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Users user) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public void update(int id, String newbook) {
		// TODO Auto-generated method stub
		
	}

	public void forgetpw(String email, int num) {
		// TODO Auto-generated method stub
		
	}

	//testing
	public List<Show> shows() {
		// TODO Auto-generated method stub
		return jdbcTemplateObject.query("select * from USER", new ShowRowMapper());
	}

	@Override
	public Users verify(Users user) {
		if(!isUserExists(user))
		{
			Users erroruser = new Users();
			erroruser.setId(0);
			return erroruser;
		}
		else {
			String sql = "SELECT password FROM USER WHERE username = ?";
			String sql2 = "SELECT userID FROM USER WHERE username = ?";
		// TODO Auto-generated method stub
			String temp = jdbcTemplateObject.queryForObject(sql,new Object[] {user.getUsername()}, String.class);
			int tid = jdbcTemplateObject.queryForObject(sql2,new Object[] {user.getUsername()}, Integer.class);
			Users user2 = new Users();
			if(!temp.isEmpty()) {
			user2.setPassword(temp);
			user2.setId(tid);
			user2.setUsername(user.getUsername());}
			else {
				user2.setId(0);
			}
			return user2;
		}
	}
	
	@Override
	public Users adminverify(Users user) {
		if(!isAdminExists(user))
		{
			Users erroruser = new Users();
			erroruser.setId(0);
			return erroruser;
		}
		else {
			String sql = "SELECT adminpassword FROM ADMIN WHERE adminname = ?";
			String sql2 = "SELECT adminID FROM ADMIN WHERE adminname = ?";
		// TODO Auto-generated method stub
			String temp = jdbcTemplateObject.queryForObject(sql,new Object[] {user.getUsername()}, String.class);
			int tid = jdbcTemplateObject.queryForObject(sql2,new Object[] {user.getUsername()}, Integer.class);
			Users user2 = new Users();
			if(!temp.isEmpty()) {
			user2.setPassword(temp);
			user2.setId(tid);
			user2.setUsername(user.getUsername());}
			else {
				user2.setId(0);
			}
			return user2;
		}
	}
	
	@Override
	public Users verifybyemail(Users user){
		if(!isUserExists2(user))
		{
			Users erroruser = new Users();
			erroruser.setId(0);
			return erroruser;
		}
		else {
			String sql = "SELECT password FROM USER WHERE email = ?";
			String sql2 = "SELECT username FROM USER WHERE email = ?";
			String sql3 = "SELECT userID FROM USER WHERE email = ?";
		// TODO Auto-generated method stub
			String temp = jdbcTemplateObject.queryForObject(sql,new Object[] {user.getEmail()}, String.class);
			String temp2 = jdbcTemplateObject.queryForObject(sql2,new Object[] {user.getEmail()}, String.class);
			int tid = jdbcTemplateObject.queryForObject(sql3,new Object[] {user.getEmail()}, Integer.class);
			Users user2 = new Users();
			if(!temp.isEmpty()) {
			user2.setPassword(temp);
			user2.setId(tid);
			user2.setUsername(temp2);}
			else {
				user2.setId(0);
			}
			return user2;
		}
	}
	
private boolean isUserExists(Users usertemp) {
	String username = usertemp.getUsername();
        String sql = "SELECT count(*) FROM USER WHERE username = ?";
        boolean result = false;
         int count = jdbcTemplateObject.queryForObject(sql, new Object[] { username }, Integer.class);
     if (count >= 1) {
	    result = true;
    	 }
       return result;
   }


private boolean isUserExists2(Users usertemp) {
	String email = usertemp.getEmail();
    String sql = "SELECT count(*) FROM USER WHERE email = ?";
    boolean result = false;
     int count = jdbcTemplateObject.queryForObject(sql, new Object[] { email }, Integer.class);
 if (count >= 1) {
	    result = true;
       }
   return result;
}

private boolean isAdminExists(Users usertemp) {
	String username = usertemp.getUsername();
        String sql = "SELECT count(*) FROM ADMIN WHERE adminname = ?";
        boolean result = false;
         int count = jdbcTemplateObject.queryForObject(sql, new Object[] { username }, Integer.class);
     if (count >= 1) {
	    result = true;
    	 }
       return result;
   }

private boolean isAdminExists2(Users usertemp) {
	String email = usertemp.getEmail();
    String sql = "SELECT count(*) FROM ADMIN WHERE adminemail = ?";
    boolean result = false;
     int count = jdbcTemplateObject.queryForObject(sql, new Object[] { email }, Integer.class);
 if (count >= 1) {
	    result = true;
       }
   return result;
}

@Override
public ArrayList<Users> userinfo() {
	String sql = "SELECT password FROM USER ";
	String sql2 = "SELECT username FROM USER ";
	String sql3 = "SELECT userID FROM USER ";
	String sql4 = "SELECT email FROM USER ";
// TODO Auto-generated method stub
//	String temp = jdbcTemplateObject.queryForObject(sql,new Object[] {user.getEmail()}, String.class);
//	String temp2 = jdbcTemplateObject.queryForObject(sql2,new Object[] {user.getEmail()}, String.class);
//	int tid = jdbcTemplateObject.queryForObject(sql3,new Object[] {user.getEmail()}, Integer.class);
	ArrayList<Users> ret = new ArrayList<Users>();
	List<String> ta = jdbcTemplateObject.queryForList(sql,String.class);
	List<String> tb = jdbcTemplateObject.queryForList(sql2,String.class);
	List<Integer> tc = jdbcTemplateObject.queryForList(sql3,Integer.class);
	List<String> td = jdbcTemplateObject.queryForList(sql4,String.class);
	for(int i=0; i< ta.size();i++){
	Users user2 = new Users();
	user2.setPassword(ta.get(i));
	user2.setId(tc.get(i));
	user2.setUsername(tb.get(i));
	user2.setEmail(td.get(i));
	ret.add(user2);
	}
	return ret;
}

@Override
public String deleteuser(Users admin,Users user) {
	if(!isUserExists(user))
	{
		return "Not";
	}
	String SQL0 = "DELETE FROM USER where userID = ?";	
	jdbcTemplateObject.update( SQL0, new Object[]{user.getId()} );	
	addadminhistory(admin,user,"delete");
	return "Fin";
}


private void addadminhistory(Users admin, Users user,String type) {
	String sql0 = "insert into ADMINHISTORY(adminID,ID,username,email,type) VALUES(?,?,?,?,?)";
			jdbcTemplateObject.update(sql0, new Object[]{admin.getId(),user.getId(),user.getUsername(),user.getEmail(),type} );	
}

@Override
public ArrayList<Operation> userinfo2() {
	String sql0 = "Select adminID from ADMINHISTORY";
	String sql1 = "Select adminname from ADMINHISTORY Natural Join ADMIN";
	String sql2 = "Select ID from ADMINHISTORY";
	String sql3 = "Select username from ADMINHISTORY";
	String sql4 = "Select type from ADMINHISTORY";
	ArrayList<Operation> ret = new ArrayList<Operation>();
	List<Integer> aid = jdbcTemplateObject.queryForList(sql0,Integer.class);
	List<String> an = jdbcTemplateObject.queryForList(sql1,String.class);
	List<Integer> uid = jdbcTemplateObject.queryForList(sql2,Integer.class);
	List<String> un = jdbcTemplateObject.queryForList(sql3,String.class);
	List<String> ty = jdbcTemplateObject.queryForList(sql4,String.class);
	for(int i=0; i< aid.size();i++){
		Operation op = new Operation();
		op.setAdminid(aid.get(i));
		op.setAdminname(an.get(i));
		op.setId(uid.get(i));
		op.setUsername(un.get(i));
		op.setType(ty.get(i));
		ret.add(op);
		}
	
	return ret;
}
}


//testing

class ShowRowMapper implements RowMapper<Show> {
    public Show mapRow(ResultSet rs, int rowNum) throws SQLException {
        Show show = new Show();
        show.setEmail(rs.getString("EMAIL"));
        show.setUsername(rs.getString("USERNAME"));
        show.setId(rs.getInt("USERID"));
        show.setUpassword(rs.getString("PASSWORD"));
        return show;
    }
    


}
