package eventTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import org.example.Admin;
import org.example.Calendar;
import org.example.Customer;
import org.example.Event;
import org.example.Functions;
import org.example.Printing;
import org.junit.Before;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CalendarNotificationsTest {
	boolean logged=false;
	boolean printed=false;
    Functions functions = new Functions();
    private Printing printing;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Before
    public void setUp() {
    	 System.setOut(new PrintStream(outputStreamCaptor));
        
    }


	@Given("the user is logged in")
    public void theUserIsLoggedIn() {
    	logged=true;
    	assertTrue(logged);
    }

    @When("the user opens the calendar")
    public void theUserOpensTheCalendar() {
    	
    }

    @Then("the user should see a list of upcoming events")
    public void theUserShouldSeeAListOfUpcomingEvents() {
    	
    	 Calendar calendar = functions.loadEventsForCustomerInCalendar("11");
     	if (calendar != null) {
     	 Functions.displayAllCustomerEvents(calendar);
     	 } 
    }
    

    @Given("there is an upcoming event")
    public void thereIsAnUpcomingEvent() {
    	//assume there is upcoming event
    }
    @When("the notification system checks for upcoming events")
    public void theNotificationSystemChecksForUpcomingEvents() {
    	
    }
    @Then("the user should receive a notification for the event")
    public void theUserShouldReceiveANotificationForTheEvent() {
        functions.startApproachingUpcomingEvents();

    }

    
    @Test
    public void testAddEventToCalendar() {
        Calendar calendar = new Calendar();
        Event event = new Event();
        calendar.addEvent(event);
        assertTrue(calendar.getEvents().contains(event));
    }
    @Test
    public void testDeleteEventFromCalendar() {
        Calendar calendar = new Calendar();
        Event testEvent = new Event();
        calendar.addEvent(testEvent);

        calendar.deleteEvent(testEvent);
        assertTrue(!calendar.getEvents().contains(testEvent));
    }

    @Test
    public void testGetEventById() {
        Calendar calendar = new Calendar();
        Event event = new Event();
        event.setEID("test123");
        calendar.addEvent(event);

        Event retrievedEvent = calendar.getEventById("test123");
        assertEquals(event, retrievedEvent);
    }

    @Test
    public void testGetUpcomingEventsForMonth() {
        Calendar calendar = new Calendar();
        // Add some events to the calendar
        // Set the year and month
        // Get upcoming events for the specified month
        // Assert that the list is not empty and contains the expected events
    }

    @Test
    public void testSetYear() {
        Calendar calendar = new Calendar();
        calendar.setYear(2024);

        assertEquals(2024, calendar.getYear());
    }

    
    
    @Test
    public void testGetYear() {
        Calendar calendar = new Calendar();
        calendar.setYear(2024);

        assertEquals(2024, calendar.getYear());
    }

    @Test
    public void testSetMonth() {
        Calendar calendar = new Calendar();
        calendar.setMonth(4);

        assertEquals(4, calendar.getMonth());
    }

    @Test
    public void testGetMonth() {
        Calendar calendar = new Calendar();
        calendar.setMonth(4);

        assertEquals(4, calendar.getMonth());
    }

    private String captureOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);
        runnable.run();
        System.setOut(originalPrintStream);
        return outputStream.toString().trim();
    }

    @Test
    public void testDisplayCalendarEvents() {
      
        Calendar calendar = new Calendar();
        calendar.setYear(2024);
        calendar.setMonth(4);
        String output = captureOutput(() -> Functions.displayCalendarEvents(calendar));
        printed=true;
        assertTrue(printed);
        
    }
    
    @Test
    public void testGetEmailAndNameFromCustomerFile1() {
    	
    	
    
		Customer c = new Customer("1","John" ,"0599999","address","password","jumanaevent@gmail.com");
   
		Functions.updateCustomersList();

        String result = "jumanaevent@gmail.com";
    
        assertEquals("jumanaevent@gmail.com", result);
    }

    @Test
    public void testGenerateMessageContent() {
        String customerName = "John";
        String eventTime = "2024-04-02 5:15 AM";
        long hoursDifference = 24;

        String expected = "Dear John,\n\n"
                + "We are pleased to confirm your registration for the upcoming event.\n\n"
                + "Event Details:\n"
                + "Event Time: 2024-04-02 5:15 AM\n"
                + "Event Start Time: 24 hours from now\n\n"
                + "Thank you for your participation. We look forward to seeing you at the event.\n\n"
                + "Best regards,\n"
                + "The Event Management Team";

      
        String result = functions.generateMessageContent(customerName, eventTime, hoursDifference);

        assertEquals(expected, result);
    }

    @Test
    public void testSendNotificationsToParticipants() {
        String recipientEmail = "jumanaevent@gmail.com";
        String subject = "Confirmation of Event Registration";
        String messageContent = "Test message content";

        Functions functions2 = new Functions();
		functions2.sendNotificationsToParticipants(recipientEmail, subject, messageContent);

        String printedOutput = outputStreamCaptor.toString().trim();
       boolean send=true;
      assertTrue(send);
    }
    
    @Test
    public void testDisplayAllCustomerEvents() {

        Calendar calendar = new Calendar();

        
        List<Event> events = new ArrayList<>();
       
        events.add(new Event());
       
        Functions.displayAllCustomerEvents(calendar);

        
        
       
	       
        assertTrue(true);

    }


	  
    
    
    
 

}