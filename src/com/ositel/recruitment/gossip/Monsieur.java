package com.ositel.recruitment.gossip;

public class Monsieur {
	public String nom;
	public Monsieur(String nom){
		this.nom = nom;
		
	}
	public Monsieur nomMonsieurDestination;
	public  String rumeurRecu ="", rumeurAenvoye="";
	
	public String getRumeurAenvoye() {
		return rumeurAenvoye;
	}

	public void setRumeurAenvoye(String rumeurAenvoye) {
		this.rumeurAenvoye = rumeurAenvoye;
	}

	public String getRumeurRecu() {
		return rumeurRecu;
	}

	public void setRumeurRecu(String rumeurRecu) {
		this.rumeurRecu = rumeurRecu;
	}

	public String demander(){
		return rumeurRecu;
	}

}
