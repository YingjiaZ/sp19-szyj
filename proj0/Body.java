public class Body {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
               xxPos = xP;
               yyPos = yP;
               xxVel = xV;
               yyVel = yV;
               mass = m;
               imgFileName = img;   
              }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.yyVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body SuppliedBody) {
        double dx = SuppliedBody.xxPos - this.xxPos;
        double dy = SuppliedBody.yyPos - this.yyPos;
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));
        return distance;
    }

    public double calcForceExertedBy(Body GivenBody) {
        double force = G * GivenBody.mass * this.mass / Math.pow(calcDistance(GivenBody),2);
        return force;
    }

    public double calcForceExertedByX(Body GivenBody) {
        double dx = GivenBody.xxPos - this.xxPos;
        double Xforce = calcForceExertedBy(GivenBody) * dx /calcDistance(GivenBody);
        return Xforce;
    }

    public double calcForceExertedByY(Body GivenBody) {
        double dy = GivenBody.yyPos - this.yyPos;
        double Yforce = calcForceExertedBy(GivenBody) * dy /calcDistance(GivenBody);
        return Yforce;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double NetForceX = 0;
        for (int i = 0; i < allBodys.length; i += 1) {
            if (allBodys[i].equals(this)) {
                continue;
            }
            NetForceX = NetForceX + calcForceExertedByX(allBodys[i]);
        }
        return NetForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double NetForceY = 0;
        for (int i = 0; i < allBodys.length; i += 1) {
            if (allBodys[i].equals(this)) {
                continue;
            }
            NetForceY = NetForceY + calcForceExertedByY(allBodys[i]);
        }
        return NetForceY;
    }

    public void update(double dt,double fX,double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        String filename = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, filename);
    }
}