package aplinf.engineercalc.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import aplinf.engineercalc.R;
import aplinf.engineercalc.auxiliary.InputFilterMinMax;
import aplinf.engineercalc.auxiliary.InputFilterMinMaxInt;

import static aplinf.engineercalc.auxiliary.CogwheelFc.getCogHeightStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getCogMarStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getCogWidth;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getFootDiameterStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getFootStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getHeadStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getMod;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getOutletStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getPitchDiameterStr;
import static aplinf.engineercalc.auxiliary.CogwheelFc.getPitchStr;

public class CogwheelRevCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cogwheel_rev_calc);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final EditText diameter_input = (EditText) findViewById(R.id.diameter_input);
        diameter_input.setFilters(new InputFilter[]{new InputFilterMinMax("1", "500")});
        diameter_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (diameter_input.getText().length() == 0) {
                    setAllFieldsZero();
                } else if (checkInput()) {
                    calculate();
                }
                ((TextView) findViewById(R.id.text_hlav_kr)).setText(diameter_input.getText().toString() + "mm");

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final EditText input = (EditText) findViewById(R.id.cog_count);
        input.setFilters(new InputFilter[]{new InputFilterMinMaxInt("1", "500")});
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (input.getText().length() == 0) {
                    setAllFieldsZero();
                } else if (checkInput()) {
                    calculate();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private boolean checkInput() {
        String input = ((EditText) findViewById(R.id.cog_count)).getText().toString();
        String diameter_input = ((EditText) findViewById(R.id.diameter_input)).getText().toString();
        return input.length() > 0 && diameter_input.length() > 0;
    }

    private void calculate() {
        String diameter_input = ((EditText) findViewById(R.id.diameter_input)).getText().toString();
        String count_txt = ((EditText) findViewById(R.id.cog_count)).getText().toString();

        double diameter = Double.parseDouble(diameter_input);

        int count = Integer.parseInt(count_txt);
        double modul = getMod(diameter, count);

        ((TextView) findViewById(R.id.modul_txt)).setText(String.format("%.2f", modul));
        ((TextView) findViewById(R.id.text_roztec_kr)).setText(getPitchDiameterStr(modul, count));
        ((TextView) findViewById(R.id.text_pata_kr)).setText(getFootDiameterStr(modul, count));
        ((TextView) findViewById(R.id.text_roztec)).setText(getPitchStr(modul));
        ((TextView) findViewById(R.id.text_vrch_vule)).setText(getOutletStr(modul));
        ((TextView) findViewById(R.id.text_vyska_hlavy)).setText(getHeadStr(modul));
        ((TextView) findViewById(R.id.text_vyska_paty)).setText(getFootStr(modul));
        ((TextView) findViewById(R.id.text_vyska_zubu)).setText(getCogHeightStr(modul));
        ((TextView) findViewById(R.id.text_tl_zub)).setText(getCogWidth(modul));
        ((TextView) findViewById(R.id.text_zub_mez)).setText(getCogMarStr(modul));
    }

    private void setAllFieldsZero() {
        ConstraintLayout results = (ConstraintLayout) findViewById(R.id.constraintLayout);
        int count = results.getChildCount();
        for (int i = 0; i < count; i++) {
            ((TextView) results.getChildAt(i)).setText("0 mm");
        }
        ((TextView) findViewById(R.id.modul_txt)).setText("0");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
