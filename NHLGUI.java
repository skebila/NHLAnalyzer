
package nhlassign;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Vaishnav Akavaram
 * @author Steven Kebila
 */

public class NHLGUI extends Application {
    
    StackPane rootPane, childPaneSPane;
    GridPane childPaneGPane, childPaneGPane2;
    VBox childPaneVBx, childPaneVBx2;
    HBox childPaneHBx;
    NHLAnalyzer nhl;
    String[] database;
    
    public NHLGUI(){
        rootPane = new StackPane();
        childPaneSPane = new StackPane();
        childPaneGPane = new GridPane();
        childPaneGPane2 = new GridPane();
        childPaneVBx = new VBox();
        childPaneHBx = new HBox();
        childPaneVBx2 = new VBox();
        nhl = new NHLAnalyzer();
    }
    
    @Override
    public void start(Stage primaryStage) {
        Button signIn = new Button("Sign in");
        Button signUp = new Button("Sign up");       
        Text mbrTxt = new Text("MEMBER?");
        Text nMbrTxt = new Text("NOT A MEMBER?");
     
        childPaneVBx.getChildren().addAll(mbrTxt, signIn, nMbrTxt, signUp);

        childPaneVBx.setAlignment(Pos.CENTER);
        childPaneVBx.setSpacing(20);
        
        signIn.setOnAction(e->{signIn();});
        
        signUp.setOnAction(e->{signUp();});
                
        TextField userName = new TextField();
        PasswordField passWord = new PasswordField();
        Button lgnBttn = new Button("Login");
        Label acntLbl1 = new Label("");

        userName.setPromptText("Username");
        passWord.setPromptText("Password");
                        
        childPaneGPane.add(userName, 0, 0);
        childPaneGPane.add(passWord, 0, 1);
        childPaneGPane.add(lgnBttn, 0, 2);
        childPaneGPane.add(acntLbl1, 0, 5);
        
        childPaneGPane.setVgap(5);
        
        lgnBttn.setOnAction(e->{login(userName, passWord, acntLbl1);});
        
        childPaneGPane.setAlignment(Pos.CENTER);
        childPaneGPane.setVisible(false);

        Label nameLbl = new Label("Name");
        TextField nameTxt = new TextField();
        Label userName2Lbl = new Label("Username");
        TextField userName2 = new TextField();
        Label passWord2Lbl = new Label("Password");
        PasswordField passWord2 = new PasswordField();
        Button createAcnt = new Button("Create");
        Button resetAcnt = new Button("Reset");
        Label acntLbl = new Label("");

        childPaneGPane2.add(nameLbl,0, 0);
        childPaneGPane2.add(nameTxt, 0, 1);
        childPaneGPane2.add(userName2Lbl, 0, 2);
        childPaneGPane2.add(userName2, 0, 3);
        childPaneGPane2.add(passWord2Lbl, 0, 4);
        childPaneGPane2.add(passWord2, 0, 5);
        childPaneGPane2.add(acntLbl, 0, 8);
        
        childPaneHBx.getChildren().addAll(createAcnt, resetAcnt);

        childPaneGPane2.add(childPaneHBx, 0, 6);
        
        childPaneHBx.setSpacing(3);
        childPaneGPane2.setVgap(5);
        
        createAcnt.setOnAction(e->{create(nameTxt, userName2, passWord2, acntLbl);});
        
        childPaneGPane2.setAlignment(Pos.CENTER);
        childPaneGPane2.setVisible(false);
   
        Label teamLbl = new Label("Select a team");

        Label methodLbl = new Label("Select a stat");
        ArrayList<String> method = new ArrayList();
            method.add("Winning Percentage");
            method.add("Losing Percentage");
            method.add("Winning Streak");
            method.add("Losing Streak");
        
        NhlTeams nhlteam = new NhlTeams();     
        ObservableList<String> teamOptions = FXCollections.observableArrayList(nhlteam.getTeams());
        ObservableList<String> methodOptions = FXCollections.observableArrayList(method);
        
        ComboBox cBx1 = new ComboBox(teamOptions); 
        ComboBox cBx2 = new ComboBox(methodOptions); 
        
        cBx1.getSelectionModel().selectFirst();
        cBx2.getSelectionModel().selectFirst();        
        
        Button showStat = new Button("Show");
        Button goBack = new Button("Back");
        
        showStat.setPrefSize(50, 30);
        
        Label outputInfo = new Label("");
        
        outputInfo.setStyle("-fx-font-size: 30px; -fx-text-fill: red");
        
        childPaneVBx2.getChildren().addAll(teamLbl, cBx1, methodLbl, cBx2, showStat, goBack, outputInfo);
        
        showStat.setOnAction(e->{output(cBx1, cBx2, outputInfo);});
        goBack.setOnAction(e->{back();});
       
        childPaneSPane.getChildren().addAll(childPaneVBx2);
        childPaneVBx2.setAlignment(Pos.CENTER);
       
        childPaneVBx2.setSpacing(15);
        childPaneSPane.setVisible(false);
        Image image = new Image("pics/NHL.png");
        rootPane.getChildren().addAll(childPaneVBx, childPaneGPane, childPaneGPane2, childPaneSPane);
        
        Scene scene = new Scene(rootPane, 500, 400);
        childPaneGPane.setStyle("-fx-background-color: crimson");
        childPaneGPane2.setStyle("-fx-background-color: chartreuse ");
        childPaneSPane.setStyle("-fx-background-color: springgreen ");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("NHL Analyzer");
        primaryStage.setScene(scene);
        resetAcnt.setOnAction((javafx.event.ActionEvent e) -> {
                nameTxt.clear();
                userName2.clear();
                passWord2.clear();
        });
        primaryStage.show();
    }
    
    public void signIn(){
       childPaneVBx.setVisible(false);  
       childPaneGPane.setVisible(true);    //Sign in pane
    }
    
    public void signUp(){
       childPaneVBx.setVisible(false);
       childPaneGPane2.setVisible(true);  //Sign up pane
    }
    
    public void create(TextField name, TextField user, PasswordField pass, Label acntLbl){
   
        if(!name.getText().isEmpty() && !user.getText().isEmpty() && !pass.getText().isEmpty()){
            System.out.println("Account created");
            acntLbl.setText("Account created");
                     
//            database[1] = name.getText();
//            database[2] = user.getText();
//            database[3] = pass.getText();
            
            childPaneGPane.setVisible(false); 
            childPaneSPane.setVisible(true);       
        }
        else{
            acntLbl.setText("Please fill out all the fields");
            System.out.println("Please fill out all the fields");
        }  
    }
    
    public void login(TextField userName, PasswordField passWord, Label acnt){
     if(!userName.getText().isEmpty() && !passWord.getText().isEmpty()){
       childPaneGPane.setVisible(false);
       childPaneSPane.setVisible(true);}
     else{
         acnt.setText("Missing username or password");
      }
    }
    
    public void output(ComboBox team, ComboBox method, Label info){
        
        if(method.getValue().equals("Winning Percentage")){
            info.setText("" + nhl.getTeamWinningPercentage(team.getValue().toString()));
            System.out.println("Winning Percentage: " + nhl.getTeamWinningPercentage(team.getValue().toString()));
        }
            
        else if(method.getValue().equals("Losing Percentage")){
            info.setText("" + nhl.getTeamLosingPercentage(team.getValue().toString()));
            System.out.println("Losing Percentage: " + nhl.getTeamLosingPercentage(team.getValue().toString()));
        }

        else if(method.getValue().equals("Winning Streak")){
            info.setText("" + nhl.getTeamWinningStreak(team.getValue().toString()));
            System.out.println("Winning Streak: " + nhl.getTeamWinningStreak(team.getValue().toString()));
        }
            
        else{
            info.setText("" + nhl.getTeamLosingStreak(team.getValue().toString()));
            System.out.println("Losing Streak: " + nhl.getTeamLosingStreak(team.getValue().toString()));
        }
    }
    
    
    private void back() {
        rootPane.setVisible(true); childPaneGPane.setVisible(false); childPaneGPane2.setVisible(false);
        childPaneVBx.setVisible(true); childPaneSPane.setVisible(false);    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
