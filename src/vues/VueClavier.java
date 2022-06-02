package vues;

import exceptions.DureeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import modeles.Arezzo;

import java.util.ArrayList;

public class VueClavier{

    private Arezzo arezzo;

    public VueClavier(Arezzo arezzo){
        this.arezzo = arezzo;
    }

    public void reagirAuClic(ActionEvent actionEvent) {
        if(actionEvent.getSource().getClass().equals(Button.class)) {   //Pour vérifier que tu appuis bien sur un bouton
            Button bout = (Button) actionEvent.getSource(); // pour récupérer le bouton que tu appuis
            try {
                arezzo.ajouterNote(bout.getId());
            }catch (DureeException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ATTENTION");
                alert.setHeaderText("Impossible d'ajouter la note");
                alert.setContentText(e.getErreur());
                alert.showAndWait();

            }
        }
    }
}
