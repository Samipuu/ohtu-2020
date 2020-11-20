package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        
        varasto = mock(Varasto.class);
        
        k = new Kauppa(varasto, pankki, viite);   
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        
        
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
    }
    
    @Test
    public void ostaEriTuotteitaJoitaVarastossa() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Olut", 10));
        
        k.lisaaKoriin(2);
        k.tilimaksu("sami", "98765");
        
        verify(pankki).tilisiirto(eq("sami"), anyInt(), eq("98765"), anyString(), eq(15));
        
    }
    
    @Test
    public void ostaSamaaTuotettaUseampiKappaleOnVarastossa() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Olut", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        
        k.tilimaksu("sami", "98765");
        
        verify(pankki).tilisiirto(eq("sami"), anyInt(), eq("98765"), anyString(), eq(20));
    }
    
    @Test
    public void ostaTuoteJotaOnJaTuoteJotaEiOle() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Olut", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));
        
        k.tilimaksu("sami", "98765");
        
        verify(pankki).tilisiirto(eq("sami"), anyInt(), eq("98765"), anyString(), eq(10));
        
        
    }
    
    @Test
    public void aloitaAsiointiNollaaOstoskorin() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Olut", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        
        k.tilimaksu("sami", "98765");
        
        verify(pankki).tilisiirto(eq("sami"), anyInt(), eq("98765"), anyString(), eq(0));
        
    }
    
    @Test
    public void kauppaPyytääUudenViiteNumeron() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Olut", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("sami", "98765");
        
        verify(viite, times(1)).uusi();
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(viite, times(2)).uusi();
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Matti", "56789");
        
        verify(viite, times(3)).uusi();
    }
    
    @Test
    public void tuotePalaaOstoskoristaVarastoon() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Olut", 10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        
        k.poistaKorista(1);
        
        k.tilimaksu("sami", "98765");
        
        verify(pankki).tilisiirto(eq("sami"), anyInt(), anyString(), anyString(), eq(0));
        
    }
}

