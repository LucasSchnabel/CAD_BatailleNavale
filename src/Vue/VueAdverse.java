package Vue;

import java.util.Observable;
import java.util.Observer;

import Model.Grille;

public class VueAdverse extends AbstractVue{

	public VueAdverse(Grille j,Grille o) {
		super(j,o);
		this.getFrame().setJMenuBar(null);
		this.getFrame().setTitle("Adversaire");
	}

	public void update(Observable o, Object arg) {
		//affiche les tirs du joueurs 
		
		//(optionnel)affiche les bateaux de l'adversaire quand la partie est fini
		
	}

}
