package com.br.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
