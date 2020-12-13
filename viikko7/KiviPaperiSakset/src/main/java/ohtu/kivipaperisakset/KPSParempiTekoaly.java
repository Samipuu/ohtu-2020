package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSTemplate {
    private TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        tekoaly.asetaSiirto(siirto);
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}
