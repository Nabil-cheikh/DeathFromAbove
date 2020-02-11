package Implementation;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Salle {
	private int id;
	
	private String nom_salle;
	private int pos_x;
	private int pos_y;
	private int largeur;
	private int hauteur;
	private boolean active;
	private List<Porte> portes;
	private List<Mur> murs;
	private List<ZoneSpawn> spawns;
	
	public Salle(String nom_salle, int pos_x, int pos_y, int largeur, int hauteur, boolean active) {
		this.nom_salle = nom_salle;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.active = active;
		portes = new ArrayList<>();
		murs = new ArrayList<>();
		spawns = new ArrayList<>();
		setID();
	}
	
	public void ajouterPorte(Porte porte) {
		portes.add(porte);
	}
	
	public void ajouterMur(Mur mur) {
		murs.add(mur);
	}
	
	public void ajouterSpawn(ZoneSpawn spawn) {
		spawns.add(spawn);
	}
	
	public List<Mur> getMurs() {
		return murs;
	}
	
	public List<Porte> getPortes() {
		return portes;
	}
	
	public List<ZoneSpawn> getSpawns() {
		return spawns;
	}
	
	public int getX() {
		return this.pos_x;
	}
	
	public int getY() {
		return this.pos_y;
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	public void setActive() {
		this.active = true;
	}
	
	public boolean estActive() {
		return active;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(pos_x,pos_y,largeur,hauteur);
	}
	
	public String getString() {
		return nom_salle;
	}
	
	public void setID() {
		switch (this.nom_salle) {
		case "salle 1" : id = 0; break;
		case "salle 2" : id = 1; break;
		case "salle 3" : id = 2; break;
		case "salle 4" : id = 3; break;
		case "salle 5" : id = 4; break;
		}
	}
	
	public int getID() {
		return id;
	}
	
	@Override
	public String toString() {
		return nom_salle;
	}
}
