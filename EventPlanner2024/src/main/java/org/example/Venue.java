package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Venue {
	private String venueId;
	 private String name;
	    private String address;
	    private int capacity;
	    private double price;
	    private String availability;
	    private String imagepath;
	    private String date;

	   private static final String ERROR_PREFIX = "An error occurred: ";
	    static final String VENUE_FILE_NAME = "venue.txt";
	    static final String VENUEBOOK_FILE_NAME = "venuebook.txt";   
	    static Printing printing = new Printing();
	    private static final String FORMAT = "yyyy-MM-dd";

	
	    public Venue(String name, String address, int capacity, double price,String availability,String id,String image) {
	        this.name = name;
	        this.address = address;
	        this.capacity = capacity;
	        this.price = price;
	        this.availability=availability;
	        this.venueId=id;
	        this.imagepath=image;
	    }

	    public Venue() {
			
		}

		public Venue(String venueId2, String name2, String address2, int capacity2, double price2,String image) {
			this.address=address2;
			this.capacity=capacity2;
			this.name=name2;
			this.price=price2;
			this.venueId=venueId2;
			this.imagepath=image;
		}
		
		
		public Venue(String name , String address , int capacity , 
				String imagepath, double price , String availability,String date ) 
		{
			this.name= name ;
			this.address=address;
			this.capacity=capacity;
			this.imagepath=imagepath;
			this.price=price;
			this.date=date;
			this.availability=availability;
		}


		
		

public static void updateVenueInVenueBook(String eventId, Date newDate, String venueBookFileName) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
       
	try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
           StringBuilder sb = new StringBuilder();
           String line;
           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length >= 5 && parts[4].equals(eventId)) {
               	 Date date;
                    try {
                        date = dateFormat.parse(parts[2]);
                    } catch (ParseException e) {

                    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
                       
                    }
                    date=newDate;
                   line = String.join(",", parts);
               }
               sb.append(line).append("\n");
           }
           // Write the updated content back to the file
           try (FileWriter writer = new FileWriter(venueBookFileName)) {
               writer.write(sb.toString());
           }
       } catch (IOException e) {
    	   printing.printSomething( ERROR_PREFIX + e.getMessage());
       }
   }
   
   
   
public static void updateVenueInVenueBook(String eventId, String newV, String venueBookFileName) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
	    try (BufferedReader reader = new BufferedReader(new FileReader(venueBookFileName))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[4].equals(eventId)) {
                // Find the venue ID based on the new venue name
                String venueId = findVenueIdByName(newV, VENUE_FILE_NAME );
                if (venueId != null) {
                    Date date = new Date();
                    try {
                        date = dateFormat.parse(parts[2]);
                        parts[0] = venueId; // Update the venue ID
                        parts[2] = dateFormat.format(date); // Update the date part
                    } catch (ParseException e) {
                    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
                        
                    }
                } else {
                    // Handle case where venue ID is not found for the new venue name
                    // You can log an error message or handle it according to your requirements
                }
            }
            line = String.join(",", parts); // Construct the updated line
            sb.append(line).append("\n");
        }
        
        // Write the updated content back to the file
        try (FileWriter writer = new FileWriter(venueBookFileName)) {
            writer.write(sb.toString());
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
}



   
   
public static String findVenueIdByName(String venueName, String filename) {
    String id = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[1].trim().equals(venueName.trim())) {
                id = parts[0].trim(); // Found the venue name, return its ID
                break; // No need to continue searching
            }
        }
    } catch (IOException e) {
    	 printing.printSomething( ERROR_PREFIX + e.getMessage());
    }
    return id;
}







	
		public void setdate(String d) {
			this.date=d;
		}
		
		public String getdate() {
			return date;
		}

	    public String getId() {return venueId;}
	    public void setId(String id) {this.venueId=id;}
	    
	    public String getAvailavility() { return availability;}
	    public void setAvailability(String availability) {this.availability=availability;}

	    public String getName() {
	        return name;
	    }
	    public String getImage() {
	    	return imagepath;
	    
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public void setImage(String i)
	    {this.imagepath=i;}

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public int getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
	    
	    public String toFileString() {
	        return venueId + "," + name + "," + address + "," +imagepath+"," + capacity + "," + price;
	    }
}
