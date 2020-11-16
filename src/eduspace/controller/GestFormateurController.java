package eduspace.controller;

import eduspace.model.Formateur;
import eduspace.model.departement;
import eduspace.model.employe;
import eduspace.vendor.AlertBox;
import eduspace.vendor.DatabaseConn;
import eduspace.vendor.Routing;
import eduspace.vendor.popup;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GestFormateurController {

    public TextField nom;
    public TextField prenom;
    public DatePicker addDateNaiss;
    public TextField addCin;
    public TextField addEmail;
    public TextField addTel;
    public TextField addAdr;
    public TextField addMatiere;
    public TextField addZip;
    public ChoiceBox addGender;

    public TableColumn cin;
    public TableColumn Nom;
    public TableColumn Prenom;
    public TableColumn specialite;
    public TableView table;
    @FXML
    private AnchorPane body;


    public void initialize() throws SQLException {
        if (body.getChildren().contains(addGender)) {
            addGender.getItems().clear();
            addGender.getItems().addAll("Homme", "Femme");
            addGender.getSelectionModel().selectFirst();
        } else if (body.getChildren().contains(table)) {
            this.aff();
        }
    }

    private void aff() throws SQLException {
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT ID_EMP " +
                "FROM EMPLOYE " +
                "WHERE ID_POSTE = 41";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            table.getItems().add(new Formateur(rs.getInt("ID_EMP")));
        }
    }

    @FXML
    private void goToAdd(ActionEvent event) throws IOException {
        Routing.RedirectSubScene(body, "AddFormateur");
    }


    public void submitAdd(ActionEvent actionEvent) throws SQLException, IOException {
        if (verfy()) {
            String sql = " insert into EMPLOYE(NOM,PRENOM,DATE_NAISS,ID_POSTE,CIN,NUM_TEL,MAIL,ADRESSE,GENDER,ZIP) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, nom.getText());
            stmt.setString(2, prenom.getText());
            LocalDate localDate = addDateNaiss.getValue();
            stmt.setDate(3, Date.valueOf(localDate));
            stmt.setInt(4, 41);
            stmt.setInt(5, Integer.parseInt(addCin.getText()));
            stmt.setInt(6, Integer.parseInt(addTel.getText()));
            stmt.setString(7, addEmail.getText());
            stmt.setString(8, addAdr.getText());
            stmt.setString(9, String.valueOf(addGender.getValue().toString().charAt(0)));
            stmt.setInt(10, Integer.parseInt(addZip.getText()));
            stmt.execute();
            DatabaseConn.getConnect().close();
            sql = "SELECT * " +
                    "FROM EMPLOYE " +
                    "WHERE CIN = " + Integer.parseInt(addCin.getText());
            ResultSet rs = DatabaseConn.getConnect().createStatement().executeQuery(sql);
            if (rs.next()) {
                employe emp = new employe(rs.getInt("ID_EMP"));
                DatabaseConn.getConnect().close();
                sql = " insert into FORMATEUR(ID_EMP,SPECIALITE) values (?, ?)";
                stmt = DatabaseConn.getConnect().prepareStatement(sql);
                stmt.setInt(1, emp.getId());
                stmt.setString(2, addMatiere.getText());
                stmt.execute();
                DatabaseConn.getConnect().close();
                Routing.RedirectSubScene(body, "gestFormateur");
            }


        }

    }

    public boolean verfy() throws SQLException, IOException {
        boolean v = true;
        EmailValidator validator = EmailValidator.getInstance();
        if (!nom.getText().matches("[A-Z a-z]*") || nom.getText().length() < 3) {
            AlertBox.display("Nom", "Nom invalide");
            v = false;
        } else if (!prenom.getText().matches("[A-Z a-z]*") || prenom.getText().length() < 3) {
            AlertBox.display("prenom", "Prenom invalide");
            v = false;
        } else if (!validator.isValid(addEmail.getText())) {
            AlertBox.display("Adresse", "Email invalide");
            v = false;
        } else if (!uniqueMail(addEmail.getText())) {
            AlertBox.display("MAil", "Email exist deja");
            v = false;
        } else if (addCin.getText().length() != 8 || !addCin.getText().matches("\\d+")) {
            AlertBox.display("CIN", "CIN invalide");
            v = false;
        } else if (!uniqueCin(addCin.getText())) {
            AlertBox.display("CIN", "CIN exist deja");
            v = false;
        } else if (addTel.getText().length() != 8 || !addTel.getText().matches("\\d+")) {
            AlertBox.display("Tel", "Numero de telephone invalide");
            v = false;
        } else if (addDateNaiss.getValue() == null && addDateNaiss.isEditable()) {
            AlertBox.display("Date", "Date invalide");
            v = false;
        } else if (addAdr.getText().length() < 5) {
            AlertBox.display("Adresse", "Adresse invalide");
            v = false;
        } else if (addZip.getText().length() != 4 || !addZip.getText().matches("\\d+")) {
            AlertBox.display("ZIP", "ZIP invalide");
            v = false;
        } else if (addMatiere.getText().length() < 3) {
            AlertBox.display("Matiere", "Specialite invalide");

            v = false;
        }

        return v;
    }

    public boolean uniqueCin(String value) throws SQLException {
        String sql = "SELECT * " +
                "FROM EMPLOYE " +
                "WHERE CIN = " + value;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return !rs.next();

    }

    public boolean uniqueMail(String value) throws SQLException {
        String sql = "SELECT * " +
                "FROM EMPLOYE " +
                "WHERE MAIL LIKE '" + value + "'";
        Statement stmt = DatabaseConn.getConnect().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return !rs.next();

    }

}
