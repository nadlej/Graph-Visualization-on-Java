package sample;

public class DrawVertices {

    double[] X;
    double[] Y;

    public DrawVertices(int V){

        X = new double[V];
        Y = new double[V];

        double angle = 360.0 / (V);
        int circleR = 300;

        for(int i = 0; i < V; i++){

            double x = circleR * Math.cos(Math.toRadians(angle * i));
            double y = circleR * Math.sin(Math.toRadians(angle * i));

            x += 640.0;
            y += 360.0;

            X[i] = x;
            Y[i] = y;

        }
    }

    public double[] getX(){
        return X;
    }

    public double[]getY(){
        return Y;
    }

}
