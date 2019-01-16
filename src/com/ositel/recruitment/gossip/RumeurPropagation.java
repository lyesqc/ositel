package com.ositel.recruitment.gossip;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class RumeurPropagation {

    @Test
    public void etrePropageParUnMonsieur() {

        Rumeurs rumeurs = new Rumeurs("Mr White", "Mr Black", "Mr Blue")
                .source("White").destination("Black")
                .source("Black").destination("Blue");

        rumeurs.dire("Hello").destination("White");

        assertThat(rumeurs.demander("White")).isEqualTo("Hello");

        rumeurs.propager();

        assertThat(rumeurs.demander("White")).isEqualTo("");
        assertThat(rumeurs.demander("Black")).isEqualTo("Hello");

        rumeurs.propager();
        System.out.println("------");

        assertThat(rumeurs.demander("Black")).isEqualTo("");
        assertThat(rumeurs.demander("Blue")).isEqualTo("Hello");

    }

    @Test 
    public void etreRetenuSiDestinationADejaUneRumeur() {

        Rumeurs rumeurs = new Rumeurs("Mr White", "Mr Black", "Mr Blue")
                .source("White").destination("Black")
                .source("Blue").destination("Black");

        rumeurs.dire("Hello").destination("White");
        rumeurs.dire("Secret").destination("Blue");

        assertThat(rumeurs.demander("White")).isEqualTo("Hello");
        assertThat(rumeurs.demander("Blue")).isEqualTo("Secret");

        rumeurs.propager();

        assertThat(rumeurs.demander("White")).isEqualTo("");
        assertThat(rumeurs.demander("Black")).isEqualTo("Hello");
        assertThat(rumeurs.demander("Blue")).isEqualTo("Secret");

        rumeurs.propager();

        assertThat(rumeurs.demander("White")).isEqualTo("");
        assertThat(rumeurs.demander("Black")).isEqualTo("Secret");
        assertThat(rumeurs.demander("Blue")).isEqualTo("");

    }

    @Test 
    public void etreRapelleParLeMedecin() {

        Rumeurs rumeurs = new Rumeurs("Mr White", "Mr Black", "Dr Brown", "Mr Pink")
                .source("White").destination("Brown")
                .source("Black").destination("Brown")
                .source("Brown").destination("Pink");


        rumeurs.dire("Hello").destination("White");
        rumeurs.dire("ByeBye").destination("Black");

        rumeurs.propager();

        assertThat(rumeurs.demander("Brown")).isEqualTo("Hello");
        assertThat(rumeurs.demander("Pink")).isEqualTo("");

        rumeurs.propager();

        assertThat(rumeurs.demander("Brown")).isEqualTo("Hello, ByeBye");
        assertThat(rumeurs.demander("Pink")).isEqualTo("Hello");

        rumeurs.propager();

        assertThat(rumeurs.demander("Brown")).isEqualTo("Hello, ByeBye");
        assertThat(rumeurs.demander("Pink")).isEqualTo("ByeBye");

    }

}
