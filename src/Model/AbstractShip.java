package Model;

public abstract class AbstractShip {

	/**
	 * point de vie du bateau
	 */
	private int pv;
	/**
	 * point de vie du bateau au moment de sa creation 
	 */
	private static int pvDefaut = 3;
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
	public AbstractShip(Position a,Position b){
		this.pv = pvDefaut;
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
		if(this.proue.getX()==this.poupe.getX()){//si le bateau est verticale
			if(p.getX()==this.proue.getX()){//si le tir est sur la meme colonne
				if((p.getY()>=this.proue.getY() && p.getY()<=this.poupe.getY()) || (p.getY()<=this.proue.getY() && p.getY()>=this.poupe.getY())){
					res = true;
				}
			}
		}else if(this.proue.getY()==this.poupe.getY()){//si le bateau est horizontale
			if(p.getY()==this.proue.getY()){//si le tir est sur la meme ligne
				if((p.getX()>=this.proue.getX() && p.getX()<=this.poupe.getX()) || (p.getX()<=this.proue.getX() && p.getX()>=this.poupe.getX())){
					res = true;
				}
			}
		}
		return res;
	}
	
	/**
	 * renvoie si le bateau positionner en a,b est en colision avec le bateau en c,d
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 * 		si il y a colision
	 */
	public boolean colision(Position a,Position b){
		boolean res = false;
		int dx = 0;int dy = 0;int taille = 0;
		if(a.getX()==b.getX()){//si le bateau est verticale
			dy = 1;taille = Math.abs(a.getY()-b.getY());
		}else if(a.getY()==b.getY()){//si le bateau est horizontale
			dx = 1;taille = Math.abs(a.getX()-b.getX());
		}
		Position tmp;
		if(a.getX()<=b.getX() && a.getY()<=b.getY()){
			tmp = new Position(a);
		}else{
			tmp = new Position(b);
		}
		for(int i = 0;i<taille;i++){
			if(this.toucher(tmp)){
				res = true;
				break;
			}
			tmp.setX(tmp.getX()+dx);
			tmp.setY(tmp.getY()+dy);
		}
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

	@Override
	public String toString() {
		return "AbstractShip [pv=" + pv + ", coule=" + coule + ", proue=" + proue + ", poupe=" + poupe
				+ ", nbMunitions=" + nbMunitions + "]";
	}
	
	
}
