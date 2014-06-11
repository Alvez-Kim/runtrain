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


	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<ul class="nav">
					<li class="active"><a href="#">${menuList[0].name}</a></li>
					<li><a href="#">链接</a></li>
					<li><a href="#">链接</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 帐户 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li CLASS><a href="#">链接</a></li>
							<li CLASS><a href="#">链接</a></li>
						</ul>
						</li>
				</ul>
			</div>
		</div>
	</div>
	<br/>
	<br/>
	<br/>

</body>
</html>