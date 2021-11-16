<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/10/14
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${stus}" var="stus">
    <p>${stus.id}</p>
    <p>${stus.name}</p>
    <p>${stus.sex}</p>
    <p>${stus.age}</p>
    <hr>
</c:forEach>
</body>
</html>
