package Model;

public class Galere extends AbstractFourCaseShip{

	public Galere( Position a, Position b) {
		super( a, b);
		this.setPv(getPv()+1);
	}

	@Override
	public String toString() {
		return "Galere :"+super.toString();
	}
}
