package AuthenticationClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shubham Bhama
 */
public class Registration extends HttpServlet {

    int successCode = 0;
    int loginSuccessCode = 0;
    boolean success = false;
    private String errorMsg = "";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pincode = request.getParameter("pincode");
        String password = request.getParameter("pass");
        String conformPassword = request.getParameter("conpass");

        serverSideValidation(email, pw, password, fullname, address, pincode, city, conformPassword);

        if (success) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication", "root", "419244");
                Statement statement = connection.createStatement();
                successCode = statement.executeUpdate("insert into registration (employee_id,fullname,email,address,city,pincode,password,conformPassword) values"
                        + "(" + (Util.findId(connection) + 1) + ",'" + fullname + "','" + email + "','" + address + "','" + city + "','" + pincode + "','" + password + "','" + conformPassword + "')");
                RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
                rd.include(request, response);
                int m = statement.executeUpdate("insert into login values(" + (Util.findId(connection)) + ",'" + email + "','" + password + "')");
                if (successCode != 0) {
                    pw.print("Data Inserted");
                } else {
                    pw.print("Data not inserted");
                }
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
                pw.print(ex.getMessage());
            }
        }
    }

    private void serverSideValidation(String email, PrintWriter pw, String password, String fullname, String address, String pincode, String city, String conformPassword) {
        if (!Util.emailValidator(email)) {
            pw.println("Error : Check your email</br>");
            success = false;
        } else {
            success = true;
        }

        if (!Util.passwordValidator(password)) {
            success = false;
            pw.println("Error : Check your password");
        } else {
            success = true;
        }

        if (!Util.nameValidator(fullname)) {
            success = false;
            pw.println("Error : Name is required");
        } else {
            success = true;
        }
        
        if (!Util.addressValidator(address)) {
            success = false;
            pw.println("Error : Check your password");
        } else {
            success = true;
        }
        
        if (!Util.pincodeValidator(pincode)) {
            success = false;
            pw.println("Error : Check your password");
        } else {
            success = true;
        }
        
        if (!Util.cityValidator(city)) {
            success = false;
            pw.println("Error : Check your password");
        } else {
            success = true;
        }
        
        if (!password.equals(conformPassword)) {
            success = false;
        } else {
            success = true;
        }
    }
}
