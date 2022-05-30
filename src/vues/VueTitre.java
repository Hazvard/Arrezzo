package vues;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modeles.Arezzo;

public class VueTitre implements Observateur {

    private Arezzo arezzo;
    @FXML
    Label titre;

    public  VueTitre( Arezzo arezzo){
        this.arezzo = arezzo;
    }

    @Override
    public void reagir() {
        titre.setText(arezzo.getTitre());
    }
}
