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






## Player







## Open the application on either and emulator or a device
### From an emulator
1. Open Android Studio.
2. Load the existing code by importing the TourneyCalc folder.
3. *In Android Studio* Build the code.
4. *In Android Studio* Run the code.
	* If the display to chose device displays select the default values *ARM* and *Nexus 5*.
5. After a time *usually 5 to 10 minutes maximum* the application should automatically load onto the emulated Nexus 5.

### From a device
1. This is a place holder for future reference.

## Operating the TourneyCalc application
The program should be running on the emulator or device at this point in time. If not revisit **Open the application on either and emulator or a device**.

### Display information
The application is set up to take in **three** pieces of information as listed below. It will also display **four** points of information that it calculates.

#### Input fields
Field | Input Information
----- | -----------------
Entrance Fee | This is the fee that is charged to enter the Tourney and is required to be an integer greater than 0.
Entrants |  This is the number of entrants in the Tourney and is required to be and integer greater than 3.
House Percentage | This is the percent of the prize pool the house will automatically get and is required to be an integer between 0 and 100.

#### Button
There is a single button to use called **Show Me The Prizes** and this will complete the calculation only when all inputs are valid.

#### Display Fields
Field | Display Information
----- | -------------------
House Cut | This is the actual house prize pool for running the Tourney.
1st | This is the amount of the first prize at 50% of the prize pool after the House Cut.
2nd | This is the amount of the second prize at 30% of the prize pool after the House Cut.
3rd | This is the amount of the third prize at 20% of the prize pool after the House Cut.

### Running TourneyCalc
1. To run TourneyCalc and get results enter the following information into the Input Fields. Where **X**, **Y**, and **Z** are Integer values.

	Field | Input
	----- | -----
	Entrance Fee | X > 0
	Entrants |  Y > 3
	House Percentage | 0 < Z < 100

2. Press the **Show Me The Prizes** button.
	* The program will clear out *House Cut*, *1st*, *2nd*, and *3rd* on invalid inputs into the system.
