package vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
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
    }

    private void remplirCellule(String item){


        // On initialise l'octave
        if(item.contains("z")){
            octave.setText("");
        } else if(item.contains(",")){
            octave.setText("GRAVE");
        }else if(item.contains("A") || item.contains("B") || item.contains("C") || item.contains("D") ||
                item.contains("E") || item.contains("F") || item.contains("G")){
            octave.setText("MEDIUM");
        }else{
            octave.setText("AIGÜE");
        }

        // On créer la note
        StringBuilder noteSimple;
        boolean diese = false;

        if(item.contains("z")){
            noteSimple = new StringBuilder("Silence");
        }else if(item.contains("^") || item.contains("_")){
            diese = true;
        }

        if (item.contains("A") || item.contains("a")){
            noteSimple = new StringBuilder("La");
        }else if (item.contains("B") || item.contains("b")){
            noteSimple = new StringBuilder("Si");
        }else if (item.contains("C") || item.contains("c")){
            noteSimple = new StringBuilder("Do");
        }else if (item.contains("D") || item.contains("d")){
            noteSimple = new StringBuilder("Ré");
        }else if (item.contains("E") || item.contains("e")){
            noteSimple = new StringBuilder("Mi");
        }else if (item.contains("F") || item.contains("f")){
            noteSimple = new StringBuilder("Fa");
        }else if(item.contains("G") || item.contains("g")){
            noteSimple = new StringBuilder("Sol");
        } else {
            noteSimple = new StringBuilder("Silence");
        }

        if(diese){
            noteSimple.append("#");
        }
        note.setText(noteSimple.toString());

        // On place l'image

        if (item.contains("z")){

            if(item.contains("/")){
                imageDuree.setImage(new Image("/images/demiSoupir.png"));
            }else if(item.contains("1")){
                imageDuree.setImage(new Image("/images/soupir.png"));
            }else if(item.contains("2")){
                imageDuree.setImage(new Image("/images/demiPause.png"));
            }else if(item.contains("3")){
                imageDuree.setImage(new Image("/images/demiPause.png"));
            }else if(item.contains("4")){
                imageDuree.setImage(new Image("/images/pause.png"));
            }

        }else if(item.contains("/")){
            imageDuree.setImage(new Image("/images/croche.png"));
        }else if(item.contains("2")){
            imageDuree.setImage(new Image("/images/blanche.png"));
        }else if(item.contains("4")){
            imageDuree.setImage(new Image("/images/ronde.png"));
        }else{
            imageDuree.setImage(new Image("/images/noire.png"));
        }



    }


    @Override
    public void updateItem(String item, boolean empty) {
        // mise à jour des composants
        super.updateItem(item, empty);


        if (item == null || empty){
            setText(null);
            setGraphic(null);
        } else {

            //System.out.println(octave.toString());

            // On récupère l'item pour créer la cellule



            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/vues/compocell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            remplirCellule(item);

            setText(null);
            setGraphic(box);

        }
    }

}
