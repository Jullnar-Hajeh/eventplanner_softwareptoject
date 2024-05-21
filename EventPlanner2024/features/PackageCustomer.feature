Feature: Package Selection for Customers

  Scenario: Customer selects a package
    Given the customer is logged in
    When the customer selects a package with ID "1"
    Then the package should be added succesfully 
    