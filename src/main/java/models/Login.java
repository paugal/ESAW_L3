package models;

public class Login {

	private String username = "";
	private String password = "";
	private String errorMessage = "";
	private int[] error = {0,0};
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int[] getError() {
		return error;
	}
	
	
	public boolean isComplete() {
	    return( hasValue(getUsername()) &&
	    		hasValue(getPassword()));
	}
	
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	
	
}