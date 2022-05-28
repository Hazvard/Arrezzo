package vues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import modeles.Arezzo;

public class VueMenus extends MenuBar {

    private Arezzo arezzo;

    private Menu melodie;
    private Menu outils;

    private MenuItem ouvrir;
    private MenuItem nouveau;
    private MenuItem enregistrer;
    private MenuItem quitter;

    private MenuItem renommer;
    private MenuItem transposer;

    public VueMenus(Arezzo arezzo) {
        this.arezzo = arezzo;

        melodie = new Menu("Mélodie");
        outils = new Menu("Outils");

        nouveau = new MenuItem("Nouveau");
        ouvrir = new MenuItem("Ouvrir");
        enregistrer = new MenuItem("Enregistrer sous ...");
        quitter = new MenuItem("Quitter");

        renommer = new MenuItem("Renommer");
        transposer = new MenuItem("Transposer");

        // Création des écouteurs :

        // On ajoute tout
        melodie.getItems().addAll(nouveau, ouvrir, enregistrer, quitter);
        outils.getItems().addAll(renommer, transposer);

        this.getMenus().addAll(melodie, outils);
    }
}
