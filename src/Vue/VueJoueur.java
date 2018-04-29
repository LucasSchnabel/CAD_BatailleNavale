package Vue;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import Model.AbstractShip;
import Model.Bataille;
import Model.Grille;
import Model.Position;

public class VueJoueur extends AbstractVue{

	public VueJoueur(Bataille b) {
		super(b,true);
		this.getFrame().setTitle("Joueur");
	}

	@Override
	public void update(Observable o, Object arg) {
		//affiche les bateaux du joueur
		for(AbstractShip bateau:this.model.getGrilleJoueur().getBateaux()){
			for(Position p:bateau.positionsBateau()){
				this.cases[p.getX() + p.getY()*Grille.LARGEUR].setBackground(this.model.getGrilleJoueur().getEpoque().getColor());
			}
		}
		//affiche les tirs de l'adversaire
		int nbTir = this.model.getGrilleJoueur().getListeTirs().size();
		Color tir;Position p;
		for(int i = 0;i<nbTir;i++){
			p = this.model.getGrilleJoueur().getPositionTir(i);
			if(this.model.getGrilleJoueur().getResTir(i)){
				tir = Color.green;
			}else{
				tir = Color.cyan;
			}
			this.cases[p.getX() + p.getY()*Grille.LARGEUR].setBackground(tir);
		}
	}

	

}
