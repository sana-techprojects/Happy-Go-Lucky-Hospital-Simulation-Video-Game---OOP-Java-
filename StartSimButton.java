import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * A button that starts the simulation when clicked.
 * 
 * @ Sana
 * @version November 9th, 2023
 */
public class StartSimButton extends Actor {
    
    private ArrayList<NumberDisplay> numberDisplays;
    
    // The image for the start button.
    private GreenfootImage startButton = new GreenfootImage("startButton.png");
    
    // The world to switch to when the button is clicked.
    private MainSim world;
    
    GreenfootSound settingsMusic = new GreenfootSound("playing-in-color-120336.mp3");
    
    private GreenfootSound button = new GreenfootSound("start-13691.mp3");
    
    private int sickRatio;
    private int emerPatientSuccessRate;
    private int numChairs;
    private int numBeds;
    private int spawnRate;
    
    /**
     * Constructor for objects of class StartSimButton.
     */
    public StartSimButton() {
        startButton.scale(280, 80);
        setImage(startButton);
    }

    /**
     * Act - do whatever the StartSimButton wants to do. 
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        settingsMusic.play();
        if (Greenfoot.mouseClicked(this)) {
            button.setVolume(100);
            button.play();
            settingsMusic.stop();
            numberDisplays = (ArrayList<NumberDisplay>)getWorld().getObjects(NumberDisplay.class);
            for(NumberDisplay n : numberDisplays) {
                if(n.getParameterNumber() == 1) {
                    sickRatio = n.getNumber();
                } else if(n.getParameterNumber() == 2) {
                    emerPatientSuccessRate = n.getNumber();
                } else if(n.getParameterNumber() == 3) {
                    numChairs = n.getNumber();
                } else if(n.getParameterNumber() == 4) {
                    numBeds = n.getNumber();
                } else if(n.getParameterNumber() == 5) {
                    spawnRate = n.getNumber();
                }
            }
            world = new MainSim(sickRatio, emerPatientSuccessRate, numChairs, numBeds, spawnRate);
            Greenfoot.setWorld(world);
        }
    }
    
    /**
     * Method to get user info
     * 
     * @author vincent
     */
    public void getUserInfo() {
        
    }
    
    public void started()
    {
        settingsMusic.playLoop();
    }
    //stops/ pauses background sound/ sound effects
    public void stopped()
    {
        settingsMusic.stop();
    }
    
}
