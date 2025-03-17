import greenfoot.*;

/**
 * PlusButtonImg is a Greenfoot Actor that represents a button with a plus sign.
 * 
 * <p>This class provides an interactive button that, when clicked,
 * increases the numerical value displayed in the associated NumberDisplay.</p>
 * 
 * <p>Author: Sana</p>
 * <p>Version: November 9th, 2023</p>
 */
public class PlusButtonImg extends Button {
    // Image for the plus button
    private GreenfootImage plusButton = new GreenfootImage("Screenshot_2023-11-09_003451-removebg-preview.png");
    private GreenfootSound buttonClick = new GreenfootSound("shooting-sound-fx-159024.mp3");
    /**
     * Constructor for PlusButtonImg class.
     * <p>This constructor initializes the plus button image and sets it to the actor.</p>
     */
    public PlusButtonImg(int userNumber) {
        super(userNumber);
        plusButton.scale(55, 55);
        setImage(plusButton);
    }

    /**
     * Perform action when the plus button is clicked.
     * <p>This method is called whenever the 'Act' or 'Run' button is pressed in the environment.
     * It increases the numerical value in the associated NumberDisplay when the button is clicked.</p>
     */
    public void act() {
        super.act();
    }
    
    public void affectNumberDisplay() {
        number.increaseNumber();
    }
}
