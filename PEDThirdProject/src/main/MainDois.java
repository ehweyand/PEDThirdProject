/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author evand
 */
public class MainDois {

    /**
     *
     * @param c First Coordinate
     * @param f Second Coordinate
     * @return Distance between two coordinates
     */
    private static void printHashMap(Map<Cliente, Fornecedor> map) {
        for (Cliente cliente : map.keySet()) {
            System.out.println("Cliente: " + cliente.getNome() + " -> Fornecedor: " + map.get(cliente).getNome());
        }
    }

    private static void showDatainLine(ArrayList<Fornecedor> f, ArrayList<Cliente> c) {
        System.out.println("Fornecedores: \n");
        for (Fornecedor fornecedor : f) {
            System.out.println(fornecedor.getNome());
            System.out.print("Lon: " + fornecedor.getCordenada().getLongitude() + " ");
            System.out.print("Lat: " + fornecedor.getCordenada().getLatitude() + " ");
        }
        System.out.println("Clientes: \n");
        for (Cliente cliente : c) {
            System.out.println(cliente.getNome());
            System.out.print("Lon: " + cliente.getCordenada().getLongitude() + " ");
            System.out.print("Lat: " + cliente.getCordenada().getLatitude() + " ");
        }
        System.out.println("");
    }

    public static double getDistanciaDois(double latitude, double longitude, double latitudePto, double longitudePto) {
        double dlon, dlat, a, distancia;
        dlon = longitudePto - longitude;
        dlat = latitudePto - latitude;
        a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(latitude) * Math.cos(latitudePto) * Math.pow(Math.sin(dlon / 2), 2);
        distancia = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6378140 * distancia;
        /* 6378140 is the radius of the Earth in meters*/ }

    private static double getDistancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2)
                + Math.pow(y2 - y1, 2) * 1.0);
    }

    public static void main(String[] args) {
        // Fornecedores
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        fornecedores.add(new Fornecedor("A", new Coordinates(0.0715, 0.5984)));
        fornecedores.add(new Fornecedor("B", new Coordinates(0.2336, 0.2094)));
        fornecedores.add(new Fornecedor("C", new Coordinates(0.0612, 0.8530)));
        fornecedores.add(new Fornecedor("D", new Coordinates(0.5088, 0.4992)));
        fornecedores.add(new Fornecedor("E", new Coordinates(0.5567, 0.8742)));
        fornecedores.add(new Fornecedor("F", new Coordinates(0.0944, 0.0894)));
        fornecedores.add(new Fornecedor("G", new Coordinates(0.9028, 0.4606)));
        // Clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        SimpleReader sr = new SimpleReader("src/main/clients.txt");
        String line = sr.readLine();
        while (line != null) {
            String[] splitted = line.split(" ");
            clientes.add(new Cliente(splitted[0],
                    new Coordinates(Double.parseDouble(splitted[1]),
                            Double.parseDouble(splitted[2]))));
            line = sr.readLine();
        }
        /**
         * ***************************************************************
         */

        Map<Cliente, Fornecedor> map = new HashMap<>();
        for (Cliente cliente : clientes) {
            map.put(cliente, fornecedores.get(0));
            for (Fornecedor fornecedor : fornecedores) {
                //verifica se é menor
                if ((getDistancia(cliente.getCordenada().getLatitude(), cliente.getCordenada().getLongitude(), fornecedor.getCordenada().getLatitude(), fornecedor.getCordenada().getLongitude()))
                        < (getDistancia(cliente.getCordenada().getLatitude(), cliente.getCordenada().getLongitude(), map.get(cliente).getCordenada().getLatitude(), map.get(cliente).getCordenada().getLongitude()))) {
                    //atualiza o map
                    map.put(cliente, fornecedor);
                }
            }
        }
        printHashMap(map);

    }
}
