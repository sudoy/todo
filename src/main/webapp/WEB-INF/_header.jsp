<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Todoリスト</title>

	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<!--[if lt IE 9]>
		<script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<header>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="./index.html" class="navbar-brand">Todoリスト</a>
			</div>
		</div>
	</div>
</header>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<!-- 
			<div class="alert alert-dismissible alert-success">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h4><strong>完了しました！</strong></h4>
				<ul>
					<li>No.27 のTodoを更新しました。</li>
				</ul>
			</div>
			 -->
			<c:if test="${errors != null}">
			<div class="alert alert-dismissible alert-danger">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h4><strong>エラーが発生しました！</strong></h4>
				<ul>
				<c:forEach items="${errors}" var="error">
					<li>${error}</li>
				</c:forEach>
				</ul>
			</div>
			</c:if>
			