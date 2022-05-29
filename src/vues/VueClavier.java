package vues;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import modeles.Arezzo;

public class VueClavier extends TilePane {

    private Arezzo arezzo;

    public VueClavier(Arezzo arezzo){
        this.arezzo = arezzo;
    }



    @FXML
    public void test(javafx.event.ActionEvent actionEvent) {
        System.out.println(actionEvent.toString());
    }

    public void reagirAuClic(ActionEvent actionEvent) {
        if(actionEvent.getSource().getClass().equals(Button.class)) {   //Pour vérifier que tu appuis bien sur un bouton
            Button bout = (Button) actionEvent.getSource(); // pour récupérer le bouton que tu appuis
            arezzo.ajouterNote(bout.getId());
        }
    }
}
