<%-- 
    Document   : UserRegister
    Created on : 11 Nov, 2017, 7:01:11 PM
    Author     : Shubham Bhama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <script type = "text/javascript">
            function formValidation()
            {
                var name = document.registration.fullname.value;
                var email = document.registration.email.value;
                var address = document.registration.address.value;
                var city = document.registration.city.value;
                var pincode = document.registration.pincode.value;
                var password = document.registration.pass.value;
                var conformPassword = document.registration.conpass.value;
                var atposition = email.indexOf("@");
                var dotposition = email.lastIndexOf(".");

                if (name === "") {
                    alert("Enter your name");
                    document.registration.fullname.focus();
                    return false;
                }
                if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= x.lengthx) {
                    alert("Please enter a valid e-mail address \n atpostion:" + atposition + "\n dotposition:" + dotposition);
                    document.registration.email.focus();
                    return false;
                }
                if (address === "") {
                    alert("Address is required");
                    document.registration.address.focus();
                    return false;
                }
                if (city === "") {
                    alert("City is required");
                    document.registration.city.focus();
                    return false;
                }
                if (pincode === "") {
                    alert("Pincode is required");
                    document.registration.pincode.focus();
                    return false;
                }
                if (password === "") {
                    alert("Enter your password");
                    document.registration.password.focus();
                    return false;
                }
                if (conformPassword === "") {
                    alert("conform your password");
                    document.registration.conformPassword.focus();
                    return false;
                }
                if (password !== "" && password === conformPassword) {
                    if (!validatePassword(password)) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    alert("Error: Please check that you've entered and confirmed your password!");
                    return false;
                }
            }
            function validatePassword(newPassword) {
                var minNumberofChars = 8;
                var maxNumberofChars = 16;
                var regularExpression = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,16}$/;
                if (newPassword.length < minNumberofChars || newPassword.length > maxNumberofChars) {
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
        <h2>Registration Form</h2>
        <form name='registration' action="Registration" method="POST" onsubmit="return formValidation();">  
            <table>
                <tr>
                    <td><label>Full Name:</label></td>
                    <td><input type="text" name="fullname"/></td>
                </tr>
                <tr>  
                    <td><label>Email: </label></td>
                    <td><input type="email" name="email" size="30" /></td>
                </tr>
                <tr> 
                    <td><label>Address: </label></td>
                    <td><textarea name="address" id="address"></textarea><br></td>
                </tr>
                <tr>  
                    <td><label>City: </label></td>
                    <td><input type="text" name="city"/></td>
                </tr>
                <tr>  
                    <td><label>Pincode: </label></td>
                    <td><input type="number" name="pincode"/></td>
                </tr>
                <tr>  
                    <td><label>Password: </label></td>
                    <td><input type="password" name="pass" /></td>
                </tr>
                <tr>  
                    <td><label>Conform Password: </label></td>
                    <td><input type="password" name="conpass"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Submit" /><br><br><br><br><td>
                </tr>
            </table>  
        </form>
    </body>
</html>
