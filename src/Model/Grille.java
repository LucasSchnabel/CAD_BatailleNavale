package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import Factory.AbstractShipFactory;

public class Grille extends Observable {

	private List<Position> listeTirs;

	private List<Boolean> listeResultatTir;
	
	private List<AbstractShip> bateaux;

	private AbstractShipFactory epoque;
	
	private String name;
	
	public final static int HAUTEUR = 9;
	
	public final static int LARGEUR = 9;

	public final static int NB_MAX_BATEAUX = 5;
	
	public Grille(AbstractShipFactory factory, String n) {
		this.bateaux = new ArrayList<AbstractShip>();
		this.listeTirs = new ArrayList<Position>();
		this.listeResultatTir = new ArrayList<Boolean>();
		this.epoque = factory;
		this.name = n;
	}

	/**
	 * ajoute un bateau a la liste des bateaux
	 * 
	 * @param a
	 */
	public void addBateau(AbstractShip a) {
		this.bateaux.add(a);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * supprime le bateau numero i de la liste
	 * @param i
	 */
	public void suppBateau(int i){
		this.bateaux.remove(i);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * supprime de la liste des bateaux le bateau mis en parametre
	 * @param b
	 */
	public void suppBateau(AbstractShip b){
		this.bateaux.remove(b);
		this.setChanged();
		this.notifyObservers();
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
		if(this.bateaux.size()<NB_MAX_BATEAUX){
			if(a.getX()==b.getX()){//si le bateau est verticale
				taille = Math.abs(a.getY()-b.getY())+1;
			}else if(a.getY()==b.getY()){//si le bateau est horizontale
				taille = Math.abs(a.getX()-b.getX())+1;
			}else{//sinon le bateau est en diagonale et on ne le creer pas
				System.out.println("Vous ne pouvez pas cr�er de bateau en diagonale");
			}
			
			if(taille>1 && taille<5){
				AbstractShip bateau = null;
				boolean colision = false;
				//on verifie qu'il n'y a pas de colision avec un autre bateau deja place
				for(AbstractShip ship:this.bateaux){
					if(ship.colision(a, b)){
						colision = true;
						System.out.println("Colision avec un autre bateau");
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
					if(bateau!=null){
						this.addBateau(bateau);
						res = true;
					}
				}
			}
		}else{
			System.out.println("Vous avez atteint le nombre max de bateau");
		}
		return res;
	}

	/**
	 * ajoute le tir effectuer a la liste de tir et renvoie si le tir est effectue
	 * @param p
	 *            position du tir
	 * @return si le tir est effectue
	 */
	public boolean touche(Position p) {
		boolean res = false;
		boolean toucher = false;
		if (!listeTirs.contains(p)) {
			this.listeTirs.add(p);
			res = true;
			for (AbstractShip bateau : this.bateaux) {
				if (bateau.toucherParUnTir(p)){
					toucher = true;
					break;
				}
			}
			this.listeResultatTir.add(toucher);
			this.setChanged();
			this.notifyObservers();
		}
		return res;
	}

	public AbstractShipFactory getEpoque() {
		return epoque;
	}

	/**
	 * set l'epoque de la grille et remplace tout les bateaux par leurs equivalent dans la nouvelle epoque
	 * @param epoque
	 */
	public void setEpoque(AbstractShipFactory epoque) {
		this.epoque = epoque;
		for(AbstractShip bateau:this.bateaux){
			this.suppBateau(bateau);
			this.constructionBateau(bateau.getProue(), bateau.getPoupe());
		}
	}
	
	public List<AbstractShip> getBateaux(){
		return this.bateaux;
	}

	public List<Position> getListeTirs() {
		return listeTirs;
	}

	public List<Boolean> getListeResultatTir() {
		return listeResultatTir;
	}
	
	public boolean getResTir(int i){
		return this.listeResultatTir.get(i);
	}
	
	public Position getPositionTir(int i){
		return this.listeTirs.get(i);
	}
	
	public String getName() {
		return name;
	}

	
}
