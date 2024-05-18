package todo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class DBUtils {
	private static final String DSN = "jdbc:mysql://us-cluster-east-01.k8s.cleardb.net/heroku_5d2133367028194?reconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private static final String USER = "b3a786857413f1";
	private static final String PASSWORD = "9fb86b1d";
	
	public static Connection getConnection() throws NamingException, SQLException {
		//データベースの接続を確立
//	    Context initContext = new InitialContext();
//	    Context envContext  = (Context)initContext.lookup("java:/comp/env");
//	    DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
//	    return ds.getConnection();
	    
	    Connection conn = null;
		
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.jdbc.Driver");
			
			// データベースへ接続
			conn = DriverManager.getConnection(DSN, USER, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
	        if(rs  != null) { rs.close(); }
	        if(ps  != null) { ps.close(); }
	        if(con != null) { con.close(); }
	    } catch (Exception e) { }
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		try {
	        if(ps  != null) { ps.close(); }
	        if(con != null) { con.close(); }
	    } catch (Exception e) { }
	}
}
