/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxtest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CC
 */
public class ReceiptController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Label receiptType;
    @FXML
    private Label receiptQuantity;
    @FXML
    private Label receiptLast;
    @FXML
    private Label receiptFirst;
    @FXML
    private Label receiptAge;
    @FXML
    private Label receiptPrice;
    @FXML
    private AnchorPane receiptTopForm;
    @FXML
    private Label receiptAmount;
    @FXML
    private Label receiptSeats;

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) receiptTopForm.getScene().getWindow();
        stage.setIconified(true);
    }

    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage) receiptTopForm.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void setReceiptData(String firstName, String lastName, String age, String type, String quantity, String amount, String price, String seats) {
        receiptFirst.setText(firstName);
        receiptLast.setText(lastName);
        receiptAge.setText(age);
        receiptType.setText(type);
        receiptQuantity.setText(quantity);
        receiptAmount.setText(amount);
        receiptPrice.setText(price);
        receiptSeats.setText(seats);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        receiptTopForm.setOnMousePressed(this::handleMousePressed);
        receiptTopForm.setOnMouseDragged(this::handleMouseDragged);
    }
}