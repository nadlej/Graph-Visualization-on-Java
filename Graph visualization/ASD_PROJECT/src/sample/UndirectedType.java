package sample;

import javafx.scene.Group;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UndirectedType {

    UndirectedType(Group root, String path) throws FileNotFoundException {


        Scanner in = new Scanner(new FileReader(path));
        int V = 0, E = 0;
        String type = in.next();

        while (in.hasNext()) {

            try{

                V = Integer.parseInt(in.next());
                E = Integer.parseInt(in.next());

            }catch(NumberFormatException a){

                System.out.print("WRONG TYPE OF DATA");
                System.exit(0);

            }

            GraphListNON graph = new GraphListNON(V, E);

            double[] X = graph.getXvalues().clone();
            double[] Y = graph.getYvalues().clone();

            String info = in.nextLine(); // linia kontrolna

            for (int i = 0; i < E; i++) {

                info = in.nextLine();
                String[] vertices = info.split("\\s");

                if (vertices.length > 2) {

                    try{

                        int vertex1 = Integer.parseInt(vertices[0]);
                        int vertex2 = Integer.parseInt(vertices[1]);
                        graph.addEdge(vertex1, vertex2, root);

                        String nameE = "";

                        for(int j = 2; j < vertices.length; j++)
                            nameE += " " + vertices[j];

                        if(Math.abs(vertex1 - vertex2) == 1)
                            new DrawLineInfo(X[vertex1], Y[vertex1], X[vertex2], Y[vertex2], nameE, root, true);
                        else
                            new DrawLineInfo(X[vertex1], Y[vertex1], X[vertex2], Y[vertex2], nameE, root, false);

                    }catch(NumberFormatException a){

                        System.out.println("DATA IS NOT A NUMBER");

                    }

                } else if (vertices.length == 2) {

                    try{

                        int vertex1 = Integer.parseInt(vertices[0]);
                        int vertex2 = Integer.parseInt(vertices[1]);
                        graph.addEdge(vertex1, vertex2, root);

                    }catch(NumberFormatException a){

                        System.out.println("DATA IS NOT A NUMBER");

                    }

                } else {

                    System.out.println("WROGN ARGUMENTS\n");
                    System.exit(0);

                }
            }

            graph.drawAllVertices(root);

            String[] places = new String[X.length];

            if (in.hasNext()) {

                while (in.hasNext()) {

                    int index = 0;

                    try {

                        String line = in.nextLine();
                        String[] VertexInfo = line.split("\\s");
                        String VertexName = "";

                        index = Integer.parseInt(VertexInfo[0]);

                        if(index > X.length){

                            System.out.print("INDEX OUT OF BOUNDS");
                            continue;

                        }

                        for (int j = 1; j < VertexInfo.length; j++)
                            VertexName += " " + VertexInfo[j];

                        places[index] = VertexName;

                    } catch (NumberFormatException a) {

                        System.out.println("DATA IS NOT A NUMBER");

                    }
                }
            }

            for (int k = 0; k < X.length; k++)
                if (places[k] == null)
                    places[k] = String.valueOf(k);

            new DrawIndex(X, Y, places, root);
        }
    }
}

