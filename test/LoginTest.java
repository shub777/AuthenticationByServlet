/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import AuthenticationClass.Login;
import AuthenticationClass.Util;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Shubham Bhama
 */
public class LoginTest extends Mockito {

    public LoginTest() {
    }
    
    @Test(timeout = 400)
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("me");
        when(request.getParameter("pass")).thenReturn("passphrase");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);

        verify(request, atLeast(1)).getParameter("email"); // only if you want to verify email was called...
        writer.flush();
    }

    @Test
    public void testPasswordValidation() {
        String expected = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.]).{8,16})";
        assertTrue(expected, Util.passwordValidator("Admin123."));

        /**
         * Below two instruction fails because requirement of password
         * validation. atleast one UPPERCASE,one LOWERCASE,one number and open
         * special character and must be of minimum length of 8.
         */
        
        //assertTrue(expected, Util.passwordValidator("Admin."));
        //assertTrue(expected, Util.passwordValidator("dmin123"));
    }

    @Test
    public void testEmailValidation() {
        assertTrue(Util.emailValidator("shubhambhama30@gmail.com"));

        /**
         * Below two instruction fails because requirement of email validation.
         * it is not in email format.
         */
        
        //assertTrue(Util.emailValidator("shubhambhama30@gmailcom"));
        //assertTrue(Util.emailValidator("shubhambhama30gmail.com"));
    }
}
