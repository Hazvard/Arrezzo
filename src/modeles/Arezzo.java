package modeles;

import java.util.ArrayList;

public class Arezzo {

    private ArrayList<String> partition;
    private int octave;
    private int duree;
    private int instrument;


    public Arezzo(){
        partition = new ArrayList<>();

        this.duree = 1;
        // 0 = croche
        // 1 = noire
        // 2 = blanche
        // 3 = ronde

        this.octave = 1;
        // 0 = Aïgu
        // 1 = Médium
        // 2 = Grave

        this.instrument = 0;
        // 0 = piano
        // 1 = Guitare
        // 2 = Saxophone
        // 3 = Trompette


    }

    public void ajouterNote(String note){
        StringBuilder creationNote = new StringBuilder();

        if(note.contains("S"))
            return;

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
            }

        }else if( octave ==1){
            if (note.equals("chut")){
                creationNote.append("z");
            }else{
                creationNote.append(note);
            }

        }else if (octave == 2){
            if (note.equals("chut")){
                creationNote.append("z");
            }else{
                creationNote.append(note + ",");
            }
        }


        if(duree == 0){

        }else if(duree == 1){

        }else if(duree == 2){

        }else if(duree == 3){

        }
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }
}
