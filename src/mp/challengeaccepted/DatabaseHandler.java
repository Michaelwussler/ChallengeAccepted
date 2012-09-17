package mp.challengeaccepted;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


	//Klasse zur Bearbeitung CRUD der Datatbase

public class DatabaseHandler extends SQLiteOpenHelper {
		 
	// static Variablen zur Beschreibung der Database

	static final int DB_VERSION =1;
	
	////////////////////////////////////////////
	static final String USER_TABLE="user";
	//----------------------------------------	
	static final String USER_NAME="name";
	static final String USER_MAIL="mail";
	static final String USER_NUMBER="number";
	static final String USER_VERIFIED="verfied";
	
	////////////////////////////////////////////////////
	static final String CONTACT_TABLE="contacts";
	//---------------------------------------------------
	static final String CONTACT_NAME="name";
	static final String CONTACT_NUMBER="number";
	static final String CONTACT_MAIL="mail";
	static final String CONTACT_VERIFIED="verified";
	
	
	///////////////////////////////////////////////////7
	static final String CHALLENGES_TABLE ="challenges";
	//-------------------------------------------------
	static final String C_ID="_ID";
	static final String C_TIMESTAMP = "created_at";
	static final String C_SENDER = "sender";
	static final String C_RECEIVER ="receiver";
	static final String C_TITLE ="shortDescription";
	static final String C_DESCRIPTION ="longDescription";
	static final String C_PROOF ="proof";
	static final String C_STATUS="status";
	
	Context context;
	
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    private static final String DATABASE_NAME = "ChallangeAccepted";	 

    // Profile table name
    private static final String TABLE_PROFILES = "profiles";

    // Profile Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORT = "passwort";
    private static final String KEY_FAHRZEUG = "fahrzeug";
//    private static final String KEY_WEIBLICH = "weiblich";

    // Konstructor 
    public DatabaseHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	 
    // Erstellen einer Database onCreate()
	@Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + USER_TABLE + "("
    								+ KEY_ID + " INTEGER PRIMARY KEY,"
    								+ KEY_NAME + " TEXT," + KEY_PASSWORT + " TEXT,"
    								+ KEY_FAHRZEUG + " TEXT" +/*,"+ KEY_WEIBLICH + " INTEGER PRIMARY KEY"*/ ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	}
	 
	// �nderungen auf die Database �bertragen onUpgrade()
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// L�scht altes Table (falls existent)
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
	 
	// Erschafft ein neues, mit den neuen Daten
	onCreate(db);
	}
	
	 /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
//    // Neues Profil erstellen
//    void addProfil(Profil profil) {
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, profil.getName()); // Profil Name
//        values.put(KEY_PASSWORT, profil.getPasswort()); // Profil Passwort
//        values.put(KEY_FAHRZEUG, profil.getFahrzeug()); // Profil Passwort
//
//        // Reihe in Tabelle eintragen
//        db.insert(TABLE_PROFILES, null, values);
//        db.close(); // database Verbindung schlie�en
//    }
// 
//    // Einzelnes Profil auslesen
//    Profil getProfil(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
// 
//        Cursor cursor = db.query(TABLE_PROFILES, new String[] { KEY_ID, KEY_NAME,
//                KEY_PASSWORT, KEY_FAHRZEUG}, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
// 
//        Profil profil = new Profil(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2), cursor.getString(3));
//        // return profil
//        return profil;
//    }
// 
//    // Alle Profile auslesen
//    public List<Profil> getAllProfils() {
//        List<Profil> profilList = new ArrayList<Profil>();
//        // Alles zum Auslesen ausw�hlen
//        String selectQuery = "SELECT  * FROM " + TABLE_PROFILES;
// 
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // alle einzelnen Reihen werden an die Liste geh�ngt
//        if (cursor.moveToFirst()) {
//            do {
//                Profil profil = new Profil();
//                profil.setId(Integer.parseInt(cursor.getString(0)));
//                profil.setName(cursor.getString(1));
//                profil.setPasswort(cursor.getString(2));
//                profil.setFahrzeug(cursor.getString(3));
//                // Profil wird zur Liste hinzugef�gt
//                profilList.add(profil);
//            } while (cursor.moveToNext());
//        }
// 
//        // R�ckgabe der Liste
//        return profilList;
//    }
// 
//    // �ndern eines Profils
//    public int updateProfil(Profil profil) {
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, profil.getName());
//        values.put(KEY_PASSWORT, profil.getPasswort());
//        values.put(KEY_FAHRZEUG, profil.getFahrzeug());
//
//        // updaten einer Reihe
//        return db.update(TABLE_PROFILES, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(profil.getId()) });
//    }
// 
//    // L�schen eines einzelnen Profils
//    public void deleteProfil(Profil profil) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_PROFILES, KEY_ID + " = ?",
//                new String[] { String.valueOf(profil.getId()) });
//        db.close();
//    }
// 
//    // Auslesen der Anzahl an Profilen
//    public int getProfilsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_PROFILES;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
// 
//        // R�ckgabe des Werts
//        return cursor.getCount();
//    }
// 
}
