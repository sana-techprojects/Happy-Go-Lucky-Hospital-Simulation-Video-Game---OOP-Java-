import greenfoot.*;
/**
 * Write a description of class Chair here.
 * 
 * @sana
 * @version (a version number or a date)
 */
public class HealthBar extends Actor {
    private int health = 53;
    private Color goodColor = Color.GREEN;
    private Color mediumColor = Color.YELLOW;
    private Color badColor = Color.RED;

    public HealthBar() {
        setImage(new GreenfootImage(health, 12));
        getImage().drawRect(0, 0, 51, 5);
        updateColor();
    }

    public void setHealth(int health) {
        this.health = health;
        updateColor();
    }
    
    /**
     * The method "updateColor()" constantly resets the health bar, changing its width and color after periods of time.
     */
    public void act(){
        updateColor();
    }

    private void updateColor() {
        setImage(new GreenfootImage(health, 12));
        if (health >= 35) {
            getImage().setColor(goodColor);
        } else if (health >= 20 && health < 35) {
            getImage().setColor(mediumColor);
        } else if (health >= 0 && health < 20) {
            getImage().setColor(badColor);
        }
        getImage().fillRect(1, 1, health, 10);
    }
    
    
    /**
     * Method that decreases the width of the health bar as long as the variable "health" is greater than or equal to 3.
     */
    public void decrease(){
        if(health >= 3){
            health--;
        }
    }

    /**
     * Method used to get access to a patient's health bar's numerical value.
     * @return health is the integer that determines the length of the bar. The higher the health integer is, the wider the bar becomes.
     */
    public int getH(){
        return health;
    }
}
