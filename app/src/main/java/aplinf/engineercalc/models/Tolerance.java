package aplinf.engineercalc.models;

/**
 * Created by C0RRUP70R.
 */

public class Tolerance {
    private double ES;
    private double EI;

    public Tolerance(double ES, double EI) {
        this.ES = ES;
        this.EI = EI;
    }

    public double getES() {
        return ES;
    }

    public void setES(double ES) {
        this.ES = ES;
    }

    public double getEI() {
        return EI;
    }

    public void setEI(double EI) {
        this.EI = EI;
    }
}
