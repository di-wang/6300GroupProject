# TourneyCalc Design Information

This will describe the design rationale for the Assignment5.pdf UML Class Diagram which is located in the same directory as this MD file.

1. The tournament is organized as a single elimination tournament with third place playoff.

  * For a class diagram, this requirement does not come into play

2.	The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.

  * For a class diagram, this does not come into play.  This would be addressed in an UML activity diagram

3.	The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.

  * To realize this requirement I put the following operations in the respective classes:
    - (1) addPlayer operation under the Player class
    - (2) createTourney, startTourney and endTourney operations under the Tournaments class
    - (3) displayTourneyInfo operation under the Tournaments class.

4.	The tournament players use the system to (1) view the match list and (2) view the total player prizes.

  * To realize this requirement I put the following operations in the respective classes:
    - (1) displayMatchList operation under the Tournament class
    - (2) displayTourneyInfo operation under the Tournaments class.

5.	The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).

  * To realize this requirement I have the Players, Tournaments and Matches classes defined in this class diagram.  These will possibly become Players, Tournaments and Matches tables in the underlying database.

6.	A player in the system is characterized by the following information:
  a.	Name
  b.	Unique alphanumeric username
  c.	Numeric phone number
  d.	A deck choice, from a list of deck options

  * To realize this requirement I added name, userName, phoneNumber and deckChoice attributes to the Players class.

7.	The tournament manager can add a player to and remove a player from the system.

  * To realize this requirement I added the addPlayer as well as the deletePlayer operations to the Players class.

8.	There can only be one ongoing tournament at any given time.

  * This requirement cannot be addressed in a class diagram.  This would be more geared towards the activity diagram.  However, in order to help realize this requirement, I added the status attribute to the Tournaments class to track the status of each individual tournament.

9.	To start a tournament:

  a.	The tournament manager will enter the house cut.  
  b.	The tournament manager will enter the entry price.  
  c.	The tournament manager will enter all player usernames. For simplicity, let’s assume that there will be either 8 or 16 players in a tournament.  
  d.	When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.
  
  * a., b., and c. would really be addressed in an UML interaction diagram.  As for d. this would be used by the displayTourneyInfo operation defined for the Tournaments class.

10.	When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.

  * While the determination of a ongoing tournament and if the application was running in player mode would be handled by an UML interaction diagram, the gathering of the player data would be executed by the displayPlayerTotals operation under the Players class.

11.	When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.

  * While the determination of a ongoing tournament and if the application was running in player mode would be handled by an UML interaction diagram, the gathering of the match data would be executed by the displayMatchList operation under the Tournaments class.

12.	When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
  a.	Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
  b.	End an ongoing match and specify a result for it.
  c.	End the tournament. If the tournament is ended early, money is refunded.

  * While the determination of a ongoing tournament and if the application was running in manager mode would be handled by an UML interaction diagram, a. would be realized by the startTourney operation in the Tournaments class.  b. would be realized by the endMatch operation located in the Matches class. c. would be realized by the endTourney operation located in the Tournaments class and the determination of a early terminated tourney would be specified in an UML activity diagram.

13.	After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database . 

  * To realize this requirement I added the firstPrizeAmt, secondPrizeAmt and thirdPrizeAmt attributes to the Tournaments class.  However to determine when a tournament has completed would be addressed by an UML activity disgram.

14.	After a tournament is completed, the house profit will also be recorded in the underlying database.

 * To realize this requirement I added the houseCut attribute to the Tournaments class.  However to determine when a tournament has completed would be addressed by an UML activity disgram.

15.	When there is no ongoing tournament, the tournament manager can:
  a.	View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
b.	View the list of past house profits in chronological order and the total.

  * In order to realize this requirement I will address each sub point individually.   a. I added the prizeMoney attribute to the Players class for first list, then I also added firstPrizeWinner, secondPrizeWinner and thirdPrizeWinner attributes to the Tournaments class to track which place a player may have finished in in order to properly display the correct information when populating the list of a player's winnings.  Now the generation of the report would be addressed by an UML interaction diagram.  b. I added a tourneyDateTime attribute to the Tournaments class in order to help fulfill the requirement of the report being in chronological order. 

16.	The User Interface (UI) must be intuitive and responsive.

  * Not considered because it does not affect the design directly.