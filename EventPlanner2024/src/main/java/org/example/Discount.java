

package org.example;

public class Discount {
    private int discountId;
    private String discountCode;
    private double discountPercentage;
    private String validityPeriod;
 

    public Discount(  double discountPercentage,int discountId, String validityPeriod,String discountCode) {
        this.discountId = discountId;
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.validityPeriod = validityPeriod;
    }
    public Discount()
    {} 
   public double getper() {return discountPercentage; }
   public String getV() {return validityPeriod;}
    public void setsiscountid(int id) {
    	this.discountId=id;
    	
    }
    public int getDiscountId() {
    	return discountId;
    }
    
    public void setdiscountcode(String code) {
    	this.discountCode=code;
    	
    }
    
    public void setdiscountpercentage(double percent) {
    	this.discountPercentage=percent;
    	
    }
    
    public void setvalidity(String validaty) {
    	this.validityPeriod=validaty;
    }
    
    @Override
    public String toString() {
        return discountId + "," + discountCode + "," + discountPercentage + "," + validityPeriod ;
    }
}
