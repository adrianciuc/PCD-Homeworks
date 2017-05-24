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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>EDUE</title>
    <link rel="shortcut icon" href="../../img/favicon.ico" />
    <link rel="stylesheet" href="../../css/mainPage.css">
    <link rel="stylesheet" href="../../css/login.css">
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
    <header id="top" class="header">
        <div class="text-vertical-center">
            <%--<form name='f' action="/login" method='POST'>--%>
                <%--<h1>EDUE</h1>--%>
                <%--User:--%>
                <%--<input type='text' name='ssoId' value=''>--%>
                <%--Password:--%>
                <%--<input type='password' name='password' />--%>
                <%--<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />--%>
                <%--<input name="submit" type="submit" value="submit" />--%>
            <%--</form>--%>
                <h1>EDUE</h1>
                <div class="container">
                    <div class="card card-container">
                        <form class="form-signin" name='f' action="/login" method='POST'>
                            <span id="reauth-email" class="reauth-email"></span>
                            <input type="text" name='ssoId' value='' id="inputEmail" class="form-control" placeholder="Name" required autofocus>
                            <input type="password" name='password' id="inputPassword" class="form-control" placeholder="Password" required>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
                        </form>
                    </div>
                </div>
        </div>
    </header>
</body>
</html>
