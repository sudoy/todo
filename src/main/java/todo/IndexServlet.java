package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.beans.Todo;
import todo.utils.DBUtils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
		    // SQL
		    sql = "SELECT id, title, importance, limit_date FROM todos ORDER BY id";
		    // SELECT命令の準備
		    ps = con.prepareStatement(sql);
		    // SELECT命令を実行
		    rs = ps.executeQuery();
		    
		    List<Todo> todos = new ArrayList<>();
		    
		    while(rs.next()) {
		    	// 取得したデータを1行ずつ処理していく
		    	Todo todo = new Todo();
		    	todo.setId(rs.getInt("id"));
		    	todo.setTitle(rs.getString("title"));
		    	todo.setImportance(rs.getInt("importance"));
		    	todo.setLimitDate(rs.getDate("limit_date"));
		    	
		    	// Listに値を追加
		    	todos.add(todo);
		    }
		    
		    // JSPへ値を渡す
		    request.setAttribute("todos", todos);
		    
		    // forward
		    request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		    
		} catch(Exception e) {
		    throw new ServletException(e);// ブラウザ上にエラーがでる
		    
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
