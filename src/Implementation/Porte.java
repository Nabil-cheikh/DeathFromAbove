package Implementation;

import java.awt.Rectangle;

public class Porte extends Sprite {
	private int hitbox_x;
	private int hitbox_y;
	private int hitbox_largeur;
	private int hitbox_hauteur;
	private boolean horizontale;
	private boolean fermee;

	/**
	 * @param x position de la porte sur l'axe des abscisses
	 * @param y position de la porte sur l'axe des ordonnées
	 * @param horizontale gestion de la hitbox de la porte, pour savoir si la porte est horizontale ou verticale, true si la porte est horizontale, false sinon*/
	public Porte(int x, int y, boolean horizontale) {
		super(x,y);
		this.horizontale = horizontale;
		initPorte();
		setHitBox();
		this.setFermee(true);
	}
	
	private void setHitBox() {
		if (horizontale) {
			hitbox_x = this.getX() - this.getWidth()/2;
			hitbox_y = this.getCenterY()-5 - this.getHeight()/2;
			hitbox_largeur = this.getWidth();
			hitbox_hauteur = 10;
		}
		else {
			hitbox_x = this.getCenterX()-5 - this.getWidth()/2;
			hitbox_y = this.getY() - this.getHeight()/2;
			hitbox_largeur = 10;
			hitbox_hauteur = this.getHeight();
		}
	}
	
	public boolean estHorizontale() {
		return horizontale;
	}
	
	public boolean estVerticale() {
		return !horizontale;
	}
	
	private void initPorte() {
		loadImage("src/images/porte.png");
		getImageDimension();
	}

	public boolean isFermee() {
		return fermee;
	}

	public void setFermee(boolean fermee) {
		this.fermee = fermee;
	}
	
	public int getHitBox_X() {
		return hitbox_x;
	}
	
	public int getHitBox_Y() {
		return hitbox_y;
	}
	
	public int getHitBox_Largeur() {
		return hitbox_largeur;
	}
	
	public int getHitBox_Hauteur() {
		return hitbox_hauteur;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(hitbox_x,hitbox_y,hitbox_largeur,hitbox_hauteur);
	}
}
