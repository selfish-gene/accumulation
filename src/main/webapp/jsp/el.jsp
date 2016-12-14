<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align: center; line-height: 32px;">
	 <h4>EL表达式</h4> 
	
	 ${book.author.hobbit.name}<br/>
	 ${map.color}------${map.size}<br/>
	 ${requestScope["my.doc"]}<br/>	 
	 ${arr[1]}<br/>	 
	 ${arr[index]}<br/>	 
	 ${list[0]}------${list[index]}------${list[2]}<br/>	 
	
</body>
</html>