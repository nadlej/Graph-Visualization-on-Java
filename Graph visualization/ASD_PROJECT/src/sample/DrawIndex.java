package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DrawIndex {

    DrawIndex(double[] X, double[] Y, String[] name,  Group root ){

        for(int i = 0; i < X.length; i++) {

            if (i < X.length / 2) {

                if(i <= X.length / 4){

                    Text text = new Text(name[i]);
                    text.setX(X[i] + 25.0); // decreasing the value because of circle center
                    text.setY(Y[i] + 25.0);
                    text.setStyle("-fx-font-weight: bold");
                    text.setStroke(Color.GREENYELLOW);
                    root.getChildren().add(text);

                }else{

                    Text text = new Text(name[i]);
                    text.setX(X[i] - 25.0); // decreasing the value because of circle center
                    text.setY(Y[i] + 25.0);
                    text.setStyle("-fx-font-weight: bold");
                    text.setStroke(Color.GREENYELLOW);
                    root.getChildren().add(text);

                }
            }else{

                if(i >= X.length / 2 && i <=  X.length * 3 / 4){

                    Text text = new Text(name[i]);
                    text.setX(X[i] - 25.0); // decreasing the value because of circle center
                    text.setY(Y[i] - 25.0);
                    text.setStyle("-fx-font-weight: bold");
                    text.setStroke(Color.GREENYELLOW);
                    root.getChildren().add(text);

                }else{

                    Text text = new Text(name[i]);
                    text.setX(X[i] + 25.0); // decreasing the value because of circle center
                    text.setY(Y[i] - 25.0);
                    text.setStyle("-fx-font-weight: bold");
                    text.setStroke(Color.GREENYELLOW);
                    root.getChildren().add(text);

                }
            }
        }

    }

}
