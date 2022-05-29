package modeles;

import exceptions.DureeException;
import javafx.scene.image.Image;
import partition.Partition;
import vues.Observateur;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.ArrayList;

public class Arezzo {

    private ArrayList<Observateur> observateurs;
    private StringBuilder partitionBuilder;
    private Synthesizer synthesizer;
    private Partition partition;
    private int quartTemps;
    private boolean demiTemps;
    private int octave;
    private int duree;
    private double volume;
    private int tempo;
    private String instrument;


    public Arezzo() throws MidiUnavailableException {
        partitionBuilder = new StringBuilder();
        synthesizer = MidiSystem.getSynthesizer();
        partition = new Partition(synthesizer);
        observateurs = new ArrayList<>();
        this.instrument = "Piano";
        demiTemps = false;
        quartTemps = 0;
        volume = 25.0;
        tempo = 45;




        this.duree = 1;
        // 0 = croche
        // 1 = noire
        // 2 = blanche
        // 3 = ronde

        this.octave = 1;
        // 0 = Aïgu
        // 1 = Médium
        // 2 = Grave


    }

    public void ajouterNote(String note) throws DureeException {
        StringBuilder creationNote = new StringBuilder();


        // On commence par l'octave
        if (octave == 0){
            if(note.equals("A")){
                creationNote.append("a");

            }else if (note.equals("B")){
                creationNote.append("b");

            }else if (note.equals("C")){
                creationNote.append("c");

            }else if (note.equals("D")){
                creationNote.append("d");

            }else if (note.equals("E")){
                creationNote.append("e");

            }else if (note.equals("F")){
                creationNote.append("f");

            }else if (note.equals("G")){
                creationNote.append("g");

            }else if (note.equals("chut")){
                creationNote.append("z");

            }else if (note.equals("CS")){
                creationNote.append("^c");

            }else if (note.equals("DS")){
                creationNote.append("^d");

            }else if (note.equals("FS")){
                creationNote.append("^f");

            }else if (note.equals("GS")){
                creationNote.append("^g");

            }else if (note.equals("AS")){
                creationNote.append("^a");

            }

        }else if( octave ==1){
            if (note.equals("chut")){
                creationNote.append("z");
            }else if(note.contains("S")){

                if (note.equals("CS")){
                    creationNote.append("^C");

                }else if (note.equals("DS")){
                    creationNote.append("^D");

                }else if (note.equals("FS")){
                    creationNote.append("^F");

                }else if (note.equals("GS")){
                    creationNote.append("^G");

                }else if (note.equals("AS")){
                    creationNote.append("^A");

                }

            }else{
                creationNote.append(note);
            }


        }else if (octave == 2){
            if (note.equals("chut")){
                creationNote.append("z");

            }else if(note.contains("S")){

                if (note.equals("CS")){
                    creationNote.append("^C,");

                }else if (note.equals("DS")){
                    creationNote.append("^D,");

                }else if (note.equals("FS")){
                    creationNote.append("^F,");

                }else if (note.equals("GS")){
                    creationNote.append("^G,");

                }else if (note.equals("AS")){
                    creationNote.append("^A,");

                }

            }else{
                creationNote.append(note + ",");
            }
        }

        // Ensuite on fait la durée
        if(duree == 0){
            if (demiTemps){
                demiTemps = false;
                quartTemps++;
            }else {
                demiTemps = true;
            }

            if(note.equals("chut")){
                creationNote.append("1/2");
            }else{
                creationNote.append("/");
            }

            // Si tout est en ordre on ajoute la note
            partitionBuilder.append(creationNote);
            jouerUnSon(creationNote.toString());


        }else if(duree == 1){

            quartTemps++;
            if(note.equals("chut")){
                creationNote.append("1");
            }

            partitionBuilder.append(creationNote);
            jouerUnSon(creationNote.toString());


        }else if(duree == 2){
            if(quartTemps + 2 <= 4){

                creationNote.append("2");
                partitionBuilder.append(creationNote);
                jouerUnSon(creationNote.toString());
            }else{
                throw new DureeException("La note de rentre pas dans le quart temps");
            }

        }else if(duree == 3){
            if(quartTemps + 4 <= 4){

                creationNote.append("4");
                partitionBuilder.append(creationNote);
                jouerUnSon(creationNote.toString());
            }else{
                throw new DureeException("La note de rentre pas dans le quart temps");
            }
        }

        // On remet quartTemps à 0 si on a finit le quart
        if(quartTemps == 4)
            quartTemps = 0;

        System.out.println(partition.toString());
        reagir();
    }

    public void jouerUnSon(String note){
        partition.play(note);

    }

    public void ajouterObservateur(Observateur observateur){
        observateurs.add(observateur);
    }

    public void reagir(){
        for (Observateur observateur: this.observateurs)
            observateur.reagir();
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public void setVolume( int volume) {
        this.volume = volume;
        partition.setVolume(this.volume);
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
        partition.setTempo( this.tempo);
    }

    public Image getImage(){
        partition.setMelodie(partitionBuilder.toString());
        return partition.getImage();
    }
}
