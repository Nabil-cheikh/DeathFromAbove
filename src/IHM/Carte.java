package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

import Implementation.Balle;
import Implementation.Mur;
import Implementation.Personnage;
import Implementation.Porte;
import Implementation.Salle;
import Implementation.Zombie;
import Implementation.ZoneSpawn;

@SuppressWarnings("serial")
public class Carte extends JPanel implements ActionListener, Common {
	public static Personnage personnage;
	public boolean ingame;
	private Timer timer;
	
	public Carte () {
		initialiserCarte();
	}
	
	public void initialiserCarte() {
		addKeyListener(new TAdapter());
		addMouseListener(new MAdapter());
		setPreferredSize(new Dimension(dim));
		setFocusable(true);
		ingame = true;
		
		personnage = new Personnage(175,90);
		salles.add(salle1); salles.add(salle2); salles.add(salle3); salles.add(salle4); salles.add(salle5);
		portes.add(porte_salle12); portes.add(porte_salle15); portes.add(porte_salle23); portes.add(porte_salle24);
		portes.add(porte_salle34); portes.add(porte_salle45);
		murs.add(mur1_salle1); murs.add(mur1_salle3); murs.add(mur1_salle4); murs.add(mur1_salle5); murs.add(mur21_salle3); murs.add(mur21_salle4);
		murs.add(mur22_salle3); murs.add(mur22_salle4); murs.add(mur2_salle1); murs.add(mur2_salle5); murs.add(mur31_salle1); murs.add(mur32_salle1);
		murs.add(mur3_salle3); murs.add(mur41_salle1); murs.add(mur42_salle1); murs.add(mur4_salle3); murs.add(mur5_salle2); murs.add(mur61_salle2);
		murs.add(mur62_salle2); murs.add(mur71_salle2); murs.add(mur72_salle2);
		
		salle1.ajouterPorte(porte_salle12);
		salle1.ajouterPorte(porte_salle15);
		salle2.ajouterPorte(porte_salle12);
		salle2.ajouterPorte(porte_salle23);
		salle2.ajouterPorte(porte_salle24);
		salle3.ajouterPorte(porte_salle23);
		salle3.ajouterPorte(porte_salle34);
		salle4.ajouterPorte(porte_salle24);
		salle4.ajouterPorte(porte_salle34);
		salle4.ajouterPorte(porte_salle45);
		salle5.ajouterPorte(porte_salle15);
		salle5.ajouterPorte(porte_salle45);
		
		salle1.ajouterMur(mur1_salle1);
		salle1.ajouterMur(mur2_salle1);
		salle1.ajouterMur(mur31_salle1);
		salle1.ajouterMur(mur32_salle1);
		salle1.ajouterMur(mur41_salle1);
		salle1.ajouterMur(mur42_salle1);
		
		salle2.ajouterMur(mur5_salle2);
		salle2.ajouterMur(mur61_salle2);
		salle2.ajouterMur(mur62_salle2);
		salle2.ajouterMur(mur71_salle2);
		salle2.ajouterMur(mur72_salle2);
		
		salle3.ajouterMur(mur1_salle3);
		salle3.ajouterMur(mur21_salle3);
		salle3.ajouterMur(mur22_salle3);
		salle3.ajouterMur(mur3_salle3);
		salle3.ajouterMur(mur4_salle3);
		
		salle4.ajouterMur(mur1_salle4);
		salle4.ajouterMur(mur21_salle4);
		salle4.ajouterMur(mur22_salle4);
		
		salle5.ajouterMur(mur1_salle5);
		salle5.ajouterMur(mur2_salle5);
		
		salle1.ajouterSpawn(salle1_spawn1);
		salle1.ajouterSpawn(salle1_spawn2);
		salle2.ajouterSpawn(salle2_spawn1);
		salle2.ajouterSpawn(salle2_spawn2);
		salle2.ajouterSpawn(salle2_spawn3);
		salle2.ajouterSpawn(salle2_spawn4);
		salle3.ajouterSpawn(salle3_spawn1);
		salle3.ajouterSpawn(salle3_spawn2);
		salle4.ajouterSpawn(salle4_spawn1);
		salle4.ajouterSpawn(salle4_spawn2);
		salle5.ajouterSpawn(salle5_spawn1);
		salle5.ajouterSpawn(salle5_spawn2);
		salle5.ajouterSpawn(salle5_spawn3);
		
		for (Salle salle : salles) {
			for (ZoneSpawn spawn : salle.getSpawns()) {
				spawn.invoquer();
			}
		}
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (ingame) {
			dessiner_carte(g);
			dessiner_personnage(g);
			dessiner_zombie(g);
			dessiner_balle(g);
		}
		else {
			dessiner_menu_fin(g);
		}
	}
	
	private void dessiner_menu_fin(Graphics g) {
		//Graphics2D g2 = (Graphics2D)g;
		String msg = "GAME OVER";
        Font small = new Font("Helvetica", Font.BOLD, 100);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, dim.width/2 - 300, dim.height/2);
	}

	private void dessiner_carte(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for (Salle salle : salles) {
			for (Mur mur : salle.getMurs()) {
				g2.drawImage(mur.getImage(), mur.getX(), mur.getY(), mur.getLargeur(), mur.getHauteur(), this);
			}
		}	
		for (Porte porte : portes) {
			if (porte.isVisible()) {
				g2.drawImage(porte.getImage(), porte.getX()-porte.getWidth()/2, porte.getY()-porte.getHeight()/2, porte.getWidth(), porte.getHeight(), this);
			}
		}
	}

	private void dessiner_personnage(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (personnage.isVisible()) {
			g2.drawImage(personnage.getImage(),personnage.getX(), personnage.getY(),personnage.getWidth(),personnage.getHeight(), this);
			Rectangle2D r = new Rectangle2D.Double(personnage.getHitBox_X(),personnage.getHitBox_Y(),personnage.getHitBox_Largeur(),personnage.getHitBox_Hauteur());
			g2.draw(r);
		}
	}
	
	private void dessiner_zombie(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for (Zombie zombie : zombies) {
			if (zombie.isVisible()) {
				g2.drawImage(zombie.getImage(), zombie.getX(), zombie.getY(), zombie.getWidth(), zombie.getHeight(),this);
				Rectangle2D r = new Rectangle2D.Double(zombie.getHitBox_X(),zombie.getHitBox_Y(),zombie.getHitBox_Largeur(),zombie.getHitBox_Hauteur());
				g2.draw(r);
			}
		}
	}
	
	private void dessiner_balle(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		List<Balle> balles = (personnage.getArmePrincipale()).getBalles();
		
		for (Balle balle : balles) {
			if (balle.isVisible()) {
				g2.drawImage(balle.getImage(), balle.getX(), balle.getY(), balle.getWidth(),balle.getHeight(),this);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		inGame();
		
		updatePersonnage();
		updateZombie();
		updateBalles();
		checkCollisionsMurs();
		checkCollisionPortes();
		checkCollisionZombies();
		
		repaint();
	}

	private void inGame() {
		if (!ingame) {
			timer.stop();
		}
	}

	private void updatePersonnage() {
		if (personnage.isVisible()) personnage.move();
	}
	
	private void updateZombie() {
		for (int i = 0; i < zombies.size(); i++) {
			Zombie zombie = zombies.get(i);
			if (zombie.isVisible()) {
				zombie.move();
			} else {
				zombies.remove(i);
			}
		}
	}
	private void updateBalles() {
		List<Balle> balles = (personnage.getArmePrincipale()).getBalles();
		
		for (int i = 0; i < balles.size(); i++) {
			Balle balle = balles.get(i);
			if (balle.isVisible()) {
				balle.shoot();
			} else {
				balles.remove(i);
			}
		}
	}
	
	private void checkCollisionsMurs() {
		List<Balle> balles = (personnage.getArmePrincipale()).getBalles();
		
		for (Balle balle : balles) {
			Rectangle rb = balle.getBounds();
			for (Mur mur : murs) {
				if (mur.getBounds().intersects(rb)) {
					balle.setVisible(false);
				}
			}
		}
	}
	
	private void checkCollisionPortes() {
		List<Balle> balles = (personnage.getArmePrincipale()).getBalles();
		
		for (Balle balle : balles) {
			Rectangle rb = balle.getBounds();
			for (Porte porte : portes) {
				if (porte.getBounds().intersects(rb)) {
					balle.setVisible(false);
				}
			}
		}
	}
	
	private void checkCollisionZombies() {
		Rectangle r3 = personnage.getBounds();

        for (Zombie zombie : zombies) {
            Rectangle r2 = zombie.getBounds();

            if (r3.intersects(r2)) {
                personnage.setVisible(false);
                ingame = false;
            }
        }
        
		List<Balle> balles = (personnage.getArmePrincipale()).getBalles();
		
		for (Balle balle : balles) {
			Rectangle rb = balle.getBounds();
			for (Zombie zombie : zombies) {
				if (zombie.getBounds().intersects(rb)) {
					balle.setVisible(false);
					zombie.setVisible(false);
				}
			}
		}
	}
	
	private class MAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			personnage.mousePressed(e);
		}
	}
	
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			personnage.KeyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			personnage.KeyPressed(e);
		}
	}
	
}
