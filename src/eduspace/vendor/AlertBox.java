package eduspace.vendor;

import animatefx.animation.*;
import animatefx.util.SequentialAnimationFX;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AlertBox {
    public static void display(String Title,String Message) throws IOException {
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
        Button closeBtn = (Button) scene.lookup("#close") ;
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
