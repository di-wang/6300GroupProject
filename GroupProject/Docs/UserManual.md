# User Manual for Tourney Manager Application

**Author:** Adric Cain (acain33)

**Team:** Di Wang (dwang383), Ed Redmond (eredmond6), Jongho Jung (jjung327)

## PreRequisite
Application is installed on a device.
* The code for Team 75's TourneyManager application is located at [TourneyManager] (https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/tree/master/GroupProject/TourneyManager)

## About
This User Manual will be split into three (3) main sections: [Initial Screen](#initial-screen), [Manager](#manager) and [Player](#player).
* The Initial Screen will allow the user to select either Manager or Player.
* The Manager will guide the user to all the Manager features.
* The Player will guide the user to all the Player features.

## Initial Screen
When the Tourney Manager application is launced the user will see a screen with the name "Tourney Manager" and contain two (2) log in buttons. One (1) button is for Manager features of the application and one (1) button is for Player features of the application.


## Manager
The following describes the actions the Manager may take with the Tournament Manager application.

### Initial Manager screen
The starting screen once a Manager selects **Manager** is the Organizations page. This page list the organizations information and has buttons to interact with other application pages.

### Manager interactions when no tournament is operating
The manager can view the following information and have the following interactions with the application when no tournament is running.

#### View house information
* The manager can view the total house winnings.
* The manager can view the historical house cut winnings by tournament.

#### View player information
* The manager can view all players in the system.
* The manager can view individual players individual information and historical winnings.

#### Add/Remove players from the system
* The manager can add additional players into the system .
* The manager can remove players from the system.

##### Input fields for adding a player
Field | Input Information
----- | -----------------
Name | This is the name of the legal name of the player to be added to the system.
User Name |  This is the unique user name that will be used to store player information and add them to tournaments.
Phone Number | This is the users phone number to contact for any information regarding tournaments.

#### Start tournament
This section will describe the information input to start a tournament.

##### Input fields for starting a tournament
Field | Input Information
----- | -----------------
Entrance Fee | This is the fee that is charged to enter the Tourney and is required to be an integer greater than 0.
Entrants |  This is the number of entrants in the Tourney and is required to be and integer greater than 3.
House Percentage | This is the percent of the prize pool the house will automatically get and is required to be an integer between 0 and 100.
UserName | This is an array of Players *Usernames* that is either 8 or 16 in total.

###### Help on input information
To start a Tournament enter the following information into the Input Fields. Where **X**, **Y**, and **Z** are Integer values and **Name** is a String Array.

	Field | Input
	----- | -----
	Entrance Fee | X > 0
	Entrants |  Y > 3
	House Percentage | 0 < Z < 100
	User Names | Name[8] or Name[16] 

##### Start Tournament button
There is a single button called **Start Trournament** That will allow the Tournament to start when all valid information is input as described above.

### Manager interactions when a tournament is operating
The manager can view the following information and have the following interactions with the application when a tournament is ongoing.

#### Interact with tournament information
* The manager can view the tournament prize pool values.
* The manager can end the current tournament due to error on set up.
	* This will not save the prizes and house cut information.
* The manager can end the current tournament after all matches are completed.
	* This will distribute the value of the the *House Cut*, *1st*, *2nd*, and *3rd* prizes to the correct user.

#### Interact with match information
* The manager can start the match between players
* The manager can select the winner and end the match between players
* The manager can view all ongoing matches for the tournament and historical match results in the current tournament.


## Player
The following describes the actions the Player may take with the Tournament Manager application.

### Initial Player screen
The starting screen once a Player selects **Player** is their personal page. This page list the player information that was input by the Manager and their tournament winnings.

### Player interactions when no tournament is operating
* The player is able to select the deck they wish to use for future tournaments.
* The player can view their information on the main Player screen.
* The player can view their historical tournament winnings; By tournament or by total.

### Player interactions when a tournament is operating
* The player can click the tournament button to move to the tournament information screen.

#### Tournament page
* The player can view the tournament prize pool values.
* The player can click the match button to move to the tournament match information screen.

#### Match page
* The player can view all ongoing matches for the tournament and historical match results in the current tournament.
