package IHM;


import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MaFenetre extends JFrame {
	
	public MaFenetre() {
		initUI();
	}
	
	private void initUI() {
		add(new Carte());
		setResizable(false);
		pack();
		setTitle("Death from above");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(()->{
			MaFenetre ex = new MaFenetre();
			ex.setVisible(true);
		});
	}
}
