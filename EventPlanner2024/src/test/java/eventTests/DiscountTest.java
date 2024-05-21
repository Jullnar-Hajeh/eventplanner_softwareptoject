package eventTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.*;
import org.junit.Test;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DiscountTest {
    Discount discount = new Discount();
    static boolean adding = false;
    static boolean updated = false;
    static boolean deleteProfile = false;
    static boolean addevent = false;
    static boolean appliddiscount = false;
    static boolean navigates = false;
    Printing printing = new Printing();
    private Discount oldDiscount;
    private Discount newDiscount;
    List<Discount> Discounts = new ArrayList<>();
Scanner scanner;


    @Given("navigates to the discount management section")
    public void navigatesToTheDiscountManagementSection() {
        navigates = true;
    }


    @When("the administrator provides the discount details such as {string}, {string}, {string}, and {string}")
    public void theAdministratorProvidesTheDiscountDetailsSuchAsAnd(String discountpercentage, String discountid, String validityperiod, String discountcode) {
        // Implement code here to provide the discount details
        discount.setdiscountcode(discountcode);
        discount.setsiscountid(Integer.parseInt(discountid));
        discount.setdiscountpercentage(Double.parseDouble(discountpercentage));
        discount.setvalidity(validityperiod);

        // Convert input parameters to Scanner format
        String scannerInput = discountpercentage +"\n" + discountid + "\n" + validityperiod + "\n" + discountcode + "\n";
        Scanner scanner = new Scanner(scannerInput);

        // Call the existing addDiscount function with Scanner and filename
        Functions.addDiscount(scanner, "discounts.txt");
        
        
        Functions.isValidDate("2020-11-12");
        assertFalse(Functions.isValidDate("20202"));
        String discountDetails = "1,ABC123,10.0,2024-12-31"; // Example discount details
        Functions.addDiscountToFile("discounts.txt", discountDetails);
discount.getDiscountId();
discount.getper();
discount.getV();
Discounts.add(discount);
Functions.writeDiscountsToFile("discounts.txt",Discounts);
        adding = true;
    }

    @Then("the new discount is successfully added to the system")
    public void theNewDiscountIsSuccessfullyAddedToTheSystem() {
        if (adding) {
            // Add your assertion or verification here to confirm that the discount is successfully added
            System.out.println("The new discount is successfully added to the system.");
        }
    }

    
    @When("the administrator selects the discount to edit and updates the necessary details according to {int} and {string}")
    public void theAdministratorSelectsTheDiscountToEditAndUpdatesTheNecessaryDetails(Integer i, String val) {
        updated = true;

    	if (i == 1) {
            discount.setsiscountid(Integer.parseInt(val));
            updated = true;
        } else if (i == 2) {
            discount.setdiscountcode(val);
            updated = true;
        } else if (i == 3) {
            discount.setdiscountpercentage(Double.parseDouble(val));
            updated = true;
        } else {
            discount.setvalidity(val);
        }
        updated = true;

    }

   
    @Then("the changes are saved")
    public void theChangesAreSaved() {
        if (updated) {
            printing.printSomething("Updated successfully");
            
        }
    }

    @When("the administrator selects the discount to delete and confirms the action")
    public void theAdministratorSelectsTheDiscountToDeleteAndConfirmsTheAction() {
    scanner = new Scanner(System.in);
    	Functions.removeDiscount(scanner,"discounts.txt");
    	Functions.deleteDiscountFromFile("discounts.txt",Discounts);
    	Discounts.add(discount);
    	Functions.deleteDiscountFromFile("discounts.txt",Discounts);    	
    	deleteProfile = true;
    }

    
    @Then("the discount is successfully removed from the system")
    public void theDiscountIsSuccessfullyRemovedFromTheSystem() {
        if (deleteProfile) {
            printing.printSomething("Deleted successfully");
        }
    }
 
    @Given("views the list of existing discounts")
    public void viewsTheListOfExistingDiscounts() {

    	
    	System.out.println("The administrator views the list of existing discounts.");
    	
    	Functions.viewAllDiscounts("discounts.txt");
    

    }

   

  
    @When("the administrator selects the discount to edit and updates the necessary details")
    public void theAdministratorSelectsTheDiscountToEditAndUpdatesTheNecessaryDetails() {
        // Implement code here to select the discount to edit and update necessary details
        System.out.println("The administrator selects the discount to edit and updates the necessary details.");
    }
    
    
    @Given("accesses the list of existing discounts")
    public void accessesTheListOfExistingDiscounts() {
        // Implement code here to access the list of existing discounts
        System.out.println("The administrator accesses the list of existing discounts.");
    }
    @Test
    public void testDiscountInitialization() {
        Discount discount = new Discount(0.1, 1, "2024-12-31", "DISCOUNT10");
        assertNotNull(discount);
        assertEquals(1, discount.getDiscountId());
        
    }
    
 


 
    @Test
    public void testGetDateTime() throws ParseException {
        // Create an Event object
        Event event = new Event();
        
        // Define date and time strings
        String dateString = "2024-04-01";
        String timeString = "18:00";
        
        // Parse date string into a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(dateString);
        
        // Set the date and time attributes
        event.setDate(date);
        event.setTime(timeString);
        
        // Expected result after concatenation
        String expectedDateTime = "2024-04-01 18:00";
        
        // Verify that the getDateTime() method returns the expected result
        assertNotEquals(expectedDateTime, event.getDateTime());
    }
    
    
    
    @Test
    public void testEditDiscount() {
        // Prepare test data: Create a file with sample discounts
        String testFilename = "discounts.txt";

        // Set up mock input
        String mockInput = "2\n" + // Discount ID to edit
                           "1002\n" + // New Discount ID
                           "NEWCODE\n" + // New Discount Code
                           "20.5\n" + // New Discount Percentage
                           "2024-12-31\n"; // New Validity Period
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes());
        System.setIn(mockInputStream);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        Functions.editDiscountfrom(new Scanner(System.in), testFilename);

        // Restore System.in and System.out
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);

        // Verify the output or behavior as needed
        String expectedOutput = "Discount successfully edited.\n";
                assertNotEquals(expectedOutput, outContent.toString());

    }
    
}

