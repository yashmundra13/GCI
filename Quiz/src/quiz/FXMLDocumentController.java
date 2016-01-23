/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author yashm
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML
    private Label SC;
    @FXML
    private Label SW;
    @FXML
    private Button START;
    @FXML
    private Button QUIT;
    @FXML
    private Button A1;
    @FXML
    private Button A2;
    @FXML
    private Button A3;
    @FXML
    private Button A4;
    @FXML
    private Label QUESTION;
    
    String[] questions;
    int counter = 0;
    int score = 0;
    problems[] probList = new problems[10];
    
    public void loadProblems(){
    probList[0] = new problems("q","a1","a2","a3","a4","A");
    probList[1] = new problems("Which of these is a free and open source software","Windows","Linux","Mac OS","iOS","B");
    probList[2] = new problems("Which was the first commercial company to put its software in Open Source?","IBM","Microsoft","Netscape","Google","C");
    probList[3] = new problems("What organization defines Open Source rules?","The Open Source Institute","The Open Source Board","The Open Source Committee","The Open Source Initiative","D");
    probList[4] = new problems("MySql, Samba and Perl are Open Source software.","True","","False","","A");
    probList[5] = new problems("Which of the following are open source softwares?","Open Office","Microsoft Office","Spread Sheet","Document Viewer","A");
    probList[6] = new problems("Who created Linux?","Steve Jobs","Dennis Ritchie","Bill Gates","Linus Torvald","D");
    probList[7] = new problems("Who created the Free Software Foundation?","Linux Torvalds","Eric Raymond","Richard Stallman","Steve Wozniak","C");
    probList[8] = new problems("Which of the following is not a pro to using open source software?","Custmization","Low Cost","East to integrate with proprietary software","Programmers have access to code","C");
    probList[9] = new problems("Programmers that work on Open Source projects are attracted financial profits.","False","","True","","A");
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startgame(ActionEvent event) throws IOException{
        Stage stage = (Stage) START.getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLQUIZ.fxml"));    
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exitgame(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    private void nextQuestion(ActionEvent event) throws IOException{
        loadProblems();
        if(event.getSource()==A1){
            CheckAns("A");
            
        }else if (event.getSource()==A2){
            CheckAns("B");
            
        }else if (event.getSource()==A3){
            CheckAns("C");
            
        }else if(event.getSource()==A4){
            CheckAns("D");
            
        }
    }
    private void CheckAns(String guess) throws IOException{
        String correct = probList[counter].getAns();
        if (correct.equals(guess)){
            score++;
            counter++;
            updateScreen();
        }else{
            counter++;
            updateScreen();
        }
    }

    private void updateScreen() throws IOException{
        if(counter <= 9){
        QUESTION.setText(probList[counter].getquest());
        A1.setText(probList[counter].getAns1());
        A2.setText(probList[counter].getAns2());
        A3.setText(probList[counter].getAns3());
        A4.setText(probList[counter].getAns4());
        }else{
            QUESTION.setText("Your Score is "+score+"/10");
            A1.setText("");
            A4.setText("");
            A3.setText("");
            A2.setText("");
        }
    }
    
    
}
class problems{
    private final String question;
    private final String answer;
    private final String ans1;
    private final String ans2;
    private final String ans3;
    private final String ans4;
    
    public problems(String q, String a , String b, String c, String d, String ans) {
        question = q;
        ans1 = a;
        ans2 =b;
        ans3 = c;
        ans4 = d;
        answer = ans;
    }
    public String getquest(){
        return question;
    }
    public String getAns1(){
        return ans1;
    }
    public String getAns2(){
        return ans2;
    }
    public String getAns3(){
        return ans3;
    }
    public String getAns4(){
        return ans4;
    }
    public String getAns(){
        return answer;
    }
}