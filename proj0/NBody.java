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

    public void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);

        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale((-1) * radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

        /* Stamps three copies of advice.png in a triangular pattern. */
        
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(2000);


    }

}