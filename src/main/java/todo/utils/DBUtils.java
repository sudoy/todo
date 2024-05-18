package todo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {
	public static Connection getConnection() throws NamingException, SQLException {
		//データベースの接続を確立
	    Context initContext = new InitialContext();
	    Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
	    return ds.getConnection();
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
