<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="heading">
    <h1>Welcome to conferences</h1>
</div>

<div class="registration">
    <h2>Enter</h2>
    <form name="Form2" method="post" action="conferences">
        <input type="hidden" name="command" value="login">
        <table style="width: 80%">
            <tr class="registration-line">
                <td class="registration-title">Email</td>
                <td>
                    <input type="email" name="email" required class="registration-input">
                </td>
            </tr>
            <tr class="registration-line">
                <td class="registration-title">Password</td>
                <td>
                    <input type="password" name="password" required class="registration-input">
                </td>
            </tr>
        </table>
        <input type="submit" value="Login" class="btn btn-password">
    </form>
</div>
<div class="languages">
    <a href="conferences?command=changeLang&language=EN">EN</a>
    <a href="conferences?command=changeLang&language=UA">UA</a>
</div>
<div class="registration-page">
    <a href="registration_en.jsp">Registration</a>
</div>
</body>
</html>
