package com.br.object;

public class Operation {
private int adminid;
private String adminname;
private int id;
private String username;
private String type;

public int getAdminid() {
	return adminid; 
}
public void setAdminid(int adminid) {
	this.adminid = adminid;
}

public String getAdminname() {
	return adminname; 
}
public void setAdminname(String adminname) {
	this.adminname = adminname;
}

public int getId() {
	return id; 
}
public void setId(int id) {
	this.id = id;
}

public String getUsername() {
	return username; 
}
public void setUsername(String username) {
	this.username = username;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public boolean equals(Object o) { 
	  
    // If the object is compared with itself then return true   
    if (o == this) { 
        return true; 
    } 

    /* Check if o is an instance of Complex or not 
      "null instanceof [type]" also returns false */
    if (!(o instanceof Operation)) { 
        return false; 
    } 
      
    // typecast o to Complex so that we can compare data members  
    Operation c = (Operation) o; 
      
    // Compare the data members and return accordingly  
    return c.getAdminid()==this.getAdminid()
            && c.getUsername().equals(this.getUsername())
            && c.getType().equals(this.getType());
} 
}
