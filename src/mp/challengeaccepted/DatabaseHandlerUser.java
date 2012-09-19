package mp.challengeaccepted;

import java.util.ArrayList;
import java.util.List;

import mp.challengeaccepted.db.DBFunctions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


	//Klasse zur Bearbeitung CRUD der Datatbase

public class DatabaseHandlerUser extends SQLiteOpenHelper {
		 
	// static Variablen zur Beschreibung der Database
	// Database Version
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    private static final String DATABASE_NAME = "challange";	 

    private static final String TABLE_USERS = "user";

   
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TELEFONNUMMER = "telefonnummer";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_VERIFIED = "verified";
    private static final String KEY_REGISTERED = "registered";
    private static final String KEY_SIM = "sim";

    //Konstruktor
    public DatabaseHandlerUser(Context context) {
    	  super(context, DATABASE_NAME, null,DATABASE_VERSION); 
    	  }
	
    // Erstellen einer Database onCreate()
	@Override
    public void onCreate(SQLiteDatabase db) {
		  // TODO Auto-generated method stub
		  
		  db.execSQL("CREATE TABLE "+TABLE_USERS+" ("+KEY_ID+ " INTEGER PRIMARY KEY , "
		  +KEY_NAME+ " TEXT, " 
		  +KEY_TELEFONNUMMER+ " TEXT, "
		  +KEY_EMAIL+ " TEXT, "
		  +KEY_VERIFIED+ " TEXT, "
		  +KEY_REGISTERED+ " TEXT, "
		  +KEY_SIM+ " TEXT)");
	}

	 
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  // TODO Auto-generated method stub
		  
		  db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
		  
		  onCreate(db);
		 }
	
	 /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Neuen User erstellen
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); 
        values.put(KEY_TELEFONNUMMER, user.getPhoneNumber()); 
        values.put(KEY_EMAIL,  user.getEmail()); 
        values.put(KEY_VERIFIED, Boolean.toString(user.isVerified())); 
        values.put(KEY_REGISTERED, Boolean.toString(user.isRegistered())); 
        values.put(KEY_SIM, user.getSim());
        // Reihe in Tabelle eintragen
        
        db.insert(TABLE_USERS, KEY_NAME, values);
        db.close(); // database Verbindung schliessen
    }
	
    public User getUser(){
        SQLiteDatabase db = this.getWritableDatabase();

        User tmpuser = null;
		Cursor c = db.query(TABLE_USERS, null,null, null, null, null, null);
		c.moveToFirst();
		
		if(c.getCount()!=0){
			tmpuser = new User(c.getString(1), c.getString(2), c.getString(3));
			Log.d("ID",String.valueOf(c.getInt(0)));
			Log.d("Name",c.getString(1));
			Log.d("Nummer",c.getString(2));
			Log.d("EMail",c.getString(3));
			Log.d("Verifiziert",c.getString(4));
			Log.d("SIM",c.getString(6));

		}
		else{
			tmpuser = new User();
		}
		c.close();
		db.close();
		return tmpuser;
    }
    
    // User loeschen
    public int deleteUser()
    {
     SQLiteDatabase db=this.getWritableDatabase();
     int tmp = db.delete(TABLE_USERS, null, null);
     db.close();
     return tmp;
    }
    
    //User als verified setzen
    public void verifyUser(User user)
    {
    SQLiteDatabase db=this.getWritableDatabase();
    
    ContentValues values = new ContentValues();
    values.put(KEY_VERIFIED, "true");
    db.update(TABLE_USERS, values, null, null);
    
    db.close();
    }
   
    //User Sim setzen
    public void setSim(String sim)
    {
    SQLiteDatabase db=this.getWritableDatabase();
    
    ContentValues values = new ContentValues();
    values.put(KEY_SIM, sim); 
    db.update(TABLE_USERS, values, null, null);
    
    db.close();
    }
//    
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
 
}
