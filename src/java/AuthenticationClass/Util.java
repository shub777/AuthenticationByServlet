package AuthenticationClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shubham Bhama
 */
public class Util {

    public static int findId(Connection connection) throws SQLException {
        PreparedStatement loginStatement = connection.prepareStatement("SELECT employee_id FROM registration ORDER BY employee_id DESC LIMIT 1;");
        ResultSet rs = loginStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static boolean passwordValidator(final String password) {
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.]).{8,16})";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
    
    public static boolean emailValidator(final String email){
        String PASSWORD_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return true;
        }
        return false;

    }
    
    
    public static boolean cityValidator(String city) {
        boolean success;
        String errorMsg = "";
        if (city.equals("")) {
            success = false;
            errorMsg += "<br>City is required</br>";
        } else {
            success = true;
        }
        return success;
    }

    public static boolean pincodeValidator(String pincode) {
        boolean success;
        String errorMsg = "";
        if (pincode.equals("")) {
            success = false;
            errorMsg += "<br>Pincode is required</br>";
        } else {
            success = true;
        }
        return success;
    }

    public static boolean addressValidator(String address) {
        boolean success;
        String errorMsg = "";
        if (address.equals("")) {
            success = false;
            errorMsg += "<br>Address is required</br>";
        } else {
            success = true;
        }
        return success;
    }

    public static boolean nameValidator(String fullname) {
        boolean success;
        String errorMsg = "";
        if (fullname.equals("")) {
            success = false;
            errorMsg += "<br>Full name is required</br>";
        } else {
            success = true;
        }
        return success;
    }

//    public static boolean emailValidation(String email) {
//        boolean success = false;
//        String errorMsg = "";
//        int emailAddressStartsWithAt = email.indexOf('@');
//        if (email.length() == 0) {
//            success = false;
//            errorMsg += "<br>Email is required<br>";
//        } else if (email.indexOf('@') <= -1) {
//            success = false;
//            errorMsg += "Invalid email address @ is not present";
//        } else if (emailAddressStartsWithAt == 0) {
//            success = false;
//            errorMsg += "<br>Invalid email Address! It starts with @<br>";
//        } else {
//            success = true;
//        }
//        return success;
//    }
    
//    public static boolean passwordValidation(String password) {
//        boolean success = false;
//        String errorMsg = "";
//        if(!Util.passwordValidator(password)){
//            success = false;
//            errorMsg += "<br>Invalid password Format</br>";
//        }
//        else{
//            success=true;
//        }
//        return success;
//    }
}
