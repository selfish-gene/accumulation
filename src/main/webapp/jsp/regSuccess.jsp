<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="text-align:center;">
   <div class="jumbotron">
      <h1>提示信息</h1>
      <p>注册成功</p>
      <p>name=${param.name}</p>
      <p>age=${param.age}</p>
      <p>userName=${param.userName}</p>
      <p>password=${param.password}</p>
      <p>hobbit=${paramValues.hobbit[0]} ${paramValues.hobbit[1]} ${paramValues.hobbit[2]}</p>
      <p>rember=${param.checkbox}</p>
      <p>regFrom=${param.regFrom}</p>
      <p>weixin=${initParam.weixin}</p>
      <p>email=${initParam.email}</p>
      
      <p>cookie=${cookie}</p>
      <p>cookies=${pageContext.request.cookies}</p>
      <p>cookie.JSESSIONID=${cookie.JSESSIONID.value}</p>
      
      <p>pageScope=${pageScope}</p>
      <p>requestScope=${requestScope}</p>
      <p>sessionScope=${sessionScope}</p>
     <%--  <p>applicationScope=${applicationScope}</p> --%>
      
      <p>pageContext=${pageContext}</p>
      <p>localAddr=${pageContext.request.localAddr}</p>
      <p>localPort=${pageContext.request.localPort}</p>
      <p>contextPath=${pageContext.request.contextPath}</p>
      <p>servletPath=${pageContext.request.servletPath}</p>
      <p>requestURL=${pageContext.request.requestURL}</p>
      
      <p>requestURL=<%=request.getRequestURI()%></p>
      <p>servletPath=<%= request.getServletPath() %></p>
      <p><a class="btn btn-primary btn-lg" role="button" href="${pageContext.request.contextPath}/html/reg.html"> 返回</a></p>
   </div>
</div>

</body>
</html>