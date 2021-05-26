
package Model;

public class Solutie {
    private int nivel;
    private String traseu;
    private long timpExecutie;
    private int distanta;
    private int numarNoduriExplorate;

    public int getNumarNoduriExplorate() {
        return numarNoduriExplorate;
    }

    public void setNumarNoduriExplorate(int numarNoduriExplorate) {
        this.numarNoduriExplorate = numarNoduriExplorate;
    }
    
    
    public int getDistanta() {
        return distanta;
    }

    public void setDistanta(int distanta) {
        this.distanta = distanta;
    }
    
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTraseu() {
        return traseu;
    }

    public void setTraseu(String traseu) {
        this.traseu = traseu;
    }

    public long getTimpExecutie() {
        return timpExecutie;
    }

    public void setTimpExecutie(long timpExecutie) {
        this.timpExecutie = timpExecutie;
    }
    
    @Override
    public String toString() {
        return 
                  "Timp executie: " + this.timpExecutie + "\n"
                + "Solutia s-a gasit la nivelul: " + this.nivel + "\n"
                + "Numarul de noduri explorate: " + this.numarNoduriExplorate + "\n"
                + "Traseul obtinut: " + this.traseu + "\n"
                + "Distanta: " + this.distanta + " (unitati)\n";
    }
}
