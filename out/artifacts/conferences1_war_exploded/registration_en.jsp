<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Registration</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div align="center" class="heading">
    <h1>Account registration form</h1>
    <form name="Form1" method="post" action="conferences">
        <input type="hidden" name="command" value="register">
        <table style="width: 80%">
            <tr>
                <td class="registration-page-title">Name</td>
                <td>
                    <input type="text" name="first_name" required class="registration-page-input">
                </td>
            </tr>
            <tr>
                <td class="registration-page-title">Surname</td>
                <td>
                    <input type="text" name="last_name" required class="registration-page-input">
                </td>
            </tr>
            <tr>
                <td class="registration-page-title">Email</td>
                <td>
                    <input type="email" name="email" required class="registration-page-input">
                </td>
            </tr>
            <tr>
                <td class="registration-page-title">Password</td>
                <td>
                    <input type="password" name="password" required class="registration-page-input">
                </td>
            </tr>
            <tr>
                <td class="registration-page-title">Role</td>
                <td>
                    <select name="role" required class="registration-page-select">
                        <option value=" " selected disabled></option>
                        <option value="speaker">Speaker</option>
                        <option value="listener">Listener</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Register" class="btn btn-light">
    </form>
</div>
<div class="to-main-page">
    <a href="index_en.jsp">To the login page</a>
</div>
</body>
</html>
