package Main;

import Factory.AbstractShipFactory;
import Factory.MoyenAgeShipFactory;
import Model.AbstractShip;
import Model.Grille;

public class Bataille {
	
	private Grille grilleJoueur;
	private Grille grilleAdverse;
	
	
	public Bataille(AbstractShipFactory epoque){
		this.grilleJoueur = new Grille(epoque);
		this.grilleAdverse = new Grille(epoque);
	}
	
	public static void main(String[]args){
		MoyenAgeShipFactory mf = new MoyenAgeShipFactory();
		AbstractShip drakkar = mf.createTwoCaseShip();
		System.out.println(drakkar);
	}
	
	

}
