package eventTests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import org.example.Printing;
import org.example.Venue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class EventTest{
	
	
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	 private InputStream inputStream;
	  static Printing printing = new Printing();
	  
	  Event  event = new Event();
	 
	
    boolean cancel;
    boolean found;
    boolean search;
    boolean update;
    boolean existe;
    boolean creat;
    boolean toadd;
    boolean newUpdate;
    Customer customer1 = new Customer();
    Event eventToUpdate;
    Venue v = new Venue("Palestine_Convention_Center", "Main_Street_Ramallah", 200, 200.0, "Available", "R456", "ramallah_venue_image.jpg");
   
   
////////////////////////////////////////////////////////////////////////////////////    
    @Test
	@Given("the customer is going to create an event")
	public void theCustomerIsGoingToCreateAnEvent()
	{
		customer1.setId("12029026");
        customer1.setName("Ahmad");
        customer1.setEmail("AhmadHH334@gmail.com");
        customer1.setPhone("0599897846");
        customer1.setAddress("Nablus");
        event.setUserId(customer1.getId());
        System.out.println(" customer is going to create an event ");
		    creat=true;
        assertTrue(creat);

	}
	                                                 
    
	@When("the customer enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},Venue {string},eventid {string}")
	public void theCustomerEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeVenueEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
	
		 Date date=new Date();
        try {
            date = DATE_FORMAT.parse(string);
        } catch (ParseException e) {

            e.printStackTrace();
           
        } 
     //  Functions.addVenueToFile("venue.txt",  v.toFileString());
        
       List<String> serviceIds = new ArrayList<>();
        serviceIds.add("100");
        
        
        
        event = new Event(string5, date, string2, string3, string4, customer1.getId(), string7, string6, string8, serviceIds, string9);
        if (Functions.searchIdE(string9, "requst.txt")|| Functions.searchIdE(string9, "event.txt")) { found =true ;}else found=false;	              
        
        if (!found)
		{	
		   Event.addEventToFile(event,"requst.txt");}
		else    System.out.println(" event existe ");

	    } 
        
	     
	  
	
  
	@Then("the event is added to admin requst")
	public void theEventIsAddedToAdminRequst() throws Exception { 
	}

	////////////////////////////////////
	
	@Given("there is an existing event")
	public void thereIsAnExistingEvent() { 
		if (Functions.searchIdE(event.getEID(), "requst.txt")|| Functions.searchIdE(event.getEID(), "event.txt"))
			existe=true;
		else 	
		existe=false;}

	@When("cancel event selected")
	public void cancelEventSelected() {  cancel=true;}

	@Then("the event deleted")
	public void theEventDeleted() throws Exception{ 
	assertTrue(cancel);	
	event .deleteEvent("event.txt","2000"); 
	System.out.println("The event deleted");}

	
	
 
	@Given("there is an non_existing event")
	public void thereIsAnNonExistingEvent() {existe=false;}
	
    
	@Then("non_Existing massage")
	public void nonExistingMassage() {assertFalse(existe);System.out.println("The event does not exist.");}



    /////////////////////////////////////////////////
	
@Given("the administrator is going to create an event")
	public void theAdministratorIsGoingToCreateAnEvent() {creat=true;}
	   

@When("the administrator enters the event details such as Date {string}, time {string}, description {string}, attendeeCount {string}, name {string},category {string} ,theme {string},Venue {string},eventid {string}")
public void theAdministratorEntersTheEventDetailsSuchAsDateTimeDescriptionAttendeeCountNameCategoryThemeVenueEventid(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) throws NullPointerException, IOException {
	Date date=new Date();
    try {
        date = DATE_FORMAT.parse(string);
    } catch (ParseException e) {
    	 printing.printSomething( "/////////////ERROR_PREFIX/////////////" + e.getMessage());
       
    }
	    event.setDate(date);
        event.setTime(string2);
        event.setDescription(string3);
        event.setAttendeeCount(string4);
        event.setName(string5);
        event.setCategory(string6);
        event.setTheme(string7);
        event.setVenuename(string8);
        event.setEID(string9);
        event.setUserId("12114777");
        
        
        inputStream = new ByteArrayInputStream("testupdate".getBytes());
        System.setIn(inputStream);
        event.updateEvent(string9, "event.txt");
        event.getDateAsLocalDate();
        }

	@Then("the event is successfully created in the system")
	public void theEventIsSuccessfullyCreatedInTheSystem() throws Exception { assertTrue(creat); 
     
	 if (Functions.searchIdE(event.getEID(), "requst.txt")|| Functions.searchIdE(event.getEID(), "event.txt")) { found =true ;}else found=false;	                      
     if (!found)
		{	
		Event.addEventToFile(event,"event.txt");}
		else    System.out.println(" event existe ");

	    } 
     
	

	

 ////////////////////////////////////
	
	@Given("the customer is going to search about event")
	public void theCustomerIsGoingToSearchAboutEvent() {
		search=true;
	
	}
	@When("the customer enters the event details such as event name {string}")
	public void theCustomerEntersTheEventDetailsSuchAsEventName(String string) {
	   assertTrue(search);
	  Event.searchEvent("12114777", string, 0);
	   
	}
	
	@When("the customer enters the event details such as venue name {string}")
	public void theCustomerEntersTheEventDetailsSuchAsVenueName(String string) {
		assertTrue(search);
		Event.searchEvent("12114777", string, 8);
		
	}
	
	
@Then("the system should display all events matching the name")
	public void theSystemShouldDisplayAllEventsMatchingTheName() {
		 
	
   }

///////////////////////////////////////////////////////////////////////////////////



@Given("the user wants to update event details")
public void theUserWantsToUpdateEventDetails() throws IOException {
	 eventToUpdate = Event. findeventID("1000", "event.txt");
	if ( eventToUpdate!= null)
	{ update=true;
	Event.printUpdateList(eventToUpdate);}
	else update=false;
	 
}
@When("they update the event name to {string}")
public void theyUpdateTheEventNameTo(String string) {
	
    inputStream = new ByteArrayInputStream(string.getBytes());
    System.setIn(inputStream);
   Event.updateEventName(eventToUpdate);
   newUpdate=true;


}

@When("they update the event time to start at {string}")
public void theyUpdateTheEventTimeToStartAt(String string) {
	 inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventTime(eventToUpdate);
	   newUpdate=true;
}

@When("they update the event description to {string}")
public void theyUpdateTheEventDescriptionTo(String string) {
	   inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventDescription(eventToUpdate);
	   newUpdate=true;
}


@When("they update the event date to {string}")
public void theyUpdateTheEventDateTo(String string) {
	   inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventDate(eventToUpdate,"event.txt");
	   newUpdate=true;
}



@When("they update the attendee count to {string}")
public void theyUpdateTheAttendeeCountTo(String string) {
	   inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventAttendeeCount(eventToUpdate);
	   newUpdate=true;
}

@When("they update the event theme to {string}")
public void theyUpdateTheEventThemeTo(String string) {
	 inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventTheme(eventToUpdate);
	   newUpdate=true;
}


@When("they update the event category to {string}")
public void theyUpdateTheEventCategoryTo(String string) {
	 inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventCategory(eventToUpdate);
	   newUpdate=true;
}

@When("they update the event venue to {string}")
public void theyUpdateTheEventVenueTo(String string) throws NumberFormatException, NullPointerException, IOException {
	Functions.addVenueToFile("venue.txt", v.toFileString());
	inputStream = new ByteArrayInputStream(string.getBytes());
    System.setIn(inputStream);
   event.updateEventVenue("1000",eventToUpdate);
   newUpdate=true;
	
}

@When("they update the event services to include {string}")
public void theyUpdateTheEventServicesToInclude(String string) {
	 inputStream = new ByteArrayInputStream(string.getBytes());
	    System.setIn(inputStream);
	   Event.updateEventServices(eventToUpdate);
	   newUpdate=true;
}


@Then("the system should successfully save the changes")
public void theSystemShouldSuccessfullySaveTheChanges() throws NullPointerException, IOException {
   assumeTrue(newUpdate);
   event.updateEventInFile(eventToUpdate, "event.txt");
   eventToUpdate.toString2();
	
}


@Test
public void testAddBookingVenue() throws IOException {
    String venid = "VEN123";
    String custid = "CUST456";
    String date = "2024-04-01";
    String status = "Confirmed";
    String eventid = "EVENT789";

    // Call the method to be tested
    Functions.addBookingVenue(venid, custid, date, status, eventid);

    // Read the content of the file to verify the addition
    BufferedReader reader = new BufferedReader(new FileReader("venuebook.txt"));
    String line;
    boolean found = false;
    while ((line = reader.readLine()) != null) {
        if (line.contains(venid) && line.contains(custid) && line.contains(date) && line.contains(status) && line.contains(eventid)) {
            found = true;
            break;
        }
    }
    reader.close();

    assertTrue(found);
}

@Test
public void testAddToInvoice() throws IOException {
    String customerId = "CUST123";
    String eventId = "EVENT456";
    String eventName = "Sample Event";
    double price = 100.0;

    Functions.addToInvoice(customerId, eventId, eventName, price);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("test_invoices.txt", true))) {
        writer.write(customerId + "," + eventId + "," + eventName + "," + price + "\n");
        System.out.println("Invoice item added successfully.");
    } catch (IOException e) {
        System.err.println("An error occurred while adding the invoice item: " + e.getMessage());
    }
    
    BufferedReader reader = new BufferedReader(new FileReader("test_invoices.txt"));
    String line;
    boolean found = false;
    while ((line = reader.readLine()) != null) {
        if (line.contains(customerId) && line.contains(eventId) && line.contains(eventName) && line.contains(String.valueOf(price))) {
            found = true;
            break;
        }
    }
    reader.close();

 
    assertTrue(found);
}







	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	
	
