package modeles;

import exceptions.DureeException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import partition.Partition;
import vues.Observateur;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Arezzo {

    private ArrayList<Observateur> observateurs;
    private ArrayList<String> listeDeNote;
    private StringBuilder partitionBuilder;
    private Synthesizer synthesizer;
    private Partition partition;
    private int quartTemps;
    private int octave;
    private int duree;
    private String titre;


    public Arezzo() throws MidiUnavailableException {

        partitionBuilder = new StringBuilder();
        listeDeNote = new ArrayList<>();
        synthesizer = MidiSystem.getSynthesizer();
        partition = new Partition(synthesizer);
        partition.setPreferedMaxWidth(900);
        observateurs = new ArrayList<>();
        titre = "Nouveau Projet";
        quartTemps = 0;




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
            quartTemps++;

            if(note.equals("chut")){
                creationNote.append("1/2");
            }else{
                creationNote.append("/");
            }

            // Si tout est en ordre on ajoute la note

            ajouterNoteBis(creationNote);


        }else if(duree == 1){
            if(quartTemps + 2 <= 8){
                quartTemps += 2;


                if(note.equals("chut")){
                    creationNote.append("1");
                }

                ajouterNoteBis(creationNote);
            }else{
            throw new DureeException("La note de rentre pas dans le quart temps");
        }


        }else if(duree == 2){
            if(quartTemps + 4 <= 8){

                quartTemps +=4;
                creationNote.append("2");
                ajouterNoteBis(creationNote);
            }else{
                throw new DureeException("La note de rentre pas dans le quart temps");
            }

        }else if(duree == 3){
            if(quartTemps + 8 <= 8){

                quartTemps +=8;
                creationNote.append("4");
                ajouterNoteBis(creationNote);
            }else{
                throw new DureeException("La note de rentre pas dans le quart temps");
            }
        }

        // On remet quartTemps à 0 si on a finit le quart
        if(quartTemps == 8){
            quartTemps = 0;
            partitionBuilder.append("|");
            //listeDeNote.add("|");
        }


        reagir();
    }

    private void ajouterNoteBis(StringBuilder note){
        partitionBuilder.append(note);
        listeDeNote.add(note.toString());
        jouerUnSon(note.toString());
    }

    public void jouerUnSon(String note){
        partition.play(note);

    }

    public String getTitre(){
        return titre;
    }

    public void jouerLaMelodie(){
        partition.setMelodie(partitionBuilder.toString());
        partition.play();
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
        partition.setInstrument(instrument);
    }

    public void setVolume( double volume) {
        partition.setVolume(volume);
    }

    public void setTempo(int tempo) {
        partition.setTempo(tempo);
    }

    public Image getImage(){
        partition.setMelodie(partitionBuilder.toString());
        return partition.getImage();
    }

    public void stop(){
        partition.close();
    }

    public void quitter(){
        Platform.exit();
        stop();
        System.exit(0);
    }

    public void renommer(String name){
        titre = name;
    }

    public void transposer() {


        ListIterator itr = listeDeNote.listIterator();
        while (itr.hasNext()){
            String note = itr.next().toString();
            StringBuilder notecreation = new StringBuilder(note);

            if (note.contains("^C")){
                notecreation.replace(0, 2, "D");
            }else if (note.contains("^D")){
                notecreation.replace(0, 2, "E");
            }else if (note.contains("^F")){
                notecreation.replace(0, 2, "G");
            }else if (note.contains("^G")){
                notecreation.replace(0, 2, "A");
            }else if (note.contains("^A")){
                notecreation.replace(0, 2, "B");
            }else if (note.contains("A")){
                notecreation.replace(0, 1, "^A");
            }else if (note.contains("B")){
                notecreation.replace(0, 1, "C");
            }else if (note.contains("C")){
                notecreation.replace(0, 1, "^C");
            }else if (note.contains("D")){
                notecreation.replace(0, 1, "^D");
            }else if (note.contains("E")){
                notecreation.replace(0, 1, "F");
            }else if (note.contains("F")){
                notecreation.replace(0, 1, "^F");
            }else if (note.contains("G")){
                notecreation.replace(0, 1, "^G"); // Maintenant pour les minuscules
            }else if (note.contains("^c")){
                notecreation.replace(0, 2, "d");
            }else if (note.contains("^d")){
                notecreation.replace(0, 2, "e");
            }else if (note.contains("^f")){
                notecreation.replace(0, 2, "g");
            }else if (note.contains("^g")){
                notecreation.replace(0, 2, "a");
            }else if (note.contains("^a")){
                notecreation.replace(0, 2, "b");
            }else if (note.contains("a")){
                notecreation.replace(0, 1, "^a");
            }else if (note.contains("b")){
                notecreation.replace(0, 1, "c");
            }else if (note.contains("c")){
                notecreation.replace(0, 1, "^c");
            }else if (note.contains("d")){
                notecreation.replace(0, 1, "^d");
            }else if (note.contains("e")){
                notecreation.replace(0, 1, "f");
            }else if (note.contains("f")){
                notecreation.replace(0, 1, "^f");
            }else if (note.contains("g")){
                notecreation.replace(0, 1, "^g");
            }

            itr.set(notecreation.toString());
        }

        this.listeVersPartionBuilder();

        partition.setMelodie(partitionBuilder.toString());
        reagir();
    }

    public void augmenterDemiTon(ObservableList<Integer> liste){
        Iterator<Integer> iter = liste.iterator();
        while (iter.hasNext()){
            Integer position = iter.next();
            String note = listeDeNote.get(position);
            StringBuilder notecreation = new StringBuilder(listeDeNote.get(position));

            if (note.contains("^C")){
                notecreation.replace(0, 2, "D");
            }else if (note.contains("^D")){
                notecreation.replace(0, 2, "E");
            }else if (note.contains("^F")){
                notecreation.replace(0, 2, "G");
            }else if (note.contains("^G")){
                notecreation.replace(0, 2, "A");
            }else if (note.contains("^A")){
                notecreation.replace(0, 2, "B");
            }else if (note.contains("A")){
                notecreation.replace(0, 1, "^A");
            }else if (note.contains("B")){
                notecreation.replace(0, 1, "C");
            }else if (note.contains("C")){
                notecreation.replace(0, 1, "^C");
            }else if (note.contains("D")){
                notecreation.replace(0, 1, "^D");
            }else if (note.contains("E")){
                notecreation.replace(0, 1, "F");
            }else if (note.contains("F")){
                notecreation.replace(0, 1, "^F");
            }else if (note.contains("G")){
                notecreation.replace(0, 1, "^G"); // Maintenant pour les minuscules
            }else if (note.contains("^c")){
                notecreation.replace(0, 2, "d");
            }else if (note.contains("^d")){
                notecreation.replace(0, 2, "e");
            }else if (note.contains("^f")){
                notecreation.replace(0, 2, "g");
            }else if (note.contains("^g")){
                notecreation.replace(0, 2, "a");
            }else if (note.contains("^a")){
                notecreation.replace(0, 2, "b");
            }else if (note.contains("a")){
                notecreation.replace(0, 1, "^a");
            }else if (note.contains("b")){
                notecreation.replace(0, 1, "c");
            }else if (note.contains("c")){
                notecreation.replace(0, 1, "^c");
            }else if (note.contains("d")){
                notecreation.replace(0, 1, "^d");
            }else if (note.contains("e")){
                notecreation.replace(0, 1, "f");
            }else if (note.contains("f")){
                notecreation.replace(0, 1, "^f");
            }else if (note.contains("g")){
                notecreation.replace(0, 1, "^g");
            }

            listeDeNote.remove( (int) position);
            listeDeNote.add(position, notecreation.toString());

        }

        this.listeVersPartionBuilder();

        partition.setMelodie(partitionBuilder.toString());
        reagir();

    }

    public void descendreDemiTon(ObservableList<Integer> liste){
        Iterator<Integer> iter = liste.iterator();
        while (iter.hasNext()){
            Integer position = iter.next();
            String note = listeDeNote.get(position);
            StringBuilder notecreation = new StringBuilder(listeDeNote.get(position));

            if (note.contains("^C")){
                notecreation.replace(0, 2, "C");
            }else if (note.contains("^D")){
                notecreation.replace(0, 2, "D");
            }else if (note.contains("^F")){
                notecreation.replace(0, 2, "F");
            }else if (note.contains("^G")){
                notecreation.replace(0, 2, "G");
            }else if (note.contains("^A")){
                notecreation.replace(0, 2, "A");
            }else if (note.contains("A")){
                notecreation.replace(0, 1, "^G");
            }else if (note.contains("B")){
                notecreation.replace(0, 1, "^A");
            }else if (note.contains("C")){
                notecreation.replace(0, 1, "B");
            }else if (note.contains("D")){
                notecreation.replace(0, 1, "^C");
            }else if (note.contains("E")){
                notecreation.replace(0, 1, "^D");
            }else if (note.contains("F")){
                notecreation.replace(0, 1, "E");
            }else if (note.contains("G")){
                notecreation.replace(0, 1, "^F"); // Maintenant pour les minuscules
            }else if (note.contains("^c")){
                notecreation.replace(0, 2, "c");
            }else if (note.contains("^d")){
                notecreation.replace(0, 2, "d");
            }else if (note.contains("^f")){
                notecreation.replace(0, 2, "f");
            }else if (note.contains("^g")){
                notecreation.replace(0, 2, "g");
            }else if (note.contains("^a")){
                notecreation.replace(0, 2, "a");
            }else if (note.contains("a")){
                notecreation.replace(0, 1, "^g");
            }else if (note.contains("b")){
                notecreation.replace(0, 1, "^a");
            }else if (note.contains("c")){
                notecreation.replace(0, 1, "b");
            }else if (note.contains("d")){
                notecreation.replace(0, 1, "^c");
            }else if (note.contains("e")){
                notecreation.replace(0, 1, "^d");
            }else if (note.contains("f")){
                notecreation.replace(0, 1, "e");
            }else if (note.contains("g")){
                notecreation.replace(0, 1, "^f");
            }

            listeDeNote.remove( (int) position);
            listeDeNote.add(position, notecreation.toString());

        }

        this.listeVersPartionBuilder();

        partition.setMelodie(partitionBuilder.toString());
        reagir();
    }



    public void effacerNotes(ObservableList<Integer> liste){
        Iterator<Integer> iter = liste.iterator();
        while (iter.hasNext()){
            Integer position = iter.next();
            String note = listeDeNote.get(position);
            if(note.contains("z") && position == (listeDeNote.size() - 1)){

                listeDeNote.remove((int) position);

            }else if(note.contains("/")){

                listeDeNote.remove((int) position);
                listeDeNote.add(position, "z1/2");

            }else if(note.contains("2")){

                listeDeNote.remove((int) position);
                listeDeNote.add(position, "z2");

            }else if(note.contains("4")){

                listeDeNote.remove((int) position);
                listeDeNote.add(position, "z4");

            }else {

                listeDeNote.remove((int) position);
                listeDeNote.add(position, "z1");
            }

        }
        listeVersPartionBuilder();
        partition.setMelodie(partitionBuilder.toString());
        reagir();


    }

    public void listeVersPartionBuilder(){
        partitionBuilder.setLength(0);
        int compteur = 0;
        for (String note : listeDeNote){

            partitionBuilder.append(note); // On ajoute la note

            // On place les barres

            if(note.contains("/")){
                compteur ++;
            }else if (note.contains("2")){
                compteur += 4;
            }else if (note.contains("4")){
                compteur += 8;
            }else{
                compteur += 2;
            }

            if( compteur == 8){
                compteur = 0;
                partitionBuilder.append("|");
            }
        }
    }

    public void nouveau(){
        partitionBuilder = new StringBuilder();
        listeDeNote = new ArrayList<>();
        titre = "Nouveau Projet";
        quartTemps = 0;
        partition.close();
        try {
            synthesizer = MidiSystem.getSynthesizer();
        }catch (Exception e){}
        partition = new Partition(synthesizer);
        reagir();
    }

    public ArrayList<String> getListeDeNote() {
        return listeDeNote;
    }

}
