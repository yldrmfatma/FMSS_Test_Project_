Feature: Search System Users by User Role

  @search_1
  Scenario: Ensure the search button is visible
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user clicks on admin button in the left menu
    And the user is on the User Management page
    Then the search button should be visible

  @search_2
  Scenario: Display system users with a randomly selected User Role
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user clicks on admin button in the left menu
    And the user is on the User Management page
    When the user selects a random role from the dropdown
    Then the user clicks on the search button
    Then the roles in the result table should match the selected role

  @search_3
  Scenario: Search for "Performance" in the navigation menu
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user types "Performance" in the search input of the navigation menu
    Then the user clicks on "Performance"
    And the user should be redirected to the "Performance" page

  @search_4
  Scenario: Display system users with a specific Username
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user clicks on admin button in the left menu
    And the user is on the User Management page
    Then the user enters username to the input field
    And the user clicks on the search button
    Then the usernames in the result table should match the searched username

  @search_5
  Scenario: Display system users with a specific Employee Name
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user clicks on admin button in the left menu
    And the user is on the User Management page
    Then the user enters employee name to the input field
    And the user clicks on the search button
    Then the user should see an invalid entry warning if no results are found

  @search_6
  Scenario: Display system users with a specific Employee ID and check no results found message
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user clicks on the PIM button in the left menu
    And the user is on the PIM page
    Then the user enters employeeId in the Employee ID input field
    And the user clicks on the search button
    Then the user should see a no results found message

  @search_7
  Scenario: Attempt to search timesheet with an empty Employee Name field
    Given the user is logged into the OrangeHRM system
    And the user is on the OrangeHRM Dashboard page
    When the user clicks on the Time button in the left menu
    And the user is on the Employee Timesheet page
    Then the user leaves the employee name input field empty
    And the user clicks on the View button
    Then the user should see a required entry warning