package Implementation;

import IHM.Common;

public class ZoneSpawn implements Common {
	private int x;
	private int y;
	
	public ZoneSpawn(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void invoquer() {
		zombies.add(new Zombie(x,y));
	}
	
	
}
