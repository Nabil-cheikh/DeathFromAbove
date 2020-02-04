package Implementation;

import java.util.ArrayList;
import java.util.List;

public class Arme {
	private List<Balle> balles;
	
	public Arme() {
		balles = new ArrayList<Balle>();
	}
	
	public void shoot(int x, int y, double dirr_x, double dirr_y) {
		Balle balle = new Balle(x, y, dirr_x, dirr_y,10);
		balles.add(balle);
	}
	
	public List<Balle> getBalles() {
		return balles;
	}
}
