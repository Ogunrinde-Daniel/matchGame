
public class Player extends Character {

	private int round;				//stores the child object's class 
	
	
	public Player(int tempPoints, int tempLevel,int tempRound) {
		// ####################################################################################################
		// Method				:	Character(int tempPoints, int tempLevel,int tempRound ) | Constructor
		//
		// Method parameters	:	int tempPoints, int tempLevel, int tempRound
		//
		// Method return		:	null
		//
		// Synopsis				:   This constructor method takes in 3 integers and sets the points,rounds and level to the data members
		//							This class is a child of the character class
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		super(tempPoints, tempLevel);					//constructor from the parent class
		round = tempRound;								//sets the child data type
	}
	
	/*Accessor methods | this are getters and setters for the data members in this class*/
	
	/*returns the value of round*/
	public int getRounds() {
		return round;
	}

	/*sets the value of round*/
	public void setRounds(int rounds) {
		this.round = rounds;
	}


	
}
