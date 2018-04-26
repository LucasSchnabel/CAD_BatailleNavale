package Factory;

import Model.AbstractFourCaseShip;
import Model.AbstractThreeCaseShip;
import Model.AbstractTwoCaseShip;
import Model.Caravelle;
import Model.Drakkar;
import Model.Galere;
import Model.Position;

public class MoyenAgeShipFactory extends AbstractShipFactory{

	public AbstractTwoCaseShip createTwoCaseShip() {
		return new Drakkar(null, null);
	}

	public AbstractThreeCaseShip createThreeCaseShip() {
		return new Caravelle(null, null);
	}

	public AbstractFourCaseShip createFourCaseShip() {
		return new Galere(null, null);
	}

	public AbstractTwoCaseShip createTwoCaseShip(Position a, Position b) {
		return new Drakkar(a, b);
	}

	public AbstractThreeCaseShip createThreeCaseShip(Position a, Position b) {
		return new Caravelle(a, b);
	}

	public AbstractFourCaseShip createFourCaseShip(Position a, Position b) {
		return new Galere(a, b);
	}

}
