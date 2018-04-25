package Model;

public abstract class AbstractShip {

	/**
	 * point de vie du bateau
	 */
	private int pv;
	/**
	 * est ce que le bateau est coule (pv = 0)
	 */
	private boolean coule;
	/**
	 * position de l'avant du bateau
	 */
	private Position proue;
	/**
	 * position de l'arriere du bateau
	 */
	private Position poupe;
	
	/**
	 * nombre de munitions du bateau (utile si on compte les munitions)
	 */
	private int nbMunitions;
	
	/**
	 * constructeur par defaut de tous les bateaux
	 * @param p
	 * 		nombre de point de vie
	 * @param a
	 * 		position de l'avant du bateau
	 * @param b
	 * 		position de l'arriere du bateau
	 */
	public AbstractShip(int p,Position a,Position b){
		this.pv = p;
		this.coule = false;
		this.proue = a;
		this.poupe = b;
		this.nbMunitions = 1;
	}
	
	/**
	 * est ce que le bateau peut effectuer un tir sur la position p
	 * @param p
	 * 		position du tir a effectuer
	 * @return
	 * 		vrai si le bateau peut tirer
	 */
	public boolean tirer(Position p){
		boolean res = false;
		if(this.nbMunitions>0){
			res = true;
		}
		return res;
	}
	
	/**
	 * renvoie si la position p est entre la proue et la poupe du bateau
	 * @param p
	 * 		la position qu'on l'on souhaite tester
	 * @return
	 * 		le bateau est il touche
	 */
	public boolean toucher(Position p){
		boolean res = false;
		
		
		
		return res;
	}

	
	/**
	 * tous les getters et setters du bateaux
	 * 
	 */
	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public boolean isCoule() {
		return coule;
	}

	public void setCoule(boolean coule) {
		this.coule = coule;
	}

	public Position getProue() {
		return proue;
	}

	public void setProue(Position proue) {
		this.proue = proue;
	}

	public Position getPoupe() {
		return poupe;
	}

	public void setPoupe(Position poupe) {
		this.poupe = poupe;
	}

	public int getNbMunitions() {
		return nbMunitions;
	}

	public void setNbMunitions(int nbMunitions) {
		this.nbMunitions = nbMunitions;
	}
	
	
}
