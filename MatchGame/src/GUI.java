import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	/*
	  Reference / Credits
	   
	 AUDIO			   Website Name			| Date			| Link	
	 buttonClick 	-> Youtube Sound Effect | May 3, 2022	| https://www.youtube.com/watch?v=xtg4Vn3MM7w
	 correctAnswer  -> Gaming Sounds Effect | May 3, 2022	| https://www.youtube.com/watch?v=0ZQ-Lk--ILE
	 gameOver		-> GFX Sounds			| May 3, 2022	| https://gfxsounds.com/?s=game+over
	 newLevel		-> orangeFreesounds 	| May 3, 2022	| https://orangefreesounds.com/level-sound-effect/
	 wrongAnswer	-> mixkit 				| May 3, 2022   | https://mixkit.co/free-sound-effects/buzzer/
	 
	 IMAGE			   Website Name			| Date			| Link	
	 background		-> pixabay,scott webb	| May 4, 2022   | https://pixabay.com/photos/plant-succulent-potted-white-space-2004483/
	 #all other images used in this application were created by Oluwaseun Ogunrinde in PhotoShop
	 
	 */
	
	
	/*GUI ImageIcons*/
	private static ImageIcon red    = new ImageIcon("img//redDark.png");				//image for red button 
	private static ImageIcon blue   = new ImageIcon("img//blueDark.png"); 				//image for blue button 
	private static ImageIcon yellow = new ImageIcon("img//yellowDark.png");				//image for yellow button 
	private static ImageIcon green  = new ImageIcon("img//greenDark.png");				//image for green button 

	private static ImageIcon colorPieIcon = new ImageIcon("img//colorPie.png");			//image for the color pie?
	private static ImageIcon flashIcon    = new ImageIcon(""); 							//empty icon for the different pie flash
																						//It is filled up in the tickTok Override function
	private static ImageIcon generateIcon = new ImageIcon("img//generate.png"); 		//image for the generate button

	
	private static ImageIcon emptyIcon  = new ImageIcon("");							//empty Icon
	private static ImageIcon correctIcon = new ImageIcon("img//correctMark.png");		//correct message image
	private static ImageIcon wrongIcon   = new ImageIcon("img//wrongMark.png");			//loss message image
	
	private static ImageIcon backgroundIcon = new ImageIcon("img//background.png");		//background image Icon
	private static ImageIcon gameOverIcon  = new ImageIcon("img//gameOver.png");		//game over Icon
	private static ImageIcon newLevelIcon  = new ImageIcon("img//newLevel.png");		//new Level Icon

	/*GUI buttons*/
	private static JButton btnRed;				//red button 
	private static JButton btnBlue;				//blue button 
	private static JButton btnYellow;			//yellow button 
	private static JButton btnGreen;			//green button 
	private static JButton btnGenerate;			//generate button 
	
	/*GUI labels*/
	private static JLabel lblMessage;			//message label 	:: displays picture
	private static JLabel lblColorPie;			//colorPie label 	:: displays picture
	private static JLabel lblColorFlash;		//colorFlash label 	:: displays picture
	private static JLabel lblGameScreen;		//gameScreen label 	:: displays picture
	private static JLabel lblBackground;		//background label	:: displays picture
	private static JLabel lblTextMessage;		//message label	:: displays text
	private static JLabel lblPoints;			//points label	:: displays text
	private static JLabel lblLevel;				//level label  	:: displays text
	private static JLabel lblRound;				//round label 	:: displays text
	private static JLabel lblPenalty;			//penalty label :: displays text
	private static JLabel lblReward;			//reward label 	:: displays text
	
	
	/*other data memebers*/
	private static ArrayList<ImageIcon> flashIconArray;									//storage for the imageIcons to be displayed
	private static int flashCounter = 0;												//flashCounter makes sure for a given color passed
																						//it goes to highInstensity and back to low intensity
																						//it runs twice
	
	private static int flashCycleCounter = 0;											//stores the counter for the flashIconArray

	private static final Integer FRAMETIME = 400;										//time per each update for a timer object | 0.4 seconds
	
	private Timer tickTock = new Timer(FRAMETIME,this);									//timer object instantiation

	private static EnumColour enumColour;												//stores the button clicked at a given time
	
	private static boolean darkMode = true;												//checks if we are in the development environment
	
	public static void main(String[] args) {			
		// ####################################################################################################
		// Method				:	void main(String[] args)
		//
		// Method parameters	:	String[] args
		//
		// Method return		:	args - the method permits String command line parameters to be entered
		//
		// Synopsis				:   This method is a boiler code template created by windows builder for 
		//							the graphical user interface
		//							this method is TRIGGERED by the main method in the Main class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTextMessage = new JLabel("");
		lblTextMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextMessage.setBounds(506, 159, 120, 25);
		contentPane.add(lblTextMessage);
			
		lblMessage = new JLabel(emptyIcon);
		lblMessage.setBounds(506, 29, 120, 120);
		contentPane.add(lblMessage);
	
		lblGameScreen = new JLabel(emptyIcon);
		lblGameScreen.setEnabled(true);
		lblGameScreen.setBounds(0, 0, 650, 500);
		contentPane.add(lblGameScreen);
		
		btnRed = new JButton(red);
		btnRed.setEnabled(false);
		btnRed.setBackground(UIManager.getColor("Button.background"));
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("audio//buttonClick.wav");		//plays click sound
				enumColour = new EnumColour(0);				//creates a new enumColor color::red
				GameMechanics.inputColours(enumColour);		//sends the red color to the inputColor function
			}
		});
		btnRed.setBounds(258, 349, 59, 48);
		contentPane.add(btnRed);
		
		btnGreen = new JButton(green);
		btnGreen.setEnabled(false);
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("audio//buttonClick.wav");		//plays click sound
				enumColour = new EnumColour(1);				//creates a new enumColor color::green
				GameMechanics.inputColours(enumColour);		//sends the red color to the inputColor function
			}
		});
		btnGreen.setBounds(325, 405, 59, 48);
		contentPane.add(btnGreen);
		
		btnYellow = new JButton(yellow);
		btnYellow.setEnabled(false);
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("audio//buttonClick.wav");		//plays click sound
				enumColour = new EnumColour(2);				//creates a new enumColor color::yellow
				GameMechanics.inputColours(enumColour);		//sends the red color to the inputColor function
			}
		});
		btnYellow.setBounds(258, 405, 59, 48);
		contentPane.add(btnYellow);
		
		btnBlue = new JButton(blue);
		btnBlue.setEnabled(false);
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("audio//buttonClick.wav");		//plays click sound
				enumColour = new EnumColour(3);				//creates a new enumColor color::blue
				GameMechanics.inputColours(enumColour);		//sends the red color to the inputColor function

			}
		});
		btnBlue.setBounds(325, 349, 59, 48);
		contentPane.add(btnBlue);
		
		btnGenerate = new JButton(generateIcon);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMessage.setIcon(emptyIcon);				//the message Icon is cleared
				lblTextMessage.setText("");					//the text message  is cleared
				lblGameScreen.setIcon(emptyIcon);			//the gameScreen Icon is cleared
				GameMechanics.generateRandomColours();		//the randomColours are generated
				tickTock.start();							//and the timer starts

			}
		});
		btnGenerate.setBounds(258, 316, 126, 25);
		contentPane.add(btnGenerate);
		
		lblPoints = new JLabel("0");
		lblPoints.setFont(new Font("Arial", Font.BOLD, 20));
		lblPoints.setForeground(new Color(240, 230, 140));
		lblPoints.setBounds(112, 87, 54, 33);
		contentPane.add(lblPoints);
		
		lblLevel = new JLabel("1");
		lblLevel.setForeground(new Color(240, 230, 140));
		lblLevel.setFont(new Font("Arial", Font.BOLD, 20));
		lblLevel.setBounds(112, 29, 54, 33);
		contentPane.add(lblLevel);
		
		lblColorFlash = new JLabel(emptyIcon);
		lblColorFlash.setBounds(172, 8, 300, 300);
		contentPane.add(lblColorFlash);
		
		lblColorPie = new JLabel(colorPieIcon);
		lblColorPie.setBounds(172, 8, 300, 300);
		contentPane.add(lblColorPie);
		
		lblRound = new JLabel("1");
		lblRound.setForeground(new Color(240, 230, 140));
		lblRound.setFont(new Font("Arial", Font.BOLD, 20));
		lblRound.setBounds(112, 57, 54, 33);
		contentPane.add(lblRound);
		
		lblReward = new JLabel("10");
		lblReward.setHorizontalAlignment(SwingConstants.CENTER);
		lblReward.setForeground(new Color(240, 230, 140));
		lblReward.setFont(new Font("Arial", Font.BOLD, 20));
		lblReward.setBounds(482, 455, 54, 33);
		contentPane.add(lblReward);
		
		lblPenalty = new JLabel("-20");
		lblPenalty.setHorizontalAlignment(SwingConstants.CENTER);
		lblPenalty.setForeground(new Color(240, 230, 140));
		lblPenalty.setFont(new Font("Arial", Font.BOLD, 20));
		lblPenalty.setBounds(572, 455, 54, 33);
		contentPane.add(lblPenalty);
		
		lblBackground = new JLabel(backgroundIcon);
		lblBackground.setBounds(0, 0, 650, 500);
		contentPane.add(lblBackground);
		

	}

	
	public static void playSound(String filePath) {
		// ####################################################################################################
		// Method				:	void playSound(String filePath)
		//
		// Method parameters	:	String filePath
		//
		// Method return		:	void
		//
		// Synopsis				:   This method plays a sound using the file path passed into the function
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		try {
			File soundFile = new File(filePath);												//creates a new sound file using the parameter passed in
			
			AudioInputStream soundInputStream = AudioSystem.getAudioInputStream(soundFile);		//creates a new soundInput stream
			
			Clip soundClip = AudioSystem.getClip();												//stores the soundClip from the AudioSystem
			
			soundClip.open(soundInputStream);													//opens the sound file
			soundClip.start();																	//plays the music
		
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Problem playing sound");						//error tracking
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// ####################################################################################################
		// Method				:	void actionPerformed(ActionEvent e)
		//
		// Method parameters	:	ActionEvent e
		//
		// Method return		:	void
		//
		// Synopsis				:   This method performs a function every update. The update timer is set as a 
		//							data member "FRAMETIME". This update runs the number of times as the size of the array
		//							it wants to display from.
		//							then, for every given index in the array, the function runs twice.
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
				
		if(flashCycleCounter < flashIconArray.size()) {										//checks if the whole array of colors hasn't been displayed
																							//flashCycleCounter stores the no of colors that have been displayed | it increments once every 2 updates 
																							//flashIconArray stores the whole images to be displayed
			
			flashIcon = flashIconArray.get(flashCycleCounter);								//sets the flashIcon to the current index

		}
		 
		
		if (darkMode){																		//checks if the image is still in darkMode | the image is in darkMode by default					
			
			lblColorFlash.setIcon(flashIcon);												//flashes the color to be flashed passed in through flashIcon

		}
		else{																				//if the image is not in darkMode
		
			lblColorFlash.setIcon(emptyIcon);												//it empties the Icon, making the background to be displayed
			
		}
		
		darkMode = !darkMode;																//darkMode is switched
		
		flashCounter++;																		//flashCounter is incremented | NOTE: flashCounter is different from flashCycleCounter
		if (flashCounter > 1) {																//if the flashCounter has run twice, meaning the light image has flashed and the darkImage is back
			
			flashCounter = 0;																//flashCoutner is reset to 0
			flashCycleCounter++;															//flashCycleCounter is incremented
			
			if (flashCycleCounter >= flashIconArray.size() ) {								//if the Array has been completed
				
				flashCycleCounter = 0;														//the flashCycleCounter is reset
				setGenerateButtonState(false);												//the generate button is disabled
				setColoursButtonState(true);												//color input is enabled
				tickTock.stop();															//tickTok is stopped
			}
				

		}
		
	}
	
	
	
	public static void setColoursButtonState(boolean state){	
		// ####################################################################################################
		// Method				:	void setColoursButtonState(boolean state)
		//
		// Method parameters	:	boolean state
		//
		// Method return		:	void
		//
		// Synopsis				:   This method sets the button state to the parameter passed in
		//							it enables and disables the 4 buttons on screen
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		/*
		btnRed.setIcon(red);
		btnBlue.setIcon(blue);
		btnYellow.setIcon(yellow);
		btnGreen.setIcon(green);
		*/
		
		btnRed.setEnabled(state);		//disables the red button
		btnBlue.setEnabled(state);		//disables the blue button
		btnYellow.setEnabled(state);	//disables the yellow button
		btnGreen.setEnabled(state);		//disables the green button
	}
	

	public static void setFlashIconArray(ArrayList<EnumColour> enumColorArray) {
		// ####################################################################################################
		// Method				:	void setFlashIconArray(ArrayList<EnumColour> enumColorArray)
		//
		// Method parameters	:	ArrayList<EnumColour> enumColorArray
		//
		// Method return		:	void
		//
		// Synopsis				:   This method sets the flashIconArray used to to flash colors
		//							after the random colors have been generated have been generated in game mechanics
		//							the array of the colors is passed into this method.
		//							this method then switches and fills in the flashIconArray with imageIcons
		//							this flashIconArray is then passed to the timer.
		//							this method is TRIGGERED in the GameMechanics class
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-05-04		O. Ogunrinde			Initial setup
		//
		// ######################################################################################################
		
		flashIconArray = new ArrayList<>();						//instantiates a new ArrayList
		
		ArrayList<ImageIcon> tempArray = new ArrayList<>();		//creates a new tempArray to be used in this function
		ImageIcon tempImageIcon = new ImageIcon();				//tempImage Icon created
		
		/*This loops through the array passed in*/
		for(EnumColour enumColor : enumColorArray) {			//Auto ranging loop
			
			switch (enumColor.colour) {							//switches between the color property
			case Red:											//red flash
				tempImageIcon = new ImageIcon("img//redFlash.png");
				break;
			case Blue:											//blue flash
				tempImageIcon = new ImageIcon("img//blueFlash.png");
				break;
			case Green:											//green flash
				tempImageIcon = new ImageIcon("img//greenFlash.png");
				break;
			case Yellow:										//yellow flash
				tempImageIcon = new ImageIcon("img//yellowFlash.png");
				break;	
			default:											//blank flash
				tempImageIcon = new ImageIcon("");
				System.out.println();
				break;
			}
			
			tempArray.add(tempImageIcon);						//adds to the array
		}
		
		flashIconArray = tempArray;								//sets the flashIconArray to the tempArray
																//we could have edited the flashIcon but using an intermediary array
																//makes debugging easily and less error prone
	
	}
	
	
	/* accessor methods :: Getters and setters
	   getters and setters are used to access to set data members in this class
	 */
	
	/*sets the imageIcon for the message display*/
	public static void setMessage(boolean correct){
		lblMessage.setIcon(emptyIcon);
		if(correct)
			lblMessage.setIcon(correctIcon);
		else
			lblMessage.setIcon(wrongIcon);

	}
	
	/*sets the text mesage*/
	public static void setTextMessage(boolean correct) {
		if(correct)
			lblTextMessage.setText("Correct");
		else
			lblTextMessage.setText("Wrong");

	}
	
	/*sets the generate button active state*/
	public static void setGenerateButtonState(boolean state){	
		btnGenerate.setEnabled(state);
	}
	
	/*sets the points variable in the interface*/
	public static void setLblPoints(Integer points) {
		try {
			String pointString = points.toString();
			lblPoints.setText(pointString);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error from set points");
		}
		
	}
	
	/*sets the level variable in the interface*/
	public static void setLblLevel(Integer level) {
		try {
			String levelString = level.toString();
			lblLevel.setText(levelString);
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println("error from set level");
		}
		
	}
	
	/*sets the round variable in the interface*/
	public static void setLblRound(Integer round) {
		try {
			String roundString = round.toString();
			lblRound.setText(roundString);
		} catch (Exception e) {
			// TODO: handle exception
		System.out.println("error from set round");
		}
		
	}
	
	/*sets the imageIcon for the game screen*/	
	public static void setGameScreen(String gameScreen) {
		if(gameScreen == "gameOver") {
			lblGameScreen.setIcon(gameOverIcon);
		}
		else if(gameScreen == "newLevel") {
			lblGameScreen.setIcon(newLevelIcon);

		}
	}

	/*sets the penalty variable in the interface*/
	public static void setLblPenalty(Integer penalty) {
		penalty *= -1;
		String penaltyString = penalty.toString();
		lblPenalty.setText(penaltyString);
	}

	/*sets the reward variable in the interface*/
	public static void setLblReward(Integer reward) {
		String rewardString = reward.toString();
		lblReward.setText(rewardString);
	}
}
