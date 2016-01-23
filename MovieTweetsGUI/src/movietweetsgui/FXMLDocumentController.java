/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietweetsgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author yashm
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField movie;
    @FXML
    private TextArea TweetResults;
    
    @FXML
    private void handleButtonAction(ActionEvent event)throws TwitterException{
        String search = movie.getText();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("UB5R3HBBzlEiB8BS8x6LcxKuF")
          .setOAuthConsumerSecret("vjwCtWpd6Z9PMGdZ1chs4804loG0RkvWE1jcChe33DcVLqtH9h")
          .setOAuthAccessToken("1415796548-9RliCTkRL8tJard454LJcKixcFhl6QGk7YehS6X")
          .setOAuthAccessTokenSecret("csPiPp3lqF7AIYdjERIEldn4gPm4IrhkATG5MyazDgy9V");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try{
            Query query = new Query(search);
            query.setCount(100);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                TweetResults.setText("@" + status.getUser().getScreenName() + ": " + status.getText()+"\n"+TweetResults.getText());
            }
        }catch(TwitterException te){
            TweetResults.setText("Failed to get results");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
