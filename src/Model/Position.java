package Model;

import java.util.Random;

public class Position {

	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position a){
		this.x = a.getX();
		this.y = a.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		return "X : "+x+",Y : "+y;
	}

	public static Position random() {
		Position res = new Position(0,0);
		Random r = new Random();
		int x = r.nextInt(Grille.HAUTEUR);
		int y = r.nextInt(Grille.LARGEUR);
		res = new Position(x,y);
		return res;
	}

	public static Position randomSide(Position a) {
		Position res = null;
		int x = -1;int y = -1;
		Random r = new Random();
		int taille = r.nextInt(3)+1;
		int cote = 0;
		while((x<0 || x>=Grille.HAUTEUR) || (y<0 || y>=Grille.LARGEUR)){
			cote = r.nextInt(4);
			switch(cote){
			case 0:
				x = a.getX() + taille;
				y = a.getY();
				break;
			case 1:
				x = a.getX() - taille;
				y = a.getY();
				break;
			case 2:
				x = a.getX();
				y = a.getY() + taille;
				break;
			case 3:
				x = a.getX();
				y = a.getY() - taille;
				break;
			}
			taille = r.nextInt(3)+1;
			res = new Position(x,y);
		}
		return res;
	}
	
	
}
