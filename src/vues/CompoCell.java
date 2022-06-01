package vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import modeles.Arezzo;

import java.io.IOException;

public class CompoCell extends ListCell<String> {

    @FXML
    private Label octave ;
    @FXML
    private Label note ;
    @FXML
    private ImageView imageDuree ;
    @FXML
    private HBox box;

    private Arezzo arezzo ;
    private FXMLLoader loader;

    public CompoCell(Arezzo arezzo){
        this.arezzo = arezzo;

        setText(null);
        setGraphic(null);

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("/vues/compocell.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setText(null);
        setGraphic(box);
    }


    @Override
    public void updateItem(String item, boolean empty) {
        // mise à jour des composants
        super.updateItem(item, empty);


        if (item == null || empty){
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/vues/compocell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            setText(null);
            setGraphic(box);

        }
    }

}
