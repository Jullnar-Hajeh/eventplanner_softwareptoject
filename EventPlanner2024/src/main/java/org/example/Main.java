package org.example;

import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) throws Exception {
        Functions functions = new Functions();
        functions.startApproachingUpcomingEvents();
        Scanner scanner = new Scanner(System.in);
        Printing printing = new Printing();
        
        boolean running = true;
        while (running) {
            printWelcomeMenu();
            int choice = getValidIntegerInput(scanner, printing);
            
            switch (choice) {
                case 1:
                    handleSignUpMenu(scanner, printing, functions);
                    break;
                case 2:
                	 functions.signInFunction();
                    break;
                case 3:
                    running = false; // Set running to false to exit the loop
                    break;
                default:
                    printing.printSomething("Invalid choice! Please enter a valid choice.");
                    break;
            }
        }
    }
    
    private static void printWelcomeMenu() {
        Printing printing = new Printing();
        printing.printSomething("""
        ------ Welcome to Home Page ------
        |                                |
        |          1. Sign up            |
        |          2. Sign in            |
        |          3. Exit               |
        |                                |
        ----------------------------------
        Enter your choice: 
        """);
    }
    
    private static int getValidIntegerInput(Scanner scanner, Printing printing) {
        while (!scanner.hasNextInt()) {
            printing.printSomething("Invalid input! Please enter a valid integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static void handleSignUpMenu(Scanner scanner, Printing printing, Functions functions) throws Exception {
        printing.printSomething("""
            ----------- SINGUP PAGE ----------
            |                                |
            |          1. CUSTOMER           |
            |          2. PROVIDER           |
            |          3. Exit               |
            |                                |
            ----------------------------------
            Enter your choice: 
        """);

        int choice2 = getValidIntegerInput(scanner, printing);
        
        switch (choice2) {
            case 1:
                functions.customerSignUp();
                break;
            case 2:
                functions.providerSignUp();
                break;
            case 3:
                // Exit the loop
                break;
            default:
                printing.printSomething("Invalid choice! Please enter a valid choice.");
                break;
        }

        }}


