package model;

public class User {
	
	private int userId;
	private String email;
	private String username;
	private String password;
	private String type;

	public User(){
		userId = -1;
		email = "";
		username = "";
		password = "";
	}
	
	public User(String eM, String uN, String pW, String ty){
		userId = -1;
		email = eM;
		username = uN;
		password = pW;
		type = ty;
	}
	
	public User(int uI, String eM, String uN, String pW, String ty){
		userId = uI;
		email = eM;
		username = uN;
		password = pW;
		type = ty;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String ty) {
		this.type = ty;
	}
	
	
}
