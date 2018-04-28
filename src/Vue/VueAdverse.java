package Vue;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import Model.Bataille;
import Model.Grille;
import Model.Position;

public class VueAdverse extends AbstractVue{

	public VueAdverse(Bataille b) {
		super(b,false);
		this.getFrame().setJMenuBar(null);
		this.getFrame().setTitle("Adversaire");
	}

	public void update(Observable o, Object arg) {
		//affiche les tirs du joueurs 
		int nbTir = this.model.getGrilleAdverse().getListeTirs().size();
		Color tir;Position p;
		for(int i = 0;i<nbTir;i++){
			p = this.model.getGrilleAdverse().getPositionTir(i);
			if(this.model.getGrilleAdverse().getResTir(i)){
				tir = Color.green;
			}else{
				tir = Color.cyan;
			}
			this.cases[p.getY() + p.getX()*Grille.LARGEUR].setBackground(tir);
		}
		//(optionnel)affiche les bateaux de l'adversaire quand la partie est fini
		
	}

}
