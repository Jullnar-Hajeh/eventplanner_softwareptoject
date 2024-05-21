package eventTests;

import org.example.Admin;
import org.example.Functions;
import org.example.Printing;
import org.example.Service;
import org.junit.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AdminTest {
	Functions functions;
    Printing printing;
    Admin admin = new Admin();
  


    @Before
    public void setUp() throws Exception {

         functions = new Functions();
        
       

         InputStream inputStream = new ByteArrayInputStream(        		   
      		   (       "1\n1\n"+"1\n4\n"    //customer view all
        				   + "2\n1\n"+"2\n5\n"+"2\n6\n"      				   
      				   + "3\n1\ndone\n3\n2\ndone\n"+"3\n2\n1000\ndone\n"+"3\n4\ndone\n"+"3\n5\ndone\n"+"3\n7\n"
        				  // +"3\n1\n111\nN\n"
      				   + "4\n1\n"+"4\n5\n"+"4\n6\n"   // venue
        				   + "5\n1\n"+"5\n3\n" +"5\n6\n"    //provider
      				   + "6\n1\n" +"6\n5\n"+"6\n6\n"           // packeg        				  
      				   + "7\n"              //report 
      				   +"9\n"
      				   + "0000\n"
       				 //  + "8\n"           //logout
      				 
      				   
      				   ).getBytes());
	        System.setIn(inputStream);
	        System.setIn(inputStream);
          try {
        	  //Functions.signInAdmin("111");
               functions.adminPage(admin.getAdminId());
	            	            
	        } catch (Exception e) {
	            e.printStackTrace();
	       }

	        System.setIn(System.in);
	    
	      }
     
    
    
    
    
    
    @Given("admin log in with true {string} and {string}")
    public void adminLogInWithTrueAnd(String a0, String a1) throws Exception {

        if(a0.equals(admin.getAdminId()) && a1.equalsIgnoreCase(admin.getAdminPassword())){
            admin.setState(true);
            functions.adminPage(admin.getAdminId());
            
           
        }
    }
    @Test
    public void testGetEmail() {
       
        assertEquals("royasmine05@gmail.com", admin.getEmail());
    }

    @Test
    public void testGetAdminId() {
        Admin admin = new Admin();
        assertEquals("111", admin.getAdminId());
    }

    @Test
    public void testGetAdminPassword() {
        Admin admin = new Admin();
        assertEquals("1234", admin.getAdminPassword());
    }

    @Test
    public void testGetEmailPassword() {
        Admin admin = new Admin();
        assertEquals("igun bclo kbti fzno", admin.getEmailPassword());
    }
    
    @Test
    public void testGetStateDefault() {
        Admin admin = new Admin();
        assertFalse(admin.getstate());
    }
    
    @Test
    public void testSetState() {
        Admin admin = new Admin();
        admin.setState(true);
        assertTrue(admin.getstate());
    }
  @Test
    public void testViewBusinessReports() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Functions.updateCustomersList();
        Functions.updateEventList("event.txt");
        
        try {
            Method method = Functions.class.getDeclaredMethod("viewBusinessReports");
            method.setAccessible(true);
            Object result = method.invoke(functions);
            
            // Add assertion to verify the result
            assertNull(result);
            
        } catch (Exception e) {
            // Handle any exceptions that may occur during reflection or method invocation
            fail("An exception occurred: " + e.getMessage());
        }
    }
  
 
  

  
}
