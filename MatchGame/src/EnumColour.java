
public class EnumColour {

	Colour colour;										//color data member
	private static boolean DEBUG_MODE = false;			//boolean for debugging purposes
	
	public enum Colour{	Red, Green, Yellow, Blue }		//an enumerated dataType with 4 elements 
	
	
	public EnumColour(int colourIndex)
	{
		// ####################################################################################################
		// Method				:	EnumColour(int colourIndex) | Constructor
		//
		// Method parameters	:	int colourIndex
		//
		// Method return		:	null
		//
		// Synopsis				:   This constructor method takes in an integer and sets the color element for the object
		//							depending on the index passed in. The Color options are from the Color enum datatype.
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		switch(colourIndex)								//switches on the number passed in
		{
		case 0:
			this.colour = EnumColour.Colour.Red;		//sets the data member for for particular object to red 
			break;
		case 1:
			this.colour = EnumColour.Colour.Green;		//sets the data member for for particular object to green 
			break;
		case 2:
			this.colour = EnumColour.Colour.Yellow;		//sets the data member for for particular object to yellow 
			break;
		case 3:
			this.colour = EnumColour.Colour.Blue;		//sets the data member for for particular object to blue 
			break;
		default:
			if(DEBUG_MODE) System.out.println("Error from EnumColour constructor"); //tells us where the error is coming from
			break;
		}
	}
}
