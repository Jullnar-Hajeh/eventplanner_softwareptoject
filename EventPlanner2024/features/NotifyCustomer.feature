Feature: Notify Customer by Email

  Scenario: Customer event request is accepted
    Given there is an existing event request for a customer
    When the event request is accepted by the administrator
    Then an message notification is sent to the customer informing them about the acceptance

  Scenario: Customer event request is rejected
    Given there is an existing event request for a customer
    When the event request is rejected by the administrator
    Then an message notification is sent to the customer informing them about the rejection
    