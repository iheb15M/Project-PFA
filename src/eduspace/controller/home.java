package eduspace.controller;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import eduspace.model.user;
import eduspace.vendor.AlertBox;
import eduspace.vendor.DatabaseConn;
import eduspace.vendor.Routing;
import eduspace.vendor.loggedIn;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class home {

    public Text mpo;
    @FXML
private AnchorPane loginForm;
@FXML
private PasswordField password;
@FXML
private TextField pseudo;
@FXML
private AnchorPane footer;
@FXML
private AnchorPane body;
@FXML
private Button submit;

@FXML
private AnchorPane content;

@FXML
private ImageView splash;

private Connection conn;

    public void initialize() throws InterruptedException {
        this.conn= DatabaseConn.getConnect();
         new FadeOutLeftBig(splash).setSpeed(0.3).play();
         new FadeInRightBig(content).setSpeed(0.3).play();


        //this.splash();

    }


    @FXML
    public  void Login() throws SQLException, IOException, InterruptedException {

        if (verifPseudo()){
            if (this.password.getText().length()>0 && verifPassword()){
                loggedIn.setUser(new user(this.pseudo.getText()));
                DatabaseConn.getConnect().close();
                Routing.middlewareLogin(this.pseudo.getText());
            }
        }

    }


    public  boolean verifPseudo() throws SQLException {
        boolean verif = true;
        Statement stmt=conn.createStatement();
        String sql= "SELECT pseudo FROM compte where pseudo LIKE '"+this.pseudo.getText()+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if (!rs.next()){
            this.pseudo.getStyleClass().add("invalid");
            verif=false;
        }else {
            this.pseudo.getStyleClass().add("valid");

        }

        return verif;
    }

    private boolean verifPassword() throws SQLException, InterruptedException {
        boolean verif = true;
        if (this.password.getText().equals(new user(this.pseudo.getText()).getPassword())){
            this.password.getStyleClass().add("valid");
        }else {
            this.password.getStyleClass().add("invalid");
            verif =false;
        }

        return verif;
    }
    
}

