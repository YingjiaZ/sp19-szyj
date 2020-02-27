import java.nio.channels.UnsupportedAddressTypeException;

public class NBody {
    public static double readRadius(String FileName) {
        In in = new In(FileName);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Body[] readBodies(String FileName) {
        In in = new In(FileName);
        int NumOfPlanet = in.readInt();
        Body BodyArr[] = new Body[NumOfPlanet];
        in.readDouble();
        for (int i = 0; i < BodyArr.length; i += 1) {
            BodyArr[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble()
                                  ,in.readDouble(),in.readDouble(),in.readString());
        }
        return BodyArr;
    }
    
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body BodyArr[] = readBodies(filename);
        double radius = readRadius(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        String starfield = "images/starfield.jpg";
        /*StdDraw.picture(0, 0, starfield);
        StdDraw.pause(2000);
        for (int i = 0; i < BodyArr.length; i += 1) {
            BodyArr[i].draw();
        }
        StdDraw.show();*/

        double time = 0;
        while (time <= T) {
            double xForces[] = new double[BodyArr.length];
            double yForces[] = new double[BodyArr.length];
            for (int i = 0; i < BodyArr.length; i += 1) {
                xForces[i] = BodyArr[i].calcNetForceExertedByX(BodyArr);
                yForces[i] = BodyArr[i].calcNetForceExertedByY(BodyArr);
            }
            for (int i = 0; i < BodyArr.length; i += 1) {
                BodyArr[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, starfield);
            for (int i = 0; i < BodyArr.length; i += 1) {
                BodyArr[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", BodyArr.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < BodyArr.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  BodyArr[i].xxPos, BodyArr[i].yyPos, BodyArr[i].xxVel,
                  BodyArr[i].yyVel, BodyArr[i].mass, BodyArr[i].imgFileName);   
        }
    }
}