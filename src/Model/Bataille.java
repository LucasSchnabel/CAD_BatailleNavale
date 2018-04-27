package Model;

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
	public static final String[] epoque = {"Moyen-Age","XXe siecle"}; 
	
	public Bataille(AbstractShipFactory epoque){
		this.grilleJoueur = new Grille(epoque);
		this.grilleAdverse = new Grille(epoque);
		VueJoueur vJ = new VueJoueur(grilleJoueur,grilleAdverse);
		VueAdverse vA = new VueAdverse(grilleJoueur,grilleAdverse);
		this.grilleJoueur.addObserver(vJ);
		this.grilleJoueur.addObserver(vA);
		this.grilleAdverse.addObserver(vJ);
		this.grilleAdverse.addObserver(vA);
		this.tour = true;
	}
	
	public void setEpoque(String s){
		AbstractShipFactory newEpoque;
		switch(s){
		case "MoyenAge":
			newEpoque = new MoyenAgeShipFactory();
			break;
		case "XXeme":
			newEpoque = new XXShipFactory();
			break;
		default:
				newEpoque = new XXShipFactory();
		}
		this.grilleJoueur.setEpoque(newEpoque);
		this.grilleAdverse.setEpoque(newEpoque);		
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

}