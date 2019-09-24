package mvc.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;

	public DAO() {
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://remotemysql.com:3306/Y6Ljchy0Hv","Y6Ljchy0Hv","kT2S6zd1jI");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("connection is: " + (connection != null));
	}

	// USER -----------------------------------------------------
	
	
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
				user.setSenha(rs.getString("senha"));
				
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
			stmt1.setString(2, user.getSenha());
			
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
	
	// get userID
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
	
	
	// TAREFA ----------------------------------------------
	
	
	// adiciona descricao a tarefa
	public void adicionaDescricao(Tarefa tarefa) {
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO Tarefa (descricao) values(?)");
			
			stmt.setString(1, tarefa.getDescricao());
			stmt.execute();
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}    
	}
	
	// adiciona tarefa
	public void adiciona(Tarefa tarefa) {
		try {
			String sql = "INSERT INTO Tarefa (descricao, dataFinalizacao) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setDate(2, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// lista tarefas
	public List<Tarefa> getLista() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tarefa");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				Calendar data = Calendar.getInstance();
				Date dataFinalizacao = rs.getDate("dataFinalizacao");

				if (dataFinalizacao != null) {
					data.setTime(dataFinalizacao);
					tarefa.setDataFinalizacao(data);
				}
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return tarefas;
	}

	// remove tarefa
	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM Tarefas WHERE id=?");
			stmt.setInt(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// busca tarefas por id
	public Tarefa buscaPorId(int id) {
		Tarefa tarefa = new Tarefa();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tarefa WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tarefa.setId(rs.getInt("id"));
				tarefa.setDescricao(rs.getString("descricao"));

				Calendar data = Calendar.getInstance();

				Date dataFinalizacao = rs.getDate("dataFinalizacao");
				if (dataFinalizacao != null) {
					data.setTime(dataFinalizacao);
					tarefa.setDataFinalizacao(data);
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return tarefa;
	}

	// editar tarefa
	public void altera(Tarefa tarefa) {
		try {
			String sql = "UPDATE Tarefa SET descricao=?, dataFinalizacao=? WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());

			if (tarefa.getDataFinalizacao() != null) {
				stmt.setDate(2, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			} else {
				stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			}

			stmt.setInt(3, tarefa.getId());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
