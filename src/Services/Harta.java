package Services;

import java.util.ArrayList;
import java.util.HashMap;

public class Harta {
    private static Harta harta;
    
    private HashMap<String, Integer> index;
    private HashMap<Integer, String> orase;
    private HashMap<String, Integer> distante;
    private int[][] graf;

    private Harta() {}
    public static Harta getInstance() {
        if (harta == null) {
            harta = new Harta();
        }
        return harta;
    }

    private void setHashMaps(String[] locatii) {
        orase = new HashMap<>();
        index = new HashMap<>();
        for (int i = 0; i < locatii.length; i++) {
            index.put(locatii[i], i);
            orase.put(i, locatii[i]);
        }
    }

    public int indexOras(String oras) {
        return index.get(oras);
    }

    public String numeOras(int index) {
        return orase.get(index);
    }

    public ArrayList<String> genereazaVecini(String oras) {
        ArrayList<String> x = new ArrayList<>();
        int i = indexOras(oras);
        for (int j = 0; j < graf[indexOras(oras)].length; j++) {
            if (graf[i][j] > 0) {
                x.add(numeOras(j));
            }
        }

        return x;
    }

    public int costTranzitie(String a, String b) {
        int i = indexOras(a);
        int j = indexOras(b);

        return graf[i][j];
    }

    public int distantaAproximativa(String oras) {
        return distante.get(oras);
    }
    
    public void setDistante(HashMap<String, Integer> h) {
        distante = h;
    }
    
    public void setGraf(int[][] g) {
        graf = g;
    }
    
    public void setOrase(String[] o) {
        setHashMaps(o);
    }
    
    public String[] getOrase() {
        String[] rezultat = new String[orase.size()];
        for (int i = 0; i < orase.size(); i++) {
            rezultat[i] = orase.get(i);
        }
        return rezultat;
    }
}
