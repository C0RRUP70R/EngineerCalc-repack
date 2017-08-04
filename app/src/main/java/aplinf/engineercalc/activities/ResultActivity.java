package aplinf.engineercalc.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import aplinf.engineercalc.R;

import static aplinf.engineercalc.activities.MainActivity.MESSAGE_MAIN;
import static aplinf.engineercalc.activities.MainActivity.MESSAGE_SIDE;
import static aplinf.engineercalc.activities.MainActivity.MESSAGE_SIDE2;
import static aplinf.engineercalc.activities.MainActivity.MESSAGE_TYPE;
import static aplinf.engineercalc.auxiliary.AuxFc.getTension;
import static aplinf.engineercalc.auxiliary.AuxFc.prepareOutput;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_MAIN);
        String type = intent.getStringExtra(MESSAGE_TYPE);

        TextView textType = (TextView) findViewById(R.id.textType);
        TextView textOutput = (TextView) findViewById(R.id.textOutput);
        TextView mainText = (TextView) findViewById(R.id.mainText);

        TextView start_text = (TextView) findViewById(R.id.start_text);
        TextView end_Text = (TextView) findViewById(R.id.end_text);

        start_text.setText(intent.getStringExtra("inputStart"));
        end_Text.setText(intent.getStringExtra("inputEnd"));
        switch (type) {
            case "rot":
                textType.setText("Optimální otáčky");
                mainText.setText("Otáčky:");
                textOutput.setText(message);
                break;
            case "force":
                textType.setText("Maximální zatížení");
                mainText.setText("Síla:");
                textOutput.setText(message + " N");
                break;
            case "tension":
                textType.setText("Vnitřní napětí materiálu");
                mainText.setText("Napětí:");
                textOutput.setText(prepareOutput(message));
                prepareSpinner();
                setOutputTension();
                break;
        }
    }

    private void setOutputTension() {
        Intent intent = getIntent();
        String tens = intent.getStringExtra(MESSAGE_MAIN);
        double tension = Double.parseDouble(tens);

        String max = ((TextView) findViewById(R.id.textOutputSide)).getText().toString();
        max = max.substring(0, max.indexOf(' '));
        max = max.replaceAll(",", ".");
        double maximum = Double.parseDouble(max);

        TextView sideText = (TextView) findViewById(R.id.sideText);
        sideText.setText("Maximum:");
        sideText.setVisibility(View.VISIBLE);

        TextView sideOutput2 = (TextView) findViewById(R.id.textOutputSide2);
        double tot = maximum - tension;
        String side2 = tot + "";
        sideOutput2.setTextColor(Color.BLACK);
        if (tot < 0) {
            side2 = "+" + prepareOutput(side2);
            sideOutput2.setTextColor(Color.RED);
        } else if (tot > 0) {
            side2 = "-" + prepareOutput(side2);
            sideOutput2.setTextColor(0xFF339900);
        }
        sideOutput2.setText(side2);
        sideOutput2.setVisibility(View.VISIBLE);
    }



    private void prepareSpinner() {
        Spinner norm_spinner = (Spinner) findViewById(R.id.norm_spinner);
        ArrayAdapter<CharSequence> norm_adapt = ArrayAdapter.createFromResource(this, R.array.norm_type, android.R.layout.simple_spinner_item);
        norm_adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        norm_spinner.setAdapter(norm_adapt);

        norm_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switchMaterialSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        norm_spinner.setVisibility(View.VISIBLE);
        findViewById(R.id.norm).setVisibility(View.VISIBLE);

        Spinner material_spinner = (Spinner) findViewById(R.id.material_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.csn_material_array_short, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material_spinner.setAdapter(adapter2);
        material_spinner.setVisibility(View.VISIBLE);
        TextView materialText = (TextView) findViewById(R.id.materialText);
        materialText.setVisibility(View.VISIBLE);
        setSpinnerOutput();

        material_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setSpinnerOutput();
                setOutputTension();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void switchMaterialSpinner() {
        String norm = ((Spinner) findViewById(R.id.norm_spinner)).getSelectedItem().toString().toLowerCase();
        Spinner material_spinner = (Spinner) findViewById(R.id.material_spinner);
        switch (norm) {
            case "čsn":
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.csn_material_array_short, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material_spinner.setAdapter(adapter);
                break;
            case "čsn en":
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.csn_en_material_array_short, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material_spinner.setAdapter(adapter);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.nr_material_array_short, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material_spinner.setAdapter(adapter);
        }
    }

    private void setSpinnerOutput() {
        String material = ((Spinner) findViewById(R.id.material_spinner)).getSelectedItem().toString();
        TextView output = (TextView) findViewById(R.id.textOutputSide);
        Intent intent = getIntent();
        String typ = intent.getStringExtra(MESSAGE_SIDE);
        String nature = intent.getStringExtra(MESSAGE_SIDE2);


        int tension = getTension(material, typ, nature, this);
        output.setText(prepareOutput(tension + ""));
        output.setVisibility(View.VISIBLE);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}


