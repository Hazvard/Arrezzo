package vues;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import modeles.Arezzo;

import java.awt.event.ActionEvent;

public class VueClavier extends TilePane {

    private Arezzo arezzo;

    public VueClavier(Arezzo arezzo){
        this.arezzo = arezzo;
    }



    @FXML
    public void test(javafx.event.ActionEvent actionEvent) {
        System.out.println(actionEvent.toString());
    }
}
