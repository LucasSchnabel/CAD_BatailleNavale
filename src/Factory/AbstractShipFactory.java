package Factory;

import java.awt.Color;

import Model.AbstractFourCaseShip;
import Model.AbstractThreeCaseShip;
import Model.AbstractTwoCaseShip;
import Model.Position;

public abstract class AbstractShipFactory {
	
	protected Color couleur;
	
	protected String name;
	
	public AbstractShipFactory() {
		this.name = "Abstract";
	}

	public abstract AbstractTwoCaseShip createTwoCaseShip();
	
	public abstract AbstractThreeCaseShip createThreeCaseShip();
	
	public abstract AbstractFourCaseShip createFourCaseShip();
	
	public abstract AbstractTwoCaseShip createTwoCaseShip(Position a,Position b);
	
	public abstract AbstractThreeCaseShip createThreeCaseShip(Position a,Position b);
	
	public abstract AbstractFourCaseShip createFourCaseShip(Position a,Position b);

	public Color getColor() {
		return couleur;
	}
	
	public String getName() {
		return name;
	}
}
