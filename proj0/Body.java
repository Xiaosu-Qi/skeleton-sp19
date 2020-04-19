import java.lang.Math; 
public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body x) {
        return Math.sqrt(Math.pow((this.xxPos - x.xxPos), 2) + Math.pow((this.yyPos - x.yyPos), 2));
    }

    public double calcForceExertedBy(Body x) {
        double G = 6.67 * Math.pow(10,-11);
        double F = G * x.mass * this.mass / Math.pow(this.calcDistance(x), 2);
        return F;
    }

    public double calcForceExertedByX(Body x) {
        return this.calcForceExertedBy(x) * (x.xxPos - this.xxPos) / this.calcDistance(x);
    }

    public double calcForceExertedByY(Body x) {
        return this.calcForceExertedBy(x) * (x.yyPos - this.yyPos) / this.calcDistance(x);
    }

    public double calcNetForceExertedByX(Body[] x) {
        double F = 0;
        for (Body b : x) {
            if (b != this) {
                F += this.calcForceExertedByX(b);
            }
        }
        return F;
    }

    public double calcNetForceExertedByY(Body[] x) {
        double F = 0;
        for (Body b : x) {
            if (b != this) {
                F += this.calcForceExertedByY(b);
            }
        }
        return F;
    }

    public void update(double dt, double fX, double fY) {
        // 1. Calculate the acceleration using the provided x- and y-forces.
        double ax = fX / this.mass;
        double ay = fY / this.mass;
    
        // 2. Calculate the new velocity by using the acceleration and current velocity. 
        // Recall that acceleration describes the change in velocity per unit time, 
        // so the new velocity is (vx+dt⋅ax,vy+dt⋅ay).
        double new_vx = this.xxVel + dt * ax;
        double new_vy = this.yyVel + dt * ay;

        // 3. Calculate the new position by using the velocity computed in step 2 and the current position. 
        // The new position is (px+dt⋅vx,py+dt⋅vy).
        double new_px = this.xxPos + dt * new_vx;
        double new_py = this.yyPos + dt * new_vy;

        // 4. update
        this.xxPos = new_px;
        this.yyPos = new_py;
        this.xxVel = new_vx;
        this.yyVel = new_vy;
    }
}
