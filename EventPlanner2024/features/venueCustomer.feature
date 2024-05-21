Feature: Venue Availability

Scenario: Updating venue status to reserved when booked by user
  Given there is a venue available for booking
  When a user books the venue
  Then the status of the venue should be updated to reserved

Scenario: Notifying user when trying to book a reserved venue
  Given there is a venue that is already reserved
  When a user tries to book the venue
  Then a message should appear informing the user that the venue is unavailable