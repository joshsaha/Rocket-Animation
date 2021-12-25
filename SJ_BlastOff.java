import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import hsa.Console;
import sun.audio.*;
import javax.imageio.*;
import java.io.*;
import java.util.Random;

public class SJ_BlastOff
{
    static Console c;       // The output console
    static Graphics2D graphics;
    static Random rand = new Random ();

    //*******************Image Loading**********************
    public static Image loadImage (String file) throws Exception
    {
	Image img = ImageIO.read (new File (file));
	return img;
    }


    //*******************Audio********************
    public static void playAudio (String file) throws Exception
    {
	InputStream in = new FileInputStream (file);
	AudioStream as = new AudioStream (in);
	AudioPlayer.player.start (as);
    }


    //****************MAIN Method*****************
    public static void main (String[] args) throws Exception
    {
	c = new Console ();
	BufferedImage image = new BufferedImage (640, 480, BufferedImage.TYPE_INT_ARGB);
	graphics = image.createGraphics ();

	Image rocket = loadImage ("rocket1.png");
	Image rocketSized = rocket.getScaledInstance (100, 180, Image.SCALE_DEFAULT);

	Image launchpad = loadImage ("launchpad2.jpg");
	Image rocketExplosion = loadImage ("rocketExplosion.png");
	int x = 150;



	graphics.drawImage (launchpad, -300, -100, null);
	graphics.drawImage (rocketSized, 250, 260, null);
	c.drawImage (image, 0, 0, null);

	c.println ("Press any key to initiate launch");
	c.getChar ();
	
	
	
	Image one = loadImage("one.png");
	Image two = loadImage("two.png");
	Image three = loadImage("three.png");
	
	Image oneSized = one.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
	Image twoSized = two.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
	Image threeSized = three.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
	Image[] nums = {threeSized, twoSized, oneSized};
	
	for(int i = 0; i<nums.length; i++)
	{
	    graphics.drawImage(launchpad, -300, -100, null);
	    graphics.drawImage (rocketSized, 250, 260, null);
	    graphics.drawImage(nums[i], rand.nextInt (450), rand.nextInt (250), null);
	    c.drawImage(image, 0, 0, null);
	    Thread.sleep(1000);
	}
	
	//Explosion indicating the combustion of hydrocarbons in the tank
	Image rocketExplosionSized = rocketExplosion.getScaledInstance (x, x, Image.SCALE_DEFAULT);
	graphics.drawImage (rocketExplosionSized, 230, 350, null);
	playAudio("explosion.wav");
	c.drawImage (image, 0, 0, null);
	Thread.sleep (1000);

	Image rocketFire = loadImage ("Fire2.png");
	Image rocketFireSized = rocketFire.getScaledInstance (100, 180, Image.SCALE_DEFAULT);
	graphics.drawImage (rocketFireSized, 250, 390, null);
	c.drawImage (image, 0, 0, null);

	//rocket going up
	for (int i = 0 ; i < 300 ; i++)
	{
	    graphics.drawImage (launchpad, -300 + rand.nextInt(50), -100 + rand.nextInt(50), null);
	    graphics.drawImage (rocketSized, 250, 260 - 3 * i, null);
	    graphics.drawImage (rocketFireSized, 250, 390 - 3 * i, null);
	    c.drawImage (image, 0, 0, null);
	}
	
	Image earthHorizon = loadImage("earthHorizon.jpg"); //background is the horizon of the earth
	Image rocketSized2 = rocket.getScaledInstance(50, 90, Image.SCALE_DEFAULT); //size of rocket over the horizon
	Image rocketFireSized2 = rocketFire.getScaledInstance(50, 90, Image.SCALE_DEFAULT); //size of rocket fire over the horizon
	
	//rocket going over the horizon of the earth
	for(int i = 0; i < 300; i++)
	{
	    graphics.drawImage(earthHorizon, 0 + rand.nextInt(20), 0 + rand.nextInt(20), null);
	    graphics.drawImage(rocketSized2, 250, 260 - i, null);
	    graphics.drawImage(rocketFireSized2, 250, 320 - i, null);
	    c.drawImage(image, 0, 0, null);
	}
	

	Image spaceBackground = loadImage ("space.jpg"); //setting the background as space
	Image spaceBackgroundSized = spaceBackground.getScaledInstance (700, 500, Image.SCALE_DEFAULT);
	graphics.drawImage (spaceBackgroundSized, 0, 0, null);
	c.drawImage (image, 0, 0, null);

	Image rocketSideways = loadImage ("rocketSideways.png");
	Image fireSideways = loadImage ("fireSideways.png");
	Image rocketSidewaysSized = rocketSideways.getScaledInstance (180, 100, Image.SCALE_DEFAULT);
	Image fireSidewaysSized = fireSideways.getScaledInstance (180, 100, Image.SCALE_DEFAULT);

	//rocket going in right direction towards other planet
	Image missileRight = loadImage("missileRight.png");
	for (int i = 0 ; i <= 300 ; i++)
	{
	    int y = (int) (Math.sin (Math.toRadians (i)) * 125 + 125);
	    graphics.drawImage (spaceBackgroundSized, 0, 0, null);
	    graphics.drawImage (rocketSidewaysSized, 2 * i, y, null);
	    graphics.drawImage (fireSidewaysSized, 2 * i - 140, y, null);
	    //graphics.drawImage (missileRight, 10 * i, 200, null);
	    c.drawImage (image, 0, 0, null);
	}
	
	

	Image planetBackground = loadImage ("pluto.jpg"); // animate rocket landing on a different planet
	Image planetBackgroundSized = planetBackground.getScaledInstance (150, 150, Image.SCALE_DEFAULT);
	
	//changing size of planet (gives sense of approaching new planet)
	for(int i = 0; i<=200; i++)
	{
	    Image planetBackgroundChangeSize = planetBackground.getScaledInstance(50 + i, 50 + i, Image.SCALE_DEFAULT);
	    graphics.drawImage(spaceBackgroundSized, 0, 0, null);
	    graphics.drawImage(planetBackgroundChangeSize, 300 - i, 100, null);
	    c.drawImage(image, 0, 0, null);
	}


	//landing on another planet
	for (int i = 0 ; i <= 50 ; i++)
	{
	    graphics.drawImage (spaceBackgroundSized, 0, 0, null);
	    graphics.drawImage (planetBackgroundSized, 200, 200, null);
	    graphics.drawImage (rocketSized, 225, i, null);
	    c.drawImage (image, 0, 0, null);
	}
	c.println ("press any key to go back home");
	c.getChar ();
	
	graphics.drawImage (rocketExplosionSized, 205, 140, null);
	playAudio("explosion.wav");
	c.drawImage (image, 0, 0, null);
	Thread.sleep(1000);
	

	//going up from new planet
	for (int i = 0 ; i < 300 ; i++)
	{
	    graphics.drawImage (spaceBackgroundSized, rand.nextInt(50), rand.nextInt(50), null);
	    graphics.drawImage (planetBackgroundSized, 200 + rand.nextInt(50), 200 + rand.nextInt(50), null);
	    graphics.drawImage (rocketSized, 225, 50 - 5 * i, null);
	    graphics.drawImage (rocketFireSized, 225, 180 - 5 * i, null);
	    c.drawImage (image, 0, 0, null);
	}

	Image fireLeft = loadImage ("FireLeft.png");
	Image fireLeftSized = fireLeft.getScaledInstance (180, 100, Image.SCALE_DEFAULT);
	Image rocketLeft = loadImage("rocketLeft.png");
	Image rocketLeftSized = rocketLeft.getScaledInstance(180, 100, Image.SCALE_DEFAULT);


	//going towards earth
	for (int i = 400 ; i >= -300 ; i--)
	{
	    int y = (int) (Math.sin (Math.toRadians (i)) * 125 + 125);
	    graphics.drawImage (spaceBackgroundSized, 0, 0, null);
	    graphics.drawImage (fireLeftSized, 2*i + 130, y, null);
	    graphics.drawImage(rocketLeftSized, 2*i, y, null);
	    c.drawImage (image, 0, 0, null);
	}
	
	
	//landing back on the same spot as the starting spot --> original launchpad
	for(int i = 0; i<=260; i++)
	{
	    graphics.drawImage(launchpad, -300 + rand.nextInt(50), -100 + rand.nextInt(50), null);
	    graphics.drawImage (rocketSized, 225, i, null);
	    c.drawImage(image, 0, 0, null);
	}
	
	c.println("Congratulations, you have landed successfully!");
	
	
	










	// Place your program here.  'c' is the output console
    } // main method
} // SJ_BlastOff class
