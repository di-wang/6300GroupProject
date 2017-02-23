# Test Plan

**Author**: acain33

**Editors**: dwang383, eredmond6, jjung327

## 1 Testing Strategy

### 1.1 Overall strategy

The overall testing strategy 
*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

### 1.2 Test Selection

The testing method that will be preferred is black box unit testing. The test method will cover the major points of the requirements and check the actions against what is the expected behavior. 

### 1.3 Adequacy Criterion

The test cases will be assessed on how well they verify the requirements of the system. The test cases will be built in order to cover the original requirements by the customer and additional requirements stated in [Extra Requirements](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/ExtraRequirements.md). All the use cases for the application design are captured in the [Use Case Model](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/UseCaseModel.md) document.

### 1.4 Bug Tracking

Bug tracking will be handled using a form method. We would like to capture the user information that encountered the bug, the screen the bug occured on and a short description of the bug itself. This gives a starting point to reproduce the bug by the developers and a reach back to help them understand more if they can not produce the bug.

Enhancements will also be handled by a form method. The user would submit their information, the suggested enhancement and the reason they think this feature should be added. These will all be aggrogated together by a quality control analyst and sorted by number of instances to determine the enhancement priority.

### 1.5 Technology

The majority of the testing method will be via JUnit. We will also use the Intellij IDEA IDE to help in the testing process by finding bugs that would cause the code not to build.

## 2 Test Cases

All test cases will assume basic knowledge by the tester. The assumption of each test case is the tester has the application opened on a nexus 5 device as a starting point.

**Test 1:** Adding Players

This tests validates that the add feature works in the system and the manager can add a player to the database.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click Add Player | Opens Screen to input player information | | |
3 | Enter Name | Name typed into system | | |
4 | Enter Username | Username typed into system | | |
5 | Enter Phone Number | Phone number typed into system | | |
6 | Click button to save information to system | Player information saved to database | | |
7 | Click View All Player Prizes | Opens Player information for manager to view | | |
8 | Verify Player exist in system | Player exists in the system to view | | |

**Test 2:** Check illegal inputs

This tests validates that the application blocks illegal inputs from being used to start a tournament.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click Enter Tournament | Opens Screen to input tournament information | | |
3 | Enter Cut | Illegal House cut typed into system | | |
4 | Enter Price | Illegal Price typed into system | | |
5 | Enter User Names | Illegal User Names typed into system | | |
6 | Click button to start tournament | Start tournament fails | | |
7 | Repeat for additional illegal values | Start tournament fails for all illegal values tested | | |

**Test 3:** Tournament with no player input

This tests validates that a default/no deck choice will not cause the system to fail.

*PreReq:* Test 1 has been completed a minimum of 8 times to create 8 players.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click Enter Tournament | Opens Screen to input tournament information | | |
3 | Enter Cut | House cut typed into system | | |
4 | Enter Price | Price typed into system | | |
5 | Enter User Names | User Names typed into system | | |
6 | Click button to start tournament | Tournament begins | | |
7 | Select a match | Match information opens | | |
8 | Select the first player as a winner | First player wins the match | | |
9 | Repeat Steps 7 and 8 for all matches | Tournament finishes with the first player in each match winning | | |
9 | Click view Results | Tournament runs as expected | | |

**Test 4:** Players can add decks

This tests validates that players can choose decks to use in a tournament.

*PreReq:* Test 1 has been completed at least once.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Player | Opens Player Screen | | | 
2 | Click Deck Options | List of decks is displayed | | |
3 | Select deck | Deck is added to players deck options in the database | | |

**Test 5:** Tournament works with all valid information

This tests validates that the tournament will run with players and their own deck choices without fail.

*PreReq:* Test 4 has been completed at least 8 times.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click Enter Tournament | Opens Screen to input tournament information | | |
3 | Enter Cut | House cut typed into system | | |
4 | Enter Price | Price typed into system | | |
5 | Enter User Names | User Names typed into system | | |
6 | Click button to start tournament | Tournament begins | | |
7 | Select a match | Match information opens | | |
8 | Select the first player as a winner | First player wins the match | | |
9 | Repeat Steps 7 and 8 for all matches | Tournament finishes with the first player in each match winning | | |
9 | Click view Results | Tournament runs as expected | | |

**Test 6:** Players can view match list

This tests validates that players can view the match list.

*PreReq:* Test 5 has been completed to step 7

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Player | Opens Player Screen | | | 
2 | Click Show Match List | Opens Screen to see current tournament matches | | |
3 | Click a Match | Player is able to see match information | | |

**Test 7:** Manager view winnings

This tests validates that a manager can view house and player winnings.

*PreReq:* Test 5 has been completed at least once.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click View Past Profits | Opens Screen to view all house cuts from previous tournaments | | |
3 | Return to Management screen | Opens Management Screen | | |
4 | Click All Players Total Prize | View a list of all players that have win prizes | | |
5 | Return to Management screen | Opens Management Screen | | |
6 | Click View Player Prizes | Opens an input to put a player name | | |
7 | Enter Player name | Player name typed into system | | |
8 | Click Show | Displays player prizes for input players name | | |

**Test 8:** Player view winnings

This tests validates that a player can only view their own winnings.

*PreReq:* Test 5 has been completed and Player is one of the winners.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Player | Opens Player Screen | | | 
2 | Click Total Prize | View a list of all prize money the player has won | | |

**Test 9:** Removing players

This tests validates that the system does not fault when players are removed that have won money or not.

*PreReq:* Test 5 has been completed.

Steps | Action | Expected Result | Actual Result | Pass/Fail | Comments
----- | ------ | --------------- | ------------- | --------- | --------
1 | Click Manager | Opens Management Screen | | | 
2 | Click Remove Player | Opens Screen to remove player information | | |
3 | Enter User name who has not won prizes | User name typed into system | | |
4 | Click Remove | Player removed from system | | |
5 | Repeat steps 2 through 4 with a user name that has won prizes | Player removed from system | | |
6 | Click button to start tournament | Start tournament fails | | |
7 | Return to Management screen | Opens Management Screen | | |
8 | Click All Players Total Prize | View a list of all players that have win prizes | | |
9 | Verify values | Verify deleting did not affect results of other players | | |
