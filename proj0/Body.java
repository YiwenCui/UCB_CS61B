public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public Body(double xP, double yP, double xV,
    double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = Math.abs(b.xxPos - this.xxPos);
        double dy = Math.abs(b.yyPos - this.yyPos);
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public double calcForceExertedBy(Body b){
        return (6.67e-11 * b.mass * this.mass) / (this.calcDistance(b) * this.calcDistance(b));
    }

    public double calcForceExertedByX(Body b){
        double dx = Math.abs(b.xxPos - this.xxPos);
        return (calcForceExertedBy(b) * dx / calcDistance(b));
    }

    public double calcForceExertedByY(Body b){
        double dy = Math.abs(b.yyPos - this.yyPos);
        return (calcForceExertedBy(b) * dy / calcDistance(b));
    }

    public double calcNetForceExertedByX(Body[] bs){
        double netForceX = 0;
        for (Body b : bs) {
            if (!this.equals(b)){
                netForceX += this.calcForceExertedByX(b);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] bs){
        double netForceY = 0;
        for (Body b: bs) {
            if (!this.equals(b)){
                netForceY += this.calcForceExertedByY(b);
            }    
        }
        return netForceY;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
    
}