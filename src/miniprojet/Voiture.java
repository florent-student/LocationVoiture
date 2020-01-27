package miniprojet;

import java.util.Locale;
import java.util.Random;

public class Voiture {
	private static long idCounter = 0;
	public String idVoiture;
	public double kilometrage;
	public double kmPourVidange;
	public String immatriculation;
	public Marque marque;
	public double prixJournalier;
	public Date dateDeRetour;
	public boolean estEnLocation; // true si la voiture est louee, false si disponible
	public Client monLocataireActuel; // le client qui utilise actuellement la voiture
	private static Random random;

	// creation par defaut incluant des criteres aleatoire
	public Voiture() {
		random = new Random();
		idVoiture = createID();
		kilometrage = 0;
		immatriculation = AssignARandomNumberplate();
		estEnLocation = random.nextBoolean();
		kmPourVidange = Math.random() * 5000;
		kilometrage = Math.random() * 200000;
		AssignARandomBrand();
		AssignDailyPrice();
	}

	public Voiture(double kilometrage, String immatriculation, boolean estEnLocation) {
		this();
		this.kilometrage = kilometrage;
		this.immatriculation = immatriculation;
		this.estEnLocation = estEnLocation;
	}

	/**
	 * attribue au hasard une immatriculation � la voiture
	 */
	private String AssignARandomNumberplate() {

		return generateRandomCharacter(3, false) + generateRandomCharacter(3, false)
				+ generateRandomCharacter(3, false);
	}

	/**
	 * g�nere une string de taille X caract�res qui peut etre des chiffres ou des
	 * majuscules
	 * 
	 * @param number taille de la string
	 * @param value  true si chiffre; false si lettre
	 * @return la string form�
	 */
	private String generateRandomCharacter(int number, boolean numeric) {
		int plage = 0;
		char debut = ' ';
		String result = "";

		// si on veut des chiffres
		if (numeric) {
			plage = 10; // il y a 10 chiffres
			debut = '0'; // on comme � 0
		}
		// si on veut des lettres
		else {
			plage = 26; // il y a 26 lettres
			debut = 'A'; // on commence � A
		}

		for (int i = 0; i < number; i++) {
			char c = (char) (random.nextInt(plage) + debut);
			result = result + c;
		}
		return result;
	}

	/**
	 * renvoie une marque de voiture al�atoire
	 */
	private static Marque RandomBrand() {
		int pick = random.nextInt(Marque.values().length);
		return Marque.values()[pick];
	}

	/**
	 * attribue au hasard une marque � la voiture
	 */
	private void AssignARandomBrand() {
		this.marque = RandomBrand();
	}

	/**
	 * attribue le prix de la voiture en fonction de la marque (en euros)
	 * 
	 */
	private void AssignDailyPrice() {
		switch (this.marque) {

		case Ford:
			prixJournalier = 50;
			break;

		case Renault:
			prixJournalier = 40;
			break;

		case Peugeot:
			prixJournalier = 50;
			break;

		case Ferrari:
			prixJournalier = 180;
			break;

		case Toyota:
			prixJournalier = 70;
			break;

		case Nissan:
			prixJournalier = 60;
			break;

		case Opel:
			prixJournalier = 55;
			break;

		case Citroen:
			prixJournalier = 45;
			break;

		case Audi:
			prixJournalier = 100;
			break;
		}
	}

	public static synchronized String createID() {
		idCounter++;
		return String.valueOf(idCounter);
	}

	/**
	 * fonction qui renvoie une chaine de caract�re contenant "Oui" ou "Non" selon
	 * que la voiture soit disponible pour un pret ou pas
	 * 
	 * @return String Oui / Non
	 */
	public String estDisponible() {
		String resultat;
		if (estEnLocation) {
			resultat = "Non";
		}

		else {
			resultat = "Oui";
		}
		return resultat;
	}

	/**
	 * affiche les informations de la voiture sur une ligne selon un format sp�cifi�
	 * 
	 * @param format
	 */
	public void print(String format) {
		// ITALY pour avoir les separateurs entre les milliers
		System.out.printf(Locale.ITALY, format, this.idVoiture, this.prixJournalier,
				this.marque, this.estDisponible(), this.kmPourVidange);
	}

	/**
	 * affiche avec un format par d�faut
	 */
	public void printDefaultFormat() {
		final String format = "%-15s %-15s %-20s %-15s %,-25.0f %n";
		// %,-25.0f pour le kilometrage
		print(format);
	}

	/**
	 * renvoie le prix de la location en fonction du nombre de jour
	 * 
	 * @param nombreJour
	 * @return prix
	 */
	public double prixLocation(int nombreJour) {
		return (nombreJour * this.prixJournalier);
	}

}
