package mp.challengeaccepted;

import java.util.ArrayList;
import java.util.List;

import mp.challengeaccepted.db.DBFunctions;
import mp.challengeaccepted.db.ServerDB;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;


	//Klasse zur Bearbeitung CRUD der Datatbase

public class DatabaseHandlerProfile extends SQLiteOpenHelper {
		 
	// static Variablen zur Beschreibung der Database
	// Database Version
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    private static final String DATABASE_NAME = "challange";	 
    private static final String TABLE_PROFILES = "profile";
    private static final String TABLE_USERS = "user";

   
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TELEFONNUMMER = "telefonnummer";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_VERIFIED = "verified";
    private static final String KEY_REGISTERED = "registered";
    private static final String KEY_SIM = "sim";

    //Konstruktor
    public DatabaseHandlerProfile(Context context) {
    	  super(context, DATABASE_NAME, null,DATABASE_VERSION); 
    	  }
	
    // Erstellen einer Database onCreate()
	@Override
    public void onCreate(SQLiteDatabase db) {
		  // TODO Auto-generated method stub
		  
		  db.execSQL("CREATE TABLE "+TABLE_PROFILES+	   
		    "("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
		  +KEY_NAME+ " TEXT, " 
		  +KEY_TELEFONNUMMER+ " TEXT, "
		  +KEY_EMAIL+ " TEXT, "
		  +KEY_REGISTERED+ " TEXT)");
		  
		  db.execSQL("CREATE TABLE "+TABLE_USERS+" ("+KEY_ID+ " INTEGER PRIMARY KEY , "
		  +KEY_NAME+ " TEXT, " 
		  +KEY_TELEFONNUMMER+ " TEXT, "
		  +KEY_EMAIL+ " TEXT, "
		  +KEY_VERIFIED+ " TEXT, "
		  +KEY_REGISTERED+ " TEXT, "
		  +KEY_SIM+ " TEXT)");
		  Log.d("CREATE ON TABLE", "BLA");
		  
		 }

	 
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  // TODO Auto-generated method stub
		  
		  db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROFILES);
		  
		  onCreate(db);
		 }
	
	 /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
  //Neues Profil erstellen
    public void addProfile(Profile profile) {
 
        if(!(exist(profile))){
        	
            SQLiteDatabase db = this.getWritableDatabase();
        	ContentValues values = new ContentValues();
	        values.put(KEY_NAME, profile.getName()); 
	        values.put(KEY_TELEFONNUMMER, profile.getPhoneNumber()); 
	        values.put(KEY_EMAIL,  profile.getEmail()); 
	        values.put(KEY_REGISTERED, Boolean.toString(profile.isRegistered())); 
	        // Reihe in Tabelle eintragen
	        db.insert(TABLE_PROFILES, KEY_NAME, values);
	        db.close(); // database Verbindung schliessen
        }
        else{
        	Log.i("addProfile", "user der erstellt werden soll, existiert schon!");
        }
        
       
    }
	
  //gibt im log ein Profil aus, die Telefonnummer gegeben ist
    public void print(String telefonnummer){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	Cursor c = db.query(TABLE_PROFILES,new String[] { KEY_ID, KEY_NAME, KEY_TELEFONNUMMER, KEY_EMAIL, KEY_REGISTERED}, KEY_TELEFONNUMMER + "=?",
                new String[] { telefonnummer }, null, null, null, null);
      	c.moveToFirst();		
      	
      	if(c.getCount()!=0){
      		Log.i("print", String.valueOf(c.getInt(0))+", " + c.getString(1)+", " + c.getString(2)+", " + c.getString(3)+", " + c.getString(4));
      	}
      	db.close();
    }
    
  //Profile loeschen
    public int deleteProfile()
    {
     SQLiteDatabase db=this.getWritableDatabase();
     int tmp = db.delete(TABLE_PROFILES, null, null);
     db.close();
     return tmp;
    } 
    
  //ŸberprŸft ob es dazu einen eintrag in der DB gibt (um doppelbelegungen zu verhindern) -->>>> Sollte auch noch beim USER eingefŸgt werde!!!!
    public boolean exist(Profile profile){

    	boolean var = false;
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor c = db.query(TABLE_PROFILES,new String[] { KEY_ID, KEY_NAME, KEY_TELEFONNUMMER, KEY_EMAIL, KEY_REGISTERED}, KEY_TELEFONNUMMER + "=?",
                new String[] { profile.getPhoneNumber() }, null, null, null, null);
    	c.moveToFirst();		
    	if(c.getCount()!=0){
	      	if(c.getString(2).equalsIgnoreCase(profile.getPhoneNumber())){	
	      		 var =true;
	     	}
    	}
      	db.close();
    	return var;
    }
    
    
  //gibt alle Profile aus, die einen account haben
    public ArrayList<Profile> getAllRegistered(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ArrayList<Profile> tmp = new ArrayList<Profile>();
    	
    	Cursor c = db.query(TABLE_PROFILES,new String[] { KEY_ID, KEY_NAME, KEY_TELEFONNUMMER, KEY_EMAIL, KEY_REGISTERED}, KEY_REGISTERED + "=?",
                new String[] { "true" }, null, null, null, null);
    	c.moveToFirst();	
    	
    	if(c.getCount()==0){
    		Log.i("AUSGABE", "Es existieren keine registrierten Profile");
    	}
    	else{
    		
    		for(int i=0; i< c.getCount(); i++){
        		Profile tmpProfile = new Profile();
        		tmpProfile.setId(c.getInt(0));
        		tmpProfile.setName(c.getString(1));
        		tmpProfile.setPhoneNumber(c.getString(2));
        		tmpProfile.setEmail(c.getString(3));
        		tmpProfile.setRegistered(true);
    			tmp.add(tmpProfile);
    			c.moveToNext();
        	}
    	}
    	c.close();
      	db.close();	
    	
    	return tmp;
    	
    }

  //gibt alle Profile aus, die keinen account habe
    public ArrayList<Profile> getAllUnregistered(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ArrayList<Profile> tmp = new ArrayList<Profile>();
    	
    	Cursor c = db.query(TABLE_PROFILES,new String[] { KEY_ID, KEY_NAME, KEY_TELEFONNUMMER, KEY_EMAIL, KEY_REGISTERED}, KEY_REGISTERED + "=?",
                new String[] { "false" }, null, null, null, null);
    	c.moveToFirst();	
    	
    	if(c.getCount()==0){
    		Log.i("AUSGABE", "Es existieren keine registrierten Profile");
    	}
    	
    	else{
    		
    		for(int i=0; i< c.getCount(); i++){
    			
        		Profile tmpProfile = new Profile();
        		tmpProfile.setId(c.getInt(0));
        		tmpProfile.setName(c.getString(1));
        		tmpProfile.setPhoneNumber(c.getString(2));
        		tmpProfile.setEmail(c.getString(3));
        		tmpProfile.setRegistered(false);
    			tmp.add(tmpProfile);
    			c.moveToNext();
        	}
    	}
    	c.close();
      	db.close();	
    	
    	return tmp;
    }
    
  //Ÿbernimmt die Profile aus dem Telefonbuch (argument array List) und ŸbertrŠgt diese in die lokale DB
    public void updateLocalDB(ArrayList<Profile> profileAusKontaktdaten){
	
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	for(Profile n:profileAusKontaktdaten){
    		if(!exist(n)){
    			addProfile(n);
    		}
    	} 	
    }
    
  //funktion der die Array List aus ServerDB.profilSync(...) Ÿbergeben werden
    public void profilSync(ArrayList<Profile> profileVomServer){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	for(Profile n:profileVomServer){
    			if(!(isRegistered(n))){  //Dieser TEst ist vllt ŸberflŸssig, mal schauen
    				register(n);
    			}
    			else{
        		Log.d("ServerLOKAL test", "PROFIL SCHON REGISTRIERT");
    			}
    	}
    }
    
    //funktion, die fŸr ein profil ueberprueft, ob es registriert ist anhand der Telefonnummer!!!!
    public boolean isRegistered(Profile profil){
    	boolean isRegistered = false;
    	SQLiteDatabase db = this.getWritableDatabase();

    	Cursor c = db.query(TABLE_PROFILES,new String[] { KEY_ID, KEY_NAME, KEY_TELEFONNUMMER, KEY_EMAIL, KEY_REGISTERED}, KEY_TELEFONNUMMER + "=?",
              new String[] { profil.getPhoneNumber() }, null, null, null, null);
    	c.moveToFirst();		
    			
    	if(c.getString(4).equalsIgnoreCase("true")){
    		isRegistered=true;
    	}
    	c.close();
    	db.close();
    	return isRegistered;
    }
    
    //setz ein profil auf registriert
    public void register(Profile profile){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
        ContentValues values = new ContentValues();
        values.put(KEY_REGISTERED, "true");

        db.update(TABLE_PROFILES, values, KEY_TELEFONNUMMER + "=?", new String[] { profile.getPhoneNumber() });
        db.close();
    	
    }
    
    
    // nicht geschrieben
//    public int deleteUser(int id)
//    {
//     SQLiteDatabase db=this.getWritableDatabase();
//     int tmp = db.delete(TABLE_PROFILES, null, null);
//     db.close();
//     return tmp;
//    }
    
    //User als verified setzen
//    public void verifyUser(User user)
//    {
//    SQLiteDatabase db=this.getWritableDatabase();
//    Log.i("hier der Path", db.getPath());
//    
//    ContentValues values = new ContentValues();
//    values.put(KEY_VERIFIED, "true");
//    db.update(TABLE_PROFILES, values, null, null);
//    
//    db.close();
//    }
//   
//    //User Sim setzen
//    public void setSim(String sim)
//    {
//    SQLiteDatabase db=this.getWritableDatabase();
//    
//    ContentValues values = new ContentValues();
//    values.put(KEY_SIM, sim); 
//    db.update(TABLE_PROFILES, values, null, null);
//    
//    db.close();
//    }
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
//        db.close(); // database Verbindung schlieï¿½en
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
//        // Alles zum Auslesen auswï¿½hlen
//        String selectQuery = "SELECT  * FROM " + TABLE_PROFILES;
// 
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // alle einzelnen Reihen werden an die Liste gehï¿½ngt
//        if (cursor.moveToFirst()) {
//            do {
//                Profil profil = new Profil();
//                profil.setId(Integer.parseInt(cursor.getString(0)));
//                profil.setName(cursor.getString(1));
//                profil.setPasswort(cursor.getString(2));
//                profil.setFahrzeug(cursor.getString(3));
//                // Profil wird zur Liste hinzugefï¿½gt
//                profilList.add(profil);
//            } while (cursor.moveToNext());
//        }
// 
//        // Rï¿½ckgabe der Liste
//        return profilList;
//    }
// 
//    // ï¿½ndern eines Profils
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
//    // Lï¿½schen eines einzelnen Profils
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
//        // Rï¿½ckgabe des Werts
//        return cursor.getCount();
//    }
 
}
