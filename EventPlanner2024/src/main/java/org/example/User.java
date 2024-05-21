package org.example;
import java.util.ArrayList;
import java.util.List;


public class User {

    String password;
    String username;
    String address;
    String id;
    String phone;
    String type;
    private boolean logged;
    public  static final List<User> users1 = new ArrayList<>();

    public User()
    {
        User.getUsers1().add(new User(" yamamah ","1234","admin"));
     

    }
   
    public User(String name, String pass,String type)
    {
        this.username=(name);
        this.password=(pass);
        this.type=(type);
    }

    
    
    public String getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() { return password;}
    public boolean getLogstate() { return logged;}
    public String getaddress() { return address;}
    public String getphone() {return phone;}
    
    
    
     public void setId(String string) { this.id = string;}
     public void setName(String string) { this.username = string;} 
     public void setPassword(String string2) {this.password= string2;}
     public void setLogstate(boolean l) { this.logged = l;}
     public void setAddress(String string) {this.address = string;}
     public void setPhone(String string) {  this.phone = string;}
     public void setType(String string) {this.type = string;}
     

    

    public void loginCH(String string, String string2)
    { logged = string.equals(username) && string2.equals(password);
        setLogstate(logged); }

    public static void adduser(User l) {
        User u =new User();
        if(u.getLogstate())
        {
            getUsers1().add(l);

        }
    }
    public boolean isRegest(String string) {

        for (User user : getUsers1()) {
            if (user.getUsername().equals(string))
                return false;
        }
        return true;
    }

	 public static List<User> getUsers1() {
        return users1;
    }

	
   
}
