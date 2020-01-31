@FunctionalTest
Feature: News Site
As a user of Guardian news viewer,
I want to view the news from news section
I also want to verify the news information is correct by referring from other sources


Background:
  Given I launches the news site

@SmokeTest
  Scenario: To verify news on guardian site is valid by referring the same news from some other sources
    When I clicks on first news link
    Then I read the news headline
    And I search news headline in some other sources
    And I verify the content by using highest matching percentage of headline words   
  
@SmokeTest
  Scenario: To verify news on guardian site is not valid by referring the same news from some other sources
    When I clicks on first news link
    Then I read the news headline
    And I search some other sources with random news text
    And I verify the content by using highest matching percentage of headline words

