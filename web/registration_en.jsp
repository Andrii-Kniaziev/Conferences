<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align="center">
    <h1>Account registration form</h1>
    <form name="Form1" method="post" action="conferences">
        <input type="hidden" name="command" value="register">
        <table style="width: 80%">
            <tr>
                <td>Name</td>
                <td>
                    <input type="text" name="first_name" required>
                </td>
            </tr>
            <tr>
                <td>Surname</td>
                <td>
                    <input type="text" name="last_name" required>
                </td>
            </tr>
            <tr>
                <td>Email</td>
                <td>
                    <input type="email" name="email" required>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <input type="password" name="password" required>
                </td>
            </tr>
            <tr>
                <td>Role</td>
                <td>
                    <select name="role" required>
                        <option value=" " selected disabled></option>
                        <option value="speaker">Speaker</option>
                        <option value="listener">Listener</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Register">
    </form>
</div>
<div>
    <a href="index_en.jsp">To the login page</a>
</div>
</body>
</html>
