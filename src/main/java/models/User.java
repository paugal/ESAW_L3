package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username = "";
	private String mail = "";
	private String pwd1 = "";
	private String pwd2 = "";
	private String fullName = "";
	private String phoneNumber = "";
	private String location = "";
	
	
	private boolean[] error  = {false,false,false,false,false,false,false};
	private String[] errorMessages  = {
		"Incorrect username format {length(2,255) no-spaces}",
		"Incorrect mail format {pattern: ___@___}",
		"Incorrect password format {pattern: lowerHigherCase length(>7) Numbers}",
		"Incorrect repeated password {not equal to original password}",
		"Incorrect full name format {length(3,255) +1 words}",
		"Incorrect phone number format {pattern: 123456789}",
		"Incorrect location {must be in the list}",
	};
	
	public User() {}
	
	/* Getters */
	public String getUsername() 		{ return this.username; }
	public String getMail() 			{ return this.mail; }
	public String getPwd1() 			{ return this.pwd1; }
	public String getPwd2() 			{ return this.pwd2; }
	public String getFullName() 		{ return this.fullName; }
	public String getPhoneNumber() 		{ return this.phoneNumber; }
	public String getLocation() 		{ return this.location; }
	public boolean[] getError() 		{ return this.error; }
	public String[] getErrorMessages()  { return this.errorMessages; }
	
	
	
	/*Setters*/
	public void setUsername(String user){
		if (user.length() > 2 && user.length() < 256 && !user.contains(" "))
			this.username = user;
		else
			error[0] = true;
		
		System.out.println(user);
	}
	
	public void setMail(String mail){
		// pattern: ___@___
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		Matcher matcher = pattern.matcher(mail);
		
		if (matcher.matches())
			this.mail = mail;
		else 
			error[1] = true;
		
		System.out.println(mail);

	}
	
	public void setPwd1(String pwd1) {
		// pattern: lowerHigherCase length(>7) Numbers
		Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
		Matcher matcher = pattern.matcher(pwd1);
		
		this.pwd1 = pwd1;
		
		if (!matcher.matches())
			error[2] = true;
		
		
		
		System.out.println(pwd1);
	}
	
	public void setPwd2(String pwd2) {
		// equal to password 1
		this.pwd2 = pwd2;
		
		System.out.println(pwd2);
	}

	public void setFullName(String fullName) {
		// length(3,255) 2+words
		if (fullName.length() > 3 && fullName.length() < 256 && fullName.split(" ").length > 1)
			this.fullName = fullName;
		else 
			error[4] = true;
		
		System.out.println(fullName);
	}

	public void setPhoneNumber(String phoneNumber) {
		// pattern: 123456789
		Pattern pattern = Pattern.compile("[0-9]{9}");
		Matcher matcher = pattern.matcher(phoneNumber);
		
		if (matcher.matches())
			this.phoneNumber = phoneNumber;
		else 
			error[5] = true;
		
		System.out.println(phoneNumber);
	}

	public void setLocation(String location) {
		this.location = location;
		
		System.out.println(location);
	}
	
	
}
