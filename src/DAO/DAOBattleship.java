package DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Factory.AbstractShipFactory;
import Factory.MoyenAgeShipFactory;
import Factory.XXShipFactory;
import Model.AbstractShip;
import Model.Bataille;
import Model.Grille;
import Model.Position;
import Vue.VueAdverse;
import Vue.VueJoueur;

public class DAOBattleship {

	private volatile static DAOBattleship unique = null;

	private DateFormat dateFormat;
	private Date date;

	private DAOBattleship() {
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		this.date = Calendar.getInstance().getTime();
	}

	public static DAOBattleship getInstance() {
		if (unique == null) {
			synchronized (DAOBattleship.class) {
				if (unique == null) {
					unique = new DAOBattleship();
				}
			}
		}
		return unique;
	}

	public void generateCSV(Bataille bataille) {

		try {
			String outputFileName = this.dateFormat.format(this.date);
			outputFileName = outputFileName.replaceAll("/", "_") + ".csv";
			FileWriter writer = new FileWriter(outputFileName);

			String csvSeparator = ",";
			Grille grilleJoueur = bataille.getGrilleJoueur();
			Grille grilleAdverse = bataille.getGrilleAdverse();

			Grille[] tabGrille = { grilleJoueur, grilleAdverse };
			// Meme epoque pour les deux grilles
			String epoque = grilleJoueur.getEpoque().getName();
			writer.append(epoque);
			writer.append('\n');

			for (Grille grille : tabGrille) {
				writer.append(grille.getName());
				writer.append('\n');
				// Ecriture liste des bateaux
				List<AbstractShip> list_bateaux = grille.getBateaux();

				// Ecriture de la taille de la liste de bateaux pour la lecture
				writer.append(Integer.toString(list_bateaux.size()));
				writer.append('\n');
				for (AbstractShip bateau : list_bateaux) {
					// Ecriture du type
					writer.append(bateau.getType());
					writer.append(csvSeparator);
					// Ecriture des points du vie du bateau
					writer.append(Integer.toString(bateau.getPv()));
					writer.append(csvSeparator);

					// Ecriture du statut du bateau (coule ou non coule)
					writer.append(Boolean.toString(bateau.isCoule()));
					writer.append(csvSeparator);

					// Ecriture de la position de la proue
					writer.append(Integer.toString(bateau.getProue().getX()));
					writer.append(csvSeparator);
					writer.append(Integer.toString(bateau.getProue().getY()));
					writer.append(csvSeparator);

					// Ecriture de la position de la poupe
					writer.append(Integer.toString(bateau.getPoupe().getX()));
					writer.append(csvSeparator);
					writer.append(Integer.toString(bateau.getPoupe().getY()));
					writer.append(csvSeparator);

					// Ecriture de nbMunitions
					writer.append(Integer.toString(bateau.getNbMunitions()));
					writer.append('\n');
				}
				// Ecriture de la liste des tirs
				List<Position> listeTirs = grille.getListeTirs();

				// Ecriture de la taille de la liste des tirs pour la lecture
				writer.append(Integer.toString(listeTirs.size()));
				writer.append('\n');
				for (Position p : listeTirs) {
					// Ecriture des positions des tirs pour pouvoir effectuer tous les tirs au
					// chargement de la grille avec la fonction Grille.touche(Position p)
					writer.append(Integer.toString(p.getX()));
					writer.append(csvSeparator);
					writer.append(Integer.toString(p.getY()));
					writer.append('\n');
				}
			}
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Bataille readCSV(String inputFileName) {
		Bataille bataille = null;
		BufferedReader br = null;
		String line = "";
		// séparateur du CSV
		String csvSeparator = ",";
		int nbBateauxJoueur = 0;
		int nbTirsJoueur = 0;
		int nbBateauxAdverse = 0;
		int nbTirsAdverse = 0;
		Grille grillejoueur = null;
		Grille grilleadverse = null;
		try {
			br = new BufferedReader(new FileReader(inputFileName));
			int i = 0;
			// on boucle sur chaque ligne du fichier
			while ((line = br.readLine()) != null) {
				i++;
				// Creation de la factory en fonction de l'epoque
				if (i == 1) {
					switch (line) {
					case "Moyen-Age":
						bataille = new Bataille(new MoyenAgeShipFactory());
						break;

					case "XXe siecle":
						bataille = new Bataille(new XXShipFactory());
						break;
					default:
						break;
					}
				} else {
					if (i == 2) {// recuperation de la grille du joueur
						grillejoueur = bataille.getGrilleJoueur();
					} else if (i == 3) {// ligne taille liste bateaux joueur
						nbBateauxJoueur = Integer.parseInt(line) + i + 1;
					} else if (i < nbBateauxJoueur) { // si le numero de ligne est inferieur au nombre de bateaux du
														// joueur on cree ses bateaux
						String[] ligneBateau = line.split(csvSeparator);
						grillejoueur.addBateau(this.readline(ligneBateau, grillejoueur));
					} else if (i == nbBateauxJoueur) {// sinon on passe a sa liste de tirs
						nbTirsJoueur = Integer.parseInt(line) + i + 1; // ligne taille liste de tirs
					} else if (i < nbTirsJoueur) {// on cree ses tirs
						String[] tir = line.split(csvSeparator);
						this.tirGrille(tir, grillejoueur);
					} else if (i == nbTirsJoueur) {// recuperation de la grille du adverse
						grilleadverse = bataille.getGrilleAdverse();
					} else if (i == nbTirsJoueur + 1) {// ligne taille liste bateaux adverse
						nbBateauxAdverse = Integer.parseInt(line) + i + 1;
					} else if (i < nbBateauxAdverse) { // si le numero de ligne est inferieur au nombre de bateaux du
														// joueur adverse on cree ses bateaux
						String[] ligneBateau = line.split(csvSeparator);
						grilleadverse.addBateau(this.readline(ligneBateau, grilleadverse));
					} else if (i == nbBateauxAdverse) {// sinon on passe a sa liste de tirs
						nbTirsAdverse = Integer.parseInt(line) + i + 1; // ligne taille liste de tirs
					} else if (i < nbTirsAdverse) {// on cree ses tirs
						String[] tir = line.split(csvSeparator);
						this.tirGrille(tir, grilleadverse);
					} // fin
				}
			}

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		bataille.setNbBateaux(grillejoueur.getBateaux().size());//On met le nombre de bateaux aux maximum
		bataille.setGrilleJoueur(grillejoueur);
		bataille.setGrilleAdverse(grilleadverse);
		return bataille;
	}

	private AbstractShip createCustomedShip(String type, Grille g, int pv, boolean statut, Position p1, Position p2,
			int nbMunitions) {
		AbstractShip res = null;
		AbstractShipFactory factory = g.getEpoque();
		switch (type) {
		case "TwoCaseShip":
			res = factory.createTwoCaseShip(p1, p2);
			break;

		case "ThreeCaseShip":
			res = factory.createThreeCaseShip(p1, p2);

			break;
		case "FourCaseShip":
			res = factory.createFourCaseShip(p1, p2);
			break;
		}
		res.setPv(pv);
		res.setCoule(statut);
		res.setNbMunitions(nbMunitions);
		return res;
	}

	private AbstractShip readline(String[] line, Grille g) {
		// Type du bateau
		String type = line[0];
		// point de vie du bateau
		int pv = Integer.parseInt(line[1]);
		// statut du bateau
		boolean statut = Boolean.parseBoolean(line[2]);
		// Position proue
		Position p1 = new Position(Integer.parseInt(line[3]), Integer.parseInt(line[4]));
		// Position poupe
		Position p2 = new Position(Integer.parseInt(line[5]), Integer.parseInt(line[6]));
		// nbMunitions
		int nbMunitions = Integer.parseInt(line[7]);
		return this.createCustomedShip(type, g, pv, statut, p1, p2, nbMunitions);
	}
	
	private boolean tirGrille(String[] line, Grille g) {
		Position p = new Position(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		return g.touche(p);
	}
}
