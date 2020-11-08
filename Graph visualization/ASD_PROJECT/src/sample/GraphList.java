package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.*;

    public class GraphList implements GraphList_Interface {

        int V = 0; // liczba wierzchołków
        int E = 0; // liczba krawedzi
        double[] X;
        double[] Y;

        private Map<Integer, LinkedList<Integer>> map = new HashMap<>();  // pointer na nasza liste wszystkich wierzcholkow

        public GraphList(int maxV, int maxE) {

            V = maxV;
            E = maxE;

            for (int i = 0; i < V; i++)
                map.put(i, new LinkedList<Integer>());

            DrawVertices draw = new DrawVertices(V);

            X = draw.getX().clone();
            Y = draw.getY().clone();

        }

        @Override
        public void addEdge(int v, int w, Group root) {

            map.get(v).add(w);

            DrawLines lines = new DrawLines(X[v], Y[v],
                                            X[w], Y[w],
                                            root, GraphType.DIRECTED);
        }

        @Override
        public int outEdges(int v) {
            return map.get(v).size();
        }

        @Override
        public int getEdges(int v) {
            int counter = 0;

            for (int i = 0; i < map.size(); i++)
                if (map.get(i).contains(v))
                    counter++;

            return counter;
        }

        @Override
        public int getAllVertices() {
            return V;
        }

        @Override
        public double[] getXvalues() {
            return X;
        }

        @Override
        public double[] getYvalues() {
            return Y;
        }

        @Override
        public LinkedList<Integer> listEdges(int v) {
            return map.get(v);
        }

        @Override
        public LinkedList<Integer> listGetEdges(int v) {

            LinkedList<Integer> GetEdges = new LinkedList<>();

            for (int i = 0; i < map.size(); i++)
                if (map.get(i).contains(v))
                    GetEdges.add(i);

            return GetEdges;
        }

        @Override
        public void drawAllVertices(Group root) {

            Circle cercle;

            for (int i = 0; i < X.length; i++) {

                cercle = new Circle();
                cercle.setCenterX(X[i]);
                cercle.setCenterY(Y[i]);
                cercle.setRadius(10);
                cercle.setFill(Color.GRAY);
                root.getChildren().add(cercle);

            }
        }
    }

