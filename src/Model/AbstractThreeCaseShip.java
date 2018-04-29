package Model;

public abstract class AbstractThreeCaseShip extends AbstractShip{

	public AbstractThreeCaseShip(Position a, Position b) {
		super(a, b);
		this.setTaille(3);
		this.type = "ThreeCaseShip";
	}

	@Override
	public String toString() {
		return super.toString();
	}


}
