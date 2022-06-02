package vues;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modeles.Arezzo;

import javax.sound.midi.MidiUnavailableException;
import java.util.Optional;

public class VueMenus {

    private Arezzo arezzo;

    @FXML
    MenuItem quitter;

    @FXML
    Label titre;

    public VueMenus(Arezzo arezzo) {
        this.arezzo = arezzo;
    }

    public void quitter() {
        arezzo.quitter();
    }

    public void transposer() {
        arezzo.transposer();
    }

    public void renommer() {

        TextInputDialog dialog = new TextInputDialog(arezzo.getTitre());

        dialog.setTitle("Titre du morceau");
        dialog.setHeaderText("Entrer le titre du morceau :");
        dialog.setContentText("Titre :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            this.arezzo.renommer(name);
            titre.setText(name);
        });
    }

    public void nouveau() {
        arezzo.nouveau();
        arezzo.renommer("Nouveau Projet");
    }
}
