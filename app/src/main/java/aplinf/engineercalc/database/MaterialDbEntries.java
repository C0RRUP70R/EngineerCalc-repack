package aplinf.engineercalc.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by C0RRUP70R.
 */

public class MaterialDbEntries {
    private static final String TABLE_NAME = "Materials";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EN_NAME = "en_name";
    private static final String KEY_NR_NAME = "nr_name";
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
            KEY_EN_NAME,
            KEY_NR_NAME,
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

    public static void createDbEntries(SQLiteDatabase db) {
        createTable(db);
        createDbMaterialEntries(db);
    }

    private static void createTable(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_EN_NAME + " TEXT, " +
                KEY_NR_NAME + " TEXT, " +
                KEY_STATIC_THRUST + " INTEGER, " +
                KEY_STATIC_PRESSURE + " INTEGER, " +
                KEY_STATIC_BEND + " INTEGER, " +
                KEY_STATIC_CUT + " INTEGER, " +
                KEY_PASSIVE_THRUST + " INTEGER, " +
                KEY_PASSIVE_PRESSURE + " INTEGER, " +
                KEY_PASSIVE_BEND + " INTEGER, " +
                KEY_PASSIVE_CUT + " INTEGER, " +
                KEY_ALTERNATE_THRUST + " INTEGER, " +
                KEY_ALTERNATE_PRESSURE + " INTEGER, " +
                KEY_ALTERNATE_BEND + " INTEGER, " +
                KEY_ALTERNATE_CUT + " INTEGER)";
        db.execSQL(CREATION_TABLE);
    }

    private static void createDbMaterialEntries(SQLiteDatabase db) {
        ContentValues values = createMaterialEntry("11343", "null", "null", 90, 90, 100, 55, 75, 75, 85, 45, 60, 60, 65, 35);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("11373", "S235JRG1", "1.0036", 100, 100, 110, 65, 65, 65, 70, 40, 45, 45, 50, 30);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("11423", "null", "null", 115, 115, 120, 70, 75, 75, 80, 45, 55, 55, 60, 35);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("11500", "E295", "1.0050", 140, 140, 150, 85, 90, 90, 100, 55, 65, 65, 70, 40);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("11600", "E335", "1.0060", 150, 150, 170, 105, 110, 110, 125, 65, 75, 75, 85, 50);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("11700", "E360", "1.0070", 210, 210, 230, 125, 135, 135, 150, 80, 90, 90, 105, 60);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("12020", "C16E", "1.1148", 115, 115, 125, 70, 95, 95, 105, 45, 75, 75, 80, 33);
        db.insert(TABLE_NAME, null, values);
        values = createMaterialEntry("12060", "C55E", "1.1203",175, 175, 190, 110, 150, 150, 120, 70, 115, 115, 85, 50);
        db.insert(TABLE_NAME, null, values);
    }

    private static ContentValues createMaterialEntry(String name, String en_name, String nr_name, int static_thrust, int static_pressure, int static_bend, int static_cut,
                                                     int passive_thrust, int passive_pressure, int passive_bend, int passive_cut,
                                                     int alternate_thrust, int alternate_pressure, int alternate_bend, int alternate_cut) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EN_NAME, en_name);
        values.put(KEY_NR_NAME, nr_name);
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
}
