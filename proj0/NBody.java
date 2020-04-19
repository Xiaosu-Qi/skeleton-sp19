import java.lang.Double;
public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Body[] readBodies(String file) {
        In in = new In(file);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        Body[] bodies = new Body[firstItemInFile];

        for (int i = 0; i < firstItemInFile; i++) {
            bodies[i] = new Body(
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readString()
            );
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();

        for (double time = 0; time < T; time += dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int l = 0; l < bodies.length; l++) {
                xForces[l] = bodies[l].calcNetForceExertedByX(bodies);
                yForces[l] = bodies[l].calcNetForceExertedByY(bodies);
            }

            for (int k = 0; k < bodies.length; k++) {
                bodies[k].update(time, xForces[k], yForces[k]);
            }

            StdDraw.clear();
            StdDraw.setScale((-1) * radius, radius);
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body b: bodies) {
                b.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}