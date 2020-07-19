package com.br.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import org.springframework.test.web.servlet.result.ModelResultMatchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.br.object.Books;
import com.br.object.Operation;




 
   
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
	@Test
	public void contextLoads() {
		
	}
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	 @Autowired  
   private MockHttpSession session;
    
    @Autowired  
    private MockHttpServletRequest request;
    


@Before 
public void setup() {
	this.mockMvc = MockMvcBuilders. webAppContextSetup(this.wac).build(); 
}

@Test  
public void testhistory_emptyhistory() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "emptyuser");
	sessionattr.put("userID", 47);
	mockMvc.perform(get("/history").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","No history yet"))
			.andExpect(status().isOk());
} 

@Test  
public void testfavorite_emptyfavorite() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "emptyuser");
	sessionattr.put("userID", 47);
	mockMvc.perform(get("/favorite").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","No favorite book yet"))
			.andExpect(status().isOk());
} 


@Test  
public void testfavorite_newfavorite() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	book.setIsbn(Long.parseLong("9780061962783"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	mockMvc.perform(post("/newfavorite").param("isbn", "9780061962783").sessionAttrs(sessionattr))
			.andExpect(redirectedUrl("/favorite"));
	mockMvc.perform(post("/favorite").sessionAttrs(sessionattr))
	.andExpect(model().attribute("lastfav",book))
	.andExpect(status().isOk());
	mockMvc.perform(post("/delbook").param("isbn", "9780061962783").param("favorhist", "Favorite").sessionAttrs(sessionattr))
	.andExpect(redirectedUrl("/favorite"));
	mockMvc.perform(get("/favorite").sessionAttrs(sessionattr))
	.andExpect(model().attribute("errormess","No favorite book yet"))
	.andExpect(status().isOk());
} 
	
@Test  
public void testsearchbyisbn_resultfound() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	book.setIsbn(Long.parseLong("9780061962783"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "9780061962783").sessionAttrs(sessionattr))
			.andExpect(model().attribute("res", book))
			.andExpect(status().isOk());
	mockMvc.perform(get("/history").sessionAttrs(sessionattr))
	.andExpect(model().attribute("lasthist",book))
	.andExpect(status().isOk());
} 

@Test  
public void testsearchbyisbn_resultfound_isbnwithdash() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	book.setIsbn(Long.parseLong("9780061962783"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "978-0061962783").sessionAttrs(sessionattr))
			.andExpect(model().attribute("res", book))
			.andExpect(status().isOk());
	mockMvc.perform(get("/history").sessionAttrs(sessionattr))
	.andExpect(model().attribute("lasthist",book))
	.andExpect(status().isOk());
} 
    
@Test  
public void testsearchbyisbn_shorterisbn() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	book.setIsbn(Long.parseLong("97800619627"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "97800619783").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","No result is found by this isbn"))
			.andExpect(status().isOk());
} 


@Test  
public void testsearchbyisbn_invalidisbn13() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	book.setIsbn(Long.parseLong("9780439062273"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "97800619783").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","No result is found by this isbn"))
			.andExpect(status().isOk());
} 

@Test  
public void testsearchbyisbn_invalidisbn() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	//book.setIsbn(Long.parseLong("gwgq93gtqqg3"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "ohoi999").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","No result is found by this isbn"))
			.andExpect(status().isOk());
} 

@Test  
public void testsearchbyisbn_emptyisbn() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	Books book = new Books();
	//book.setIsbn(Long.parseLong("gwgq93gtqqg3"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","Did you type an invalid isbn?"))
			.andExpect(status().isOk());
} 

@Test  
public void testsearchbyname_notfound() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search3").param("bookname", "Thisistolongforasearchbartowork").sessionAttrs(sessionattr))
			.andExpect(model().attribute("errormess","No result is found by this title"))
			.andExpect(status().isOk());
} 

@Test  
public void testsearchbyname_found() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search3").param("bookname", "Harry Potter").param("total", "100").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",1))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyname_nextpage() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search4").param("title", "Harry Potter").param("total", "100").param("spageno", "10").param("type", "next").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",11))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyname_nextpage_outofrange() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search4").param("title", "Harry Potter").param("total", "100").param("spageno", "100").param("type", "next").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",100))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyname_previouspage() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search4").param("title", "Harry Potter").param("total", "100").param("spageno", "10").param("type", "previous").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",9))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyname_previouspage_outofrange() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search4").param("title", "Harry Potter").param("total", "100").param("spageno", "1").param("type", "previous").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",1))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyname_jumppage() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search4").param("title", "Harry Potter").param("total", "100").param("spageno", "30").param("type", "jump").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",30))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyname_jumpexception() throws Exception {  
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "junit");
	sessionattr.put("userID", 13);
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search4").param("title", "Harry Potter").param("total", "100").param("spageno", "30").param("type", "jump").sessionAttrs(sessionattr))
			.andExpect(model().attribute("page",30))
			.andExpect(model().attribute("searchtitle","Harry Potter"))
			.andExpect(status().isOk());
}

@Test  
public void testsearchbyisbn_notlogin() throws Exception {  
	Books book = new Books();
	book.setIsbn(Long.parseLong("9780439062273"));
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search").param("sisbn", "97800619783"))
			.andExpect(redirectedUrl("/home"));
} 

@Test  
public void testsearchbypic_notlogin() throws Exception {  
	Books book = new Books();
	book.setBookname("Inside Out And Back Again (Turtleback School & Library Binding Edition)");
	book.setAuthor("Thanhha Lai");
	request.getSession().setAttribute("username", "junit");
	request.getSession().setAttribute("userID", 13);
	mockMvc.perform(post("/search2").param("sisbn", "97800619783"))
			.andExpect(redirectedUrl("/home"));
} 

@SuppressWarnings("deprecation")
@Test  
public void testsearchbypic_isbnfound() throws Exception {  
HashMap<String, Object> sessionattr = new HashMap<String, Object>();
sessionattr.put("username", "junit");
sessionattr.put("userID", 13);
File file = new File("bar4.jpg");
Long isbn = Long.parseLong("9787107263538");
InputStream stream =  new FileInputStream(file);
MockMultipartFile multipartFileToSend = new MockMultipartFile("barpic",
        file.getName(), "image/jpg", IOUtils.toByteArray(stream));
mockMvc.perform(MockMvcRequestBuilders.fileUpload("/search2")                
        .file(multipartFileToSend)
        .sessionAttrs(sessionattr)
        )              
        .andExpect(model().attribute("isbn",isbn));
/*
mockMvc.perform(post("/search2").param("barpic", "97800619783").sessionAttrs(sessionattr).contentType(multipartFileToSend))
        .andExpect(model().attribute("errormess","No result is found by this isbn"))
		.andExpect(status().isOk());*/
}

@SuppressWarnings("deprecation")
@Test  
public void testsearchbypic_isbnnotfound() throws Exception {  
HashMap<String, Object> sessionattr = new HashMap<String, Object>();
sessionattr.put("username", "junit");
sessionattr.put("userID", 13);
File file = new File("meme.jpg");
InputStream stream =  new FileInputStream(file);
MockMultipartFile multipartFileToSend = new MockMultipartFile("barpic",
        file.getName(), "image/jpg", IOUtils.toByteArray(stream));
mockMvc.perform(MockMvcRequestBuilders.fileUpload("/search2")                
        .file(multipartFileToSend)
        .sessionAttrs(sessionattr)
        )              
.andExpect(model().attribute("errormess","No isbn is found in this pic"));
/*
mockMvc.perform(post("/search2").param("barpic", "97800619783").sessionAttrs(sessionattr).contentType(multipartFileToSend))
        .andExpect(model().attribute("errormess","No result is found by this isbn"))
		.andExpect(status().isOk());*/
}

@SuppressWarnings("deprecation")
@Test  
public void testsearchbypic_emptyfile() throws Exception {  
HashMap<String, Object> sessionattr = new HashMap<String, Object>();
sessionattr.put("username", "junit");
sessionattr.put("userID", 13);
File file = new File("meme.jpg");
InputStream stream =  new FileInputStream(file);
MockMultipartFile multipartFileToSend = new MockMultipartFile("barpic",
        file.getName(), "image/jpg", IOUtils.toByteArray(stream));
mockMvc.perform(MockMvcRequestBuilders.fileUpload("/search2")                
        .sessionAttrs(sessionattr)
        )              
.andExpect(model().attribute("errormess","No isbn is found in this pic"));
/*
mockMvc.perform(post("/search2").param("barpic", "97800619783").sessionAttrs(sessionattr).contentType(multipartFileToSend))
        .andExpect(model().attribute("errormess","No result is found by this isbn"))
		.andExpect(status().isOk());*/
}

@Test  
public void testlogin_wrongaccount() throws Exception {  
	
	mockMvc.perform(post("/login").param("username", "qgg").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","No such account exist"))
			.andExpect(status().isOk());
} 

@Test  
public void testlogin_emptyaccount() throws Exception {  
	
	mockMvc.perform(post("/login").param("username", "").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","No such account exist"))
			.andExpect(status().isOk());
} 

@Test  
public void testlogin_wrongpassword() throws Exception {  
	mockMvc.perform(post("/login").param("username", "junit").param("password", "qgqgq"))
	.andExpect(model().attribute("errormess","Incorrect password"))
	.andExpect(status().isOk());
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testlogin_emptypassword() throws Exception {  
	mockMvc.perform(post("/login").param("username", "junit").param("password", "qgqgq"))
	.andExpect(model().attribute("errormess","Incorrect password"))
	.andExpect(status().isOk());
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testlogin_loginsuccess() throws Exception {  
	mockMvc.perform(post("/login").param("username", "junit").param("password", "junit"))
	.andExpect(request().sessionAttribute("username", "junit"))
	.andExpect(request().sessionAttribute("userID", "13"))
	.andExpect(request().sessionAttribute("admin", false))
	.andExpect(redirectedUrl("/home"));
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testlogin_wrongemail() throws Exception {  
	
	mockMvc.perform(post("/login2").param("email", "qgg").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","No such account exist"))
			.andExpect(status().isOk());
} 

@Test  
public void testlogin_emptyemail() throws Exception {  
	
	mockMvc.perform(post("/login2").param("email", "").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","No such account exist"))
			.andExpect(status().isOk());
} 

@Test  
public void testlogin_wrongpassword2() throws Exception {  
	mockMvc.perform(post("/login2").param("email", "junit@gmail.com").param("password", "qgqgq"))
	.andExpect(model().attribute("errormess","Incorrect password"))
	.andExpect(status().isOk());
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testlogin_emptypassword2() throws Exception {  
	mockMvc.perform(post("/login2").param("email", "junit@gmail.com").param("password", ""))
	.andExpect(model().attribute("errormess","Incorrect password"))
	.andExpect(status().isOk());
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testlogin_loginsuccess2() throws Exception {  
	mockMvc.perform(post("/login2").param("email", "junit@gmail.com").param("password", "junit"))
	.andExpect(request().sessionAttribute("username", "junit"))
	.andExpect(request().sessionAttribute("userID", "13"))
	.andExpect(request().sessionAttribute("admin", false))
	.andExpect(redirectedUrl("/home"));
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testadminlogin_wrongaccount() throws Exception {  
	
	mockMvc.perform(post("/sup").param("username", "qgg").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","This is not an admin account"))
			.andExpect(status().isOk());
} 

@Test  
public void testadminlogin_notadminaccount() throws Exception {  
	
	mockMvc.perform(post("/sup").param("username", "junit").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","This is not an admin account"))
			.andExpect(status().isOk());
} 

@Test  
public void testadminlogin_empty() throws Exception {  
	
	mockMvc.perform(post("/sup").param("username", "").param("password", "qgqgq"))
			.andExpect(model().attribute("errormess","This is not an admin account"))
			.andExpect(status().isOk());
} 

@Test  
public void testadminlogin_wrongpassword() throws Exception {  
	mockMvc.perform(post("/sup").param("username", "admin").param("password", "qgqgq"))
	.andExpect(model().attribute("errormess","Incorrect password"))
	.andExpect(status().isOk());
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test  
public void testadminlogin_loginsuccess() throws Exception {  
	mockMvc.perform(post("/sup").param("username", "admin").param("password", "admin"))
	.andExpect(request().sessionAttribute("username", "admin"))
	.andExpect(request().sessionAttribute("userID", "1"))
	.andExpect(request().sessionAttribute("admin", true))
	.andExpect(redirectedUrl("/home"));
	//.andExpect(status().isOk());
		//	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		//	.andReturn();
} 

@Test
public void testregister_existaccount() throws Exception{
	mockMvc.perform(post("/reg").param("username", "1").param("email", "1").param("password", "1"))
	.andExpect(model().attribute("errormess","Username in used, please use another user name"))
	.andExpect(status().isOk());
}

@Test
public void testregister_existemail() throws Exception{
	mockMvc.perform(post("/reg").param("username", "11111111111").param("email", "1@gmail.com").param("password", "1"))
	.andExpect(model().attribute("errormess","There's already an account with this email!"))
	.andExpect(status().isOk());
}

@Autowired
JdbcTemplate jdbcTemplateObject;

@Test
public void testregister_reganddel() throws Exception{
	mockMvc.perform(post("/reg").param("username", "junitaddanddel").param("email", "junitaddanddel").param("password", "junitaddanddel"))
	.andExpect(model().attribute("username","junitaddanddel"))
	.andExpect(status().isOk());
	String sql2 = "SELECT userID FROM USER WHERE username = ?";
	int tid = jdbcTemplateObject.queryForObject(sql2,new Object[] {"junitaddanddel"}, Integer.class);
	HashMap<String, Object> sessionattr = new HashMap<String, Object>();
	sessionattr.put("username", "admin");
	sessionattr.put("userID", 1);
	sessionattr.put("admin", true);
	mockMvc.perform(post("/del").param("id", tid+"").param("username","junitdaaanddel").param("email", "junitaddanddel").sessionAttrs(sessionattr))
			.andExpect(redirectedUrl("/del"));
	mockMvc.perform(post("/login").param("username", "junitdaaanddel").param("password", "qgqgq"))
	.andExpect(model().attribute("errormess","No such account exist"))
	.andExpect(status().isOk());
    Operation op = new Operation();
    op.setAdminid(1);
    op.setUsername("junitdaaanddel");
    op.setType("delete");
    mockMvc.perform(get("/adminhistory").sessionAttrs(sessionattr))
    .andExpect(model().attribute("lastop",op))
	.andExpect(status().isOk());
}

}
