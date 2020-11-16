package eduspace.controller;

import javafx.event.*;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFormateurController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField datenaiss;

    @FXML
    private TextField telephone;

    @FXML
    private TextField cin;

    @FXML
    private TextField email;

    @FXML
    private TextField zip;

    @FXML
    private ComboBox poste;

    @FXML
    private CheckBox compte;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @Override
    public void initialize(URL event, ResourceBundle rb) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    public void submitAdd(ActionEvent actionEvent) {
    }
}
