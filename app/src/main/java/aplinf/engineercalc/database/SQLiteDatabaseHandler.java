package aplinf.engineercalc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import aplinf.engineercalc.models.Material;

/**
 * Created by C0RRUP70R.
 */

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EnginnerDB";
    private static final String TABLE_NAME = "Materials";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STATIC_THRUST = "static_thrust";
    private static final String KEY_STATIC_PRESSURE = "static_pressure";
    private static final String KEY_STATIC_BEND = "static_bend";
    private static final String KEY_STATIC_CUT = "static_cut";
    private static final String KEY_PASSIVE_THRUST = "passive_thrust";
    private static final String KEY_PASSIVE_PRESSURE = "passive_pressure";
    private static final String KEY_PASSIVE_BEND = "passive_bend";
    private static final String KEY_PASSIVE_CUT = "passive_cut";
    private static final String KEY_ALTERNATE_THRUST = "alternate_thrust";
    private static final String KEY_ALTERNATE_PRESSURE = "alternate_pressure";
    private static final String KEY_ALTERNATE_BEND = "alternate_bend";
    private static final String KEY_ALTERNATE_CUT = "alternate_cut";

    private static final String[] COLUMNS = {
            KEY_ID,
            KEY_NAME,
            KEY_STATIC_THRUST,
            KEY_STATIC_PRESSURE,
            KEY_STATIC_BEND,
            KEY_STATIC_CUT,
            KEY_PASSIVE_THRUST,
            KEY_PASSIVE_PRESSURE,
            KEY_PASSIVE_BEND,
            KEY_PASSIVE_CUT,
            KEY_ALTERNATE_THRUST,
            KEY_ALTERNATE_PRESSURE,
            KEY_ALTERNATE_BEND,
            KEY_ALTERNATE_CUT
    };

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MaterialDbEntries.createDbEntries(db);
        ToleranceDbEntries.createDbEntries(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }



    private ContentValues createMaterialEntry(String name, int static_thrust, int static_pressure, int static_bend, int static_cut,
                                              int passive_thrust, int passive_pressure, int passive_bend, int passive_cut,
                                              int alternate_thrust, int alternate_pressure, int alternate_bend, int alternate_cut) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_STATIC_THRUST, static_thrust);
        values.put(KEY_STATIC_PRESSURE, static_pressure);
        values.put(KEY_STATIC_BEND, static_bend);
        values.put(KEY_STATIC_CUT, static_cut);
        values.put(KEY_PASSIVE_THRUST, passive_thrust);
        values.put(KEY_PASSIVE_PRESSURE, passive_pressure);
        values.put(KEY_PASSIVE_BEND, passive_bend);
        values.put(KEY_PASSIVE_CUT, passive_cut);
        values.put(KEY_ALTERNATE_THRUST, alternate_thrust);
        values.put(KEY_ALTERNATE_PRESSURE, alternate_pressure);
        values.put(KEY_ALTERNATE_BEND, alternate_bend);
        values.put(KEY_ALTERNATE_CUT, alternate_cut);
        return values;
    }

    public Material getMaterial(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " name = ?", // c. selections
                new String[]{String.valueOf(name)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit*/

        if (cursor != null)
            cursor.moveToFirst();

        Material material = new Material();
        material.setId(cursor.getInt(cursor.getColumnIndex("id")));
        material.setName(cursor.getString(cursor.getColumnIndex("name")));
        material.setStatic_thrust(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_THRUST)));
        material.setStatic_pressure(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_PRESSURE)));
        material.setStatic_bend(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_BEND)));
        material.setStatic_cut(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_CUT)));
        material.setPassive_thrust(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_THRUST)));
        material.setPassive_pressure(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_PRESSURE)));
        material.setPassive_bend(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_BEND)));
        material.setPassive_cut(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_CUT)));
        material.setAlternate_thrust(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_THRUST)));
        material.setAlternate_pressure(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_PRESSURE)));
        material.setAlternate_bend(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_BEND)));
        material.setAlternate_cut(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_CUT)));

        return material;
    }

    public int getMaterialProperty(String name, String property) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[]{String.valueOf(property)}, // b. column names
                " name = ?", // c. selections
                new String[]{String.valueOf(name)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit*/

        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(property));
    }

    public List<Material> allMaterials() {

        List<Material> materials = new LinkedList<Material>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Material material = null;

        if (cursor.moveToFirst()) {
            do {
                material = new Material();
                material.setId(cursor.getInt(cursor.getColumnIndex("id")));
                material.setName(cursor.getString(cursor.getColumnIndex("name")));
                material.setStatic_thrust(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_THRUST)));
                material.setStatic_pressure(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_PRESSURE)));
                material.setStatic_bend(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_BEND)));
                material.setStatic_cut(cursor.getInt(cursor.getColumnIndex(KEY_STATIC_CUT)));
                material.setPassive_thrust(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_THRUST)));
                material.setPassive_pressure(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_PRESSURE)));
                material.setPassive_bend(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_BEND)));
                material.setPassive_cut(cursor.getInt(cursor.getColumnIndex(KEY_PASSIVE_CUT)));
                material.setAlternate_thrust(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_THRUST)));
                material.setAlternate_pressure(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_PRESSURE)));
                material.setAlternate_bend(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_BEND)));
                material.setAlternate_cut(cursor.getInt(cursor.getColumnIndex(KEY_ALTERNATE_CUT)));
                materials.add(material);
            } while (cursor.moveToNext());
        }

        return materials;
    }

    public double getFieldTolerance(String name, String range, String it){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(name, // a. table
                new String[]{it}, // b. column names
                " range = ?", // c. selections
                new String[]{String.valueOf(range)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        double field = cursor.getDouble(cursor.getColumnIndex(it));
        return field;
    }


}
