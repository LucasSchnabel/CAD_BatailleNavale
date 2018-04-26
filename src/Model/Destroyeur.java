package Model;

public class Destroyeur extends AbstractTwoCaseShip{

	public Destroyeur(Position a, Position b) {
		super( a, b);
		this.setPv(getPv()-1);
	}

	@Override
	public String toString() {
		return "Destroyeur :"+super.toString();
	}
	
}
