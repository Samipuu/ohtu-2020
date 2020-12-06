/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author suonpaas
 */
public class QueryBuilder {
    Matcher matchers;
    
    public QueryBuilder() {
        matchers = new All();
        
    }
    
    public Matcher build() {
        return this.matchers;
    }
    
    public QueryBuilder playsIn(String team) {
        matchers = new And(new PlaysIn(team));
        
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        matchers = new And(matchers, new HasAtLeast(value, category));
        return this;
    } 
    
    public QueryBuilder hasFewerThan(int value, String category) {
        matchers = new And(matchers, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher first, Matcher second) {
        matchers = new And(new Or(first, second));
        return this;
    }
}
