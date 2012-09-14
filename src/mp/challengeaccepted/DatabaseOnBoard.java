package mp.challengeaccepted;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseOnBoard 
{
	SQLiteDatabase myDB=null;
	

	static final String DB_NAME ="ChallengeAccepted.db";
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


	private void onCreateDBAndDBTabled()
	{
		
		try {
			myDB = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null);
			//myDB.delete(TABLE, null, null); 
			{ 
				String sql ="create table if not exists " + USER_TABLE + " ("+ USER_NAME + " string, " + USER_NUMBER + " string, " + USER_MAIL + " string, " + USER_VERIFIED + " boolean)";
				myDB.execSQL(sql);
				sql ="create table if not exists " + CONTACT_TABLE + " ("+ CONTACT_NAME + " string, " + CONTACT_NUMBER + " string, " + CONTACT_MAIL + " string, " + CONTACT_VERIFIED + " boolean)";
				myDB.execSQL(sql);
				sql ="create table if not exists " + CHALLENGES_TABLE + " ("+ C_ID + " integer, " + C_SENDER + " string, " + C_RECEIVER + " string, " + C_TITLE + " string"+ C_DESCRIPTION + " string"+ C_PROOF + " string"+ C_STATUS + " integer"+ C_TIMESTAMP + " string)";
				myDB.execSQL(sql);
				
			}
			Log.d("Database","created"); 
	    } 
		finally 
		{
	         if (myDB != null);
	         myDB.close();
	    }
		
	}
	
	
	/*
	public static void laden(double latitudeMin, double latitudeMax, double longitudeMin, double longitudeMax)
	{
				Cursor c = myDB.query(TABLE, null,"("+ C_LATITUDE +" BETWEEN " + String.valueOf(latitudeMin)+" AND " +String.valueOf(latitudeMax)+") AND ("+C_LONGITUDE+ " BETWEEN "+String.valueOf(longitudeMin)+" AND "+String.valueOf(longitudeMax)+")", null, null, null, null);
				//Cursor c = myDB.query(TABLE, null, null, null, null, null, null);
				Log.d("Count",String.valueOf(c.getCount()));
				c.moveToFirst(); 
				for(int i=0;i<c.getCount();i++)
				{
				Location location=new Location("GPS");
				location.setLatitude(c.getDouble(0));
				location.setLongitude(c.getDouble(1));
				location.setAccuracy((float) c.getDouble(7));
				puffer.add(new Messpunkt(location,c.getDouble(4),c.getDouble(2),c.getDouble(6),System.currentTimeMillis()));
				if(c.getCount()!=i+1)
				{
					c.moveToNext();
				}
			}
		}
	
	
	public static void speichern(Messpunkt messpunkt)
	{
		
				ContentValues contentValues=new ContentValues(); 
				contentValues.put(C_LATITUDE, messpunkt.location.getLatitude());
				contentValues.put(C_LONGITUDE, messpunkt.location.getLongitude());
				contentValues.put(C_HORIZONTAL, messpunkt.getMagneticHorizontal());
				contentValues.put(C_VERTICAL, messpunkt.getMagneticVertical());
				contentValues.put(C_ERRORHORIZONTAL, messpunkt.getFehlerHorizontal());
				contentValues.put(C_ERRORVERTICAL, messpunkt.getFehlerVertical());
				contentValues.put(C_ANZAHL,messpunkt.getAnzahl());
				contentValues.put(C_GENAUIGKEIT,messpunkt.location.getAccuracy());
				myDB.insert(TABLE, null, contentValues);
	}
			
}
*/
}
