package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Factory.AbstractShipFactory;

public class Grille extends Observable {

	private List<Position> listeTirs;

	private List<AbstractShip> bateaux;

	private AbstractShipFactory epoque;

	public Grille(AbstractShipFactory factory) {
		this.bateaux = new ArrayList<AbstractShip>();
		this.listeTirs = new ArrayList<Position>();
		this.epoque = factory;
	}

	/**
	 * ajoute un bateau a la liste des bateaux
	 * 
	 * @param a
	 */
	public void addBateau(AbstractShip a) {
		this.bateaux.add(a);
	}
	
	/**
	 * supprime le bateau numero i de la liste
	 * @param i
	 */
	public void suppBateau(int i){
		this.bateaux.remove(i);
	}
	
	/**
	 * supprime de la liste des bateaux le bateau mis en parametre
	 * @param b
	 */
	public void suppBateau(AbstractShip b){
		this.bateaux.remove(b);
	}

	/**
	 * verifie si la construction d'un bateau a partir des positions a et b est possible puis la rajoute dans la liste
	 * @param a
	 * @param b
	 * @return
	 * 		vrai si le bateau est creer avec succes
	 */
	public boolean constructionBateau(Position a,Position b){
		boolean res = false;
		int taille = 0;
		if(a.getX()==b.getX()){//si le bateau est verticale
			taille = Math.abs(a.getY()-b.getY());
		}else if(a.getY()==b.getY()){//si le bateau est horizontale
			taille = Math.abs(a.getX()-b.getX());
		}//sinon le bateau est en diagonale et on ne le creer pas
		
		if(taille>1 && taille<5){
			AbstractShip bateau = null;
			boolean colision = false;
			//on verifie qu'il n'y a pas de colision avec un autre bateau deja place
			for(AbstractShip ship:this.bateaux){
				if(ship.colision(a, b)){
					colision = true;
					break;
				}
			}
			if(!colision){
				switch(taille){
				case 2:
					bateau = epoque.createTwoCaseShip(a, b);
					break;
				case 3:
					bateau = epoque.createThreeCaseShip(a, b);
					break;
				case 4:
					bateau = epoque.createFourCaseShip(a, b);
					break;
				}
				if(bateau!=null)this.addBateau(bateau);
			}
		}
		return res;
	}

	/**
	 * ajoute le tir effectuer a la liste de tir et renvoie si le tir a touche
	 * un bateau
	 * 
	 * @param p
	 *            position du tir
	 * @return si le tir a touche un bateau
	 */
	public boolean touche(Position p) {
		boolean res = false;
		if (!listeTirs.contains(p)) {
			this.listeTirs.add(p);
			for (AbstractShip bateau : this.bateaux) {
				if (bateau.toucher(p))
					res = true;
			}
		}
		return res;
	}

	public AbstractShipFactory getEpoque() {
		return epoque;
	}

	public void setEpoque(AbstractShipFactory epoque) {
		this.epoque = epoque;
		for(AbstractShip bateau:this.bateaux){
			this.suppBateau(bateau);
			this.constructionBateau(bateau.getProue(), bateau.getPoupe());
		}
	}

}
