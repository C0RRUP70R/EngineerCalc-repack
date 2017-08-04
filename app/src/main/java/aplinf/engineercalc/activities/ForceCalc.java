package aplinf.engineercalc.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import aplinf.engineercalc.R;
import aplinf.engineercalc.auxiliary.AuxFc;
import aplinf.engineercalc.auxiliary.CalcFc;
import aplinf.engineercalc.auxiliary.InputFilterMinMax;

import static aplinf.engineercalc.activities.MainActivity.MESSAGE_MAIN;
import static aplinf.engineercalc.activities.MainActivity.MESSAGE_TYPE;
import static aplinf.engineercalc.auxiliary.AuxFc.getTension;

public class ForceCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_calc);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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


        Spinner type_spinner = (Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(adapter);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // checkValues();
                switchInputValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner nature_spinner = (Spinner) findViewById(R.id.nature_spinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.nature_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nature_spinner.setAdapter(adapter4);

        nature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switchInputValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner material_spinner = (Spinner) findViewById(R.id.material_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.csn_material_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material_spinner.setAdapter(adapter2);

        material_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switchInputValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner shape_spinner = (Spinner) findViewById(R.id.shape_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.prurez_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shape_spinner.setAdapter(adapter3);
        shape_spinner.setSelection(adapter3.getCount() - 1);

        shape_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String item = shape_spinner.getSelectedItem().toString().toLowerCase();
                switchInput(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        final EditText material_input = (EditText) findViewById(R.id.material_value);
        material_input.setFilters(new InputFilter[]{new InputFilterMinMax("1", "1500")});
        final EditText sideA_input = (EditText) findViewById(R.id.sideA);
        sideA_input.setFilters(new InputFilter[]{new InputFilterMinMax("0", "250")});
        final EditText sideB_input = (EditText) findViewById(R.id.sideB);
        sideB_input.setFilters(new InputFilter[]{new InputFilterMinMax("0", "250")});
    }

    private void switchInput(String item) {
        EditText sideB = (EditText) findViewById(R.id.sideB);
        TextView textA = (TextView) findViewById(R.id.textA);
        TextView textB = (TextView) findViewById(R.id.textB);
        switch (item) {
            case "kruh":
                sideB.setVisibility(View.INVISIBLE);
                textB.setVisibility(View.INVISIBLE);
                textA.setText(getString(R.string.sideA_diameter));
                break;
            case "čtverec":
                sideB.setVisibility(View.INVISIBLE);
                textB.setVisibility(View.INVISIBLE);
                textA.setText(getString(R.string.sideA_length));
                break;
            case "obdélník":
                sideB.setVisibility(View.VISIBLE);
                textB.setVisibility(View.VISIBLE);
                textA.setText(getString(R.string.sideA_length));
                break;
        }
    }

    private void switchInputValue() {
        String typ = ((Spinner) findViewById(R.id.type_spinner)).getSelectedItem().toString().toLowerCase();
        String material = ((Spinner) findViewById(R.id.material_spinner)).getSelectedItem().toString();
        String nature = ((Spinner) findViewById(R.id.nature_spinner)).getSelectedItem().toString().toLowerCase();
        TextView material_text = (TextView) findViewById(R.id.material_text);
        EditText material_value = (EditText) findViewById(R.id.material_value);
        if (!material.equals("Jiný:")) {
            int tension = getTension(material, typ, nature, this);

            String tension_txt = tension + "";
            material_text.setText(tension_txt);
            material_text.setVisibility(View.VISIBLE);
            material_value.setVisibility(View.INVISIBLE);
        } else {
            material_text.setVisibility(View.INVISIBLE);
            material_value.setVisibility(View.VISIBLE);
            material_value.requestFocus();
        }
    }

    private void switchMaterialSpinner() {
        String norm = ((Spinner) findViewById(R.id.norm_spinner)).getSelectedItem().toString().toLowerCase();
        Spinner material_spinner = (Spinner) findViewById(R.id.material_spinner);
        switch (norm) {
            case "čsn":
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.csn_material_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material_spinner.setAdapter(adapter);
                break;
            case "čsn en":
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.csn_en_material_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material_spinner.setAdapter(adapter);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.nr_material_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material_spinner.setAdapter(adapter);
        }
    }

    private void checkValues() {
        Spinner shape = (Spinner) findViewById(R.id.shape_spinner);
        Spinner type = (Spinner) findViewById(R.id.type_spinner);
        String type_str = type.getSelectedItem().toString().toLowerCase();
        int shape_idx = shape.getSelectedItemPosition();
        if (type_str.equals("krut")) {
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                    R.array.prurez_array_short, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            shape.setAdapter(adapter3);
            if (shape_idx < adapter3.getCount()) {
                shape.setSelection(shape_idx);
            } else {
                shape.setSelection(0);
            }
        } else {
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                    R.array.prurez_array, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            shape.setAdapter(adapter3);
            shape.setSelection(shape_idx);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ResultActivity.class);

        EditText sideA = (EditText) findViewById(R.id.sideA);
        EditText sideB = (EditText) findViewById(R.id.sideB);

        String sideA_txt = sideA.getText().toString();
        String sideB_txt = sideB.getText().toString();

        if (sideA_txt.length() == 0) {
            sideA.setError("Field is required!");
        } else if (sideB_txt.length() == 0 && sideB.getVisibility() == View.VISIBLE) {
            sideB.setError("Field is required!");
        } else {
            String typ = ((Spinner) findViewById(R.id.type_spinner)).getSelectedItem().toString().toLowerCase();
            String material = ((Spinner) findViewById(R.id.material_spinner)).getSelectedItem().toString();

            int tension;
            if (material.equals("Jiný:")) {
                tension = Integer.parseInt(((EditText) findViewById(R.id.material_value)).getText().toString());
            } else {
                tension = Integer.parseInt(((TextView) findViewById(R.id.material_text)).getText().toString());
            }

            Spinner spinner3 = (Spinner) findViewById(R.id.shape_spinner);
            String areaType = spinner3.getSelectedItem().toString().toLowerCase();

            double area = AuxFc.getArea(typ, areaType, sideA_txt, sideB_txt);
            double force = CalcFc.maxForce(area, tension);

            String message = String.format("%.2f", force);
            intent.putExtra(MESSAGE_MAIN, message);
            intent.putExtra(MESSAGE_TYPE, "force");

            typ = ((Spinner) findViewById(R.id.type_spinner)).getSelectedItem().toString();
            String druh = ((Spinner) findViewById(R.id.nature_spinner)).getSelectedItem().toString();
            String inputMessage = "Typ:\nDruh:\nPrůřez:";
            String inputMessageEnd = "\t" + typ +"\n\t" + druh + "\n\t" + String.format("%.2f", area);
            intent.putExtra("inputStart", inputMessage);
            intent.putExtra("inputEnd", inputMessageEnd);
            startActivity(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
