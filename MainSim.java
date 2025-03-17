import greenfoot.*;
import java.util.ArrayList;
import greenfoot.GreenfootImage;

/**
 * The MainSim class represents the main simulation world.
 * It includes the hospital layout, doctors, patients, reputation bars, and other elements.
 * 
 * * Hospital Simulation Project:
 * 
 * This simulation can act as an educational and entertaining project
 * with the goal of providing users an opportunity to explore the complexities and challenges
 * involved in managing a hospital environment. Inspired by the vital role of
 * healthcare institutions, the simulation aims to imitate the responsibilities that are
 * present in a hospital setting, such as patient influx, emergency situations.
 * 
 * Story behind the Simulation: 
 * In the heart of Healthsville, a new healthcare venture, Happy Go Lucky Hospital, emerges with a mission 
 * to secure the distinguished 'Responsible Hospital Prize.' The owners of the hospital aims to redefine patient 
 * care by having a seamless and welcoming healthcare experience. Users can adjust crucial parameters such as sick 
 * patient ratios, emergency success rates... to guide the hospital towards success. Happy Go Lucky Hospital fate 
 * is in your hands. Choose the parameters wisely! Will users lead the hospital to triumph or face the complex
 * challenges of healthcare management?
 * 
 * 
 * Sound & Music Credits: 
 * Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=16725">Pixabay</a> 
 * Sounds edited by Sana Pardiwala through Flixier
 * 
 * Image Credits:
 * Main Sim Layout, Buttons, Settings Background, Title of Simulation, Reputation Bar, Other actors in the Main Sim Layout designed by Rebecca
 * Other images from OpenGameArt.org and Google
 * 
 * Features:
 * 1. Random event of an emergency patient where they can either emerge healthy/ pass away, angel image hovering over emergency patient
 * 2. Doctors called out their corresponding patient 
 * 3. Reputation Bar which deterines the well being of the hospital
 * 4. Zoom in transition in the Intro World
 * 5. Scrolling Background in the Intro World
 * 6. In the User Settings World when you hover over the number display it gives you an idea what each parameter does
 * 7. Edited sounds & music
 * 8. Each Patient has their own patience bar which reflects their mood depending on long waits, delays...
 * 9. Humor in the intro world!!
 * 10. Imitates a real life hopital --> real life experience through a simulation
 * 
 * Known Bugs:
 * - Sometimes patients won't follow through their actual pathing and coordinates to follow
 * - Made a huge attempt to fix it but it kept getting bugged
 * 
 * <p><b>Author:</b> Everyone</p>
 * Comments and API done by Sana Pardiwala
 * 
 * @version 1.0 (2023-11-12)
 */
public class MainSim extends World
{
    // Background image
    GreenfootImage bg = new GreenfootImage("images/Map 2.3.png");
    
    // Elements in the world
    private Patient player;
    private Chair c;
    private Bed_down bd;
    private Bed_up bu;
    private Doctor d, d2, d3;
    private ER er = new ER();
    private ReputationBarImg repBar = new ReputationBarImg();
    private VerticalHealthBar repHealthBar = new VerticalHealthBar();
    
    // Variables that USER CAN AFFECT
    private int sickRatio;
    private int emerPatientSuccessRate;
    private int numChairs;
    private int numBeds;
    private int spawnRate;
    
    // Timer variables
    private SimpleTimer timer = new SimpleTimer();
    
    // Background music and sound effects
    GreenfootSound bgm;
    GreenfootSound conversationBgm;
    
    // Reputation variable
    public static int reputation;

    /**
     * Constructor for objects of class MainSim.
     * 
     * @param userSickRatio The ratio of sick patients.
     * @param userEmerPatientSuccessRate The success rate of emergency patients.
     * @param userNumChairs The number of chairs in the waiting room.
     * @param userNumBeds The number of beds in the treatment room.
     * @param userPatientSpawnRate The rate at which patients are spawned.
     */
    public MainSim(int userSickRatio, int userEmerPatientSuccessRate, int userNumChairs, int userNumBeds, int userPatientSpawnRate)
    {    
        // Create a new world with 1109x650 cells and a cell size of 1x1 pixels.
        super(1109, 650, 1, false); 
        
        // Initialize background music and sounds
        bgm = new GreenfootSound("joyful-day-130448.mp3");
        bgm.setVolume(35);
        conversationBgm = new GreenfootSound("conversation-25675.mp3");
        conversationBgm.setVolume(100);
        
        // Set background image
        bg.scale(1109,650);
        setBackground(bg);
        
        // Initialize patient spawner
        PatientSpawner patientSpawner = new PatientSpawner(userSickRatio, userPatientSpawnRate, userEmerPatientSuccessRate);
        addObject(patientSpawner, 0, 630);
        
        // Initialize elements
        Chair c = new Chair();
        Bed_down bd = new Bed_down();
        Bed_up bu = new Bed_up();
        d = new Doctor();
        d2 = new Doctor();
        d3 = new Doctor();
        
        // Add emergency room
        addObject(er, 339, 55);
        
        // Set the number of chairs and beds
        numBeds = userNumBeds;
        numChairs = userNumChairs;
        
        // Prepare the world
        prepare();
        
        // Add reputation-related elements
        addObject(repHealthBar, 1045, 130);
        addObject(repBar, 1045, 160);
    }
    
    /**
     * Act method for the MainSim class.
     * Handles simulation logic and updates reputation.
     */
    public void act()
    {
        started();
        reputation = repHealthBar.getReputation();
        // Check if the simulation time exceeds a certain limit
        if(timer.millisElapsed() > 60000)
        {
            // Transition to the ending world
            Greenfoot.setWorld(new EndingWorld(reputation));
            stopped();
        }
    }
    
    /**
     * Starts background music and sound effects.
     */
    public void started()
    {
        bgm.playLoop();
        conversationBgm.playLoop();
    }
    
    /**
     * Stops or pauses background music and sound effects.
     */
    public void stopped()
    {
        bgm.stop();
        conversationBgm.stop();
    }

    /**
     * Draw a path from start to the first coordinate on the path, and then
     * from one coordinate on the path to the end.
     * 
     * @param start The start Coordinate.
     * @param path The path of Coordinates.
     */
    public void redrawPath(Coordinate start, ArrayList<Coordinate> path) {
        // Implementation of drawing path, not provided in the original code
        // ...
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        // Initialize coordinates for placing chairs and beds
        int x = 873;
        int y = 101;
        
        // Add chairs to the world
        if(numChairs <= 6) {
            for(int i = 0; i < numChairs; i++){
                addChair(x, y);
                y += 74;
            }
        } else {
            for(int i = 0; i < 6; i++){
                addChair(x, y);
                y += 74;
            }
            x = 504;
            y = 101;
            numChairs -= 6;
            if(numChairs <= 2) {
                for(int i = 0; i < numChairs; i++){
                    addChair(x, y);
                    y += 74;
                }
            } else {
                for(int i = 0; i < 2; i++){
                    addChair(x, y);
                    y += 74;
                }
                x = 504;
                y = 400;
                if(numChairs <= 2) {
                    for(int i = 0; i < (numChairs - 12); i++){
                        addChair(x, y);
                        y += 74;
                    }
                } else {
                    for(int i = 0; i < 2; i++){
                        addChair(x, y);
                        y += 74;
                    }
                }
            }
        }
        
        // Reset coordinates for placing beds
        x = 65;
        y = 70;
        
        // Add beds to the world
        if(numBeds <= 3) {
            for(int i = 0; i < numBeds; i++){
                addBedUp(bu, x, y);
                x += 74;
            }
        } else {
            for(int i = 0; i < 3; i++){
                addBedUp(bu, x, y);
                x += 74;
            }
            numBeds -= 3;
            x = 65;
            y = 200;
            if(numBeds <= 3) {
                for(int i = 0; i < numBeds; i++){
                    addBedDown(bd, x, y);
                    x += 74;
                }
            }
        }
        
        // Add doctors, receptionist, and reception desk
        Receptionist receptionist = new Receptionist();
        addObject(receptionist,723, 33);
        ReceptionDesk desk = new ReceptionDesk();
        addObject(desk,690, 40);
        
        addObject(d, 100, 387);
        addObject(d2, 240, 387);
        addObject(d3, 380, 387);
    }
    
    /**
     * Adds a chair to the world at the specified coordinates.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void addChair(int x, int y){
        Chair chair = new Chair();
        addObject(chair, x, y);
    }
    
    /**
     * Adds an up bed to the world at the specified coordinates.
     * 
     * @param bed The up bed object.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void addBedUp(Bed_up bed, int x, int y)
    {
        bed = new Bed_up();
        addObject(bed, x, y);
    }
    
    /**
     * Adds a down bed to the world at the specified coordinates.
     * 
     * @param bed The down bed object.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    private void addBedDown(Bed_down bed, int x, int y)
    {
        bed = new Bed_down();
        addObject(bed, x, y);
    }
}
