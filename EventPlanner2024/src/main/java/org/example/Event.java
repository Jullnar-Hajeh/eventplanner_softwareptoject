
package org.example;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class Event {
    private String userId;
    private String name;
    private Date date;
    private String time;
    private String description;
    private String attendeeCount;
    private String theme;
    private String category;
    private String eventId;
    private String venueName;
    private List<String> serviceIds = new ArrayList<>();
    private static final String COMMA_WITH_WHITESPACE_REGEX = ",\\s*";
    private static final String FORMAT = "yyyy-MM-dd";
    private static final String ERROR_PREFIX = "An error occurred: ";
    static final String VENUE_FILE_NAME = "venue.txt";
    static final String VENUEBOOK_FILE_NAME = "venuebook.txt";   
    static Scanner scannerr = new Scanner(System.in);
    static Printing printing = new Printing();
    
    
   
   
    public  Event() { }
    
    public Event(String eventName, Date eventDate, String eventTime, String eventDescription, String eventAttendeeCount, String userId, String eventTheme, String eventCategory, String venueName, String eventId) {
        this.userId = userId; 
        this.name = eventName;
        this.date = eventDate;
        this.time = eventTime;
        this.description = eventDescription;
        this.attendeeCount = eventAttendeeCount;
        this.category = eventCategory;
        this.theme = eventTheme;
        this.eventId = eventId;
        this.venueName = venueName;
    }

    
    //////
    public Event(String eventName, Date eventDate, String eventTime, String eventDescription, String eventAttendeeCount, String userId, String eventTheme, String eventCategory, String venueName, List<String> serviceIds, String eventId) {
        this(eventName, eventDate, eventTime, eventDescription, eventAttendeeCount, userId, eventTheme, eventCategory, venueName, eventId);
        this.serviceIds.addAll(serviceIds);
    }

    
   
    

   
   public List<String> getServiceIds() {
       return serviceIds;
   }

   public void setServiceIds(List<String> serviceIds) {
       this.serviceIds = serviceIds;
   }
   
 /////////////////////////////////////////////////////////////////////////////////   
   public static Event getEventFromLine(String line) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
       
	   Event event = new Event();
	    String[] items = line.split(" , ");
	    if (items.length >= 11) {
	        String name = items[0];
	        Date date;
	        try {
	            date = dateFormat.parse(items[1]);
	        } catch (ParseException e) {
	        	 printing.printSomething( ERROR_PREFIX + e.getMessage());
	            return null;
	        }
	        String time = items[2];
	        String description = items[3];
	        String attendeeCount = items[4];
	        String userId = items[5];
	        String category = items[6];
	        String theme = items[7];
	        String venueName = items[8];
	        String eventId = items[9];
	        String serviceIdsString = items[10];
	        // Remove leading and trailing brackets if present
	        serviceIdsString = serviceIdsString.replaceAll("^\\[|\\]$", "");
	    	// The regex "\\s*,\\s*" is safe as it splits the input string by commas with optional whitespace.
	    // The limit of -1 ensures that trailing empty strings are not discarded.
	    String[] serviceIdsArray = serviceIdsString.split(COMMA_WITH_WHITESPACE_REGEX, -1);




	        event.setName(name);
	        event.setDate(date);
	        event.setTime(time);
	        event.setDescription(description);
	        event.setAttendeeCount(attendeeCount);
	        event.setUserId(userId);
	        event.setCategory(category);
	        event.setTheme(theme);
	        event.setVenuename(venueName);
	        event.setEID(eventId);
	        for (String serviceId : serviceIdsArray) {
	            event.addServiceId(serviceId);
	        }
	    }
	    return event;
	}

   public void addServiceId(String serviceId) {
       this.serviceIds.add(serviceId);
   }

   
    
    
   public static void addEventToFile(Event event, String filename) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
       
	   try (FileWriter eventFile = new FileWriter(filename, true)) { // Open the file in append mode
	        eventFile.write(event.getName() + " , " +
	        		dateFormat.format(event.getDate()) + " , " +
	                        event.getTime() + " , " +
	                        event.getDescription() + " , " +
	                        event.getAttendeeCount() + " , " +
	                        event.getUsrTd() + " , " +
	                        event.getCategory() + " , " +
	                        event.getTheme() + " , " +
	                        event.getVenuename() + " , " +
	                        event.getEID() + " , ");

	        List<String> otherServiceIds = event.getServiceIds();
	        StringBuilder formattedServiceIds = new StringBuilder("[");
	        for (int i = 0; i <  otherServiceIds.size(); i++) {
	            formattedServiceIds.append( otherServiceIds.get(i));
	            if (i <  otherServiceIds.size() - 1) {
	                formattedServiceIds.append(", ");
	            }
	        }
	        formattedServiceIds.append("]\n");
	        eventFile.write(formattedServiceIds.toString());
	    } catch (IOException e) {
	        printing.printSomething( ERROR_PREFIX + e.getMessage());
	    }
	}




    public static Event findeventID(String eventId2, String filename) throws IOException {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
           
            while ((line = reader.readLine()) != null) {
                Event e = getEventFromLine(line);
                if (e != null) {
                    String eventid = e.getEID();
                    if (eventid != null && eventid.equals(eventId2)) {
                        return e;
                    }
                }
                
               
            }
        }
		return null;

       
    }

 
	 	
     public void deleteEvent(String eventFileName, String eventId2) throws IOException {
       String venueBookFileName = VENUEBOOK_FILE_NAME;
    	StringBuilder eventFileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(eventFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(" , ");
                if (items.length >= 11) {
                    String eventid = items[9];
                    if (!eventid.equals(eventId2)) {
                        eventFileContent.append(line).append("\n");
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(eventFileName, false)) {
            fos.write(eventFileContent.toString().getBytes());
        }

        StringBuilder venueBookFileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                if (items.length >= 5) {
                    String eventID = items[4].trim(); // Trim to remove any leading/trailing spaces
                    if (!eventID.equals(eventId2)) {
                        venueBookFileContent.append(line).append("\n");
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(venueBookFileName, false)) {
            fos.write(venueBookFileContent.toString().getBytes());
        }
        Functions.deleteInvoice(eventId2);
        Functions.deleteVenueBooking(eventId2, venueBookFileName);
    }
       
///////////////////////////////////////////////////////////////////////////////////////////////////// 
   
	   
    

   	public static void searchEvent(String customerId, String searchTerm, int searchFieldIndex) {
     	    String filename = "event.txt";

     	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
     	        String line;
     	        boolean eventFound = false;
     	        while ((line = reader.readLine()) != null) {
     	            String[] fields = line.split(COMMA_WITH_WHITESPACE_REGEX);

     	            if (fields.length >= 9 && fields[5].trim().equals(customerId.trim()) && fields[searchFieldIndex].trim().equals(searchTerm)) {
     	            	printing.printSomething(line);
     	                eventFound = true;
     	            }
     	        }
     	        if (!eventFound) {
     	            if (searchFieldIndex == 0) {
     	            	printing.printSomething("There is no event with this Name");
     	            } else if (searchFieldIndex == 8) {
     	            	printing.printSomething("There is no event with this Venue");
     	            }
     	        }
     	    } catch (IOException e) {
     	    	  printing.printSomething( ERROR_PREFIX + e.getMessage());
     	    	  }
     	}
   	

///////////////////////////////////////////////////////////////////////////////////////////////////// 
  
	 
	 
	 
	 
	 
	 
	 
	  
	 
    
    
    
  
///////////////////////// /////////////////////////////////////////////////////////////////////////////////////////////////
   public static void printUpdateList(Event eventUpdate) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
       
	   printing.printSomething(Printing.ANSI_MAGENTA + // Set text color to magenta
        	    '\n' +
        	    "UserID: " + eventUpdate.getUsrTd() + '\n' +
        	    "Event ID: " + eventUpdate.eventId + '\n' +
        	    "1. Name: " + eventUpdate.name + '\n' +
        	    "2. Date: " + dateFormat.format(eventUpdate.date) + '\n' +
        	    "3. Time: " + eventUpdate.time + '\n' +
        	    "4. Description: " + eventUpdate.description + '\n' +
        	    "5. Attendee Count: " + eventUpdate.attendeeCount + '\n' +
        	    "6. Theme: " + eventUpdate.theme + '\n' +
        	    "7. Category: " + eventUpdate.category + '\n' +
        	    "8. Venue Name: " + eventUpdate.venueName + '\n' +
        	    "9. Service IDs: " + eventUpdate.getServiceIds() + '\n'
        	    + Printing.ANSI_RED // Reset text color to default
        	    );} 
	
    public Event updateEvent(String eventidd, String filename) throws IOException,NullPointerException{
       Event eventToUpdated = findeventID(eventidd, filename);

        if (eventToUpdated != null && eventToUpdated.getEID() != null) {
            printUpdateList(eventToUpdated);
             printing.printSomething ("\nEnter the number of the field you want to update: ");
             Scanner scanner = new Scanner(System.in);
             String choice = scanner.next();


            switch (choice) {
                case "1":
                    updateEventName(eventToUpdated);
                    break;
                case "2":
                    updateEventDate(eventToUpdated,eventidd);
                    break;
                case "3":
                    updateEventTime(eventToUpdated);
                    break;
                case "4":
                    updateEventDescription(eventToUpdated);
                    break;
                case "5":
                    updateEventAttendeeCount(eventToUpdated);
                    break;
                case "6":
                    updateEventTheme(eventToUpdated);
                    break;
                case "7":
                    updateEventCategory(eventToUpdated);
                    break;
                case "8":
                    updateEventVenue(eventidd,eventToUpdated);
                    break;
                case "9":
                    updateEventServices(eventToUpdated);
                    break;
                default:
                    printing.printSomething("Invalid choice.");
            }
        } else {
            printing.printSomething("Event not found or event ID is null.");
        }

        updateEventInFile(eventToUpdated,filename);
        return eventToUpdated;
    }
/////////////////////////////////////////////////////////////////////
   

    public static Event updateEventName(Event eventt) {
        printing.printSomething("Enter new event name:");
        Scanner scannerN = new Scanner(System.in);
       String newName=scannerN.next();
        eventt.setName(newName);
        return eventt;
    }


    public static Event updateEventDate(Event eventt,String eventidd) {
    	
    	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
         printing.printSomething("Enter new event date (yyyy-MM-dd):");
    	 Scanner scannerD = new Scanner(System.in);
    	 String dateInput = scannerD.next();
         Date newdate;
         try {
             newdate = dateFormat.parse(dateInput);
         } catch (ParseException e) {
        	 printing.printSomething( ERROR_PREFIX + e.getMessage());
             return null;
         }
         eventt.setDate(newdate);
         Venue.updateVenueInVenueBook(eventidd, newdate, VENUEBOOK_FILE_NAME);
         return eventt;
    }
   

    public static Event updateEventTime(Event eventt) {
        printing.printSomething("Enter new event time(HH:MM):");
        Scanner scannerTime = new Scanner(System.in);
        String newTime = scannerTime.next();
        printing.printSomething("Enter 'am' or 'pm':");
        String amPmChoice = scannerTime.next().toUpperCase();
        String formattedTime = newTime + " " + amPmChoice;

       eventt.setTime(formattedTime);
        return eventt;
    }

    public static Event updateEventDescription(Event eventt) {
        printing.printSomething("Enter new event description:");
        Scanner scannerDe = new Scanner(System.in);
        String newDe=scannerDe.next();
        eventt.setDescription(newDe);
        return eventt;
    }

    public static Event updateEventAttendeeCount(Event eventt) {
        printing.printSomething("Enter new event attendee count:");
        Scanner scannerCount = new Scanner(System.in);
        String newCount=scannerCount.next();
        eventt.setAttendeeCount(newCount);
        return eventt;
    }

    public static Event updateEventTheme(Event eventt) {
        printing.printSomething("Enter new event theme:");
        Scanner scannerTheme = new Scanner(System.in);
        String newTheme=scannerTheme.next();
        eventt.setTheme(newTheme);
        return eventt;
    }

    public static Event updateEventCategory(Event eventt) {
        printing.printSomething("Enter new event category:");
        Scanner scannerCat = new Scanner(System.in);
        String newCat=scannerCat.next();
        eventt.setCategory(newCat);
        return eventt;
    }
    public static Event updateEventServices(Event eventt) {
    	Functions.updateServiceList();
    	Functions.viewallservice();
        printing.printSomething("\nEnter new service IDs (comma-separated):\n");
        Scanner scannerServ = new Scanner(System.in);
        String newServ=scannerServ.next();
        String serviceIdsInput =newServ;
   
    List<String> serviceIds2 = Arrays.asList(serviceIdsInput.split(COMMA_WITH_WHITESPACE_REGEX));


        eventt.setServiceIds(serviceIds2);
		return eventt;
        
    }
 
    
    public  void updateEventVenue(String eventidd,Event eventt) throws IOException,NullPointerException, NumberFormatException{
    	Functions.viewAllVenuesCustomer(VENUE_FILE_NAME);
        
        SimpleDateFormat dateFormatV = new SimpleDateFormat(FORMAT);
        String eventDateString = dateFormatV.format(eventt.getDate());
       boolean venueAvailable=false;
        
        do {
            printing.printSomething("Enter Venue name:");
            Scanner scannerN = new Scanner(System.in);
            venueName = scannerN.next();
            if (Functions.checkAvailability(venueName,eventDateString )) {
                if (Integer.parseInt(eventt.getAttendeeCount()) <= Functions.getVenueCapacity(venueName)) {
                    venueAvailable = true;
                    eventt.setVenuename(venueName);
                } else {
                    printing.printSomething("The attendee count exceeds the capacity of the venue. Please choose another venue.\n");
                }
            } 
        } while (!venueAvailable );

        
        eventt.setVenuename(venueName);
       Venue.updateVenueInVenueBook(eventidd, venueName,VENUEBOOK_FILE_NAME);
          }
//////////////////////////////////////////////////////////
    public static void updateEventInFile(Event event2, String filename) throws IOException, NullPointerException {
        // Check if event2 is null
        if (event2 == null) {
            // Handle the null case (printing an error message, throwing an exception, etc.)
            printing.printSomething(ERROR_PREFIX + "Event to update is null.");
            return;
        }

        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Event event1 = getEventFromLine(line);
                if (event1 != null) {
                    if (event1.getEID() != null && event1.getEID().equals(event2.getEID())) {
                        event1.setDate(event2.getDate());
                        // Replace the old event with the updated one
                        events.add(event2);
                    } else {
                        events.add(event1);
                    }
                }
            }
        }

        // Write all events back to the file
        try (FileWriter writer = new FileWriter(filename, false)) {
            for (Event e : events) {
                addEventToFile(e, "event.txt");
            }
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
    }


    
 /////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public  Date getDate() {
    return date;
}

public void setDate(Date date) {
    this.date = date;
}

public String getTime() {
    return time;
}

public void setTime(String time) {
    this.time = time;
}


public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String  getAttendeeCount() {
    return attendeeCount;
}

public void setAttendeeCount(String attendeeCount) {
    this.attendeeCount = attendeeCount;
}

public String getUsrTd() {
    return userId;
}

public void setUserId(String userId) {
    this.userId = userId;
}
       
public String getEID() {
    return eventId;
}

public void setEID(String eventid2) {
    this.eventId = eventid2;
}

public String getTheme() {
    return theme;
}

public void setTheme(String theme) {
    this.theme = theme;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}

public void setVenuename(String n) {this.venueName=n; }
public String getVenuename(){return venueName;}



public String toString2() {
	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
     
	StringBuilder sb = new StringBuilder();
    sb.append(printing.ANSI_GRAY); // Set text color to cyan
    sb.append("Event Details:\n");
    sb.append(printing.ANSI_YELLOW); // Set text color to yellow for attribute names
    sb.append("- UserID: ").append(userId).append("\n");
    sb.append("- Name: ").append(name).append("\n");
    sb.append("- Date: ").append(dateFormat.format(date)).append("\n");
    sb.append("- Time: ").append(time).append("\n");
    sb.append("- Description: ").append(description).append("\n");
    sb.append("- Attendee Count: ").append(attendeeCount).append("\n");
    sb.append("- Theme: ").append(theme).append("\n");
    sb.append("- Venue name: ").append(venueName).append("\n");
    sb.append("- Category: ").append(category).append("\n");
    sb.append("- Event ID: ").append(eventId).append("\n");
   sb.append("- Service IDs: ").append(String.join(", ", serviceIds)).append("\n"); // Moved service IDs to the end
    
   Functions. updateServiceList();
   for (String serviceId : serviceIds) {
     serviceId = serviceId.replaceAll("[\\[\\]]", "");

       
        for (Service service : Functions.serviceDetails) {
        	if (serviceId.equalsIgnoreCase("[No service]")) {
        		sb.append("No service");
        	    break;
        	}
        	
            if (service.getServiceID().equals(serviceId)) {
            	sb.append("- Service Names: ").append(service.getServiceName()).append(".");
                break;
            }
        }
    }
   sb.append(Printing.ANSI_RESET); // Reset text color
   return sb.toString();
}



public String toString() {
	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
     
	StringBuilder sb = new StringBuilder();
   sb.append(Printing.ANSI_MAGENTA); // Set text color to magenta
    sb.append("Name: ").append(name).append(", ")
      .append("Date: ").append(dateFormat.format(date)).append(", ")
      .append("User ID: ").append(userId).append(", ")
      .append("Event ID: ").append(eventId).append("."); // Moved event ID to the end
     sb.append(Printing.ANSI_RESET); // Reset text color

    return sb.toString();
}

public LocalDate getDateAsLocalDate() {
    // Assuming 'date' is your java.util.Date object
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault(); // Or specify the desired time zone
   return instant.atZone(zoneId).toLocalDate();
}


public String getDateTime() {
    return date + " " + time;
}

 
    
    
    
    
}
    
    
    
    
    
    
    
    
    
    