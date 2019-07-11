Feature: Test login page and features

Scenario Outline:
	Given User is on login page
	Then Validate login page
	Then User <user> logs in
	Then Validate home page
	Then User sign out
	Then Validate login page
@qa
Examples:
|user	 |
|"userqa"|	
@uat
Examples:
|user|
|"useruat"|