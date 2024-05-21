package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Paackage {
private int id;
private String description;
private double price ;
private String validityPeriod;
private String title; 
private static final String ERROR_MESSAGE = "An error occurred while writing package details to file: ";
public Paackage(){}
 static final String PACKAGE_WITH_ID = "Package with ID ";
 static final String ALL_PACKAGES = "All Packages:";
static Printing print=new Printing ();
public void setId(int id) {
    this.id = id;
}

public void setDescription(String description) {
    this.description = description;
}

public void setPrice(double price) {
    this.price = price;
}

public void setValidityPeriod(String validityPeriod) {
    this.validityPeriod = validityPeriod;
}

public void setTitle(String title) {
    this.title = title;
}

// Getter methods
public int getId() {
    return id;
}

public String getDescription() {
    return description;
}

public double getPrice() {
    return price;
}

public String getValidityPeriod() {
    return validityPeriod;
}

public String getTitle() {
    return title;
}




public Paackage(int id2, String description,double price)
{
	this.id=id2; this.description=description;this.price=price; 
	
}

@Override
public String toString() {
    return "Package ID: " + id +
            "\nName: " + title +
            "\nDescription: " + description +
            "\nPrice: " + price +
            "\nValidity Date: " + validityPeriod +
            "\n";
}
public static void addPackageToFile(String filename, String packageDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(packageDetails);
        writer.newLine();
    } catch (IOException e) {
    	print.printSomething(ERROR_MESSAGE + e.getMessage());
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
    	print.printSomething(ERROR_MESSAGE + e.getMessage());

    }
    return false;
}










public static void savePackagesToFile(String filename, List<Paackage> packages) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Paackage p : packages) {
            writer.write(p.toFileString());
            writer.newLine();
        }
    } catch (IOException e) {
    	print.printSomething(ERROR_MESSAGE + e.getMessage());

    }
}
public String toFileString() {
    return id + "," + title + "," + description + "," + price + "," + validityPeriod;
}



public static void updatePackageValidityDate(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new validity date (YYYY-MM-DD): ");
    String newValidityDate = scanner.nextLine();
    packageToUpdate.setValidityPeriod(newValidityDate);
    print.printSomething("Validity date is updating successfully.");

    
    savePackagesToFile(filename, packages);
}


public static void updatePackageDescription(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new description: ");
    String newDescription = scanner.nextLine();
    packageToUpdate.setDescription(newDescription);
    print.printSomething("Description is updating successfully.");

    
    savePackagesToFile(filename, packages);
}

public static void updatePackagePrice(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new price: ");
   
	double newPrice = scanner.nextDouble();	
   // scanner.nextLine(); // Consume newline character
    packageToUpdate.setPrice(newPrice);
    print.printSomething("Price is updating successfully.");

    savePackagesToFile(filename, packages);
}

public static void updatePackageId(Scanner scanner, Paackage packageToUpdate, String filename) {
    String newId;
    int newIdD;
    do {
    	 print.printSomething("Enter a new ID: ");
        newId = scanner.next();
       newIdD=Integer.parseInt(newId);
        
        if (!isPackageIdExists(filename, newIdD)) {
            packageToUpdate.setId(newIdD);
            print.printSomething("ID is updating successfully.");
            break;
        } else {
        	 print.printSomething("ID already exists in the file. Please enter a new ID.");
        }
    } while (true);
}




public static void viewAllPackages(List<Paackage> packages) {
    if (packages.isEmpty()) {
    	print.printSomething("No packages found in the file.");
        return;
    }

    print.printSomething(ALL_PACKAGES);
    for (Paackage p : packages) {
    	print.printSomething("ID: " + p.getId() +
                "  Name: " + p.getTitle() +
                "  Description: " + p.getDescription() +
                "  Price: " + p.getPrice() +
                "  Validity Date: " + p.getValidityPeriod());
    }
}


public static void updatePackageTitle(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new title: ");
    String newTitle = scanner.nextLine();
    packageToUpdate.setTitle(newTitle);
    print.printSomething("The title is updating successfully.");

   
    savePackagesToFile(filename, packages);
}


}


