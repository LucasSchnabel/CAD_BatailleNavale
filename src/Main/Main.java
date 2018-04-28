package Main;

import javax.swing.JOptionPane;

import Factory.AbstractShipFactory;
import Factory.MoyenAgeShipFactory;
import Factory.XXShipFactory;
import Model.AbstractShip;
import Model.Bataille;
import Model.Position;

public class Main {

	public static void main(String[]args){
		JOptionPane jop = new JOptionPane();
	    String epoque = (String)jop.showInputDialog(null, 
	      "Choisissez votre époque !",
	      "Bataille Navale",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      Bataille.epoque,
	      Bataille.epoque[1]);
	    Bataille b = new Bataille(null);
	    b.setEpoque(epoque);
	}

}
