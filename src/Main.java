import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modeles.Arezzo;
import vues.*;

import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws IOException, MidiUnavailableException {
        Arezzo arezzo = new Arezzo();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("root.fxml"));

        VueMenus vueMenus = new VueMenus(arezzo);
        VueClavier vueClavier = new VueClavier(arezzo);
        VuePartition vuePartition = new VuePartition(arezzo);
        VueOption vueOption = new VueOption(arezzo);
        VueButton vueButton = new VueButton(arezzo, primaryStage);

        loader.setControllerFactory(ic -> {
            if (ic.equals(VueMenus.class)) return vueMenus;
            else if (ic.equals(VueClavier.class)) return vueClavier;
            else if (ic.equals(VuePartition.class)) return vuePartition;
            else if (ic.equals(VueOption.class)) return  vueOption;
            else if (ic.equals(VueButton.class)) return  vueButton;
            else // par défaut...
            return null ;
        });


        primaryStage.setScene(new Scene(loader.load(), 1600, 900));
        primaryStage.getIcons().add(new Image("/images/logo_azzero.png"));
        primaryStage.setTitle("Azzero");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
