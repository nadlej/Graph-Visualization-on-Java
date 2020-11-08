package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DrawLineInfo {

    DrawLineInfo(double startX, double startY, double endX, double endY, String name, Group root, boolean neighbour){

        double middleX = 0;
        double middleY = 0;

             if(neighbour){

                middleX = (endX + startX)  / 2;
                middleY = (endY + startY) / 2 ;

             }else{

                 middleX = (endX - startX) * 4 /  5 + startX;
                 middleY = (endY - startY) * 4 / 5 + startY;

             }

            double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;

            if(endX > startX && endY > startY){

                middleX -= 20;
                middleY += 10;

            }else if(endX > startX && endY < startY){

                middleX -= 15;
                middleY += 10;

            }else if(endX < startX && endY < startY){

                middleY += 10;
                middleX -= 20;

            }else{

                middleX -= 10;
                middleY -= 5;

            }

            Text text = new Text(middleX, middleY, name);
            text.setStyle("-fx-font-weight: bold");
            text.setStroke(Color.LIGHTSKYBLUE);
            root.getChildren().add(text);
        }
}



