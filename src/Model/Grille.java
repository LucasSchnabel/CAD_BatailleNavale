package Model;

import java.util.ArrayList;
import java.util.List;

public class Grille {

	private List<Position> listeTirs;
	
	private List<AbstractShip> bateaux;
	
	public Grille(){
		this.bateaux = new ArrayList<AbstractShip>();
		this.listeTirs = new ArrayList<Position>();
	}
	
	public void addBateau(AbstractShip a){
		this.bateaux.add(a);
	}
	
	/**
	 * ajoute le tir effectuer a la liste de tir et renvoie si le tir a touche un bateau
	 * @param p
	 * 		position du tir
	 * @return
	 * 		si le tir a touche un bateau
	 */
	public boolean touche(Position p){
		boolean res = false;
		if(!listeTirs.contains(p)){
			this.listeTirs.add(p);
			for(AbstractShip bateau:this.bateaux){
				if(bateau.toucher(p))res = true;
			}
		}
		return res;
	}
	
}
