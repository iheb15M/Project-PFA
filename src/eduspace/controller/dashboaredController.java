package eduspace.controller;

import animatefx.animation.Bounce;
import animatefx.animation.FadeOut;
import eduspace.model.user;
import eduspace.vendor.Routing;
import eduspace.vendor.Visibility;
import eduspace.vendor.loggedIn;
import eduspace.vendor.popup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;


public class dashboaredController {
    @FXML
    public AnchorPane subScene;
    @FXML
    public Pane content;
    @FXML
    public ImageView logoutBtn;
    @FXML
    public VBox sidemenucontent;
    public Text tiltesidemenu;
    public ImageView affProfile;
    @FXML
    private AnchorPane sidebar;
    @FXML
    private AnchorPane sidemenu;
    @FXML
    private AnchorPane body;

    @FXML
    private AnchorPane setting;
    @FXML
    private ImageView settingBtn;
    @FXML
    private Button GestRH;

    public void initialize() {
        subMenuSetting();
        profile();
        logout();

    }

    public void subMenuSetting() {
        setting.setVisible(false);
        if (loggedIn.getUser().getType().equals("SUPER")) {
            settingBtn.setVisible(false);
        } else {
            settingBtn.setPickOnBounds(true);
            settingBtn.setOnMouseClicked((MouseEvent e) -> {
                if (setting.isVisible()) {
                    Visibility.hide(setting);
                } else {
                    Visibility.show(setting);
                }
            });
        }
    }

    public void profile() {
        affProfile.setPickOnBounds(true);
        affProfile.setOnMouseClicked((MouseEvent e) -> {
            try {
                popup.custom(500, 800, "affprofile");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public void logout() {

        logoutBtn.setPickOnBounds(true);
        logoutBtn.setOnMouseClicked((MouseEvent e) -> {
            try {
                Routing.RedirectTo("home");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /****** Dashboared Super *****/

    public void goToGestRH(ActionEvent actionEvent) throws IOException {

        Routing.RedirectSubScene(content,"gestRH");


    }

    public void goToGestPoste(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(content, "gestPoste");
    }

    public void goToGestSalle(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(content, "gestSalle");
    }

    public void goToGestDomaine(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(content, "gestDomaine");
    }

    /****** Dashboared RH *****/
    public void goToGestEmp(ActionEvent actionEvent) {
    }

    public void goToGestFormateur(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(content, "gestFormateur");
    }

    public void goToConsAb(ActionEvent actionEvent) throws IOException {
        Routing.RedirectSubScene(content, "consultationAbsence");
    }


    public void editPassword(ActionEvent actionEvent) throws IOException {
        popup.custom(500, 800, "editpassword");
    }

    public void editCompte(ActionEvent actionEvent) throws IOException {
        popup.custom(500, 800, "editprofile");
    }
}
