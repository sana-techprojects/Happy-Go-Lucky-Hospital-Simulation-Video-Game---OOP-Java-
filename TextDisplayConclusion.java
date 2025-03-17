import greenfoot.*;
/**
 * Write a description of class SIckPatient here.
 * 
 * @Sana
 * @version (a version number or a date)
 */
public class TextDisplayConclusion extends Actor {
    private String conclusionText;
    private int transparency = 0;
    private int fadeSpeed = 5;

    public TextDisplayConclusion(String text) {
        conclusionText = text;
        setImage(new GreenfootImage(conclusionText, 30, Color.BLACK, new Color(0, 0, 0, transparency)));
    }

    public void act() {
        // Fade in effect
        if (transparency < 255) {
            getImage().setTransparency(transparency);
            transparency += fadeSpeed;
        }
    }
}
