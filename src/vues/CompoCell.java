package vues;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import modeles.Arezzo;

public class CompoCell extends ListCell<String> {
    @FXML
    private Label compo;

    @FXML
    private Label count;

    private Arezzo arezzo;

    public CompoCell(Arezzo arezzo){
        this.arezzo = arezzo;
        // load du fichier xml
    }

    public void updateItem(String item, boolean empty){
        // Maj des composants
    }



}
