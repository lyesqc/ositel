package com.ositel.recruitment.gossip;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Rumeurs {
	String direRumeur = "";
	String sourceName;
	Monsieur monsieur;
	private LinkedHashMap<String, Monsieur> listMonsieur;

	public  Rumeurs(String... args) {
		listMonsieur = new LinkedHashMap<String, Monsieur>();
		for (String monsieurTitre : args) {
			if (monsieurTitre.startsWith("Mr"))
				monsieur = new Monsieur(monsieurTitre.split(" ")[1]);
			else
				monsieur = new Docteur(monsieurTitre.split(" ")[1]);
			listMonsieur.put(monsieur.nom, monsieur);
			
		}
		int iArray[] = new int[] {1,2,3,4,5};
	}

	public  Rumeurs  source(String sourceName) {
		this.sourceName = sourceName;
		return this;
	}

	public Rumeurs destination(String destinationName) {
		if (!sourceName.isEmpty()) {
			((Monsieur) listMonsieur.get(sourceName)).nomMonsieurDestination = (Monsieur) listMonsieur
					.get(destinationName);
			System.out.println("source " + sourceName + ", dest " + destinationName);
			sourceName = "";
		} else {
			((Monsieur) listMonsieur.get(destinationName)).rumeurRecu = direRumeur;
			direRumeur = null;
		}
		return this;
	}

	protected Rumeurs dire(String direRumeur) {
		this.direRumeur = direRumeur;
		System.out.println("in dire " + direRumeur);
		return this;
	}

	protected String demander(String nomMonsiuer) {

		return ((Monsieur) listMonsieur.get(nomMonsiuer)).demander();

	}

	protected void propager() {
		Monsieur currentMonsieur;
		Iterator it, it1;

		it = listMonsieur.entrySet().iterator();
		it1 = listMonsieur.entrySet().iterator();
		Entry<String, Monsieur> myentry;

		/*
		 * deplacer les derniers rumeur recue depuis le buffer de reception
		 * (rumeurRecu)au buffer d'envoie (rumeurAenvoye)
		 */

		while (it.hasNext()) {
			myentry = (Entry) it.next();
			currentMonsieur = myentry.getValue();
			currentMonsieur.setRumeurAenvoye(currentMonsieur.getRumeurRecu());
			currentMonsieur.setRumeurRecu("");
		}

		/*
		 * envoie la rumeur au monsier suivant, a condition que le monsieur
		 * suivant n'a pas encore reçue de rumeur
		 */

		while (it1.hasNext()) {
			myentry = (Entry) it1.next();
			currentMonsieur = myentry.getValue();

			/*
			 * verifier si le monsieur source a une destination, si oui envoie
			 * la rumeur a condition que la destination n'a pas encore recue de
			 * rumeur (en vérifiant le buffeur de reception : rumeurRecu)
			 */

			if (currentMonsieur.nomMonsieurDestination != null) {

				if (currentMonsieur.getRumeurAenvoye() != "") {
					/*
					 * 1. verifier si monsieur destination a déja recue une
					 * rumeur ou pas 2. si il n'a pas encore recue une rumeur,
					 * alors envoie la rumeur au buffer de reception de monsieur
					 * destination 3. si le monsieur destination a déja recue
					 * une rumeur, alors remettre la rumeur au buffuer de
					 * reception du monsieur source
					 * 
					 */
					if (currentMonsieur.nomMonsieurDestination.getRumeurRecu() == "") {

						currentMonsieur.nomMonsieurDestination.setRumeurRecu(currentMonsieur.getRumeurAenvoye());
						currentMonsieur.setRumeurAenvoye("");
					} else {
						currentMonsieur.setRumeurRecu(currentMonsieur.getRumeurAenvoye());
						currentMonsieur.setRumeurAenvoye("");
					}
				}

			}

		}

	}

}
