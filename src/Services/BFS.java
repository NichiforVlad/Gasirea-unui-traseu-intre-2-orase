package Services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import Model.Nod;
import Model.Solutie;

public class BFS {
    private Queue<Nod> frontiera;
    private ArrayList<Nod> explorate;
    private ArrayList<Nod> traseu;
    private Harta harta;
    private static BFS instanta;
    
    public static BFS getInstance() {
        if (instanta == null) {
            instanta = new BFS();
        }
        return instanta;
    }
    
    private BFS() {
        harta = Harta.getInstance();
        explorate = new ArrayList<>();
        frontiera = new LinkedList<>();
        traseu = new ArrayList<>();
    }

    public String algBFS(String stareInitiala, String stareFinala) {
        reset();
        
        Solutie solutie = new Solutie();
        long timp = System.nanoTime(); 
        
        frontiera.add(new Nod(stareInitiala));

        do {
            Nod nodCurent = frontiera.remove();      
            if (!nodCurent.getNume().equals(stareFinala)) {

                for (String temp:harta.genereazaVecini(nodCurent.getNume())) {
                    if (temp.equals(stareFinala)) { 
                        solutie.setNivel(nodCurent.getNivel() + 1);
                        solutie.setTraseu(calculeazaTraseu(explorate, nodCurent.getParinte()) + " " + nodCurent.getNume() + " " + temp);
                        solutie.setTimpExecutie(System.nanoTime() - timp);
                        solutie.setNumarNoduriExplorate(explorate.size());
                        return solutie.toString(); 
                    }

                    if (!seAflaInExplorate(temp) && !seAflaInFrontiera(temp)) {
                        Nod nod = new Nod(temp);
                        nod.setParinte(nodCurent.getNume());
                        nod.setNivel(nodCurent.getNivel() + 1);

                        frontiera.add(nod);
                    }	
                }
                
                explorate.add(nodCurent);
            } else {
                solutie.setNivel(nodCurent.getNivel());
                solutie.setTraseu(calculeazaTraseu(explorate, nodCurent.getNume()));
                solutie.setTimpExecutie(System.nanoTime() - timp);
                solutie.setNumarNoduriExplorate(explorate.size());
                return solutie.toString();
            }	
        } while (!frontiera.isEmpty());
        
        solutie.setTimpExecutie(System.nanoTime() - timp);
        return solutie.toString();
    }

    private void gasesteTraseu(ArrayList<Nod> noduri, String parinte) {	
        if (parinte != null) {
            for (Nod nod:noduri) {
                if (nod.getNume().equals(parinte)) {
                    traseu.add(nod);
                    gasesteTraseu(noduri, nod.getParinte()); 
                    break;
                }
            }
        }
    }
   
    private String calculeazaTraseu(ArrayList<Nod> noduri, String parinte) {
        gasesteTraseu(noduri, parinte);
        StringBuilder sb = new StringBuilder();
        for (int i = traseu.size()-1; i >= 0; i--) {
            sb.append(traseu.get(i).getNume() + " ");
        }
        return sb.toString();
    }

    private boolean seAflaInFrontiera(String x) {
        for (Nod nod:frontiera) {
            if (nod.getNume().equals(x)) {
                    return true;
            }
        }
        return false;
    }

    private boolean seAflaInExplorate(String x) {
        for (Nod nod:explorate) {
            if (nod.getNume().equals(x)) {
                    return true;
            }
        }
        return false;
    }
    
    private void reset() {
        frontiera.clear();
        explorate.clear();
        traseu.clear();
    }
}

