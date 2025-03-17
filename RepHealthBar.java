import greenfoot.*;
/**
 *
 * 
 * @sana
 * @version (a version number or a date)
 */
public class RepHealthBar extends Actor {
    private double maxHealth = 100; // Maximum health for visual representation
    private int health; // Actual health

    private Color goodColor = Color.GREEN;
    private Color mediumColor = Color.YELLOW;
    private Color badColor = Color.RED;

    GoodEndingImg goodEnding = new GoodEndingImg();
    BadEndingImg badEnding = new BadEndingImg();

    public RepHealthBar(int reputation) {
        health = reputation;
        updateImage();
    }

    public void setHealth(int health) {
        this.health = health;
        updateImage();
    }

    /**
     * The act method updates the health bar and decreases health over time.
     */
    public void act() {
        decrease();
        updateImage();
    }

    private void updateImage() {
        GreenfootImage image = new GreenfootImage(185, 30);
        int displayedHealth = (int) health; // Calculate displayed health

        if (displayedHealth >= 70) {
            image.setColor(goodColor);
        } else if (displayedHealth >= 40 && displayedHealth < 70) {
            image.setColor(mediumColor);
        } else if (displayedHealth >= 0 && displayedHealth < 40) {
            image.setColor(badColor);
        }
        image.fillRect(1, 1, displayedHealth * 3, 28);
        setImage(image);
    }

    /**
     * Method that decreases the actual health over time.
     * The health bar stops decreasing when it reaches 55.
     */
    private void decrease() {
        if (health > 55) {
            health--;
            //getWorld().addObject(badEnding, 320, 300);
        }
    }

    /**
     * Method used to get access to a patient's health bar's numerical value.
     *
     * @return health is the integer that determines the length of the bar.
     * The higher the health integer is, the wider the bar becomes.
     */
    public int getH() {
        return health;
    }
}

