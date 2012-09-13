package mp.challengeaccepted;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

public class DatabaseOnBoard 
{
	SQLiteDatabase myDB=null;
	

	static final String DB_NAME ="ChallengeAccepted.db";
	static final int DB_VERSION =1;
	static final String CHALLENGES_TABLE ="Challenges";
	static final String NUMBER_TABLE="myPhoneNumber";
	static final String C_ID="_ID";
	static final String C_TIMESTAMP = "created_at";
	static final String C_EMITTER = "emitter";
	static final String C_RECEIVER ="receiver";
	static final String C_SHORTDESCRIPTION ="shortDescription";
	static final String C_LONGDESCRIPTION ="longDescription";
	static final String C_DEADLINE ="deadline";
	static final String C_PROOF ="proof";
	static final String C_STATUS="status";
	Context context;

/*
	private void onCreateDBAndDBTabled()
	{
		
		try {
			myDB = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null);
			//myDB.delete(TABLE, null, null); 
			{ 
				String sql ="create table if not exists " + TABLE + " ("+ C_LATITUDE + " double, " + C_LONGITUDE + " double, " + C_HORIZONTAL + " double, " + C_ERRORHORIZONTAL + " double,  " + C_VERTICAL + " double, " + C_ERRORVERTICAL + " double, " + C_ANZAHL + " double,"+ C_GENAUIGKEIT + " double)";
				myDB.execSQL(sql);
			}
			Log.d("Database","created"); 
	    } 
		finally 
		{
	         if (myDB != null);
	    }
		
	}
	
	
	
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
