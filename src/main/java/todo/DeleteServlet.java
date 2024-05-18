package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.utils.DBUtils;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 削除処理
		
		request.setCharacterEncoding("UTF-8");

//		// バリデーションチェック
//		List<String> errors = validate(request);
//		
//		if (errors.size() > 0) {
//			// JSPへエラーメッセージのリストを渡す
//			request.setAttribute("errors", errors);
//			
//			// エラーがあったらフォワード
//			request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
//			return;
//		}

		// エラーがなかったらUPDATE処理

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			// データベースの接続を確立
			con = DBUtils.getConnection();
			sql = "DELETE FROM todos WHERE id = ?";
			// INSERT命令の準備
			ps = con.prepareStatement(sql);
			// INSERT命令にポストデータの内容をセット
			ps.setString(1, request.getParameter("id"));

//			// SQLの実行確認（デバッグ）
//			System.out.println(ps);

			// INSERT命令を実行
			ps.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps);
		}
		// 処理後はindex.htmlにリダイレクト
		response.sendRedirect("index.html");
	}
}
