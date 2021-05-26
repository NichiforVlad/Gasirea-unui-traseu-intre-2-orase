
package Controller;

import Model.Solutie;
import Services.AStar;
import Services.BFS;
import Services.Harta;
import Services.CitireFisier;
import Services.DFS;
import Services.GBFS;
import java.util.HashMap;
import view.Gui;

public class Controller {
    private static Controller instanta;
    private Gui gui;
    private CitireFisier citireFisier;
    private Harta harta;
    
    private Controller() {
        gui = new Gui(this);
        citireFisier = CitireFisier.getInstance();
        harta = Harta.getInstance();
    }
    
    public static Controller getInstance() {
        if (instanta == null) {
            instanta = new Controller();
        }
        return instanta;
    }
    
    public void cerePornireaAplicatiei() {
        gui.afiseaza();
    }
    
    public void incarcaFisier(String cale) {
        citireFisier.citireFisier(cale);
        gui.seteazaComboBoxuri(harta.getOrase());
    }

    public void aplicaAlgoritm(String algoritm, String stareInceput, String stareSfarsit) {
        String rezultat = null;
        if (algoritm.equals("BFS")) {
            rezultat = BFS.getInstance().algBFS(stareInceput, stareSfarsit);
        } else if (algoritm.equals("A*")){
            rezultat = AStar.getInstance().alg(stareInceput, stareSfarsit);
        } else if (algoritm.equals("DFS")) {
            rezultat = DFS.getInstance().algDFS(stareInceput, stareSfarsit);
        } else {
            rezultat = GBFS.getInstance().alg(stareInceput, stareSfarsit);
        }
        gui.completeazaTextArea(rezultat);
    }
}
