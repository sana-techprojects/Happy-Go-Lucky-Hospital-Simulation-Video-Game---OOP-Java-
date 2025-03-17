import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The UserSettings class represents the world where users can modify the simulation settings.
 * It allows users to adjust various parameters before starting the simulation.
 * 
 * <p><b>Authors:</b></p>
 * <ul>
 *   <li>Sana</li>
 *   <li>Modified by: Vincent</li>
 * </ul>
 * 
 * <p><b>Version:</b> [Version Number or Date]</p>
 * 
 * @sana
 * @11/29/23
 * Modified by Vincent
 */
public class UserSettings extends World
{
    // Background image for the user settings world
    GreenfootImage bg = new GreenfootImage("Selections.png");

    // Plus button image for adjusting parameters
    PlusButtonImg plusButtonImg;

    // Counter for parameter number
    private int parameterNumber = 1;

    // Button to start the simulation
    StartSimButton startButton = new StartSimButton();

    /**
     * Constructor for objects of class UserSettings.
     * Creates a new world with a specified size and sets the background.
     */
    public UserSettings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1109, 650, 1);
        bg.scale(1109, 650);
        setBackground(bg);

        // Adds sections for adjusting parameters
        int y = 163;
        for(int i = 0; i < 5; i++) {
            addSection(y);
            y += 95;
        }

        addObject(startButton, 909, 84);

        prepare();
    }
    
    /**
     * Adds a section for adjusting a parameter at the specified y-coordinate.
     * 
     * @param y The y-coordinate for placing the parameter adjustment section.
     */
    private void addSection(int y)
    {
        PlusButtonImg plusButton = new PlusButtonImg(parameterNumber);
        addObject(plusButton, 630, y);
        NumberDisplay numberDisplay = new NumberDisplay(parameterNumber);
        addObject(numberDisplay, 730, y);
        MinusButtonImg minusButton = new MinusButtonImg(parameterNumber);
        addObject(minusButton, 830, y);
        parameterNumber++;
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        // No specific preparation code for now
    }
}
