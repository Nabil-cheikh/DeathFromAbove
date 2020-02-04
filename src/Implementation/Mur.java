package Implementation;

import java.awt.Rectangle;

public class Mur extends Sprite {
	private int largeur;
	private int hauteur;
	
	public Mur(int x, int y, int largeur, int hauteur) {
		super(x, y);
		this.largeur = largeur;
		this.hauteur = hauteur;
		initMur();
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	private void initMur() {
		loadImage("src/images/mur.png");
		getImageDimension();
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,largeur,hauteur);
	}
}
