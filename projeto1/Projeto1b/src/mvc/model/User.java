package mvc.model;

public class User {
	private int id;
	private String username;
	private String senha;

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
	
	// setter senha
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	// getter senha
	public String getSenha() {
		return senha;
	}
}

