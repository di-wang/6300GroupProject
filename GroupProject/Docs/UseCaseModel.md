# Use Case Model

**Author**: Di Wang (dwang383)

**Team:** Adric Cain (acain33), Ed Redmond (eredmond6), Jongho Jung (jjung327)

## 1 Use Case Diagram

![UseCaseDiagram] (https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/UseCaseDiagram.png)


## 2 Use Case Descriptions

### Actor: Manager
####Login
* Requirements: The application allows manager to select manager mode. 
* Pre-conditions: Application is started.
* Post-conditions: Manager mode is switched on. 
* Scenarios: Start the application. The manager select his/her user type as Manager and input the login credentials. The manager mode will be switched on and display a list of functions. 

#### Add Player
* Requirements: The tournament manager uses the system to add players.
* Pre-conditions: The manager mode is selected. The player username is not duplicating any one already existing in the system.
* Post-conditions: A new player is added into the system. 
* Scenarios: The tournament manager input the all required information of a player (Name, username, phone number, deck choice), and click add a player. The system will verify if all information is complete and there is not a duplicate existing in the system. If any error, the system will display the error to the manager. Otherwise, the system will add the player into tournament.

#### Remove Player
* Requirements: The tournament manager uses the system to remove players.
* Pre-conditions: The manager mode is selected. There is at least one player and the input player username exists in the system. 
* Post-conditions: The player is removed from the system. 
* Scenarios: The manager input the username of the player to be removed from the system. The system will look up the username. If the username is found, the system will delete the found player from the system. If there is not player with that username found, the system will display an error and ask to try a different username.

#### Run Tournament
* Requirements: The tournament manager can start the tournament.
* Pre-conditions: House cut/Entry price/All player names have to be inputted. There are either 8 or 16 players. There is no ongoing tournament.
* Post-conditions: Tournament is ongoing.
* Scenarios: After the tournament manager has inputted all required information and double checked the information. The tournament will start, the manager and player mode will show a match list. 

#### Input Tournament Information
* Requirements: The tournament manager will enter the house cut. The tournament manager will enter the entry price. The tournament manager will enter all player usernames. When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit.
* Pre-conditions: The format of input data is correct. There is no ongoing tournament. The number of player usernames is either 8 or 16. 
* Post-conditions: The system will display the player names, the potential prizes and profit
* Scenarios: The manager input all the required data, and the system will check if input in each field is valid and if the number of usernames is 8 or 16. If all input is valid, the system will utilize TourneyCalc app to generate and display potential prizes and profits. After the manager verifies the information, the manager will decide to start this tournament or try a different set of input. 

#### Start Match
* Requirements:  In an ongoing tournament, the tournament manager will be able to start a match ready to be played by selecting it from the match list. The system will then mark the game between the specified players as started.
* Pre-conditions: The tournament is ongoing and there is at least one match ready to be started. 
* Post-conditions: The state of the selected match is marked as started. 
* Scenarios: When a tournament is ongoing, the manager mode display the match list. The manager select a match from the list and specify two players. The match state is marked as started. 

#### End Match
* Requirements:  The tournament manager will be able to end an ongoing match and specify a result for it.
* Pre-conditions: The tournament is ongoing and there is at least one match marked as started.
* Post-conditions: The match is marked as finished and the winner advances to the next round.
* Scenarios: A tournament is ongoing; the manager selects a started match from the match list and marks its state as finished. The manager then input winner's username in the following match as found in the match list.

#### View Matchlist
* Requirements: When tournament is ongoing, the tournament manager mode will show a match list.
* Pre-conditions： The tournament is ongoing. 
* Post-conditions: A list of all matches is displayed. The manager can start/end a match ready to be played by selecting it from the list. 
* Scenarios: When there is an ongoing tournament, the manager mode displays a list of matches.

#### Display Prize
* Requirements: When there is no ongoing tournament, the tournament manager can view totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
* Pre-conditions: There is no ongoing tournament. All tournament states are marked as completed.
* Post-conditions: Display totals for every player in the system, sorted by total. The manager can also view a list of the player’s individual prizes by selecting the player from the list.
* Scenarios: When there is no ongoing tournament, the tournament manager has an option to view totals for every player in the system as a list sorted by total. If clicking on a specific player, the system will display the player's individual prizes.

#### Display Profits
* Requirements: When there is no ongoing tournament, the tournament manager can view the list of past house profits in chronological order and the total.
* Pre-conditions: There is no ongoing tournament. All tournament states are marked as completed.
* Post-conditions: Display the list of past house profits in chronological order and the total.
* Scenarios: 

### Actor: Player
#### Login
* Requirements: The application allows player to select player mode. 
* Pre-conditions: Application is started.
* Post-conditions: Manager mode is switched on. 
* Scenarios: Start the application. The manager select his/her user type as Manager and input the login credentials. The manager mode will be switched on and display a list of functions. 

#### View Matchlist
* Requirements: When tournament is ongoing, the player mode will show a match list.
* Pre-conditions： The tournament is ongoing. 
* Post-conditions: A list of all matches is displayed.
* Scenarios: When there is an ongoing tournament, the player mode displays a list of matches.

#### Display Prize
* Requirements: When there is no ongoing tournament, the player can view totals for every player in the system as a list sorted by total. 
* Pre-conditions: There is no ongoing tournament. All tournament states are marked as completed.
* Post-conditions: Display totals for every player in the system, sorted by total. 
* Scenarios: When there is no ongoing tournament, the player mode will display totals for every player in the system as a list sorted by total.

#### Change Deck
* Requirements: When there is no ongoing tournament, the player can change their deck in the system.
* Pre-conditions: There is no ongoing tournament. Player is in the system.
* Post-conditions: Display deck the player will use in the tournament.
* Scenarios: When there is no ongoing tournament, the player mode will allow the player to change decks.