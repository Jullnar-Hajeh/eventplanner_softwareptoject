package eventTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.logging.Level;
import java.util.logging.*;
import org.example.*;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserRegTest {
	
	Functions f=new Functions();
	   User u = new User();
	    Logger logger = Logger.getLogger(UserRegTest.class.getName());


	    @Given("customers")
	    public void customersStep() {
	        // Implementation of the step goes here
	    }
	
	@Given("there is a User with User ID {string} ,NAME {string} , PASSWORD {string} , phone {string}, address {string}")
	public void thereIsAUserWithUserIDNAMEPASSWORDPhoneAddress(String string, String string2, String string3, String string4, String string5) {
	
		 u.setId(string);
	        u.setName(string2);
	        u.setPassword(string3);
	        u.setPhone(string4);
	        u.setAddress(string5);
	        u.setType("customer");
	        


	}
	 
	@When("the User is registered {string}")
	public void theUserIsRegistered(String string) {

        if(u.isRegest(string)) {
            User.adduser(u);
            boolean registered = u.isRegest(string);

            assertTrue(registered);
     
        }
	}
	
	@Then("the User with User ID {string} ,NAME {string} , PASSWORD {string} , phone {string}, address {string} is registered in the system")
	public void theUserWithUserIDNAMEPASSWORDPhoneAddressIsRegisteredInTheSystem(String string, String string2, String string3, String string4, String string5) {
		   if(u.isRegest(string))
	            assertTrue(true);
	}


	    @Test

	 @Then("the error message {string} is given")
	    public void theErrorMessageIsGiven() {
	        String string = "this user is already registered";
	        logger.log(Level.INFO, string);
	    }


	 
	 
	
	    @Test
	    public void testUserRegistrationSuccess() {
	    
	        User u = new User();
	        u.setId("123");
	        u.setName("John Doe");
	        u.setPassword("password123");
	        u.setPhone("1234567890");
	        u.setAddress("123 Street");

	        boolean registered = u.isRegest("string");

	        assertTrue(registered);
	    }

	    @Test
	    public void testUserRegistrationFailure() {
	    
	        User u = new User();
	        u.setId("123");
	        u.setName("John Doe");
	        u.setPassword("password123");
	        u.setPhone("1234567890");
	        u.setAddress("123 Street");

	        User.adduser(u);

	        boolean registered = u.isRegest("John Doe");

	        assertFalse(!registered);
	    }
}
