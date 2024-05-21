package eventTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;



import org.example.Event;
import org.example.Functions;
import org.junit.Test;
import org.junit.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class ViewTest {

boolean show_Adminlist ,
show_ListOfEventsforAdmin,
show_allcustomers,
show_VenueforAdmin,
show_allProviders,
show_customerlist,
show_eventsofCustomer,
show_Venueforcustomer,
show_providerlist,
show_mangepages;

Functions F;
private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
 @Before
public void setUp() throws ParseException {
    F = new Functions();
    Event e = new Event("Conference", DATE_FORMAT.parse("2024-03-15"), "9:00 AM", "Tech conference on AI and Machine Learning", "200", "admin123", "Tech", "Conference", "V1", "111");
    assertNotNull(e);
 
 }
 


    @Test
	@Given("the Administrator has selected show Adminlist")
	public void theAdministratorHasSelectedShowAdminlist() {
         show_Adminlist =true;
		assertTrue(show_Adminlist);
	
	}
	@Then("the list Adminlist  displayed")
	public void theListAdminlistDisplayed() throws IOException, Exception {
    assertTrue(show_Adminlist); Functions.adminList();
  
	}
//////////////////////////////////////////////////////////////////


	@Given("the Administrator has selected show customar")
	public void theAdministratorHasSelectedShowCustomar() {
	 show_allcustomers =true;
	 
	 
	}
	@Then("the list of users  displayed")
	public void theListOfUsersDisplayed() {
		 assertTrue(show_allcustomers); 
		 F.viewCustomer();               ////////////////////////////////////////////////////////////////////
	}


/////////////////////////////////////////////////////////////////////////////////
	
	@Given("the Administrator has selected show events")
	public void theAdministratorHasSelectedShowEvents() {
		show_ListOfEventsforAdmin =true;
	}
	@Then("the list of events  displayed")
	public void theListOfEventsDisplayed() {
		 
		assertTrue(show_ListOfEventsforAdmin); F.viewalleventsforAdmin("event.txt");              
	}


/////////////////////////////////////////////////////////////////////////////////


	@Given("the Administrator has selected show venues")
	public void theAdministratorHasSelectedShowVenues() {
		show_VenueforAdmin =true;
   }
	@Then("the list of venues  displayed")
	public void theListOfVenuesDisplayed() {
		 assertTrue(show_VenueforAdmin); F.viewAllVenues("venue.txt");    /////////////////////////////////////////////////////          
	}

/////////////////////////////////////////////////////////////////////////////////

	@Given("the Administrator has selected show providers")
	public void theAdministratorHasSelectedShowProviders() {
	
	show_allProviders=true;
	}
	@Then("the list of  providers  displayed")
	public void theListOfProvidersDisplayed() {
		 assertTrue(show_allProviders); F. viewallprovider("provider.txt");            
			
	}

/////////////////////////////////////////////////////////////////////////////////


	@Given("the User has selected show Userlist")
	public void theUserHasSelectedShowUserlist() {

	show_customerlist=true;
	}
	@Then("the list Userlist  displayed")
	public void theListUserlistDisplayed() {
		 assertTrue(show_customerlist); Functions.customerPageList();
	}

/////////////////////////////////////////////////////////////////////////////////

	@Given("the User has selected show Myevents")
	public void theUserHasSelectedShowMyevents() {
	show_eventsofCustomer=true;
	}
	@Then("the list of Myevents  displayed")
	public void theListOfMyeventsDisplayed() throws Exception {
		 assertTrue(show_eventsofCustomer);//.viewCostomerevents();         //////////////////////////////
			Functions.viewCostomerevents("admin123","event.txt" );
	}


//////////////////////////////////////////////////////////////////////////////////


	@Given("the USer has selected show available venues")
	public void theUSerHasSelectedShowAvailableVenues() {
		show_Venueforcustomer=true;
		}
	@Then("the list of available venues  displayed")
	public void theListOfAvailableVenuesDisplayed() {
		 assertTrue(show_Venueforcustomer); F.viewAllVenuesCustomer("venue.txt");         
			}

	
	
	
//////////////////////////////////////////////////////////////////////////////////

	

/////////////////////////////////////////////////////////////////////////////////


	@Given("the provider has selected show  providerlist")
	public void theProviderHasSelectedShowProviderlist() {
	show_providerlist=true;
	
	}
	@Then("the list  providerlist  displayed")
	public void theListProviderlistDisplayed() {
		 assertTrue(show_providerlist); F.providerPageList();              

	}
///////////////////////////////////////////////////////////////////////////////
	
	@Given("the Administrator has selected show Mangementpages")
	public void theAdministratorHasSelectedShowMangementpages() {
     show_mangepages=true;}
	@Then("the pages displayed")
	public void thePagesDisplayed() {
		 assertTrue(show_mangepages);
		 F.signInPageList();
		 F.eventManagementAdminPageList();
		 F.discountManagementadminList();
		 F.userManagementAdminPageList();
		 F.userSearchPageList();
		 F.venueManagementadminList();
		 F.providerManagementAdminPageList();
		 F.packageManagementadminList();
		
	
	}







}
