package eduspace.controller;

import eduspace.model.employe;
import eduspace.model.poste;
import eduspace.model.user;
import eduspace.vendor.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class profileController {

    public AnchorPane bodyProfile;
    public Text affNom;
    public Text affPseudo;
    public TextField editMail;
    public TextField editTel;
    public TextField EditAdress;
    public TextField EditZip;

    public PasswordField oldPwd;
    public PasswordField newPwd;
    public PasswordField confirmPwd;
    @FXML
    private Text affMail;

    @FXML
    private Text affTel;

    @FXML
    private Text affAdr;

    @FXML
    private Text affDateNaiss;

    @FXML
    private Text affCin;

    @FXML
    private Text affPoste;

    @FXML
    private ImageView editBtn;

    public void initialize() throws SQLException {
        this.getUser();
        this.editUser();
    }

    protected void getUser() throws SQLException {

        if (bodyProfile.getChildren().contains(affMail)) {
            user compte = loggedIn.getUser();
            affPseudo.setText(compte.getPseudo());
            //Aff super
            if (compte.getType().equals("SUPER")) {
                affMail.setText("");
                affAdr.setText("");
                affCin.setText("");
                affDateNaiss.setText("");
                affPoste.setText("");
                affTel.setText("");
                affNom.setText("Super Admin");
                editBtn.setVisible(false);
            } else {
                //Employe
                //Affemploye

                employe employe = new employe(compte.getIdEmploye());
                affNom.setText(employe.getNom() + " " + employe.getPrenom());
                affDateNaiss.setText(affDateNaiss.getText() + "");
                affMail.setText(affMail.getText() + " " + employe.getEmail());
                affCin.setText(affCin.getText() + " " + employe.getCin());
                affAdr.setText(affAdr.getText() + " " + employe.getAdresse() + " " + employe.getZip());
                affTel.setText(affTel.getText() + " " + employe.getTel());
                affPoste.setText(affPoste.getText() + " " + new poste(employe.getPoste_Id()).getNom());
            }
        }
    }

    protected void editUser() throws SQLException {
        if (bodyProfile.getChildren().contains(editMail)) {
            user compte = loggedIn.getUser();
            affPseudo.setText(compte.getPseudo());
            employe employe = new employe(compte.getIdEmploye());
            affNom.setText(employe.getNom() + " " + employe.getPrenom());
            //Edit employe
            editMail.setText(employe.getEmail());
            editTel.setText(String.valueOf(employe.getTel()));
            EditAdress.setText(employe.getAdresse());
            EditZip.setText(String.valueOf(employe.getZip()));
        }

    }

    public void submitEdit(ActionEvent actionEvent) throws IOException, SQLException {
        if (verify()) {
            String sql = " UPDATE EMPLOYE " +
                    "SET MAIL = ?, " +
                    "NUM_TEL = ?, " +
                    "ADRESSE = ? ," +
                    "ZIP = ? " +
                    "WHERE ID_EMP = ?";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, editMail.getText());
            stmt.setInt(2, Integer.parseInt(editTel.getText()));
            stmt.setString(3, EditAdress.getText());
            stmt.setInt(4, Integer.parseInt(EditZip.getText()));
            stmt.setInt(5, loggedIn.getUser().getIdEmploye());
            stmt.executeUpdate();
            DatabaseConn.getConnect().close();
            popup.stage.close();
        }

    }

    private boolean verify() throws IOException, SQLException {
        boolean v = true;
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(editMail.getText())) {
            AlertBox.display("Email", "Adresse Email invalide");
            v = false;
        } else if (editTel.getText().length() != 8 || !editTel.getText().matches("\\d+")) {
            AlertBox.display("Tel", "Numero de telephone invalide");
            v = false;
        } else if (EditAdress.getText().length() < 5) {
            AlertBox.display("Adresse", "Adresse invalide");
            v = false;
        } else if (EditZip.getText().length() != 4 || !EditZip.getText().matches("\\d+")) {
            AlertBox.display("ZIP", "ZIP invalide");
            v = false;
        }
        return v;
    }

    public boolean unique(String Table, String colmun, String value) throws SQLException {
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT COUNT(*) AS total " +
                "FROM " + Table +
                "WHERE " + colmun + " like '" + value + "'" +
                " AND " + colmun + " <> " + loggedIn.getUser().getIdEmploye();
        ResultSet rs = stmt.executeQuery(sql);
        return rs.getInt("total") != 0;

    }

    public void close(ActionEvent actionEvent) {
        popup.stage.close();
    }

    public void submitEditPwd(ActionEvent actionEvent) throws IOException, SQLException {
        if (!oldPwd.getText().equals(loggedIn.getUser().getPassword())) {
            AlertBox.display("Password", "Ancien mot de passe incorrecte");
        } else if (newPwd.getText().length() < 6) {
            AlertBox.display("Password", "le mot de passe doit contenir au moins 6 caractères ");
        } else if (!newPwd.getText().equals(confirmPwd.getText())) {
            AlertBox.display("Password", "Confirmer votre mot de passe");
        } else {
            String sql = " UPDATE COMPTE " +
                    "SET PASSWORD = ? " +
                    "WHERE PSEUDO = ?";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, newPwd.getText());
            stmt.setString(2, loggedIn.getUser().getPseudo());
            stmt.executeUpdate();
            DatabaseConn.getConnect().close();
            popup.stage.close();
        }
    }
}
