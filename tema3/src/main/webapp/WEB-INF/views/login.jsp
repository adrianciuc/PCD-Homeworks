<%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 01/05/2017
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDUE</title>
</head>
<body>
    <h1>Login</h1>
    <form name='f' action="/login" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='ssoId' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
