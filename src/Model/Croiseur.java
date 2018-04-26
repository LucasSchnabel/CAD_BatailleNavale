package Model;

public class Croiseur extends AbstractThreeCaseShip{

	public Croiseur(Position a, Position b) {
		super(a, b);
	}

	@Override
	public String toString() {
		return "Croiseur :"+super.toString();
	}
}
