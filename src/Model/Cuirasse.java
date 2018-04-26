package Model;

public class Cuirasse extends AbstractFourCaseShip{

	public Cuirasse(Position a, Position b) {
		super(a, b);
		this.setPv(getPv()+1);
	}

	@Override
	public String toString() {
		return "Cuirasse :"+super.toString();
	}
}
