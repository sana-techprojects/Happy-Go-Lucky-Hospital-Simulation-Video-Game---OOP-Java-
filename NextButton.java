import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextButton here.
 * 
 * @sana
 * @version (a version number or a date)
 */
public class NextButton extends Actor
{
    GreenfootImage continueButton = new GreenfootImage("Continue button.png");
    GreenfootSound titleSong = new GreenfootSound("summer-ukulele-upbeat-happy-positive-children-kids-114026.mp3");
    //private UserSettings settings = new UserSettings();
    private GreenfootSound button = new GreenfootSound("start-13691.mp3");
    /**
     * Act - do whatever the NextButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public NextButton()
    {
        setImage(continueButton);
    }
    
    public void act()
    {
        titleSong.play();
        if (Greenfoot.mouseClicked(this)) {
            button.setVolume(100);
            button.play();
            titleSong.stop();
            Greenfoot.setWorld(new UserSettings());
            getWorld().removeObject(this);
        }
    }
    
    public void started()
    {
        titleSong.playLoop();
    }
    //stops/ pauses background sound/ sound effects
    public void stopped()
    {
        titleSong.stop();
    }
}
