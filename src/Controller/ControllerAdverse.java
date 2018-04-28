package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Bataille;
import Model.Grille;
import Model.Position;

public class ControllerAdverse implements ActionListener{

	private Bataille model;
	
	private Position tir;
	
	private boolean tirer;
	
	private static ControllerAdverse instance = null;
	
	public static ControllerAdverse getInstance(Bataille b){
		if(instance == null){
			instance = new ControllerAdverse(b);
		}
		return instance;
	}
	
	private ControllerAdverse(Bataille b){
		this.model = b;
		this.tirer = false;
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(model.getNbBateaux()<Grille.NB_MAX_BATEAUX){
			System.out.println("Vous devez placer tout vos bateaux avant de tirer");
		}else{
			if(action == "Tirer"){
				tirer = true;
			}else{
				String[] pos = action.split(",");
				Position p = new Position(Integer.parseInt(pos[0]),Integer.parseInt(pos[1]));
				this.tir = p;
			}
			if(tirer && tir!=null){
				if(model.tirJoueur(tir)){
					System.out.println("Tir réussi");
					tirer = false;
					tir = null;
				}else{
					System.out.println("Tir non effectuer");
				}
			}
		}
		
	}

}
