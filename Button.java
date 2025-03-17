import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The Button class represents an abstract button in the simulation.
 * It serves as a base class for different types of buttons.
 * 
 * <p><b>Author:</b></p>
 * <ul>
 *   <li>Vincent</li>
 * </ul>
 * 
 * <p><b>Version:</b> 11/29/23</p>
 * 
 * @vincent
 * @version 11/29/23
 */
public abstract class Button extends Actor
{
    protected int parameterNumber;
    NumberDisplay number;
    protected ArrayList<NumberDisplay> numberDisplays;
    
    // Sound effect for button click
    protected GreenfootSound buttonClick = new GreenfootSound("shooting-sound-fx-159024.mp3");
    
    // Image for the button
    GreenfootImage buttonImage;
    
    /**
     * Constructs a Button with a specified user number.
     * 
     * @param userNumber The user number associated with the button.
     */
    public Button(int userNumber) {
        setImage(buttonImage);
        parameterNumber = userNumber;
    }
    
    /**
     * Performs an action when the button is clicked.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            buttonClick.setVolume(100);
            buttonClick.play();
            // Get the NumberDisplay object and modify the number
            numberDisplays = (ArrayList<NumberDisplay>)getWorld().getObjects(NumberDisplay.class);
            for(NumberDisplay n : numberDisplays) {
                if(n.getParameterNumber() == parameterNumber) {
                    number = n;
                    affectNumberDisplay();
                }
            }
        }
    }
    
    /**
     * Abstract method to be implemented by subclasses.
     * Defines how the button affects the associated NumberDisplay.
     */
    public abstract void affectNumberDisplay();
}
