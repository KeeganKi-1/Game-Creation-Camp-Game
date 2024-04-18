package code;

import java.awt.Font;

/**
 * Class ArcadeDemo
 * This class contains demos of many of the things you might
 * want to use to make an animated arcade game.
 * 
 * Adapted from the AppletAE demo from years past. 
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.Timer;
import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;

public class CookieClicker extends AnimationPanel {
   
    ArcadeRunner runner;
    int score = 40000;
    boolean autoClickers = false;
    boolean abilities = false;
    boolean mouseAutoClick = false;

    int autoClickFactoryAmount = 0;

    int amountClicker1 = 0;
    int amountClicker5 = 0;
    int amountClicker10 = 0;
    int amountClicker25 = 0;
    int amountClicker100 = 0;
    int unlockClickersCost =100;
    double critChance = 0;
    
    int unlockAbiltiesCost = 100;
    int critChanceCost = 1000;
    int mouseAutoClickCost = 300;
    int randomScoreCost = 1000;
    int autoClickerFactoryCost = 1000;

    CircleHitbox cookieHitbox;
    Rectangle unlockClickersHitbox;
    Rectangle buyClicker1Hitbox;
    Rectangle buyClicker5Hitbox;
    Rectangle buyClicker10Hitbox;
    Rectangle buyClicker25Hitbox;
    Rectangle buyClicker100Hitbox;
    Rectangle unlockAbilitiesHitbox;
    Rectangle critChanceHitbox;
    Rectangle mouseAutoClickerHitbox;
    Rectangle randomScoreHitbox;
    Rectangle autoClickerFactoryHitbox;

    int costClicker1;
    int costClicker5;
    int costClicker10;
    int costClicker25;
    int costClicker100;
    // Constructor
    // -------------------------------------------------------
    public CookieClicker() { // Enter the name and width and height.
        super("ArcadeDemo", 1400, 775);
        System.out.println("Hello world");

        cookieHitbox = new CircleHitbox(500, 200, 150);

        unlockClickersHitbox = new Rectangle(0,0,300,100);
        unlockAbilitiesHitbox = new Rectangle(975,0,300,100);

        buyClicker1Hitbox = new Rectangle(0,120,300,100);
        buyClicker5Hitbox = new Rectangle(0,240,300,100);
        buyClicker10Hitbox = new Rectangle(0,360,300,100);
        buyClicker25Hitbox = new Rectangle(0,480,300,100);
        buyClicker100Hitbox = new Rectangle(0,600,300,100);

        critChanceHitbox = new Rectangle(975,120,300,100);
        mouseAutoClickerHitbox = new Rectangle(975,240,300,100);
        randomScoreHitbox = new Rectangle(975,360,300,100);
        autoClickerFactoryHitbox = new Rectangle(975,480,300,100);

        costClicker1 = 100;
        costClicker5 = 400;
        costClicker10 = 800;
        costClicker25 = 1600;
        costClicker100 = 30000;
    }

    // The renderFrame method is the one which is called each time a frame is drawn.
    // -------------------------------------------------------
    protected void renderFrame(Graphics g) {
        g.drawImage(backGround, 0, 0, this);
        g.drawImage(cookieImage, cookieHitbox.x, cookieHitbox.y, this);
        g.drawImage(unlockAutoClicker, unlockClickersHitbox.x, unlockClickersHitbox.y, this);
        g.drawImage(ImageClicker1, buyClicker1Hitbox.x,buyClicker1Hitbox.y, this);
        g.drawImage(ImageClicker5, buyClicker5Hitbox.x,buyClicker5Hitbox.y, this);
        g.drawImage(ImageClicker10, buyClicker10Hitbox.x,buyClicker10Hitbox.y, this);
        g.drawImage(ImageClicker25, buyClicker25Hitbox.x,buyClicker25Hitbox.y, this);
        g.drawImage(ImageClicker100, buyClicker100Hitbox.x,buyClicker100Hitbox.y, this);
        g.drawImage(unlockAbilities, unlockAbilitiesHitbox.x, unlockAbilitiesHitbox.y, this);
        g.drawImage(critImage, critChanceHitbox.x, critChanceHitbox.y, this);
        g.drawImage(mouseAutoClickImage, mouseAutoClickerHitbox.x, mouseAutoClickerHitbox.y, this);
        g.drawImage(multiplyImage, randomScoreHitbox.x, randomScoreHitbox.y, this);
        g.drawImage(autoClickFactoryImage, autoClickerFactoryHitbox.x, autoClickerFactoryHitbox.y, this);
        
        
        g.setFont(new Font("Times New Roman",0,25));
        g.drawString("Score: "+score, 300, 25);
        g.drawString("Crit Chance: "+(int)(critChance*100),800,25);
        
        if(frameNumber%60==0){
        updateScore();
        }
        if((mouseAutoClick && frameNumber%10 == 0) && cookieHitbox.isIntersecting(mouseX,mouseY)){
            if(Math.random()<critChance)
            score+=7;
            else
            score++;
        }

    }

    public void updateScore(){
        amountClicker1 += autoClickFactoryAmount;
        if(autoClickers){
            if(Math.random()<critChance){
                score+=7*amountClicker1;
                score+=7*amountClicker5*5;
                score+=7*amountClicker10*10;
                score+=7*amountClicker25*25;
                score+=7*amountClicker100*100;
            }
            else{
            score+=amountClicker1;
            score+=amountClicker5*5;
            score+=amountClicker10*10;
            score+=amountClicker25*25;
            score+=amountClicker100*100;
            }
            
        }
    }
    
           

 



    // -------------------------------------------------------
    // Respond to Mouse Events
    // -------------------------------------------------------
    public void mouseClicked(MouseEvent e) {

        if(cookieHitbox.isIntersecting(mouseX,mouseY)){
            if(Math.random()<critChance)
            score+=7;
            else
            score++;
        }
        else if(unlockClickersHitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >= unlockClickersCost){
                autoClickers = true;
                score -=unlockClickersCost;
        }
        else if(buyClicker1Hitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >=costClicker1){
            amountClicker1++;
            score -=costClicker1;
        }
        else if(buyClicker5Hitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >=costClicker5){
            amountClicker5++;
            score -=costClicker5;
        }
        else if(buyClicker10Hitbox.intersects(new Rectangle(mouseX,mouseY,1,1))
         && score >=costClicker10){
            amountClicker10++;
            score -=costClicker10;
        }
        else if(buyClicker25Hitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >=costClicker25){
            amountClicker25++;
            score -=costClicker25;
        }
        else if(buyClicker100Hitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >=costClicker100){
            amountClicker100++;
            score -=costClicker100;
        }
        else if(unlockAbilitiesHitbox.intersects(new Rectangle(mouseX,mouseY,1,1))
         && score >= unlockAbiltiesCost){
            abilities = true;
            score -= unlockAbiltiesCost;
        }
        else if(critChanceHitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >= critChanceCost&& abilities){
            critChance += .05;
            score-= critChanceCost;
        }
        else if(randomScoreHitbox.intersects(new Rectangle(mouseX,mouseY,1,1))
         && score >= randomScoreCost&& abilities){
            score-=randomScoreCost;
            score =(int) (score* Math.random()*3);
        }
        else if(mouseAutoClickerHitbox.intersects(new Rectangle(mouseX,mouseY,1,1)) 
        && score >= mouseAutoClickCost && abilities){
            mouseAutoClick = true;
            score-=mouseAutoClickCost;
        }
        else if(autoClickerFactoryHitbox.intersects(new Rectangle(mouseX,mouseY,1,1))
         && score >= autoClickerFactoryCost && abilities){
            autoClickFactoryAmount++;
            score-=autoClickerFactoryCost;
        }
        

    }

    // -------------------------------------------------------
    // Respond to Keyboard Events
    // -------------------------------------------------------
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
       

    }

    public void keyReleased(KeyEvent e) {
       
    }

    // -------------------------------------------------------
    // Initialize Graphics
    // -------------------------------------------------------
    // -----------------------------------------------------------------------
    /*
     * Image section...
     * To add a new image to the program, do three things.
     * 1. Make a declaration of the Image by name ... Image imagename;
     * 2. Actually make the image and store it in the same directory as the code.
     * 3. Add a line into the initGraphics() function to load the file.
     * //-----------------------------------------------------------------------
     */
 Image cookieImage;
 Image ImageClicker1;
 Image ImageClicker5;
 Image ImageClicker10;
 Image ImageClicker25;
 Image ImageClicker100;

 Image unlockAutoClicker;
 Image unlockAbilities;
 
 Image critImage;
 Image mouseAutoClickImage;
 Image multiplyImage;
 Image autoClickFactoryImage;

 Image backGround;
    public void initGraphics() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        cookieImage = toolkit.getImage("src/images/cookie.png").getScaledInstance(300, 300, 0);
        ImageClicker1 = toolkit.getImage("src/images/BuyBasicClicker.png").getScaledInstance(300, 100, 0);
        ImageClicker5 = toolkit.getImage("src/images/Imagex5.png").getScaledInstance(300, 100, 0);
        ImageClicker10 = toolkit.getImage("src/images/Imagex10.png").getScaledInstance(300, 100, 0);
        ImageClicker25 = toolkit.getImage("src/images/Imagex25.png").getScaledInstance(300, 100, 0);
        ImageClicker100 = toolkit.getImage("src/images/Imagex100.png").getScaledInstance(300, 100, 0);

        unlockAutoClicker = toolkit.getImage("src/images/unlockAutoClicker.png").getScaledInstance(300, 100, 0);
        unlockAbilities = toolkit.getImage("src/images/UnlockAbilities.png").getScaledInstance(300, 100, 0);
        
        critImage = toolkit.getImage("src/images/CritChance.png").getScaledInstance(300, 100, 0);
        mouseAutoClickImage = toolkit.getImage("src/images/mouse auto clicker.png").getScaledInstance(300, 100, 0);
        multiplyImage = toolkit.getImage("src/images/MultiplyScoreByRandomAmount.png").getScaledInstance(300, 100, 0);
        autoClickFactoryImage = toolkit.getImage("src/images/AutoClickerFactory.png").getScaledInstance(300, 100, 0);
        backGround = toolkit.getImage("src/images/Back.jpg").getScaledInstance(1400, 700, 0);
    } // --end of initGraphics()--

    // -------------------------------------------------------
    // Initialize Sounds
    // -------------------------------------------------------
    // -----------------------------------------------------------------------
    /*
     * Music section...
     * To add music clips to the program, do four things.
     * 1. Make a declaration of the AudioClip by name ... AudioClip clipname;
     * 2. Actually make/get the .wav file and store it in the same directory as the
     * code.
     * 3. Add a line into the initMusic() function to load the clip.
     * 4. Use the play(), stop() and loop() functions as needed in your code.
     * //-----------------------------------------------------------------------
     */
    Clip themeMusic;
    Clip bellSound;

    public void initMusic() {
      
      
            // themeMusic.start(); //This would make it play once!
             // This would make it loop 2 times.
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}// --end of ArcadeDemo class--
