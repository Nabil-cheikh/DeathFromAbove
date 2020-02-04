package Implementation;

import java.awt.Image;
import java.awt.Rectangle;
import IHM.Common;
import javax.swing.ImageIcon;

public class Sprite implements Common {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	protected void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}

	protected void getImageDimension() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getCenterX() {
		return getX() + getWidth()/2;
	}
	
	public int getCenterY() {
		return getY() + getHeight()/2;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public Image getImage() {
		return image;
	}
	
	public void setImage(ImageIcon image) {
		this.image = image.getImage();
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
}
