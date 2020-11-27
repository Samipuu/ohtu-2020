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
public abstract class Komento {
    Sovelluslogiikka sovellus;
    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    int edellinen;
    
    public abstract void peru();
    public abstract void suorita();
}
