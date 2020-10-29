/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author suonpaas
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            
            players.add(new Player("Sementko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void pelaajaLoytyy() {
        Player pelaaja = stats.search("Kurri");
        assertEquals("Kurri", pelaaja.getName());
    }
    
    @Test
    public void pelaajaEiLoydy() {
        Player pelaaja = stats.search("Pöö");
        assertEquals(null, pelaaja);
    }
    
    @Test
    public void joukkueLoytyy() {
        List<Player> joukkue = stats.team("EDM");
        assertEquals(3, joukkue.size(), 0.0);
    }
    
    @Test
    public void joukkuettaEiLoydy() {
        List<Player> joukkue = stats.team("ANA");
        assertEquals(0, joukkue.size(), 0.0);
    }
    
    @Test
    public void parhaatPistemiehet() {
        List<Player> pelaajat = stats.topScorers(3);
        assertEquals(pelaajat.get(0).getName(),"Gretzky");
    }
    
}
