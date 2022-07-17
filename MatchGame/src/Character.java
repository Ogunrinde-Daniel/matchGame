
public class Character {
	
	private int points;				//stores the object current points
	private int level;				//stores the object current level
	private int round;				//stores the object current round

	
	public Character(int tempPoints, int tempLevel) {
		// ####################################################################################################
		// Method				:	Character(int tempPoints, int tempLevel) | Constructor
		//
		// Method parameters	:	int tempPoints, int tempLevel
		//
		// Method return		:	null
		//
		// Synopsis				:   This constructor method takes in 2 integers and sets the points and level to the data members
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		points = tempPoints;		//sets the points data member for the particular object to points passed in
		level = tempLevel;			//sets the level data member for the particular object to level passed in
	}

	
	/*Accessor methods | this are getters and setters for the data members in this class*/
	
	/*returns the value of points*/
	public int getPoints() {
		return points;
	}

	/*sets the value of points*/
	public void setPoints(int points) {
		this.points = points;
	}
	
	/*returns the value of level*/
	public int getLevel() {
		return level;
	}

	/*sets the value of level*/
	public void setLevel(int level) {
		this.level = level;
	}

	/*returns the value of round*/
	public int getRounds() {
		return round;
	}
	/*sets the value of round*/
	public void setRound(int rounds) {
		this.round = rounds;
	}
	
	
}
