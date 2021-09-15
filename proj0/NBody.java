public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double desired = in.readDouble();
        return desired;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int numberOfPlanet = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[numberOfPlanet];
        for (int i =0; i < numberOfPlanet; i++){
            bodies[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(), in.readString());            
        }
        return bodies;       
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);

        StdDraw.clear();

        String background = "images/starfield.jpg";
        StdDraw.picture(0, 0, background);

        
        for (Body body : bodies) {
            body.draw();
        }

        double time = 0.0;
        while (time<T){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i <bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i <bodies.length;i++){
                bodies[i].update(dt,xForces[i], yForces[i]);
            }
            

            StdDraw.picture(0, 0, background );

            for (Body body : bodies) {
                body.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }


        StdDraw.show();
    }
}



