/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        /*        
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
        */
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        List<Player> organizedFIN = new ArrayList<Player>();
        System.out.println("Oliot:");
        
        for (Player player : players) {
            if(player.getNationality().equals("FIN")) {
                player.setPoints();
                organizedFIN.add(player);                
            } 
        }
        
        Comparator<Player> compareByPoints = (Player o1, Player o2) -> {
            if(o1.getPoints() == o2.getPoints()) {
                return o2.getGoals() - o1.getGoals();
            } else {
                return o2.getPoints() - o1.getPoints();
            }
        };

        Collections.sort(organizedFIN, compareByPoints);
        
        
        for(Player player : organizedFIN) {
            System.out.println(player.getName() + " " + player.getTeam() + " " + player.getGoals() + " + " + player.getAssists() + " = "  + player.getPoints());
        }
    }
  
}
