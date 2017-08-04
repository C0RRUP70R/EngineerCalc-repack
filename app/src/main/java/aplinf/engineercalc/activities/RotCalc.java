package aplinf.engineercalc.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import aplinf.engineercalc.R;
import aplinf.engineercalc.auxiliary.CalcFc;
import aplinf.engineercalc.auxiliary.InputFilterMinMax;
import aplinf.engineercalc.auxiliary.InputFilterMinMaxInt;

import static aplinf.engineercalc.activities.MainActivity.MESSAGE_MAIN;
import static aplinf.engineercalc.activities.MainActivity.MESSAGE_TYPE;

public class RotCalc extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rot_calc);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final EditText speed_input = (EditText) findViewById(R.id.edit_speed);
        speed_input.setFilters(new InputFilter[]{new InputFilterMinMaxInt("1", "200")});
        final EditText diameter_input = (EditText) findViewById(R.id.edit_diameter);
        diameter_input.setFilters(new InputFilter[]{new InputFilterMinMax("1", "500")});
    }



    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to
        Intent intent = new Intent(this, ResultActivity.class);
        //String EXTRA_MESSAGE = "";

        EditText editTextSpeed = (EditText) findViewById(R.id.edit_speed);
        EditText editTextDiameter = (EditText) findViewById(R.id.edit_diameter);

        String speed_str = editTextSpeed.getText().toString();
        String diameter_str = editTextDiameter.getText().toString();
        if (speed_str.length() == 0) {
            editTextSpeed.setError("Speed is required!");
        } else if (diameter_str.length() == 0) {
            editTextDiameter.setError("Diameter is required!");
        } else {
            Double Speed = Double.parseDouble(speed_str);
            Double Diameter = Double.parseDouble(diameter_str);

            String message = CalcFc.countRot(Speed, Diameter) + " ot/min";
            intent.putExtra(MESSAGE_MAIN, message);
            intent.putExtra(MESSAGE_TYPE, "rot");
            startActivity(intent);
        }


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
