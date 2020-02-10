package IHM;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Implementation.Mur;
import Implementation.Porte;
import Implementation.Salle;
import Implementation.Zombie;
import Implementation.ZoneSpawn;

public interface Common {
	public static Rectangle intersection = new Rectangle();
	//Dimensions écran
	public static final Toolkit tk = Toolkit.getDefaultToolkit();
	public static final Dimension dim = tk.getScreenSize();
	
	//Chargement d'images
	public static final ImageIcon[] deplacement_droit = {
			new ImageIcon("src/Images/DeplacementDroit/walk0.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk1.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk2.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk3.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk4.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk5.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk6.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk7.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk8.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk9.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk10.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk11.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk12.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk13.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk14.png"),
			new ImageIcon("src/Images/DeplacementDroit/walk15.png")
	};
	public static final ImageIcon[] deplacement_gauche = {
			new ImageIcon("src/Images/DeplacementGauche/walk0.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk1.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk2.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk3.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk4.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk5.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk6.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk7.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk8.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk9.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk10.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk11.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk12.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk13.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk14.png"),
			new ImageIcon("src/Images/DeplacementGauche/walk15.png")
	};
	public static final ImageIcon[] deplacement_haut = {
			new ImageIcon("src/Images/DeplacementHaut/walk0.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk1.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk2.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk3.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk4.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk5.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk6.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk7.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk8.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk9.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk10.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk11.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk12.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk13.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk14.png"),
			new ImageIcon("src/Images/DeplacementHaut/walk15.png")
	};
	public static final ImageIcon[] deplacement_bas = {
			new ImageIcon("src/Images/DeplacementBas/walk0.png"),
			new ImageIcon("src/Images/DeplacementBas/walk1.png"),
			new ImageIcon("src/Images/DeplacementBas/walk2.png"),
			new ImageIcon("src/Images/DeplacementBas/walk3.png"),
			new ImageIcon("src/Images/DeplacementBas/walk4.png"),
			new ImageIcon("src/Images/DeplacementBas/walk5.png"),
			new ImageIcon("src/Images/DeplacementBas/walk6.png"),
			new ImageIcon("src/Images/DeplacementBas/walk7.png"),
			new ImageIcon("src/Images/DeplacementBas/walk8.png"),
			new ImageIcon("src/Images/DeplacementBas/walk9.png"),
			new ImageIcon("src/Images/DeplacementBas/walk10.png"),
			new ImageIcon("src/Images/DeplacementBas/walk11.png"),
			new ImageIcon("src/Images/DeplacementBas/walk12.png"),
			new ImageIcon("src/Images/DeplacementBas/walk13.png"),
			new ImageIcon("src/Images/DeplacementBas/walk14.png"),
			new ImageIcon("src/Images/DeplacementBas/walk15.png")
	};
	
	//Temps
	public static final int DELAY = 10;
	
	//------------------
	//Positions salles
	//------------------
	//salle 1:
	public static final int POSITION_SALLE1_X = 0;
	public static final int POSITION_SALLE1_Y = 0;
	//salle 2:
	public static final int POSITION_SALLE2_X = dim.width/3;
	public static final int POSITION_SALLE2_Y = 0;
	//salle 3
	public static final int POSITION_SALLE3_X = 5*dim.width/6;
	public static final int POSITION_SALLE3_Y = 0;
	//salle 4
	public static final int POSITION_SALLE4_X = dim.width/4;
	public static final int POSITION_SALLE4_Y = dim.height/2;
	//salle 5;
	public static final int POSITION_SALLE5_X = 0;
	public static final int POSITION_SALLE5_Y = dim.height/2;
	
	//-------------------
	//Dimensions salles
	//-------------------
	//salle 1:
	public static final int LARGEUR_SALLE1 = dim.width/3;
	public static final int HAUTEUR_SALLE1 = dim.height/2;
	//salle 2:
	public static final int LARGEUR_SALLE2 = dim.width/2;
	public static final int HAUTEUR_SALLE2 = dim.height/2;
	//salle 3:
	public static final int LARGEUR_SALLE3 = dim.width/6;
	public static final int HAUTEUR_SALLE3 = dim.height;
	//salle 4:
	public static final int LARGEUR_SALLE4 = 7*dim.width/12;
	public static final int HAUTEUR_SALLE4 = dim.height/4;
	//salle 5:
	public static final int LARGEUR_SALLE5 = 5*dim.width/6;
	public static final int HAUTEUR_SALLE5 = dim.height/2;
	
	//-----------------
	//Positions portes
	//-----------------
	//porte 1-2:
	public static final int PORTE_1_2_X = dim.width/3;
	public static final int PORTE_1_2_Y = dim.height/4;
	//porte 1-5:
	public static final int PORTE_1_5_X = dim.width/8;
	public static final int PORTE_1_5_Y = dim.height/2;
	//porte 2-3:
	public static final int PORTE_2_3_X = 5*dim.width/6;
	public static final int PORTE_2_3_Y = dim.height/4;
	//porte 2-4:
	public static final int PORTE_2_4_X = 7*dim.width/12;
	public static final int PORTE_2_4_Y = dim.height/2;
	//porte 3-4:
	public static final int PORTE_3_4_X = 5*dim.width/6;
	public static final int PORTE_3_4_Y = 5*dim.height/8;
	//porte 3-5:
	public static final int PORTE_3_5_X = 5*dim.width/6;
	public static final int PORTE_3_5_Y = 7*dim.width/8;
	//porte 4-5:
	public static final int PORTE_4_5_X = 7*dim.width/12;
	public static final int PORTE_4_5_Y = 3*dim.height/4;
	
	
	//Implémentations salles, murs et portes
	public static final Salle salle1 = new Salle(POSITION_SALLE1_X,POSITION_SALLE1_Y,LARGEUR_SALLE1,HAUTEUR_SALLE1);
	public static final Salle salle2 = new Salle(POSITION_SALLE2_X,POSITION_SALLE2_Y,LARGEUR_SALLE2,HAUTEUR_SALLE2);
	public static final Salle salle3 = new Salle(POSITION_SALLE3_X,POSITION_SALLE3_Y,LARGEUR_SALLE3,HAUTEUR_SALLE3);
	public static final Salle salle4 = new Salle(POSITION_SALLE4_X,POSITION_SALLE4_Y,LARGEUR_SALLE4,HAUTEUR_SALLE4);
	public static final Salle salle5 = new Salle(POSITION_SALLE5_X,POSITION_SALLE5_Y,LARGEUR_SALLE5,HAUTEUR_SALLE5);
	
	public static final Porte porte_salle12 = new Porte(PORTE_1_2_X,PORTE_1_2_Y,false);
	public static final Porte porte_salle15 = new Porte(PORTE_1_5_X,PORTE_1_5_Y,true);
	public static final Porte porte_salle24 = new Porte(PORTE_2_4_X,PORTE_2_4_Y,true);
	public static final Porte porte_salle23 = new Porte(PORTE_2_3_X,PORTE_2_3_Y,false);
	public static final Porte porte_salle34 = new Porte(PORTE_3_4_X,PORTE_3_4_Y,false);
	public static final Porte porte_salle45 = new Porte(PORTE_4_5_X,PORTE_4_5_Y,true);
	
	public static final Mur mur1_salle1 = new Mur(salle1.getX(),salle1.getY(),10,salle1.getHauteur());
	public static final Mur mur2_salle1 = new Mur(salle1.getX(),salle1.getY(),salle1.getLargeur(),10);
	public static final Mur mur31_salle1 = new Mur(salle1.getLargeur(),salle1.getY(),10,porte_salle12.getY()-porte_salle12.getHeight()/2);
	public static final Mur mur32_salle1 = new Mur(salle1.getLargeur(),porte_salle12.getCenterY(),10,salle1.getHauteur()-porte_salle12.getCenterY());
	public static final Mur mur41_salle1 = new Mur(salle1.getX(),salle1.getHauteur(),porte_salle15.getX()-porte_salle15.getWidth()/2,10);
	public static final Mur mur42_salle1 = new Mur(porte_salle15.getCenterX(),salle1.getHauteur(),salle1.getLargeur()-porte_salle15.getCenterX(),10);
	
	public static final Mur mur5_salle2 = new Mur(salle2.getX(),salle2.getY(),salle2.getLargeur(),10);
	public static final Mur mur61_salle2= new Mur(salle2.getX()+salle2.getLargeur(),salle2.getY(),10,porte_salle23.getY()-porte_salle23.getHeight()/2);
	public static final Mur mur62_salle2 = new Mur(salle2.getX()+salle2.getLargeur(),porte_salle23.getCenterY(),10,salle2.getHauteur()-porte_salle23.getCenterY());
	public static final Mur mur71_salle2 = new Mur(salle2.getX(),salle2.getHauteur(),porte_salle24.getX()-porte_salle24.getWidth()/2 - salle2.getX(),10);
	public static final Mur mur72_salle2 = new Mur(porte_salle24.getCenterX(),salle2.getHauteur(),salle2.getX() + salle2.getLargeur()-porte_salle24.getCenterX(),10);
	
	public static final Mur mur1_salle3 = new Mur(salle3.getX(),salle3.getY(),salle3.getLargeur(),10);
	public static final Mur mur21_salle3 = new Mur(salle3.getX(),salle2.getY()+salle2.getHauteur(),10,porte_salle34.getY()-porte_salle34.getHeight()/2-(salle2.getY()+salle2.getHauteur()));
	public static final Mur mur22_salle3 = new Mur(salle3.getX(),porte_salle34.getCenterY(),10,dim.height-porte_salle34.getCenterY());
	public static final Mur mur3_salle3 = new Mur(salle3.getX()+salle3.getLargeur()-10,salle3.getY(),10,dim.height);
	public static final Mur mur4_salle3 = new Mur(salle3.getX(),dim.height-35,salle3.getLargeur(),10);
	
	public static final Mur mur1_salle4 = new Mur(salle4.getX(),salle4.getY(),10,salle4.getHauteur());
	public static final Mur mur21_salle4 = new Mur(salle4.getX()+10,salle4.getY()+salle4.getHauteur(),(porte_salle45.getX()-porte_salle45.getWidth()/2)-salle4.getX(),10);
	public static final Mur mur22_salle4 = new Mur(porte_salle45.getCenterX(),salle4.getY()+salle4.getHauteur(),salle5.getLargeur()-porte_salle45.getCenterX(),10);
	
	public static final Mur mur1_salle5 = new Mur(salle5.getX(),salle5.getY(),10,salle5.getHauteur());
	public static final Mur mur2_salle5 = new Mur(salle5.getX(), salle5.getY()+salle5.getHauteur()-35,salle5.getLargeur(),10);
	
	//Implémentation zones spawn
	public static final ZoneSpawn salle1_spawn1 = new ZoneSpawn(salle1.getX()+40, salle1.getY()+40);
	public static final ZoneSpawn salle1_spawn2 = new ZoneSpawn(salle1.getX()+salle1.getLargeur()-80,salle1.getY()+salle1.getHauteur()-80);
	
	public static final ZoneSpawn salle2_spawn1 = new ZoneSpawn(salle2.getX()+40, salle2.getY()+40);
	public static final ZoneSpawn salle2_spawn2 = new ZoneSpawn(salle2.getX()+40, salle2.getY()+salle2.getHauteur()-80);
	public static final ZoneSpawn salle2_spawn3 = new ZoneSpawn(salle2.getX()+salle2.getLargeur()-80,salle2.getY()+40);
	public static final ZoneSpawn salle2_spawn4 = new ZoneSpawn(salle2.getX()+salle2.getLargeur()-80,salle2.getY()+salle2.getHauteur()-80);
	
	public static final ZoneSpawn salle3_spawn1 = new ZoneSpawn(salle3.getX()+salle3.getLargeur()-80,salle3.getY()+40);
	public static final ZoneSpawn salle3_spawn2 = new ZoneSpawn(salle3.getX()+salle3.getLargeur()-80,salle3.getY()+salle3.getHauteur()-80);
	
	public static final ZoneSpawn salle4_spawn1 = new ZoneSpawn(salle4.getX()+40,salle4.getY()+40);
	public static final ZoneSpawn salle4_spawn2 = new ZoneSpawn(salle4.getX()+40,salle4.getY()+salle4.getHauteur()-80);
	
	public static final ZoneSpawn salle5_spawn1 = new ZoneSpawn(salle5.getX()+40,salle5.getY()+40);
	public static final ZoneSpawn salle5_spawn2 = new ZoneSpawn(salle4.getX()-80,salle5.getY()+40);
	public static final ZoneSpawn salle5_spawn3 = new ZoneSpawn(salle5.getX()+salle5.getLargeur()-80,salle5.getY()+salle5.getHauteur()-80);
	
	//Nombre salles : 
	public static List<Salle> salles = new ArrayList<>();
	public static List<Porte> portes = new ArrayList<>();
	public static List<Mur> murs = new ArrayList<>();
	public static List<Zombie> zombies = new ArrayList<>();
	
}