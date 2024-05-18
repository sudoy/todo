package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.beans.Todo;
import todo.utils.DBUtils;

@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			// データベースの接続を確立
			con = DBUtils.getConnection();
			// SQL
			sql = "SELECT id, title, detail, importance, limit_date FROM todos WHERE id = ?";
			// SELECT命令の準備
			ps = con.prepareStatement(sql);

			// プレースホルダーに値をセット
			ps.setString(1, request.getParameter("id"));

//		    // デバッグ
//		    System.out.println(ps);

			// SELECT命令を実行
			rs = ps.executeQuery();

			rs.next();
			
			// 取得したデータを1行ずつ処理していく
			Todo todo = new Todo();
			todo.setId(rs.getInt("id"));
			todo.setDetail(rs.getString("detail"));
			todo.setTitle(rs.getString("title"));
			todo.setImportance(rs.getInt("importance"));
			todo.setLimitDate(rs.getDate("limit_date"));

			// JSPへ値を渡す
			request.setAttribute("todo", todo);

			// forward
			request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);// ブラウザ上にエラーがでる

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// バリデーションチェック
		List<String> errors = validate(request);
		
		if (errors.size() > 0) {
			// JSPへエラーメッセージのリストを渡す
			request.setAttribute("errors", errors);
			
			// エラーがあったらフォワード
			request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
			return;
		}

		// エラーがなかったらUPDATE処理

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			// データベースの接続を確立
			con = DBUtils.getConnection();
			// sql = "INSERT INTO todos (title, detail, importance, limit_date) VALUES (?, ?, ?, ?)";
			sql = "UPDATE todos SET title = ?, detail = ?, importance = ?, limit_date = ? WHERE id = ?";
			// INSERT命令の準備
			ps = con.prepareStatement(sql);
			// INSERT命令にポストデータの内容をセット
			ps.setString(1, request.getParameter("title"));
			ps.setString(2, request.getParameter("detail"));
			ps.setString(3, request.getParameter("importance"));
			
			// 期限が未入力の場合はnullをINSERTする
			String limitDate = request.getParameter("limit_date");
			if(limitDate.equals("")) {
				ps.setString(4, null);
			} else {
				ps.setString(4, limitDate);
			}
			
			ps.setString(5, request.getParameter("id"));

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
	
	private List<String> validate(HttpServletRequest request) {
		List<String> errors = new ArrayList<>();
		
		// 題名の必須チェック
		String title = request.getParameter("title");
		if (title.equals("")) {
			errors.add("題名は必須入力です！");
		}

		// 題名の100文字チェック
		if (title.length() > 100) {
			errors.add("題名は100文字以内にしてください！");
		}

		// 日付の形式チェック（YYYY/MM/DD）
		String limitDate = request.getParameter("limit_date");
		// 期限が空の場合に形式チェックでエラーになってしまうため
		if(!limitDate.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			sdf.setLenient(false);
			try {
				sdf.parse(limitDate);
			} catch (Exception e) {
//				e.printStackTrace();
				// 例外が発生したら形式エラーとみなす
				errors.add("期限は「YYYY/MM/DD」形式で入力してください！");
			}
		}


		// 重要度が1から3の値チェック
		String importance = request.getParameter("importance");
		if(importance.equals("1") || importance.equals("2") || importance.equals("3")) {
			// 正しい値
		} else {
			// 不正な値
			errors.add("重要度は１～３を選択してください！");
		}
//		こんな書き方でもOK
//		if(!importance.equals("1") && !importance.equals("2") && !importance.equals("3")) {
//			return true;
//		}

		return errors;
	}
}
