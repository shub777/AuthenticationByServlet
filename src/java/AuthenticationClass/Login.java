/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthenticationClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shubham Bhama
 */
public class Login extends HttpServlet {

    private boolean status = false;
    private String errorMsg = "";
    private boolean success = false;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        if(!Util.emailValidator(email)){
            pw.println("Error : Check your email</br>");
            success = false;
        }
        else{
            success = true;
        }
        if(!Util.passwordValidator(password)){
            success = false;
            pw.println("Error : Check your password");
        }
        else{
            success = true;
        }
//
//        if (errorMsg.length() != 0) {
//            success = false;
//            pw.println("Sorry! there were errors. Error message is " + errorMsg);
//        } else {
//            success = true;
//        }
        
        if (success) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication", "root", "419244");
                PreparedStatement ps = connection.prepareStatement(
                        "select * from login where email=? and password=?");
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                status = rs.next();

                if (status) {
                    RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
                    rd.forward(request, response);
                    HttpSession session = request.getSession();
                    session.setAttribute("email_id", email);
                } else {
                    pw.println("Login unSuccessful");
                    request.getRequestDispatcher("UserLogin.jsp").include(request, response);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                pw.print(ex.getMessage());
            }
        }
    }
}
