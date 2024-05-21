
package org.example;



import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;





public class Customer extends User {
    private String email;
    private static final Logger logger = Logger.getLogger(Customer.class.getName());

   public static final List<Event> Cevents = new ArrayList<>();
   
   
    static Printing printing = new Printing();
	Functions f =new Functions();
	 
	
   public static List<Event> getCevents() {
        return Cevents;
    }
    
public static List<Event> getEvents() {
    return new ArrayList<>(Cevents);
}

    public static void addEvent(Event event) { Cevents.add(event); }
	
            
   
    public Customer() {}
    public Customer(String id, String username, String phone, String address,String pass, String email) {
        super(username, pass, "Customer");
        this.address = address;
        this.phone=phone;
        this.id=id;
        this.email=email;
        
       
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;} 
   
    
   
    
   
   public static void addCustomerToFile(Customer customer) {
    try (FileWriter customersFile = new FileWriter("customer.txt", true)) {
        customersFile.append(customer.getId()).append(" , ")
                .append(customer.getUsername()).append(" , ")
                .append(customer.getphone()).append(" , ")
                .append(customer.getaddress()).append(" , ")
                .append(customer.getEmail()).append(" , ")
                .append(customer.getPassword())
                .append("\n");
    } catch (IOException e) {
        printing.printSomething("An error occurred: " + e.getMessage());
    }
   }

    
 
    
    
  public static Customer getCustomerFromLine(String line){
      Customer customer1 = new Customer();
      String[] items = line.split(" , ");
      if (items.length >= 6) {
          customer1.setId(items[0]);
          customer1.setName(items[1]);
          customer1.setPhone(items[2]);
          customer1.setAddress(items[3]);
          customer1.setEmail(items[4]);
          customer1.setPassword(items[5]);
     } else {
      
logger.warning(() -> "Invalid line format: " + line);


      }
      return customer1;
  }
    
   
  
  
  
  public String toString() {
	    return "Customer{" +
	            "id='" + getId() + '\'' +
	            ", username='" + getUsername() + '\'' +
	            ", phone='" + getphone() + '\'' +
	            ", address='" + getaddress() + '\'' +
	            ", email='" + getEmail() + '\'' +
	            ", password='" + getPassword() + '\'' +
	           '}';
	}
  
  
  
 


}
