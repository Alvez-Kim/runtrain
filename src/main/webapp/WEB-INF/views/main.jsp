<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
		welcome!!
	</center>
	<a href="logout">logout</a>
	<button onclick="ajtest()">TEST</button>
	<sf:form method="delete" action="upload" enctype="multipart/form-data">
		<input type="submit" value="click！！"/>
		<input type="file" name="file"/>
	</sf:form>
</body>

<script type="text/javascript">  
function ajtest(){
    jQuery.ajax({  
        url : 'userList',  
        accept:'application/xml',
        contentType : "application/xml",  
        processData : true,  
        dataType : "json",  
        data : {  
            tag : 'tag123'  
        },  
        success : function(data) {
            alert(data);
        },  
        error : function(e) {  
            alert('error');
        }  
    });  
}
</script>  

</html>