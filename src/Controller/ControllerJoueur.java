package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Model.Bataille;
import Model.Position;

public class ControllerJoueur implements ActionListener{

	private Bataille model;
	
	public void setModel(Bataille model) {
		this.model = model;
	}

	private List<Position> cases;
	
	private boolean placerBateau;
	
	public ControllerJoueur(Bataille b){
		this.model = b;
		this.placerBateau = false;
		this.cases = new ArrayList<Position>();
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action == "Placer Bateau"){
			placerBateau = true;
		}else{
			String[] pos = action.split(",");
			Position p = new Position(Integer.parseInt(pos[1]),Integer.parseInt(pos[0]));
			cases.add(p);
			if(cases.size()>2){
				cases.remove(0);
			}
		}
		if(cases.size()==2 && placerBateau){
			if(!model.constructionBateauJoueur(cases.get(0), cases.get(1))){
				System.out.println("Les positions que vous avez donné ne peuvent pas créer de bateau");
				cases.removeAll(cases);
			}else{
				model.constructionBateauAdverse();
				placerBateau = false;
			}
		}
	}

}
