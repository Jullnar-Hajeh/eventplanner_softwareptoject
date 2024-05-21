Feature: Calendar and Notifications

  Scenario: View upcoming events
    Given the user is logged in
    When the user opens the calendar
    Then the user should see a list of upcoming events


  Scenario: Receive notification for upcoming event
    Given the user is logged in
    And there is an upcoming event
    When the notification system checks for upcoming events
    Then the user should receive a notification for the event
