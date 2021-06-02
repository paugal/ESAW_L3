package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();}}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();}}

	public String addUser(String ...parameters) {
		String query = "INSERT INTO users (username, fullname, phoneNumber, location, mail, pwd) VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = null; int i = 1;
		
		try {
			statement = db.prepareStatement(query);
			
			for(String parameter: parameters) {
				statement.setString(i,parameter);i++;}
			
			statement.executeUpdate(); 
			statement.close();
			
		} catch (SQLIntegrityConstraintViolationException e) {	// SQL insertion error
			return e.getMessage();
		} catch (SQLException e) {								// SQL general error
			e.printStackTrace();}

		// No error
		return "";
	}
	
	/* Logic Functions */
	public boolean isComplete(User user) {
		return (hasValue(user.getUsername()) &&
				hasValue(user.getFullName()) &&
				hasValue(user.getLocation()) &&
				hasValue(user.getPhoneNumber()) &&
				hasValue(user.getMail()) &&
				hasValue(user.getPwd1()) &&
				hasValue(user.getPwd2()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	/*Check if all the fields are correct */
	public boolean checkErrors(User user) {
		boolean[] errors = user.getError();
		
		for(int i = 0; i < errors.length; i++) {
			if(errors[i])
				return true;
		}
		return false;		
	}
	
	public User getUser(String username) {
		
		String query = "SELECT username, fullname, phoneNumber, location, mail, pwd FROM users WHERE username = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,username);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setMail(rs.getString("mail"));
				user.setPwd1(rs.getString("pwd"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setLocation(rs.getString("location"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

}
