package vues;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import modeles.Arezzo;

public class VueOption {

    private Arezzo arezzo;



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

            switch (bout.getId()){

                case "piano":
                    arezzo.setInstrument(0);
                    break;

                case "guitare":
                    arezzo.setInstrument(1);
                    break;

                case "saxophone":
                    arezzo.setInstrument(2);
                    break;

                case "trompette":
                    arezzo.setInstrument(3);
                    break;
            }
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
}
