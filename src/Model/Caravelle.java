package Model;

public class Caravelle extends AbstractThreeCaseShip{

	public Caravelle(Position a, Position b) {
		super(a, b);
	}

	@Override
	public String toString() {
		return "Caravelle :"+super.toString();
	}
}
