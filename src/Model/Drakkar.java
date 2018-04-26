package Model;

public class Drakkar extends AbstractTwoCaseShip{
	
	public Drakkar( Position a, Position b) {
		super( a, b);
		this.setPv(getPv()-1);
	}

	@Override
	public String toString() {
		return "Drakkar :"+super.toString();
	}

}
