<%--
  Created by IntelliJ IDEA.
  User: wsq
  Date: 2020/7/31
  Time: 1:20 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User form</title>
</head>
<body>
<form action="test2.jsp" method="post">
    <table>
        <tr>
            <td>Name：</td>
            <td><input name="name"></td>
        </tr>
        <tr>
            <td>Address：</td>
            <td><input name="address"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Login">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
