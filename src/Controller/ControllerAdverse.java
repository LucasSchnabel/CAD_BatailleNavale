package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Bataille;
import Model.Position;

public class ControllerAdverse implements ActionListener{

	private Bataille model;
	
	private Position tir;
	
	private boolean tirer;
	
	public ControllerAdverse(Bataille b){
		this.model = b;
		this.tirer = false;
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action == "Tirer"){
			tirer = true;
		}else{
			String[] pos = action.split(",");
			Position p = new Position(Integer.parseInt(pos[0]),Integer.parseInt(pos[1]));
			this.tir = p;
		}
		if(tirer && tir!=null){
			if(model.getGrilleJoueur().touche(tir)){
				System.out.println("Tir réussi");
				tirer = false;
				tir = null;
			}else{
				System.out.println("Tir non effectuer");
			}
		}
	}

}
