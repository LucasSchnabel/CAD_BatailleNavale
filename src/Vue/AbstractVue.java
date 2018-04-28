package Vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Controller.ControllerAdverse;
import Controller.ControllerJoueur;
import Model.Bataille;
import Model.Grille;

public abstract class AbstractVue implements Observer{

	protected Bataille model;
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
	private boolean joueur;
	
	public AbstractVue(Bataille b,boolean j){
		this.model = b;
		this.joueur = j;
		this.buildFrame();
	}
	
	public void buildFrame(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ControllerAdverse ca = ControllerAdverse.getInstance(model);
		ControllerJoueur cj = ControllerJoueur.getInstance(model);
		
		//remplit la grille de bouton
        grille = (JPanel) frame.getContentPane();
		grille.setLayout(new GridLayout(Grille.HAUTEUR,Grille.LARGEUR));
		cases = new JButton[Grille.HAUTEUR*Grille.LARGEUR];
		for(int i = 0;i<Grille.HAUTEUR;i++){
			for(int j = 0;j<Grille.LARGEUR;j++){
				JButton button = new JButton(i+","+j);
				cases[i*Grille.LARGEUR + j] = button;
				grille.add(button);
				if(joueur){
					button.addActionListener(cj);
				}else{
					button.addActionListener(ca);
				}
			}
		}
		//ajout des controlleur
		tirer.addActionListener(ca);
		placerBateau.addActionListener(cj);
		//changement d'époque
		moyenAge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setEpoque("Moyen-Age");
			}
		});
		xxSiecle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setEpoque("XXe siecle");
			}
		});
		//gestion de la partie 
		chargerPartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		sauvegarderPartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		nouvellePartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
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
