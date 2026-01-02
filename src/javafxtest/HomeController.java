/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxtest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.util.HashMap;
import java.util.Map;

/**
 * FXML Controller class
 *
 * @author CC
 */
public class HomeController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button signOut_home;
    @FXML
    private AnchorPane topForm;
    @FXML
    private Label displayName;
    @FXML
    private AnchorPane homeForm;
    @FXML
    private Button buyTicketsBtn_eraserheads;
    @FXML
    private AnchorPane eraserheadsForm;
    @FXML
    private AnchorPane vipSeatingA_eraserheadsSeats;
    @FXML
    private Label seatsSelectedLabel;
    @FXML
    private Label totalAmountLabel;
    @FXML
    private Button vipSeatingAbtn_eraserheads;
    @FXML
    private CheckBox VSA2;
    @FXML
    private CheckBox VSA11;
    @FXML
    private CheckBox VSA21;
    @FXML
    private CheckBox VSA12;
    @FXML
    private CheckBox VSA22;
    @FXML
    private CheckBox VSA23;
    @FXML
    private CheckBox VSA13;
    @FXML
    private CheckBox VSA3;
    @FXML
    private CheckBox VSA24;
    @FXML
    private CheckBox VSA14;
    @FXML
    private CheckBox VSA4;
    @FXML
    private CheckBox VSA25;
    @FXML
    private CheckBox VSA15;
    @FXML
    private CheckBox VSA5;
    @FXML
    private CheckBox VSA26;
    @FXML
    private CheckBox VSA16;
    @FXML
    private CheckBox VSA6;
    @FXML
    private CheckBox VSA27;
    @FXML
    private CheckBox VSA17;
    @FXML
    private CheckBox VSA7;
    @FXML
    private CheckBox VSA28;
    @FXML
    private CheckBox VSA18;
    @FXML
    private CheckBox VSA8;
    @FXML
    private CheckBox VSA29;
    @FXML
    private CheckBox VSA19;
    @FXML
    private CheckBox VSA9;
    @FXML
    private CheckBox VSA30;
    @FXML
    private CheckBox VSA20;
    @FXML
    private CheckBox VSA10;
    @FXML
    private CheckBox VSA1;
    @FXML
    private AnchorPane paymentForm;
    @FXML
    private TextField firstName_tf;
    @FXML
    private TextField lastName_tf;
    @FXML
    private TextField age_tf;
    @FXML
    private TextField cardNumber_tf;
    @FXML
    private TextField cvcCode_tf;
    @FXML
    private Button confirmseatsBtn_VSA;
    @FXML
    private Button confirmPurchaseBtn;
    @FXML
    private Label type;
    @FXML
    private Label price;
    @FXML
    private Label quantity;
    @FXML
    private AnchorPane purchaseCompleteForm;
    @FXML
    private Label selectedCheckboxesLabel;
    @FXML
    private AnchorPane vipSeatingB_eraserheadsSeats;
    @FXML
    private Button vipSeatingBbtn_eraserheads;
    @FXML
    private AnchorPane lodge_eraserheadsSeats;
    @FXML
    private Button lodgeBtn_eraserheads;
    @FXML
    private Button balconyBtn_eraserheads;
    @FXML
    private AnchorPane balcony_eraserheadsSeats;
    @FXML
    private Button backButtonForBalcony;
    @FXML
    private Button backButtonForVIPA;
    @FXML
    private Button backButtonForVIPB;
    @FXML
    private Button backButtonForLodge;
    @FXML
    private Button generateReceiptBtn;
    
    private void updateLabels() {
        int count = 0;
        int total = 0;
        StringBuilder selectedCheckboxes = new StringBuilder();

        CheckBox[] vsaCheckboxes = {VSA1, VSA2, VSA3, VSA4, VSA5, VSA6, VSA7, VSA8, VSA9, VSA10,
            VSA11, VSA12, VSA13, VSA14, VSA15, VSA16, VSA17, VSA18, VSA19, VSA20,
            VSA21, VSA22, VSA23, VSA24, VSA25, VSA26, VSA27, VSA28, VSA29, VSA30};

        for (CheckBox checkbox : vsaCheckboxes) {
            if (checkbox.isSelected()) {
                count++;
                total += getPrice("Option 1");
                selectedCheckboxes.append(checkbox.getId()).append(", ");
            }
        }

        if (selectedCheckboxes.length() > 0) {
            selectedCheckboxes.delete(selectedCheckboxes.length() - 2, selectedCheckboxes.length()); // Remove the last comma and space
        }

        seatsSelectedLabel.setText(String.valueOf(count));
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        totalAmountLabel.setText("₱ " + numberFormat.format(total));

        // Set the selected checkboxes on the label
        selectedCheckboxesLabel.setText(selectedCheckboxes.toString());
    }

    private int getPrice(String option) {
        if (option.equals("Option 1")) {
            int price = 8275;
            return price;
        } else {
            return 0;
        }
    }
    
    private Map<String, Boolean> checkboxDisabledStates = new HashMap<>();

    @FXML
    public void checkBoxVSA(ActionEvent event) {
        CheckBox[] vsaCheckboxes = {VSA1, VSA2, VSA3, VSA4, VSA5, VSA6, VSA7, VSA8, VSA9, VSA10,
            VSA11, VSA12, VSA13, VSA14, VSA15, VSA16, VSA17, VSA18, VSA19, VSA20,
            VSA21, VSA22, VSA23, VSA24, VSA25, VSA26, VSA27, VSA28, VSA29, VSA30};

        for (CheckBox checkbox : vsaCheckboxes) {
            checkbox.selectedProperty().addListener((obs, oldValue, newValue) -> {
                if (!checkbox.isDisabled()) {
                    updateLabels();
                }
            });

            // Store the initial disabled state of the checkbox in the HashMap
            checkboxDisabledStates.put(checkbox.getId(), checkbox.isDisabled());
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {
        
        if(event.getSource() == homeBtn) {
            
            homeForm.setVisible(true);
            eraserheadsForm.setVisible(false);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == buyTicketsBtn_eraserheads) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(true);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == vipSeatingAbtn_eraserheads) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(false);
            vipSeatingA_eraserheadsSeats.setVisible(true);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == vipSeatingBbtn_eraserheads) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(false);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(true);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
        
        } else if(event.getSource() == lodgeBtn_eraserheads) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(false);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(true);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == balconyBtn_eraserheads) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(false);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(true);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == backButtonForVIPA) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(true);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
        
        } else if(event.getSource() == backButtonForVIPB) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(true);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
        
        } else if(event.getSource() == backButtonForLodge) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(true);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == backButtonForBalcony) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(true);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(false);
            purchaseCompleteForm.setVisible(false);
            
        } else if(event.getSource() == confirmseatsBtn_VSA) {
            
            homeForm.setVisible(false);
            eraserheadsForm.setVisible(false);
            vipSeatingA_eraserheadsSeats.setVisible(false);
            vipSeatingB_eraserheadsSeats.setVisible(false);
            lodge_eraserheadsSeats.setVisible(false);
            balcony_eraserheadsSeats.setVisible(false);
            paymentForm.setVisible(true);
            purchaseCompleteForm.setVisible(false);
            type.setText("VIP SEATING A");
            String seats = seatsSelectedLabel.getText();
            quantity.setText(seats + " X " + " VIP SEATING A " + " ₱ 8,275");
            String totalAmount = totalAmountLabel.getText();
            price.setText(totalAmount);
            
        } else if (event.getSource() == confirmPurchaseBtn) {

            Alert alert;

            CheckBox[] vsaCheckboxes = {VSA1, VSA2, VSA3, VSA4, VSA5, VSA6, VSA7, VSA8, VSA9, VSA10,
                VSA11, VSA12, VSA13, VSA14, VSA15, VSA16, VSA17, VSA18, VSA19, VSA20,
                VSA21, VSA22, VSA23, VSA24, VSA25, VSA26, VSA27, VSA28, VSA29, VSA30};

            for (CheckBox checkbox : vsaCheckboxes) {
                checkbox.setDisable(checkbox.isSelected() || checkbox.isDisabled());

                // Update the disabled state in the checkboxDisabledStates HashMap
                checkboxDisabledStates.put(checkbox.getId(), checkbox.isDisabled());
            }

            if (firstName_tf.getText().isEmpty() || lastName_tf.getText().isEmpty() || age_tf.getText().isEmpty() || cardNumber_tf.getText().isEmpty() || cvcCode_tf.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blank Fields");
                alert.showAndWait();

            } else if (!cardNumber_tf.getText().matches("\\d{16}")) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid card number. Please enter 16 digits.");
                alert.showAndWait();

            } else if (!cvcCode_tf.getText().matches("\\d{3}")) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid CVC code. Please enter 3 digits.");
                alert.showAndWait();

            } else {
                // Process the form data
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Transaction Complete");
                alert.showAndWait();
                paymentForm.setVisible(false);
                purchaseCompleteForm.setVisible(true);
                
            }
        
        }
        if (event.getSource() == generateReceiptBtn) {
            
            String firstName = firstName_tf.getText();
            String lastName = lastName_tf.getText();
            String age = age_tf.getText();
            String type = "VIP SEATING A";
            String quantity = seatsSelectedLabel.getText();
            String amount = totalAmountLabel.getText();
            String price = "₱ 8,275";
            String seats = selectedCheckboxesLabel.getText();
            
            try {
                // Load the receipt.fxml file and create a new stage
                FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));
                Parent root = loader.load();
                Stage receiptStage = new Stage();

                // Set the ReceiptController as the controller for the loaded FXML
                ReceiptController receiptController = loader.getController();

                // Set the receipt data
                receiptController.setReceiptData(firstName, lastName, age, type, quantity, amount, price, seats);

                // Create a new scene and set it in the stage
                Scene scene = new Scene(root);
                receiptStage.setScene(scene);
                receiptStage.setTitle("Receipt");
                receiptStage.show();
            } catch (Exception e) {
                e.printStackTrace();

            }
            
            // CLEAR THE TEXTFIELDS AFTER SUCCESSFULL PURCHASE
                firstName_tf.setText("");
                lastName_tf.setText("");
                age_tf.setText("");
                cardNumber_tf.setText("");
                cvcCode_tf.setText("");

                clearCheckboxSelections();
                seatsSelectedLabel.setText("0");
                totalAmountLabel.setText("₱ 0");
            
        }

    }

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    public void logout() {

        signOut_home.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayUsername() {
       
        displayName.setText("HI! " + getData.username.toUpperCase());
        
    }
    
    private void clearCheckboxSelections() {
        
        VSA1.setSelected(false);
        VSA2.setSelected(false);
        VSA3.setSelected(false);
        VSA4.setSelected(false);
        VSA5.setSelected(false);
        VSA6.setSelected(false);
        VSA7.setSelected(false);
        VSA8.setSelected(false);
        VSA9.setSelected(false);
        VSA10.setSelected(false);
        VSA11.setSelected(false);
        VSA12.setSelected(false);
        VSA13.setSelected(false);
        VSA14.setSelected(false);
        VSA15.setSelected(false);
        VSA16.setSelected(false);
        VSA17.setSelected(false);
        VSA18.setSelected(false);
        VSA19.setSelected(false);
        VSA20.setSelected(false);
        VSA21.setSelected(false);
        VSA22.setSelected(false);
        VSA23.setSelected(false);
        VSA24.setSelected(false);
        VSA25.setSelected(false);
        VSA26.setSelected(false);
        VSA27.setSelected(false);
        VSA28.setSelected(false);
        VSA29.setSelected(false);
        VSA30.setSelected(false);

    }
    
    @FXML
    public void close() {
        
        System.exit(0);
        
    }
    
    @FXML
    public void minimize() {
        
        Stage stage = (Stage)topForm.getScene().getWindow();
        stage.setIconified(true);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
        displayUsername();
        
    }    
  
}