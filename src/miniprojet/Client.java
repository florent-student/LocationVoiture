package miniprojet;

import java.util.ArrayList;

public class Client {
	public static long idCounter = 0;
	public String idClient;
	public String nom;
	public String prenom;
	public Date dateNaissance;
	public ArrayList<Voiture> mesVoituresLouees = new ArrayList<Voiture>();

	public Client() {
		this.idClient = createID();
		this.nom = "";
		this.prenom = "";
	}

	public Client(String nom, String prenom, Date dateNaissance) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public static synchronized String createID() {
		idCounter++;
		return String.valueOf(idCounter);
	}

	public void validerLocationVoiture(Voiture voiture, Reservation contrat) {
		voiture.estEnLocation = true;
		voiture.monLocataireActuel = this;
		mesVoituresLouees.add(voiture);

	}

	/**
	 * fonction qui demande a l'utilisateur de renseigner les champs pour un nouveau
	 * client
	 * 
	 * @return le client nouvellement cr��
	 */
	public static Client createClient() {
		String prenom;
		String nom;
		Date dateNaissance;

		System.out.println("Saisir votre prenom");
		prenom = InterfaceClient.scanStr();
		System.out.println("Saisir votre nom");
		nom = InterfaceClient.scanStr();
		dateNaissance = new Date();
		dateNaissance.askDate("de naissance");

		Client client = new Client(nom, prenom, dateNaissance);
		return client;
	}

	/**
	 * compare deux clients pour savoir si ils sont identiques
	 * 
	 * @param client � comparer
	 * @return vrai si identique, faux sinon
	 */
	public boolean isEqual(Client client) {
		boolean identique = true;
		if (!this.nom.equals(client.nom))
			identique = false;
		if (!this.prenom.equals(client.prenom))
			identique = false;
		if (!this.dateNaissance.equals(client.dateNaissance))
			identique = false;

		return identique;
	}
}