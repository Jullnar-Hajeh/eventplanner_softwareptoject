package org.example;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Service {
    private String serviceID;
    private String providerID;
    private String serviceType;
    private String serviceName;
    private String description;
    private double price;
    private String availability;
    static Printing printing = new Printing();
    Functions f =new Functions();
    private static final List<Service> serviceDetailsList = new ArrayList<>();
      static final String SERVICE_FILE_NAME = "service.txt";
    
    public Service() {}

    public Service( String serviceType, String serviceName, String description, String availability,String serviceID, double price,String providerid) {
        this.serviceID = serviceID;
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this .providerID=providerid;
    }

    
   
   

 public static double calculateTotalPrice(List<String> serviceIds) {
       // Map to store service IDs and their corresponding prices
       Map<String, Double> servicePrices = getServicePricesFromFile(SERVICE_FILE_NAME);
       double totalPrice = 0.0;

       // Iterate through the chosen service IDs and sum up their prices
       for (String serviceId : serviceIds) {
           if (servicePrices.containsKey(serviceId)) {
               totalPrice += servicePrices.get(serviceId);
           } else {
        	   printing.printSomething("Price for service ID " + serviceId + " not found.");
           }
       }

       return totalPrice;
   }

   // Helper method to read service prices from file and store them in a map
   private static Map<String, Double> getServicePricesFromFile(String filename) {
       Map<String, Double> servicePrices = new HashMap<>();

       try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
           String line;
           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length >= 2) {
                   String serviceId = parts[0].trim();
                   double price = Double.parseDouble(parts[5].trim()); // Assuming price is at index 5
                   servicePrices.put(serviceId, price);
               }
           }
       } catch (IOException | NumberFormatException e) {
    	   printing.printSomething("error at getServicePricesFromFile:"+e); // Handle or log the exception as needed
       }

       return servicePrices;
   }

   

   public String toString() {
       return 
    		   
    		   serviceID + ", " +
               serviceType + ", " +
               serviceName + ", " +
               description + ", " +
               price + ", " +
               availability+"\n";
   }
////////////////////////////////////////////////////////////////////////
   public static void addServiceToFile(Service service) {
	    try (FileWriter serviceFile = new FileWriter(SERVICE_FILE_NAME, true)) {
	        
	        serviceFile.append(service.getServiceID()).append(" , ")
	                   .append(service.getProviderID()).append(" , ")
	                   .append(service.getServiceType()).append(" , ")
	                   .append(service.getServiceName()).append(" , ")
	                   .append(service.getDescription()).append(" , ")
	                   .append(String.valueOf(service.getPrice())).append(" , ")
	                   .append(service.getAvailability())
	                   .append("\n");
	        
	    } catch (IOException e) {
	        printing.printSomething("An error occurred: " + e.getMessage());
	    }
	}
////////////////////////////////////////////////////////////////////////  
   public static Service getServiceFromLine(String line) {
	    Service service = new Service();
	    String[] items = line.split(" , ");
	    if (items.length >= 7) {
	        service.setServiceID(items[0]);
	        service.setProviderID(items[1]);
	        service.setServiceType(items[2]);
	        service.setServiceName(items[3]);
	        service.setDescription(items[4]);
	        service.setPrice(Double.parseDouble(items[5]));
	        service.setAvailability(items[6]);
	    } else {
	    	 printing.printSomething("Invalid line format: " + line);	  
	    	 }
	    return service;
	}
   
 ////////////////////////////////////////////////////////////////////  
  /*
   public static  void deleteService(String serviceID) throws IOException {
	    Functions.updateServiceList();
	   // Functions.updateProviderAndServiceList( providerID);
	   
	    if ( !serviceDetailsList.isEmpty()) {
	        Service serviceToDelete = null;
	        for (Service service : Functions.serviceDetails) {
	            if (service.getServiceID().equals(serviceID)) {
	                serviceToDelete = service;
	                break;
	            }
	        }

         if (serviceToDelete != null) {
               serviceDetailsList.remove(serviceToDelete);
               deleteServiceFromFile(SERVICE_FILE_NAME, serviceID);
                 
           } else {
           	printing.printSomething("Service not found.\n");
           }
       } else {
       	printing.printSomething("Service details list is null.\n");
       }}	
	
   */
/////////////////////////////////////////////////////////
   public static void deleteServiceFromFile(String filename,String sid)throws IOException 
	{ 
	
		
	    String line;
		 StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
		{   
	        while ((line = reader.readLine()) != null)
	        {   String[] items = line.split(" , ");
	        if (items.length >= 7) 
	        {
	        	  String serviceId=items[0];
	        	  if (!serviceId.equals(sid)) {
	                  sb.append(line).append("\n");
	              } 
	        } 
	   
	        }
	        
	       
	     }
	         try (FileOutputStream fos = new FileOutputStream(filename, false)) {
		            fos.write(sb.toString().getBytes());
		        }
			
	         
	              
	}
///////////////////////////////////////////////////////////////////////
   public static Service findServiceByID(String serviceId, String filename) {
	    String line;
	    Service service =new Service();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        while ((line = reader.readLine()) != null) {
	            String[] items = line.split(" , ");
	            if (items.length >= 7) {
	    	        
	            	String sid = items[0];
	            	if (sid.equals(serviceId)) {
	            	
					service.setServiceID(items[0]);
	    	        service.setProviderID(items[1]);
	    	        service.setServiceType(items[2]);
	    	        service.setServiceName(items[3]);
	    	        service.setDescription(items[4]);
	    	        service.setPrice(Double.parseDouble(items[5]));
	    	        service.setAvailability(items[6]);

	                    return service;}
	               
	            } else {
	            	printing.printSomething("Invalid line format for service ID " + serviceId + ": " + line);
	            }
	        }
	    } catch (IOException e) {
	    	printing.printSomething("Error reading file: " + e.getMessage());
	    }

	    return null; // Service not found or error occurred
	}   
   
////////   
   
   public static void updateServiceDetails(String sid) throws IOException,NullPointerException {
		
	   Service toupdatedService = findServiceByID(sid,SERVICE_FILE_NAME);
	   deleteServiceFromFile(SERVICE_FILE_NAME,sid);
	   Functions.updateServiceList();
		  
	   
		if ( toupdatedService != null)
		{
			Scanner scanner = new Scanner(System.in);
			printing.printSomething("""
			Choose which field to update:
			1. Service Type
			2. Service Name
			3. Description
			4. Price
			5. Availability
			""");
			
			
			printing.printSomething("Enter the number of the field you want to update: ");
            String x = scanner.next();
            
            switch (x) {
            case "1":
                printing.printSomething("Enter new service type:");
                toupdatedService.setServiceType(scanner.next());
                break;
            case "2":
                printing.printSomething("Enter new service name:");
                toupdatedService.setServiceName(scanner.next());
                break;
            case "3":
                printing.printSomething("Enter new service description:");
                toupdatedService.setDescription(scanner.next());
                break;
            case "4":
                printing.printSomething("Enter new service price:");
                toupdatedService.setPrice(scanner.nextDouble());
                break;
            case "5":
                printing.printSomething("Enter new service availability:");
                toupdatedService.setAvailability(scanner.next());
                break;
            default:
           	 printing.printSomething("Invalid choice.");
        }
	   
	   
	     Service.addServiceToFile(toupdatedService);
		}
		
		
		
	}
//////////////////////////////////////////////////////////////////////
   public String getServiceID() {
       return serviceID;}


   public void setServiceID(String serviceID) {
       this.serviceID = serviceID;
   }

   public String getServiceType() {
       return serviceType;
   }

   public void setServiceType(String serviceType) {
       this.serviceType = serviceType;
   }

   public String getServiceName() {
       return serviceName;
   }

   public void setServiceName(String serviceName) {
       this.serviceName = serviceName;
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description;
   }

   public double getPrice() {
       return price;
   }

   public void setPrice(double price) {
       this.price = price;
   }

   public String getAvailability() {
       return availability;
   }

   public void setAvailability(String availability) {
       this.availability = availability;
   }
   public void setProviderID(String providerID) {
       this.providerID = providerID;
   }

  public String getProviderID() {
       return providerID;
   }
  
}
