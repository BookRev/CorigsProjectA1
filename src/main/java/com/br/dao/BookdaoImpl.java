package com.br.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.br.object.Books;
import com.br.object.Users;

@Repository("bookDao")
public class BookdaoImpl implements Bookdao{
	@Autowired 
	JdbcTemplate jdbcTemplateObject;
 
	@Override
	public Books searchbyisbn(Users user,Books book) throws MalformedURLException, ProtocolException, IOException {
		if(isBookExists(book)<1&&(book.getIsbn()+"").length()==13) {	
		ArrayList<String> as = FindReview.FindReviews(book.getIsbn()+"");
		if(as.contains("review")&&user.getId()>0) {
			addnewbook(as,book.getIsbn(),isBookExists(book));
			try {
			    String presql = "SELECT COUNT(cover) from BOOK where isbn = ?";
				int count = jdbcTemplateObject.queryForObject(presql, new Object[] { book.getIsbn()}, Integer.class);
				String updatecover = ""; 
				if(count == 0) {
					JavaGetRequest.findisbn(book);
					updatecover = "UPDATE BOOK SET cover = ? WHere isbn = ?;";
					jdbcTemplateObject.update( updatecover, new Object[]{book.getBookpic(),book.getIsbn()} );	
				}
				}catch(Exception ee) {}
			addhistory(user,book,false);
			book.setBookname(as.get(0));
			book.setLink(new String[] {as.get(1)});
			book.setRate(new Float[] {Float.parseFloat(as.get(2))});
			book.setAuthor(as.get(3));
			int i = 4;
			ArrayList<String> revs = new ArrayList<String>();
			ArrayList<Float> rats = new ArrayList<Float>();
			String summary = ""; 
		try {
			while(as.get(i).equals("review")) {
				summary = as.get(i+1) + ":  \n" + as.get(i+2);
				revs.add(summary);
				rats.add(Float.parseFloat(as.get(i+3)));
				i = i + 4;
				book.setReviews(revs.toArray(new String[revs.size()]));
				book.setRateofReviews((rats.toArray(new Float[revs.size()])));
			return book;}
		}catch(Exception ee) {}
		}else {
			book.setIsbn(0);
		return book;}} 
		if((book.getIsbn()+"").length()!=13)
         {
	      book.setIsbn(0);
	      return book;
         }
		try {
		    String presql = "SELECT COUNT(cover) from BOOK where isbn = ?";
			int count = jdbcTemplateObject.queryForObject(presql, new Object[] { book.getIsbn()}, Integer.class);
			String updatecover = "";
			if(count == 0) {
				JavaGetRequest.findisbn(book);
				updatecover = "UPDATE BOOK SET cover = ? WHere isbn = ?;";
				jdbcTemplateObject.update( updatecover, new Object[]{book.getBookpic(),book.getIsbn()} );	
			}
			}catch(Exception ee) {}
			return searchbyisbn2(user,book);
	}

	@Override
	public Books searchbyisbn2(Users user, Books book) throws MalformedURLException, ProtocolException, IOException {
		String sql = "SELECT title FROM BOOK WHERE isbn = ?";
		String sql2 = "SELECT url FROM WEBSITE WHERE isbn = ? and webname = ?";
		String sql4 = "SELECT author FROM BOOK WHERE isbn = ?";
		String sql5 = "SELECT summary FROM REVIEW WHERE isbn = ?";
		String sql6 = "SELECT rating FROM REVIEW WHERE isbn = ?";
		String sql7 = "SELECT overallrating FROM WEBSITE WHERE isbn = ? and webname = ?";
		String sql8 = "SELECT cover FROM BOOK WHERE isbn = ?";
		//if(user.getId()>=1)
        //	addhistory(user,book,false);
		//JavaGetRequest.findisbn(book);
		//return new String[]{book.getBookname(),book.getBookpic(),book.getAuthor()};
	    // ArrayList<String> as= new ArrayList<String>();
	     book.setBookname(jdbcTemplateObject.queryForObject(sql,new Object[] {book.getIsbn()}, String.class));
	     book.setLink(new String[] {jdbcTemplateObject.queryForObject(sql2,new Object[] {book.getIsbn(),"Amazon"}, String.class)});
	     book.setAuthor(jdbcTemplateObject.queryForObject(sql4,new Object[] {book.getIsbn()}, String.class));
	     List<String> ta = jdbcTemplateObject.queryForList(sql5,new Object[] {book.getIsbn()}, String.class);
	     List<Float> tb = jdbcTemplateObject.queryForList(sql6,new Object[] {book.getIsbn()}, Float.class);
	     String[] reviews = ta.toArray(new String[ta.size()]);
	     Float[] ratingreviews = tb.toArray(new Float[tb.size()]);
	     book.setReviews(reviews);
	     book.setRateofReviews(ratingreviews);
	     book.setRate(new Float[] {jdbcTemplateObject.queryForObject(sql7,new Object[] {book.getIsbn(),"Amazon"}, Float.class)});
	     book.setBookpic(jdbcTemplateObject.queryForObject(sql8,new Object[] {book.getIsbn()}, String.class));
	     addhistory(user,book,false);
	     return book;
	}

	@Override
	public ArrayList<Books> getfavorite(Users user) {
		String sql = "SELECT title FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? and favorite = true order by accessTime";
		String sql2 = "SELECT author FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? and favorite = true order by accessTime";
		String sql3 = "SELECT cover FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? and favorite = true order by accessTime";
		String sql4 = "SELECT isbn FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? and favorite = true order by accessTime";
		ArrayList<Books> rest = new ArrayList<Books>();
		List<String> tt = jdbcTemplateObject.queryForList(sql,new Object[] {user.getId()}, String.class);
		List<String> at = jdbcTemplateObject.queryForList(sql2,new Object[] {user.getId()}, String.class);
		List<String> ct = jdbcTemplateObject.queryForList(sql3,new Object[] {user.getId()}, String.class);
		List<Long> it = jdbcTemplateObject.queryForList(sql4,new Object[] {user.getId()}, Long.class);
		String[] titles = tt.toArray(new String[tt.size()]);
		String[] authors = at.toArray(new String[at.size()]);
		String[] covers = ct.toArray(new String[ct.size()]);
		Long[] isbns = it.toArray(new Long[it.size()]);
		for(int i=0;i<titles.length;i++) {
			Books temp = new Books();
			temp.setBookname(titles[i]);
			temp.setAuthor(authors[i]);
			temp.setBookpic(covers[i]);
			temp.setIsbn(isbns[i]);
			rest.add(temp);
			} 
		return rest;
	}

	@Override
	public ArrayList<Books> gethistory(Users user) throws MalformedURLException, ProtocolException, IOException {
		String sql = "SELECT title FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? order by accessTime";
		String sql2 = "SELECT author FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? order by accessTime";
		String sql3 = "SELECT cover FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? order by accessTime";
		String sql4 = "SELECT isbn FROM SEARCHISTORY Natural Join BOOK WHERE userID = ? order by accessTime";
		ArrayList<Books> rest = new ArrayList<Books>();
		List<String> tt = jdbcTemplateObject.queryForList(sql,new Object[] {user.getId()}, String.class);
		List<String> at = jdbcTemplateObject.queryForList(sql2,new Object[] {user.getId()}, String.class);
		List<String> ct = jdbcTemplateObject.queryForList(sql3,new Object[] {user.getId()}, String.class);
		List<Long> it = jdbcTemplateObject.queryForList(sql4,new Object[] {user.getId()}, Long.class);
		String[] titles = tt.toArray(new String[tt.size()]);
		String[] authors = at.toArray(new String[at.size()]);
		String[] covers = ct.toArray(new String[ct.size()]);
		Long[] isbns = it.toArray(new Long[it.size()]);
		for(int i=0;i<titles.length;i++) {
			Books temp = new Books();
			temp.setBookname(titles[i]);
			temp.setAuthor(authors[i]);
			temp.setBookpic(covers[i]);
			temp.setIsbn(isbns[i]);
			rest.add(temp);
			} 
		return rest;
	}

	@Override
	public String[] searchbybarcode(Books book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] addhistory(Users user, Books book,boolean isfav) {
		String sql1 = "SELECT count(*) FROM SEARCHISTORY WHERE isbn = ? and userID = ?";
		String sql2 = "SELECT count(*) FROM BOOK WHERE isbn = ?";
		int count = jdbcTemplateObject.queryForObject(sql1, new Object[] { book.getIsbn(),user.getId()}, Integer.class);
				if (count >= 1) {
					String SQL0 = "DELETE FROM SEARCHISTORY where isbn = ? and userID = ?";	
					jdbcTemplateObject.update( SQL0, new Object[]{book.getIsbn(),user.getId()} );	
			    	 }
		count = jdbcTemplateObject.queryForObject(sql2, new Object[] { book.getIsbn()}, Integer.class);
				String SQL = "INSERT IGNORE INTO SEARCHISTORY (userID,isbn,favorite) VALUES(?,?,?);";	
		jdbcTemplateObject.update( SQL, new Object[]{user.getId(),book.getIsbn(),isfav} );	
		return null; 
	}

	private int isBookExists(Books book) {
		Long isbn = book.getIsbn();
	        String sql = "SELECT count(*) FROM BOOK WHERE isbn = ?";
	        String sql1 = "SELECT count(*) FROM WEBSITE WHERE isbn = ?";
	        String sql2 = "SELECT count(*) FROM REVIEW WHERE isbn = ?";
	        int result = 0;
	         int count = jdbcTemplateObject.queryForObject(sql, new Object[] { isbn}, Integer.class);
	         int count1 = jdbcTemplateObject.queryForObject(sql1, new Object[] { isbn}, Integer.class);
              int count2 = jdbcTemplateObject.queryForObject(sql2, new Object[] { isbn}, Integer.class);
	        // int count2 = 3;
              count2 = 3;
	         if (count >= 1) {
	    	 if(count1 >=1) {
	    		 if(count2 >=1)
		           result = 1;
	    	     else
	    	   result = -2;}
	    	 else
	    		 result = -1;
	    	 }
	       return result;
	   }

	@Override
	public void addnewbook(ArrayList<String> as,Long isbn,int isexist) {
		// TODO Auto-generated method stub
		String title = as.get(0);
		String url = as.get(1);
		Float rating = Float.parseFloat(as.get(2)); 
		String author = as.get(3);
		String revtitle = "";
		String body = "";
		Float revrating = (float) 0.0;
		String summary = "";
		String SQL = "INSERT IGNORE INTO BOOK (isbn,title,author) VALUES(?,?,?);";	
		jdbcTemplateObject.update(SQL, new Object[]{isbn,title,author});
		String SQL2 = "INSERT IGNORE INTO WEBSITE (webname,URL,isbn,overallrating) VALUES(?,?,?,?);";	
		if(isexist >= -1)
		  jdbcTemplateObject.update(SQL2, new Object[]{"Amazon",url,isbn,rating});
		int i = 4;
		String SQL3 = "INSERT IGNORE INTO REVIEW (rating,summary,isbn) VALUES(?,?,?);";	
		try {
		while(as.get(i).equals("review")) {
			revtitle = as.get(i+1);
			body = as.get(i+2);
			summary = revtitle + ":  \n" + body;
			revrating = Float.parseFloat(as.get(i+3));
			jdbcTemplateObject.update(SQL3, new Object[]{revrating,summary,isbn});
			i = i + 4;}
		}catch(Exception ee) {}
		
	}

	@Override
	public void addfavorite(Users user,Books book) {
		// TODO Auto-generated method stub
		addhistory(user,book,true);
	}

	@Override
	public ArrayList<Books> searchbyname(String title,int page) {
		ArrayList<Books> ret = new ArrayList<Books>();
		ArrayList<String> as = FindReview.FindBooks(title, page);
		Books bk = new Books();
		if(as.size()<=1) {
			bk.setIsbn(0);
			bk.setBookname("notfound");
			ret.add(bk);
			return ret;
		}
		try {
		bk.setBookname("found");
		bk.setIsbn(Long.parseLong(as.get(0)));
		ret.add(bk);
		for(int i =0; i<10; i++) {
			Books tk = new Books();
			tk.setBookname(as.get(i*4+2));
			tk.setBookpic(as.get(i*4+3));
			tk.setIsbn(Long.parseLong(as.get(i*4+4)));
			ret.add(tk);
		}}
		catch(Exception ee)
		{
		Books rk = new Books();
		rk.setIsbn(0);
		if(ret.size()>=3) {
			}
		else
			ret.add(rk);
		}
		return ret;
	}

	@Override
	public void deletebook(Users user,Books book) {
		// TODO Auto-generated method stub
		String type = book.getFavorhist();
		if(type.equals("Favorite")) {
			addhistory(user,book,false);
		}
		else {
			String SQL0 = "DELETE FROM SEARCHISTORY WHERE isbn = ? and userID = ?";	
			jdbcTemplateObject.update( SQL0, new Object[]{book.getIsbn(),user.getId()} );	
		}
	}
	
	
}
