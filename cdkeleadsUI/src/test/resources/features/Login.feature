Feature: eLeads Automation

  @ELSALES_398
  Scenario Outline: validate the adding newsletter
    Given Read the test data  "<TestData>" from Excel file
    When User navigate to site url
    When I Enter the user name
    And I Enter the Password
    When I Click on Login button
    Then Validate login successfull and check the dashboard title

    Examples: 
      | TestData  |
      | TestData1 |   
    

  @ELSALES_427 @TFS-2342 @NotReady
  Scenario Outline: Validate the basic regression test on eLeads
    Given Read the test data  "<TestData>" from Excel file
    When User navigate to site url
    When I Enter the user name
    And I Enter the Password
    When I Click on Login button
    Then Validate login successfull and check the dashboard title
    When I Click on Prospects Menu item from left hand side
    And I Click on Add Prospect submenu under Prospects
    When I Click on Add plus button on the top
    Then I Validate the title of the page
    When I Enter the FirstName and Last name
    And I Click on Search button
    Then I Validate the no search results lable
    When I Click on No Match - Add New Sales Customer link
    Then Validate the Firstname and Last Name entered successfully
    When Enter the Address details Zip and click on house button select city
    And Enter the Phone number along with primary email address
    When I select the Showroom up as type
    And I Select the google as a Source
    When I CLick on Save button
    And I Click on 24PhoneCall Activity by clicking on Complete
    When I Enter the comments
    And I CLick on Complete Activity
    When I Click on Schedule at the top of the Opportunity
    And I Click on Schedule Task of Send Email /Letter  and click on Schedule
    When Click on Complete on Send Email / Letter task
    And I CLick on Send Email in Process Activity Page
    When I Add Email Address in To: Field
    And I Enter the comments, subject and Body of the message
    When I Click on Send button
    And I CLick on DeskLog link on left side of the page
    Then User verify the Customer is now on the desklog for today

    Examples: 
      | TestData  |
      | TestData1 |
