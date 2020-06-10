/*
TAREFA 0: Escreva um programa que abra o arquivo clients.txt,
que descreve em coordenadas geográficas normalizadas
(longitude e latitude, ambas entre 0.0 e 1.0) um conjunto de clientes que devem ser atendidos
por sete fornecedores. As coordenadas desses fornecedores são: A (0.0715, 0.5984),
B (0.2336, 0.2094), C (0.0612, 0.8530), D (0.5088, 0.4992), E (0.5567, 0.8742), F (0.0944, 0.0894)
e G (0.9028, 0.4606).

Inicialmente, é preciso fazer a visualização dos dados, ilustrando de alguma forma a distribuição de
clientes e fornecedores, gerando uma imagem. Pede-se relacionar cada cliente a um único fornecedor,
usando o critério de menor distância euclidiana entre eles. Uma vez feita essa associação, solicita-se
exibir cada fornecedor e os códigos dos seus respectivos clientes, por ordem decrescente de distância
(entre cada cliente e seu fornecedor).
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        fornecedores.add(new Fornecedor("A", new Coordinates(0.0715, 0.5984)));
        fornecedores.add(new Fornecedor("B", new Coordinates(0.2336, 0.2094)));
        fornecedores.add(new Fornecedor("C", new Coordinates(0.0612, 0.8530)));
        fornecedores.add(new Fornecedor("D", new Coordinates(0.5088, 0.4992)));
        fornecedores.add(new Fornecedor("E", new Coordinates(0.5567, 0.8742)));
        fornecedores.add(new Fornecedor("F", new Coordinates(0.0944, 0.0894)));
        fornecedores.add(new Fornecedor("G", new Coordinates(0.9028, 0.4606)));

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

    }
}    

//        Map<String, Coordinates> fornecedores = new HashMap<>();
//        fornecedores.put("A", new Coordinates(0.0715, 0.5984));
//        fornecedores.put("B", new Coordinates(0.2336, 0.2094));
//        fornecedores.put("C", new Coordinates(0.0612, 0.8530));
//        fornecedores.put("D", new Coordinates(0.5088, 0.4992));
//        fornecedores.put("E", new Coordinates(0.5567, 0.8742));
//        fornecedores.put("F", new Coordinates(0.0944, 0.0894));
//        fornecedores.put("G", new Coordinates(0.9028, 0.4606));
//        
//        
//        Map<String, Coordinates> clientes = new HashMap<>(); //TreeMap always sort based on key - (key, value)
//        SimpleReader sr = new SimpleReader("src/main/clients.txt");
//        String line = sr.readLine();
//        while (line != null) {
//            String[] splitted = line.split(" ");
//            Coordinates c = new Coordinates(Double.parseDouble(splitted[1]), Double.parseDouble(splitted[2]));
//            clientes.put(splitted[0], c);         
//            line = sr.readLine();
//        }
//        
//        // percorrer um map
//        for (String s : clientes.keySet()) {
//            System.out.println(s+" - Longitude: "+clientes.get(s).getLatitude());
//        }
