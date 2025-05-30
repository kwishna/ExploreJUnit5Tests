@all @ui
Feature: Test login feature
  Background:
    Given User is on login page "https://fb.com/"

  @ValidCredentials
  Scenario: Login with valid credentials

  When User enters username as "Admin" and password as "admin123"
  Then User should be able to login successfully and new page open

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials

  When User enters username as "<username>" and password as "<password>"
  Then User should be able to see error message "<errorMessage>"

  Examples:
    | username   | password  | errorMessage                      |
    | Admin      | admin12$$ | Invalid credentials               |
    | admin$$    | admin123  | Invalid credentials               |
    | abc123     | xyz$$     | Invalid credentials               |

