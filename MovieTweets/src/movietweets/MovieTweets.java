/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietweets;
import twitter4j.*;
import java.util.Scanner;
/**
 *
 * @author yashm
 */
public class MovieTweets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a name of a movie.");
        String search = scan.nextLine();
        try{
            Twitter twitter = TwitterFactory.getSingleton();
            Query query = new Query(search);
            query.setCount(100);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        }catch(TwitterException te){
            System.out.println("Failed to get resulsts for "+search);
        }
        // TODO code application logic here
    }
    
}
