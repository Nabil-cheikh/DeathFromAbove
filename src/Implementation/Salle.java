package Implementation;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Salle {
	private int pos_x;
	private int pos_y;
	private int largeur;
	private int hauteur;
	private List<Porte> portes;
	private List<Mur> murs;
	private List<ZoneSpawn> spawns;
	
	public Salle(int pos_x, int pos_y, int largeur, int hauteur) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		portes = new ArrayList<>();
		murs = new ArrayList<>();
		spawns = new ArrayList<>();
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
	
	public Rectangle getBounds() {
		return new Rectangle(pos_x,pos_y,largeur,hauteur);
	}
}
