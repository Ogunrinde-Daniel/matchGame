
public class Main {
	
	public static void main(String[] args) {
		// ####################################################################################################
		// Method				:	void main(String[] args)
		//
		// Method parameters	:	args - the method permits String command line parameters to be entered
		//
		// Method return		:	void
		//
		// Synopsis				:   This method starts up the Game mechanics class by running it's startGame method
		//							It then displays the interface for the user by class it's main class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		
		GameMechanics.startGame();				//game mechanics class function | initializes the data members in the gameMechanics class
		GUI.main(args);							//graphical user interface display | windowsBuilder

	}

}
