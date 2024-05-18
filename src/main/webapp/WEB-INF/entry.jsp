<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="_header.jsp" />

<form class="form-horizontal" action="entry.html" method="post">
	<fieldset>
		<legend>登録フォーム</legend>
		<div class="form-group">
			<label for="title" class="col-md-2 control-label">題名</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="title" placeholder="題名" value="${param.title}" name="title">
			</div>
		</div>
		<div class="form-group">
			<label for="detail" class="col-md-2 control-label">詳細</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="3" id="detail" placeholder="詳細" name="detail">${param.detail}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">重要度</label>
			<div class="col-md-10">
				<div class="radio">
					<label>
						<input type="radio" name="importance" value="3" ${param.importance == null || param.importance == "3" ? "checked" : ""}>★★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" value="2" ${param.importance == "2" ? "checked" : ""}>★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" value="1" ${param.importance == "1" ? "checked" : ""}>★
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="limit" class="col-md-2 control-label">期限</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="limit" placeholder="期限" value="${param.limit_date}" name="limit_date">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-10 col-md-offset-2">
				<button type="submit" class="btn btn-default">キャンセル</button>
				<button type="submit" class="btn btn-primary">追 加</button>
			</div>
		</div>
	</fieldset>
</form>

<jsp:include page="_footer.jsp" />