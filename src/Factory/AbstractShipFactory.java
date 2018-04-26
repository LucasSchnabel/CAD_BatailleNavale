package Factory;

import Model.AbstractFourCaseShip;
import Model.AbstractThreeCaseShip;
import Model.AbstractTwoCaseShip;
import Model.Position;

public abstract class AbstractShipFactory {

	public abstract AbstractTwoCaseShip createTwoCaseShip();
	
	public abstract AbstractThreeCaseShip createThreeCaseShip();
	
	public abstract AbstractFourCaseShip createFourCaseShip();
	
	public abstract AbstractTwoCaseShip createTwoCaseShip(Position a,Position b);
	
	public abstract AbstractThreeCaseShip createThreeCaseShip(Position a,Position b);
	
	public abstract AbstractFourCaseShip createFourCaseShip(Position a,Position b);
}
