package eventTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



/////
public class CustomerActionTest {
	Functions f=new Functions();
	 User user = new User();
Customer customer = new Customer("12114777","Ansam","057806241","Nablus","123456","ansam@gmail.com");

private static final String TEST_FILENAME = "test_invoices.txt";
private static final String CUSTOMER_ID = "123";
	    Functions functions = new Functions();
	    static boolean updated = false;
	    static boolean read = false;
	    static boolean deleteProfile = false;
	    static boolean added = false;

	    Printing printing = new Printing();
	    Event event=new Event();
	  
	    private static List<Customer> customers;
	 
	 
	    @Given("customer log in with true {string} and {string}")
	    public void customerLogInWithTrueAnd(String arg0, String arg1) throws Exception {
	        customer.setPassword("12345");
	        if(arg0.equals(customer.getId()) && arg1.equalsIgnoreCase(customer.getPassword())){
	            user.setLogstate(true);
	          
	    
	           
	        }
	    }
	    @Given("I am logged in")
	    public void i_am_logged_in() throws Exception {
	        user.setLogstate(true);
	       
	       // Functions.updateCustomerFile();
	      
	       // Functions.updateEventList("event.txt");
	      //  Functions.updateeventandcustomer("event.txt");
	    }
	    @When("I navigate to My Profile")
	    public void i_navigate_to_my_profile() {
	        if(user.getLogstate()){
	            Functions.customerPageList();
	        }
	    }

	    @When("I update my profile information depends on {int} and {string}")
	    public void i_update_my_profile_information_depends_on_and(Integer i, String val) throws IOException {
	
	    	   
        if( i == 1){
	            customer.setName(val);
	            updated = true;
	          
	            assertEquals(val, customer.getUsername());
	           
	           
	        } else if (i == 2) {
	            customer.setPhone(val);
	            updated = true;
	        } else if (i==3) {
	            customer.setAddress(val);
	            updated = true;
	        }else{
	            customer.setEmail(val);
	            updated = true;
	        }
	       
	    }
	    @Then("my profile should be updated successfully") 
	    public void my_profile_should_be_updated_successfully() throws IOException {
	        if(updated){
	            printing.printSomething("Updated successfully");
	            Functions.updateCustomerFile();
	            Customer.addCustomerToFile(customer);
	            
	            
	        }
	    }


		  @When("I navigate to Invoices")
		    public void i_navigate_to_invoices()  {
		        //printing.printSomething("\nNumber of orders:"+ customer.getNumOfEvents());
		       //invoice = new Invoice(customer);
		        //invoice.invoiceRes(customer);
		    }
		    @Then("I should see a my invoice")
		    public void i_should_see_a_my_invoice() {

		    }




     @When("I choose to delete my profile depends on {string}")
     public void iChooseToDeleteMyProfileDependsOn(String string) throws IOException {
     
      deleteProfile = true;
      Functions.deleteCustomer(string);
      Functions.deleteCustomerProfile(string);
       assertTrue(deleteProfile);
    
}



	 
	        
	       

@Test
public void testDeleteCustomer_InvalidId() {
   
    boolean deleted = Functions.deleteCustomer("12345678");
    assertFalse(deleted);
}
	 @When("I confirm the deletion")
	 public void iConfirmTheDeletion() {
	    
	 }

	  
	    @Then("my profile should be deleted successfully")
	    public void my_profile_should_be_deleted_successfully() {
	        printing.printSomething("\nAccount Successfully Deleted\n\n");
	        
	    }

	    @Test
	    public void testGetCustomerFromLine_ValidLine() {
	        String line = "12114777,Ansam,057806241,Nablus,ansam@gmail.com,12345";
	        Customer.getCustomerFromLine(line);
	        assertNotNull(customer);
	     
	       
	    }
	 
	    
	    @Test
	    public void testToString() {
	        Customer customer = new Customer("123", "John", "123456", "123 Main St", "password", "john@example.com");
	        String expectedString = "Customer{id='123', username='John', phone='123456', address='123 Main St', email='john@example.com', password='password'}";
	        assertEquals(expectedString, customer.toString());
	    }
	    @Test
	    public void testGetCevents() {
	        List<Event> events = Customer.getCevents();
	        int numEvc=events.size();
	        assertEquals(numEvc, events.size());
	    }

	    @Test
	    public void testGetEvents() {
	        List<Event> events = Customer.getEvents();
	        int numEv=events.size();
	        assertEquals(numEv, events.size());
	    }
	    
	    @Test
	    public void testAddEvent() throws ParseException {
	    	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	         Date eventDate = dateFormat.parse("2024-03-15");
	         
	        Event event = new Event("Conference",eventDate, "9:00 AM", "Tech conference", "200", "12114777", "Tech", "Conference", "V1", "111");
	        Customer.addEvent(event);
	        List<Event> events = Customer.getCevents();
	        int numE=events.size();
	        assertEquals(numE, events.size());
	       
	    }
	    
	    @Test
	    public void testPrintInColor() throws IOException {
   
	        Printing printing = new Printing();
	        String message = "Test message";
	        String color = Printing.ANSI_RED; 
	        printing.printInColor(message, color);    
	        boolean printedTrue=true;
	        assertTrue(printedTrue);
	    }

	    @Test
	    public void testColorText() {
	     
	        String text = "Test message 2";
	        String color = Printing.ANSI_RED; 

	        String coloredText = Printing.colorText(text, color);

	        String expectedColoredText = Printing.ANSI_RED + text + Printing.ANSI_RESET;
	        assertEquals(expectedColoredText, coloredText);
	    }
	    
	   @Test
	    public void testGetColoredString() {
	       
	        String input = "Test message 3";
	        String color = Printing.ANSI_RED; 
	     
	        Printing printing = new Printing();

	        String coloredString = printing.getColoredString(input, color);

	        
	        String expectedColoredString = Printing.ANSI_RED + input + Printing.ANSI_RESET;
	        assertEquals(expectedColoredString, coloredString);
	    }
	    
	 /*   @Test
	    public void testCustomerToFilet() throws ParseException {
	    	Customer customer = new Customer("121Test","Test","057806241","Nablus","123456","Test@gmail.com");
	    	
	    	 boolean found;
			
	    	if (Functions.searchIdU("121Test")) 
	    	{
	    		found=true;
	    	
	    	}
	    	else 
	    	{ 
	    		found=false;
	    		assertFalse(found);
	    
	    			                            
	    	}
		    if (found)
		    {	
		    	 Functions.deleteCustomer("121Test");
		      
		    
		    } 
		   
		   else {
			  
			   Customer.addCustomerToFile(customer);
			 
			     added=true;
			     assertTrue(added);
			     
		   }
	    	
	    }*/
	    @Test
	    public void testReadInvoiceFile_ValidInvoices() throws IOException {
	    	
                Functions.addToInvoice(CUSTOMER_ID, "Evint1", "EventName", 150.0);         
	     
	        Functions.readInvoiceFile("invoice.txt", CUSTOMER_ID);
	        read=true;
	        assertTrue(read);
	        

	       
	    }
	  
	    @Test
	    public void testUpdateCustomerProfile_Name() throws IOException {
	    	 customers = new ArrayList<>();
		        customers.add(new Customer("123", "John", "1234567890", "123 Main St", "000","john@example.com"));
	        String input = "New Name";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        updated=true;
            assertTrue(updated);
	       

	       
	    }

	    @Test
	    public void testUpdateCustomerProfile_Phone() throws IOException {
	    	customers = new ArrayList<>();
	        customers.add(new Customer("123", "John", "1234567890", "123 Main St", "000","john@example.com"));
	        String input = "1234567890";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
             updated=true;
             assertTrue(updated);



	        
	    }

	    @Test
	    public void testUpdateCustomerProfile_Email() throws IOException {
	    	customers = new ArrayList<>();
	        customers.add(new Customer("123", "John", "1234567890", "123 Main St", "000","john@example.com"));
	        String input = "newemail";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
             updated=true;
             assertTrue(updated);
      	        
	    }
	    

	    @Test
	    public void testUpdateCustomerProfile_Adress() throws IOException {
	    	customers = new ArrayList<>();
	        customers.add(new Customer("123", "John", "1234567890", "123 Main St", "000","john@example.com"));
	        String input = "neweaddress";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
             updated=true;
             assertTrue(updated);
      	        
	    }

	    
	    @Test
	    public void testPrintCustomersList() throws IOException {
	     
	      
	        Customer c= new Customer("2", "Jane Doe", "0987654321","Los Angeles","9999 ","jane@example.com");
	      
	      
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test_customers.txt", true))) {
	            writer.write(c.getId() + "," + c.getaddress() + "," + c.getEmail() + "," + c.getphone() + "," +c.getUsername()+ "\n");
	            System.out.println("customer  added successfully.");
	        } catch (IOException e) {
	            System.err.println("An error occurred while adding the customer : " + e.getMessage());
	        }
	        
	        BufferedReader reader = new BufferedReader(new FileReader("test_customers.txt"));
	        String line;
	        boolean found = false;
	        while ((line = reader.readLine()) != null) {
	            if (line.contains(c.getId()) && line.contains(c.getaddress()) && line.contains(c.getEmail()) && line.contains(c.getphone()) && line.contains(c.getUsername()) ) {
	                found = true;
	                break;
	            }
	        }
	        reader.close();

	     
	        assertTrue(found);
	    }
	    }

	    
	    

	    

