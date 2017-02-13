# Software Design Information for Woodruff Lounge Tournament Application

## PreRequisite
The UML Design referenced at [Design] (https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17acain33/tree/master/Assignment5/design.pdf).

The assignment information with the requirements is located at [Assignment5] (https://docs.google.com/document/d/1JS08f2XyEuwPyy_dWisfeA1JiggqeN5A-Rbpjy1d0g0/edit).

## Assumptions
This section explains a few of the assumptions that are taken in about the program. 

1. There is no login for the Tournament Manager or Player.
2. There is a predefined list of Decks for the Player to use.
3. The database system contains all features and has a publish subscribe type interface.

## Class Breakdown and definition
This section contains the breakdown and definitions of the class objects in the UML.

1. Main - This is the main screen of the application that would allow the user to select to be a manager or a player.
2. TournamentManager - This class would contain the basic tournament manager features of adding/removing a player, showing the prize pool and start a tournament.
3. TournamentPlayer - This class contains the basic player information of their name, user name, phone number and deck list.
4. Database - Contains all information that is saved from any class that needs to be held. Ex: Player information, historical winnings and historical house cut.
5. Decks - Contain the list of decks to choose from. Separated into its own class for easy modification and extending decks in the future.
6. Tournament - Where the current tournament information is processed and kept until the finish. It will create the tournament number, the bracket, hold the values of prizes, toggle the match information, and pass all necessary values to the data base when finished.
7. StandbyMode - When there is no active tournament this allows the view of all winning information for a player and the house can view its cut as well as player information.
8. Match - Will notify the users the status or results of a match in an active tournament.

## Requirement check
Check where each of the 16 requirements are verified in the UML

1. Tournament bracket system is handled by the Tournament and Match classes.
2. Tournament Manager and Tournament Player are handled by Main.
3. Handled under the TournamentManager class.
4. Handled in the Tournament class and the StandbyMode class.
5. Database class handles all information to store.
6. TournamentPlayer class handles the player information.
7. Tournament Manager handles this. Slight repeat of requirement 3.
8. Handled by a boolean switch in TournamentManager.
9. Handled in Tournament to select and input all information and create a tournament number to keep track in the database.
10. Handled in StandbyMode.
11. Handled in Tournament.
12. Handled in Match and Tournament depending on level needed.
13. Handled in Tournament and passed to database. With user name, tournament id and prize value.
14. Handled in Tournament and passed to database. With tournament id and house cut.
15. Handled in StandbyMode. Similar to requirement 10.
16. N/A for UML. Require separate graphical mock up.

## Future TODO list to improve functionality
This contains a list of things that would help improve functionality.

1. Add a log in feature so a Player can not purposefully or accidentally mess with on going Tournaments.
	* Adds safety if the Tournament Manager is also a Player in a Tournament.
2. Add an option for the Manager or Users to add custom Decks to the DeckList.
3. Algorithm to complete bracket matching automatically.