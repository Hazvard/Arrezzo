package vues;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import modeles.Arezzo;

import java.awt.*;
import java.awt.Menu;
import java.net.URL;
import java.util.ResourceBundle;

public class VueListeNotes implements Initializable, Observateur {
    @FXML
    private ListView<String> liste;

    private Arezzo arezzo;
    private ObservableList listeDeNotes;

    private ContextMenu menu;
    private MenuItem effacer;
    private MenuItem monter;
    private MenuItem descendre;

    public VueListeNotes(Arezzo arezzo){
        this.arezzo = arezzo;
        arezzo.ajouterObservateur(this);

        listeDeNotes = FXCollections.observableArrayList(arezzo.getListeDeNote());

        menu = new ContextMenu();
        effacer = new MenuItem("Effacer");
        effacer.setOnAction(actionEvent ->
                arezzo.effacerNotes(liste.getSelectionModel().getSelectedIndices())
                );
        monter = new MenuItem("Monter d'un demi-ton");
        monter.setOnAction(actionEvent ->
                arezzo.augmenterDemiTon( liste.getSelectionModel().getSelectedIndices()));

        descendre = new MenuItem("Descendre d'un demi-ton");
        descendre.setOnAction(actionEvent ->
                arezzo.descendreDemiTon(liste.getSelectionModel().getSelectedIndices()));

        menu.getItems().addAll(effacer,monter,descendre);



    }

    @Override
    public void reagir() {
        listeDeNotes = FXCollections.observableArrayList(arezzo.getListeDeNote());
        liste.setItems(listeDeNotes);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeDeNotes = FXCollections.observableArrayList(arezzo.getListeDeNote());
        liste.setItems(listeDeNotes);
        liste.setCellFactory((listView -> new CompoCell()));
        liste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        liste.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue )->
                        liste.setContextMenu(menu));

    }
}
