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
    public class Erotus extends Komento {

        
        public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
            this.sovellus = sovellus;
            this.tuloskentta = tuloskentta;
            this.syotekentta = syotekentta;
            this.nollaa = nollaa;
            this.undo = undo;
        }

        @Override
        public void suorita() {
            edellinen = sovellus.tulos();
            sovellus.miinus(Integer.valueOf(syotekentta.getText()));
            tuloskentta.setText(""+sovellus.tulos());
            nollaa.setDisable(false);
        }

        @Override
        public void peru() {
            sovellus.nollaa();
            sovellus.plus(edellinen);
            tuloskentta.setText(""+edellinen);
        }
    }
