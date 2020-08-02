<%--
  Created by IntelliJ IDEA.
  User: wsq
  Date: 2020/7/31
  Time: 1:20 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>User form</title>
</head>
<body>
<form action="test2.jsp" method="post">
    <table>
        <tr>
            <td>Name：</td>
            <td>${param.name}
                (length:${fn:length(param.name)})
            </td>
        </tr>
        <tr>
            <td>Address：</td>
            <td>${param.address}
                (length:${fn:length(param.address)})
            </td>
        </tr>
    </table>
</form>
</body>
</html>
