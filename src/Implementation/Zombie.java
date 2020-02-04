package Implementation;

import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

import IHM.Carte;
import Implementation.Mur;
import Implementation.Porte;
import Implementation.Salle;
import Implementation.Sprite;

public class Zombie extends Sprite {
	private int hitbox_x;
	private int hitbox_y;
	private int hitbox_largeur;
	private int hitbox_hauteur;
	private int dx;
	private int dy;
	private Salle salle_actuelle;
	private ImageIcon[] frames;
	private int currentFrame = 0;
	private int compteur = 0;
	private int vitesse_zombie = 1;
	
	public Zombie(int x, int y) {
		super(x, y);
		initZombie();
		setHitBox();
		frames = new ImageIcon[15];
	}

	private void setHitBox() {
		hitbox_x = this.getCenterX() - 25;
		hitbox_y = this.getCenterY() - 25;
		hitbox_largeur = 50;
		hitbox_hauteur = 50;
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

	private void initZombie() {
		loadImage("src/images/zombie.png");
		getImageDimension();
	}
	
	private void estDansSalle() {
		Rectangle r = this.getBounds();
		for (Salle salle : salles) {
			if (salle.getBounds().contains(r)) {
				salle_actuelle = salle;
				break;
			}
		}
	}
	
	public void move() {
		x += dx;
		y += dy;
		deplacer_zombie();
		deplacementAnime();
		setHitBox();
		estDansSalle();
		collisionMur();
		collisionPorte();
	}

	private void collisionPorte() {
		for (Porte porte : portes) {
			if (hitbox_x > porte.getHitBox_X() - this.getHitBox_Largeur() && hitbox_x < porte.getHitBox_X() + porte.getHitBox_Largeur() &&
					hitbox_y > porte.getHitBox_Y() - this.getHitBox_Hauteur() && hitbox_y < porte.getHitBox_Y() + porte.getHitBox_Hauteur()) {
				if (porte.estVerticale()) {
					if (hitbox_x > porte.getHitBox_X() - this.getHitBox_Largeur() && hitbox_x < porte.getHitBox_X() + porte.getHitBox_Largeur()) {
						if (this.getCenterX() < porte.getHitBox_X()) x = porte.getHitBox_X() - ((this.getWidth()+this.getHitBox_Largeur())/2);
						if (this.getCenterX() > porte.getHitBox_X()) x = porte.getHitBox_X() + porte.getHitBox_Largeur() - ((this.getWidth()-this.getHitBox_Largeur())/2);
					}
				}
				if (porte.estHorizontale()) {
					if (hitbox_y > porte.getHitBox_Y() - this.getHitBox_Hauteur() && hitbox_y < porte.getHitBox_Y() + porte.getHitBox_Hauteur()) {
						if (this.getCenterY() < porte.getHitBox_Y()) y = porte.getHitBox_Y() - ((this.getHeight()+this.getHitBox_Hauteur())/2);
						if (this.getCenterY() > porte.getHitBox_Y()) y = porte.getHitBox_Y() + porte.getHitBox_Hauteur() - ((this.getHeight()-this.getHitBox_Hauteur())/2);
					}
				}
			}
		}
	}

	private void collisionMur() {
		for (Salle salle : salles) {
			List<Mur> murs = salle.getMurs();
			for (Mur mur : murs) {
				if (hitbox_x > mur.getX() - this.getHitBox_Largeur() && hitbox_x < mur.getX() + mur.getLargeur()
				&& hitbox_y > mur.getY() - this.getHitBox_Hauteur() && hitbox_y < mur.getY() + mur.getHauteur()) { //collision
					//à gauche ou à droite ou en haut ou en bas du mur ?
					if (mur.getLargeur() == 10) { //mur vertical
						if (hitbox_y  + this.getHitBox_Hauteur() < mur.getY() || hitbox_y  > mur.getY() + mur.getHauteur()) {
							if (this.getCenterY() < mur.getCenterY()) y = mur.getY() - ((this.getHeight()+this.getHitBox_Hauteur())/2); //en haut
							if (this.getCenterY() > mur.getCenterY()) y = mur.getY() + mur.getHauteur() - ((this.getHeight()-this.getHitBox_Hauteur())/2); //en bas
						}
						else {
							if (this.getCenterX() < mur.getCenterX()) x = mur.getX() - ((this.getWidth()+this.getHitBox_Largeur())/2); //à gauche
							if (this.getCenterX() > mur.getCenterX()) x = mur.getX() + mur.getLargeur() - ((this.getWidth()-this.getHitBox_Largeur())/2); //à droite
						}
					}
					if (mur.getHauteur() == 10) { //mur horizontal
						if (hitbox_x + this.getHitBox_Largeur() < mur.getX() || hitbox_x > mur.getX() + mur.getLargeur()) {
							if (this.getCenterX() < mur.getCenterX()) x = mur.getX() - ((this.getWidth()+this.getHitBox_Largeur())/2); //à gauche
							if (this.getCenterX() > mur.getCenterX()) x = mur.getX() + mur.getLargeur(); //à droite
						}
						else {
							if (this.getCenterY() < mur.getCenterY()) y = mur.getY() - ((this.getHeight()+this.getHitBox_Hauteur())/2); //en haut
							if (this.getCenterY() > mur.getCenterY()) y = mur.getY() + mur.getHauteur() - ((this.getHeight()-this.getHitBox_Hauteur())/2); //en bas
						}
					}
				}
			}
		}
	}

	
	private void deplacementAnime() {
		if (frames != null && frames[0] != null) {
			this.setImage(frames[currentFrame]);
			if (compteur == 4) {
				currentFrame = (currentFrame + 1) % 15;
				compteur = 0;
			}
			compteur++;
		}
	}
	
	private void deplacer_zombie() {
		if (Carte.personnage.getSalleActuelle() == this.getSalleActuelle()) {
			if (Carte.personnage.getCenterX() > this.getCenterX()) {
				dx = vitesse_zombie;
			}
			else if (Carte.personnage.getCenterX() < this.getCenterX()) {
				dx = -vitesse_zombie;
			}
			else {
				dx = 0;
			}
			if (Carte.personnage.getCenterY() > this.getCenterY()) {
				dy = vitesse_zombie;
			}
			else if (Carte.personnage.getCenterY() < this.getCenterY()) {
				dy = -vitesse_zombie;
			}
			else {
				dy = 0;
			}
		}
		else {
			dx = 0; dy = 0;
		}
	}

	public Salle getSalleActuelle() {
		return salle_actuelle;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(hitbox_x,hitbox_y,hitbox_largeur,hitbox_hauteur);
	}
}
