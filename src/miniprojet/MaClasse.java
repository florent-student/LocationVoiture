package miniprojet;

public class MaClasse {

	public static void main(String[] args) {

		// creation de l'agence
		AgenceLocation monAgence = new AgenceLocation();
		loadDataBase(monAgence);
		InterfaceClient monInterface = new InterfaceClient(monAgence);
		monInterface.principal();

	}

	private static void loadDataBase(AgenceLocation monAgence) {
		// creation et ajout du parc voiture
		for (int i = 0; i < 20; i++) {
			createCar(monAgence);
		}

		// creation et ajout des clients
		/*
		 * Client paul = new Client("dupont","paul",25); Client henri = new
		 * Client("thomas","henri",35); Client jules = new Client("martin","jules",42);
		 * Client herve = new Client("bernard","herve",28); Client alex = new
		 * Client("petit","alex",29);
		 * 
		 * monAgence.addClient(paul); monAgence.addClient(henri);
		 * monAgence.addClient(jules); monAgence.addClient(herve);
		 * monAgence.addClient(alex);
		 */

		// creation et ajout de transactions
		Transaction transaction1 = new Transaction("paiement du client", TypeTransaction.valueOf("Recette"), 3000.0);
		Transaction transaction2 = new Transaction("loyer", TypeTransaction.valueOf("Depense"), 500.0);
		Transaction transaction3 = new Transaction("salari�s", TypeTransaction.valueOf("Depense"), 1000.0);
		Transaction transaction4 = new Transaction("imp�ts", TypeTransaction.valueOf("Depense"), 1000.0);

		monAgence.addTransaction(transaction1);
		monAgence.addTransaction(transaction2);
		monAgence.addTransaction(transaction3);
		monAgence.addTransaction(transaction4);
	}

	static void createCar(AgenceLocation monAgence) {
		Voiture voiture = new Voiture();
		monAgence.addVoiture(voiture);
	}

}
