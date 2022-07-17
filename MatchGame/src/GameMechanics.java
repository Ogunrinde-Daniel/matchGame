import java.util.ArrayList;
import java.util.Random;

public class GameMechanics {
	
	/*Data Members Declaration*/
	
	private static Character player;							//create a player object;
	
	private static int playerPoints;							//stores the playerPoints retrieved from the player object
	private static int playerLevel;								//stores the playerLevel retrieved from the player object
	private static int roundCount;								//stores the roundCount for each level retrieved from the player object
	
	private static int colourInputCount;						//stores the number of times a user has presses a button for each given round
	private static ArrayList<EnumColour> coloursDisplayed; 		//stores the colors generated in each round
	private static EnumColour[] coloursChosen; 					//stores the colors chosen by the user
	
	private static boolean DEBUG_MODE = false;					//checks if we are still in development phase of the game



	
	public static void startGame() {
		// ####################################################################################################
		// Method				:	void startGame()
		//
		// Method parameters	:	null
		//
		// Method return		:	void
		//
		// Synopsis				:   This method initializes the data members in this class 
		//							This is so that every start of the game, the player statistics are reset
		//							It instantiates a player, which is a child to the character base class
		//							this method is TRIGGERED at the start of a new game by the main method 
		//							in the Main class
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		player = new Player(0, 1, 1);								//instantiates a new player object of child class player
		
		playerPoints = player.getPoints();							//sets the playerPoints to player's object point using accessor methods
		playerLevel  = player.getLevel();							//sets the playerLevel to player's object Level using accessor methods
		roundCount   = player.getRounds();							//sets the roundCount to player's object roundCount using accessor methods
		
	}
	


	public static void generateRandomColours() {
		// ####################################################################################################
		// Method				:	void generateRandomColours()
		//
		// Method parameters	:	null
		//
		// Method return		:	void
		//
		// Synopsis				:   This method generates random colors using the following procedures 
		//							Firstly, it generates a random number between 0 & 4 (which are indices to the enumColor constructor)
		//							It then passes in the randomNumber to the create a new enumColor
		//							finally, it calls in a method to display the colors generated
		//							this method is TRIGGERED by the generate button in the GUI class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		/*NOTE::every thing in this class runs again every new round, so the array instantiation creates a new storage each time automatically clearing old data*/
		Random random = new Random();												//instantiates a random object 
		
		int noOfColoursDisplayed = playerLevel + 2;									//determines the no of color to be displayed
		coloursDisplayed = new ArrayList<EnumColour>();								//instantiates a new storage for colors to be generated
		coloursChosen = new EnumColour[noOfColoursDisplayed];						//instantiates a new storage for colors the user will choose | NOTE that it is an array
																					//with fixed size of the numberOfCOlors displayed, this way, the user would never choose
																					//more than the colors displayed
		
		for(int counter = 0; counter < noOfColoursDisplayed; counter++) {			//fills in the colourDisplayed array with colors and with the size of the noOfColoursDisplayed
			EnumColour enumColour = new EnumColour(random.nextInt(4));				//generates a new color (random)
			coloursDisplayed.add(enumColour);										//adds to the array the color generated above

		}
	    displayColours(coloursDisplayed);											//this calls a function that displays the randomly generated colors | NOTE that this happens 
	    																			//once in the function after the loop

	}
	
	public static void displayColours(ArrayList<EnumColour> colourDisplayArrayList){	
		// ####################################################################################################
		// Method				:	void displayColours(ArrayList<EnumColour> enumColour)
		//
		// Method parameters	:	ArrayList<EnumColour> enumColour
		//
		// Method return		:	void
		//
		// Synopsis				:   This method triggers a function in the GUI class that flashes the colors in order 
		//							The function trigers the setFlashIconArray method and passes in the enumColor array
		//							passed in by the generated colors
		//							this method is TRIGGERED by the generateRandomColors in the GameMechanics class
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		if(DEBUG_MODE) {
			System.out.println("");			
			for(EnumColour e: coloursDisplayed )							
				System.out.print(e.colour + " ");				//prints out the array of colors for faster debugging
		}
		GUI.setFlashIconArray(colourDisplayArrayList);			//calls a function in the GUI that flashes the array of colors passed in
			
	}
	
		
	public static void inputColours(EnumColour enumColour){
		// ####################################################################################################
		// Method				:	void inputColours(EnumColour enumColour)
		//
		// Method parameters	:	EnumColour enumColour
		//
		// Method return		:	void
		//
		// Synopsis				:   This method fills in the coloursChoosen Array whenever any of the color buttons are pressed 
		//							It fills in the array while the size of the array is less than the size of the color displayed array
		//							when the color chosen is equal to the size of the colursDisplayed, It triggers a function to compare the 2 arrays
		//							this method is TRIGGERED by the 4 color buttons in the GUI class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		if (colourInputCount < coloursDisplayed.size() ){				//checks if the user hasn't picked the max no Of Colors 
			coloursChosen[colourInputCount] = enumColour;				//fills in the coloursChosen next index
			colourInputCount++;											//increments the counter			
		}
			
		if(colourInputCount == coloursDisplayed.size() ){				//if the color clicked is the last color			
			colourInputCount = 0;										//the counter is turned into 0
			trackColours();												//it then calls a function that validates the colors chosen by the user
																		//NOTE this if statement runs just once in every round
		}
		
	}
	
	public static void trackColours() {
		// ####################################################################################################
		// Method				:	void TrackColours()
		//
		// Method parameters	:	null
		//
		// Method return		:	void
		//
		// Synopsis				:   This method compares the coloursDisplayed and the Colors Chosen
		//							the boolean validationState is false when the 2 arrays are different and true when the 2 arrays are the same
		//							It triggers 2 functions and passes in the validation state
		//							this method is TRIGGERED by the input color method in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		boolean validationState = true;														//sets the validation to true | we assume the user chose the correct order of colors
		for(int counter = 0; counter < coloursDisplayed.size(); counter++ ){				//loops through the colorDisplayed array
			if(coloursDisplayed.get(counter).colour != coloursChosen[counter].colour )		//compares each index for the both arrays
				validationState = false;													//if there is any index without the same values, then the user has chosen a 
																							//wrong order
		}
		
		if(validationState)																	//if the validation is still true after the loop runs
			signifySuccess(validationState);												//signifies success
		else																				//if the validation is set to false during the loop 
			signifyLoss(validationState);													//signifies loss
		
	}
	
	
	public static void signifySuccess(boolean validation) {
		// ####################################################################################################
		// Method				:	void signifySuccess(boolean validation)
		//
		// Method parameters	:	boolean validation
		//
		// Method return		:	void
		//
		// Synopsis				:   This method signifies success by playing a triumph sound and displaying a message signified by a tick mark
		//							it adds to the player points and increments the level and rounds according
		//							the if condition at the start of the statement is to avoid unforeseen future bugs
		//							this method is TRIGGERED by the trackColors method in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		if (validation){													//extra check for unforeseen bugs
			GUI.setMessage(true);											//displays a message in form of image
			GUI.setTextMessage(true);										//displays a message
			GUI.playSound("audio//correctAnswer.wav");						//plays a victory sound

			playerPoints += determinePointsAdded(playerLevel);				//adds to the playerPoints
			roundCount++;													//increments the roundCount

			/*
			if(roundCount > 4){												//if the round > 4, then we need to move to a new level
				roundCount = 1;												//round count is set back to 1
				playerLevel++;												//the player moves to a new level
				GUI.setGameScreen("newLevel");								//a new level message is set
				GUI.playSound("audio//newLevel.wav");						//a new level sound is played
			}
			*/
			updatePlayerStatistics();										//the player statics is then updated to the screen
		}
		
	}
	
	public static void signifyLoss(boolean validation) {
		// ####################################################################################################
		// Method				:	void signifyLoss(boolean validation)
		//
		// Method parameters	:	boolean validation
		//
		// Method return		:	void
		//
		// Synopsis				:   This method signifies loss by playing a buzzer sound and displaying a message signified by a X mark
		//							it  then subtracts from the player points.
		//							the if condition at the start of the statement is to avoid unforeseen future bugs
		//							this method is TRIGGERED by the trackColors method in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		if(!validation) {													//extra check for unforeseen bugs
			GUI.setMessage(false);		 									//displays a message in form of image	
			GUI.setTextMessage(false);										//displays a message
			GUI.playSound("audio//wrongAnswer.wav"); 					    //plays a buzzer sound
			playerPoints -= determinePointsSubtracted(playerLevel);			//Deducts the playerPoints
			updatePlayerStatistics();										//the player statics is then updated to the screen
		}
		
	}
	
	public static void updatePlayerStatistics(){
		
		// ####################################################################################################
		// Method				:	void updatePlayerStatistics()
		//
		// Method parameters	:	null
		//
		// Method return		:	void
		//
		// Synopsis				:   This method updates the player statics to the GUI and also tests if the Game should be Over
		//							this method is TRIGGERED by the signifyLoss and signifySuccess method in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		if(DEBUG_MODE) {											//prints out the colors chosen and displayed for faster debugging
			System.out.print("\nThe balls displayed were ");		//caption		
			for(EnumColour e: coloursDisplayed )					//loops through the colourDisplayed array
				System.out.print(e.colour + " ");					//displays the colors in the colourDisplayed array
			
			System.out.print("\nYou chose ");						//caption
			for(EnumColour e: coloursChosen )						//loops through the coloursChosen array
				System.out.print(e.colour + " ");					//displays the colors in the coloursChosen array
		}
		/*Updates the interface with the updated values*/
		GUI.setLblPoints(playerPoints);
		GUI.setLblLevel(playerLevel);
		GUI.setLblRound(roundCount);
		
		/*Updates the player object dataMembers with the updated values*/
		player.setRound(roundCount);
		player.setPoints(playerPoints);
		player.setLevel(playerLevel);

		/*GAME OVER*/
		if(playerPoints < 0) {										//if the playerPoints < 0
			GUI.playSound("audio//gameOver.wav");					//plays a gameOver audio | reference in the GUI class
			GUI.setGameScreen("gameOver");							//displays a gameOver screen
			GUI.setGenerateButtonState(false);						//disables the generate button
			GUI.setColoursButtonState(false);						//disables the color Buttons
			
		}
		/*GAME CONTINUATION*/
		else{
			/*Updates the interface with the updated values*/
			GUI.setLblPenalty(determinePointsSubtracted(playerLevel));	
			GUI.setLblReward(determinePointsAdded(playerLevel));
			GUI.setGenerateButtonState(true);						//enables the generate Button
			GUI.setColoursButtonState(false);						//disables the color Buttons
		}

		
	}
	
	public static int determinePointsAdded(int level){
		
		// ####################################################################################################
		// Method				:	int determinePointsAdded(int level)
		//
		// Method parameters	:	int level
		//
		// Method return		:	void
		//
		// Synopsis				:   This method determines the points to be added on a particular level
		//							it then loops on the level, if the level is 2, 5 is multiplied twice making the points 20
		//							If we had done it directly, by using the points * 2, the result would have been that it would multiply by 2 in each round
		//							making the algorithm inefficient.
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		/*             Algorithm                */
		/* newPoints = previousLevelPoints * 2	*/
		/* {P^n = P^(n-1) * 2 | P^0 = 5 }		*/
		/*	where n = level	&& p = points		*/
		/*				End						*/
		
		if(level < 1) return -1;								//if level is negative, the game has a bug, so we do not run the function
		int points = 5;											//using the algorithm provided, P^0 = 5;
		
		for(int counter = 0; counter < level; counter++){		//this loop runs the size of "n" 
			points *= 2;										//increments the points
		}
		return points;											//return points
	}
	
	public static int determinePointsSubtracted(int level){
		
		// ####################################################################################################
		// Method				:	int determinePointsAdded(int level)
		//
		// Method parameters	:	int level
		//
		// Method return		:	void
		//
		// Synopsis				:   This method determines the points to be subtracted on a particular level
		//							it then loops on the level, if the level is 2, 5 is multiplied twice making the points 20
		//							If we had done it directly, by using the points * 2, the result would have been that it would multiply by 2 in each round
		//							making the algorithm inefficient.
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		/*             Algorithm                */
		/* newPoints = previousLevelPoints * 2	*/
		/* {P^n = P^(n-1) * 2 | P^0 = 10 }		*/
		/*	where n = level	&& p = points		*/
		/*				End						*/
		
		if(level < 1) return -1; 								//if level is negative, the game has a bug, so we do not run the function
		int points = 10;										//using the algorithm provided, P^0 = 10;
		
		for(int counter = 0; counter < level; counter++){		//this loop runs the size of "n" 
			points *= 2;										//increments the points
		}
		return points;											//increments the points
	}
	

}
