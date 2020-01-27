package miniprojet;

import java.util.ArrayList;

//import java.text.NumberFormat;
//import java.text.DecimalFormat;

// l'agence de location possede un parc de client, de voiture et de contrats

public class AgenceLocation {
	public ArrayList<Client> listeClient = new ArrayList<Client>(); // liste des clients
	public ParcVoiture parcVoiture = new ParcVoiture(); // liste des voitures
	public ArrayList<Reservation> listeContrat = new ArrayList<Reservation>(); // liste des contrats
	public ArrayList<Transaction> listeTransactions = new ArrayList<Transaction>(); // liste des transactions

	public void addVoiture(Voiture voiture) {
		parcVoiture.add(voiture);
	}

	public void addClient(Client client) {
		listeClient.add(client);
	}

	public void addTransaction(Transaction transaction) {
		listeTransactions.add(transaction);
	}

	/**
	 * renvoie true si un client est dans la liste client, false sinon
	 */
	public boolean clientExiste(Client monClient) {
		boolean resultat = false;
		for (Client client : listeClient)
			if (client.isEqual(monClient)) {
				resultat = true;
				break;
			}
		return resultat;
	}

	/**
	 * recherche un client avec son nom prenom age et permet de retourner l'objet de
	 * la liste client associ�
	 * 
	 * @param le client qu'on recherche avec nom prenom age
	 * @return le vrai objet qui contient toute les informations
	 */
	public Client getCorrespondingClient(Client monClient) {
		Client resultat = null;
		for (Client client : listeClient)
			if (client.isEqual(monClient)) {
				resultat = client;
				break;
			}
		return resultat;
	}

	/**
	 * fonction qui permet d'afficher un parc voiture pass� en argument
	 * 
	 * @param parc voiture a afficher
	 */
	public void printListOfCars(ParcVoiture parc) {
		final String formatTexte = "%-15s %-25s %-20s %-15s %-25s %n";
		final String formatValeur = "%-15s %,-25.0f %-20s %-15s %,-25.0f %n";
		String champ1 = "Numero";
		String champ2 = "Prix journalier";
		String champ3 = "Marque";
		String champ4 = "Disponible";
		String champ5 = "Kilom�tres avant vidange";

		// affiche l'entete
		System.out.printf(formatTexte, champ1, champ2, champ3, champ4, champ5);
		// afiche le contenu
		parc.print(formatValeur);

	}

	/**
	 * affiche toutes les voitures de l'agence
	 */
	public void printAllCars() {
		printListOfCars(parcVoiture);
	}

	/**
	 * affiche toutes les voitures disponible de l'agence
	 */
	public void printAvailableCars() {
		printListOfCars(parcVoiture.getAvailableCars());
	}

	/**
	 * affiche toutes les voitures de l'agence ayant besoin d'une maintenance
	 */
	public void printMaintenanceCars() {
		int seuilKm = 500; // seuil de kilometre ou l'on consid�ere que la maintenance est urgente
		printListOfCars(parcVoiture.getCarsThatNeedMaintenance(seuilKm));
	}

	/*
	 * Affiche dans le terminal la liste des voitures ainsi que leurs attributs
	 * (kilometrage etc..)
	 * 
	 * public void showListOfCars() { final String format =
	 * "%-15s %-25s %-20s %-15s %-15s %n"; String champ1 = "Numero"; String champ2 =
	 * "Kilometrage"; String champ3 = "Immatriculation"; String champ4 =
	 * "Disponible"; String champ5 = "Kilom�tres avant vidange"; NumberFormat
	 * formatter = new DecimalFormat("#0.0"); ArrayList<Voiture> voituresAVidanger =
	 * new ArrayList<Voiture>();
	 * 
	 * 
	 * System.out.printf(format, champ1,champ2,champ3,champ4,champ5);
	 * 
	 * for(Voiture voiture : listeVoiture) { voiture.print(format); if
	 * (voiture.kmPourVidange == 0) { voituresAVidanger.add(voiture); }
	 * 
	 * } if (voituresAVidanger.isEmpty()) {
	 * System.out.printf("Aucune voiture n'a besoin de vidange.\n"); } else { String
	 * ids = ""; int i = 0; for (Voiture voiture : voituresAVidanger) { ids +=
	 * voiture.idVoiture;
	 * 
	 * if (i < voituresAVidanger.size() - 1) { ids += ", "; } ++i; } if
	 * (voituresAVidanger.size() == 1) { System.out.println("La voiture " + ids +
	 * " n�cessite vidange."); } else { System.out.println("Les voitures " + ids +
	 * " n�cessitent vidange."); } } }
	 */

	public void showListOfTransactions() {
		String format;
		Double totalRecettes = 0.0;
		Double totalDepenses = 0.0;

		format = "%-40s %-15s %-15s%n";
		System.out.printf(format, "", "DEPENSES", "RECETTES");

		format = "%-40s %-15s %-15s\n";
		for (Transaction transaction : listeTransactions) {
			if (transaction.type == TypeTransaction.Recette) {
				System.out.printf(format, transaction.name, "", transaction.value);
				totalRecettes += transaction.value;
			} else {
				System.out.printf(format, transaction.name, transaction.value, "");
				totalDepenses += transaction.value;
			}
		}

		format = "%-40s %-15s %-15s %-15s\n";
		System.out.printf(format, "Total", totalDepenses, totalRecettes, totalRecettes - totalDepenses);
	}

}
