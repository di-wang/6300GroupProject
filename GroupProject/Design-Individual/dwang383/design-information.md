Assignment5: Software Design CS 6300 2017 Spring (Lecturer: Prof. Alex)
Author: Di Wang (gt username: dwang383)
Email: diwang383@gatech.edu

#Design Information 
##1. The tournament is organized as a single elimination tournament with third place playoff.
-In the UML design, I created a class Tournament and a class MatchList with attributes MatchList : NodeList, Match : Node. MatchList. 1 tournament is made up of 1 matchlist with lifetime dependency. The MatchList will contains the tournament brackets and Match Node will contains match information. 
##2. The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.
-To realize the requriement, I added to the design a class Manager with attributes Username and a class Player with attributes Name, Username, PhoneNumber, and DeckChoice. For detailed methods, please refer to the UML design.
##3. The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.
-I created class Manager, class Player and class Tournament. (1)I added AddPlayer/RemovePlayer association from class Manager to class Player, (2) added StartTournmaent/EndTourment association from Manager to Tournament. (3) Underlying system is predefined to be able to store the historical prizes for all players and past house profits. Under Manager class, I added methods ViewTotalsOfEveryPlayer(), ViewPastHouseProfits() to call the <Utility> class SystemDatabase. Under Player class, I added methods ViewTotalsOfEveryPlayer() to call <Utility> class SystemDatabase.
##4. The tournament players use the system to (1) view the match list and (2) view the total player prizes.
-(1)I added ViewMatchList association from class Player to class MatchList. Method ViewMatchList() created under class player. (2) Call Utility class SystemDatabase to view total player prizes. Method ViewTotalsOfEveryPlayer() created under class Player.
##5. The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).
-Given this, utiltiy class SystemDatabase is added to retrieve database information relating to prizes and profits.
##6. A player in the system is characterized by the following information:
*a. Name
*b. Unique alphanumeric username
*c. Numeric phone number
*d. A deck choice, from a list of deck options
-The class Player is added with attributes Name, Username, PhoneNumber, and DeckChoice. Methods are created, please refer to the UML diagram.
##7. The tournament manager can add a player to and remove a player from the system.
-Associations AddPlayer and RemovePlayer are established from class Manager to class Player. Methods AddPlayer(), RemovePlayer() are created under class Manager.
##8. There can only be one ongoing tournament at any given time.
-Relationship between class Manager and class Tournament are defined as many to 1. Attribute IsOn : Boolean is created under Tournament class to switch options of Manager and Players. When IsOn = true, display...; When False, display...
##9. To start a tournament:
*a. The tournament manager will enter the house cut.
*b. The tournament manager will enter the entry price.
*c. The tournament manager will enter all player usernames. For simplicity, let’s
assume that there will be either 8 or 16 players in a tournament .
*d. When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.
-(a,b,c) Under class Manager, I created methods StartTournament(), EndTournament(), InputHouseCut(int x), InputEntreeFee(int y), InputAllPlayerNames(String array). (d) I created a StartTournament association from Manager to Tournament. The association StartTournament has attributes HouseCut, EntryPrice, AllPlayerNames, and Confirmed. The class Manager has methods ViewPotentialPrize(), ViewPotentialProfit(), which will call utility class TourneyCalcApp to calculate the PotentialPrize and PotentialProfit. With these, the manager would be able to double check the information and when confirmed, the tournament will be started and initialized with the input. 
##10. When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.
-Class Player has method ViewTotalsOfEveryPlayer() to call utility class SystemDatabase to view totals for every player sorted by total. 
##11. When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.
-Class MatchList with attributes MatchLIst and Match is created to contain all the match information. Match is a Node contains player 1 and player 2, match state, match result, and next Node. Also, see the answer to requirement 12, class Manager is able to StartMatch and EndMatch. So matchlist would be updated as tournament goes on. The system will check if attribute IsOn under class Tournament = true to switch manager options. 
##12. When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
*a. Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
*b. End an ongoing match and specify a result for it.
*c. End the tournament. If the tournament is ended early, money is refunded.
-(a,b) Association StartMatch/EndMatch from class Manager to class MatchList is created to allow manager to update match information. Methods StartMatch(...),EndMatch(...), ViewMatchList() are created under Manager class. 
(c)Associate EndTournament() from class Manager to Tournament is created to allow manager to end tournament. Method EndTournament() included in Manager class. Method RefundMoney() is created under Tournament class. The way to judge whether tournament is ended early is either to the status of Tournament or match states in MatchList. When Tournament attribute IsOn = true, or when not all match states in MatchList are marked finished, the tournament is defined as being ended early and will call RefundMoney() to return funds.
##13. After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database.
-As mentioned above, MatchList class is created and Manager is able to update the match information. When all match states are marked as finished, the tournament is automatically completed. Methods ViewFirstPlace,ViewSecondPlace,ViewThirdPlace are created under MatchList class. The prizes will be distributed to the first, second and third place players and underly SystemDatabase will record it. We can call methods InsertFirstPlace(), InsertSecondPlace(), InsertThirdPlace() in the underlying SystemDatabase to include the results of this tournament and update prizes accordingly.
##14. After a tournament is completed, the house profit will also be recorded in the underlying database.
-InsertThirdPlace() added in the utility SystemDatabase to update the database to include the house profits of this tournament. 
##15. When there is no ongoing tournament, the tournament manager can:
a. View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
b. View the list of past house profits in chronological order and the total.
-Methods ViewTotalsOfEveryPlayer() and ViewPastHouseProfits() are created under class Manager to call methods under utility class SystemDatabase, ViewTotalsOfEveryPlayer() and ViewPastHouseProfits() to retrieve the required information.  
##16. The User Interface (UI) must be intuitive and responsive.
-Not required in this assignment