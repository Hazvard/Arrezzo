package vues;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modeles.Arezzo;

import java.io.IOException;

public class VueButton {

    private Arezzo arezzo;
    private Stage stageParent;

    public VueButton(Arezzo arezzo, Stage stage){

        this.arezzo = arezzo;
        this.stageParent = stage;

    }

    public void boutonactive() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vues/listenotes.fxml"));
        loader.setControllerFactory(IC -> new VueListeNotes(arezzo));

        Scene listeNotes;

        try {
            listeNotes = new Scene(loader.load(), 400, 800);
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Stage affichageListeNotes = new Stage();
        affichageListeNotes.setScene(listeNotes);
        affichageListeNotes.initOwner(stageParent);
        affichageListeNotes.setTitle("Liste de notes");
        affichageListeNotes.getIcons().add(new Image("/images/logo_azzero.png"));
        affichageListeNotes.showAndWait();
    }
}
