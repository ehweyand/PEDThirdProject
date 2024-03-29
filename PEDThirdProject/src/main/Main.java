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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {

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

    private static void showData(ArrayList<Cliente> clientes) {
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><>");
        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente.getNome() + " - Distância: " + cliente.getDist());
        }
        System.out.println("");
    }

    private static void clearScreen() {
        System.out.println("<><><><><><><><><><><><><><><><><><><><>");
        for (int i = 0; i < 28; i++) {
            System.out.println("");
        }
        System.out.println("<><><><><><><><><><><><><><><><><><><><>");
    }

    //other way of get the distance between two points
    private static double getDistanciaDois(double latitude, double longitude, double latitudePto, double longitudePto) {
        double dlon, dlat, a, distancia;
        dlon = longitudePto - longitude;
        dlat = latitudePto - latitude;
        a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(latitude) * Math.cos(latitudePto) * Math.pow(Math.sin(dlon / 2), 2);
        distancia = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6378140 * distancia;
        /* 6378140 is the radius of the Earth in meters*/ }

    //this one is used
    protected static double getDistancia(double x1, double y1, double x2, double y2) {
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
        // - Scatter Graph Plot View -
        Plotter plot = new Plotter(clientes, fornecedores);

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
        // new way
        for (Fornecedor fornecedor : fornecedores) {
            ArrayList<Cliente> clibyforn = new ArrayList<>();
            for (Cliente cliente : map.keySet()) {
                if (map.get(cliente).getNome().equals(fornecedor.getNome())) {
                    //tenho acesso aqui ao fornecedor do cliente mais próximo e o cliente respectivo
                    clibyforn.add(new Cliente(cliente.getNome(), cliente.getCordenada(), fornecedor,
                            getDistancia(cliente.getCordenada().getLatitude(), cliente.getCordenada().getLongitude(), fornecedor.getCordenada().getLatitude(), fornecedor.getCordenada().getLongitude())));
                }
            }
            //antes de ir pro próximo fornecedor
            System.out.println("Fornecedor: " + fornecedor.getNome());
            //ordena
            Collections.sort(clibyforn, new SortClients());
            System.out.println("Total de clientes para o fornecedor: " + clibyforn.size());
            showData(clibyforn);
            clearScreen();
        }
    }

    private static class SortClients implements Comparator<Cliente> {

        @Override
        public int compare(Cliente o1, Cliente o2) {
            return -Double.compare(o1.getDist(), o2.getDist());
        }

    }
}

// Plot classes
//libs: JFreeChart, VisualVM, XChart
class Plotter extends JFrame {

    public Plotter() {
    }

    public Plotter(ArrayList<Cliente> clientes, ArrayList<Fornecedor> fornecedores) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Scatter Graph");
        int x, y;
        x = y = 800;
        PlotterComponent pcomp = new PlotterComponent(x, y);
        this.add(pcomp);
        for (Fornecedor fornecedor : fornecedores) {
            pcomp.addPoint(fornecedor.getCordenada().getLongitude() * x, fornecedor.getCordenada().getLatitude() * y);
        }
        for (Cliente cliente : clientes) {
            pcomp.addPointClient(cliente.getCordenada().getLongitude() * x, cliente.getCordenada().getLatitude() * y);
        }

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class PlotterComponent extends JComponent {

    private ArrayList<Point2D> pointsFornecedores = new ArrayList<Point2D>();
    private ArrayList<Point2D> pointsClientes = new ArrayList<Point2D>();

    public PlotterComponent(int width, int height) {
        setPreferredSize(new Dimension(width, height));//define o tamanho
    }

    /**
     * Add a "Fornecedor" point to the view.
     *
     * @param x
     * @param y
     */
    public void addPoint(double x, double y) {
        pointsFornecedores.add(new Point2D.Double(x, y));
    }

    /**
     * Add a "Cliente" point to the view.
     *
     * @param x
     * @param y
     */
    public void addPointClient(double x, double y) {
        pointsClientes.add(new Point2D.Double(x, y));
    }

    /**
     * Paint a graphical component
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        for (Point2D p : pointsFornecedores) {
            Shape point = new Ellipse2D.Double(p.getX(), p.getY(), 10, 10);
            g2d.fill(point);
            g2d.draw(point);
        }
        for (Point2D p : pointsClientes) {
            Shape point = new Ellipse2D.Double(p.getX(), p.getY(), 2, 2);
            g2d.setColor(Color.red);
            g2d.draw(point);
        }

    }
}
