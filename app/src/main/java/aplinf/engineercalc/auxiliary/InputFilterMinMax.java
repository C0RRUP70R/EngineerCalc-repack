package aplinf.engineercalc.auxiliary;


import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by C0RRUP70R.
 */

public class InputFilterMinMax implements InputFilter {

    private double min, max;

    public InputFilterMinMax(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Double.parseDouble(min);
        this.max = Double.parseDouble(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String test = dest.toString() + source.toString();
            if ((test.length() <= (end + "").length() + 4) && !test.equals("00")) {
                double input = Double.parseDouble(test);
                if ((input + "").length() < 8) {
                    if (isInRange(min, max, input))
                        return null;
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

}
