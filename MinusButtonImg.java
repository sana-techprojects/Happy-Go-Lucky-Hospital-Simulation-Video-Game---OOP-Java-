import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * MinusButtonImg is a Greenfoot Actor that displays the minus button image.
 * 
 * <p>This class provides the graphical representation of the minus button image.
 * It includes the initialization of the image and scaling to fit the button.</p>
 * 
 * <p>Author: Sana</p>
 * <p>Version: November 9th, 2023 </p>
 */
public class MinusButtonImg extends Button
{
    // Image for the minus button
    GreenfootImage minusButton = new GreenfootImage("Screenshot_2023-11-09_002050-removebg-preview.png");
    private GreenfootSound buttonClick = new GreenfootSound("shooting-sound-fx-159024.mp3");
    /**
     * Constructor for MinusButtonImg class.
     * <p>This constructor initializes the minus button image and scales it to fit the button.</p>
     */
    public MinusButtonImg(int userNumber)
    {
        super(userNumber);
        minusButton.scale(55, 55);
        setImage(minusButton);
    }
    
    /**
     * Act method for MinusButtonImg class.
     * <p>This method is called whenever the 'Act' or 'Run' button gets pressed in the
     * environment. It contains the action code for the MinusButtonImg class.</p>
     */
    public void act()
    {
        super.act();
    }
    
    public void affectNumberDisplay() {
        number.decreaseNumber();
    }
}
