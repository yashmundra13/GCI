/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worlddistancecalculator;

import static java.lang.Math.atan2;
import static java.lang.Math.floor;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import static java.lang.Math.toRadians;

/**
 *
 * @author yashm
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField lat1;
    @FXML
    private TextField lat2;
    @FXML
    private ChoiceBox<String> ew2;
    @FXML
    private TextField lon1;
    @FXML
    private TextField lon2;
    @FXML
    private Label dist;
    @FXML
    private Label time;
    @FXML
    private ChoiceBox<String> ns2;
    @FXML
    private ChoiceBox<String> ns1;
    @FXML
    private ChoiceBox<String> ew1;
    
    @FXML
    void calculate(ActionEvent event) {
//Getting Value from main functions.
        // Rounding value of distance.
        double distance = round(haversine());
        double td = timediff();
//Converting time difference into minutes and hours and rounding it..
        double th = floor((td/60));
        double tm = round(((td/60) - (th))*60);
//Printing out these values.
        dist.setText("Distance: "+distance+" km");
        time.setText(th+" Hours "+tm+" Minutes");  
        
    }
    public double r = 6371;//Approximate value for earth's radius.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ns1.getSelectionModel().selectFirst();
        ns2.getSelectionModel().selectFirst();
        ew1.getSelectionModel().selectFirst();
        ew2.getSelectionModel().selectFirst();
        // TODO
    }  
//Function for distance.  
    public double haversine(){
//Getting latitudes and longitudes from text fields.
        double lat_one = Double.parseDouble(lat1.getText());
        double lat_two = Double.parseDouble(lat2.getText());
        double lon_one = Double.parseDouble(lon1.getText());
        double lon_two = Double.parseDouble(lon2.getText());
//Conditionals to check long distance or short distance calculation.
        if(ns1.getValue().equals(ns2.getValue())){
            lat_one = -lat_one;
        }
        if(ew1.getValue().equals(ew2.getValue())){
            lon_one = -lon_one;
        }
//Finding difference in coordinates and converting them to radians.
        double dlat = toRadians(lat_two - lat_one);
        double dlon = toRadians(lon_two - lon_one);
//Applying Haversine formula. This assumes earth is a perfect sphere.
        double a = (sin(dlat/2)*sin(dlat/2))+
                cos(toRadians(lat_one))*cos(toRadians(lat_two))*
                sin(dlon/2)*sin(dlon/2);
        double b = 2*atan2(sqrt(a),sqrt(1-a));
        double c = b*r;
        return c;
    }
//Function for time difference.
    public double timediff(){
        double dl;
//Getting values from text fields.
        double lon_one = Double.parseDouble(lon1.getText());
        double lon_two = Double.parseDouble(lon2.getText());
//Conditionals to see wether longitudes are on same side of earth.
        if(ew1.getValue().equals(ew2.getValue())){
            dl = lon_one - lon_two;
            
        }else {
            dl = lon_one + lon_two;
            
        }
        
        if(dl<0){
            dl = -dl;
        }//Converting any possible negative time difference to positive value.
        double td = dl*4;//Assuming that there is an exact time difference for one longitude.
        //Returning value for time difference.
        return td;
    }
}
