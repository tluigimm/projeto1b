package mvc.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	
	public DAO() {
		try {
			String  url = "jdbc:mysql://remotemysql.com:3306/wsjQIHnIAM";
			String user = "wsjQIHnIAM";
			String pswd = "6vywDqNWtE";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,pswd);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	// USERS ------------------------------------------------------------------
	
	// list users
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		try {
			PreparedStatement stmt = connection.
					prepareStatement("SELECT * FROM users");
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				User user = new User();
				user.setId(Integer.parseInt(rs.getString("id")));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				
				users.add(user);
				
			}
			
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	// add user
	public void addUser(User user) {
		String sql1 = "INSERT INTO users(username, password) values(?, ?)";
		String sql2 = "SELECT id FROM users WHERE username=?";
		
		try {
			// add user and password
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			stmt1.setString(1, user.getUsername());
			stmt1.setString(2, user.getPassword());
			
			stmt1.execute();
			stmt1.close();
			
			// add id
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setString(1, user.getUsername());
			ResultSet rs = stmt2.executeQuery();
			
			while(rs.next()) {
				user.setId(Integer.parseInt(rs.getString("id")));
			}
			rs.close();
			stmt2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// get username
	public String getUsername(int userId) {
		String username = new String();
		try {
			String sql = "SELECT username FROM users WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				username = rs.getString("username");
			}
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}
	
	// get username
	public String getId(User user) {
		String id = null;
		try {
			String sql = "SELECT id FROM users WHERE username=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				id = rs.getString("id");
			}
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	// NOTES ------------------------------------------------------------------
	
	// list assignments from user
	public List<Assignment> getUserAssignments(int userId) {
		List<Assignment> asgs = new ArrayList<Assignment>();
		PreparedStatement stmt1 = null;
		
		try {
			
			System.out.println("entrou onde devia");
			stmt1 = connection.prepareStatement("SELECT * FROM notes WHERE user_id=?");
			stmt1.setInt(1, userId);
			
			ResultSet rs1 = stmt1.executeQuery();
			
			while (rs1.next()) {
				Assignment asg = new Assignment();
				asg.setId(rs1.getInt("id"));
				asg.setSub(rs1.getString("subject"));
				asg.setNote(rs1.getString("note"));
				asg.setDate(rs1.getString("date"));
				
				asgs.add(asg);
			}
			
			rs1.close();
			stmt1.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return asgs;
	}
	
	// add assignment
	public void createAssignment(Assignment asg) {
		String sql1 = "INSERT INTO notes" + 
					"(user_id, subject, note, date) values(?, ?, ?, ?)";
		String sql2 = "SELECT id FROM notes WHERE note=?";
		try {
			
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			stmt1.setInt(1, asg.getUserId());
			stmt1.setString(2, asg.getSub());
			stmt1.setString(3, asg.getNote());
			stmt1.setString(4, asg.getDate());
			
			stmt1.execute();
			stmt1.close();
			
			// add id
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt2.setString(1, asg.getNote());
			ResultSet rs = stmt2.executeQuery();
			
			while(rs.next()) {
				asg.setId(Integer.parseInt(rs.getString("id")));
				System.out.println("id: "        + asg.getId());
				System.out.println("subject: "   + asg.getSub());
				System.out.println("note: " 	 + asg.getNote());
				System.out.println("date: " 	 + asg.getDate());
			}
			rs.close();
			stmt2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// remove assignment
	public void removeAssignment(int asgId) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM notes WHERE id=?");
			System.out.println("asgId: " + asgId);
			stmt.setInt(1, asgId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// edit assignment
	public void editAssginment(Assignment asg) {
		try {
			String sql = "UPDATE notes SET note=?, date=? WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, asg.getNote());
			stmt.setString(2, asg.getDate());
			stmt.setInt(3, asg.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	// close connection 
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
