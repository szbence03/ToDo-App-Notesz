Feature: Website functionality

  Scenario: Navigation
    Given I navigate to the website
    Then I should see no added notes
    When I add a note with the text "Test"
    Then I should see the added note with the text "Test"
    When I delete the note
    Then I should see no added notes
