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
				this.cases[p.getY() + p.getX()*Grille.LARGEUR].setBackground(Color.BLUE);
			}
		}
		//affiche les tirs de l'adversaire
		int nbTir = this.model.getGrilleAdverse().getListeTirs().size();
		Color tir;Position p;
		for(int i = 0;i<nbTir;i++){
			p = this.model.getGrilleAdverse().getPositionTir(i);
			if(this.model.getGrilleAdverse().getResTir(i)){
				tir = Color.green;
			}else{
				tir = Color.cyan;
			}
			this.cases[p.getX() + p.getY()*Grille.LARGEUR].setBackground(tir);
		}
	}

	

}
