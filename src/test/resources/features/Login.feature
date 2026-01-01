Feature: Login Feature

  Scenario: Positive Login Test
    Given I open Koel Login Page
    When I enter email "ricardo.lu@testpro.io"
    And I enter password "DVNeY4ER"
    And I click submit
    Then I am logged in