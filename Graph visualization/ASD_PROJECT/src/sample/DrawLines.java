package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class DrawLines {

     DrawLines(double startX, double startY, double endX, double endY, Group root, GraphType type) {

         if (type == GraphType.UNDIRECTED) {

             Line line = new Line(startX, startY, endX, endY);
             line.setStroke(Color.BLACK);
             root.getChildren().add(line);

        }else{

             Line line = new Line(startX, startY, endX, endY);
             line.setStroke(Color.BLACK);
             root.getChildren().add(line);

            Polygon polygon = new Polygon();

            double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;

            double sin = Math.sin(angle);
            double cos = Math.cos(angle);

            double arrowHeadSize = 11.0;

            double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            //point2
            double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;

            double middleX = (x1 + x2) / 2;
            double middleY = (y1 + y2) / 2;

            angle = Math.atan2((middleY- startY), (middleX - startX)) - Math.PI / 2.0;

            x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + middleX;
            y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + middleY;

            sin = Math.sin(angle);
            cos = Math.cos(angle);

            x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + middleX;
            y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + middleY;

            if(startX < endX) {

                polygon.getPoints().addAll(
                        middleX, middleY,
                        x1, y1,
                        x2, y2);

            }else if(startX == endX){

                polygon.getPoints().addAll(
                        middleX, middleY ,
                        x1, y1,
                        x2, y2);
            }else{

                polygon.getPoints().addAll(
                        middleX, middleY,
                        x1, y1,
                        x2, y2);

            }
                root.getChildren().add(polygon);

        }
    }
}
