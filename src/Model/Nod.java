package Model;

public class Nod {
    private String parinte;
    private String nume;
    private int nivel;

    public Nod() {}

    public Nod(String nume) {
        this.nume = nume;
    }

    public Nod(String parinte, String nume, int nivel) {
        this.parinte = parinte;
        this.nume = nume;
        this.nivel = nivel;
    }

    public String getParinte() {
        return parinte;
    }
    public void setParinte(String parinte) {
        this.parinte = parinte;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Nod{" + "parinte=" + parinte + ", nume=" + nume + ", nivel=" + nivel + '}';
    }
}
