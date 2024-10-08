package Implementation;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
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
		
	public void move() {
		x += dx;
		y += dy;
		estDansSalle();
		deplacer_zombie();
		deplacementAnime();
		setHitBox();
		gestionCollisions();
	}
	
	private void gestionCollisions() {
		collisionMur();
		collisionPorte();
		collisionZombie();
	}

	private void estDansSalle() {
		Rectangle r = this.getBounds();
		for (Salle salle : salles) {
			if (salle.getBounds().contains(r) || salle.getBounds().intersects(r)) {
				salle_actuelle = salle;
				break;
			}
		}
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
					//� gauche ou � droite ou en haut ou en bas du mur ?
					if (mur.getLargeur() == 10) { //mur vertical
						if (hitbox_y  + this.getHitBox_Hauteur() < mur.getY() || hitbox_y  > mur.getY() + mur.getHauteur()) {
							if (this.getCenterY() < mur.getCenterY()) y = mur.getY() - ((this.getHeight()+this.getHitBox_Hauteur())/2); //en haut
							if (this.getCenterY() > mur.getCenterY()) y = mur.getY() + mur.getHauteur() - ((this.getHeight()-this.getHitBox_Hauteur())/2); //en bas
						}
						else {
							if (this.getCenterX() < mur.getCenterX()) x = mur.getX() - ((this.getWidth()+this.getHitBox_Largeur())/2); //� gauche
							if (this.getCenterX() > mur.getCenterX()) x = mur.getX() + mur.getLargeur() - ((this.getWidth()-this.getHitBox_Largeur())/2); //� droite
						}
					}
					if (mur.getHauteur() == 10) { //mur horizontal
						if (hitbox_x + this.getHitBox_Largeur() < mur.getX() || hitbox_x > mur.getX() + mur.getLargeur()) {
							if (this.getCenterX() < mur.getCenterX()) x = mur.getX() - ((this.getWidth()+this.getHitBox_Largeur())/2); //� gauche
							if (this.getCenterX() > mur.getCenterX()) x = mur.getX() + mur.getLargeur(); //� droite
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
	
	private void collisionZombie() {
		for (Zombie zombie : zombies) {
			if (this != zombie) 
			if (hitbox_x > zombie.getHitBox_X() - this.getHitBox_Largeur() && hitbox_x < zombie.getHitBox_X() + zombie.getHitBox_Largeur() &&
					hitbox_y > zombie.getHitBox_Y() - this.getHitBox_Hauteur() && hitbox_y < zombie.getHitBox_Y() + zombie.getHitBox_Hauteur()) {
				Rectangle2D.intersect(this.getBounds(), zombie.getBounds(), intersection);
				if (intersection.width>intersection.height) { //collision verticale
					if (this.getCenterY() < zombie.getCenterY()) y = zombie.getHitBox_Y() - ((this.getHeight()+this.getHitBox_Hauteur())/2);
					if (this.getCenterY() > zombie.getCenterY()) y = zombie.getHitBox_Y() + zombie.getHitBox_Hauteur() - ((this.getHeight()-this.getHitBox_Hauteur())/2);
					dy = 0;
				}
				else if (intersection.width == intersection.height) {
					y = y - intersection.height * (int)Math.signum(dy);
					x = x - intersection.width * (int)Math.signum(dx);
					dx = dy = 0;
				}
				else { //collision horizontale
					if (this.getCenterX() < zombie.getCenterX()) x = zombie.getHitBox_X() - ((this.getWidth()+this.getHitBox_Largeur())/2);
					if (this.getCenterX() > zombie.getCenterX()) x = zombie.getHitBox_X() + zombie.getHitBox_Largeur() - ((this.getWidth()-this.getHitBox_Largeur())/2);
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
	
	private void deplacer_zombie_personnage() {
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
	
	private void deplacer_zombie_porte() {
		if (this.getSalleActuelle().estActive()) {
			//X
			if (dijkstra[this.getSalleActuelle().getID()][Carte.personnage.getSalleActuelle().getID()].getX() > this.getCenterX()) {
				dx = vitesse_zombie;
			}
			else if (dijkstra[this.getSalleActuelle().getID()][Carte.personnage.getSalleActuelle().getID()].getX() < this.getCenterX()) {
				dx = -vitesse_zombie;
			}
			else {
				deplacer_zombie_personnage();
			}
			//Y
			if (dijkstra[this.getSalleActuelle().getID()][Carte.personnage.getSalleActuelle().getID()].getY() > this.getCenterY()) {
				dy = vitesse_zombie;
			}
			else if (dijkstra[this.getSalleActuelle().getID()][Carte.personnage.getSalleActuelle().getID()].getY() < this.getCenterY()) {
				dy = -vitesse_zombie;
			}
			else {
				deplacer_zombie_personnage();
			}
		}
	}
	
	private void deplacer_zombie() {
		if (Carte.personnage.getSalleActuelle() == this.getSalleActuelle()) {
			deplacer_zombie_personnage();
		}
		else {
			if (this.getSalleActuelle().estActive()) {
				deplacer_zombie_porte();
			}
			else {
				dx = 0; dy = 0;
			}
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
