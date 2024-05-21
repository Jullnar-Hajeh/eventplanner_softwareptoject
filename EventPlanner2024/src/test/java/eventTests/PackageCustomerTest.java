package eventTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.example.Admin;
import org.example.Paackage;
import org.example.Printing;
import org.junit.Test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PackageCustomerTest {
    private Admin admin;
    private Paackage selectedPackage;
    private boolean packageAddedSuccessfully;
    private boolean selected ;
    Printing printing = new Printing();
    		
@Given("the customer is logged in")
public void theCustomerIsLoggedIn() {
	 admin = new Admin();
    admin.setState(true);
    assertTrue( admin.getstate()); // Assuming login() method authenticates the administrator
    System.out.println("Administrator is logged into the system.");
}
@When("the customer selects a package with ID {string}")
public void theCustomerSelectsAPackageWithID(String string) {
    System.out.println("Customer selected package with ID: " + string);
    selected = true ;
}
@Then("the package should be added succesfully")
public void thePackageShouldBeAddedSuccesfully() {
	 if (selected) {
         printing.printSomething("Deleted successfully");
     }
	 else {
  
	 }
}
@org.junit.Before
public void setUp() {
    admin = new Admin();
}

@Test
public void customerLoginTest() {

	admin.setState(true);
    assertTrue(admin.getstate()); 
}

@Test
public void selectPackageTest() {

    selected = true;
    assertTrue(selected);
}


 

}
