<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="todo.utils.HTMLUtils" %>

<jsp:include page="_header.jsp" />

<table class="table table-striped table-hover ">
	<tr><th>#</th><th>題名</th><th>重要度</th><th>期限</th></tr>

<c:forEach items="${todos}" var="todo">
    <tr>
    	<td>${todo.id}</td>
    	<td><a href="update.html?id=${todo.id}">${todo.title}</a></td>
    	<td>${HTMLUtils.formatImportance(todo.importance)}</td>
    	<td>${HTMLUtils.formatLimitDate(todo.limitDate)}</td>
    </tr>
</c:forEach>

</table>

<p>
	<a href="entry.html" class="btn btn-primary">追 加</a>
</p>

<jsp:include page="_footer.jsp" />