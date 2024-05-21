package eventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.example.*;
import org.junit.Test;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VenueTest {
	
    static boolean adding = false;
    static boolean updated = false;
    static boolean delete = false;
    Venue v = new Venue();
	 private InputStream inputStream;
private Scanner scanner;
    Printing printing = new Printing();
    
    List<Venue> venues = new ArrayList<>();
    
    Venue venue1 = new Venue("VenueName", "VenueAddress", 100, 150.0, "Available", "VenueID123", "image.jpg");
    Venue venue2 = new Venue("VenueName", "VenueAddress", 100, "image.jpg", 150.0, "Available", "2026-01-01");
   
   
	
    @Given("that the admin is logged into the system")
    public void thatTheAdminIsLoggedIntoTheSystem() {
        Admin admin = new Admin();
        admin.setState(true);
        assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
        System.out.println("Administrator is logged into the system.");
    }


    
    @When("select to add a new venue such as details {string}, {double}, {string}, {string}, {string}, {int}")
    public void selectToAddANewVenueSuchAsDetails(String string, Double double1, String string2, String string3, String string4, Integer int1) {
      
    	v.setId(string);
         v.setPrice(double1);
         v.setAddress(string2);
         v.setName(string4);
         v.setCapacity(int1);
         v.setImage(string3);
         String venueDetails = v.toFileString();
         String input = "000\nVenueName\nVenueAddress\nVenueImage\n100\n50.0\n";
         ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
         System.setIn(inputStream);
         venues.add(v);
         //venues.add(venue1);
         Scanner scanner = new Scanner(System.in);

         Functions.addVenue(scanner, "venue.txt");
         
         
         
      
         Functions.addVenueToFile("venue.txt",venueDetails );
         
    	// Functions.updateVenueFile("venue.txt",venues);

         
         
        
    }

    @Then("the admin can successfully add the venue to the system.")
    public void theAdminCanSuccessfullyAddTheVenueToTheSystem() {
        // Assuming some logic to add the venue to the system
    	
    	    adding = true;
    	 //   Functions.addVenue(scanner, "venue.txt");
    	    venue1.toFileString();
    	    printing.printSomething("Venue added successfully");
    }
    @When("select to edit venue details with the following changes:")
    public void selectToEditVenueDetails(DataTable changesTable) {
        Map<String, String> changesMap = changesTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : changesMap.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
           	Venue.findVenueIdByName("Palestine_Convention_Center","venue.txt");
            switch (field) {
                case "Address":   
                v.setAddress(value);
                 inputStream = new ByteArrayInputStream(value.getBytes());
                System.setIn(inputStream);
                
                 scanner = new Scanner(System.in);
                Functions.editVenuefrom(scanner,"venue.txt");
//                Functions.updateVenueFile("venue.txt",venues);
               
                    break;
                case "ID":
                	
                    v.setId(value);
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                    Functions.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Name":
                	
                    v.setName(value);
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                    Functions.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Capacity":
                    v.setCapacity(Integer.parseInt(value));
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                    Functions.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Price":
                    v.setPrice(Double.parseDouble(value));
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                    Functions.editVenuefrom(scanner,"venue.txt");
                    break;
                case "Image":
                    v.setImage(value);
                    inputStream = new ByteArrayInputStream(value.getBytes());
                    System.setIn(inputStream);
                    
                     scanner = new Scanner(System.in);
                    Functions.editVenuefrom(scanner,"venue.txt");
                    break;
            }
        }
        updated = true;
    	
//    	
//    	 
    }

  
    String venueIdToRemove;
    @When("select to delete a venue by its {string}")
    public void selectToDeleteAVenueByIts(String venueId) {
    	Functions.getPriceByVenue("jullnar");
    	Functions.getPriceByVenue(venue1.getName());

        
    	 venueIdToRemove = "000"; // Venue ID to remove
         inputStream = new ByteArrayInputStream(venueIdToRemove.getBytes());
         scanner = new Scanner(inputStream); // Provide venue ID as input

          // Test the deleteVenueById method
          
         Functions.isVenueIdExists("venue.txt","000");
         assertTrue( Functions.isVenueIdExists("venue.txt","000"));
         assertFalse (Functions.isVenueIdExists("venue.txt","nmnmnmn"));
        assertFalse(Functions.removeVenue0(venues,"9"));
         Venue venue123 = new Venue("123", "VenueName", "VenueAddress", 100, 1000.0, "imagepath");

         venues.add(venue123);
         assertTrue(Functions.removeVenue0(venues,"123"));

           venueId = "VenueID123";
          InputStream inputStream = new ByteArrayInputStream(venueId.getBytes());
           scanner = new Scanner(inputStream);

          // Call the method
          Functions.getVenueIdToRemove(scanner);
          scanner = new Scanner(System.in);
         // Functions.deleteVenueById(scanner, "venue.txt");

        // Verify that the venue is removed from the list
          
      
       
    	delete = true;
}


    @Then("the admin can successfully remove the venue from the system.")
    public void theAdminCanSuccessfullyRemoveTheVenueFromTheSystem() {
        if (delete) {
            printing.printSomething("Venue deleted successfully");
        }
    }

    @When("select to view all venues registered in the system")
    public void selectToViewAllVenuesRegisteredInTheSystem() {
    	Functions.viewAllVenues("venue.txt");
    	
    	Functions.printAllVenues(venues);

    }

    @Then("the admin can see a comprehensive list of all venues.")
    public void theAdminCanSeeAComprehensiveListOfAllVenues() {
    	Functions.viewAllVenues("venue.txt");
    	Functions.updateVenueFile("venue.txt", venues);
    	
    	
    	//venues.add(venue1);
  //	Functions.updateVenueFile("venue.txt", venues);

    }
    @Test
    public void testVenueConstructorWithAllParameters() {
        Venue venue = new Venue("Venue Name", "Venue Address", 100, 150.0, "Available", "VenueID123", "image.jpg");

        assertEquals("VenueID123", venue.getId());
        assertEquals("Venue Name", venue.getName());
        assertEquals("Venue Address", venue.getAddress());
        assertEquals(100, venue.getCapacity());
        assertEquals(150.0, venue.getPrice(), 0.001);
        assertEquals("Available", venue.getAvailavility());
        assertEquals("image.jpg", venue.getImage());
    }

    @Test
    public void testVenueConstructorWithLimitedParameters() {
        Venue venue = new Venue("VenueID123", "Venue Name", "Venue Address", 100, 150.0, "image.jpg");

        assertEquals("VenueID123", venue.getId());
        assertEquals("Venue Name", venue.getName());
        assertEquals("Venue Address", venue.getAddress());
        assertEquals(100, venue.getCapacity());
        assertEquals(150.0, venue.getPrice(), 0.001);
        assertEquals("image.jpg", venue.getImage());
    }

    @Test
    public void testSetAndGetDate() {
        Venue venue = new Venue();
        venue.setdate("2024-03-27");
        assertEquals("2024-03-27", venue.getdate());
    }

    @Test
    public void testToFileString() {
        Venue venue = new Venue("VenueID123", "VenueName", "VenueAddress", 100, 150.0, "image.jpg");
        String expectedString = "VenueID123,VenueName,VenueAddress,image.jpg,100,150.0";
        assertEquals(expectedString, venue.toFileString());
        deleteFileContents("venue.txt");
   
    }
    
    
    public static void deleteFileContents(String filename) {
        try {
            FileWriter writer = new FileWriter("venue.txt");
            writer.write(""); // Write an empty string to truncate the file
            writer.close();
            System.out.println("Contents of " + filename + " deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting contents of " + filename + ": " + e.getMessage());
        }
    }
  


    
}
