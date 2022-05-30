package vues;

import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import modeles.Arezzo;

public class VueOption {

    private Arezzo arezzo;

    @FXML
    Button play;

    @FXML
    Slider volume;

    @FXML
    Slider tempo;



    public VueOption(Arezzo arezzo){
        this.arezzo = arezzo;



    }



    public void setDuree(ActionEvent actionEvent) {
        if(actionEvent.getSource().getClass().equals(RadioButton.class)) {   //Pour vérifier que tu appuis bien sur un bouton
            RadioButton bout = (RadioButton) actionEvent.getSource(); // pour récupérer le bouton que tu appuis
            
            switch (bout.getId()){

                case "croche":
                    arezzo.setDuree(0);
                    break;

                case "noire":
                    arezzo.setDuree(1);
                    break;

                case "blanche":
                    arezzo.setDuree(2);
                    break;

                case "ronde":
                    arezzo.setDuree(3);
                    break;
            }
        }
    }

    public void setInstrument(ActionEvent actionEvent) {
        if(actionEvent.getSource().getClass().equals(RadioButton.class)) {
            RadioButton bout = (RadioButton) actionEvent.getSource();
            arezzo.setInstrument(bout.getId());
        }

    }

    public void setOctave(ActionEvent actionEvent) {
        if(actionEvent.getSource().getClass().equals(RadioButton.class)) {
            RadioButton bout = (RadioButton) actionEvent.getSource();

            switch (bout.getId()){

                case "aigu":
                    arezzo.setOctave(0);
                    break;

                case "medium":
                    arezzo.setOctave(1);
                    break;

                case "grave":
                    arezzo.setOctave(2);
                    break;
            }
        }
    }

    public void play(ActionEvent actionEvent) {
            arezzo.jouerLaMelodie();
            Duration duration = Duration.millis(1500);
            RotateTransition rotateTransition = new RotateTransition(duration, play);
            rotateTransition.setByAngle(720);
            rotateTransition.play();



    }

    public void setVolume() {
        arezzo.setVolume(volume.getValue());
    }

    public void setTempo() {
        arezzo.setTempo( (int) tempo.getValue());
    }
}
