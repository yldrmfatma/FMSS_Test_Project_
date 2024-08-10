Feature: Login Functionality

  @false_login
  Scenario: Unsuccessful login with invalid credentials
    Given The user navigates to the login page
    When The user enters username "Invalid User" and password "Invalid Pass"
    Then The user should see an error message


  @true_login
  Scenario:  Successful login with valid credentials
    Given The user navigates to the login page
    When The user enters valid credentials
    Then The user should be redirected to the dashboard





