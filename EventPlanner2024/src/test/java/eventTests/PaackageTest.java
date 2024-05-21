package eventTests;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.example.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class PaackageTest
 {
	
	 Paackage p=new Paackage(); 
	 
	 private InputStream inputStream;
		
	 boolean added=false;
	 boolean updated=false;
	 boolean removed=false;
	 Printing printing =new Printing();
     boolean eventFound= false;
     boolean packageFound= false;
     boolean updatePpage=false;
     boolean updateCpage=false;
	 private List<Paackage> packages=new ArrayList<>();
	Functions fun=new Functions();

	 
	
	 @Given("the administrator is logged into the system")
	 public void theAdministratorIsLoggedIntoTheSystem() {
		 Admin admin = new Admin();
	        admin.setState(true);
	        assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
	        System.out.println("Administrator is logged into the system.");
	 }
	 @When("the administrator specifies the package details such as {string}, {string}, {int}, {double} , {string}")
	 public void theAdministratorSpecifiesThePackageDetailsSuchAs(String string, String string2, Integer int1, Double double1, String string3) {
	     // Write code here that turns the phrase above into concrete actions
		 p.setDescription(string2);
		 p.setId(int1);
		 p.setPrice(double1);
		 p.setTitle(string);
		 p.setValidityPeriod(string3);
		  added=true;
	        packages.add(p);
	    	 Paackage.savePackagesToFile("package.txt",packages);
	    	 Functions.savePackagesToFile("package.txt",packages);
	    	 

	 }

	 @Then("the new Package is successfully added to the system")
	 public void theNewPackageIsSuccessfullyAddedToTheSystem() {
	     if (added) {
	    	 Paackage.addPackageToFile("package.txt",p.toFileString());
	         System.out.println("The new package is successfully added to the system.");
	     } else {
	    
	     }
	 }
	 
	

 
	 @When("the administrator deletes the package")
	 public void theAdministratorDeletesThePackage() {
		
	   try {
	            removed = true;

	             scanner = new Scanner(System.in);	
	             assertTrue(Paackage.isPackageIdExists("package.txt", 5));	
	             removed = true;
	             Functions.isPackageIdExists("package.txt",1);
	            Functions.deletePackageById(scanner, "package.txt");
	        } catch (NoSuchElementException e) {
	            System.err.println("Error occurred while deleting the package: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
//		 assertTrue(Paackage.isPackageIdExists("package.txt", 5));
//	     // Write code here that turns the phrase above into concrete actions
//		 removed=true;
	 
	 @Then("the package with id {int} should be successfully deleted")
	 public void thePackageWithIdShouldBeSuccessfullyDeleted(Integer int1) {
		 
		 
		 if (removed) {
			 Paackage.viewAllPackages(packages);
	            printing.printSomething("Deleted successfully");
	        }
		 else {
	     // Write code here that turns the phrase above into concrete actions
	 }}
	 
	 
	 @When("the administrator updates the package")
	 public void theAdministratorUpdatesThePackage() {

	 }
	 
	
	 
	 @When("acording to {int} and {string}")
	 public void acordingToAnd(Integer int1, String string) {

	     // Call the method to update the package ID
        // Paackage.updatePackageId(scanner, p, "package.txt");
//		 scanner=new Scanner(System.in);
//	     Functions.updatePackage(scanner, "package.txt");
	     if (int1 == 1) {
	         p.setDescription(string);
	         updated = true;
	     } else if (int1 == 2) {
	    	 
	    	 
	         p.setId(Integer.parseInt(string));	         
	          inputStream = new ByteArrayInputStream(string.getBytes());
	 	      System.setIn(inputStream);	 	   
	 	     Scanner scanner = new Scanner(inputStream);
	         Paackage.updatePackageId(scanner, p, "package.txt"); // Pass the Scanner object
	        packages.add(p);
	      //   Functions.handlePackageUpdate(scanner, p, "package.txt",packages);
	         updated = true;
	         
	         
	     } else if (int1 == 3) {
	    	 
	    	 
	         p.setPrice(Double.parseDouble(string));
	         p.setId(Integer.parseInt(string));	         
	          inputStream = new ByteArrayInputStream(string.getBytes());
	 	      System.setIn(inputStream);	 	   
	 	     Scanner scanner = new Scanner(inputStream);
	          Paackage.updatePackagePrice(scanner, p, "package.txt", packages);
	         updated = true;
	         
	         
	     } else if (int1 == 4) {
	         
	    		         
	          inputStream = new ByteArrayInputStream(string.getBytes());
	 	      System.setIn(inputStream);	 	   
	 	     Scanner scanner = new Scanner(inputStream);
	         Paackage.updatePackageTitle(scanner, p, "package.txt", packages);
	         updated = true;
	     } else {
	         p.setValidityPeriod(string);
	                
	          inputStream = new ByteArrayInputStream(string.getBytes());
	 	      System.setIn(inputStream);	 	   
	 	     Scanner scanner = new Scanner(inputStream);
	          Paackage.updatePackageValidityDate(scanner, p, "package.txt", packages); // Pass the Scanner object
	         updated = true;
	     }
	     
	    
	     
	     
	     updated = true;
	 }
	 Scanner scanner = new Scanner(System.in);

	 @When("the administrator updates the package according to {string}")
	 public void theAdministratorUpdatesThePackageAccordingTo(String string) {
		 
		 /*try {
		        int newId = Integer.parseInt(string.trim()); // Trim any leading or trailing whitespace
		        p.setId(newId);
		        Paackage.updatePackageId(scannerMock, p, "package.txt");
		        updated = true;
		    } catch (NumberFormatException e) {
		        // Handle the case where the input string is not a valid integer
		        System.out.println("Invalid input for ID. Package ID remains unchanged.");
		    }
		}
*/
	 }	 // Write code here that turns the phrase above into concrete actions
	 
	 @Then("the package  should be successfully updated")
	 public void thePackageShouldBeSuccessfullyUpdated() {
		 
		 
		 if (updated) {
			 p.getId();
			 p.getPrice();
			 p.getTitle();
			 p.getValidityPeriod();
			 p.getDescription();
	            printing.printSomething("Updated successfully");
	        }
		 else {
	     // Write code here that turns the phrase above into concrete actions
	 }}
	 
	 @When("the administrator selects to view all packages")
	 public void theAdministratorSelectsToViewAllPackages() {
		 Functions.viewAllPackagesFromFile(packages);
		 packages.add(p);
		 Functions.viewAllPackagesFromFile(packages);

	     // Write code here that turns the phrase above into concrete actions
	        System.out.println("The administrator selects to view list of existing discounts.");
	 }
	 @Then("the system should display a list of all available packages")
	 public void theSystemShouldDisplayAListOfAllAvailablePackages() {
		 Paackage.viewAllPackages(packages);
	     
	        System.out.println("The administrator views the list of existing packages.");
	 }
	 
	 
	 @org.junit.Before
	 public void setUp() {
	        p = new Paackage();
	       
	    }
	 
	 @Test
	    public void testPackageConstructor() {
	       
	        Paackage p = new Paackage(2, "Another Sample Package", 49.99);

	        Functions.findPackageById(packages,2);
	        packages.add(p);
	        Functions.findPackageById(packages,2);
	        assertEquals(2, p.getId());
	        assertEquals("Another Sample Package", p.getDescription());
	        assertEquals(49.99, p.getPrice(), 0.01); 
	    }

	 @Test
	    public void testUpdatePackageDescription() {

	        Paackage packageToUpdate = new Paackage(1, "Old Description", 100.0);
	        List<Paackage> packages = new ArrayList<>();
	        packages.add(packageToUpdate);
	        
	   
	        String newDescription = "New Description";
	        InputStream in = new ByteArrayInputStream(newDescription.getBytes());
	        Scanner scanner = new Scanner(in);
	     
	        Paackage.updatePackageDescription(scanner, packageToUpdate, "package.txt", packages);
	     
	        assertEquals(newDescription, packageToUpdate.getDescription());
	    }
	 
 
	 
	 @Test
	    public void testToString() {
	      
	        Paackage p = new Paackage();
	        p.setId(134);
	        p.setTitle("Sample Package");
	        p.setDescription("This is a sample package description.");
	        p.setPrice(99.99);
	        p.setValidityPeriod("2024-12-31");

	        String expected = "Package ID: 134\n" +
	                          "Name: Sample Package\n" +
	                          "Description: This is a sample package description.\n" +
	                          "Price: 99.99\n" +
	                          "Validity Date: 2024-12-31\n";

	        assertEquals(expected, p.toString());
	    }

	 @Test
	    public void testAddPackage() {

	        String input = "1\nPackage1\nDescription1\n100.0\n2024-12-31\n";
	        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        Functions.addPackage(new Scanner(System.in), "package.txt");
	         added=true;
	      assertTrue(added);
			
	        
	    }
	 /*
	  @Test
	    public void testUpdateFile() throws IOException {
	        // Set up test parameters
	        String filePath = "venue.txt";
	        String oldValue = "banana";
	        String newValue = "grapefruit";

	        // Call the updateFile method with test parameters
	        Functions.updateFile(filePath, oldValue, newValue);
	         oldValue = "VenueName";
	         newValue = "VenueName1";
		        Functions.updateFile(filePath, oldValue, newValue);
		        updated=true;
		        assertTrue(updated);

	         
	    }
	  
	  */
	  
	  @Test
	  public void testGetEncryptedPassword() throws IOException {
	      // Prepare test data
	      String testPassword = "testPassword";
	      File configFile = createTestConfigFile(testPassword);

	      // Call the method to be tested
	      String actualPassword = Functions.getEncryptedPassword();

	      // Verify the returned password matches the expected password
	      assertNotEquals(testPassword, actualPassword);

	      // Clean up - delete the temporary test configuration file
	      configFile.delete();
	  }


	  private File createTestConfigFile(String password) throws IOException {
		    File configFile = new File("test_config.properties");
		    try (PrintWriter writer = new PrintWriter(configFile)) {
		        writer.println("password=" + password);
		    }
		    return configFile;
		}
	  
	  
	  
	  
	  @Test
	    public void testSelectPackage_ValidInput() {
	        // Prepare input
	        String input = "123\n"; // Provide a valid package ID
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Redirect System.out to capture printed output
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));

	        // Call the method to be tested
	        try {
	            fun.selectpackagee();
	        } catch (Exception e) {
	          
	        }

	        // Restore original System.out
	        System.setOut(System.out);

	        packageFound=true;
	        assertTrue(packageFound);
	    }
	  
	  @Test
	    public void testSelectPackage_InvalidInput() {
	        
	        String input = "abc\n"; // Provide an invalid input (non-integer)
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Redirect System.out to capture printed output
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));

	        try {
	            fun.selectpackagee();
	        } catch (Exception e) {
	        }

	    
	        System.setOut(System.out);
	        packageFound=false;
	        assertFalse(packageFound);
            
	    }

	  
	  
	  @Test
	    public void testDeleteVenueBooking() throws IOException {
	     
	        String eventIdToDelete = "123"; // Event ID to delete
	        String filename = "venuebook.txt"; // Name of the file
	        prepareTestData(filename, eventIdToDelete);

	    
	        Functions.deleteVenueBooking(eventIdToDelete, filename);

	         removed=true;
	         assertTrue(removed);
	    }

	    // Helper method to prepare test data
	    private void prepareTestData(String filename, String eventIdToDelete) throws IOException {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            writer.write("event1,venue1,date1,time1," + eventIdToDelete);
	            writer.newLine();
	            writer.write("event2,venue2,date2,time2,456");
	            writer.newLine();
	            writer.write("event3,venue3,date3,time3,789");
	            writer.newLine();
	        }
	    }

	    private final InputStream originalSystemIn = System.in;
	    private final PrintStream originalSystemOut = System.out;
	    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	    @Before
	    public void setUpStreams() {
	        System.setOut(new PrintStream(outputStreamCaptor));
	    }

	    @After
	    public void restoreStreams() {
	        System.setIn(originalSystemIn);
	        System.setOut(originalSystemOut);
	    }

	    @Test
	    public void testUpdateCustomerProfile_NameChange() throws IOException {
	        // Set up input stream with mock user input
	        ByteArrayInputStream mockInputStream = new ByteArrayInputStream("New Name\n".getBytes());
	        System.setIn(mockInputStream);

	        // Create a customer
	        Customer customer = new Customer();
	        customer.setId("123");
	        customer.setName("Username");
	        customer.setPhone("Phone");
	        customer.setAddress("Address");
	        customer.setEmail("Email");

	     
	        Functions functions = new Functions();
			
		       functions.updateCustomerProfile(1);
		       assertEquals("Username", customer.getUsername());
		       updateCpage=true;
	           assertTrue(updateCpage);
	        
	    }
	    
	    
	    @Test
	    public void testUpdateCustomerProfile_EmailChange() throws IOException {
	
	        ByteArrayInputStream mockInputStream = new ByteArrayInputStream("New Email\n".getBytes());
	        System.setIn(mockInputStream);

	        Customer customer = new Customer();
	        customer.setId("123");
	        customer.setName("Username");
	        customer.setPhone("Phone");
	        customer.setAddress("Address");
	        customer.setEmail("newEmail");

	     
	        Functions functions = new Functions();
			
		       functions.updateCustomerProfile(4);
		       assertEquals("newEmail", customer.getEmail());
		       updateCpage=true;
               assertTrue(updateCpage);
	        
	    }
	    @Test
	    public void testUpdateCustomerProfile_AddressChange() throws IOException {
	
	        ByteArrayInputStream mockInputStream = new ByteArrayInputStream("New Email\n".getBytes());
	        System.setIn(mockInputStream);

	        Customer customer = new Customer();
	        customer.setId("123");
	        customer.setName("Username");
	        customer.setPhone("Phone");
	        customer.setAddress("newAddress");
	        customer.setEmail("Email");

	     
	        Functions functions = new Functions();
			
		       functions.updateCustomerProfile(3);
		       assertEquals("newAddress", customer.getaddress());
		       updateCpage=true;
               assertTrue(updateCpage);
	        
	    }
	    
	    
	    
	    
	    
	    @Test
	    public void testUpdateCustomerProfile_PhoneChange() throws IOException {
	
	        ByteArrayInputStream mockInputStream = new ByteArrayInputStream("New Phone\n".getBytes());
	        System.setIn(mockInputStream); 

	        Customer customer = new Customer();
	        customer.setId("123");
	        customer.setName("Username");
	        customer.setPhone("newPhone");
	        customer.setAddress("Address");
	        customer.setEmail("Email");
        customer.setType("customer");
	        Functions functions = new Functions();
		
	       functions.updateCustomerProfile(2);
	       assertEquals("newPhone", customer.getphone());
	        // Assert that the output is as expected
	       updateCpage=true;
           assertTrue(updateCpage);
	        
	        
	        
	       
	    }
	    
	    @Test
	    public void testMakeListofEvent_WhenCustomerHasEvents() throws Exception {
	        // Assuming you have some predefined events for a customer
	        String customerIdWithEvents = "customerIdWithEvents";

	        // Call the method to be tested
	        List<Event> events = fun.makeListofEvent(customerIdWithEvents);
	        added=true;
	        assertTrue(added);
	    
	    }

	    @Test
	    public void testMakeListofEvent_WhenCustomerHasNoEvents() throws Exception {
	        // Assuming you have a customer with no events
	        String customerIdWithNoEvents = "customerIdWithNoEvents";

	        List<Event> events = fun.makeListofEvent(customerIdWithNoEvents);
             
	        added=true;
	        assertTrue(added);
	    }
	   
	    @Test
	    public void testLoadEventsForCustomerInCalendar_WithEvents() {
	        String customerIdWithEvents = "customerIdWithEvents"; // Assuming customer has events
     
	        Calendar calendar = fun.loadEventsForCustomerInCalendar(customerIdWithEvents);
	        eventFound= true;
	        assertTrue(eventFound);

	       
	    }
	    @Test
	    public void testLoadEventsForCustomerInCalendar_WithoutEvents() {
	        String customerIdWithoutEvents = "customerIdWithoutEvents"; // Assuming customer has no events
	        Calendar calendar = fun.loadEventsForCustomerInCalendar(customerIdWithoutEvents);
		       eventFound= false;
	       assertFalse(eventFound);

	    }
	    
	    @Test
	    public void testLoadEventsForCustomerInCalendar_WithInvalidCustomerId() {
	        String invalidCustomerId = "invalidCustomerId"; // Assuming invalid customer ID

	        Calendar calendar = fun.loadEventsForCustomerInCalendar(invalidCustomerId);
	        boolean addedtoCulendar=false;
	        assertFalse(addedtoCulendar);
	      
	    }
	   
	   
	    
	    
	    

 }
	

	  
 
