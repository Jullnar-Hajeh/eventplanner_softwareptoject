Feature: Register User

  @tag1
  Scenario: Register User successful

    Given there is a User with User ID "id" ,NAME "User name" , PASSWORD "pass " , phone "phone", address "address"
    When the User is registered "User name"
    
  @tag2
  Scenario: a User that is already registered

    Given there is a User with User ID "id" ,NAME "User name" , PASSWORD "pass " , phone "phone", address "address"
    When the User is registered "User name"
    
   