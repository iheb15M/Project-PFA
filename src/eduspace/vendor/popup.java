package eduspace.vendor;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import animatefx.util.SequentialAnimationFX;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.Cursor.HAND;

public class popup {
    public static Stage stage;

    public static void custom(int height, int width, String view) throws IOException {
        //charger le fichier FXML
        String path = "../view/" + view + ".fxml";
        Pane root = FXMLLoader.load(AlertBox.class.getResource(path));
        //Creation d'un nouveau stage
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        //Cration d'un nouveau scene
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public static void info(String Title, String Message) throws IOException {
        //charger le fichier FXML
        Parent root = FXMLLoader.load(AlertBox.class.getResource("../view/alertBox.fxml"));
        //Creation d'un nouveau stage
        Stage alert = new Stage();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.TRANSPARENT);
        //Cration d'un nouveau scene
        Scene scene = new Scene(root, 450, 350);
        scene.getStylesheets().add(AlertBox.class.getResource("../view/css/popup.css").toExternalForm());
        alert.setScene(scene);
        Label Msg = (Label) scene.lookup("#message");
        Msg.setText(Message);
        // Creation d'un evenement pour bouton fermer
        Button closeBtn = (Button) scene.lookup("#close");
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                alert.close();
            }
        });
        SequentialAnimationFX sequentialAnimationFX = new SequentialAnimationFX(root, new FadeIn(), new Pulse());
        sequentialAnimationFX.play();
        alert.showAndWait();


    }


}
