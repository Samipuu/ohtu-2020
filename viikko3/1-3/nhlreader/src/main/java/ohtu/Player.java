
package ohtu;

import java.util.Comparator;

public class Player {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;
    private int points;

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setNationality(String nat) {
        this.nationality = nat;
    }
    
    public String getNationality() {
        return this.nationality;
    }
    
    public void setPoints() {
        this.points = this.goals + this.assists;
    }
    
    public int getPoints() {
        return this.points;
    }
    
   

    @Override
    public String toString() {
        return name;
    }
      
}
