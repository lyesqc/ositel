package com.ositel.recruitment.gossip;




public class Docteur extends Monsieur{

	public Docteur(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	String toutesRumeurRecue ="";
	public String demander(){
		
		return toutesRumeurRecue;
	}
	
	public void setRumeurRecu(String rumeurRecue){
		super.setRumeurRecu(rumeurRecue);
		
		if(this.toutesRumeurRecue !="" && rumeurRecue!="") 
			this.toutesRumeurRecue = toutesRumeurRecue+", "+rumeurRecue;
		else 
			if(this.toutesRumeurRecue.equals(""))
				this.toutesRumeurRecue = rumeurRecue;
	}
	

}
