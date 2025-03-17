import greenfoot.*;
/**
 * The world of which the intro simulation takes place in.
 * <p> @Sana Pardiwala
 * <p>
 */
public class SpeechBubble extends Actor {
    GreenfootImage bubble = new GreenfootImage("image-removebg-preview.png");
    
    public SpeechBubble() {
        bubble.scale(220,70);
        setImage(bubble);
    }
}
