package miniprojet;

import java.util.Scanner;

public class InterfaceClient {
	Client currentClient = null;
	AgenceLocation currentAgence = null;
	static Scanner sc = new Scanner(System.in);

	public InterfaceClient(AgenceLocation agence) {
		currentAgence = agence;
	}

	public void principal() {
		effaceConsole();
		bienvenue();
		identification();
		effaceConsole();
		menuPrincipal();

	}

	public void bienvenue() {
		System.out.println("Bonjour et bienvenue dans notre logiciel de gestion de location de voitures");
	}

	public void connexion(Client client) {
		this.currentClient = client;
	}

	/**
	 * demande a l'utilisateur si il veut creer compte client car il n'existe pas
	 * dans la liste client
	 * 
	 * @param client le compte � creer
	 * @return vrai si il faut le creer, faux sinon
	 */
	public boolean demandeCreationCompte(Client client) {
		String reponse;
		boolean reponseInconnue = true;
		boolean resultat = false;

		do {
			System.out.println("Le compte recherch� n'existe pas, voulez vous le creer ? o pour Oui et n pour Non");
			reponse = scanStr();
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

	/**
	 * Fonction qui demande a l'utilisateur de s'identifier si son compte n'existe
	 * pas, propose de lui creer un compte
	 */
	public void identification() {
		Client client;
		boolean pasIdentifie = true;
		boolean exists;
		boolean reponse;

		do {
			System.out.println("Identification :");
			client = Client.createClient();
			exists = this.currentAgence.clientExiste(client);

			// Si le client existe deja on s'identifie
			if (exists) {
				// permet d'utiliser l'objet client deja existant au lieu de celui cr�� pour
				// l'identifcation
				client = currentAgence.getCorrespondingClient(client);
				connexion(client);
				pasIdentifie = false;
			}

			// Si le client n'existe pas on demande si on veut creer le compte
			else {
				reponse = demandeCreationCompte(client);

				// si reponse positive on creer le compte et on se connecte
				if (reponse) {
					currentAgence.addClient(client);
					connexion(client);
					pasIdentifie = false;
				}

				// si reponse negative, on revient a l'identification
				else {
					pasIdentifie = true;
				}

			}
		} while (pasIdentifie);
	}

	/**
	 * renvoie un message incluant le nom pr�nom de l'utilisateur qui est affich� en
	 * entete des menus
	 * 
	 * @return le message de type String
	 */
	public String messageEntete() {
		String message = "Vous �tes connect� en tant que " + currentClient.prenom + " " + currentClient.nom;
		return message;
	}

	public static String scanStr() {
		String resultat;

		resultat = sc.nextLine();
		return resultat;
	}

	public static char scanChar() {
		char resultat;

		resultat = sc.next().charAt(0);
		return resultat;
	}

	/**
	 * permet de sauter des lignes pour que la console soit plus lisible
	 */
	public static void effaceConsole() {
		for (int i = 0; i < 15; i++) {
			System.out.println(" \n");
		}
	}

	/**
	 * l'utilisateur doit appuyer sur une touche avant que le programme continue
	 */
	private void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	/**
	 * menu principal du programme, permet de choisir des actions a faire
	 */
	public void menuPrincipal() {
		String reponse;

		// le bloc suivant permet de changer les numeros des actions
		final String reserver = "1"; // reserver
		final String voirToutesVoitures = "2"; // voir la liste des voitures
		final String voirVoitureDispo = "3"; // voir la liste des voitures disponibles
		final String voirVoituresMaintenance = "4"; // voir la liste des voitures
		final String voirToutesTransaction = "5"; // voir la liste des transactions
		final String seConnecter = "6"; // se connecter avec un autre compte

		while (true) {
			System.out.println(messageEntete());
			System.out.println("Choisissez une action � effectuer");
			System.out.println(reserver + " - R�server une voiture");
			System.out.println(voirToutesVoitures + " - Voir la liste des voitures");
			System.out.println(voirVoitureDispo + " - Voir la liste des voitures disponibles");
			System.out
					.println(voirVoituresMaintenance + " - Voir la liste des voitures ayant besoin d'une maintenance");
			System.out.println(voirToutesTransaction + " - Voir la liste des transactions");
			System.out.println(seConnecter + " - Se connecter avec un autre compte");

			reponse = InterfaceClient.scanStr();
			effaceConsole();

			switch (reponse) {
			case reserver:
				reservation();
				pressAnyKeyToContinue();
				effaceConsole();
				break;

			case voirToutesVoitures:
				currentAgence.printAllCars();
				pressAnyKeyToContinue();
				effaceConsole();
				break;

			case voirToutesTransaction:
				currentAgence.showListOfTransactions();
				pressAnyKeyToContinue();
				effaceConsole();
				break;

			case seConnecter:
				identification();
				effaceConsole();
				break;

			case voirVoitureDispo:
				currentAgence.printAvailableCars();
				pressAnyKeyToContinue();
				effaceConsole();

			case voirVoituresMaintenance:
				currentAgence.printMaintenanceCars();
				pressAnyKeyToContinue();
				effaceConsole();

			default:
				System.out.println("Action non reconnue, veuillez r�essayer");
			}
		}
	}

	private void reservation() {
		Reservation reservation = new Reservation(currentAgence);
		reservation.effectuerReservation();
	}
}
