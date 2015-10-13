
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Player extends Entity {
	
	private static BufferedImage sprite;
	
	/**
	 * Creates a new <code>Player</code>
	 * @param x The x-coordinate of the <code>Player</code>
	 * @param y The y-coordinate of the <code>Player</code>
	 */
	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
				
	}
	
	/**
	 * Sets the image of the <code>Player</code>
	 * @param image The <code>BufferedImage</code> with which to set the <code>Player</code> sprite to
	 */
	public static void setImage(BufferedImage image) {
		
		sprite = image;
		
	}
	
	/**
	 * Gets the image of the <code>Player</code>
	 * @return The sprite of the <code>Player</code> as a <code>BufferedImage</code>
	 */
	public static BufferedImage getImage() {
		
		return sprite;
		
	}
	
	/**
	 * Draws the <code>Player</code>
	 */
	@Override
	public void draw(int xOffset, int yOffset, int width, int height, Graphics g) {
		
		int xLeft = xOffset + 1 + (this.x * (width + 1));
		int yTop = yOffset + 1 + (this.y * (height + 1));
		
		g.drawImage(sprite, xLeft, yTop, width, height, null);
		
	}
	
	/**
	 * Moves the player
	 * @param x The new x-coordinate to move the player to
	 * @param y The new y-coordinate to move the player to
	 * @param jump Whether or not the player jumped
	 * @return Whether or not the player lost (moved onto a Mho/Fence)
	 */
	public boolean move(int x, int y, boolean jump) {
		
		String message = "";
		boolean lost = false;

		move(x, y);
		Main.display.repaint();
		
		if (Main.display.occupiedByFence(x, y) || Main.display.occupiedByMho(x, y)) {

			lost = true;
			
			message = Main.display.occupiedByFence(x, y) ? "You have moved onto a Fence! " : (jump ? "You have jumped onto a Mho! " : "You have moved onto a Mho! ");

			Main.display.gameOver(false, message, (Main.display.occupiedByFence(x, y) ? Grid.fenceIcon : Grid.mhoIcon));

		}
		
		return lost;

	}

	/**
	 * Moves the player, then moves the Mhos
	 * @param x The new x-coordinate to move the Player to
	 * @param y The new y-coordinate to move the Player to
	 */
	public void act(int x, int y) {

		if(!move(x, y, false)) {

			Main.display.moveMhos();

		}

	}

}
