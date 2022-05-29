package vues;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modeles.Arezzo;
import partition.Partition;

public class VuePartition implements Observateur {

    private Arezzo arezzo;
    @FXML
    ImageView imagepartition;

    public VuePartition( Arezzo arezzo){
        this.arezzo = arezzo;
        arezzo.ajouterObservateur(this);
    }




    @Override
    public void reagir() {
        imagepartition.setImage(arezzo.getImage());
    }
}
