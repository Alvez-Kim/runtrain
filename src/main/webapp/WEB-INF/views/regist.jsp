<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="pageContext.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<center>
		<form action="${context }/main/regist" class="form-horizontal">
			<fieldset>
				<legend>请填写注册信息</legend>
				<table cellpadding="10">
					<tr>
						<td align="right">ID</td>
						<td><input type="text" class="input-medium" name="name"></td>
					</tr>
					<tr>
						<td align="right">密码</td>
						<td><input type="text" class="input-medium" name="password"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button class="btn btn-success" href="#">注 册</button>
							<input type="reset" class="btn btn-success" value="重置"/>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>

	</center>
</body>
</html>