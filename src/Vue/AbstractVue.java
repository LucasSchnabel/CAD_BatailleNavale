package Vue;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Grille;

public abstract class AbstractVue implements Observer{

	private static int hauteur = 9;
	private static int largeur = 9;
	private Grille model;
	private JFrame frame = new JFrame("Bataille navale");
	private JPanel grille;
	private JButton[] cases;
	
	public AbstractVue(Grille g){
		this.model = g;
		this.buildFrame();
	}
	
	public void buildFrame(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grille = (JPanel) frame.getContentPane();
		grille.setLayout(new GridLayout(hauteur,largeur));
		cases = new JButton[hauteur*largeur];
		for(int i = 0;i<largeur;i++){
			for(int j = 0;j<hauteur;j++){
				JButton button = new JButton(i+","+j);
				cases[i*largeur + j] = button;
				grille.add(button);
			}
		}
		
		frame.pack();
        frame.setVisible(true);
	}
	
	public abstract void update(Observable o, Object arg);

}
