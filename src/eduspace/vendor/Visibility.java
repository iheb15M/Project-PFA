package eduspace.vendor;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Visibility {
    public static void show(Node node) {
        node.setVisible(true);
        new FadeIn(node).setSpeed(0.4).play();
    }

    public static void hide(Node node) {
        node.setVisible(true);
        new FadeOut(node).setSpeed(0.4).play();
    }

    public static Node replace(String type, Node n1, String classe) {
        switch (type) {
            case "TextField":
                TextField t = new TextField();
                t.setLayoutX(n1.getLayoutX());
                t.setLayoutY(n1.getLayoutY());
                t.setId(n1.getId());
                t.getStyleClass().add(classe);
                return t;
            case "ChoiceBox":
                ChoiceBox c = new ChoiceBox();
                c.setLayoutX(n1.getLayoutX());
                c.setLayoutY(n1.getLayoutY());
                c.setId(n1.getId());
                c.getStyleClass().add(classe);
                return c;
            case "Button":
                Button b = new Button();
                b.setLayoutX(n1.getLayoutX());
                b.setLayoutY(n1.getLayoutY());
                b.setId(n1.getId());
                b.getStyleClass().add(classe);
                return b;
            default:
                return null;
        }


    }
}
