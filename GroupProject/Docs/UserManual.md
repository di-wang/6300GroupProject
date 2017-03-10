# User Manual for Tourney Manager Application

**Author:** Adric Cain (acain33)

**Team:** Di Wang (dwang383), Ed Redmond (eredmond6), Jongho Jung (jjung327)

## Prerequisite
Application is installed on a device.
* The code for Team 75's TourneyManager application is located at [TourneyManager] (https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/tree/master/GroupProject/TourneyManager)

## About
This User Manual will be split into three (3) main sections: [Initial Screen](#initial-screen), [Manager](#manager) and [Player](#player).
* The Initial Screen will allow the user to select either Manager or Player.
* The Manager will guide the user to all the Manager features.
* The Player will guide the user to all the Player features.

## Initial Screen
When the Tourney Manager application is launched, the user will see a screen with the name "Tourney Manager" and contain two (2) log in buttons. One (1) button is for Manager features of the application and one (1) button is for Player features of the application.


## Manager
The following describes the actions the Manager may take with the Tournament Manager application.

### Initial Manager screen
The starting screen once a Manager selects **Manager** is the Organizations page. This page list the organizations information and has buttons to interact with other application pages.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/ManagerHome.png" alt="Initial Manager Screen" width="200" height="400">

### Manager interactions when no tournament is operating
The manager can view the following information and have the following interactions with the application when no tournament is running.

#### View house information
* The manager can view the total house winnings.
* The manager can view the historical house cut winnings by tournament.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/HouseInformation.png" alt="House Information" width="200" height="400">

#### View player information
* The manager can view all player information in the system.
* The manager can view individual player information and historical winnings.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/PlayerList.png" alt="Player List" width="200" height="400">

#### Add/Remove players from the system
* The manager can add additional players into the system.
* The manager can remove players from the system by the remove button in each player profile they wish to remove.


<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/AddPlayer.png" alt="Manager Player Add" width="200" height="400"><img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/PlayerProfileManagerView.png" alt="Manager Player View" width="200" height="400">

##### Input fields for adding a player
Field | Input Information
----- | -----------------
User Name | This is the unique user name that will be used to store player information and add them to tournaments.
Name | This is the name of the legal name of the player to be added to the system.
Phone Number | This is the users phone number to contact for any information regarding tournaments.

#### Start tournament
This section will describe the information input to start a tournament.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/StartTournament.png" alt="Manager Start Tournament" width="200" height="400">

##### Input fields for starting a tournament
Field | Input Information
----- | -----------------
House Percentage | This is the percent of the prize pool the house will automatically get and is required to be an integer between 0 and 100.
Entry Price | This is the fee that is charged to enter the Tourney and is required to be an integer greater than 0.
Players | This is an array of Players *Usernames* that is either 8 or 16 in total selected by a drop down.

###### Help on input information
To start a Tournament, enter the following information into the Input Fields. Where **X** and **Y** are Integer values and **Name** is a String Array.

	Field | Input
	----- | -----
	House Percentage | 0 < X < 100	
	Entry Fee | Y > 0
	Players | Name[8] or Name[16] 

##### Start Tournament button
There is a single button called **Start Tournament** That will allow the Tournament to start when all valid information is input as described above.

### Manager interactions when a tournament is operating
The manager can view the following information and have the following interactions with the application when a tournament is ongoing.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/MatchList.png" alt="Match List" width="200" height="400">

#### Interact with tournament information
* The manager can view the tournament prize pool values.
* The manager can end the current tournament due to error on set up.
	* This will not save the prizes and house cut information.
* The manager can end the current tournament after all matches are completed.
	* This will distribute the value of the *House Cut*, *1st*, *2nd*, and *3rd* prizes to the correct user.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/ManagerWinner.png" alt="Manager Tournament Interactions" width="200" height="400">

#### Interact with match information
* The manager can start the match between players
* The manager can select the winner and end the match between players
* The manager can view all ongoing matches for the tournament and historical match results in the current tournament.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/ManagerMatch.png" alt="Match List" width="200" height="400">

## Player
The following describes the actions the Player may take with the Tournament Manager application.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/PlayerHome.png" alt="Player Home" width="200" height="400">

### Initial Player screen
The starting screen once a Player selects **Player** is their personal page. This page list the player information that was input by the Manager and their tournament winnings.

### Player interactions when no tournament is operating
* The player can view their information on the main Player screen.
* The player can view their historical tournament winnings; By tournament or by total.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/PlayerList.png" alt="Player List" width="200" height="400">

### Player interactions when a tournament is operating
* The player can click the tournament button to move to the tournament information screen.

<img src="https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/Images/PlayerMatch.png" alt="Match List" width="200" height="400">

#### Tournament page
* The player can view the tournament prize pool values.
* The player can click the match button to move to the tournament match information screen.

#### Match page
* The player can view all ongoing matches for the tournament and historical match results in the current tournament.
