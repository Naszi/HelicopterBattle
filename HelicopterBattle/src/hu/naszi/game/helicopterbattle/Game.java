package hu.naszi.game.helicopterbattle;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Game {
	private Random random;
	private Robot robot;

	private PlayerHelicopter player;

	private ArrayList<EnemyHelicopter> enemyHelicopterList = new ArrayList<EnemyHelicopter>();

	private ArrayList<Animation> explosionList;
	private BufferedImage explosionAnimImg;

	private ArrayList<Bullet> bulletsList;
	private ArrayList<Rocket> rocketsList;
	private ArrayList<RocketSmoke> rocketSmokeList;

	private BufferedImage skyColorImg;
	private BufferedImage cloudLayer1Img;
	private BufferedImage cloudLayer2Img;
	private BufferedImage mountainsImg;
	private BufferedImage groundImg;

	private MovingBackground cloudLayer1Moving;
	private MovingBackground cloudLayer2Moving;
	private MovingBackground mountainsMoving;
	private MovingBackground groundMoving;

	private BufferedImage mouseCursorImg;

	private Font font;

	private int runAwayEnemies;
	private int destroyedEnemies;
	
	public Game() {
		Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
		
		Thread threadForInitGame = new Thread() {
			public void run() {
				Initialize();
				LoadContent();
				Framework.gameState = Framework.GameState.PLAYING;
			}
		};
		threadForInitGame.start();
	}
	
	private void Initialize() {
		random = new Random();
		
		try {
			robot = new Robot();
		} catch (AWTException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		player = new PlayerHelicopter(Framework.frameWidth / 4, Framework.frameHeight / 4);
		enemyHelicopterList = new ArrayList<EnemyHelicopter>();
		explosionList = new ArrayList<Animation>();
		bulletsList = new ArrayList<Bullet>();
		rocketsList = new ArrayList<Rocket>();
		rocketSmokeList = new ArrayList<RocketSmoke>();
		
		cloudLayer1Img = new MovingBackground();
		cloudLayer2Img = new MovingBackground();
		mountainsMoving = new MovingBackground();
		groundMoving = new MovingBackground();
		
		font = new Font("monospaced", Font.BOLD, 18);
		runAwayEnemies = 0;
		destroyedEnemies = 0;
	}
	
	private void LoadContent() {
		try 
        {
            URL skyColorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/sky_color.jpg");
            skyColorImg = ImageIO.read(skyColorImgUrl);
            URL cloudLayer1ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_1.png");
            cloudLayer1Img = ImageIO.read(cloudLayer1ImgUrl);
            URL cloudLayer2ImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/cloud_layer_2.png");
            cloudLayer2Img = ImageIO.read(cloudLayer2ImgUrl);
            URL mountainsImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/mountains.png");
            mountainsImg = ImageIO.read(mountainsImgUrl);
            URL groundImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/ground.png");
            groundImg = ImageIO.read(groundImgUrl);
            
            URL helicopterBodyImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/2_helicopter_body.png");
            EnemyHelicopter.helicopterBodyImg = ImageIO.read(helicopterBodyImgUrl);
            URL helicopterFrontPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/2_front_propeller_anim.png");
            EnemyHelicopter.helicopterFrontPropellerAnimImg = ImageIO.read(helicopterFrontPropellerAnimImgUrl);
            URL helicopterRearPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/2_rear_propeller_anim.png");
            EnemyHelicopter.helicopterRearPropellerAnimImg = ImageIO.read(helicopterRearPropellerAnimImgUrl);
            
            URL rocketImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket.png");
            Rocket.rocketImg = ImageIO.read(rocketImgUrl);
            URL rocketSmokeImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/rocket_smoke.png");
            RocketSmoke.smokeImg = ImageIO.read(rocketSmokeImgUrl);
            
            URL explosionAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/explosion_anim.png");
            explosionAnimImg = ImageIO.read(explosionAnimImgUrl);
            
            URL mouseCursorImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/mouse_cursor.png");
            mouseCursorImg = ImageIO.read(mouseCursorImgUrl);
            
            URL bulletImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/bullet.png");
            Bullet.bulletImg = ImageIO.read(bulletImgUrl);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } 
		
		
	}
	
	
}
