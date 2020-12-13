package ohtu.kivipaperisakset;


public class KPSTekoaly extends KPSTemplate{

    private Tekoaly tekoaly = new Tekoaly();
    
    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        tekoaly.asetaSiirto(siirto);
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}