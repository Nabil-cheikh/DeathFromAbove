package Implementation;

import IHM.Common;

public class Balle extends Sprite implements Common {
	private double dx;
	private double dy;
	private double norme;
	private double vitesse_balle;
	
	public Balle(int x, int y, double dirr_x, double dirr_y, double vitesse_balle) {
		super(x, y);
		norme = Math.floor(Math.sqrt(Math.pow(dirr_x - x, 2) + Math.pow(dirr_y - y, 2))+0.5);
		dx = Math.floor(((dirr_x - x) / norme)+0.5);
		dy = Math.floor(((dirr_y - y) / norme)+0.5);
		this.vitesse_balle = vitesse_balle;
		initBalle();
	}
	
	private void initBalle() {
		loadImage("src/images/missile_rouge.png");
		getImageDimension();
	}

	public void shoot() {
		x += getDx()*vitesse_balle;
		y += getDy()*vitesse_balle;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}	
	
}
