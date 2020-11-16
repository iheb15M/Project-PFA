package eduspace;

import eduspace.vendor.DatabaseConn;
import eduspace.vendor.Routing;
import javafx.application.Application;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


public class Main extends Application {

    @Override

    public void start(Stage primaryStage) throws Exception {
        Routing.home(primaryStage);
    }


    public static void main(String[] args) {

        launch(args);


    }
}
