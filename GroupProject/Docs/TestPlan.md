# Test Plan

**Author:** Adric Cain (acain33)

**Team:** Di Wang (dwang383), Ed Redmond (eredmond6), Jongho Jung (jjung327)

## 1 Testing Strategy

This document contains the testing strategy for Team 75 Spring 2017 CS6300.

### 1.1 Overall strategy

The overall testing strategy will be using mixing functional and UI testing on the Android application. The tests will be broken down into the following list:

1. Unit testing will be completed by the software developer and test whether the unit they are building is working with nominal values before being pushed into the code repository.

2. Integration testing will occur by the developer and tester of the android application. When multiple units are submitted to the overall project a developer will pull and validate that their unit does not cause errors. In addition, the tester will pull the full repository and run the test cases to make sure that no bugs occur in the process.

3. System testing will be completed once the whole system is built by the developers. It will encompass all the unit and integration that has been completed. The final test for the release of the system will be when all the tests have passed satisfactory.

4. Regression testing will occur when any bug occurs or a change request is made to the system. The developer will update the code and the tester will verify that all values from the previous system test hold true or that the bug in the code is fixed and the correct information is being displayed in the system.

### 1.2 Test Selection

The testing method that will be preferred is black box unit testing. The test method will cover the major points of the requirements and check the actions against what is the expected behavior.

### 1.3 Adequacy Criterion

The test cases will be assessed on how well they verify the requirements of the system. The test cases will be built in order to cover the original requirements by the customer and additional requirements stated in [Extra Requirements](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/ExtraRequirements.md). All the use cases for the application design are captured in the [Use Case Model](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/UseCaseModel.md) document.

### 1.4 Bug Tracking

Bug tracking will be handled using a form method. We would like to capture the user information that encountered the bug, the screen the bug occurred on and a short description of the bug itself. This gives a starting point to reproduce the bug by the developers and a reach back to help them understand more if they cannot produce the bug.

Enhancements will also be handled by a form method. The user would submit their information, the suggested enhancement and the reason they think this feature should be added. These will all be aggregated together by a quality control analyst and sorted by number of instances to determine the enhancement priority.

### 1.5 Technology

The majority of the testing method will be via JUnit. We will also use the Intellij IDEA IDE to help in the testing process by finding bugs that would cause the code not to build.

## 2 Test Cases

All test cases will assume basic knowledge by the tester. The assumption of each test case is the tester has the application opened on a nexus 5 devices as a starting point. If the testing device does not have the app open all the user needs to do is click the TourneyManager application to launch the application.

**Test 1:** Adding Players

This tests validates that the add feature works in the system and the manager can add a player to the database.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
2 | Click Add Player | Opens Screen to input player information | Opens 'Add Player' screen | Pass |
3 | Enter Name | Name typed into system | Name of player is entered in the system | Pass | A 'First Last' name format is appropriate
4 | Enter Username | Username typed into system | Username of player is entered in the system | Pass |
5 | Enter Phone Number | Phone number typed into system | Phone Number is entered in the system | Pass | A '555-555-5555' format is appropriate 
6 | Click Add Player button to save information to system | Player information saved to database | Player information is added to the system and returned to the Management screen | Pass |
7 | Click Show Player List | Opens Player information for manager to view | Player List is opened | Pass |
8 | Verify Player exist in system | Player exists in the system to view | Player is in the Player List | Pass | Player will be added at the bottom of the default database population

**Test 2:** Check illegal inputs

This tests validates that the application blocks illegal inputs from being used to start a tournament.

*PreReq:* Minimum of 16 players in the system

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
2 | Click Start A Tournament | Opens Screen to input tournament information | Start Tournament screen is displayed | Pass |
3 | Enter House Percentage | Illegal House cut typed into system | Integer value is entered into field  | Pass | Invalid values are 0 or 100
4 | Click button Show Tournament Info | Invalid input message | Error is displayed  | Pass |
5 | Enter Entry Price | Illegal Price typed into system | Integer value is entered into field | Pass | Invalid value is 0
6 | Click button Show Tournament Info | Invalid input message | Error is displayed  | Pass |
7 | Select Players | Illegal number of players selected | Players added to system | Pass | Invalid number of players are any combination not adding to 8 or 16
8 | Click button Show Tournament Info | Invalid input message | Error is displayed  | Pass |
9 | Repeat for additional illegal values | Start tournament fails for all illegal values tested | All illegal values give errors | Pass | Illegal values all cause error messages

**Test 3:** Tournament with no player input

This tests validates that a default/no deck choice will not cause the system to fail.

*PreReq:* Minimum of 8 players in the system

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
2 | Click Start A Tournament | Opens Screen to input tournament information | Start Tournament screen is displayed | Pass |
3 | Enter House Percentage | House cut typed into system | Integer value is entered into field  | Pass | For testing 50 is appropriate
4 | Enter Entry Price | Price typed into system | Integer value is entered into field | Pass | For testing 10 is appropriate
5 | Select Players | 8 players selected | Players added to system | Pass | 
6 | Click button Show Tournament Info | Tournament info is displayed | Tournament prize values are displayed  | Pass |
7 | Click Start Tournament | Tournament begins | Tournament page is displayed | Pass |
8 | Select a match | Match information opens | Manager has ability to Start match | Pass |
9 | Click Start Match | Match starts | Match is set to ongoing | Pass |
10 | Select an ongoing match | Match information opens | Manager has ability to select winner | Pass |
11 | Select the first player as a winner | First player is selected | Manager selects first player | Pass |
12 | Click End Match | First player wins the match | Match status is complete with player one being the winner | Pass |
13 | Repeat Steps 8 through 12 for all matches | Tournament finishes with the first player in each match winning | Player 1 wins the tournament | Pass |
14 | Click End Tournament | Tournament ends with the first player in each match winning | Tournament is ended and returned to the Management Screen | Pass |

**Test 4:** Players can view match list

This tests validates that players can view the match list.

*PreReq:* The tester has access to both the Management and Player features

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
2 | Click Start A Tournament | Opens Screen to input tournament information | Start Tournament screen is displayed | Pass |
3 | Enter House Percentage | House cut typed into system | Integer value is entered into field  | Pass | For testing 50 is appropriate
4 | Enter Entry Price | Price typed into system | Integer value is entered into field | Pass | For testing 10 is appropriate
5 | Select Players | 8 players selected | Players added to system | Pass | 
6 | Click button Show Tournament Info | Tournament info is displayed | Tournament prize values are displayed  | Pass |
7 | Click Start Tournament | Tournament begins | Tournament page is displayed | Pass |
8 | Close the application | Application is not running | Application is closed and not running on the system | Pass | 
9 | Open Tournament Manager application | Application is running | Application is running on system with the Manager/Player select screen | Pass | 
10 | Click Player | Opens ongoign tournament screen | List of Matches is displayed | Pass | 
11 | Click Match | Player is able to see match information | Match information is viewable | Pass | Match information should all be viewable without being clicked

**Test 5:** Manager view winnings

This tests validates that a manager can view house and player winnings.

*PreReq:* Test 3 has been completed at least once.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
2 | Click View House Profits | View all house cuts from previous tournaments | Past Profits screen is displayed | Pass |
3 | Return to Management screen | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass |
4 | Click Show Player List | View a list of all players that have win prizes | List of all players and total prize amount is displayed | Pass |
5 | Click Player name | Opens Player Profile Screen | Player Profile is opened | Pass |
6 | View Player Profit | View a list of all prize money the player has won | Player Profile displays all tournament winnings | Pass |

**Test 6:** Player view winnings

This tests validates that a player can view their own total winnings.

*PreReq:* Test 3 has been completed and Player is one of the winners.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Player | Opens Player Profile Screen | List of players and total prize is displayed | Pass | 
2 | View Player Profits | View a list of all prize money the player has won | Player list and prize is viewable | Pass |

**Test 7:** Removing players

This tests validates that the system does not fault when players are removed that have won money or not.

*PreReq:* Test 3 has been completed.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
2 | Click Show Player List | Opens Player information for manager to view | Player List is opened | Pass |
3 | Click Player who has not won prizes | Opens Player Profile Screen | Player Profile is opened | Pass | 
4 | Click Remove Player | Removes player information | Player is removed and user is returned to the Player List | Pass |
5 | Click Player who has won prizes | Opens Player Profile Screen | Player Profile is opened | Pass |
6 | Click Remove Player | Removes player information | Removes player and returns to Player List | Pass |
7 | Return to Management screen | Opens Management Screen | Opens Management screen with 'Show Player List', 'View House Profits', 'Start a Tournament', and 'Add Player' | Pass | This screen will show unless someone has started a tournament on the device prior to testing
8 | Click Show Player List | Opens Player information for manager to view | Player List is opened | Pass |
9 | Verify values | Verify deleting did not affect results of other players | Values are all correctly displayed | Pass |


### Additional Test Cases if schedule permits extra features

**Test E1:** Players can add decks

This tests validates that players can choose decks to use in a tournament.

*PreReq:* Test 1 has been completed at least once.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Player | Opens Player Screen | | | 
2 | Select player username | Player profile is displayed | | |
3 | Click Deck Options dropdown | List of decks is displayed | | |
4 | Select deck | Deck is added to players deck options in the database | | |
4 | Verify deck change | Deck is changed | | |

**Test E2:** Tournament works with all valid information

This tests validates that the tournament will run with players and their own deck choices without fail.

*PreReq:* Test 3 has been completed at least 8 times.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click Tournament | Opens Screen to input tournament information | | |
3 | Enter Cut | House cut typed into system | | |
4 | Enter Price | Price typed into system | | |
5 | Enter User Names | User Names typed into system | | |
6 | Click button to start tournament | Tournament begins | | |
7 | Select a match | Match information opens | | |
8 | Select the first player as a winner | First player wins the match | | |
9 | Repeat Steps 7 and 8 for all matches | Tournament finishes with the first player in each match winning | | |
10 | Click view Results | Tournament runs as expected | | |
