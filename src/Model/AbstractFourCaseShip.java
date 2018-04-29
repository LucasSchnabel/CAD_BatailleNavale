package Model;

public abstract class AbstractFourCaseShip extends AbstractShip {

	public AbstractFourCaseShip(Position a, Position b) {
		super( a, b);
		this.setTaille(4);
		this.type = "FourCaseShip";
	}

	@Override
	public String toString() {
		return super.toString();
	}


}
