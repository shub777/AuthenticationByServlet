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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shubham Bhama
 */
public class MainPage extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String url = "jdbc:mysql://localhost:3306/authentication";
        final String root = "root";
        final String password = "419244";
        final String driver = "com.mysql.jdbc.Driver";
        boolean empty = true;

        PrintWriter pw = response.getWriter();
        try {
            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url, root, password);
            Statement smt = cn.createStatement();
            HttpSession session = request.getSession();
            String username = session.getAttribute("email_id").toString();
            pw.print("<h3> User logged in " + "</h3>");
            pw.print("<h2>" + username + "</h2>");
            ResultSet rs = smt.executeQuery("select * from registration where email = '" + username + "'");

            if (rs.isBeforeFirst()) {
                pw.println("<h3>Logged in userdata</h3>");
                pw.println("<table border=\"2\">");
                pw.println("<tr><th>employee_id</th><th>fullname</th><th>email</th><th>add  ress</th><th>city</th>"
                        + "<th>pincode</th><th>password</th><th>conform password</th></tr>");
            } else if (!rs.isBeforeFirst()) {
                pw.println("<h2>No Data</h2>");
            }
            while (rs.next()) {
                pw.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td>"
                        + "<td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td>"
                        + "<td>" + rs.getString(7) + "</td><td>" + rs.getString(8) + "</td></tr>");
            }
            pw.println("</table>");
        } catch (Exception ex) {
            pw.println(ex);
        }
    }
}
