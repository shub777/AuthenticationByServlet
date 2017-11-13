<%-- 
    Document   : UserLogin
    Created on : 11 Nov, 2017, 7:20:10 PM
    Author     : Shubham Bhama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script type = "text/javascript">
            function formValidation()
            {
                var email = document.login.email.value;
                var password = document.login.pass.value;
                if (email === "") {
                    alert("Email is required");
                    return false;
                }
                if (password === "") {
                    alert("Enter your password");
                    return false;
                }
                if (password !== "") {
                    if (!validatePassword(password)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            function validatePassword(newPassword) {
                var minNumberofChars = 8;
                var maxNumberofChars = 16;
                var regularExpression = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,16}$/;
                if (newPassword.length < minNumberofChars || newPassword.length > maxNumberofCha(?!.*\srs) {
                    alert("password should contain minimum 8 and maximum 16");
                    return false;
                }
                if (!newPassword.match(regularExpression)) {
                    alert("Error: password should contain at least one number,uppercase,lowercase and one special character");
                    return false;
                }
                return true;
            }
        </script>

    </head>
    <body>
        <h2>Login Form</h2>
        <form name='login' action="Login" method="POST" onSubmit="return formValidation();">  
            <table>
                <tr>  
                    <td><label>Email: </label></td>
                    <td><input type="email" name="email" size="30" /></td>
                </tr>
                <tr>  
                    <td><label>Password: </label></td>
                    <td><input type="password" name="pass" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Submit" /><br><br><br><br><td>
                </tr>
            </table>  
        </form>
    </body>
</html>
