/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class CitireFisier {
    private static CitireFisier instanta;
    private Harta harta;
    private CitireFisier() {
        harta = Harta.getInstance();
    }
    
    public static CitireFisier getInstance() {
        if (instanta == null) {
            instanta = new CitireFisier();
        }
        return instanta;
    }
    
    public void citireFisier(String fileLocation) {
        
        try {
            BufferedReader bufferedReader = new BufferedReader(
                            new FileReader(fileLocation));
            //StringBuilder stringBuilder = new StringBuilder();
            String s;
            int index = 0;
            int[][] g = null;
            int nrOrase = 0;
            boolean b = false;
            int j = 0;
            HashMap<String, Integer> h = new HashMap<>();
            while ((s = bufferedReader.readLine()) != null) {
                //stringBuilder.append(s + "\n");
                if (s.equals("#")) {
                    index++;
                    continue;
                }
                
                switch (index) {
                    case 0:
                        String[] x1 = s.split(" ");
                        nrOrase = x1.length;
                        harta.setOrase(x1);
                        break;
                    case 1:
                        if (!b) {
                            g = new int[nrOrase][nrOrase];
                            b = true;
                        }     
                        String[] x2 = s.split("\t");
                        for (int i = 0; i < x2.length; i++) {
                            g[j][i] = Integer.parseInt(x2[i]);
                        }
                        j++;
                        break;
                    case 2:
                        String[] x3 = s.split(" ");
                        h.put(x3[0], Integer.parseInt(x3[1]));
                        break;
                }
            }
            
            bufferedReader.close();


            harta.setGraf(g);
            harta.setDistante(h);
            
            //String[] x = stringBuilder.toString().split("#");
//            harta.setOrase(numeOrase(x[0]));
//            harta.setGraf(genereazaHarta(x[1]));
//            harta.setDistante(genereazaDistante(x[2]));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
//    private String[] numeOrase(String x) {
//        String[] rezultat = x.split(" ");
//        return rezultat;
//    }
//    
//    private int[][] genereazaHarta(String x) {
//        
//        String[] randuri = x.split("\n");
//        int[][] harta = new int[randuri.length][randuri.length];
//        for (int i = 0; i < randuri.length; i++) {
//            String[] temp = randuri[i].split("\t");
//            
//            for (int j = 0; j < temp.length; j++) {
//                //harta[i][j] = Integer.parseInt(temp[j]);
//                System.out.print(temp[j] + " ");
//            }
//            System.out.println();
//        }
//        
//        return harta;
//    }
//    
//    private HashMap<String, Integer> genereazaDistante(String x) {
//        HashMap<String, Integer> hashMap = new HashMap<>();
//        String[] randuri = x.split("\n");
//        for (int i = 0; i < randuri.length; i++) {
//            String[] rand = randuri[i].split(" ");
//            hashMap.put(rand[0], Integer.parseInt(rand[1]));
//        }
//        return hashMap;
//    }
}
