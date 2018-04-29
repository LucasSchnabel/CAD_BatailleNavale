package Model;

import java.util.Observable;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import Factory.AbstractShipFactory;
import Factory.MoyenAgeShipFactory;
import Factory.XXShipFactory;
import Vue.VueAdverse;
import Vue.VueJoueur;

public class Bataille {
	
	private Grille grilleJoueur;
	private Grille grilleAdverse;
	private boolean tour;
	private int nbBateaux;
	VueJoueur vJ;
	VueAdverse vA;
	public static final String[] epoque = {"Moyen-Age","XXe siecle"}; 
	
	public Bataille(AbstractShipFactory epoque){
		this.grilleJoueur = new Grille(epoque, "Grille_Joueur");
		this.grilleAdverse = new Grille(epoque, "Grille_Adverse");
		this.vJ = new VueJoueur(this);
		this.vA = new VueAdverse(this);
		this.grilleJoueur.addObserver(this.vJ);
		this.grilleJoueur.addObserver(this.vA);
		this.grilleAdverse.addObserver(this.vJ);
		this.grilleAdverse.addObserver(this.vA);
		this.tour = true;
		this.nbBateaux = 0;
	}
	
	public void setEpoque(String s){
		AbstractShipFactory newEpoque;
		switch(s){
		case "Moyen-Age":
			newEpoque = new MoyenAgeShipFactory();
			break;
		case "XXe siecle":
			newEpoque = new XXShipFactory();
			break;
		default:
				newEpoque = new XXShipFactory();
		}
		this.grilleJoueur.setEpoque(newEpoque);
		this.grilleAdverse.setEpoque(newEpoque);		
	}
	
	public boolean tirJoueur(Position p){
		boolean res = false;
		if(tour && nbBateaux==Grille.NB_MAX_BATEAUX && !partieFini()){
			res = this.grilleAdverse.touche(p);
			tour = !tour;
		}
		return res;
	}
	
	public boolean tirAdverse(){
		boolean res = false;
		if(!tour && nbBateaux==Grille.NB_MAX_BATEAUX && !partieFini()){
			while(!res){
				Position p = Position.random();
				res = this.grilleJoueur.touche(p);
				System.out.println("Tir adverse sur la position :"+p);
			}
			tour = !tour;
		}
		return res;
	}
	
	public boolean partieFini(){
		boolean res = true;
		for(AbstractShip bateau:this.grilleJoueur.getBateaux()){
			if(!bateau.isCoule()){
				res = false;
			}
		}
		if(res){
			System.out.println("L'adversaire a detruit tout vos bateaux, vous avez perdu");
		}else{
			res = true;
			for(AbstractShip bateau:this.grilleAdverse.getBateaux()){
				if(!bateau.isCoule()){
					res = false;
				}
			}
			if(res){
				System.out.println("Vous avez détruit tout les bateaux de l'adverssaire, bravo vous avez gagné");
			}
		}
		return res;
	}
	
	public boolean constructionBateauJoueur(Position a,Position b){
		boolean res = false;
		res = this.grilleJoueur.constructionBateau(a, b);
		return res;
	}
	
	public boolean constructionBateauAdverse(){
		boolean res = false;
		Position a,b;
		while(!res){
			a = Position.random();
			b = Position.randomSide(a);
			res = this.grilleAdverse.constructionBateau(a, b);
		}
		nbBateaux++;
		return res;
	}
	
	public Grille getGrilleJoueur() {
		return grilleJoueur;
	}

	public void setGrilleJoueur(Grille grilleJoueur) {
		this.grilleJoueur = grilleJoueur;
	}

	public Grille getGrilleAdverse() {
		return grilleAdverse;
	}

	public void setGrilleAdverse(Grille grilleAdverse) {
		this.grilleAdverse = grilleAdverse;
	}

	public int getNbBateaux(){
		return nbBateaux;
	}
	
	public void setNbBateaux(int nbBateaux) {
		this.nbBateaux = nbBateaux;
	}
	
	public void closeFrame() {
		this.vJ.getFrame().dispose();
		this.vA.getFrame().dispose();
	}

	@Override
	public String toString() {
		return "Bataille [grilleJoueur=" + grilleJoueur + ", grilleAdverse=" + grilleAdverse + ", tour=" + tour
				+ ", nbBateaux=" + nbBateaux + ", vJ=" + vJ + ", vA=" + vA + "]";
	}

	
	
}
