package pl.codeme.jeeb.e2.bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ATMInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/ATMForm.fxml").toURI().toURL());
        Scene scene = new Scene(root, 530, 150);

        primaryStage.setTitle("ATM Bank");
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("img/codeme_n.jpg")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
