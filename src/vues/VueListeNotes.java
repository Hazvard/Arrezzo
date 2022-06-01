package vues;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import modeles.Arezzo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VueListeNotes extends ListCell<String> implements Initializable, Observateur {
    @FXML
    private ListView liste;

    @FXML
    private Label count;

    private Arezzo arezzo;
    ObservableList listeDeNotes;

    public VueListeNotes(Arezzo arezzo){
        this.arezzo = arezzo;
        arezzo.ajouterObservateur(this);

        System.out.println("aaaa");

        listeDeNotes = FXCollections.observableArrayList(arezzo.getListeDeNote());

    }

    public void updateItem(String item, boolean empty){
        // Maj des composants
    }

    @Override
    public void reagir() {
        listeDeNotes = FXCollections.observableArrayList(arezzo.getListeDeNote());
        liste.setItems(listeDeNotes);
        liste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeDeNotes = FXCollections.observableArrayList(arezzo.getListeDeNote());
        liste.setItems(listeDeNotes);
        liste.setCellFactory((listView -> new CompoCell(arezzo)));
        liste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
