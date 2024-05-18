<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="todo.utils.HTMLUtils" %>

<jsp:include page="_header.jsp" />

<form class="form-horizontal" action="update.html" method="post">
	<input type="hidden" name="id" value="${param.id != null ? param.id : todo.id}">

	<fieldset>
		<legend>更新フォーム</legend>
		<div class="form-group">
			<label for="title" class="col-md-2 control-label">題名</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="title" placeholder="題名" name="title" value="${param.title != null ? param.title : todo.title}">
			</div>
		</div>
		<div class="form-group">
			<label for="detail" class="col-md-2 control-label">詳細</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="3" id="detail" name="detail">${param.detail != nul ? param.detail : todo.detail}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">重要度</label>
			<div class="col-md-10">
				<div class="radio">
					<label>
						<input type="radio" name="importance" value="3" ${(param.importance != null ? param.importance : todo.importance) == "3" ? "checked" : ""}>★★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" value="2" ${(param.importance != null ? param.importance : todo.importance) == "2" ? "checked" : ""}>★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" value="1" ${(param.importance != null ? param.importance : todo.importance) == "1" ? "checked" : ""}>★
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="limit" class="col-md-2 control-label">期限</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="limit" placeholder="期限" name="limit_date" 
					value="${param.limit_date != null ? param.limit_date : HTMLUtils.formatLimitDate(todo.limitDate)}">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-8 col-md-offset-2 col-xs-10">
				<a href="index.html" class="btn btn-default">キャンセル</a>
				<button type="submit" class="btn btn-primary">更 新</button>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<a href="delete.html?id=${param.id != null ? param.id : todo.id}" class="btn btn-danger">削 除</a>
			</div>
		</div>
	</fieldset>
</form>

<jsp:include page="_footer.jsp" />