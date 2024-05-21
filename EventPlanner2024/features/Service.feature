Feature: Service Provider Service Management
  As a service provider (organizer/vendor)
  I want to manage my service details
  So that I can provide accurate information to potential clients

   Background:
    Given the service provider is logged in
    And the service provider has the following services in their profile:
      |ServiceType   | ServiceName           | Description                          | Price | Availability           |
      |Catering      | Catering Service      | Exquisite catering for events       | 500.0 | Available for booking  |
      |Entertainment | Live Band Service      | Energetic live band for events       | 800.0 | Not Available          |
      |DJ            | Professional DJ Service| DJ services for parties and events   | 600.0 | Available for booking  |
      |Photographer  | Event Photography     | Capturing memorable moments          | 900.0 | Available for booking  |
  

  Scenario: View Service Details
    When the service provider selects View your Service Details
    Then the system should display a list of the providers services with their details

 Scenario: Add New Decorations Service
    When the service provider selects Add New Service
    And the service "Decorations" does not exist
    And enters details for a new decorations service like
      | ServiceName                  | Price| Availability          | Description                       |
      | Floral Decor Service         | 500  | Available for booking | Beautiful floral arrangements     |
    Then the new decorations service should be added to the providers profile
    
Scenario: Edit Catering Service Details
    When the service provider selects Edit your Service Details for exist servicetype "Catering"
    And modifies the service information like price "700"
    Then the system should update the catering service details accordingly

  Scenario: Delete Entertainment Service
    When the service provider selects exist servicetype "Entertainment"
    And selects deletion
    Then the Entertainment service should be removed from the providers profile
    