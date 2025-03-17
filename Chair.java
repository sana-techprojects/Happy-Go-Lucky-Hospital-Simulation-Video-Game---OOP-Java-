import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chair here.
 * 
 * @vincent
 * @version (a version number or a date)
 */
public class Chair extends Checkpoint
{
    private int orientation;
    private boolean reflect;
    private int sizer;
    private int sizeMulti;
    private boolean willBeOccupied;
    GreenfootImage chairImage;
    /**
     * Act - do whatever the Chair wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Chair()
    {
        super();
        
        chairImage = new GreenfootImage("Chair.png");
        setImage(chairImage);
        sizeMulti = 0;
        sizer = 1;
        isEmpty = true;
        reflect = false;
        
        willBeOccupied = false;
    }

    public void act() {
        rotate();
        grow();
        super.act();
        if(willBeOccupied == true && !isTouching(Patient.class)) {
            isEmpty = false;
        } else if(isTouching(Patient.class) && willBeOccupied == true){
            willBeOccupied = false;
            isEmpty = true;
        }
    }
    
    private void grow() {
        if(sizeMulti != 1){
            GreenfootImage img = getImage();
            img.scale(img.getWidth()*(10+(++sizer))/11, img.getHeight()*(10+sizer)/11);
            setImage(img);
            sizeMulti++;
        }
    }

    public void rotate() {
        if(!reflect){
            if(getX() <= 540){
                getImage().mirrorHorizontally();
                reflect = true;
            }
        }
    }
    
    public void futureEmptyStatus(boolean isOccupied) {
        willBeOccupied = isOccupied;
    }
    
    public boolean reflectStatus() {
        return reflect;
    }
    
}
