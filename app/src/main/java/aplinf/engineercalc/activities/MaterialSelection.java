package aplinf.engineercalc.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import aplinf.engineercalc.R;

import static aplinf.engineercalc.activities.MainActivity.MESSAGE_MAIN;

public class MaterialSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_selection);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void startMaterial(View view){
        Intent intent = new Intent(this, Material.class);
        String message = ((Button)view).getText().toString();
        intent.putExtra(MESSAGE_MAIN, message);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
