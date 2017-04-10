Scenario:  User provides correct login and password

Given user is on loginpage page
When user inserts in usernameOrEmail a whooles and inserts in password a Vfrcbv-2008 and
When clicks btn-primary button
Then user see Signout option

Scenario: User provides incorrect login and password

Given user is on loginpage page
When user inserts in usernameOrEmail a Gog and inserts in password a PowerRangers and
When clicks btn-primary button
Then user still see Sign In option 

Scenario: User enters page after login

Given user is on loginpage page
When user inserts in usernameOrEmail a whooles and inserts in password a Vfrcbv-2008 and
When clicks btn-primary button
When clicks navbar-brand button
Then user see Signout option