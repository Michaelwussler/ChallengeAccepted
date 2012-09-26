package mp.challengeaccepted;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

public class DatabaseHandlerChallenge extends SQLiteOpenHelper {
		 
	// static Variablen zur Beschreibung der Database
	// Database Version
    private static final int DATABASE_VERSION = 1;
    
    // Database Name
    private static final String DATABASE_NAME = "challenge";	 
    private static final String TABLE_PROFILES = "profile";
    private static final String TABLE_USERS = "user";
    private static final String TABLE_CHALLENGES = "challenges";


   
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TELEFONNUMMER = "telefonnummer";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_VERIFIED = "verified";
    private static final String KEY_REGISTERED = "registered";
    private static final String KEY_SIM = "sim";
    
    private static final String KEY_SERVERID = "serverid";
    private static final String KEY_TITLE ="title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PROOF = "proof";
    private static final String KEY_RECEIVER = "receiver";
    private static final String KEY_SENDER = "sender";
    private static final String KEY_CHANNEL = "channelChallenge";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TIME = "time";


    //Konstruktor
    public DatabaseHandlerChallenge(Context context) {
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
		  
		  db.execSQL("CREATE TABLE "+TABLE_CHALLENGES+" ("+KEY_ID+ " INTEGER PRIMARY KEY , "
		  +KEY_SERVERID+ " TEXT, " 
		  +KEY_TITLE+ " TEXT, " 
		  +KEY_DESCRIPTION+ " TEXT, "
		  +KEY_PROOF+ " TEXT, "
		  +KEY_RECEIVER+ " TEXT, "
		  +KEY_SENDER+ " TEXT, "
		  +KEY_CHANNEL+ " TEXT, "
		  +KEY_STATUS+ " TEXT, "
		  +KEY_TIME+ " TEXT)");		  
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
    public int addChallenge(Challenge challenge) { //gibt als Wert die zugewiesen ID zurück
    	int arg =0;
        if(!(exist(challenge))){
        	
            SQLiteDatabase db = this.getWritableDatabase();
        	ContentValues values = new ContentValues();
	        values.put(KEY_TITLE, challenge.getTitle()); 
	        values.put(KEY_DESCRIPTION, challenge.getDescription()); 
	        values.put(KEY_PROOF,  challenge.getProof()); 
	        values.put(KEY_RECEIVER,  challenge.getReceiver().getPhoneNumber()); 
	        values.put(KEY_SENDER,  challenge.getSender().getPhoneNumber()); 
	        values.put(KEY_CHANNEL,  challenge.getChannel()); 
	        values.put(KEY_STATUS,  String.valueOf(challenge.getStatus())); 
	        values.put(KEY_TIME,  String.valueOf(System.currentTimeMillis())); 	   //TODO welche Zeit hier?      
	        // Reihe in Tabelle eintragen
	        db.insert(TABLE_CHALLENGES, KEY_TITLE, values);
	        
	    	Cursor c = db.query(TABLE_CHALLENGES,new String[] { KEY_ID, KEY_SERVERID, KEY_TITLE, KEY_DESCRIPTION, KEY_PROOF, KEY_RECEIVER, KEY_SENDER, KEY_CHANNEL, KEY_STATUS, KEY_TIME}, KEY_DESCRIPTION + "=?",
	                new String[] { challenge.getDescription() }, null, null, null, null);
	    	c.moveToFirst();	
	    	arg = Integer.parseInt(c.getString(0));
	        // hier muss noch irgendwie die hin und rückgabe der SERVERID geregelt werden!!
	        db.close(); // database Verbindung schliessen

        }
        else{
        	arg= -1;
        	Log.i("addChallenge", "Challenge die erstellt werden soll, existiert schon!");
        }
        return arg;
    }
	
    //Neues Profil erstellen
    public void addProof(String proof, Challenge challenge) {
 
        if(exist(challenge)){
        	
            SQLiteDatabase db = this.getWritableDatabase();
        	ContentValues values = new ContentValues();
	        values.put(KEY_PROOF,  proof); 
	       
	        db.update(TABLE_CHALLENGES, values, KEY_ID + "=?", new String[] { String.valueOf(challenge.getId()) });
	        db.close(); // database Verbindung schliessen
        }
        else{
        	Log.i("addProof", "challenge existiert nicht!");
        }
    }
    
    //Neues Profil erstellen
    public void changeStatus(int status, Challenge challenge) {
 
        if(exist(challenge)){
        	
            SQLiteDatabase db = this.getWritableDatabase();
        	ContentValues values = new ContentValues();
	        values.put(KEY_STATUS,  String.valueOf(status)); 
	       
	        db.update(TABLE_CHALLENGES, values, KEY_ID + "=?", new String[] { String.valueOf(challenge.getId()) });
	        db.close(); // database Verbindung schliessen
        }
        else{
        	Log.i("changeStatus", "challenge existiert nicht!");
        }
    }
    
  //Challenges loeschen
    public int deleteChallenge()
    {
     SQLiteDatabase db=this.getWritableDatabase();
     int tmp = db.delete(TABLE_CHALLENGES, null, null);
     db.close();
     return tmp;
    } 
    
    
    
  //überprüft ob es dazu einen eintrag in der DB gibt (um doppelbelegungen zu verhindern)
    public boolean exist(Challenge challenge){

    	boolean var = false;
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor c = db.query(TABLE_CHALLENGES,new String[] { KEY_ID, KEY_SERVERID, KEY_TITLE, KEY_DESCRIPTION, KEY_PROOF, KEY_RECEIVER, KEY_SENDER, KEY_CHANNEL, KEY_STATUS, KEY_TIME}, KEY_ID + "=?",
                new String[] { String.valueOf(challenge.getId()) }, null, null, null, null);
    	c.moveToFirst();		
    	if(c.getCount()!=0){
	      	if(c.getString(0).equalsIgnoreCase(String.valueOf(challenge.getId()))){	
	      		 var =true;
	     	}
    	}
      	db.close();
    	return var;
    }
    
    
  //gibt alle Profile aus, die einen account haben
    public ArrayList<Challenge> getAllChallenges(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ArrayList<Challenge> tmp = new ArrayList<Challenge>();

    	Cursor c = db.query(TABLE_CHALLENGES,new String[] { KEY_ID, KEY_SERVERID, KEY_TITLE, KEY_DESCRIPTION, KEY_PROOF, KEY_RECEIVER, KEY_SENDER, KEY_CHANNEL, KEY_STATUS, KEY_TIME}, null,
                null, null, null, null, null);
    	c.moveToFirst();	
    	
    	if(c.getCount()==0){
    		Log.i("AUSGABE", "Es existieren keine Challenges");
    	}
    	else{
    		
    		for(int i=0; i< c.getCount(); i++){
        		Challenge tmpchallenge = new Challenge();
        		tmpchallenge.setId(c.getInt(0));
        		tmpchallenge.setServerId(c.getInt(1));
        		tmpchallenge.setTitle(c.getString(2));
        		tmpchallenge.setDescription(c.getString(3));
        		tmpchallenge.setProof(c.getString(4));
        		tmpchallenge.getReceiver().setPhoneNumber(c.getString(5));
        		tmpchallenge.getSender().setPhoneNumber(c.getString(6));
        		tmpchallenge.setChannel(c.getString(7));
        		tmpchallenge.setStatus(Integer.getInteger(c.getString(8)));
        		tmpchallenge.setTimestamp(java.sql.Timestamp.valueOf(c.getString(9)));
        		
    			tmp.add(tmpchallenge);
    			c.moveToNext();
        	}
    	}
    	c.close();
      	db.close();	
    	
    	return tmp;
    	
    }

 
}
