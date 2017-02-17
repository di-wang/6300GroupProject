# Software Design Information for Woodruff Lounge Tournament Application
This markdown document contains the individual designs and the overall team design that was agreed on by the group.

## Individual UML Designs
This section contains the four individual UML designs listed in alphabetical order by alphabetical order of GT usernames.

### Design 1
Image of acain33's design
![design1](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Design-Team/design1.png)
#### Design 1 Pro
* Design has all the inputs required by the parameters.
* There are additional classes for future growth such as deck.
* There is a clear structure with classes.

#### Design 1 Con
* Operations are in the class boxes and not on the association lines.
* Missing part of interaction with tournament and match.
* Missing a couple features such as refundMoney.

### Design 2
Image of dwang383's design
![design2](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Design-Team/design2.png)
#### Design 2 Pro
* Lots of detail described in the classes.
* Clear to read with app and database as utility.
* Has ability to refund money to players.

#### Design 2 Con
* Lack of selection between player and manager.
* Add and remove player were also in player class.

### Design 3
Image of eredmond6's design
![design3](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Design-Team/design3.png)
#### Design 3 Pro
* Straight forward with how classes interact.

#### Design 3 Con
* No distinction between player and manager.
* Missing a class to call historical tournament data.

### Design 4
Image of jjung327's design
![design4](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Design-Team/design4.png)
#### Design 4 Pro
* Very complete.
* Covered classes for expanding capabilities.
* Holds historical values for all players in a tournament not just winners.

#### Design 4 Con
* Missing database class.
* More association arrows to help visualize the flow of data.
* Missing phone number in addPlayer call.
* Slight confusion on [8,16] username array.

## Team Design
This section contains the team UML design that was discussed by the group members.

![team design](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Design-Team/design-team.png)

### Design commonalities and differences
This contains a high-level comparison and difference look at the individual designs as matched with the team design

1. **Design 1** was similar in thought and contained the basics to meet the requirements as design. A major commonality was the choice to have the deck class and the currentmode class. The main difference is the detail and the use of utility classes in the team design.
2. **Design 2** held the similarity with the use of utility classes and it was the inspiration for the input of them into the team design. The main difference is the lack of the current mode class to have a switch between manager and player.
3. **Design 3** had a similar thought of how information was passed between the classes at the tournament, match and player level. The main difference was the lack of classes to cover extended features that gave the team design a more robust feeling.
4. **Design 4** was the basis for the group UML so the majority of the design is common. As this is the case, a few corrections and additions were done based on the discussion of the team. One of the main additions was the utility classes to help represent the tourney calc algorithm and the database.

### Main design decisions
As a team it was decided that **Design 4** *(jjung327)* had the most complete UML that required minor changes compared to the other design diagrams. The team decided to use this as the base line for our team collaboration UML. The team liked the detail in general and felt that with a couple clarifications on the UML design, a couple corrections of variables and additions of utility classes the UML design would meet what was expected as a collective group.

One major changes made from the base design was instead of showing foreign key relationships between 'Match' and 'Player', and between 'Tournament' and 'Player' as association lines between the classes, attributes 'player1' and 'player2' are added to 'Match' class, and attributes 'winningPlayer', 'secondPlayer', and 'thirdPlayer' are added to 'Tournament' class. Originally, association lines were used to represent that these attributes are not typical String attributes, but foreign keys. However, it was decided that having all the association lines could cause confusion and clutter the diagram.

Additionally, some operations from 'Manager' class acting on 'Tournament' class or 'Match' class required different multipliers. For example, 'viewPastProfits' is aggregation operation while 'showTournamentInfo' acts on a single 'Tournament' instance. Therefore, separate lines were used to differentiate these different types of operations.

## Summary
As a group, we started our meeting by discussing our individual design information about each of our UMLs and the process that went into developing the diagram we submitted for the previous assignment. Then the team went back and talked about what the pros and cons of each of the designs. Additionally, the team helped with points and fields that we missed individually. We discussed that we should continue, for now, with one video call a week and agreed on this following meeting time. Lastly, we agreed to keep in touch over google hangouts with what the next steps as a team should be taken.
