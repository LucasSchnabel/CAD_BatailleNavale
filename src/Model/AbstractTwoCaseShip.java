package Model;

public abstract class AbstractTwoCaseShip extends AbstractShip {
	
	public AbstractTwoCaseShip( Position a, Position b) {
		super(a, b);
		this.setTaille(2);
		this.type = "TwoCaseShip";
	}

	@Override
	public String toString() {
		return super.toString();
	}

	

}
