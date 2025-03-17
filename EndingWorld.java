import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The EndingWorld class represents the world where the game ends.
 * It displays the final results, including reputation and other concluding elements.
 * 
 * <p><b>Authors:</b></p>
 * <ul>
 *   <li>Sana</li>
 *   <li>Modified by: Vincent</li>
 * </ul>
 * 
 * <p><b>Version:</b> 11/28/23</p>
 * 
 * <p><b>How to Use:</b></p>
 * <p>
 *   To use this class, create an instance of EndingWorld with the world's reputation.
 * 
 * </p>
 * 
 * @author Sana
 * @version 11/28/23
 * Modified by Vincent
 */
public class EndingWorld extends World
{
    // Background sound for the ending world
    GreenfootSound bg = new GreenfootSound("");
    
    // Background image for the ending world
    GreenfootImage endingBg = new GreenfootImage("endingBg.jpg");
    
    // Clipboard actor for displaying results
    ClipboardImg clipboard;
    
    // Actor displaying reputation bar result
    RepBarResult repBarImg = new RepBarResult();
    
    // Health bar representing reputation
    RepHealthBar repBar;
    
    // The reputation value of the world
    private int reputation;
    
    // Image for a good ending
    GoodEndingImg goodEnding = new GoodEndingImg();
    
    // Image for a bad ending
    BadEndingImg badEnding = new BadEndingImg();
    
    /**
     * Constructor for objects of class EndingWorld.
     * Creates a new world with a specified size and sets the background.
     * 
     * @param worldReputation The initial reputation value of the world.
     */
    public EndingWorld(int worldReputation)
    {   
        super(1109, 650, 1); 
        endingBg.scale(1109,650);
        setBackground(endingBg);
        
        prepare();
        
        clipboard = new ClipboardImg(worldReputation);
        addObject(clipboard, 873, 362);
        
        reputation = worldReputation;
        
        // Display either a good or bad ending image based on reputation
        if(reputation < 50) {
            addObject(badEnding, 320, 300);
        } else {
            addObject(goodEnding, 320, 300);
        }
    }
    
    /**
     * Gets the current reputation value of the world.
     * 
     * @return The reputation value.
     */
    public int getReputation() {
        return reputation;
    }
    
    /**
     * Prepares the ending world by adding necessary actors.
     */
    private void prepare() {
        
        
    }
}
