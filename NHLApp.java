/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhlassign;

/**
 *
 * @author Vaishnav Akavaram
 * @author Steven Kebila
 */
public class NHLApp {
    
    public static void main(String[] args) {
 
        NHLAnalyzer nhl = new NHLAnalyzer();
         
          System.out.println(nhl.getTeamWinningPercentage("Kings"));
          System.out.println(nhl.getTeamLosingPercentage("Kings"));
          System.out.println(nhl.getTeamWinningStreak("Kings"));
          System.out.println(nhl.getTeamLosingStreak("Kings"));
         
    }
    
}
