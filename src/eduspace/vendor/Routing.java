package eduspace.vendor;

import animatefx.animation.*;
import eduspace.model.user;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Routing {
    private static final int height =1368;
    private static final int width =912;
    private static Stage primaryStage ;
    private static Object Parent;

    //Lancer la fenetre principal
    public static void home(Stage stage) throws IOException {
        primaryStage = stage;
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        RedirectTo("home");
        boolean fullscreen = primaryStage.isFullScreen();
        primaryStage.setFullScreen(fullscreen);
        primaryStage.show();

    }

    public static void RedirectTo(String view) throws IOException {
        //Charger le fichier FXML selon son nom
        String to ="../view/"+view+".fxml";
        Parent layout = FXMLLoader.load(Routing.class.getResource(to));
        //Creation d'un nouveau scene
        Scene scene = new Scene(layout, height, width);
        //Definir la scene de stage
        primaryStage.setScene(scene);
        new FadeIn(layout).setSpeed(0.5).play();
    }

    //Changement de contenu de Pane
    public static void RedirectSubScene(Pane pane , String view) throws IOException {
        //Charger le fichier FXML selon son nom
        String to ="../view/"+view+".fxml";
        Pane newLoadedPane = FXMLLoader.load(Routing.class.getResource(to));
        //Supprimer tout le contnu de la Pane
        pane.getChildren().clear();
        //Ajouter le nouveau contenu
        pane.getChildren().add(newLoadedPane);
        new FadeInRight(newLoadedPane).setSpeed(0.9).play();
    }

    public static void middlewareLogin(String pseudo) throws SQLException, IOException {
       user user = new user(pseudo);
       switch (user.getType()) {
           case "SUPER":
               Routing.RedirectTo("dashboaredSuper");
               break;
           case "RH":
               Routing.RedirectTo("dashboaredRH");
               break;
           default:
               Routing.RedirectTo("home");
       }

    }

}
