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

public class ConsultationAbsenceController implements Initializable {

    @FXML
    private AnchorPane body;

    @FXML
    private Button save;

    @FXML
    private TableView table;

    @FXML
    private TableColumn cin;

    @FXML
    private TableColumn nom;

    @FXML
    private TableColumn poste;

    @FXML
    private TableColumn p;

    @Override
    public void initialize(URL event, ResourceBundle rb) {
    }
}
