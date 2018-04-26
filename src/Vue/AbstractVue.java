package Vue;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Model.Grille;

public abstract class AbstractVue implements Observer{

	protected static int hauteur = 9;
	protected static int largeur = 9;
	protected Grille modelJoueur;
	protected Grille modelAdversaire;
	private JFrame frame = new JFrame("Bataille navale");
	private JPanel grille;
	protected JButton[] cases;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu action = new JMenu("Actions");
	private JMenu partie = new JMenu("Partie");
	private JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
	private JMenuItem chargerPartie = new JMenuItem("Charger Partie");
	private JMenuItem sauvegarderPartie = new JMenuItem("Sauvegarder partie");
	private JMenuItem tirer = new JMenuItem("Tirer");
	private JMenuItem placerBateau = new JMenuItem("Placer Bateau");
	private JMenu choixEpoque = new JMenu("Epoques");
	private JMenuItem moyenAge = new JMenuItem("Moyen Age");
	private JMenuItem xxSiecle = new JMenuItem("XXème siècle");
	
	public AbstractVue(Grille joueur,Grille ordi){
		this.modelJoueur = joueur;
		this.modelAdversaire = ordi;
		this.buildFrame();
	}
	
	public void buildFrame(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//remplit la grille de bouton
        grille = (JPanel) frame.getContentPane();
		grille.setLayout(new GridLayout(hauteur,largeur));
		cases = new JButton[hauteur*largeur];
		for(int i = 0;i<hauteur;i++){
			for(int j = 0;j<largeur;j++){
				JButton button = new JButton(i+","+j);
				cases[i*largeur + j] = button;
				grille.add(button);
			}
		}
		
		//remplit le menu deroulant
		action.add(tirer);
		action.add(placerBateau);
		choixEpoque.add(moyenAge);
		choixEpoque.add(xxSiecle);
		action.add(choixEpoque);
		partie.add(nouvellePartie);
		partie.add(chargerPartie);
		partie.add(sauvegarderPartie);
		
		menuBar.add(partie);
		menuBar.add(action);
		frame.setJMenuBar(menuBar);
		
		frame.pack();
        frame.setVisible(true);
	}
	
	public abstract void update(Observable o, Object arg);

	public JFrame getFrame(){
		return this.frame;
	}
	
}
