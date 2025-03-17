import greenfoot.*;

/**
 * The NumberDisplay class represents a display for numerical values in the simulation.
 * It allows users to interact with and modify the displayed numbers based on the parameter number.
 * 
 * <p><b>Author:</b></p>
 * <ul>
 *   <li>Sana</li>
 *   <li>Modified by: Vincent</li>
 * </ul>
 * 
 * @author Sana
 * @version 1.0
 */
public class NumberDisplay extends Actor {
    // Parameter number associated with the display
    private int parameterNumber;
    
    // Current displayed number
    private int number;
    
    // Image for displaying the number
    private GreenfootImage display;
    
    // Image for displaying hover text
    private GreenfootImage textImage;

    /**
     * Constructs a NumberDisplay object with an initial number based on the parameter number.
     * 
     * @param userNumber The parameter number associated with the display.
     */
    public NumberDisplay(int userNumber) {
        parameterNumber = userNumber;
        
        // Initialize display image and set default number based on parameter number
        if(parameterNumber == 1 || parameterNumber == 2) {
            display = new GreenfootImage("50", 58, Color.BLACK, Color.WHITE);
            number = 50;
        } else if(parameterNumber == 3 || parameterNumber == 4) {
            display = new GreenfootImage("3", 58, Color.BLACK, Color.WHITE);
            number = 3;
        } else {
            display = new GreenfootImage("100", 58, Color.BLACK, Color.WHITE);
            number = 100;
        }
        setImage(display);
    }

    /**
     * Gets the current displayed number.
     * 
     * @return The current number.
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Gets the parameter number associated with the display.
     * 
     * @return The parameter number.
     */
    public int getParameterNumber() {
        return parameterNumber;
    }
    
    /**
     * Sets the displayed number to the specified value.
     * 
     * @param num The new number value.
     */
    public void setNumber(int num) {
        if (num >= 2 && num <= 10) {
            number = num;
            updateImage();
        }
    }

    // Updates the display image with the current number
    private void updateImage() {
        display = new GreenfootImage(String.valueOf(number), 58, Color.BLACK, Color.WHITE);
        setImage(display);
    }

    /**
     * Increases the displayed number based on the parameter number.
     */
    public void increaseNumber() {
        if((parameterNumber == 1 || parameterNumber == 2) && (number < 90)) {
            number += 5;
            updateImage();
        } else if(parameterNumber == 3 && number < 10) {
            number++;
            updateImage();
        } else if(parameterNumber == 4 && number < 6) {
            number++;
            updateImage();
        } else if(parameterNumber == 5 && number < 120){
            number++;
            updateImage();
        }
    }

    /**
     * Decreases the displayed number based on the parameter number.
     */
    public void decreaseNumber() {
        if((parameterNumber == 1 || parameterNumber == 2) && (number > 10)) {
            number -= 5;
            updateImage();
        } else if(parameterNumber == 3 && number > 1) {
            number--;
            updateImage();
        } else if(parameterNumber == 4 && number > 1) {
            number--;
            updateImage();
        } else if(parameterNumber == 5 && number > 90){
            number--;
            updateImage();
        }
    }

    /**
     * Handles the act method to check for mouse interactions and display hover text.
     */
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if (mouse != null) {
            // Check if mouse is hovering over the NumberDisplay 
            if (isMouseOverNumberDisplay(mouse)) {
                // Show text when hovered over
                showHoverText();
            } else {
                // When it's not being hovered over, show the original image
                updateImage();
            }
        }
    }

    // Method to show text when the mouse is over the NumberDisplay
    private void showHoverText() {
        String text = "";
        if(parameterNumber == 1) {
            text = "The percentage \nof sick patients\n in the world.";
        } else if(parameterNumber == 2) {
            text = "The percentage \nof successful\n surgeries in\nthe ER.";
        } else if(parameterNumber == 3) {
            text = "The number \n of chairs in\n the waiting room.";
        } else if(parameterNumber == 4) {
            text = "The number \n of beds in\n the treatment room.";
        } else {
            text = "The rate patients \nwill arrive to the clinic.";
        }
        
        textImage = new GreenfootImage(text, 15, Color.BLACK, new Color(0, 0, 0, 0));
        setImage(textImage);
    }

    // Method to check if the mouse is over the NumberDisplay
    private boolean isMouseOverNumberDisplay(MouseInfo mouse) {
        int mouseX = mouse.getX();
        int mouseY = mouse.getY();
        int displayX = getX();
        int displayY = getY();
        int displayWidth = display.getWidth();
        int displayHeight = display.getHeight();
        
        return mouseX >= displayX && mouseX <= displayX + displayWidth &&
               mouseY >= displayY && mouseY <= displayY + displayHeight;
    }
}
