import greenfoot.*;

public class ClipboardImg extends Actor {
    private int targetWidth = 380;
    private int targetHeight = 490;
    private int currentWidth = 1;  // Start with a small width
    private int currentHeight = 1;  // Start with a small height
    private int scaleIncrement = 5;  // Adjust the increment based on how fast you want it to scale
    
    RepBarResult repBarImg = new RepBarResult();
    RepHealthBar repBar;
    TextDisplayConclusion reputationText = new TextDisplayConclusion("REPUTATION");
    
    
    public ClipboardImg(int reputation) {
        repBar = new RepHealthBar(reputation);
        setImage(new GreenfootImage(1, 1));  // Set a small initial image
    }

    public void act() {
        // Gradually increase the width and height
        currentWidth += scaleIncrement;
        currentHeight += scaleIncrement;

        // Create a new scaled image
        GreenfootImage scaledImage = new GreenfootImage("scoreboard.png");
        scaledImage.scale(currentWidth, currentHeight);

        // Set the scaled image
        setImage(scaledImage);

        // Check if the target size is reached
        if (currentWidth >= targetWidth || currentHeight >= targetHeight) {
            // Stop scaling once the target size is reached
            GreenfootImage finalImage = new GreenfootImage("scoreboard.png");
            finalImage.scale(targetWidth, targetHeight);
            setImage(finalImage);
            getWorld().addObject(repBar, 865, 300);
            getWorld().addObject(repBarImg,865, 300);
            getWorld().addObject(reputationText, 857, 234);
        }
    }
}
