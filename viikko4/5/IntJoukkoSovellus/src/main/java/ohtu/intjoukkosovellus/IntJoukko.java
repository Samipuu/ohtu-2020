
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alusta(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alusta(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alusta(kapasiteetti, kasvatuskoko);
    }
    
    private void alusta(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        lukuJoukko = new int[kapasiteetti];
        for (int i = 0; i < lukuJoukko.length; i++) {
            lukuJoukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukuJoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukuJoukko.length == 0) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }
    
    private void kasvataTaulukkoa() {
        int[] taulukkoOld = new int[lukuJoukko.length];
        taulukkoOld = lukuJoukko;
        lukuJoukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukuJoukko);
    }

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                on++;
            }
        }
        if (on > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int sijainti = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                sijainti = i; //siis luku löytyy tuosta kohdasta :D
                lukuJoukko[sijainti] = 0;
                break;
            }
        }
        if (sijainti != -1) {
            for (int j = sijainti; j < alkioidenLkm - 1; j++) {
                apu = lukuJoukko[j];
                lukuJoukko[j] = lukuJoukko[j + 1];
                lukuJoukko[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }


        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukuJoukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukuJoukko[i];
                tuotos += ", ";
            }
            tuotos += lukuJoukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJoukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }
 
        return erotus;
    }
        
}
