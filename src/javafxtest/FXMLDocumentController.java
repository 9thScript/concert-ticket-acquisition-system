/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxtest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CC
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane signIn_form;
    @FXML
    private TextField signIn_username;
    @FXML
    private PasswordField signIn_password;
    @FXML
    private Button signInbtn;
    @FXML
    private Hyperlink signIn_noAccount;
    @FXML
    private Button signIn_close;
    @FXML
    private Button signIn_minimize;
    @FXML
    private AnchorPane signUp_form;
    @FXML
    private TextField signUp_email;
    @FXML
    private TextField signUp_username;
    @FXML
    private PasswordField signUp_password;
    @FXML
    private Button signUpbtn;
    @FXML
    private Hyperlink signUp_haveAccount;
    @FXML
    private Button signUp_close;
    @FXML
    private Button signUp_minimize;
    
    // TOOLS FOR DATABASE
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    public boolean validEmail() {
        
        Pattern pattern = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+");
        
        Matcher match = pattern.matcher(signUp_email.getText());
        
        Alert alert;
        
        if(match.find() && match.group().matches(signUp_email.getText())) {
            
            return true;
            
        }else {
            
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            
            return false;
        }
        
    }
    
    @FXML
    public void signUp() {

        String sql = "INSERT INTO user (emailAddress, username, password) VALUES (?,?,?)";
        String checkUsernameSql = "SELECT * FROM user WHERE username = ?";
        String checkEmailSql = "SELECT * FROM user WHERE emailAddress = ?";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(checkUsernameSql);
            prepare.setString(1, signUp_username.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                // Username already exists
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The username " + signUp_username.getText() + " already exists");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(checkEmailSql);
                prepare.setString(1, signUp_email.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    // Email address already exists
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("The email address " + signUp_email.getText() + " already exists");
                    alert.showAndWait();
                } else {
                    // Username and email address do not exist, proceed with user creation
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, signUp_email.getText());
                    prepare.setString(2, signUp_username.getText());
                    prepare.setString(3, signUp_password.getText());

                    prepare.execute();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Created an Account!");
                    alert.showAndWait();

                    // CLEAR THE TEXTFIELDS AFTER SUCCESSFULLY CREATING AN ACCOUNT
                    signUp_email.setText("");
                    signUp_username.setText("");
                    signUp_password.setText("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signUp_close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void signUp_minimize(ActionEvent event) {

        Stage stage = (Stage) signUp_form.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void signIn_close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void signIn_minimize(ActionEvent event) {

        Stage stage = (Stage) signIn_form.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == signIn_noAccount) {

            signIn_form.setVisible(false);
            signUp_form.setVisible(true);

        } else if (event.getSource() == signUp_haveAccount) {

            signIn_form.setVisible(true);
            signUp_form.setVisible(false);

        }

    }

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void signIn() {
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signIn_username.getText());
            prepare.setString(2, signIn_password.getText());

            result = prepare.executeQuery();

            Alert alert;

            //VALIDATION IF THE USERNAME OR PASSWORD TEXTFIELDS IS EMPTY
            if (signIn_username.getText().isEmpty() || signIn_password.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blank Fields");
                alert.showAndWait();

            } else {

                if (result.next()) {

                    getData.username = signIn_username.getText();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    //TO HIDE THE LOGIN FORM
                    signInbtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.DECORATED.UNDECORATED);

                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();

                        }

                    });

                    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            stage.setX(event.getScreenX() - xOffset);
                            stage.setY(event.getScreenY() - yOffset);

                        }

                    });
                
                    stage.setScene(scene);
                    stage.show();
                    
                } else {
                    
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username or Password");
                alert.showAndWait(); 
                
                }
                
            }
            
        }catch(Exception e) {e.printStackTrace();}
}  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    
    }
}