package mvc.model;

public class User {
	private int id;
	private String username;
	private String password;

	//	getter id
	public int getId() {
		return id;
	}
	
	// setter id
	public void setId(int id) {
		this.id = id;
	}
	
	//	setter username
	public void setUsername(String username) {
		this.username = username;
	}
	
	// getter username 
	public String getUsername() {
		return username;
	}
	
	// setter password
	public void setPassword(String password) {
		this.password = password;
	}
	
	// getter password
	public String getPassword() {
		return password;
	}
}
