/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author suonpaas
 */




    public class Nollaa extends Komento {

        
        public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
            this.sovellus = sovellus;
            this.tuloskentta = tuloskentta;
            this.syotekentta = syotekentta;
            this.nollaa = nollaa;
            this.undo = undo;
        }

        @Override
        public void suorita() {
            edellinen = sovellus.tulos();
            sovellus.nollaa();
            tuloskentta.setText(""+sovellus.tulos());
            syotekentta.setText("");
            nollaa.setDisable(true);
        }

    @Override
        public void peru() {
            sovellus.nollaa();
            sovellus.plus(edellinen);
            tuloskentta.setText(""+edellinen);
        }
    }