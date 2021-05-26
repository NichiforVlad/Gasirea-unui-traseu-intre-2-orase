/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.NodAStar;
import Model.Solutie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author PC
 */
public class GBFS {
    private PriorityQueue<NodAStar> frontiera;
    private ArrayList<NodAStar> explorate;
    private Harta harta;
    private ArrayList<NodAStar> traseu;
    private static GBFS instanta;
    
    private GBFS() {
        frontiera = new PriorityQueue<>();
        explorate = new ArrayList<>();
        harta = Harta.getInstance();
        traseu = new ArrayList<>();
    }

    public static GBFS getInstance() {
        if (instanta == null) {
            instanta = new GBFS();
        }
        return instanta;
    }
    
    public String alg(String stareInitiala, String stareFinala) {
        reset();
        
        Solutie solutie = new Solutie();
        long timp = System.nanoTime(); 
        
        NodAStar initial = new NodAStar(stareInitiala);
        initial.setH(harta.distantaAproximativa(stareInitiala));
        frontiera.add(initial);

        while(!frontiera.isEmpty()) {
            NodAStar nodCurent = frontiera.remove();
            if (!nodCurent.getNume().equals(stareFinala)) {

                for (NodAStar temp:genereazaNodAStaruri(nodCurent)) {

                    //temp.setG(calculeazaG(nodCurent, temp));
                    temp.setH(calculeazaH(temp));
                    temp.setParinte(nodCurent.getNume());
                    temp.setNivel(nodCurent.getNivel() + 1);
  
                    if (!esteInExplorate(temp)) {
                        if (!esteInFrontiera(temp) || adaugaInFrontiera(temp))
                            frontiera.add(temp);
                    }
                }
                explorate.add(nodCurent);
            } else {
                solutie.setTraseu(calculeazaTraseu(explorate, nodCurent.getParinte()) + " " + nodCurent.getNume());
                solutie.setDistanta(nodCurent.getG());
                solutie.setNivel(nodCurent.getNivel());
                solutie.setTimpExecutie(System.nanoTime()-timp);
                solutie.setNumarNoduriExplorate(explorate.size());
                return solutie.toString();
            }
        }
        solutie.setTimpExecutie(System.nanoTime()-timp);
        solutie.setNumarNoduriExplorate(explorate.size());
        return solutie.toString();
    }
    
    
    private void gasesteTraseu(ArrayList<NodAStar> noduri, String parinte) {	
        if (parinte != null) {
            for (NodAStar nod:noduri) {
                if (nod.getNume().equals(parinte)) {
                    traseu.add(nod);
                    gasesteTraseu(noduri, nod.getParinte()); 
                    break;
                }
            }
        }
    }
   
    private String calculeazaTraseu(ArrayList<NodAStar> noduri, String parinte) {
        gasesteTraseu(noduri, parinte);
        StringBuilder sb = new StringBuilder();
        for (int i = traseu.size()-1; i >= 0; i--) {
            sb.append(traseu.get(i).getNume() + " ");
        }
        return sb.toString();
    }
    
    private int calculeazaH(NodAStar copil) {
        return copil.getG() + harta.distantaAproximativa(copil.getNume());
    }

    private int calculeazaG(NodAStar parinte, NodAStar copil) {
        return parinte.getG() + harta.costTranzitie(parinte.getNume(), copil.getNume());
    }

    private ArrayList<NodAStar> genereazaNodAStaruri(NodAStar parinte) {
        ArrayList<NodAStar> noduriGenerate = new ArrayList<>();
        ArrayList<String> x = harta.genereazaVecini(parinte.getNume());
        for (String s : x) {
                noduriGenerate.add(new NodAStar(s));
        }
        return noduriGenerate;
    }
 
    private boolean esteInFrontiera(NodAStar nod) {
        Iterator<NodAStar> it = frontiera.iterator();
        while (it.hasNext()) {
            if (it.next().getNume().equals(nod.getNume())) {
                return true;
            }    
        }
        return false;
    }
    
    private boolean esteInExplorate(NodAStar nod) {
        Iterator<NodAStar> it = explorate.iterator();
        while (it.hasNext()) {
            if (it.next().getNume().equals(nod.getNume())) {
                return true;
            }    
        }
        return false;
    }
    
    private boolean adaugaInFrontiera(NodAStar nod) {
        Iterator<NodAStar> it = frontiera.iterator();
        while (it.hasNext()) {
            NodAStar temp = it.next();
            if (temp.getNume().equals(nod.getNume())) {
                if (temp.getH() > nod.getH()) {
                    it.remove();
                    return true;
                }
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
