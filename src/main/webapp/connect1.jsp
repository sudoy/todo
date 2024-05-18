<%@ page import="java.sql.*,javax.naming.*,javax.sql.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<title>データベース接続</title>
</head>
<body>
	<%
	Context initContext = new InitialContext();
	Context envContext = (Context) initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
	Connection con = ds.getConnection();
	con.close();
	%>データベースの接続に成功
</body>
</html>
