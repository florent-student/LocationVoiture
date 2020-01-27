/*
 * La classe date permet de stocker des dates, comprenant des jours,mois et ann�es
 *  cette classe est ensuite utilis� pour gerer les reservations de voitures
 * 
 * 
 */

package miniprojet;

public class Date {
	int jour;
	int mois; // numero du mois en cours
	int annee;
	private static final int nombreJourMois[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public Date() {

	}

	public Date(int jour, int mois, int annee) {
		this();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	/**
	 * Cette fonction demande a l'utilisateur de saisir au clavier une date et
	 * rempli l'objet date associe demande en argument un string qui pr�cise la
	 * nature de la date exemple: askDate("de naissance") ou askDate("de debut de
	 * location")
	 */
	public void askDate(String message) {

		String strDate;
		do {
			System.out.println("Saisir la date " + message + " au format jj/mm/aaaa");
			strDate = InterfaceClient.scanStr();
		} while (!verifFormatDate(strDate));
		rempliDate(strDate);
	}

	/**
	 * permet de verifier que la chaine de caractere possede bien la taille ad�quate
	 * pour un format de date de type jj/mm/aaaa, la longueur doit etre �gale � 10
	 * exemple : 15/09/2019 -> true ; 15/09/201 -> false
	 * 
	 * @param chaine a controler
	 * @return vrai si conforme , faux sinon
	 */
	private boolean verifTailleDate(String str) {
		if (str.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * permet de verifier que la chaine de caractere possede bien les "/" au bon
	 * endroit pour un format de date de type jj/mm/aaaa exemple : 15/09/2019 ->
	 * true ; 15/092/019 -> false
	 * 
	 * @param chaine a controler
	 * @return vrai si conforme , faux sinon
	 */
	private boolean verifSeparateur(String str) {
		if (str.charAt(2) == '/' && str.charAt(5) == '/') {
			return true;
		} else {
			return false;
		}
	}

	// version de isInteger(s, radix) en base 10
	private boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	/**
	 * renvoie true si la chaine est compos� uniquement de chiffres renvoie false si
	 * il y a d'autres caracteres On peut preciser avec l'argument radix la base des
	 * nombres 10 pour la base decimale, 2 binaire etc..
	 */
	private boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

	private boolean verifBorneAnnee(String str) {
		int intAnnee = Integer.parseInt(str);
		if (intAnnee >= 1900 && intAnnee <= 2100)
			return true;
		else
			return false;
	}

	private boolean verifBorneMois(String str) {
		int intMois = Integer.parseInt(str);
		if (intMois >= 1 && intMois <= 12)
			return true;
		else
			return false;
	}

	/**
	 * verifie que le journee est compris dans les bornes pr�d�finies
	 */
	private boolean verifBorneJour(String str, int mois) {
		// nombre de jour dans les mois de janvier fevrier etc ..

		if (mois <= 0 || mois > 12) {
			return false;
		}

		int intJour = Integer.parseInt(str); // convertie en entier
		if (intJour >= 1 && intJour <= nombreJourMois[mois - 1]) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * renvoie l'int associ� d'un string
	 */
	private int str2Int(String str) {
		return Integer.parseInt(str);
	}

	/**
	 * fonction qui v�rifie les conditions une a une pour le format de la date si
	 * toutes les conditions sont respect�s, renvoie true, sinon false
	 */

	private boolean verifFormatDate(String str) {
		boolean conforme = false; // par defaut faux devient vrai si respecte toutes les conditions
		String strJour = "";
		String strMois = "";
		String strAnnee = "";
		int numeroTest = 1; // on commence au test 1

		switch (numeroTest) {

		// condition 1 la date doit etre de longueur ad�quate
		case 1:
			if (verifTailleDate(str))
				numeroTest++;
			else {
				System.out.println("Erreur, la date doit contenir 10 caract�res");
				break;
			}
			// condition 2, les / doivent etre plac� au bon endroit dans la date
		case 2:
			if (verifSeparateur(str)) {
				numeroTest++;
				strJour = str.substring(0, 2);
				strMois = str.substring(3, 5);
				strAnnee = str.substring(6, 10);
			} else {
				System.out.println("Erreur, un '/' est attendu pour s�parer les entr�es");
				break;
			}

			// condition 3, l'ann�e correspond bien � un entier
		case 3:
			if (isInteger(strAnnee))
				numeroTest++;
			else {
				System.out.println("Erreur, l'ann�e n'est pas reconnue comme un entier");
				break;
			}

			// condition 4 la valeur de l'ann�e correspond a la plage possible
		case 4:
			if (verifBorneAnnee(strAnnee))
				numeroTest++;
			else {
				System.out.println("Erreur, l'ann�e n'est pas compris dans la plage autoris�e");
				break;
			}

			// condition 5, le mois correspond bien � un entier
		case 5:
			if (isInteger(strMois))
				numeroTest++;
			else {
				System.out.println("Erreur, le mois n'est pas reconnu comme un entier");
				break;
			}

			// condition 6 la valeur du mois correspond a la plage possible
		case 6:
			if (verifBorneMois(strMois))
				numeroTest++;
			else {
				System.out.println("Erreur, le mois n'est pas compris dans la plage autoris�e");
				break;
			}

			// condition 7 le jour correspoond bien a un entier
		case 7:
			if (isInteger(strJour))
				numeroTest++;
			else {
				System.out.println("Erreur, le jour n'est pas reconnu comme un entier");
				break;
			}

			// condition 8 la valeur du mois correspond a la plage possible
		case 8:
			int mois = str2Int(strMois);
			if (verifBorneJour(strJour, mois)) {
				conforme = true;
			} else {
				System.out.println("Erreur, le jour n'est pas compris dans la plage autoris�e");
				break;
			}
		}

		return conforme;
	}

	/**
	 * rempli l'objet date � partir de la chaine de caract�re saisi necessite
	 * d'avoir verifier le format de la date avant (grace a la fonction
	 * verifFormatDate)
	 * 
	 * @param str
	 */
	private void rempliDate(String str) {

		String strJour = str.substring(0, 2);
		String strMois = str.substring(3, 5);
		String strAnnee = str.substring(6, 10);

		this.jour = Integer.parseInt(strJour);
		this.mois = Integer.parseInt(strMois);
		this.annee = Integer.parseInt(strAnnee);
	}

//	 /**
//	  * fonction qui compare deux dates et renvoie true si elles sont identiques
//	*/
//	public boolean equals(Date date2) {
//		boolean resultat = true;
//		if(this.annee != date2.annee) {
//			resultat = false;
//		}
//			else {
//				if(this.mois != date2.mois) {
//					resultat = false;
//				}
//					else {
//						if(this.jour != date2.jour) {
//							resultat = false;
//						}
//					}
//			}
//		return resultat;
//	}

	/**
	 * fonction qui compare une date a une date2 et renvoie true si ma date est plus
	 * grande que date2 ex: 15/02/2019 est superieur � 31/01/2019 si les dates sont
	 * identiques, renvoie faux
	 */
	public boolean estPlusGrand(Date date2) {

		if (this.annee != date2.annee) {
			return this.annee > date2.annee;
		} else {
			if (this.mois != date2.mois) {
				return this.mois > date2.mois;
			} else {
				return this.jour > date2.jour;
			}
		}
	}

	/**
	 * renvoie le nombre de jour s�parant cette date � la date2 le nombre de jour
	 * est positif si date2 est plus grand et negatif sinon
	 * 
	 * @param date2
	 * @return le nombre de jour
	 */
	public int nombreJours(Date date2) {
		int resultat;
		int differenceAnnee;
		int differenceJour;
		boolean positif;
		Date debut;
		Date fin;

		if (this.estPlusGrand(date2)) {
			debut = date2;
			fin = this;
			positif = false;
		} else {
			debut = this;
			fin = date2;
			positif = true;
		}

		differenceAnnee = (fin.annee - debut.annee) * 365;
		differenceJour = fin.nombreJourDebutAnnee() - debut.nombreJourDebutAnnee();
		resultat = differenceAnnee + differenceJour;

		if (positif) {
		} else {
			resultat = (-1) * resultat;
		}
		return resultat;
	}

	/**
	 * calcule le nombre de jour s�parant cette date du d�but de l'ann�e (1 janvier
	 * de l'ann�e en cours)
	 * 
	 * @return le nombre de jour en int
	 */
	private int nombreJourDebutAnnee() {
		int resultat;
		int debutMois;
		int debutAnnee = 0;

		debutMois = this.jour - 1;
		for (int i = 0; i < this.mois - 1; i++) {
			debutAnnee = debutAnnee + nombreJourMois[i];
		}
		resultat = debutMois + debutAnnee;
		return resultat;
	}

	/**
	 * Renvoie la date en format String
	 */
	public String toString() {
		String resultat;
		resultat = Integer.toString(this.jour) + "/" + Integer.toString(this.mois) + "/" + Integer.toString(this.annee);
		return resultat;
	}
}
