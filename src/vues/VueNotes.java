package vues;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import modeles.Arezzo;

public class VueNotes implements  Observateur{

    private Arezzo arezzo;
    @FXML
    private ListView<String> notes;

    public VueNotes (Arezzo arezzo){
        this.arezzo = arezzo;
        arezzo.ajouterObservateur(this);

        // Autoriser la séléection multiple
        notes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // renderer de cellules
        notes.setCellFactory(lv -> new CompoCell(arezzo));
    }

    @Override
    public void reagir() {
        notes.getItems().clear();
    }
}
