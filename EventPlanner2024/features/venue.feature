Feature: Venue Management for Admin


Scenario: Add Venue
Given that the admin is logged into the system
When select to add a new venue such as details "NABLUS123", 120.0, " Al-Quds_St_Nablus", "nablus_venue_image.jpg", "Nablus_Event_Center", 150
Then the admin can successfully add the venue to the system.

#Scenario: Edit Venue
#Given that the admin is logged into the system
#When select to edit venue details by its 1 and "VenueId"
#Then the admin can successfully modify venue information.



Scenario: Edit venue details
    When select to edit venue details with the following changes:
      | Field    | New Value              |
      | Address  | Palestine_Street_123  |
      | ID       | P123                 |
      | Name     | Palestine_ConventionCenter |
      | Capacity | 200                    |
      | Price    | 1500.0                 |
      | Image    | palestine_image.jpg    |



Scenario: Delete Venue
Given that the admin is logged into the system
When select to delete a venue by its "VenueId"
Then the admin can successfully remove the venue from the system.

Scenario: View All Venues
Given that the admin is logged into the system
When select to view all venues registered in the system
Then the admin can see a comprehensive list of all venues.




