package eduspace.controller;

import eduspace.model.branche;
import eduspace.model.departement;
import eduspace.model.employe;
import eduspace.vendor.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class GestBrancheController {


    public static departement dep;
    @FXML
    public TableColumn<Object, Object> nomBranche;
    public TableColumn<Object, Object> type;
    public TableView brancheTable;
    public Button submitEdit;
    public TextField brancheEdit;
    public ChoiceBox depEdit;
    public ChoiceBox typeEdit;
    public TextField brancheAdd;
    public ChoiceBox depAdd;
    public ChoiceBox typeAdd;
    public Button submitAdd;
    @FXML
    private AnchorPane affBody;
    @FXML
    private Text nomDomaine;
    @FXML
    private Text nomDep;
    @FXML
    public Text chefDep;
    //Aff Branche
    public Text affBranche;
    public Button deleteBranche;
    public Text affDepTitle;
    public Text affTypeTitle;
    public Button editBranche;
    public static branche br;


    public void initialize() throws SQLException, IOException {
        this.show();
        this.affBranche();
        this.editBranche();
        this.addBranche();
    }


    public static void setDep(departement dep) {
        GestBrancheController.dep = dep;
    }


    public void show() throws SQLException {
        if (affBody.getChildren().contains(nomDomaine)) {
            nomDomaine.setText(nomDomaine.getText() + " " + dep.getNomDomaine());
            nomDep.setText(nomDep.getText() + " " + dep.getNomDep());
            chefDep.setText(chefDep.getText() +
                    " " + new employe(dep.getChefId()).getNom() +
                    " " + new employe(dep.getChefId()).getPrenom());

            /****** Branche ******/
            nomBranche.setCellValueFactory(new PropertyValueFactory<>("nom"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            Statement stmt = DatabaseConn.getConnect().createStatement();
            String sql = "SELECT branche_id FROM BRANCHE WHERE dep_id = " + dep.getDepId();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                brancheTable.getItems().add(new branche(rs.getInt("BRANCHE_ID")));
            }

            brancheTable.setRowFactory(tv -> {
                TableRow row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        branche br = (branche) row.getItem();
                        try {
                            GestBrancheController.br = br;
                            Routing.RedirectSubScene(affBody, "affBranche");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });
        }
    }

    public void affBranche() throws IOException, SQLException {
        if (affBody.getChildren().contains(affBranche)) {
            affBranche.setText(affBranche.getText() + br.getNom());
            affDepTitle.setText(affDepTitle.getText() + new departement(br.getDepId()).getNomDep());
            affTypeTitle.setText(affTypeTitle.getText() + br.getType());
            editBranche.setOnMouseClicked(event -> {
                try {
                    GestBrancheController.br = br;
                    Routing.RedirectSubScene(affBody, "editBranche");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private boolean verifBranche(TextField name, ChoiceBox dep, ChoiceBox type) {
        boolean v = true;
        if (name.getText().length() < 3) {
            Text e = new Text("Verifiez le nom de Branche");
            e.setFill(Color.RED);
            e.setLayoutX(name.getLayoutX());
            e.setLayoutY(name.getLayoutY() + 20);
            affBody.getChildren().add(e);
            v = false;
        } else if (dep.getValue() == null) {
            Text e = new Text("Nom de departement est un champ obligatoire");
            e.setFill(Color.RED);
            e.setLayoutX(dep.getLayoutX());
            e.setLayoutY(dep.getLayoutY() + 20);
            affBody.getChildren().add(e);
            v = false;
        } else if (type.getValue() == null) {
            Text e = new Text("Type de branch est un champ obligatoire");
            e.setFill(Color.RED);
            e.setLayoutX(type.getLayoutX());
            e.setLayoutY(type.getLayoutY() + 20);
            affBody.getChildren().add(e);
            v = false;
        }
        return v;

    }

    /******* Modifier Brranche **********/

    public void editBranche() throws IOException, SQLException {
        if (affBody.getChildren().contains(submitEdit)) {
            brancheEdit.setText(br.getNom());
            depEdit.getItems().clear();

            // insertion des departement a la liste droulante
            //insertion de departement actuelle en premier
            Statement stmt = DatabaseConn.getConnect().createStatement();
            String sql = "SELECT * " +
                    "FROM DEPARTEMENT " +
                    "WHERE DEP_ID = " + br.getDepId();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String item = rs.getInt("DEP_ID") +
                        " |  Departement:"
                        + rs.getString("NOM_DEP") + " | Domaine"
                        + rs.getString("NOM_DOMAINE");
                depEdit.getItems().add(item);

            }
            //Insertion de reste de departement
            sql = "SELECT * " +
                    "FROM DEPARTEMENT";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("DEP_ID") != br.getDepId()) {
                    String item = rs.getInt("DEP_ID") +
                            " |  Departement:"
                            + rs.getString("NOM_DEP") + " | Domaine"
                            + rs.getString("NOM_DOMAINE");
                    depEdit.getItems().add(item);
                }

            }
            DatabaseConn.getConnect().close();
            depEdit.getSelectionModel().selectFirst();

            typeEdit.getItems().clear();
            switch (br.getType()) {
                case "BTS":
                    typeEdit.getItems().addAll("BTS", "CAP", "BTP");
                    break;
                case "CAP":
                    typeEdit.getItems().addAll("CAP", "BTS", "BTP");
                    break;
                case "BTP":
                    typeEdit.getItems().addAll("BTP", "CAP", "BTS");
                    break;
            }
            typeEdit.getSelectionModel().selectFirst();
        }
    }

    public void submitEdit(ActionEvent actionEvent) throws SQLException {
        if (verifBranche(brancheEdit, depEdit, typeEdit)) {
            int i = 0;
            String depid = "";
            while (depEdit.getValue().toString().length() > i && depEdit.getValue().toString().charAt(i) != ' ') {
                depid += depEdit.getValue().toString().charAt(i);
                i++;
            }
            br.setDepId(Integer.parseInt(depid));
            br.setNom(brancheEdit.getText());
            br.setType(typeEdit.getValue().toString());
            String sql = " UPDATE BRANCHE " +
                    "SET SURNOM = ?, " +
                    "TYPE = ?, " +
                    "DEP_ID = ? " +
                    "WHERE BRANCHE_ID = ?";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, br.getNom());
            stmt.setString(2, br.getType());
            stmt.setInt(3, br.getDepId());
            stmt.setInt(4, br.getId());
            stmt.executeUpdate();
            DatabaseConn.getConnect().close();
            popup.stage.close();
        }
    }

    /**** Supprimer ******/
    public void deleteBranche() throws SQLException, IOException {
        String sql = "delete from BRANCHE where BRANCHE_ID = ?";
        PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
        stmt.setInt(1, br.getId());
        stmt.execute();
        DatabaseConn.getConnect().close();
        Routing.RedirectSubScene(affBody, "AffDomaine");
    }

    /***** Branche Ajout *****/

    public void goToAddBranche(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(affBody, "addBranche");
    }

    public void addBranche() {
        if (affBody.getChildren().contains(submitAdd)) {
            String item = dep.getDepId() +
                    " |  Departement:"
                    + dep.getNomDep() + " | Domaine"
                    + dep.getNomDomaine();
            depAdd.getItems().add(item);
            depAdd.getSelectionModel().selectFirst();
            typeAdd.getItems().addAll("CAP", "BTP", "BTS");
        }
    }

    public void submitAdd(ActionEvent actionEvent) throws SQLException, IOException {
        if (verifBranche(brancheAdd, depAdd, typeAdd)) {
            String sql = " insert into BRANCHE (SURNOM,TYPE,DEP_ID) values (?, ?, ?)";
            PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
            stmt.setString(1, brancheAdd.getText());
            stmt.setInt(3, dep.getDepId());
            stmt.setString(2, typeAdd.getValue().toString());
            stmt.execute();
            Routing.RedirectSubScene(affBody, "AffDomaine");
        }
    }


    public void close(ActionEvent actionEvent) {
        popup.stage.close();
    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {
        String sql = "delete from DEPARTEMENT where DEP_ID = ?";
        PreparedStatement stmt = DatabaseConn.getConnect().prepareStatement(sql);
        stmt.setInt(1, dep.getDepId());
        stmt.execute();
        DatabaseConn.getConnect().close();
        popup.stage.close();
        Routing.RedirectSubScene(affBody, "gestDomaine");
    }
}

