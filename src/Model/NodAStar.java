package Model;

public class NodAStar extends Nod implements Comparable<NodAStar>{

    // g = parinte.g + costul tranzitiei de la parinte la copil
    private int g;
    // h = g + distanta nodului pana in linie dreapta
    private int h;

    public NodAStar(String nume) {
        super(nume);
    }

    public NodAStar(String parinte, String nume, int nivel) {
        super(parinte, nume, nivel);
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return super.toString() + " NodAStar{" + "g=" + g + ", h=" + h + '}';
    }
    
    
    
    @Override
    public int compareTo(NodAStar nod) {
        if (this.getH() < nod.getH()) {
                return -1;
        } else if (this.getH() > nod.getH()) {
                return 1;
        }
        return 0;
    }
	
}
