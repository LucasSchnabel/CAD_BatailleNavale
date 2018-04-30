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
	 * taille du bateau
	 */
	private int taille;
	
	/**
	 * type du bateau
	 */
	protected String type;
	
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
		this.nbMunitions = Grille.HAUTEUR*Grille.LARGEUR;
		this.type = "Abstract";
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
		if(this.nbMunitions>0 && (p.getX()>0 && p.getY()>0 && p.getX()<Grille.LARGEUR && p.getY()<Grille.HAUTEUR)){
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
	
	public boolean toucherParUnTir(Position p){
		if(this.toucher(p)){
			if(pv > 0)	pv--;
			if(pv==0){
				coule = true;
				System.out.println("Bateau coule :"+this);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * renvoie true si le bateau positionner en a,b est en colision avec le bateau en c,d
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

	public Position[] positionsBateau(){
		Position[] res = new Position[this.taille];
		//determine si le bateau est horizontale ou verticale
		int dx = 0;int dy = 0;
		if(proue.getX()==poupe.getX()){
			dy = 1;
		}else{
			dx = 1;
		}
		//commence par le point du bateau le plus proche de l'origine
		Position tmp;
		if(proue.getX()<=poupe.getX() && proue.getY()<=poupe.getY()){
			tmp = new Position(proue);
		}else{
			tmp = new Position(poupe);
		}
		//ajoute chaque point du bateau dans le tableau de position
		for(int i = 0;i<taille;i++){
			res[i] = tmp;
			tmp = new Position(tmp.getX()+dx,tmp.getY()+dy);
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

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	@Override
	public String toString() {
		return "AbstractShip [pv=" + pv + ", coule=" + coule + ", proue=" + proue + ", poupe=" + poupe
				+ ", nbMunitions=" + nbMunitions + ", taille=" + taille + ", type=" + type + "]";
	}
	
	public String getType() {
		return type;
	}

	
}
