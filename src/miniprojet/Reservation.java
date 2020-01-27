/**
 * Cette classe permet de gerer des reservations de voitures avec les durees, et prix
 */

package miniprojet;

public class Reservation {
	public double prixLocation;
	public int dureeLocation; // en jour
	public Date debutLocation;
	public Date finLocation;
	public AgenceLocation agenceLoc;
	public Voiture voiture;

	public Reservation() {
		prixLocation = 0;
		dureeLocation = 0;
		debutLocation = new Date();
		finLocation = new Date();
		agenceLoc = null;
	}

	public Reservation(AgenceLocation agenceLoc) {
		this();
		this.agenceLoc = agenceLoc;
	}

	public void effectuerReservation() {
		boolean voitureConfirmee = false;

		// demande a l'utilisateur de choisir les dates de reservations
		setDateReservation();

		do {
			// montre la liste des voitures possibles sur ces dates a l'utilisateur
			montreVoitureDispo();

			// demande de choisir une voiture
			selectionVoiture();
			// recapitulatif
			afficherRecapitulatif();
			// confirmation
			voitureConfirmee = demandeConfirmation();
		} while (!voitureConfirmee);

		// mise a jour des donn�es dans le systeme
		actualiserInfos();
	}

	public void setDateReservation() {
		boolean dateImpossible = true;

		do {
			debutLocation.askDate("de debut de location");
			finLocation.askDate("de fin de location");
			if (finLocation.estPlusGrand(debutLocation)) {
				dateImpossible = false;
			} else {
				System.out.println("Erreur de date, veuillez r�essayer");
			}
		} while (dateImpossible);
		dureeLocation = debutLocation.nombreJours(finLocation);
	}

	private void montreVoitureDispo() {
		System.out.println("Les voitures suivantes sont disponibles :");
		agenceLoc.printAvailableCars();
	}

	private void selectionVoiture() {
		String idVoiture;

		System.out.println("Veuillez saisir le numero de la voiture que vous souhaitez reserver");
		idVoiture = InterfaceClient.scanStr();
		voiture = agenceLoc.parcVoiture.getCarsWithID(idVoiture);
		InterfaceClient.effaceConsole();

	}

	private void afficherRecapitulatif() {
		String message;

		message = "Vous souhaitez reserver la voiture n� " + voiture.idVoiture + " pour une p�riode de " + dureeLocation
				+ " jours";
		message = message + " au prix journalier de " + voiture.prixJournalier + " euros/jour \n";
		message = message + "pour un prix total de " + voiture.prixLocation(dureeLocation) + " euros.";
		System.out.println(message);
	}

	private boolean demandeConfirmation() {
		String reponse;
		boolean reponseInconnue = true;
		boolean resultat = false;

		do {
			System.out.println("Appuyer sur 'o' pour confirmer ou 'n' pour annuler");
			reponse = InterfaceClient.scanStr();
			if (reponse.equals("o")) {
				reponseInconnue = false;
				resultat = true;
			} else {
				if (reponse.equals("n")) {
					reponseInconnue = false;
					resultat = false;
				} else {
					System.out.println("R�ponse inconnue");
				}
			}
		} while (reponseInconnue);
		return resultat;
	}

	// Actualise les infos -> rend la voiture indispnible, et cr�� une transaction
	private void actualiserInfos() {
		// actualise le statut voiture
		voiture.dateDeRetour = finLocation;
		voiture.estEnLocation = true;

		// actualise les transactions
		this.prixLocation = voiture.prixLocation(dureeLocation);
		Transaction location = new Transaction("Location", TypeTransaction.Recette, (double) prixLocation);
		agenceLoc.addTransaction(location);
	}
}
