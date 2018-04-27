package Factory;

import java.awt.Color;

import Model.AbstractFourCaseShip;
import Model.AbstractThreeCaseShip;
import Model.AbstractTwoCaseShip;
import Model.Croiseur;
import Model.Cuirasse;
import Model.Destroyeur;
import Model.Position;

public class XXShipFactory extends AbstractShipFactory{

	public XXShipFactory(){
		this.couleur = Color.BLACK;
	}
	
	public AbstractTwoCaseShip createTwoCaseShip() {
		return new Destroyeur(null, null);
	}

	public AbstractThreeCaseShip createThreeCaseShip() {
		return new Croiseur(null, null);
	}

	public AbstractFourCaseShip createFourCaseShip() {
		return new Cuirasse(null, null);
	}

	public AbstractTwoCaseShip createTwoCaseShip(Position a, Position b) {
		return new Destroyeur(a, b);
	}

	public AbstractThreeCaseShip createThreeCaseShip(Position a, Position b) {
		return new Croiseur(a, b);
	}

	public AbstractFourCaseShip createFourCaseShip(Position a, Position b) {
		return new Cuirasse(a, b);
	}

}
