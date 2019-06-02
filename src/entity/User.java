package entity;

import java.io.Serializable;

/**
 * User entity.
 * 
 * @author JGroup
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -1533391154801525066L;
	
	private String username;
	private String password;
	
	/**
	 * Constructor with username.
	 * 
	 * @param username Username.
	 */
	public User(String username) {
		super();
		this.username = username;
	}

	/**
	 * Constructor with username and password.
	 * 
	 * @param username Username.
	 * @param password Password.
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Get username.
	 * 
	 * @return Username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set the username.
	 * 
	 * @param username Username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get password.
	 * 
	 * @return Password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set the password.
	 * 
	 * @param password Password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Override the toString method.
	 * No password displayed.
	 */
	@Override
	public String toString() {
		return "User {username: " + username + "}";
	}
	
}
