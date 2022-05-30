package modeles;

import exceptions.DureeException;
import javafx.scene.image.Image;
import partition.Partition;
import vues.Observateur;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.ArrayList;
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

        //System.out.println(creationNote);
        //System.out.println(partitionBuilder.toString());
        //System.out.println(quartTemps);
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

    public void setTitre(String titre){
        this.titre = titre;
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

    public void renommer(String name){
        titre = name;
    }

    public void transposer() {


        ListIterator itr = listeDeNote.listIterator();
        while (itr.hasNext()){
            String note = itr.next().toString();
            if (note.contains("^C")){
                itr.set("D");
            }else if (note.contains("^D")){
                itr.set("E");
            }else if (note.contains("^F")){
                itr.set("G");
            }else if (note.contains("^G")){
                itr.set("A");
            }else if (note.contains("^A")){
                itr.set("B");
            }else if (note.contains("A")){
                itr.set("^A");
            }else if (note.contains("B")){
                itr.set("C");
            }else if (note.contains("C")){
                itr.set("^C");
            }else if (note.contains("D")){
                itr.set("^D");
            }else if (note.contains("E")){
                itr.set("F");
            }else if (note.contains("F")){
                itr.set("^F");
            }else if (note.contains("G")){
                itr.set("^G"); // Maintenant pour les minuscules
            }else if (note.contains("^c")){
                itr.set("d");
            }else if (note.contains("^d")){
                itr.set("e");
            }else if (note.contains("^f")){
                itr.set("g");
            }else if (note.contains("^g")){
                itr.set("a");
            }else if (note.contains("^a")){
                itr.set("b");
            }else if (note.contains("a")){
                itr.set("^a");
            }else if (note.contains("b")){
                itr.set("c");
            }else if (note.contains("c")){
                itr.set("^c");
            }else if (note.contains("d")){
                itr.set("^d");
            }else if (note.contains("e")){
                itr.set("f");
            }else if (note.contains("f")){
                itr.set("^f");
            }else if (note.contains("g")){
                itr.set("^g");
            }
        }

        this.listeVersPartionBuilder();

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
}
