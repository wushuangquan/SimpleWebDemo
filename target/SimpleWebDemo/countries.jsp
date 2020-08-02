<%--
  Created by IntelliJ IDEA.
  User: wsq
  Date: 2020/7/30
  Time: 8:36 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Country List</title>
</head>
<body>
we operate in these countries:
<ul>
    <c:forEach items="${countries}" var="country">
        <li>
                ${country.value}
        </li>
    </c:forEach>
</ul>
</body>

</html>
