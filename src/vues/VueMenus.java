package vues;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import modeles.Arezzo;

public class VueMenus extends MenuBar {

    private Arezzo arezzo;

    @FXML
    MenuItem quitter;

    public VueMenus(Arezzo arezzo) {
        this.arezzo = arezzo;
    }

    public void quitter() {
        Platform.exit();
        arezzo.stop();
        System.exit(0);
    }
}
