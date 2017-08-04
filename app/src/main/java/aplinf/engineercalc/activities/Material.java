package aplinf.engineercalc.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import aplinf.engineercalc.R;
import aplinf.engineercalc.database.SQLiteDatabaseHandler;

import static aplinf.engineercalc.activities.MainActivity.MESSAGE_MAIN;

public class Material extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String id = intent.getStringExtra(MESSAGE_MAIN);
        ((TextView) findViewById(R.id.material_id)).setText(id);
        String max_tens = getResources().getString(getResources().getIdentifier("max" + id, "string", getPackageName()));
        ((TextView) findViewById(R.id.max_tens)).setText(max_tens);
        //String material_info = getResources().getString(getResources().getIdentifier("text" + id, "string", getPackageName()));
        SQLiteDatabaseHandler db = new SQLiteDatabaseHandler(this);
        List<aplinf.engineercalc.models.Material> materials = db.allMaterials();
        String material_info = "";
        for(aplinf.engineercalc.models.Material mat : materials){
            material_info += mat.toString();
            material_info += "\n";
        }
        ((TextView) findViewById(R.id.material_text)).setText(material_info);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
