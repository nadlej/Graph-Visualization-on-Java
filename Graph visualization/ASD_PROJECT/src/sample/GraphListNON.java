package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphListNON implements GraphListNON_Interface {

    int V = 0 ; // liczba wierzchołków
    int E = 0; // liczba krawedzi
    double[] X;
    double[] Y;

    private Map<Integer, LinkedList<Integer>> map = new HashMap<>();

    GraphListNON(int maxV, int maxE){

        V = maxV;
        E = maxE;

        for(int i = 0; i < V; i++)
            map.put(i, new LinkedList<Integer>());

        DrawVertices draw = new DrawVertices(V);

        X = draw.getX().clone();
        Y = draw.getY().clone();
    }

    @Override
    public void addEdge(int v, int w, Group root) {
        map.get(v).add(w);
        map.get(w).add(v);

        DrawLines lines = new DrawLines(X[v], Y[v],
                                        X[w], Y[w],
                                        root, GraphType.UNDIRECTED);
    }

    @Override
    public int outEdges(int v) {
        return map.get(v).size();
    }

    public int getAllVertices() {
        return V;
    }

    public double[] getXvalues() {
        return X;
    }

    public double[] getYvalues() {
        return Y;
    }

    @Override
    public LinkedList<Integer> listEdges(int v) {
        return map.get(v);
    }

    @Override
    public void drawAllVertices(Group root) {

        Circle cercle = new Circle();
        cercle.setCenterX(640 + 300);
        cercle.setCenterY(360);
        cercle.setRadius(10);
        cercle.setFill(Color.GRAY);
        root.getChildren().add(cercle);


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
