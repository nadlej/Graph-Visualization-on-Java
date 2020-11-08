package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group root = new Group();

        String path = "src/sample/dane.txt";

        try (Scanner in = new Scanner(new FileReader(path))) {

            if (in.hasNext()) {

                String type = in.next();

                if(type.equals("DIRECTED")){

                    new DirectedType(root, path);

                }else if(type.equals("UNDIRECTED")) {

                    new UndirectedType(root, path);

                }else{

                    System.out.println("WRONG TYPE OF GRAPH");
                    System.exit(0);
                }
            }

            primaryStage.setTitle("GRAPH VISUALIZATION");
            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();

        } catch (IOException io) {

            System.out.println("Problem z odczytem pliku");
            io.printStackTrace();

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
