package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Bataille;
import Model.Grille;
import Model.Position;

public class ControllerAdverse implements ActionListener {

	private Bataille model;

	private Position tir;

	private boolean tirer;

	public ControllerAdverse(Bataille b) {
		this.model = b;
		this.tirer = true;
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (model.getNbBateaux() < Grille.NB_MAX_BATEAUX) {
			System.out.println("Vous devez placer tous vos bateaux avant de tirer");
		} else {

			String[] pos = action.split(",");
			Position p = new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
			this.tir = p;
			if (tir != null) {
				if (model.tirJoueur(tir)) {
					System.out.println("Tir sur la position :" + tir);
					tirer = false;
					tir = null;
					model.tirAdverse();
				} else {
					System.out.println("Tir non effectué");
				}
			}
		}

	}
}
