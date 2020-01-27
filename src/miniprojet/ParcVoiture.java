package miniprojet;

import java.util.ArrayList;

public class ParcVoiture {
	public ArrayList<Voiture> listeVoiture = new ArrayList<Voiture>(); // liste des voitures

	/**
	 * ajoute la voiture a la liste
	 * 
	 * @param la voiture � ajouter
	 */
	public void add(Voiture voiture) {
		listeVoiture.add(voiture);
	}

	/**
	 * affiche la liste des voitures ainsi que des informations annexes
	 * 
	 * @param format
	 */
	public void print(String format) {
		for (Voiture voiture : listeVoiture) {
			voiture.print(format);
		}
	}

	/**
	 * renvoie une liste des voitures disponible
	 * 
	 * @return
	 */
	public ParcVoiture getAvailableCars() {
		ParcVoiture resultat = new ParcVoiture();
		for (Voiture voiture : listeVoiture) {
			if (!voiture.estEnLocation) {
				resultat.add(voiture);
			}
		}
		return resultat;
	}

	/**
	 * renvoie la liste des voitures qui ont moins de X kilometres a faire avant la
	 * maintenance
	 * 
	 * @param seuilKilometrage
	 * @return la liste des voitures qui ont besoin d'une maintenance
	 */
	public ParcVoiture getCarsThatNeedMaintenance(int seuilKilometrage) {
		ParcVoiture resultat = new ParcVoiture();
		for (Voiture voiture : listeVoiture) {
			if (voiture.kmPourVidange < 500) {
				resultat.add(voiture);
			}
		}
		return resultat;
	}

	/**
	 * renvoie l'objet voiture qui correspond a l'identifiant fourni
	 * 
	 * @param id
	 * @return
	 */
	public Voiture getCarsWithID(String id) {
		Voiture resultat = null;

		for (Voiture voiture : listeVoiture) {
			if (voiture.idVoiture.equals(id)) {
				resultat = voiture;
			}
		}
		return resultat;
	}

}
