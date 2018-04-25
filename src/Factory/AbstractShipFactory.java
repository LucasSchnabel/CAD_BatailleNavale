package Factory;

import Model.AbstractFourCaseShip;
import Model.AbstractThreeCaseShip;
import Model.AbstractTwoCaseShip;

public abstract class AbstractShipFactory {

	public abstract AbstractTwoCaseShip createTwoCaseShip();
	
	public abstract AbstractThreeCaseShip createThreeCaseShip();
	
	public abstract AbstractFourCaseShip createFourCaseShip();
	
}
