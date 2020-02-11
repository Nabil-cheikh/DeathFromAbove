package Implementation;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;

import IHM.Common;

public class Personnage extends Sprite implements Common {
	private int hitbox_x;
	private int hitbox_y;
	private int hitbox_largeur;
	private int hitbox_hauteur;
	private int dx;
	private int dy;
	private int vitesse_perso = 3;
	private Arme arme1;
	private Porte porte_proche;
	private boolean proximite_porte;
	private Salle salle_actuelle;
	private ImageIcon[] frames;
	private int currentFrame = 0;
	private int compteur = 0;
	
	public Personnage(int x, int y) {
		super(x,y);
		initPersonnage();
		setHitBox();
		frames = new ImageIcon[15];
	}
	
	private void setHitBox() {
		hitbox_x = this.getCenterX() - 25;
		hitbox_y = this.getCenterY() - 25;
		hitbox_largeur = 50;
		hitbox_hauteur =  50;
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
	
	private void initPersonnage() {
		porte_proche = null;
		loadImage("src/images/perso_haut.png");
		getImageDimension();
		arme1 = new Arme();
		salle_actuelle = salle1;
	}
		
	public void move() {
		x += dx;
		y += dy;
		deplacementAnime();
		setHitBox();
		estDansSalle();
		collisionMur();
		collisionPorte();
		porteProche();
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
	
	private void estDansSalle() {
		Rectangle r = this.getBounds();
		for (Salle salle : salles) {
			if (salle.getBounds().contains(r)) {
				salle_actuelle = salle;
				break;
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
	
	private void porteProche() {
		proximite_porte = false;
		for (Porte porte : portes) {
			Rectangle r = this.getBounds();
			if (r.intersects(porte.getX()-porte.getWidth()/2, porte.getY()-porte.getHeight()/2, porte.getWidth(), porte.getHeight())) {
				proximite_porte = true;
				porte_proche = porte;
				break;
			}
		}
	}
	
	public void ouvrir(Porte porte) {
		if (proximite_porte) {
			for (Salle salle : salles) {
				if (salle.getPortes().contains(porte) && !salle.estActive()) {
					salle.setActive();
					dijkstra(salle);
				}
			}
			porte.setFermee(false);
			porte.setVisible(false);
			portes.remove(porte);
			porte_proche = null;
		}
	}
	
	private void dijkstra(Salle salle) {
		switch(salle + "") {
		case "salle 1":
			if (this.getSalleActuelle().getString() == "salle 2") {
				dijkstra[0][1] = new Point(porte_salle12.getHitBox_X(),porte_salle12.getCenterY());
				dijkstra[1][0] = new Point(porte_salle12.getHitBox_X(),porte_salle12.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 5") {
				dijkstra[0][4] = new Point(porte_salle15.getHitBox_X(),porte_salle15.getCenterY());
				dijkstra[4][0] = new Point(porte_salle15.getHitBox_X(),porte_salle15.getCenterY());
			}
			break;
		case "salle 2":
			if (this.getSalleActuelle().getString() == "salle 1") {
				dijkstra[1][0] = new Point(porte_salle12.getHitBox_X(),porte_salle12.getCenterY());
				dijkstra[0][1] = new Point(porte_salle12.getHitBox_X(),porte_salle12.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 4") {
				dijkstra[1][3] = new Point(porte_salle24.getHitBox_X(),porte_salle24.getCenterY());
				dijkstra[3][1] = new Point(porte_salle24.getHitBox_X(),porte_salle24.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 3") {
				dijkstra[1][2] = new Point(porte_salle23.getHitBox_X(),porte_salle23.getCenterY());
				dijkstra[2][1] = new Point(porte_salle23.getHitBox_X(),porte_salle23.getCenterY());
			}
			break;
		case "salle 3":
			if (this.getSalleActuelle().getString() == "salle 2") {
				dijkstra[2][1] = new Point(porte_salle23.getHitBox_X(),porte_salle23.getCenterY());
				dijkstra[1][2] = new Point(porte_salle23.getHitBox_X(),porte_salle23.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 4") {
				dijkstra[3][1] = new Point(porte_salle24.getHitBox_X(),porte_salle24.getCenterY());
				dijkstra[1][3] = new Point(porte_salle24.getHitBox_X(),porte_salle24.getCenterY());
			}
			break;
		case "salle 4":
			if (this.getSalleActuelle().getString() == "salle 2") {
				dijkstra[1][3] = new Point(porte_salle24.getHitBox_X(),porte_salle24.getCenterY());
				dijkstra[3][1] = new Point(porte_salle24.getHitBox_X(),porte_salle24.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 3") {
				dijkstra[2][3] = new Point(porte_salle34.getHitBox_X(),porte_salle34.getCenterY());
				dijkstra[3][2] = new Point(porte_salle34.getHitBox_X(),porte_salle34.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 5") {
				dijkstra[3][4] = new Point(porte_salle45.getHitBox_X(),porte_salle45.getCenterY());
				dijkstra[4][3] = new Point(porte_salle45.getHitBox_X(),porte_salle45.getCenterY());
			}
			break;
		case "salle 5":
			if (this.getSalleActuelle().getString() == "salle 1") {
				dijkstra[0][4] = new Point(porte_salle15.getHitBox_X(),porte_salle15.getCenterY());
				dijkstra[4][0] = new Point(porte_salle15.getHitBox_X(),porte_salle15.getCenterY());
			}
			if (this.getSalleActuelle().getString() == "salle 4") {
				dijkstra[3][4] = new Point(porte_salle45.getHitBox_X(),porte_salle45.getCenterY());
				dijkstra[4][3] = new Point(porte_salle45.getHitBox_X(),porte_salle45.getCenterY());
			}
			break;
		}
	}
	
	public void shoot(double dirr_x, double dirr_y) {
		arme1.shoot(getCenterX(),getCenterY(),dirr_x,dirr_y);
	}
	
	public void mousePressed(MouseEvent e) {
		int cursor = e.getButton();
		Point coordinate_cursor = e.getPoint();
		if (cursor == MouseEvent.BUTTON1) {
			int norme = (int) Math.floor(Math.sqrt(Math.pow(coordinate_cursor.getX() - x, 2) + Math.pow(coordinate_cursor.getY() - y, 2))+0.5);
			int dx = (int) Math.floor(((coordinate_cursor.getX() - x) / norme)+0.5);
			int dy = (int) Math.floor(((coordinate_cursor.getY() - y) / norme)+0.5);
			
			switch(dx +""+ dy) {
			case "01" :
				loadImage("src/Images/Tir/gun_down.png");
				break;
			case "0-1" :
				loadImage("src/Images/Tir/gun_up.png");
				break;
			case "10" :
				loadImage("src/Images/Tir/gun_right.png");
				break;
			case "-10":
				loadImage("src/Images/Tir/gun_left.png");
				break;
			case "-1-1":
				loadImage("src/Images/Tir/gun_up_left.png");
				break;
			case "1-1":
				loadImage("src/Images/Tir/gun_up_right.png");
				break;
			case "-11":
				loadImage("src/Images/Tir/gun_down_left.png");
				break;
			case "11":
				loadImage("src/Images/Tir/gun_down_right.png");
				break;
			}
			shoot(coordinate_cursor.getX(), coordinate_cursor.getY());
		}
	}
	
	
	public void mouseReleased(MouseEvent e) {
		int cursor = e.getButton();
		if (cursor == MouseEvent.BUTTON1) {
			
		}
	}
	
	
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_Q) {
			loadImage("src/images/perso_gauche.png");
			dx = -vitesse_perso;
			frames = deplacement_gauche;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			loadImage("src/images/perso_droite.png");
			dx = vitesse_perso;
			frames = deplacement_droit;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_Z) {
			loadImage("src/images/perso_haut.png");
			dy = -vitesse_perso;
			frames = deplacement_haut;
		}
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			loadImage("src/images/perso_bas.png");
			dy = vitesse_perso;
			frames = deplacement_bas;
		}
		if (key == KeyEvent.VK_SPACE) {ouvrir(porte_proche);};
	}
	
	
	public void KeyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_Q) {
			dx = 0;
			frames = null;
			loadImage("src/images/perso_gauche.png");
			getImageDimension();
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			dx = 0;
			frames = null;
			loadImage("src/images/perso_droite.png");
			getImageDimension();
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_Z) {
			dy = 0;
			frames = null;
			loadImage("src/images/perso_haut.png");
			getImageDimension();
		}
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			dy = 0;
			frames = null;
			loadImage("src/images/perso_bas.png");
			getImageDimension();
		}
	}
	
	public Salle getSalleActuelle() {
		return salle_actuelle;
	}
	
	public Arme getArmePrincipale() {
		return arme1;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(hitbox_x,hitbox_y,hitbox_largeur,hitbox_hauteur);
	}
}
