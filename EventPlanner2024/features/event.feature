Feature: Event Management

Scenario: Customer Creates an Event 
Given the customer is going to create an event 
When the customer enters the event details such as Date "2024-04-14", time "5:00pm", description "A_celebration", attendeeCount "300", name "omar_party",category "Birthday" ,theme "Decades_Theme",Venue "Palestine_Convention_Center",eventid "2000"
Then the event is added to admin requst

Scenario: Cancels an Existing Event 
Given there is an existing event  
When cancel event selected 
Then the event deleted

Scenario: Cancels an non_Existing Event   
Given there is an non_existing event 
When cancel event selected 
Then  non_Existing massage



#Scenario:  Update Event Details
#Given selects the option to update the event details
#And there is an nonexisting event to update  
#Then the event details are successfully updated in the system

Scenario: Administrator Creates an Event
Given the administrator is going to create an event
When the administrator enters the event details such as Date "2024-06-24", time "5:00 pm", description "A celebration", attendeeCount "200", name "reem party",category "Birthday" ,theme "Decades Theme",Venue "Palestine_Convention_Center",eventid "1000"
Then the event is successfully created in the system


Scenario: search acoording event name 
Given the customer is going to search about event
When the customer enters the event details such as event name "reem_party"
Then the system should display all events matching the name 



Scenario: search acoording event name 
Given the customer is going to search about event
When the customer enters the event details such as venue name "Nablus_Event_Center"
Then the system should display all events matching the name 



Scenario: Update Event Name
  Given the user wants to update event details
  When they update the event name to "Ali_party"
  Then the system should successfully save the changes

Scenario: Update Event Date
  Given the user wants to update event details
  When they update the event date to "2024-05-01"
  Then the system should successfully save the changes

Scenario: Update Event Time
  Given the user wants to update event details
  When they update the event time to start at "12:00 PM"
  Then the system should successfully save the changes

Scenario: Update Event Description
  Given the user wants to update event details
  When they update the event description to "New description"
  Then the system should successfully save the changes

Scenario: Update Event Attendee Count
  Given the user wants to update event details
  When they update the attendee count to "100"
  Then the system should successfully save the changes

Scenario: Update Event Theme
  Given the user wants to update event details
  When they update the event theme to "New Theme"
  Then the system should successfully save the changes

Scenario: Update Event Category
  Given the user wants to update event details
  When they update the event category to "New Category"
  Then the system should successfully save the changes

Scenario: Update Event Venue
  Given the user wants to update event details
  When they update the event venue to "Palestine_Convention_Center"
  Then the system should successfully save the changes

Scenario: Update Event Services
  Given the user wants to update event details
  When they update the event services to include "100"
  Then the system should successfully save the changes
