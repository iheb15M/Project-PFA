package eduspace.controller;

import eduspace.model.Formateur;
import eduspace.model.employe;
import eduspace.vendor.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.validator.routines.EmailValidator;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class GestRHController {

    @FXML
    public AnchorPane body;
    @FXML
    public Button Addbtn;
    @FXML
    public ScrollPane scrollPane;
    public TextField prenom;
    public TextField nom;
    public TextField cin;
    public TextField email;
    public TextField tel;
    public TextField adresse;
    public TextField zip;
    public DatePicker dateNaiss;
    public CheckBox account;
    @FXML
    public ChoiceBox Gender;
    public TableView affTable;
    public TableColumn affCin;
    public TableColumn affNom;
    public TableColumn affPrenom;
    public TableColumn affMail;
    public TableColumn affTel;


    public void initialize() throws SQLException {

        if (body.getChildren().contains(affTable)) {

            this.aff();
        }
    }

    public void goToAdd(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(body, "gestRHAjout");
    }

    private void aff() throws SQLException {
        affCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        affNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        affPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        affMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        affTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT ID_EMP " +
                "FROM EMPLOYE " +
                "WHERE ID_POSTE = 1";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            affTable.getItems().add(new employe(rs.getInt("ID_EMP")));
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
        } else if (!validator.isValid(email.getText())) {
            AlertBox.display("Adresse", "Email invalide");
            v = false;
        } else if (!this.uniqueMail(email.getText())) {
            AlertBox.display("MAil", "Email exist deja");
            v = false;
        } else if (cin.getText().length() != 8 || !cin.getText().matches("\\d+")) {
            AlertBox.display("CIN", "CIN invalide");
            v = false;
        } else if (!this.uniqueCin(cin.getText())) {
            AlertBox.display("CIN", "CIN exist deja");
            v = false;
        } else if (tel.getText().length() != 8 || !tel.getText().matches("\\d+")) {
            AlertBox.display("Tel", "Numero de telephone invalide");
            v = false;
        } else if (dateNaiss.getValue() == null && dateNaiss.isEditable()) {
            AlertBox.display("Date", "Date invalide");
            v = false;
        } else if (adresse.getText().length() < 5) {
            AlertBox.display("Adresse", "Adresse invalide");
            v = false;
        } else if (zip.getText().length() != 4 || !zip.getText().matches("\\d+")) {
            AlertBox.display("ZIP", "ZIP invalide");
            v = false;
        }

        return v;
    }


    public void add(ActionEvent actionEvent) throws SQLException, IOException {
        if (verfy()) {
            String sql = " insert into EMPLOYE(NOM,PRENOM,DATE_NAISS,ID_POSTE,CIN,NUM_TEL,MAIL,ADRESSE,GENDER,ZIP) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, nom.getText());
            stmt.setString(2, prenom.getText());
            LocalDate localDate = dateNaiss.getValue();
            stmt.setDate(3, Date.valueOf(localDate));
            stmt.setInt(4, 1);
            stmt.setInt(5, Integer.parseInt(cin.getText()));
            stmt.setInt(6, Integer.parseInt(tel.getText()));
            stmt.setString(7, email.getText());
            stmt.setString(8, adresse.getText());
            stmt.setString(9, String.valueOf(Gender.getValue().toString().charAt(0)));
            stmt.setInt(10, Integer.parseInt(zip.getText()));
            stmt.execute();
            DatabaseConn.getConnect().close();
            if (account.isSelected()) {
                sql = "SELECT * " +
                        "FROM EMPLOYE " +
                        "WHERE CIN = " + Integer.parseInt(cin.getText());
                ResultSet rs = DatabaseConn.getConnect().createStatement().executeQuery(sql);
                if (rs.next()) {
                    employe emp = new employe(rs.getInt("ID_EMP"));
                    DatabaseConn.getConnect().close();
                    sql = " insert into COMPTE(PSEUDO,PASSWORD,TYPE,ID_EMP) values (?, ?, ?, ?)";
                    stmt = DatabaseConn.getConnect().prepareStatement(sql);
                    String pseudo = Generate.pseudo("RH");
                    stmt.setString(1, pseudo);
                    stmt.setString(2, cin.getText());
                    stmt.setString(3, "RH");
                    stmt.setInt(4, emp.getId());
                    stmt.execute();
                    popup.info("succes", "Pseudo:" + pseudo + " | Mot de passe:" + cin.getText());
                    DatabaseConn.getConnect().close();
                }
            }
            Routing.RedirectSubScene(body, "gestRH");
        }
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
