package sample;

import javafx.scene.Group;

import java.util.LinkedList;

public interface GraphListNON_Interface {

    void addEdge(int v, int w, Group root); // dodaj krawedz

    int outEdges( int v);

    LinkedList<Integer> listEdges(int v);

    int getAllVertices();

    double[] getXvalues();

    double[] getYvalues();

    public void drawAllVertices(Group root);

}
