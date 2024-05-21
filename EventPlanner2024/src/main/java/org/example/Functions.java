package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class Functions {

	   static Printing printing = new Printing();
	    static Scanner scanner = new Scanner(System.in);
	    Customer customerObj;
	    Provider providerObj;
	    static Event eventObj;
	    static int choice;
	    int choice2;
	    static boolean found;
	    static String tmp;


	    static double price;
	    private static final String IMAGE_LABEL = " Image: ";

	    private static final String ADDRESS_LABEL = " Address: ";
	    static double pricee;
	    static Admin admin = new Admin();
	    static Paackage p = new Paackage();
	    static Customer customer1 = new Customer();
	    static Event event1=new Event();
	    static Provider provider1=new Provider();
	    public static final List<Customer> customers = new ArrayList<>();
	    public static final ArrayList<Provider> providers = new ArrayList<>();
	    private  static final ArrayList<Event> events = new ArrayList<>();
	    private static final String VENUE_ID_LABEL = "Venue ID: ";
	    private static final String NAME_LABEL = " Name: ";
	   protected static final List<Service> serviceDetails = new ArrayList<>();
	   private static final String NOT_FOUND_MESSAGE = " not found.";
	   private static final String ERROR= "Error: ";
  
	    static final String CUSTOMER_FILE_NAME = "customer.txt";
	    static final String PROVIDER_FILE_NAME = "provider.txt";
	    static final String REQUEST_FILE_NAME = "requst.txt";
	    static final String EVENT_FILE_NAME = "event.txt";
	    static final String PACKAGE_FILE_NAME = "package.txt";
	    static final String VENUE_FILE_NAME = "venue.txt";
	    static final String INVOICE_FILE_NAME ="invoice.txt" ;
	    static final String DISCOUNT_FILE_NAME = "discounts.txt";
	    static final String SERVICE_FILE_NAME = "service.txt";
	    private static final String YELLOW_TEXT_COLOR = "\033[1;33m";
	    private static final String ENTER_NAME = "Enter New Name:\n ";
	    static final String SPACE = "|                                       |";
	    private static final String ERROR_PREFIX = "An error occurred: ";
	    private static final String CAPACITY_LABEL = " Capacity: ";
	    private static final String PRICE_LABEL = " Price: ";
	    static final String EXITING = "Exiting...";

	     static final String INVALID_INPUT = "Invalid input: ";

	    static final String ENTER_CHOICE = "Enter your choice:\n ";
	    static final String ENTER_PASSWORD= "Enter Password:";
	    private static final String ACCOUNT_ALREADY_EXIST_MESSAGE = "This account is already existed, Please Sign in.\n";
	    private static final String THANK_MESSAGE = "  \nThank you! Your information has been recorded.    \nEnter a password: ";
        static final String INVALID_CHOICE = "\nInvalid choice! Please enter a valid choice.\n";

	    static final String LINE = "----------------------------------------";
	    static final String LINE_STARS="\n\n+************************************************************************************************************************************************************************+\n";
	    static final String LINE2 = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n";
	    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	     static final String ADD=" ADD                              |\n";
	    static final String VIEW_ALL=" VIEW_ALL                         |\n";
	      static final String DELETE=" DELETE                           |\n";
	        static final String EDIT=" EDIT                             |\n";
	     static final String LOG_OUT=" LOG_OUT                          |\n";
	    
	    private static final String ONE =  "|   1. ";
	    private static final String TWO =  "|   2. ";
	    private static final String THREE ="|   3. ";
	    private static final String FOUR = "|   4. ";
	    private static final String FIVE = "|   5. ";
	    private static final String SIXE = "|   6. ";
	    
	    
	    
	    
	    public static void printCustomersList() {
	        for (Customer customer : customers) {
	           printing.printSomething(""+customer);
	        }
	    }
	   
	    private static String  evenId ;
	    private static String  JId ;
		   
	    private static  String id;
	    private static  String adminId =admin.getAdminId();
	   public String customerId;
	    public String provideId;

///////////////////////////////////////////////////////////////////////////////////////
 void signInFunction() throws Exception {
    signInPageList();
    printing.printSomething(ENTER_CHOICE);
    int choice = scanner.nextInt();
    printing.printSomething("Enter Id: ");
    String id = scanner.next();
    
   
    
    switch (choice) {
        case 1:
        	
            signInAdmin(id);
            break; 
        case 2:
        	customerId=id;
            signInCustomer(customerId);
            break;
        case 3: 
        	provideId=id;
            signInProvider(provideId);
            break;
        default:
            printing.printSomething("\n" + INVALID_CHOICE);
    }
}

public void signInAdmin(String id) throws Exception {
	  printing.printSomething( ENTER_PASSWORD);
	    String password = scanner.next();
    if (id.equals(admin.getAdminId()) && password.equals(admin.getAdminPassword())) {
    	adminPage(adminId);
    } else {
        printing.printSomething("\nSomething went wrong!, Try again.");
        signInFunction();
    }
}

 void signInCustomer(String custId) throws Exception {
    boolean found = false;
    updateCustomersList(); 
    printing.printSomething( ENTER_PASSWORD);
    String password = scanner.next();
    try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] customerData = line.split(",");
            String customerIdFromFile = customerData[0].trim();
            String passwordFromFile = customerData[5].trim(); 
            if (custId.equals(customerIdFromFile) && password.equals(passwordFromFile)) {
                found = true;
                break;
            }
        }
    } catch (IOException e) {
        printing.printSomething("Error reading customer data: " + e.getMessage());
    }

    if (found) {
        while (x > 0) {
            customerPageList();
            int c = scanner.nextInt();
            customerOptions(custId,c);
        }
    } else {
        printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
        signInFunction();
    }
}

 void signInProvider(String provId) throws Exception {
	
    boolean found = false;
    updateProvidersList(); 
    printing.printSomething( ENTER_PASSWORD);
    String password = scanner.next();
    try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] providerData = line.split(",");
            String providerIdFromFile = providerData[0].trim();
            String passwordFromFile = providerData[5].trim(); 
            if (provId.equals(providerIdFromFile) && password.equals(passwordFromFile)) {
                found = true;
                break;
            }
        }
    } catch (IOException e) {
        printing.printSomething("Error reading provider data: " + e.getMessage());
    }

    if (found) {
        while (x > 0) {
            providerPageList();
            int c = scanner.nextInt();
            providerOptions(provId,c);
        }
    } else {
        printing.printSomething("\nThis account does not exist or the password is incorrect. Please check your inputs.\n");
        signInFunction(); 
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
static  int x =1;
public   void adminPage(String adminId) throws Exception{
	scanner=new Scanner(System.in);
	   while (x > 0) {
		 adminList();
	    printing.printSomething(ENTER_CHOICE);
	    int c = scanner.nextInt();

	    switch (c) {
	        case 1:
	        	 userManagementAdminPageList();
	        	 int j=scanner.nextInt();
	            switch(j) {
	            case 1:viewCustomer();
	            break;
	            case 2:viewCustomersanddelete();	    	            
	            break;
	            case 3:signInFunction();
	            break;
	            default:
	        	printing.printSomething(INVALID_CHOICE);
	            }
	            break;
	        case 2:
	        	discountManagementadminList();
	        	int l = scanner.nextInt();
	        	discountManagementOptions(l);
	            break;
	        case 3:
	        	eventManagementAdminPageList();
	        	int customerEvent=scanner.nextInt();
	        	eventManagementOptions(adminId,customerEvent);
	            break;
	        case 4:
	        	venueManagementadminList();
	        	int cusVenue=scanner.nextInt();
	        	venueManagementOptions(cusVenue);
	            break;
	         
	        case 5:
	        	providerManagementAdminPageList();
	        	int providerMan=scanner.nextInt();
	        	providerAdminManagementOptions(providerMan);
	            break;
	        case 6:
	        	packageManagementadminList();
	        	int packageMan=scanner.nextInt();
	        	packageManagementOptions(packageMan);
	            
	            break;
	        case 7:
		          viewBusinessReports();
		            break;
	        case 8:
	        	signInFunction();
	            break;
          case 0000:x=0;break;
	        
	        
	        
	        
	        default:
	        	printing.printSomething(INVALID_CHOICE);
	     	    
	    }
   }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
	  
	  public  void customerOptions(String customerId,int x) throws Exception {
	  
	        switch (x){
	        case 1:
                updateCustomersList();
                printing.printSomething("\nWhich info you want to update?\n1. Name  2.Phone  3. Address  4.Email "+"\n"+ENTER_CHOICE);
                updateCustomerProfile(scanner.nextInt());
	            break;
	            case 2:
	               updateCustomersList(); 
	               addevent(customerId,REQUEST_FILE_NAME);
	               break;
	            case 3:
	            	printing.printSomething("\n");
	                boolean f = viewCostomerevents(customerId, EVENT_FILE_NAME);
	                if (f) {
	                    boolean continueUpdating = true;
	                    while (continueUpdating) {
	                        printing.printSomething("\nPlease enter the Event ID you want to update (or enter 'done' to finish):\n");
	                        String eventid = scanner.next();
	                        
	                        if ("done".equalsIgnoreCase(eventid)) {
	                            continueUpdating = false; // Exit the loop if the user enters 'done'
	                        } else {
	                            event1.updateEvent(eventid, EVENT_FILE_NAME);
	                        
	                            printing.printSomething("Event updated successfully.\n");
	                        }
	                    }
	                }
	                break;
                 
	             
	            case 4:
	                printing.printSomething("\n");
	                boolean f2 = viewCostomerevents(customerId, EVENT_FILE_NAME);
	                if (f2) {
	                    boolean continueDeleting = true;
	                    while (continueDeleting) {
	                        printing.printSomething("\nPlease enter the Event ID of the event you want to delete (or enter 'done' to finish):\n");
	                        String eventidd = scanner.next();
	                        
	                        if ("done".equalsIgnoreCase(eventidd)) {
	                            continueDeleting = false; // Exit the loop if the user enters 'done'
	                        } else {
	                            event1.deleteEvent( EVENT_FILE_NAME, eventidd);
	                            printing.printSomething("\n");
	                            continueDeleting = false;
	                            printing.printSomething("Event deleted successfully.\n");
	 	                       
	                      
	                        }
	                    }
	                }
	                break;

	            case 5:
	                printing.printSomething("\n");
	                userSearchPageList();
	                int s = scanner.nextInt();
	                
	                if (s == 1) {
	                    printing.printSomething("\n" + "Please enter the Event Name you want to see it : ");
	                    String eventN = scanner.next();
	                    String usId = customerId;
	                    Event. searchEvent(usId, eventN, 0);
	                } else if (s == 2) {
	                    printing.printSomething("\n");
	                    printing.printSomething("\n" + "Please enter the Venue Name of the event you want to see it : ");
	                    String venueN = scanner.next();
	                    String userId = customerId;
	                    Event. searchEvent(userId, venueN, 8);
	                }
	                break;
              
	          case 6:
	                printing.printSomething("\t\t\n--- Delete profile! ---\n\nUr info will be Deleted & ur orders will be cancelled!!\nAre you sure? ");
	                String str = scanner.next();
	                deleteCustomerProfile(str);
	                break;
	          case 7:
	        	  printing.printSomething("\n");   
	        	  showAdminMessage(customerId);
	               break;	               
	          case 8:
	        	    if (viewAllPackagesFromFile(PACKAGE_FILE_NAME)) {
	        	        selectpackagee();
	        	    }
	        	    break;

	        case 9:

	        	events.clear();
	        	if(  viewCostomerevents(customerId,EVENT_FILE_NAME)) {
	        		

		          	 
	          	 while (true) {
	          		 
	          		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish):\n ");
	          	     String eventIDToView = scanner.next();
	          	    if ("done".equalsIgnoreCase(eventIDToView)) {
	          	       break;
	          	    } else {
	          	    	  updateEventList(EVENT_FILE_NAME);
	          			   Event e=Event.findeventID(eventIDToView,EVENT_FILE_NAME);
	          	    	
	          			 printing.printSomething(e.toString2());
	          			printing.printSomething("\n");
	          	    
	          	       }
	              	 }
	        	}
	              break;
	        	 	        	 	        		        	  	        	   	        	   
	        case 10: 

	        	 printing.printSomething("\n");
	        	 events.clear();   	        	  
	        	if(viewCostomerevents(customerId,REQUEST_FILE_NAME)){
	        		


        	     while (true) {
        		 
        		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish):\n ");
        	     String eventIDToView = scanner.next();
        	    if ("done".equalsIgnoreCase(eventIDToView)) {
        	       break;
        	    } else {
        	    	  updateEventList(REQUEST_FILE_NAME);
        			   Event e=Event.findeventID(eventIDToView,REQUEST_FILE_NAME);
        	    	
        			 printing.printSomething(e.toString2());
        	    	 
        	    	
        	       }
            	 }
	        	}
            break;
	        case 11:



	        	Calendar calendar = loadEventsForCustomerInCalendar(customerId);


	        	if (calendar != null) {
	        	    displayAllCustomerEvents(calendar);
	        	} else {
	        		printing.printSomething("Calendar is null. Unable to display events.");
	        	}      	
	        	break;
	        case 12:
	        	readInvoiceFile(INVOICE_FILE_NAME,  customerId) ;
	   	     break;
	       case 13:	
	    	   
	                signInFunction();
	                break;
	            default: printing.printSomething(INVALID_CHOICE);
	        }
	    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    public  void providerOptions(String provideId,int choice) throws Exception {
	        switch (choice) {
	            case 1:
	            	updateProvidersList();
	                printing.printSomething("Which info you want to update?\n1. Name  2.Phone  3. Address "+"\n"+ENTER_CHOICE);
	               updateProviderProfile(scanner.nextInt());
	              
	                break;
	            case 2:
	            	 updateProvidersList();
	            	addService();
	                break;
	            case 3:
	            	 printing.printSomething("\n");   
	            	 if (viewproviderservice(provideId)) {
	                     printing.printSomething("\n"+"Please enter the Event ID of the service you want to update : ");
	                     String serviceIdIn=scanner.next();
	                	
	                     updateProviderAndServiceList(provideId);
	                     updateServiceList();
	                    Service.updateServiceDetails(serviceIdIn);
	                    printing.printSomething("\nService updated successfully.");
	                    viewproviderservice(provideId);
	                     }
                   break;
	            case 4:
	            	 printing.printSomething("\n");   
	                 if (viewproviderservice(provideId)) {
	                 printing.printSomething("\n"+"Please enter the Event ID of the service you want to delete: ");
	                 String servisIdIn=scanner.next();
	                 Service.deleteServiceFromFile(SERVICE_FILE_NAME, servisIdIn);
	                 printing.printSomething("\nService deleted successfully.");
	                 }
	                break;
	          
	            case 5: viewproviderservice(provideId);
	            	break;
	            case 6:
	            	signInFunction();
	            break;
	            	
	            default:
	                printing.printSomething(INVALID_CHOICE);
	                break;
	        }
	    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   private static void venueManagementOptions(int c) {
	  	  Scanner scanner = new Scanner(System.in);
	  	switch (c)
	  	{
	  	case 2:
	  		  addVenue(scanner, VENUE_FILE_NAME);
	  		
	            break;
	  	 case 4:
	           editVenuefrom(scanner, VENUE_FILE_NAME);
	           
	           break;
	       case 3:
	           deleteVenueById( scanner,VENUE_FILE_NAME);
	           break;
	       case 1:
	           viewAllVenues(VENUE_FILE_NAME);
	           break;
	       case 5:
	    	   printing.printSomething(EXITING);
	           break;
	       default:
	           printing.printSomething(INVALID_CHOICE);
	    	     break;
	  		
	  	}
	  }     
                            /////////////////////////////////////////////////////////////////////
	  
	   private  void eventManagementOptions(String  userId,int cE) throws Exception {
	       switch (cE) {
	        case 1:
	        if( viewalleventsforAdmin(REQUEST_FILE_NAME)) {
	      	
	        while (true) {     	
	        printing.printSomething("\nEnter the Event ID of the event you want to accept or reject (or enter 'done' to finish): ");
	         String eventID = scanner.next();
	         if ("done".equalsIgnoreCase(eventID)) {
	        	
	          break; // Exit the switch case
	        	}
	        		     
	          printing.printSomething("\nEnter 'Y' to accept the event or 'N' to reject: ");
	         String choice = scanner.next().toUpperCase();        
	         if (choice.equals("Y")) {         	
	             event1= Event.findeventID(eventID, REQUEST_FILE_NAME);
	         	event1.deleteEvent( REQUEST_FILE_NAME, eventID);
	         	Event.addEventToFile(event1, EVENT_FILE_NAME);
	            printing.printSomething("\nEvent accepted.");             
	           sendmsgToCustomer("has been approved",event1);  
	            
	            } else if (choice.equals("N")) {
	         	 event1= Event.findeventID(eventID, REQUEST_FILE_NAME);
	         	sendmsgToCustomer("has been Rejected",event1);
	         	event1.deleteEvent(REQUEST_FILE_NAME, eventID);
	         	printing.printSomething("\nEvent rejected.");
	         	
	        } else {
	           printing.printSomething(INVALID_CHOICE);
	      	     }
	         
	        	}
	        }
	        break;        
	     case 2:
	     	 viewalleventsforAdmin(EVENT_FILE_NAME); /// to String 
	     	 
	     	 while (true) {
	     		 
	     		 printing.printSomething("\nEnter the Event ID you want to view details for (or enter 'done' to finish): ");
	     	     String eventIDToView = scanner.next();
	     	    if ("done".equalsIgnoreCase(eventIDToView)) {
	     	       break;
	     	    } else {
	     	    	 updateEventList(EVENT_FILE_NAME);
	     			   Event e=Event.findeventID(eventIDToView,EVENT_FILE_NAME);
	     	    	
	     			  printing.printSomething(e.toString2());// to String2
	     	    	
	     	    	
	     	       }
	         	 }
	     	 
	         break;
	     case 3:
	       addevent(userId,EVENT_FILE_NAME);
	       break;
	     case 4:
	     	if( viewalleventsforAdmin(EVENT_FILE_NAME)) {
	     		boolean continueDeleting = true;
	            while (continueDeleting) {
	            	
	            	 printing.printSomething("\nEnter the Event ID of the event you want to delete (or enter 'done' to finish): ");
	                  String eventID = scanner.next();
	                  if ("done".equalsIgnoreCase(eventID)) {
	                      continueDeleting = false; // Exit the loop if the user enters 'done'
	                  } 
	                  else {
	                      event1.deleteEvent( EVENT_FILE_NAME, eventID);
	     	              printing.printSomething("\nEvent with ID " + eventID + " successfully deleted .");
	     	              continueDeleting = false;
	                  }
	     	       }
	            }
	     	break;
	     case 5:
	     	 if (viewalleventsforAdmin(EVENT_FILE_NAME)) {
	     		 boolean continueUpdating = true;
	             while (continueUpdating) {
	      	    printing.printSomething("Please enter the Event ID you want to update: ");
	             String eventid=scanner.next();
	             if ("done".equalsIgnoreCase(eventid)) {
	                 continueUpdating = false; // Exit the loop if the user enters 'done'
	             } else {
	             event1.updateEvent(eventid, EVENT_FILE_NAME); 
	             continueUpdating = false;            
	             viewalleventsforAdmin(EVENT_FILE_NAME); 
	             printing.printSomething("\nEvent with ID " + eventid + " successfully updated.");
	             }}}
	        break;
	     case 6:
	    	 adminPage(adminId); break;
	     default:
	         printing.printSomething(INVALID_CHOICE);
	  	     break;
	 }
	}
	                           /////////////////////////////////////////////////////////////////////// 
	      private static void providerAdminManagementOptions(int p) throws Exception {
	    		switch (p) {
	    	    case 1:
	    	    	 viewallprovider(PROVIDER_FILE_NAME);
	    	    	 break;
	    	    case 2:
	    	    	  if(  viewallprovider(PROVIDER_FILE_NAME)) {
	    	              printing.printSomething("\nEnter the Provider ID  you want to delete it: ");
	    	              String providerID = scanner.next();  
	    	             provider1.deleteProviderFromFileAndArrayList( PROVIDER_FILE_NAME, providerID);
	    	      	  printing.printSomething("\nProvider with ID " + providerID + " successfully deleted .");}

	    	      	break;  
	    	    default:
	 	           printing.printSomething(INVALID_CHOICE);
	 	    	     break;
	            }}   
	      
	      ///////////////////////////////////////////////////////////////////////    
      private static void packageManagementOptions(int r)
      {
      	
      	Scanner scanner = new Scanner(System.in);
      	switch (r)
      	{
      	case 1:
      		viewAllPackagesFromFile( PACKAGE_FILE_NAME);
              break;
      	 case 2:
               addPackage(scanner, PACKAGE_FILE_NAME);              
               break;
           case 3:
          	 deletePackageById( scanner,PACKAGE_FILE_NAME);
               break;
           case 4:
               updatePackage(scanner,PACKAGE_FILE_NAME);
               break;
           case 5:
        	   printing.printSomething(EXITING);
               break;
           default:
               printing.printSomething(INVALID_CHOICE);
        	     break;     		
      	}
      }
                        private static void discountManagementOptions(int c) {
		  Scanner scanner = new Scanner(System.in);
		switch (c)
		{
		case 2:
			addDiscount(scanner,DISCOUNT_FILE_NAME);
			
	        break;
		 case 4:
			 editDiscountfrom(scanner,DISCOUNT_FILE_NAME);
	       
	       break;
	   case 3:
		   removeDiscount( scanner,DISCOUNT_FILE_NAME);
	       break;
	   case 1:
		   viewAllDiscounts(DISCOUNT_FILE_NAME);
	       break;
	   case 5:
		   printing.printSomething(EXITING);
	       break;
	   default:
	       printing.printSomething(INVALID_CHOICE);
		     break;
			
		}
	}

	/////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      public static void viewCustomersanddelete() {
    	  Scanner scannerD = new Scanner(System.in); 
          
          
    	  updateCustomersList();
          printing.printSomething("List of Customers: \n");
          for (Customer customer1 : customers) {            
              tmp = customer1.getId() + "\t  "+customer1.getUsername() + "  "+customer1.getaddress() + "  "+customer1.getphone() + "  "+customer1.getEmail()  + "  \n";
              printing.printSomething(tmp);
          }
          printing.printSomething("Please enter the ID of the customer you want to delete:");
          String customerIdToDelete = scannerD.next();

          if (deleteCustomer(customerIdToDelete)) {
              printing.printSomething("Customer deleted successfully.");
          
              updateCustomerFile();
          } else {
              printing.printSomething("Customer not found.");
          }
      }
      
      public static void viewCustomer() {
          updateCustomersList();
          printing.printSomething("List of Customers: \n");
          for (Customer customer1 : customers) {            
              tmp = customer1.getId() + "\t  "+customer1.getUsername() + "  "+customer1.getaddress() + "  "+customer1.getphone() + "  "+customer1.getEmail()  + "  \n";
              printing.printSomething(tmp);
          }}
      
                        /////////////////////////////////////////////////////////////////////////
      public static boolean viewallprovider(String filename) {
 		 List<Provider> prov = new ArrayList<>();
 	 		  Provider provider2 = new Provider();
 		   		    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
 		        String line;
 		       while ((line = reader.readLine()) != null) {
 		           
 		         provider2=Provider.getProviderFromLine(line);
 		           
 		           prov.add(provider2);
 		        }
 		    } catch (Exception e) {
 		    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
 		    }
 		    if (prov.isEmpty()) {
 		        printing.printSomething("No provider found.\n");
 		        return false;
 		    } 		    
 		    printing.printSomething("List of providers: \n");
           for (Provider providers2 : prov) {
        	   printing.printSomething(""+ providers2+"\n"); 
 		    }
 		   return true;}
                       /////////////////////////////////////////////////////////////////////////			
      public static boolean viewallservice() {
      	updateServiceList();
  		    if (serviceDetails.isEmpty()) {
  		        printing.printSomething("No Services found.\n");
  		        return false;}
    		    printing.printSomething("List of Services : \n");
    		    for (Service service : serviceDetails) {
  		    	printing.printSomething(service.toString ());
  		       }
  		     return true;}
                    /////////////////////////////////////////////////////////////////////////			
     public static boolean viewalleventsforAdmin(String filename) {
		  updateEventList(filename);
		    if (events.isEmpty()) {
		        printing.printSomething("No events found.\n");
		        return false; }
		    printing.printSomething("List of Events: \n");		    
		    for (Event event22 : events) {
		   printing.printSomething(event22.toString()+"\n");} 
           return true;}
                     /////////////////////////////////////////////////////////////////////////			
      public static void viewAllVenues(String filename) {
          List<Venue> venues = readVenuesFromFile(filename);

          if (venues.isEmpty()) {
              printing.printSomething("No venues found.");
          } else {
              printing.printSomething("All Venues:\n");
              for (Venue venue : venues) {
            	 
                  printing.printSomething(NAME_LABEL + venue.getName());
                 
                  printing.printSomething(printing.ANSI_GREEN+VENUE_ID_LABEL + venue.getId());
                  printing.printSomething(printing.ANSI_GREEN+ ADDRESS_LABEL  + venue.getAddress());
                  printing.printSomething(printing.ANSI_GREEN+IMAGE_LABEL + venue.getImage());
                  printing.printSomething(printing.ANSI_GREEN+CAPACITY_LABEL + venue.getCapacity());
                  printing.printSomething(printing.ANSI_GREEN+PRICE_LABEL + venue.getPrice()+"\n");
                
                //  printing.printSomething("\n\n");
              }}
          }
                   //////////////////////////////////////////////////////////////////////////
      public static void viewAllVenuesCustomer(String filename) {
    	    List<Venue> venues = readVenuesFromFile(filename);
    	    if (venues.isEmpty()) {
    	        printing.printSomething("No venues found.");
    	    } else {
    	    	printing.printSomething("\033[0;35mAll Venues:\033[0m");
                for (Venue venue : venues) {  	           
                	  printing.printSomething("\nName: " + venue.getName() + ", ");
                      printing.printSomething("Address: " +venue.getAddress() + ", ");
                      printing.printSomething(IMAGE_LABEL + venue.getImage());
                      printing.printSomething(CAPACITY_LABEL + venue.getCapacity() + ", ");
                       printing.printSomething(PRICE_LABEL + venue.getPrice()+"\n");
                   
    	        }
    	    }}
                            ///////////////////////////////////////////////////////////////////

    	
      public static boolean viewCostomerevents( String cIdIn,String filename)  {
 		 boolean foundd = false; 
 		    updateeventandcustomer(cIdIn,filename); 
 		    for (Customer customer : customers) {
 		        if (customer.getId().equals(cIdIn)) {
 		            List<Event> customerEvents = Customer.getEvents();
 		           
 		               if (!customerEvents.isEmpty()){
 		                printing.printSomething("\nHere are all your events:\n");
 		                for (Event event : customerEvents)
 		                {printing.printSomething(event.toString());
 		                 foundd=true;}
 		                }
 		                		                
 		                else { printing.printSomething("Customer found, but has no events.");}
 		       }
           }
 		    
 			return foundd;}

                 //////////////////////////////////////////////////////////////////////////
  	public static boolean viewproviderservice(String id2)  {
  		 boolean found = false; 
  		providers.clear();
  		  updateProviderAndServiceList(id2); 

  		    for (Provider PROV  : providers) {
  		        if ( PROV.getId().equals(id2)) {
  		            List<Service> providersservices = PROV.getServiceDetailsList();

  		            if (! providersservices .isEmpty()) {
  		               
  		            	 Provider. displayServiceDetails() ;
  		            	found=true;
  		            } else { printing.printSomething("provider found, but has Servics no .");}

  		        }
  		    }
  		    return found;}
                 //////////////////////////////////////////////////////////////////////////
  	private static void viewBusinessReports() {
  	    updateCustomersList();
  	    updateEventList(EVENT_FILE_NAME);
  	    
  	    tmp = printing.ANSI_PURPLE+
  	    		"=================Reports================="+"\nThe  number of Customers " + customers.size()+
  	            "\nThe  number of Event " + events.size()+ "\nThe  number of Provider " + Provider.providers.size()+
  	            "\nThe  number of Venues " + countLines(VENUE_FILE_NAME)+
  	            "\n==========================================\n\n";
  	    printing.printSomething(tmp);
  	} 
           
  	public static void viewAllDiscounts(String filename) {
  	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
  	        String line;
  	        while ((line = reader.readLine()) != null) {
  	            String[] data = line.split(",");
  	            if (data.length == 4) {
  	                String discountId = data[0];
  	                String discountCode = data[1];
  	                String discountPercentage = data[2];
  	                String validityPeriod = data[3];
  	              printing.printSomething("\n");
  	                printing.printSomething("ID: " + discountId +
  	                        "\t Code: " + discountCode +
  	                        "\t Percentage: " + discountPercentage +
  	                        "\t Period: " + validityPeriod+"\n");
  	 }else {
  		printing.printSomething("Invalid discount entry: " + line);
  	            }
  	        }
  	    } catch (IOException e) {
  	    	 printing.printSomething( ERROR_PREFIX + e.getMessage()); }
  	}
  	           /////////////////////////////////////////////////////////////////////////////
    public static boolean viewAllPackagesFromFile(String filename) {
  	    List<Paackage> paackages = readPackagesFromFile(filename);

  	    if (paackages.isEmpty()) {
  	        printing.printSomething("No packages found in the file.");
  	        return false;
  	    }

  	    printing.printSomething("All Packages:");
  	    for (Paackage p : paackages) {
  	        printing.printSomething("ID: " + p.getId() +
  	        		NAME_LABEL + p.getTitle() +
  	                "  Description: " + p.getDescription() +
  	                "  Price: " + p.getPrice() +
  	                "  Validity Date: " + p.getValidityPeriod());
  	    }
		return true;
  	    
  	}
              //////////////////////////////////////////////////////////////////////////////
    public static void showAdminMessage(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Msg.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2 && parts[parts.length - 1].equals(userId)) {
                    found = true;
                    int end = line.lastIndexOf(' '); 
                    String message = line.substring(0, end);
                    if (message.endsWith(",")) {
                        message = message.substring(0, message.length() - 1); 
                    }
                    printing.printSomething("Admin Message: " + message);
                }
            }
            if (!found) {
                printing.printSomething("There is no message for you.");
            }
        } catch (IOException e) {
        	 printing.printSomething( ERROR_PREFIX + e.getMessage()); }
    }
  	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void deleteLineByValue(String filePath, String value) throws IOException {
    	updateCustomerFile();
    	
    	StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(value)) {
                    sb.append(line).append("\n");
                }
            }

            writer.write(sb.toString());
        }
    } 
    


    public static void updateFile(String id,String filePath, String newValue,int index) throws IOException {
    	//updateCustomerFile();
    	
    	try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
             String line;
             long lastPos = 0;
             while ((line = file.readLine()) != null) {
                 String[] items = line.split(" , "); 
                 if (items.length >= 6) {
                     String selectedId = items[0].trim();
                     if (selectedId.equals(id)) {
                         switch (index) {
                             case 1:
                                 
                                 String updatedLine1 = line.replace(items[1], newValue);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine1);
                                 break;
                             case 2:
                                 // Replace the value at index 2 (items[2])
                                 String updatedLine2 = line.replace(items[2], newValue);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine2);
                                 break;
                             case 3:
                                 // Replace the value at index 3 (items[3])
                                 String updatedLine3 = line.replace(items[3], newValue);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine3);
                                 break;
                             case 4:
                                 // Replace the value at index 4 (items[4])
                                 //String updatedLine4 = line.replace(items[4], newValue);
                            	 items[4] = newValue;
                            	    
                            	    // Join the items back into a single line with the delimiter ", "
                            	    String updatedLine4 = String.join(" , ", items);
                                 file.seek(lastPos);
                                 file.writeBytes(updatedLine4);
                                 break;
                             default:
                                 // Handle default case
                                 break;
                         }
                         // Update the last position pointer
                        lastPos = file.getFilePointer();
                     }
                 } 
               lastPos = file.getFilePointer();
             }
         }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    public static boolean isValidDate(String date) {
      try {
          SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
          sdf.setLenient(false);
          sdf.parse(date);
          return true;
      } catch (ParseException e) {
    	  return false;
      }

  } 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
    public static int countLines(String filePath) {
    	int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
        return count;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////////////////////
    public  Event addevent (String idUser,String filename) throws Exception {
    	   SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
           
        updateEventList(REQUEST_FILE_NAME);
        updateEventList(EVENT_FILE_NAME);
         eventObj = new Event();	
        
         printing.printSomething("\n"+"Enter event Id:");
         evenId = scanner.next();	               	              
         if (searchIdE(evenId, REQUEST_FILE_NAME)|| searchIdE(evenId, EVENT_FILE_NAME)) { found =true ;}else found=false;	              
         if (found)
         { printing.printSomething(ACCOUNT_ALREADY_EXIST_MESSAGE); 
         addevent(idUser ,filename);return null; }
    	    else  {   	            	  
         eventObj.setEID(evenId);
         eventObj.setUserId(idUser);
         printing.printSomething("Enter event name:");  
         eventObj.setName(scanner.next());
         printing.printSomething("Enter event date (yyyy-MM-dd):");
         String dateInput = scanner.next();
         Date date;
         try {
             date = dateFormat.parse(dateInput);
         } catch (ParseException e) {

        	 printing.printSomething( ERROR_PREFIX + e.getMessage());
             return null;
         }
         eventObj.setDate(date);
         //////////////////////////////////////////////////
         String eventTime, amPmChoice, formattedTime;
         while (true) {
             printing.printSomething("Enter event time (H:MM):");
             eventTime = scanner.next();

             printing.printSomething("Enter 'AM' or 'PM':");
             amPmChoice = scanner.next().toUpperCase();

             // Check if AM/PM choice is valid
             if (amPmChoice.equalsIgnoreCase("AM") || amPmChoice.equalsIgnoreCase("PM")) {
                 formattedTime = eventTime + " " + amPmChoice;
                 break; // Exit the loop if valid input is provided
             } else {
                 printing.printSomething("\nInvalid input. Please enter 'AM' or 'PM'.");
             }
         }

         // Now you have the valid formattedTime
         eventObj.setTime(formattedTime);
     /////////////////////////////////////////////////////////////////
         
         
         printing.printSomething("Enter event description:");
         eventObj.setDescription(scanner.next());
         printing.printSomething("Enter event attendee count:");
         eventObj.setAttendeeCount(scanner.next());
         printing.printSomething("Enter event theme :");
         eventObj.setTheme(scanner.next());   
         printing.printSomething("Enter event category:");
         eventObj.setCategory(scanner.next());		           		           
//////////////////////         
         viewAllVenuesCustomer(VENUE_FILE_NAME);
         boolean venueAvailable = false;
         String venueName;
         do {
             
             printing.printSomething("\nEnter Venue name:");
             venueName = scanner.next();
             if (checkAvailability(venueName, dateInput)) {
                 if (Integer.parseInt(eventObj.getAttendeeCount()) <= getVenueCapacity(venueName)) {
                     venueAvailable = true;
                     eventObj.setVenuename(venueName);
                 } else {
                     printing.printSomething("The attendee count exceeds the capacity of the venue. Please choose another venue.\n");
                 }
             } 
         } while (!venueAvailable );

         pricee=getPriceByVenue(venueName);
           
        
         

        addBookingVenue(venueName,idUser,dateInput,"Reserved",evenId);


///////////////////////	               
         List<String> serviceIdsList = new ArrayList<>();
     
         printing.printSomething("\nDo you want to add a service to your event? (yes/no)\n");
         if (scanner.next().equalsIgnoreCase("yes"))
         {
             do {
                 viewallservice();
                 printing.printSomething("\nEnter service ID you want:\n");
                 String servId = scanner.next();
                 Service s = Service.findServiceByID(servId, SERVICE_FILE_NAME);
                 if (s.getAvailability().equals("available"))
                 {
                	 serviceIdsList.add(servId);
                     eventObj.setServiceIds(serviceIdsList);
                     events.add(eventObj);
                 }
                 else 
                 {
                     printing.printSomething("The service is not available.\n");
                 }
                 printing.printSomething("\nDo you want to add another service? (yes/no)\n");
                 }
             while (scanner.next().equalsIgnoreCase("yes"));
             printing.printSomething("\nDone successfully\n");
              price=Service.calculateTotalPrice(serviceIdsList);
           
         } 
       }	     
         
         pricee +=price;
         printing.printSomething("price "+pricee);
         printing.printSomething("\nDo you have discount code ? [yes or no] : ");	               
         String yorn = scanner.next();
        String codee;
        if (yorn.equalsIgnoreCase("yes"))
        {
          printing.printSomething("\nEnter the code  : ");	
          codee = scanner.next();
          pricee =applyDiscount(pricee,codee);

         }
         printing.printSomething("\nThe price is : "+pricee);	               
         addToInvoice (idUser,evenId,eventObj.getName(),pricee);
         
       Event.addEventToFile(eventObj, filename);
     return eventObj;
 }            
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Service addService() throws Exception {
         Scanner s =new Scanner(System.in);
         updateServiceList();
       Service service = new Service();
     
       printing.printSomething("\n"+"Enter Service Id:");
       JId = s.next();	               
      
       if (searchIdS(JId,SERVICE_FILE_NAME )) { found =true ;}else found=false;	              
       if (found)
       { printing.printSomething("This service ID is already in use. Please enter a different Service ID.");
        addService( );return null; }
      else  {   
    	  
   	   service.setServiceID(JId);
   	   service.setProviderID(provideId);
   	   
        printing.printSomething("Enter service type:");
       service.setServiceType(s.next());

       printing.printSomething("Enter service name:");
       service.setServiceName(s.next());

       printing.printSomething("Enter service description:");
       service.setDescription(s.next());

       printing.printSomething("Enter service price:");
       String  price = s.next();
       Double priceD=Double.valueOf(price);
       service.setPrice(priceD);

       while (true) {
           printing.printSomething("Enter service availability(available/not_available):");
           String availabilityInput = s.nextLine();
           if (availabilityInput.equalsIgnoreCase("available") || availabilityInput.equalsIgnoreCase("not_available")) {
               service.setAvailability(availabilityInput);
               break;
           } else {
               // Handle invalid input
               printing.printSomething("\nInvalid input. Please enter either 'available' or 'not available'.\n");
           }
       }
       serviceDetails.add(service);
     Service. addServiceToFile(service);
      printing.printSomething("\nService added successfully.");
       return service;}
       
       
   }
////////////////////////////////////////////////////////////////////////////////////////////////////////	    
    public static void addDiscount(Scanner scanner, String filename) {
        printing.printSomething("Adding a discount...");
        
        try {
            printing.printSomething("Enter discount ID: ");
            int discountId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Check if discount ID already exists
            if (discountIdExists(filename, discountId)) {
                printing.printSomething("Discount ID already exists. Please enter a different ID.");
                return; // Exit the method
            }
            
            printing.printSomething("Enter discount code: ");
            String discountCode = scanner.nextLine();
            
            printing.printSomething("Enter discount percentage: ");
            double discountPercentage = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            printing.printSomething("Enter validity period (YYYY-MM-DD): ");
            String validityPeriod = scanner.nextLine();

            // Validate input if necessary
            
            Discount newDiscount = new Discount(discountPercentage, discountId, validityPeriod, discountCode);
            String discountDetails = newDiscount.toString();

            addDiscountToFile(filename, discountDetails);
            printing.printSomething("Discount successfully added.");
        } catch (InputMismatchException e) {
            printing.printSomething("Invalid input format. Please enter valid input.");
            // Handle the exception or retry input
        }}
    
    
    
    
    
    
    
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////	    
    public static void addPackage(Scanner scanner, String filename) {
    	 Scanner scannerP =new Scanner(System.in);
         
    	printing.printSomething("Adding a new package...");
        int id;
        while (true) {
            try {
                printing.printSomething("Enter package ID: ");
                id = scannerP.nextInt();
                scannerP.nextLine(); // Consume newline character

                if (!isPackageIdExists(filename, id)) {
                    break;
                }

                printing.printSomething("Package ID already exists in the file. Please enter a new ID.");
            } catch (InputMismatchException e) {
                printing.printSomething("Invalid input for package ID. Please enter a valid integer ID.");
              //  scanner.nextLine();
            }
        }
        printing.printSomething("Enter package name: ");
        String name = scannerP.nextLine();

        printing.printSomething("Enter package description: ");
        String description = scannerP.nextLine();

        printing.printSomething("Enter package price: ");
       String priceP =scannerP.next();
       double pricePD = Double.parseDouble(priceP);
        scannerP.nextLine(); // Consume newline character

        printing.printSomething("Enter package validity period (YYYY-MM-DD): ");
        String validityPeriod = scannerP.nextLine();

        String packageDetails = String.format("%d,%s,%s,%.2f,%s", id, name, description, pricePD, validityPeriod);
        addPackageToFile(filename, packageDetails);
        printing.printSomething("Package successfully added.");
    }
///////////////////////////////////////////////////////////////////////////////////////	    
    public static void addVenue(Scanner scanner, String filename) {
        Scanner scannerV = new Scanner(System.in);

        printing.printSomething("Adding a new venue...");
        String venueId;
        boolean venueIdExists=false;
        
        // Read venue ID and validate
        do {
            printing.printSomething("Enter venue ID: ");
            venueId = scannerV.nextLine();
            if (!venueId.matches("\\d+")) {
                printing.printSomething("Invalid input. Please enter a valid venue ID (numeric value): ");
                continue;
            }
            venueIdExists = isVenueIdExists(filename, venueId);
            if (venueIdExists) {
                printing.printSomething("Venue ID already exists. Please enter a unique ID: ");
            }
        } while (!venueId.matches("\\d+") || venueIdExists);

        // Read other details
        printing.printSomething("Enter venue name: ");
        String name = scannerV.nextLine();
        printing.printSomething("Enter venue address: ");
        String address = scannerV.nextLine();
        printing.printSomething("Enter Image : ");
        String image = scannerV.nextLine();

        int capacity;
        // Read and validate capacity
        do {
            printing.printSomething("Enter venue capacity: ");
            while (!scannerV.hasNextInt()) {
                printing.printSomething("Invalid input. Please enter a valid venue capacity (numeric value): ");
                scannerV.next();
            }
            capacity = scannerV.nextInt();
            scannerV.nextLine(); // Consume newline
        } while (capacity <= 0);

        double price;
        // Read and validate price
        do {
            printing.printSomething("Enter venue price: ");
            while (!scannerV.hasNextDouble()) {
                printing.printSomething("Invalid input. Please enter a valid venue price (numeric value): ");
                scannerV.next();
            }
            price = scannerV.nextDouble();
            scannerV.nextLine(); // Consume newline
        } while (price <= 0);

        // Create new venue object and add it to file
        Venue newVenue = new Venue(venueId, name, address, capacity, price, image);
        String venueDetails = newVenue.toFileString();
        addVenueToFile(filename, venueDetails);
        printing.printSomething("Venue successfully added.");
    }

    
    public static void addBookingVenue(String venid, String custid, String date, String status, String eventid) {
        try (FileWriter writer = new FileWriter("venuebook.txt", true)) {
            writer.write(venid + "," + custid + "," + date + "," + status +","+eventid+ "\n");
            printing.printSomething("Booking added successfully.");
        } catch (IOException e) {
            printing.printSomething("\nAn error occurred while adding the booking: " + e.getMessage());
        }
    }
///////////////////////////////////////////////////////////////////////////////////////    
    public static void addToInvoice(String customerId, String eventId, String eventName, double price) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVOICE_FILE_NAME, true))) {
            writer.write(customerId + "," + eventId + "," + eventName + "," + price);
            writer.newLine();           
        } catch (IOException e) { printing.printSomething( ERROR_PREFIX + e.getMessage());}
    } 
/////////////////////////////////////////////////////////////////////////////
    public static void deleteInvoice(String eventId) {
        List<String> invoices = readInvoicesFromFile();

        // Filter out entries with the specified event ID
        List<String> updatedInvoices = new ArrayList<>();
        for (String invoice : invoices) {
            String[] parts = invoice.split(",");
            if (!parts[1].equals(eventId)) { // Check if the event ID matches
                updatedInvoices.add(invoice);
            }
        }

        // Rewrite the filtered entries back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVOICE_FILE_NAME))) {
            for (String updatedInvoice : updatedInvoices) {
                writer.write(updatedInvoice);
                writer.newLine();
            }
            printing.printSomething("");
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
    }

    public static List<String> readInvoicesFromFile() {
        List<String> invoices = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INVOICE_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                invoices.add(line);
            }
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
        return invoices;
    }
	 
    
////////////////////////////////////////////////////////////////////////////////////
    public static boolean discountIdExists(String filename, int discountId) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("Discount ID: " + discountId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException properly
        }
        return false;
    }

/////////////////////////////////////////////////////////////////////////////////
   
       private static void sendmsgToCustomer(String msg, Event event) {
        try (FileWriter fileWriter = new FileWriter("Msg.txt", true)) {
            fileWriter.append("The Event ")
                      .append(event.getName()).append(" ")
                      .append(msg).append(" , ")
                      .append(event.getUsrTd()).append("\n");
        } catch (IOException e) {
            printing.printSomething(ERROR_PREFIX + e.getMessage());
        }
    }

///////////////////////////////////////////////////////////////////////////////////////	    
     public static void updatePackage(Scanner scanner, String filename) {
    	    List<Paackage> packages = readPackagesFromFile(filename);

    	    if (packages.isEmpty()) {
    	    	printing.printSomething("No packages found.");
    	        return;
    	    }

    	    while (true) {
    	    	printing.printSomething(Paackage.ALL_PACKAGES);
    	        viewAllPackagesFromFile(packages);

    	        printing.printSomething("\nEnter ID for package you want to update (or 'exit' to quit): ");
    	        String userInput = scanner.nextLine();

    	        if (userInput.equalsIgnoreCase("exit")) {
    	            break;
    	        }

    	        int packageId = Integer.parseInt(userInput);
    	        Paackage packageToUpdate = findPackageById(packages, packageId);

    	        if (packageToUpdate == null) {
    	            printing.printSomething(Paackage.PACKAGE_WITH_ID + packageId + NOT_FOUND_MESSAGE);
    	        } else {
    	            handlePackageUpdate(scanner, packageToUpdate, filename, packages);
    	        }
    	    }
    	}
     
     public static void viewAllPackagesFromFile(List<Paackage> packages) {
    	    if (packages.isEmpty()) {
    	        printing.printSomething("No packages found in the list.");
    	        return;
    	    }

    	    printing.printSomething(Paackage.ALL_PACKAGES);
    	    for (Paackage p : packages) {
    	        printing.printSomething("\nID: " + p.getId() +
    	                "  Name: " + p.getTitle() +
    	                "  Description: " + p.getDescription() +
    	                "  Price: " + p.getPrice() +
    	                "  Validity Date: " + p.getValidityPeriod()+"\n");
    	    }
    	}

     public static Paackage findPackageById(List<Paackage> packages, int packageId) {
    	    for (Paackage p : packages) {
    	        if (p.getId() == packageId) {
    	            return p;
    	        }
    	    }
    	    return null; // If no package with the given ID is found
    	}


    	public static void handlePackageUpdate(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
    		printing.printSomething("1. ID");
    		printing.printSomething("\n2. Title");
    	    printing.printSomething("\n3. Price");
    	    printing.printSomething("\n4. Validity Date");
    	    printing.printSomething("\n5. Description");
    	    printing.printSomething("\n6. Exit");

    	    printing.printSomething("Select a number: ");
    	    int choice = scanner.nextInt();
    	    scanner.nextLine(); 

    	    switch (choice) {
    	        case 1:
    	        	 Paackage.updatePackageId(scanner, packageToUpdate, filename);
    	            break;
    	        case 2:
    	        	 Paackage.updatePackageTitle(scanner, packageToUpdate, filename, packages);
    	            break;
    	        case 3:
    	        	 Paackage.updatePackagePrice(scanner, packageToUpdate, filename, packages);
    	            break;
    	        case 4:
    	            Paackage.updatePackageValidityDate(scanner, packageToUpdate, filename, packages);
    	            break;
    	        case 5:
    	        	 Paackage.updatePackageDescription(scanner, packageToUpdate, filename, packages);
    	            break;
    	        case 6:
    	            printing.printSomething("Exit.");
    	            break;
    	        default:
    	            printing.printSomething("Invalid choice.");
    	    }
    	}

///////////////////////////////////////////////////////////////////////////////////////////////
    	public static void editVenuefrom(Scanner scanner, String filename) {
    	    List<Venue> venues = readVenuesFromFile(filename);

    	    if (venues.isEmpty()) {
    	        printing.printSomething("\nNo venues found.");
    	        return;
    	    }

    	    printing.printSomething("\nAll Venues:");
    	    for (Venue venue : venues) {
    	        printing.printSomething("\n"+VENUE_ID_LABEL + venue.getId());
    	        printing.printSomething("\n"+NAME_LABEL + venue.getName());
    	        printing.printSomething("\n"+ADDRESS_LABEL + venue.getAddress());
    	        printing.printSomething("\n"+IMAGE_LABEL + venue.getImage());
    	        printing.printSomething("\n"+CAPACITY_LABEL  + venue.getCapacity());
    	        printing.printSomething("\n"+PRICE_LABEL + venue.getPrice());
    	        printing.printSomething("\n");
    	    }

    	    printing.printSomething("Enter the ID of the venue to edit: \n");
    	    String venueId = scanner.nextLine();

    	    Venue oldVenue = null;
    	    for (Venue venue : venues) {
    	        if (venue.getId().equals(venueId)) {
    	            oldVenue = venue;
    	            break;
    	        }
    	    }

    	    if (oldVenue == null) {
    	        printing.printSomething("\nVenue not found.");
    	        return;
    	    }

    	    printing.printSomething("\nChoose the attribute to edit:");
    	    printing.printSomething("1. Venue ID \n");
    	    printing.printSomething("2. Venue name \n");
    	    printing.printSomething("3. Venue address\n");
    	    printing.printSomething("4. Image\n");
    	    printing.printSomething("5. Venue capacity\n");
    	    printing.printSomething("6. Venue price\n");
    	    printing.printSomething("Enter your choice (1-6): ");
    	    int choice = scanner.nextInt();
    	    scanner.nextLine(); // Consume newline

    	    switch (choice) {
    	        case 1:
    	            printing.printSomething("Enter new venue ID: ");
    	            String newVenueId = scanner.nextLine();
    	            oldVenue.setId(newVenueId);
    	            break;
    	        case 2:
    	            printing.printSomething("Enter new venue name: ");
    	            String newVenueName = scanner.nextLine();
    	            oldVenue.setName(newVenueName);
    	            break;
    	        case 3:
    	            printing.printSomething("Enter new venue address: ");
    	            String newVenueAddress = scanner.nextLine();
    	            oldVenue.setAddress(newVenueAddress);
    	            break;
    	        case 4:
    	            printing.printSomething("Enter new image: ");
    	            String newImage = scanner.nextLine();
    	            oldVenue.setImage(newImage);
    	            break;
    	        case 5:
    	            printing.printSomething("Enter new venue capacity: ");
    	            int newVenueCapacity = scanner.nextInt();
    	            oldVenue.setCapacity(newVenueCapacity);
    	            break;
    	        case 6:
    	            printing.printSomething("Enter new venue price: ");
    	            double newVenuePrice = scanner.nextDouble();
    	            oldVenue.setPrice(newVenuePrice);
    	            break;
    	        default:
    	            printing.printSomething("Invalid choice.");
    	            return;
    	    }

    	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    	        for (int i = 0; i < venues.size(); i++) {
    	            Venue venue = venues.get(i);
    	            writer.write(venue.toFileString());
    	            if (i != venues.size() - 1) {
    	                writer.newLine();
    	            }
    	        }
    	        printing.printSomething("\nVenue successfully edited.");
    	    } catch (IOException e) {
    	        printing.printSomething( ERROR_PREFIX + e.getMessage());
    	    }
    	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public static void editDiscountfrom(Scanner scanner, String filename) {
//	    List<Discount> discounts = readDiscountsFromFile(filename);
//	    if (discounts.isEmpty()) {
//	        printing.printSomething("\nNo discounts available to edit.");
//	        return;
//	    }
//	    viewAllDiscounts(DISCOUNT_FILE_NAME);
//	    printing.printSomething("Enter the ID of the discount to edit: ");
//	    int discountId = scanner.nextInt();
//	    scanner.nextLine(); // Consume newline
//
//	    Discount oldDiscount = null;
//	    for (Discount discount : discounts) {
//	        if (discount.getDiscountId() == discountId) {
//	            oldDiscount = discount;
//	            break;
//	        }
//	    }
//
//	    if (oldDiscount == null) {
//	        printing.printSomething("\nDiscount not found.");
//	        return;
//	    }
//
//	    printing.printSomething("\nEnter new discount details:");
//	    printing.printSomething("Discount ID: ");
//	    int newDiscountId = scanner.nextInt();
//	    scanner.nextLine(); // Consume newline
//	    printing.printSomething("Discount code: ");
//	    String newDiscountCode = scanner.nextLine();
//	    printing.printSomething("Discount percentage: ");
//	    double newDiscountPercentage = scanner.nextDouble();
//	    scanner.nextLine(); // Consume newline
//	    printing.printSomething("Validity period (YYYY-MM-DD): ");
//	    String newValidityPeriod = scanner.nextLine();
//
//	    // Validate inputs before creating the new discount
//	    if (isValidDiscountPercentage(newDiscountPercentage) && isValidDate(newValidityPeriod)) {
//	        Discount newDiscount = new Discount(newDiscountPercentage, newDiscountId, newValidityPeriod, newDiscountCode);
//
//	        discounts.remove(oldDiscount); // Remove old discount
//	        discounts.add(newDiscount);    // Add new discount
//
//	        writeDiscountsToFile(filename, discounts); // Write updated discounts to file
//
//	        printing.printSomething("\nDiscount successfully edited.");
//	    } else {
//	        printing.printSomething("\nInvalid input for discount percentage or validity period.");
//	    }
//	}

    
    public static void editDiscountfrom(Scanner scanner, String filename) {
        List<Discount> discounts = readDiscountsFromFile(filename);
        if (discounts.isEmpty()) {
            printing.printSomething("\nNo discounts available to edit.");
            return;
        }
        viewAllDiscounts(DISCOUNT_FILE_NAME);
        printing.printSomething("Enter the ID of the discount to edit: ");
        int discountId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Discount oldDiscount = null;
        for (Discount discount : discounts) {
            if (discount.getDiscountId() == discountId) {
                oldDiscount = discount;
                break;
            }
        }

        if (oldDiscount == null) {
            printing.printSomething("\nDiscount not found.");
            return;
        }

        printing.printSomething("\nEnter new discount details:");
        printing.printSomething("1. Discount ID. \n");
        printing.printSomething("2. Discount code. \n");
        printing.printSomething("3. Discount percentage. \n");
        printing.printSomething("4. Validity period (YYYY-MM-DD). \n");
        printing.printSomething("Choose the attribute to edit (1-4). \n");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                printing.printSomething("Enter new discount ID: ");
                int newDiscountId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                oldDiscount.setsiscountid(newDiscountId);
                break;
            case 2:
                printing.printSomething("Enter new discount code: ");
                String newDiscountCode = scanner.nextLine();
                oldDiscount.setdiscountcode(newDiscountCode);
                break;
            case 3:
                printing.printSomething("Enter new discount percentage: ");
                double newDiscountPercentage = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                oldDiscount.setdiscountpercentage(newDiscountPercentage);
                break;
            case 4:
                printing.printSomething("Enter new validity period (YYYY-MM-DD): ");
                String newValidityPeriod = scanner.nextLine();
                oldDiscount.setvalidity(newValidityPeriod);
                break;
            default:
                printing.printSomething("Invalid choice.");
                return;
        }

     // Validate inputs before writing to file
        if (isValidDiscountPercentage(oldDiscount.getper()) && isValidDate(oldDiscount.getV())) {
            writeDiscountsToFile(filename, discounts); // Write updated discounts to file
            printing.printSomething("\nDiscount successfully edited.");
        } else {
            printing.printSomething("\nInvalid input for discount percentage or validity period. Discount not edited.");
        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
  






   

   
   

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  	public static double applyDiscount(double price, String code) {
  	    try (BufferedReader br = new BufferedReader(new FileReader(DISCOUNT_FILE_NAME))) {
  	        String line;
  	        while ((line = br.readLine()) != null) {
  	            String[] parts = line.split(",");
  	            if (parts.length >= 4) { // Ensure parts array has enough elements
  	                String codeValue = parts[1];
  	                if (codeValue.equals(code)) {
  	                    double percentage = Double.parseDouble(parts[2]);
  	                    LocalDate validityDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
  	                    if (validityDate.isAfter(LocalDate.now())) {
  	                        return ( price * (1 - percentage / 100));
  	                    } else {
  	                        printing.printSomething("\n\nThe code is expired");
  	                        return price; // Example: Return -1 for expired discount
  	                    }
  	                }
  	            } else {
  	                printing.printSomething("\nInvalid format in discounts file: " + line);
  	            }
  	        }
  	        return price; 
  	    } catch (IOException e) {
  	        printing.printSomething(ERROR_PREFIX + e.getMessage());
  	        return price;
  	    }
  	}



  	public static boolean checkAvailability(String venueName, String date) throws IOException {
  	    String venueId = getVenueIdByName(venueName);
  	    
  	    if (venueId == null || venueId.isEmpty()) { // Check if venueId is null or empty
  	        printing.printSomething("\nVenue with name " + venueName + NOT_FOUND_MESSAGE);
  	        return false;
  	    }
  	    return checkAvailabilityById(venueId, date);
  	}

    private static boolean checkAvailabilityById(String venueId, String date) throws IOException {
        File venueBookFile = new File("venuebook.txt");
        Scanner scanner = new Scanner(venueBookFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            // Check if the parts array has at least 4 elements
            if (parts.length >= 4) {
                String venueIdFromFile = parts[0];
                String dateFromFile = parts[2];
                String reserved = parts[3];

                if (venueIdFromFile.equals(venueId) && dateFromFile.equals(date) && reserved.equalsIgnoreCase("Reserved")) {
                    printing.printSomething("\nThe venue is reserved on " + date + ". Please choose another venue.");
                    scanner.close();
                    return false;
                }
            }
        }

        scanner.close();
        return true; 
    }

    public static double getPriceByVenue(String venueName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(VENUE_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6 && data[1].trim().equals(venueName)) {
                    return Double.parseDouble(data[5]);
                }
            }
        } catch (IOException e) {
            printing.printSomething("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
        	 printing.printSomething("Error parsing price: " + e.getMessage());
        }
        return 0; 
    }
    private static String getVenueIdByName(String venueName) throws IOException {
        File venueFile = new File(VENUE_FILE_NAME);
        Scanner scanner = new Scanner(venueFile);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String venueId = parts[0];
            String name = parts[1];
            
            if (name.equalsIgnoreCase(venueName)) {
                scanner.close();
                return venueId;
            }
        }
        
        scanner.close();
        return null; 
    }
    public static int getVenueCapacity(String venueName) throws IOException {
        File venueFile = new File(VENUE_FILE_NAME);
        Scanner scanner = new Scanner(venueFile);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String name = parts[1];
            if (name.equalsIgnoreCase(venueName)) {
                scanner.close();
                return Integer.parseInt(parts[4]); 
            }
        }
        
        scanner.close();
        return -1;
    }
////////////////////////////////////////////////////////////////////////////         
public void selectpackagee() throws Exception {
    Scanner scanner = new Scanner(System.in);

    try {
        printing.printSomething("Enter the ID for the package you want: ");
        int packageId = scanner.nextInt();
        scanner.nextLine(); 

        
        if (!Paackage.isPackageIdExists(PACKAGE_FILE_NAME, packageId)) {
            printing.printSomething("\nPackage ID doesn't exist.");
            return;
        }

        addevent(customerId,REQUEST_FILE_NAME);

        printing.printSomething("\nRequest successfully saved.");
    } catch (InputMismatchException e) {
        printing.printSomething("\nInvalid input. Please enter a valid integer.");
    }
}
public static boolean isPackageIdExists(String filename, int id) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && Integer.parseInt(parts[0]) == id) {
                return true;
            }
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    return false;
}
public static boolean isValidDiscountPercentage(double discountPercentage) {
    return discountPercentage >= 0 && discountPercentage <= 100;
}
public static boolean isVenueIdExists(String filename, String venueId) {
      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;
          while ((line = reader.readLine()) != null) {
              if (line.startsWith(venueId + ",")) {
                  return true; 
              }
          }
      } catch (IOException e) {
    	  printing.printSomething( ERROR_PREFIX + e.getMessage());
      }
      return false; 
  }

  





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void addVenueToFile(String filename, String venueDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(venueDetails);
        writer.newLine();
     } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}
public static void addDiscountToFile(String filename, String discountDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(discountDetails);
        writer.newLine();
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}
public static void addPackageToFile(String filename, String packageDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(packageDetails);
        writer.newLine();
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}
public static void savePackagesToFile(String filename, List<Paackage> packages) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Paackage p : packages) {
            writer.write(p.toFileString());
            writer.newLine();
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void writeDiscountsToFile(String filename, List<Discount> discounts) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Discount discount : discounts) {
            writer.write(discount.toString());
            writer.newLine();
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}

public static List<Venue> readVenuesFromFile(String filename) {
    List<Venue> venues = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 6) {
                printing.printSomething("\nInvalid format in venue file: " + line);
                continue; // Skip this line and proceed to the next one
            }
            String venueId = parts[0];
            String venueName = parts[1];
            String venueAddress = parts[2];
            String imagePath=parts[3];
            int venueCapacity = Integer.parseInt(parts[4]);
            double venuePrice = Double.parseDouble(parts[5]);
            Venue venue = new Venue(venueId, venueName, venueAddress ,venueCapacity, venuePrice,imagePath);
            venues.add(venue);
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    return venues;
}
public static List<Paackage> readPackagesFromFile(String filename) {
    List<Paackage> packages = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String title = parts[1];
            String description = parts[2];
            double price = Double.parseDouble(parts[3]);
            String validityDate = parts[4];
            Paackage p = new Paackage();
            p.setTitle(title); // Set the title
            p.setDescription(description);
            p.setId(id);
            p.setPrice(price);
            p.setValidityPeriod(validityDate);
            packages.add(p);
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    return packages;
}
public static List<Discount> readDiscountsFromFile(String filename) {
	    List<Discount> discounts = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length >= 4) {
	                int discountId = Integer.parseInt(parts[0].trim());
	                String discountCode = parts[1].trim();
	                double discountPercentage = Double.parseDouble(parts[2].trim());
	                String validityPeriod = parts[3].trim();

	                Discount discount = new Discount(discountPercentage, discountId, validityPeriod, discountCode);
	                discounts.add(discount);
	            } else {
	                printing.printSomething("\nInvalid format in discounts file: " + line);
	            }
	        }
	    } catch (IOException e) {
	    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
	    }
	    return discounts;
	}
public static void readInvoiceFile(String fileName, String customerId) {
    double totalPrice = 0.0; // Variable to store the total price
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String invoiceCustomerId = parts[0].trim();
                if (invoiceCustomerId.equals(customerId)) {
                    String eventName = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    printing.printSomething("\nEvent Name: " + eventName + "  , Price: " + price);
                    totalPrice += price; // Accumulate the price
                }
            } else {
                printing.printSomething("\nInvalid format in invoice file: " + line);
            }
        }
        printing.printSomething("\nTotal Price: " + totalPrice); // Print the total price
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void deleteDiscountFromFile(String filename, List<Discount> updatedDiscounts) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Discount discount : updatedDiscounts) {
            writer.write(discount.toString());
            writer.newLine();
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}



public static void removeDiscount(Scanner scanner, String filename) {
    printing.printSomething("\nRemoving a discount...");
    List<Discount> discounts = readDiscountsFromFile(filename);
    printing.printSomething("\n");
    // Check if the discounts list is empty
    if (discounts.isEmpty()) {
        printing.printSomething("There are no discounts to remove.");
        return; // Exit the method
    }

    // Display all discounts
    viewAllDiscounts("discounts.txt");
    printing.printSomething("Enter the ID of the discount to remove: ");

    try {
        int discountIdToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;

        List<Discount> updatedDiscounts = new ArrayList<>();

        // Iterate over the discounts to find and remove the specified discount
        for (Discount discount : discounts) {
            if (discount.getDiscountId() == discountIdToRemove) {
                found = true;
                printing.printSomething("\nDiscount found:");
                printing.printSomething("" + discount);
            } else {
                // Add discounts other than the one to be removed to the updated list
                updatedDiscounts.add(discount);
            }
        }

        // If the discount to remove was found, update the file with the updated list of discounts
        if (found) {
            deleteDiscountFromFile(filename, updatedDiscounts);
            printing.printSomething("\nDiscount successfully removed.");
        } else {
            printing.printSomething("\nDiscount not found.");
        }
    } catch (InputMismatchException e) {
        printing.printSomething("Invalid input. Please enter a valid integer.");
    }
}


public static void deletePackageById(Scanner scanner, String filename) {

    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        printing.printSomething("\nNo packages found.");
        return;
    }

    printing.printSomething("\nAll Packages:");
    for (Paackage p : packages) {
        printing.printSomething("\nPackage ID: " + p.getId() + NAME_LABEL + p.getTitle());
    }

    while (true) {
        printing.printSomething("Enter the ID of the package to remove: ");
        int packageIdToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;

        for (Paackage p : packages) {
            if (p.getId() == packageIdToRemove) {
                found = true;
                packages.remove(p);
                printing.printSomething("\nPackage with ID " + packageIdToRemove + " successfully removed.");
                break;
            }
        }

        
        if (found) {
            break; // Exit the loop if the package is found and removed
        } else {
            printing.printSomething("\nPackage with ID " + packageIdToRemove + NOT_FOUND_MESSAGE);
            printing.printSomething("\nPlease insert a new ID.");
        }
    }

    savePackagesToFile(filename, packages);
}

public static void deleteVenueById(Scanner scanner, String filename) {
	 printing.printSomething("\nRemoving a venue...");
	    List<Venue> venues = readVenuesFromFile(filename);

	    if (venues.isEmpty()) {
	        printing.printSomething("\nNo venues found.");
	        return;
	    }

	    printAllVenues(venues);

	    String venueIdToRemove = getVenueIdToRemove(scanner);

	    boolean found = removeVenue0(venues, venueIdToRemove);

	    if (found) {
	        updateVenueFile(filename, venues);
	        printing.printSomething("\nVenue with ID " + venueIdToRemove + "Deleted");

	    } else {
	        printing.printSomething("\nVenue with ID " + venueIdToRemove + NOT_FOUND_MESSAGE);
	    }
}
	    public static boolean removeVenue0(List<Venue> venues, String venueIdToRemove) {
	        for (int i = 0; i < venues.size(); i++) {
	            if (venues.get(i).getId().equals(venueIdToRemove)) {
	                venues.remove(i);
	                return true;
	            }
	        }
	        return false; // Venue with specified ID not found
	    }

public static void printAllVenues(List<Venue> venues) {
    printing.printSomething("\nAll Venues:");
    for (Venue venue : venues) {
        printing.printSomething(VENUE_ID_LABEL + venue.getId());
        printing.printSomething(NAME_LABEL + venue.getName());
        printing.printSomething(ADDRESS_LABEL + venue.getAddress());
        printing.printSomething(IMAGE_LABEL + venue.getImage());
        printing.printSomething(CAPACITY_LABEL + venue.getCapacity());
        printing.printSomething("Price: " + venue.getPrice());
        printing.printSomething("\n");
    }
}

public static String getVenueIdToRemove(Scanner scanner) {
    printing.printSomething("Enter the ID of the venue to remove: ");
    return scanner.nextLine();
}



public static void updateVenueFile(String filename, List<Venue> venues) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        for (int i = 0; i < venues.size(); i++) {
            Venue venue = venues.get(i);
            writer.write(venue.toFileString());
            writer.newLine(); 
        }
    } catch (IOException e) {
        printing.printSomething(ERROR_PREFIX + e.getMessage());
    }
}

public  static void deleteVenueBooking(String eventId, String filename) {
    List<String> lines = new ArrayList<>();
    
    // Read the contents of the file and exclude the line with the specified event ID
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] eventData = line.split(","); // Assuming each line is comma-separated
            if (eventData.length >= 5 && eventData[4].trim().equals(eventId)) {
                // Skip the line with the specified event ID
                continue;
            }
            lines.add(line);
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    
    // Rewrite the file with updated contents (excluding the line with the specified event ID)
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                     //SINGUP + UPDATE +DELETE ON profiles&FILES//
void customerSignUp() throws Exception {
    customerObj = new Customer();
    printing.printSomething("Enter your Id: ");
     id = scanner.next();
    found = searchIdU(id);
    if (found) {
        printing.printSomething(ACCOUNT_ALREADY_EXIST_MESSAGE);
        signInFunction();
    } else {
    	customerObj.setId(id);
        printing.printSomething("Enter your Name: ");
        customerObj.setName(scanner.next());
        printing.printSomething("Enter your Phone: ");
        customerObj.setPhone(scanner.next());
        printing.printSomething("Enter your Address: ");
        customerObj.setAddress(scanner.next());
        printing.printSomething("Enter your Email: ");
        customerObj.setEmail(scanner.next());
        printing.printSomething(THANK_MESSAGE);
        customerObj.setPassword(scanner.next());
        printing.printSomething("\nRegistration done successfully\n");
        customers.add(customerObj);
        Customer .addCustomerToFile(customerObj);
    }
}
public  void updateCustomerProfile(int n) throws IOException {
     Scanner sc =new Scanner(System.in);
	String tmp1;
    for (Customer customer1 : customers) {
        if (customer1.getId().equals(customerId)) {
           
            switch (n) {
                case 1:
                    printing.printSomething("Enter New Name: ");
                    tmp1 = sc.nextLine(); 
                    customer1.setName(tmp1);
                    updateCustomerFile();
                   break;
                case 2:
                    printing.printSomething("Enter New Phone: ");
                    tmp1 = sc.nextLine();
                  
                    customer1.setPhone(tmp1);
                    updateCustomerFile();
                    break;
                case 3:
                    printing.printSomething("Enter New Address: ");
                    tmp1 = sc.nextLine(); 
                    customer1.setAddress(tmp1);
                    updateCustomerFile();
                    break;
                case 4:
                    printing.printSomething("Enter New Email: ");
                    tmp1 = sc.nextLine(); 
                    customer1.setEmail(tmp1);
                    updateCustomerFile();
                    break;
                
              default:
                    printing.printSomething(INVALID_CHOICE);
            }
        }  
    }
}



/////////////////////////////////////////////////////////////////////


public static void deleteCustomerProfile(String val) throws IOException {
    if (val.equalsIgnoreCase("yes")) {
        File inputFile = new File(CUSTOMER_FILE_NAME);
        File tempFile = new File("temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String lineToRemove = id;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
               if (currentLine.contains(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        if (!inputFile.delete()) {
            printing.printSomething("Could not delete the original file.");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            printing.printSomething("Could not rename the temporary file.");
        }
        printing.printSomething("\nAccount Successfully Deleted\n\n");
    }
}
public static boolean deleteCustomer(String id) {
    if (id == null) {
        return false; // Return false if id is null
    }
    String trimmedId = id.trim();
    for (int i = 0; i < customers.size(); i++) {
        Customer customer = customers.get(i);
        if (customer != null) {
            String customerId = customer.getId();
            if (customerId != null && customerId.equals(trimmedId)) {
                customers.remove(i);
                try {
                    deleteLineByValue(CUSTOMER_FILE_NAME, id); 
                } catch (IOException e) {
                    printing.printSomething("An error occurred while deleting the customer from the file: " + e.getMessage());
                }
                return true;
            }
        }
    }
    return false; 
}


//////////////////////////////////////
public  void providerSignUp() throws Exception {
      providerObj = new Provider();
      printing.printSomething("Enter your Id: ");
      id = scanner.next();
      found = searchIdP(id);
      if (found) {
      printing.printSomething(ACCOUNT_ALREADY_EXIST_MESSAGE);
      signInFunction();
      } else {
    	  providerObj.setId(id);
          printing.printSomething("Enter your Name: ");
          providerObj.setName(scanner.next());
          printing.printSomething("Enter your Phone: ");
          providerObj.setPhone(scanner.next());
          printing.printSomething("Enter your Address: ");
          providerObj.setAddress(scanner.next());
          printing.printSomething("Enter your Email: ");
          providerObj.setEmail(scanner.next());
          printing.printSomething(THANK_MESSAGE);
          providerObj.setPassword(scanner.next());
          printing.printSomething("\nRegistration done successfully\n");
          providers.add(providerObj);
          Provider. addProviderToFile(providerObj);
        }
}
public void updateProviderProfile(int n) throws IOException {
	 Scanner sP =new Scanner(System.in);
		 
	String tmp1;
    for (Provider provider1 : providers) {
        if (provider1.getId().equals(provideId)) {
            switch (n){
                case 1:
                    printing.printSomething(ENTER_NAME);
                    tmp1 =  sP.next();
                    updateFile(provideId,PROVIDER_FILE_NAME,  tmp1,1);
                    provider1.setName(tmp1);
                    break;
                case 2:
                    printing.printSomething("Enter New Phone: ");
                    tmp1 =  sP.next();
                    updateFile(provideId,PROVIDER_FILE_NAME, tmp1,2);
                    provider1.setPhone(tmp1);
                    break;
                case 3:
                    printing.printSomething("Enter New Address: ");
                    tmp1=  sP.next();
                    updateFile(provideId,PROVIDER_FILE_NAME, tmp1,3);
                    provider1.setAddress(tmp1);
                    break;
               
                default:    printing.printSomething(INVALID_CHOICE);
            }
        }
    }

	
}
/////////////////////////////////////
public static void updateCustomerFile() {
    try (FileWriter customersFile = new FileWriter(CUSTOMER_FILE_NAME)) {
        for (Customer customer : customers) {
            customersFile.write(customer.getId() + " , "
                    + customer.getUsername() + " , "
                    + customer.getphone() + " , "
                    + customer.getaddress() + " , "
                    + customer.getEmail() + " , "
                    + customer.getPassword() + "\n"); 
        }
    } catch (IOException e) {
        printing.printSomething("An error occurred while updating the customer file: " + e.getMessage());
    }
}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                           //SEARCH IDS//
public static boolean searchIdU(String id) {

	 try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE_NAME))) {

	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] items = line.split(" , ");
	            if (items.length >= 6) {
	                String cId =items[0].trim();
	                if (cId .equals(id)) {
	                    return true; 
	               }
	            }
	        }
	    } catch (IOException e) {
	        printing.printSomething(ERROR + e.getMessage());
	    } catch (NumberFormatException e) {

	        printing.printSomething(INVALID_INPUT + e.getMessage());

	    }
	    return false; // Return false if the ID is not found in any line
}

public static boolean searchIdP(String id) {
    boolean f = false;
    try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains(id)) { f = true;}
        }
    } catch (IOException e) { printing.printSomething( ERROR + e.getMessage());}
    return f;
}

public static boolean searchIdE(String id3, String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] items = line.split(" , ");
            if (items.length >= 10) {
                String eventId =items[9].trim();
                if (eventId .equals(id3)) {
                    return true; 
               }
            }
        }
    } catch (IOException e) {
        printing.printSomething(ERROR + e.getMessage());
    } catch (NumberFormatException e) {
        printing.printSomething(INVALID_INPUT + e.getMessage());
    }
    return false; // Return false if the ID is not found in any line
}

public static boolean searchIdS(String serviceID, String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] items = line.split(" , ");
            if (items.length >= 7) { 
                String serviceid = items[0].trim();
                if (serviceid.equals(serviceID)) {
                    return true; 
                }
            }
        }
    } catch (IOException e) {
        printing.printSomething(ERROR + e.getMessage());
    } catch (NumberFormatException e) {
        printing.printSomething(INVALID_INPUT + e.getMessage());
    }
    return false; 
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
                                                   //UPDATE ON LISTS//
public static void updateEventList(String filename) {
    String line;
    events.clear();
    try (FileReader eventFileReader = new FileReader(filename);
         BufferedReader lineReader = new BufferedReader(eventFileReader)) {
        while ((line = lineReader.readLine()) != null) {
            if (line.isEmpty()) continue;
            events.add(Event.getEventFromLine(line));
        }
    } catch (IOException e) {
        printing.printSomething(ERROR_PREFIX + e.getMessage());
    }
}

//////////////////////////////////////
public static void updateCustomersList() {
    String line;
    customers.clear();
    try (FileReader customersFileReader = new FileReader(CUSTOMER_FILE_NAME);
         BufferedReader lineReader = new BufferedReader(customersFileReader)) {
        while ((line = lineReader.readLine()) != null) {
            customers.add(Customer.getCustomerFromLine(line));
        }
    } catch (IOException e) {
        printing.printSomething(ERROR_PREFIX + e.getMessage());
    }
}
//////////////////////////////////////
public  static void updateeventandcustomer(String cId, String filename) {
	Customer.Cevents.clear();
	events.clear();
	updateEventList(filename);
    updateCustomersList();

    String e = null;
    String c = null;

    for (Event event : events) {
        e = event.getUsrTd();
        for (Customer customer : customers) {
            c = customer.getId();
            if (e != null && c != null && e.equals(c)&&c.equals(cId)) {
            	Customer.Cevents.add (event); 
            }
        }
    }
}
//////////////////////////////////////
public static void updateProvidersList() {
    providers.clear(); 
    try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            Provider provider = Provider.getProviderFromLine(line); 
            providers.add(provider);
        }}
      catch (IOException e) {printing.printSomething("Error reading provider data: " + e.getMessage()); }}
//////////////////////////////////////
public static void updateServiceList() {
    try (BufferedReader br = new BufferedReader(new FileReader(SERVICE_FILE_NAME))) {
        String line;
        serviceDetails.clear();
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            Service service = Service.getServiceFromLine(line);
            serviceDetails.add(service);
            }}
             catch (IOException e) { printing.printSomething(ERROR_PREFIX + e.getMessage());}}
/////////////////////////////////////
public  static void updateProviderAndServiceList(String id)  {
            Provider.serviceDetailsList.clear();
           providers.clear();
           serviceDetails.clear();
            updateProvidersList();
			updateServiceList();
			
			String p=null;
			String s=null;
			for (Provider  Prov :  providers)  
			{ p=Prov.getId();
	      			for (Service Serv : serviceDetails)
	      			{s=Serv.getProviderID();
	      				if (p.equals(s)&& p.equals(id)) 
	      				{Prov.serviceDetailsList.add(Serv); }}}}
	      				

//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//==========================================================================================================================================
                                                     //PAGE LISTS//
public static void signInPageList() {
    printing.printSomething("""
        \n---------- Sign in Page ----------
        |                                |
        |        1. Administrator        |
        |        2. Customer             |
        |        3. Provider             |
        |                                |
        ----------------------------------
        """);
}


public static void adminList() {      
    printing.printSomething("\n--------- Welcome to Admin Page --------\n"+SPACE+
            "\n|   1. Customer Management              |"+"\n|   2. Discount Management              |"+
            "\n|   3. Event Management                 |"+"\n|   4. Venue Management                 |"+
            "\n|   5. Provider Management              |"+"\n|   6. Package Management               |"+
            "\n|   7. View Report                      |"+"\n|   8. Log Out                          |"+            
            "\n"+ SPACE+ "\n-----------------------------------------\n");
    }
public  static void customerPageList(){
    printing.printSomething("\n------- Welcome to Customer Page -------\n"+SPACE+"\n|        1. Update My Profile           |"+
            "\n|        2. Make An Event               |"+"\n|        3. Update Event                |"+
            "\n|        4. Cancel Event                |"+"\n|        5. Search                      |"+
            "\n|        6. Delete My Profile           |"+"\n|        7. Show Admin MSG              |"+
            "\n|        8. Packages                    |"+"\n|        9. view all my events          |"+
            "\n|        10.view my requst              |"+"\n|        11. Calendar and Scheduling    |"+
            "\n|        12. Invoice                    |"+
            "\n|        13. Log out                    |\n"+SPACE+"\n"+LINE+"\n"
            +ENTER_CHOICE );}

    public  static void providerPageList() {
    printing.printSomething("\n-------- Welcome to Providers Page --------\n" + SPACE +
            "\n|        1. Update My Profile           |" + "\n|        2. Add Service                 |" +
            "\n|        3. Update Service              |" + "\n|        4. Delete Service              |" +            
            "\n|        5. ALl My Services             |"+  "\n|        6.Log Out                      |" +
            SPACE + "\n----------------------------------------\n" + ENTER_CHOICE);
}
     public static void eventManagementAdminPageList() {
    	printing.printSomething(
    			YELLOW_TEXT_COLOR+
    		    " ---- Welcome to EventManagement Page ----\n"+
    		    YELLOW_TEXT_COLOR+
    		      ONE+" IN requst                        |\n"+
    		      TWO+" All events                       |\n"+
    		    THREE+" ADD EVENT                        |\n"+
    		       FOUR+DELETE+
    		       FIVE+EDIT+
    		       SIXE+LOG_OUT+
    		    " ----------------------------------------\n" + ENTER_CHOICE);
}

public static void userManagementAdminPageList() {
    printing.printSomething(
    		YELLOW_TEXT_COLOR +
        "---- Welcome to User Management Page ----\n" +
        YELLOW_TEXT_COLOR+
        ONE+VIEW_ALL+
        TWO+DELETE+
        THREE+LOG_OUT+
         " ----------------------------------------\n" + ENTER_CHOICE);
        
}
public static void userSearchPageList() {
 printing.printSomething(
		 YELLOW_TEXT_COLOR+
     "---- Welcome to User Search Page ----\n" +
     YELLOW_TEXT_COLOR +
     "|   1. Search by Event Name          |\n" +
     "|   2. Search by Venue Name          |\n" +
     "|   3. Log out                       |\n"+
     " ----------------------------------------\n" + ENTER_CHOICE
 );
}

public static void venueManagementadminList() {
    printing.printSomething(
    		YELLOW_TEXT_COLOR +
        " ---- Welcome to Venue Management Page ----\n" +
        YELLOW_TEXT_COLOR  +
        ONE+VIEW_ALL+
        TWO+ADD+
        THREE+DELETE+
        FOUR+EDIT+
        FIVE+LOG_OUT+
        " ----------------------------------------\n" + ENTER_CHOICE);
    }


public static void providerManagementAdminPageList() {
 printing.printSomething(
		 YELLOW_TEXT_COLOR +
    " -- Welcome to Provider Management Page --\n" +
    YELLOW_TEXT_COLOR +
    ONE+VIEW_ALL+
    TWO+DELETE+
    THREE+LOG_OUT+
    " ----------------------------------------\n" + ENTER_CHOICE
);}

public static void packageManagementadminList() {
  printing.printSomething(
		  YELLOW_TEXT_COLOR +
      " -- Welcome to Package Management Page --\n" +
      YELLOW_TEXT_COLOR+
        ONE+VIEW_ALL+
        TWO+ADD+
        THREE+DELETE+
        FOUR+EDIT+
        FIVE+LOG_OUT+
        " ----------------------------------------\n" + ENTER_CHOICE
  );}

public static void discountManagementadminList() {
  printing.printSomething(
		  YELLOW_TEXT_COLOR +
      " -- Welcome to Discount Management Page --\n" +
      YELLOW_TEXT_COLOR+
        ONE+VIEW_ALL+
        TWO+ADD+
        THREE+DELETE+
        FOUR+EDIT+
        FIVE+LOG_OUT+
        " ----------------------------------------\n" + ENTER_CHOICE
  );}
//==========================================================================================================================================
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////***********	
public  List<Event> makeListofEvent(String cId)  {
updateeventandcustomer(customerId,EVENT_FILE_NAME);

List<Event> customerEvents = new ArrayList<>();

for (Customer customer : customers) {
if (customer.getId().equals(cId)) {
for (Event E : Customer.getCevents()) {
if (E.getUsrTd().equals(cId)) {
customerEvents.add(E);
}
}
}
}


if (customerEvents.isEmpty()) {

return Collections.emptyList(); 
} else {
return customerEvents;
}
}
////////////////////////////////////////////////////////////////////////////////////////////

//method to load events for a specific customer in calendar
public  Calendar loadEventsForCustomerInCalendar(String customerId) {
List<Event> customerEvents;
try {
customerEvents = makeListofEvent(customerId);

if (customerEvents != null && !customerEvents.isEmpty()) {
Calendar calendar = new Calendar();

for (Event event : customerEvents) {
calendar.addEvent(event);
}return calendar ;
} else {
printing.printSomething( "No events found for customer with ID: " + customerId);

}
} catch (Exception e) {

printing.printSomething( ERROR_PREFIX + e.getMessage());
} return null ;

}




public static void displayAllCustomerEvents(Calendar calendar) {
//Keep track of displayed months
Set<String> displayedMonths = new HashSet<>();

//Collect all unique year-month combinations from events and sort them chronologically
List<String> eventYearMonths = new ArrayList<>();
for (Event event : calendar.getEvents()) {
LocalDate eventDate = event.getDateAsLocalDate();
String yearMonthKey = eventDate.getYear() + "-" + eventDate.getMonthValue();
if (!eventYearMonths.contains(yearMonthKey)) {
eventYearMonths.add(yearMonthKey);
}
}
Collections.sort(eventYearMonths);

//Iterate over all unique year-month combinations
for (String yearMonthKey : eventYearMonths) {
String[] parts = yearMonthKey.split("-");
int year = Integer.parseInt(parts[0]);
int month = Integer.parseInt(parts[1]);

//Set the current year and month to the calendar
calendar.setYear(year);
calendar.setMonth(month);

//Print the events for the current year and month
displayCalendarEvents(calendar); 
printing.printInColor(LINE_STARS, Printing.ANSI_BLACK);

//Add the displayed month to the set
displayedMonths.add(yearMonthKey);
}
}


public static void displayCalendarEvents(Calendar calendar) {
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

//Print year and month with appropriate color
YearMonth yearMonth = YearMonth.of(calendar.getYear(), calendar.getMonth());
if (yearMonth.isBefore(YearMonth.now())) {
printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_GRAY);
} else if (yearMonth.equals(YearMonth.now())) {
printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_ORANGE);
} else {
printing.printInColor(yearMonth.format(dateFormatter), Printing.ANSI_BLUE);
}

//Print the header for days of the week
printing.printInColor("\n"+LINE2, Printing.ANSI_LIME);
printing.printInColor("|          Mon          |          Tue          |          Wed          |          Thu          |          Fri          |          Sat          |          Sun          |\n", Printing.ANSI_GREEN);

//Get the first day of the month
LocalDate firstDayOfMonth = LocalDate.of(calendar.getYear(), calendar.getMonth(), 1);

int frow = 0;
boolean inFirstRow = false;
//Set the current date to the first day of the month
LocalDate currentDate = firstDayOfMonth;

int currentDayOfMonth = 1;
//Print the days of the month
while (currentDayOfMonth <= yearMonth.lengthOfMonth()) {
printing.printInColor(LINE2, Printing.ANSI_LIME);

StringBuilder dayRow = new StringBuilder("|");
inFirstRow = false;
//Print the current week
for (int i = 0; i < 7; i++) {
currentDayOfMonth++;
if (currentDate.format(dayFormatter).compareTo("01") == 0 &&currentDate.getMonthValue()==calendar.getMonth()) {
inFirstRow = true;
//Print the calendar grid
for (int k = 0; k < firstDayOfMonth.getDayOfWeek().getValue() - 1; k++) {
dayRow.append("                       |");
frow = k + 2;
}
}

if (inFirstRow && i > 7 - frow) {
break;
}
String dayString = currentDate.format(dayFormatter);
if (currentDate.equals(LocalDate.now())) {
dayString = "[" + dayString + "]";
dayRow.append(String.format("*** %s ****|", dayString));
} else if(currentDate.getMonthValue()==calendar.getMonth()) {
dayRow.append(String.format("          %s           ", dayString));dayRow.append("|");
} else{
dayRow.append(String.format("                       |"));}   

currentDate = currentDate.plusDays(1);

}

printing.printInColor(dayRow.toString(), Printing.ANSI_GREEN);
printing.printInColor("\n", Printing.ANSI_GREEN);
printing.printInColor(LINE2, Printing.ANSI_LIME);

//Print events for each day
printEventsForWeek(calendar, currentDate.minusDays(7), currentDate.minusDays(1));
}

//Print the footer for the days of the week
printing.printInColor(LINE2, Printing.ANSI_LIME);
}



private static void printEventsForWeek(Calendar calendar, LocalDate startDay, LocalDate endDay) {

for (int i = 0; i < 7; i++) {
for (LocalDate currentDate = startDay; currentDate.isBefore(endDay.plusDays(1)); currentDate = currentDate.plusDays(1)) {
final LocalDate currentDate2 = currentDate;  // Declare currentDate2 as final


StringBuilder eventRow = new StringBuilder("|");

Event eventForDay = calendar.getEvents().stream()
.filter(event -> event.getDateAsLocalDate().isEqual(currentDate2))
.findFirst()
.orElse(null);

//Display the event with appropriate color
if (eventForDay != null && currentDate.getMonthValue()==calendar.getMonth()) {
String eventColor = "";
YearMonth eventYearMonth = YearMonth.of(eventForDay.getDateAsLocalDate().getYear(), eventForDay.getDateAsLocalDate().getMonth());
if (eventYearMonth.isBefore(YearMonth.now())) {
eventColor = Printing.ANSI_GRAY;
} else if (eventYearMonth.equals(YearMonth.now())) {
eventColor = Printing.ANSI_ORANGE;
}else  eventColor = Printing.ANSI_BLUE;


eventRow.append(eventColor).append(String.format("[%-6s] %-14s", eventForDay.getEID(), eventForDay.getName()));
calendar.deleteEvent(eventForDay);

} else {
eventRow.append("                       ");
}

printing.printInColor(eventRow.toString(), Printing.ANSI_LIME);

}
printing.printInColor("|\n", Printing.ANSI_LIME);

}
}




//////////////////////////////////////////////////////////////////////////////////////////////////////////***********	  








//Initialize a set to keep track of event IDs for which notifications have been sent
private Set<String> notifiedEvents = new HashSet<>();

//Initialize a scheduled executor service
private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

public void startApproachingUpcomingEvents() {
// Schedule the approachUpcomingEvents method to run every hour
executor.scheduleAtFixedRate(this::approachUpcomingEvents, 0, 1, TimeUnit.MINUTES);
}

public void stopApproachingUpcomingEvents() {
// Shutdown the executor service when you want to stop checking for upcoming events
executor.shutdown();
}

public void approachUpcomingEvents()throws NullPointerException {
LocalDateTime now = LocalDateTime.now();
updateEventList(EVENT_FILE_NAME);

List<Event> upcomingEvents = events.stream()
.filter(event -> {
// Convert Date to String
String dateString = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(event.getDate());
// Parse String as LocalDate
LocalDate eventDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
return eventDate.isEqual(now.toLocalDate());
})
.toList();



for (Event event : upcomingEvents) {

LocalTime currentTime = LocalTime.now();
LocalTime eventTime = LocalTime.parse(event.getTime().trim(), DateTimeFormatter.ofPattern("h:mm a"));
long timeDifferenceMinutes = currentTime.until(eventTime, java.time.temporal.ChronoUnit.MINUTES);
if (!notifiedEvents.contains(event.getEID()) && timeDifferenceMinutes == 59) {
String customerId = event.getUsrTd();
String recipientDetails = getEmailAndNameFromCustomerFile(customerId);
String[] details = null;

if (recipientDetails != null) {
details = recipientDetails.split("-");
} else {
details = new String[]{"royasmine05@gmail.com", "CLIENT"};
}
String recipientName = details[1];
String messageContent = generateMessageContent(recipientName, event.getTime(), 60 - timeDifferenceMinutes);
String subject = "Notification for Upcoming Event: " + event.getName();
sendNotificationsToParticipants(details[0], subject, messageContent);
notifiedEvents.add(event.getEID());
}
}

}


public String getEmailAndNameFromCustomerFile(String customerId) {

updateCustomersList();



for (Customer cust : customers) {
if (cust.getId().equals(customerId)) { 	            
return cust.getEmail()+"-"+cust.getUsername();
}
}

return null;
}

public String generateMessageContent( String customerName, String eventTime, long hoursDifference) {
//Implement this method to generate a professional message confirming the event start time for the customer with the specified ID
return "Dear " + customerName + ",\n\n"
+ "We are pleased to confirm your registration for the upcoming event.\n\n"
+ "Event Details:\n"
+ "Event Time: " + eventTime + "\n"
+ "Event Start Time: " + hoursDifference + " hours from now\n\n"
+ "Thank you for your participation. We look forward to seeing you at the event.\n\n"
+ "Best regards,\n"
+ "The Event Management Team";
}



public void sendNotificationsToParticipants(String recipientEmail, String subject, String messageContent) {
try {
String senderEmail = "royasmine05@gmail.com";
String password = getEncryptedPassword();

Properties properties = new Properties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
@Override
protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
return new javax.mail.PasswordAuthentication(senderEmail, password);
}
});

MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress(senderEmail));
message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
message.setSubject(subject);
message.setText(messageContent);

Transport.send(message);

} catch (MessagingException mex) {
printing.printSomething( ERROR_PREFIX + mex.getMessage());
}
}


////////////////////////////////////new/////////////////

public static String getEncryptedPassword() {
String password = null;
try (BufferedReader reader = new BufferedReader(new FileReader("config.properties"))) {
String line;
while ((line = reader.readLine()) != null) {
if (line.startsWith("password=")) {
password = line.substring("password=".length());
break;
}
}
} catch (IOException e) {
printing.printSomething( ERROR_PREFIX + e.getMessage());
}
return password;
}



}







	
