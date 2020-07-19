package com.br.object;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

public class Books { 
private long isbn;
private String sisbn;
private String bookpic;
private String bookname;
private Float rate[];   //rate on different platforms                          
           //1st element is Amazon, 2nd is goodReads, 3rd is others
private int[] numofgoodreviews;
private int[] numofmidreviews;
private int[] numofbadreviews;
private String[] comments;   //base on the number of reviews in three platforms
                             //pick up 20 comments
private int[] price;
private String author;
private String[] link;
private String[] webname;
private String[] reviews;
private Float[] rateofreviews;
private File barcode;
private MultipartFile barpic;
private boolean delfav;
private String favorhist;



@Id											
@GeneratedValue	
public long getIsbn() {
	return isbn;
}
public void setIsbn(long isbn) {
	this.isbn = isbn;
	}

public String getSisbn() {
	return sisbn;
}

public void setSisbn(String sisbn) {
	this.sisbn = sisbn;
			//Long.parseLong(isbn.replaceAll("[^0-9]", ""));
}


public String getBookpic() {
	return bookpic;
}
public void setBookpic(String bookpic) {
	this.bookpic = bookpic;
}

public String getBookname() {
	return bookname;
}
public void setBookname(String bookname) {
	this.bookname = bookname;
}

public Float[] getRate() {
	return rate;
}
public void setRate(Float[] rate) {
	this.rate = rate;
}

public int[] getNumofgoodreviews() {
	return numofgoodreviews;
}
public void setNumofgoodreviews(int[] numofgoodreviews) {
	this.numofgoodreviews = numofgoodreviews;
}
public int[] getNumofmidreviews() {
	return numofmidreviews;
}
public void setNumofmidreviews(int[] numofmidreviews) {
	this.numofmidreviews = numofmidreviews;
}
public int[] getNumofbadreviews() {
	return numofbadreviews;
}
public void setNumofbadreviews(int[] numofbadreviews) {
	this.numofbadreviews = numofbadreviews;
}

public String[] getComments() {
	return comments;
}
public void setComments(String[] comments) {
	this.comments = comments;
}

public int[] getPrice() {
	return price;
}
public void setPrice(int[] price) {
	this.price = price;
}

public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}

public String[] getLink() {
	return link;
}
public void setLink(String[] link) {
	this.link = link;
}

public String[] getWebname() {
	return webname;
}
public void setWebname(String[] webname) {
	this.webname = webname;
}

public String[] getReviews() {
	return reviews;
}
public void setReviews(String[] reviews) {
	this.reviews = reviews;
}

public Float[] getRateofReviews() {
	return rateofreviews;
}
public void setRateofReviews(Float[]  rateofreviews) {
	this.rateofreviews = rateofreviews; 
}
public File getBarcode() {
	return barcode;
}
public void setBarcode(File barcode) {
	this.barcode = barcode;
}

public MultipartFile getBarpic() {
	return barpic;
}
public void setBarpic(MultipartFile barpic) throws IllegalStateException, IOException {
	this.barpic = barpic;
}

public boolean getDelfav() {
	return delfav;
}
public void setDelfav(boolean delfav) {
	this.delfav = delfav;
}

public String getFavorhist() {
	return favorhist;
}

public void setFavorhist(String favorhist) {
	this.favorhist = favorhist;
}

public boolean equals(Object o) { 
	  
    // If the object is compared with itself then return true   
    if (o == this) { 
        return true; 
    } 

    /* Check if o is an instance of Complex or not 
      "null instanceof [type]" also returns false */
    if (!(o instanceof Books)) { 
        return false; 
    } 
      
    // typecast o to Complex so that we can compare data members  
    Books c = (Books) o; 
      
    // Compare the data members and return accordingly  
    return c.getAuthor().equals(this.getAuthor())
            && c.getIsbn()== this.getIsbn()
            && c.getBookname().equals(this.getBookname());
} 
} 


