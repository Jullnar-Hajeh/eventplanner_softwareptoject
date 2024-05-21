Feature: Customer Actions
  Background: View Customer Page List
    Given customer log in with true "id" and "password"
Scenario: Update My Profile
    Given I am logged in
    When I navigate to My Profile
    And I update my profile information depends on 1 and "Ali"
    And I update my profile information depends on 2 and "059847832"
    And I update my profile information depends on 3 and "yafa"
    And I update my profile information depends on 4 and "Ali@gami.com"
    Then my profile should be updated successfully
Scenario: View Invoices
    Given I am logged in
    When I navigate to Invoices
    Then I should see a my invoice
Scenario: Delete My Profile
    Given I am logged in
    When I navigate to My Profile
    And I choose to delete my profile depends on "id"
    And I confirm the deletion
    Then my profile should be deleted successfully
    
