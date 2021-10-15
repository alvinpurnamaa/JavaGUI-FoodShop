import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public Statement stat;
	public ResultSet resultset;
	public ResultSetMetaData resultsetmd;
	
	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:8080","root","");
			
			Connection con = null;
			Statement stat = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public ResultSet execQuery(String query) {
		try {
			resultset = stat.executeQuery(query);
			resultsetmd = (ResultSetMetaData) ((Connection) resultsetmd).getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultset;
		
		
	}
	
	public static void main(String[] args) {
		new Connect ();

	}

}
