package hu.naszi.game.helicopterbattle;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Framework extends Canvas {
	public static int frameWidth; // A keret szélessége
	public static int frameHeight; // A keret magassága

	// 1 secund = 1 000 000 000 nanosecunds
	public static final long SEC_IN_NANOSEC = 1000000000L;

	// 1 milisecund = 1 000 000 nanosecunds
	public static final long MILISEC_IN_NANOSEC = 1000000L;

	// Frames per secund - FPS
	private final int GAME_FPS = 60;

	// Két update közötti szünet
	private final long GAME_UPDATE_PERIOD = SEC_IN_NANOSEC / GAME_FPS;

	public static enum GameState {
		STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, DESTROYED
	}

	public static GameState gameState;

	private long gameTime;
	private long lastTime;

	private Game game;

	private Font font;

	// A menü képei
	private BufferedImage gameTitleImg;
	private BufferedImage menuBorderImg;
	private BufferedImage skyColorImg;
	private BufferedImage cloudLayer1Img;
	private BufferedImage cloudLayer2Img;

	public Framework() {
		super();
		gameState = GameState.VISUALIZING;

		Thread gameThread = new Thread() {

			@Override
			public void run() {
				GameLoop();
			}
		};

		gameThread.start();
	}

	private void initialize() {
		font = new Font("monospace", Font.BOLD, 28);
	}

	private void loadContent() {
		try {
			URL menuBorderImgUrl = this.getClass().getResource(
					"/hu.naszi.game.helicopterbattle/resources/images/menu_border.png");
			menuBorderImg = ImageIO.read(menuBorderImgUrl);
			
			URL skyColorImgUrl = this.getClass().getResource(
					"/hu.naszi.game.helicopterbattle/resources/images/sky_color.png");
			skyColorImg = ImageIO.read(skyColorImgUrl);
			
			URL gameTitleImgUrl = this.getClass().getResource(
					"/hu.naszi.game.helicopterbattle/resources/images/helicopter_battle_title.png");
			gameTitleImg = ImageIO.read(gameTitleImgUrl);
			
			URL cloudLayer1ImgUrl = this.getClass().getResource(
					"/hu.naszi.game.helicopterbattle/resources/images/cloud_layer_1.png");
			cloudLayer1Img = ImageIO.read(cloudLayer1ImgUrl);
			
			URL cloudLayer2ImgUrl = this.getClass().getResource(
					"/hu.naszi.game.helicopterbattle/resources/images/cloud_layer_2.png");
			cloudLayer2Img = ImageIO.read(cloudLayer2ImgUrl);
		} catch (IOException e) {
			Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
