package sample;

import javafx.scene.Group;
import java.util.LinkedList;

public interface GraphList_Interface {

    void addEdge(int v, int w, Group root); // dodaj krawedz

    int outEdges( int v);

    int getEdges(int v);

    int getAllVertices();

    double[] getXvalues();

    double[] getYvalues();

    LinkedList<Integer> listEdges(int v);

    LinkedList<Integer> listGetEdges(int v);

    public void drawAllVertices(Group root);
}
