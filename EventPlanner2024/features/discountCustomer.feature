Feature: Apply Discount

  Scenario: Applying a valid discount
    Given the discount code "DISCOUNT1" is valid
    When the customer applies the discount code "DISCOUNT1" to a price of $100
    Then the discounted price should be $90

  Scenario: Applying an expired discount
    Given the discount code "EXPIRED_DISCOUNT" is expired
    When the customer applies the expired discount code "EXPIRED_DISCOUNT" to a price of $100
    Then the discounted price should remain $100

  Scenario: Applying an invalid discount code
    Given the discount code "INVALID_DISCOUNT" is not valid
    When the customer applies the invalid discount code "INVALID_DISCOUNT" to a price of $100
    Then the discounted price should remain $100
    