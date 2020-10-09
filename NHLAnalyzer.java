/*
* Assignment 3: NHL WebPage Grabber 
*/
package nhlassign;

import webgrab.WebPageGrabber;

/**
 *
 * @author Vaishnav Akavaram
 * @author Steven Kebila
 */
public class NHLAnalyzer{
    private String page; //contains information from webpage www.foxsports.com/nhl/standings regarding NHL teams standings
    
    /**
     *This constructor initializes the page which contains the HTML code from the website https://www.foxsports.com/nhl/standings
     */
    public NHLAnalyzer(){
       WebPageGrabber nhl = new WebPageGrabber();
       page = nhl.getPage();
    }
    
    /**
     *
     * This method searches and parses data from the NHL webpage.
     * @param s
     * @return array containing winning percentage, losing percentage, winning streak,
     * and losing streak of a specified NHL team from the webpage.
     */
    public int[] search(String s){   
        String [] lines = page.split("/>");
        int win; int lose; int wStreak; int lStreak;
        int[] n = new int[4];
        
        for (String line : lines) {
            if (line.contains("<span>" + s)) {
                String[] lines2 = line.split("</td>", 31);

                win = Integer.parseInt(lines2[1].substring(lines2[1].indexOf(">")+1));
                lose = Integer.parseInt(lines2[2].substring(lines2[2].indexOf(">")+1));

                //System.out.println(win);
                //System.out.println(lose);

                n[0] = win;
                n[1] = lose;
                if(lines2[15].charAt(lines2[15].indexOf(">")+1) == 'W'){
                    wStreak = Integer.parseInt(lines2[15].substring(lines2[15].indexOf(">")+2));
                    n[2] = wStreak;
                }
                else{
                    lStreak = Integer.parseInt(lines2[15].substring(lines2[15].indexOf(">")+2));
                    n[3] = lStreak;
                }
                return n;
            }
        }
        return n;
    }
    
    /**
     *This method returns the winning percentage of a particular hockey team specified by the parameter
     * @param s
     * @return double value of a team's winning percentage
     */
    public double getTeamWinningPercentage(String s){
        int[] n2 = search(s);
        return (double)n2[0]/(n2[0]+n2[1]) * 100;
    }
   
    /**
     *This method returns the losing percentage of a particular hockey team specified by the parameter
     * @param s
     * @return double value of a team's losing percentage
     */
    public double getTeamLosingPercentage(String s){
       return 100 - getTeamWinningPercentage(s);
    }
    
    /**
     *This method returns the number of games consecutively won by a particular hockey team specified by the parameter
     * @param s
     * @return integer value of a team's winning streak
     */
    public int getTeamWinningStreak(String s){
        int[] n2 = search(s);
        return n2[2];
    }
  
    /**
     *This method returns the number of games consecutively lost by a particular hockey team specified by the parameter
     * @param s
     * @return integer value of a team's losing streak
     */
    public int getTeamLosingStreak(String s){
        int[] n2 = search(s);
        return n2[3];
    }

    @Override
    public String toString() {
        return  page;
    }
   
    
    
}


