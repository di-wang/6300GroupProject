1. __The tournament is organized as a single elimination tournament with third place playoff.__

   This requirement states in what format tournaments should be run, but doesn't really have much impact on the design, so this wasn't considered.
2. __The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.__

   To realize this requirement, class 'Mode', 'Manager', 'Player', and 'CurrentMode' are added to the design. 'Manager' and 'Player' inherits from 'Mode' class. 'CurrentMode' class has an association relationship with 'Mode' class. When the application starts, either 'Player' or 'Manager' is associated with 'CurrentMode'. An operation is added to 'CurrentMode' and this operation checks whether the current mode is manager mode or not by determining whether 'Mode' association with 'CurrentMode' is 'Player' or 'Manager'.
3. __The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.__

   This requirement gives overview of what kind of functions can be performed as a manager. More detailed requirements are given in below (requirements 7, 9), so this requirement is realized by more specific and detailed requirements below.
4. __The tournament players use the system to (1) view the match list and (2) view the total player prizes.__

   This requirement gives overview of what kind of functions can be performed as a manager. More detailed requirements are given in below (requirements 10), so this requirement is realized by more specific and detailed requirements below.
5. __The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).__

   This requirement implies that instance of classes and the data they contained can be persisted. This allows certain information can be fetched by query. This requirement itself doesn't really have much impact on the design, so this wasn't considered.
6. __A player in the system is characterized by the following information:__
 - __Name__
 - __Unique alphanumeric username__
 - __Numeric phone number__
 - __A deck choice, from a list of deck options__

   To realize this requirement, class attributes 'name', 'username', and 'phoneNumber' are added to 'Player' class.

   Instead of having an attribute with a predefined data structure like enum for the deck choice, a class 'DeckOptions' is created. This provides more flexibility because when there is a need to change the list of decks, admin can easily do so by adding/deleting 'DeckOptions' object, perhaps using admin interface instead of changing the code. 'Player' and 'DeckOptions' have one to many association (assuming multiple players can choose the same deck).
7. __The tournament manager can add a player to and remove a player from the system.__

   To realize this requirement, operations 'addPlayer' and 'removePlayer' are added to 'Manager' class. For 'addPlayer', it is assumed that a valid instance of 'Player' requires at least name and username, so only these two are shown for the parameters of 'addPlayer'.
8. __There can only be one ongoing tournament at any given time.__

   To realize this requirement, an attribute 'status' is added to 'Tournament' class. When a tournament's status was to be changed to ongoing, this 'status' attribute is checked for all the tournaments saved in the database so that there would be only one tournament with ongoing status.
9. __To start a tournament:__
 - __The tournament manager will enter the house cut.__
 - __The tournament manager will enter the entry price.__
 - __The tournament manager will enter all player usernames. For simplicity, let’s assume that there will be either 8 or 16 players in a tournament.__
 - __When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.__

   To realize this requirement, attributes 'houseCut' and 'entryPrice' are added to Tournament class. Tournaments and 'Player' classes have association relationship to represent 8 or 16 players participating in a tournament.

   An operation 'enterTournamentInfo' is added to 'Manager' and it can set 'houseCut', 'entryPrice', and 'usernames' of the Players participating.

   An operation calculatePrizesAndProfit is added to Tournament class and this will calculate potential prizes and house profit. This operation will set derived attributes /firstPrize, /secondPrize, /thirdPrize, and /houseProfit in class Tournament. This operation should run after enterTournamentInfo.

   An id attribute is added to 'Tournament' to identify a tournament.

   Once 'enterTournamentInfo' and 'calculatePrizesAndProfit' are run, all the information should be shown to the manager to double check. An operation 'showTournamentInfo' is added to perform this task. An operation 'startTournament' is added to Manager class. This operation will start the tournament with the given 'id'. This will set the 'status' of 'Tournament' to ongoing.
10. __When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.__

   To realize this requirement, an operation 'showAllPlayersTotalPrizes' is added to 'Player' class. This operation will check first if there is any 'Tournament' with ongoing status and if there isn't, will show a list of the total prizes for all players. A computed attribute '/totalPrize' is added to 'Player' class.
11. __When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.__

   To realize this requirement, an operation showMatchList is added to Player class. This operation will check first if there is any Tournament associated with OngoingTournament and if there is, will show a match list of the current ongoing Tournament.

   To represent matches for a 'Tournament', a class 'Match' is created. Class 'Match' has two association relationships with 'Player' class where there is always 2 'Players' for a single 'Match'.
   Also, an attribute 'status' is added to represent status of the match (ongoing, ready, completed). 'Match' and 'Tournament' has aggregation relationship.
12. __When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:__
 - __Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.__
 - __End an ongoing match and specify a result for it.__
 - __End the tournament. If the tournament is ended early, money is refunded.__

    To realize this requirement, an operation 'showMatchList' is added to Player class. This operation will check first if there is any 'Tournament' associated with ongoing and if there is, will show a match list of the current ongoing 'Tournament'.

    Operations 'startMatch', 'endMatch', and 'endTournament' are added to Manager class.

    'endMatch' has parameter indicating which 'Player' has won.

    If 'endTournament' is called when there is still 'Match' that hasn't been completed, then tournament is ended early and money is refunded
13. __After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database.__

    To realize this requirement, association relationships 'winningPlayer', 'secondPlacePlayer', and 'thirdPlacePlayer' are added to class 'Tournament'. Operation 'endTournament' should handle assigning these to a 'Player' if tournament didn't end early.
14. __After a tournament is completed, the house profit will also be recorded in the underlying database.__

    To realize this requirement, an attribute 'realHouseProfit' is added to class Tournament.
15. __When there is no ongoing tournament, the tournament manager can:__
 - __View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.__
 - __View the list of past house profits in chronological order and the total.__

    To realize this requirement, operations 'viewAllPlayersTotalPrizes' and 'viewPrizesOfPlayer' are added to 'Manager'.

    An operation 'viewPastProfits' is added to 'Manager' and also an attribute 'endDate' is added to 'Tournament' to keep track of chronological order of 'Tournament'. Also, computed attribute '/totalProfit' is added to 'Tournament'.
16. __The User Interface (UI) must be intuitive and responsive.__

    Not considered since it does not affect the design directly.
