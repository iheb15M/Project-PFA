package eduspace.controller;

import animatefx.animation.Shake;
import eduspace.model.departement;
import eduspace.model.domaine;
import eduspace.vendor.AlertBox;
import eduspace.vendor.DatabaseConn;
import eduspace.vendor.Routing;
import eduspace.vendor.popup;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestDomaineController {

    @FXML
    public AnchorPane body;
    @FXML
    public AnchorPane domainePane;
    @FXML
    public TextField nomDomaine;

    @FXML
    public TextField nomDep;
    @FXML
    public ChoiceBox chefDep;
    @FXML
    public Button submit;
    @FXML
    public Text erreurNomDomaine;
    public Text erreurNomDep;
    public Button Addbtn;
    public TableView table;
    public TableColumn domaine;
    public TableColumn branche;
    public TableColumn departement;


    public void initialize() throws SQLException {
        if (body.getChildren().contains(table)) {
            affDomaines();
        }
    }

    /************ Affichage de Domaines ***********/

    private void affDomaines() throws SQLException {
        domaine.setCellValueFactory(new PropertyValueFactory<>("nomDomaine"));
        departement.setCellValueFactory(new PropertyValueFactory<>("nomDep"));
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT DEP_ID FROM DEPARTEMENT ";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            table.getItems().add(new departement(rs.getInt("DEP_ID")));
        }
        table.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    departement dep = (departement) row.getItem();
                    try {
                        GestBrancheController.setDep(dep);
                        popup.custom(712, 1068, "AffDomaine");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


    /************ Ajout Domaine ************/
    public void goToAdd(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(body, "gestDomaineAjout");
    }


    public void NomDomaine(KeyEvent keyEvent) throws SQLException {
        boolean v = verifNomDomaine();
    }

    public void NomDep(KeyEvent keyEvent) throws SQLException {
        boolean v = this.verifNomDep();
    }


    public void setItems(MouseEvent mouseEvent) throws SQLException {
        chefDep.getItems().clear();
        // insertion des chef de departement a la liste droulante
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM EMPLOYE , POSTE "
                + "where EMPLOYE.ID_POSTE = POSTE.ID_POST " +
                "AND POSTE.TYPE_POST LIKE 'chefDep'";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String item = rs.getInt("ID_EMP") + " | " + rs.getString("NOM") + " " + rs.getString("PRENOM");
            chefDep.getItems().add(item);
        }
        DatabaseConn.getConnect().close();
    }

    public void submit(MouseEvent mouseEvent) throws SQLException, IOException {
        if (!verifNomDomaine()) {
            AlertBox.display("Nom de domaine incorrect!",
                    "Verifiez le nom de domaine ");
            new Shake(nomDomaine).setSpeed(0.5).play();
            new Shake(erreurNomDomaine).setSpeed(0.5).play();

        } else if (!verifNomDep()) {
            AlertBox.display("Nom de departement incorrect!",
                    "Verifiez le nom de departement ");
            new Shake(nomDep).setSpeed(0.5).play();
            new Shake(erreurNomDep).setSpeed(0.5).play();
        } else if (chefDep.getValue() == null) {
            AlertBox.display("Choisir un chef de departement!",
                    "Nom de departement est un champ obligatoire ");
            new Shake(chefDep).setSpeed(0.5).play();
        } else {
            int i = 0;
            String chefid = "";
            while (chefDep.getValue().toString().length() > i && chefDep.getValue().toString().charAt(i) != ' ') {
                chefid += chefDep.getValue().toString().charAt(i);
                i++;
            }
            String sql = " insert into DEPARTEMENT (NOM_DEP,CHEF_ID,NOM_DOMAINE) values (?, ?, ?)";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, nomDep.getText());
            stmt.setInt(2, Integer.parseInt(chefid));
            stmt.setString(3, nomDomaine.getText());
            stmt.execute();
            Routing.RedirectSubScene(body, "gestDomaine");
        }


    }


    // Controle de saisie
    private boolean verifNomDep() throws SQLException {
        boolean v = true;
        erreurNomDep.setFill(Color.RED);
        //Verification de la langeur de champ
        if (nomDep.getText().length() < 3) {
            erreurNomDep.setText("Ce champ doit contenir au moins 3 characters");
            v = false;
        } else {
            //Verification d la redondance nom departement
            erreurNomDep.setText("");
            Statement stmt = DatabaseConn.getConnect().createStatement();
            String sql = "SELECT NOM_DEP " +
                    "FROM DEPARTEMENT " +
                    "WHERE NOM_DEP like '" + nomDep.getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                erreurNomDep.setText("Nom de domaine existe deja!");
                v = false;
            } else {
                erreurNomDep.setText("");
            }
        }
        return v;
    }

    private boolean verifNomDomaine() throws SQLException {
        boolean v = true;
        erreurNomDomaine.setFill(Color.RED);
        if (nomDomaine.getText().length() < 3) {
            //Verification de la langeur de champ
            erreurNomDomaine.setText("Ce champ doit contenir au moins 3 characters");
            v = false;
        } else {
            //Verification d la redondance nom departement
            erreurNomDomaine.setText("");
            Statement stmt = DatabaseConn.getConnect().createStatement();
            String sql = "SELECT NOM_DOMAINE " +
                    "FROM DEPARTEMENT " +
                    "WHERE NOM_DOMAINE like '" + nomDomaine.getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                erreurNomDomaine.setText("Nom de domaine existe deja!");
                v = false;
            } else {
                erreurNomDomaine.setText("");
            }
        }
        return v;
    }


}
