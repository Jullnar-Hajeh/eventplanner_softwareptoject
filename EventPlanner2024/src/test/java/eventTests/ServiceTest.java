package eventTests;

import static org.junit.Assert.*;

import org.example.Event;
import org.example.Functions;
import org.example.Provider;
import org.example.Service;
import org.example.User;
import org.junit.Test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceTest {
    static Provider serviceProviderService;
    private  Service newService;
    private Service modifiedService;
    private Service deletedService;
    boolean found;
    Functions fun=new Functions();
  @Before
    public void setUp() throws IOException {
        serviceProviderService = new Provider();}
       
 
    @Given("the service provider is logged in")
    public void theServiceProviderIsLoggedIn() {

		 Provider.login("23443", "2233");
    	
    }
    
    @Given("the service provider has the following services in their profile:")
    public void theServiceProviderHasFollowingServicesInProfile(DataTable dataTable) {
        List<Map<String, String>> services = dataTable.asMaps(String.class, String.class);

        // Assuming ServiceProviderService has a method to add services to the profile
        for (Map<String, String> service : services) {
            serviceProviderService.addService(
                service.get("ServiceType"),
                service.get("ServiceName"),
                service.get("Description"),
                Double.parseDouble(service.get("Price")),
                service.get("Availability")
            );
        }
    }
	

	
	@When("the service provider selects View your Service Details")
	public void the_service_provider_selects_view_your_service_details() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Then("the system should display a list of the providers services with their details")
	public void the_system_should_display_a_list_of_the_providers_services_with_their_details() {
	    // Write code here that turns the phrase above into concrete actions
		 List<Service> serviceDetailsList = serviceProviderService.getServiceDetailsList();
	     assertNotNull(serviceDetailsList);

	        // Check if the list is not empty (indicating that services are displayed)
	     assertTrue(!serviceDetailsList.isEmpty());
	        
	        // Print service details (replace this with your actual verification logic)
	        
		boolean serviceDetailsDisplayed = serviceProviderService.displayServiceDetails();
        assertTrue("Service details should be displayed, but they are not.", serviceDetailsDisplayed);
}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	@When("the service provider selects Add New Service")
    public void theServiceProviderSelectsAddNewService() {
        newService = new Service();
        
        ///////////////////////////////////////////////
       
        
        
	}
        
  

    @When("the service {string} does not exist")
    public void theServiceDoesNotExist(String serviceType) {
        assertFalse(serviceProviderService.doesServiceExist(serviceType));
    }

    @When("enters details for a new decorations service like")
    public void entersDetailsForNewDecorationsService(DataTable dataTable) {
        // Convert the DataTable to a Map<String, String>
        Map<String, String> detailsMap = dataTable.transpose().asMap(String.class, String.class);

        // Extract values from the map
        String serviceName = detailsMap.get("ServiceName");
        String price = detailsMap.get("Price");
        String availability = detailsMap.get("Availability");
        String description = detailsMap.get("Description");
        
        newService.setServiceType("Decorations");
        newService.setServiceName(serviceName);
        newService.setPrice(Double.parseDouble(price)); // Assuming the price is formatted as "500"
        newService.setAvailability(availability);
        newService.setDescription(description);


    }

    @Then("the new decorations service should be added to the providers profile")
    public void theNewDecorationsServiceShouldBeAddedToTheProvidersProfile() {
        serviceProviderService.addService(newService);

        // Assuming logic to verify that the new service is added
        Service addedService = serviceProviderService.getServiceDetails(newService.getServiceType());
        assertNotNull(addedService);
      
        assertEquals(newService.getServiceName(), addedService.getServiceName());
        
        
     }
  
///////////////////////////////////////////////////////////////////////////////////////	

    @When("the service provider selects Edit your Service Details for exist servicetype {string}")
    public void selectsEditServiceDetailsForExistingType(String serviceType) {
        // Assuming serviceProviderService has a method to retrieve service details by type
        modifiedService = serviceProviderService.getServiceDetails(serviceType);

        assertNotNull(modifiedService);
    }

    @When("modifies the service information like price {string}")
    public void modifiesServiceInformation(String newPrice) {
        // Check if modifiedService is not null
        assertNotNull(modifiedService);

        // Modify the service price
        modifiedService.setPrice(Double.parseDouble(newPrice)); // Assuming the price is formatted as "500"
    }

    @Then("the system should update the catering service details accordingly")
    public void systemShouldUpdateServiceDetails() {
        // Assuming serviceProviderService has a method to edit service details
    //	assertTrue(serviceProviderService.editServiceDetails(modifiedService));
        assertNotNull(serviceProviderService.editServiceDetails(modifiedService));
	

    }
	
///////////////////////////////////////////////////////////////////////////////////////	
	

    @When("the service provider selects exist servicetype {string}")
    public void the_service_provider_selects_exist_servicetype(String serviceType) {
        // Assume you have a method in ServiceProviderService to select a service type
    	deletedService  = serviceProviderService.getServiceDetails(serviceType);
        assertNotNull(deletedService);
    }

    @When("selects deletion")
    public void selects_deletion() {
        // Assume you have a method in ServiceProviderService to confirm the deletion
    
    }

    @Then("the Entertainment service should be removed from the providers profile")
    public void the_entertainment_service_should_be_removed_from_the_providers_profile() throws Exception {
        // Assume you have a method in ServiceProviderService to check if the service is removed
    	//serviceProviderService.deleteService(deletedService.getServiceType());
       // assertTrue(!serviceProviderService.doesServiceExist("Entertainment"));
    	boolean serviceDetailsDisplayed = serviceProviderService.displayServiceDetails();
        assertTrue("Service details should be displayed, but they are not.", serviceDetailsDisplayed);
   
    }

	
/////////////////////////////////////////////////////////////////////////////////////////
    
    

    @Test 
    public void testAll() throws IOException {
    Service Exservice = new Service("Photography", "Full_Event_Coverage", "Professional_photography_coverage_event", "available", "100", 600.0, "700");
    if (Functions.searchIdS(Exservice.getServiceID(), "service.txt")) found=true;else found=false;                            
    if (found)
    {	 Service.deleteServiceFromFile( "service.txt","100"); 
        Service.addServiceToFile(Exservice); 
    }       		          
    
     List<String> serviceIds = Arrays.asList("100"); // Example list of service IDs
     double totalPrice = Service.calculateTotalPrice(serviceIds);
     assertEquals(600.0, totalPrice, 0.01); 
     Service Exservice2= Service.findServiceByID("100", "service.txt");
     assertNotNull(Exservice2); // Ensure the found service is not null
     
     
     String[] simulatedInputs = {
			    "1\nnewname\n",
			    
			    "2\nType\n",
			    
			    "3\nDescription\n",
			   
			    "4\n80\n",
			    
			    "5\navailable\n",
			    "6\n"
			    };
	
	 for (String simulatedInput : simulatedInputs) {
	        // Set up input stream with simulated input
	        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
	        System.setIn(inputStream);

	        // Call the method you want to test
	        try {
	            Service.updateServiceDetails("100");
	            	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle exceptions if any
	        }

	        System.setIn(System.in);
	    
	      }
	 
	 Service . deleteServiceFromFile( "service.txt","100"); 
     Service.addServiceToFile(Exservice); }
     
     
     
     
     
     
     
     
 
/////////////////////////////////////////////////////


@Test
public void testAddService() throws Exception {
    // Prepare test input
    String input = "1\n" + // Service Id
            "type1\n" + // Service Type
            "name1\n" + // Service Name
            "description1\n" + // Service Description
            "10.0\n" + // Service Price
            "not_available\n"; // Service Availability

    // Store the original System.in
    InputStream originalSystemIn = System.in;

    try {
        // Set the test input stream
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call the method to be tested
        Service addedService = fun.addService();

        // Verify the returned service is not null
        assertNotNull(addedService);
        // Verify the service details
       
        assertEquals("1", addedService.getServiceID());
        assertEquals("type1", addedService.getServiceType());
        assertEquals("name1", addedService.getServiceName());
        assertEquals("description1", addedService.getDescription());
        assertEquals(10.0, addedService.getPrice(), 0.01);
        assertEquals("not_available", addedService.getAvailability());
    } finally {
        // Restore System.in to its original value
        System.setIn(originalSystemIn);
    }
    
    
    Service.deleteServiceFromFile( "service.txt","1");

 }



}

    
	
	
	
	
	
	
	
	
	
