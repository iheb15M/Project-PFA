package eduspace.controller;

import animatefx.animation.Shake;
import eduspace.model.departement;
import eduspace.model.salle;
import eduspace.vendor.*;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestSalleController  {
    public static salle s ;
    public static departement dep;

    @FXML
    public Button goToAdd;

    @FXML
    public Button close;
    @FXML
    public AnchorPane Body;

    @FXML
    public TextField nomDep;

    @FXML
    public TextField surnomSalle;

    @FXML
    public ChoiceBox Dep;

    @FXML
    public TableView table;
    public TableColumn surnom;
    public TableColumn capacite;
    public TableColumn departement;
    public TableColumn action;

    @FXML
    public Text erreurNomDep;

    @FXML
    public Text erreurSurnomSalle;

    @FXML
    public Button submit;

    @FXML
    public Button submitDelete;

    @FXML
    public Button submitEdit;
    public ChoiceBox choiceDep;
    public TextField Surnom;
    public TextField Capacite;
    public AnchorPane addBody;

    public void initialize() {
    }

    public void close(ActionEvent actionEvent) {
        popup.stage.close();
    }

    public static void setDep(departement dep) {
        GestSalleController.dep = dep;
    }

    // Affichage des salles

    private void affSalle() throws SQLException {
        surnom.setCellValueFactory (new PropertyValueFactory<>("surnom"));
        capacite.setCellValueFactory (new PropertyValueFactory<>("Capacité"));
        departement.setCellValueFactory (new PropertyValueFactory<>("nomDep"));
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT ID_SALLE FROM SALLE ";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            table.getItems().add(new salle (rs.getInt("ID_SALLE")));
        }
        table.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    s = (salle)row.getItem();
                    try {
                        GestSalleController.setDep(dep);
                        popup.custom(500, 800, "editSalle");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


    // Ajout salle

    public void goToAdd(ActionEvent actionEvent) throws IOException {
        popup.custom(500, 800, "gestSalleAjout");
    }

    public void surnomSalle(KeyEvent keyEvent) throws SQLException {
        boolean v = verifSurnomSalle();
    }

    public void NomDep(KeyEvent keyEvent) throws SQLException {
        boolean v = this.verifNomDep();
    }

    public void submit(MouseEvent mouseEvent) throws SQLException, IOException {

        String sql = " insert into SALLE (SURNOM,CAPACITE,DEP_ID) values (?, ?, ?)";
        PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
        stmt.setString(1, Surnom.getText());
        stmt.setString(2, Capacite.getText());
        stmt.setInt(3, 2);
        stmt.execute();
        Routing.RedirectSubScene(addBody, "gestSalle");
    }

    // controle de saisie

    private boolean verifNomDep()throws SQLException{
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

    private boolean verifSurnomSalle () throws SQLException {
        boolean v = true;
        erreurSurnomSalle.setFill(Color.RED);
        if (surnomSalle.getText().length() < 2) {
            //Verification de la langeur de champ
            erreurSurnomSalle.setText("Ce champ doit contenir au moins 2 characters");
            v = false;
        } else {
            //Verification d la redondance
            erreurSurnomSalle.setText("");
            Statement stmt = DatabaseConn.getConnect().createStatement();
            String sql = "SELECT SURNOM " +
                    "FROM SALLE " +
                    "WHERE SURNOM like '" + surnomSalle.getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                erreurSurnomSalle.setText("Surnom existe deja!");
                v = false;
            } else {
                erreurSurnomSalle.setText("");
            }
        }
        return v;
    }

    //Mofification de salle

    public void submitEdit(ActionEvent actionEvent) throws SQLException {
        if (verifSurnomSalle ()) {
            int i = 0;
            String depid = "";
            while (Dep.getValue().toString().length() > i && Dep.getValue().toString().charAt(i) != ' ') {
                depid += Dep.getValue().toString().charAt(i);
                i++;
            }
            s.setDepId(Integer.parseInt(depid));
            s.setSurnom(surnom.getText());
            s.setCapacite(capacite.getCellValueFactory());
            String sql = " UPDATE SALLE " +
                    "SET SURNOM = ?, " +
                    "CAPACITE = ?, " +
                    "DEP_ID = ? " +
                    "WHERE ID_SALLE = ?";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, s.getSurnom());
            stmt.setInt(2, s.getCapacite());
            stmt.setInt(3, s.getDepId());
            stmt.setInt(4, s.getId());
            stmt.executeUpdate();
            DatabaseConn.getConnect().close();
            popup.stage.close();
        }
    }

    //Suppression de salle

        public void submitDelete() throws SQLException {
        String sql = "delete from SALLE where ID_SALLE = ?";
        PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
        stmt.setInt(1, s.getId());
        stmt.execute();
        DatabaseConn.getConnect().close();

    }


}

