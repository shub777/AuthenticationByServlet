/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import AuthenticationClass.Registration;
import AuthenticationClass.Util;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Shubham Bhama
 */
public class RegisterTest {
    
    public RegisterTest() {
    }
    
    @Test(timeout = 500)
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("fullname")).thenReturn("name of the user");
        when(request.getParameter("email")).thenReturn("Me");
        when(request.getParameter("address")).thenReturn("Address");
        when(request.getParameter("city")).thenReturn("City");
        when(request.getParameter("pincode")).thenReturn("Pincode");
        when(request.getParameter("pass")).thenReturn("PassPhrase");
        when(request.getParameter("conpass")).thenReturn("Conform PassPhrase");
        
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Registration().doPost(request, response);

        verify(request, atLeast(1)).getParameter("email"); // only if you want to verify email was called...
        writer.flush();
    }
    
    @Test
    public void testNameValidation(){
        assertTrue(Util.nameValidator("Shubham"));
        
        /**
         * Below instruction fails because name cannot be blank.
         */
        
        //assertTrue(Util.nameValidator(""));
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
    
    @Test
    public void testAddressValidation(){
        assertTrue(Util.addressValidator("Dehmi kalan near gvk toll plaza manipal university,jaipur"));
        
        /**
         * Below instruction fails because address cannot be blank.
         */
        
        //assertTrue(Util.addressValidator(""));
    }
    
    @Test
    public void testCityValidation(){
        assertTrue(Util.cityValidator("Jaipur"));
        
        /**
         * Below instruction fails because city cannot be blank.
         */
        
        //assertTrue(Util.cityValidator(""));
    }
    
    @Test
    public void testPincodeValidation(){
        assertTrue(Util.pincodeValidator("302026"));
        
        /**
         * Below instruction fails because pincode cannot be blank.
         */
        
        //assertTrue(Util.pincodeValidator(""));
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
}
