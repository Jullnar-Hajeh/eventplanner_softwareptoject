Feature: Offer and Package Management

Scenario: when an administrator wants to create a new service package
Given the administrator is logged into the system
When the administrator specifies the package details such as "title", "description", 5, 400.5 , " 2020-03-04"
Then the new Package is successfully added to the system


    Scenario: Administrator deletes a service package
    Given the administrator is logged into the system
    When the administrator deletes the package
    Then the package with id 5 should be successfully deleted
    
    Scenario: Administrator updates a service package
    Given the administrator is logged into the system
    When the administrator updates the package 
    And acording to 1 and "value" 
     And acording to 2 and "11" 
      And acording to 3 and "50" 
       And acording to 4 and "value" 
        And acording to 00 and "value" 
    Then the package  should be successfully updated
    
    
    Scenario: Administrator views all available service packages
    Given the administrator is logged into the system
    When the administrator selects to view all packages
    Then the system should display a list of all available packages
    
Scenario: Administrator updates a service package with a valid new ID
Given the administrator is logged into the system
When the administrator updates the package according to "123"
    Then the package  should be successfully updated


