package mp.challengeaccepted.db;
import java.util.ArrayList;

import mp.challengeaccepted.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServerDB 
{
	
	static String KEY_SUCCESS = "success";
	static String KEY_ERROR = "error";
	  

	//funktion zum erst und einmaligen anmelden eines noch nicht registrieten Users
	static public void registerUser(String name, String email, String telefonnummer){
      
	DBFunctions databasefunction = new DBFunctions();
	JSONObject json = databasefunction.anmelden(name, email, telefonnummer);		
			
      try {
            String res = json.getString(KEY_SUCCESS);
            if(Integer.parseInt(res) == 1){
           	 Log.d("Profil speichern", KEY_SUCCESS);
            }else{
           	 Log.d("Profil nicht gespeichert", KEY_ERROR);
            }

    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
	}
	
	//funktion zum ueberprüfen, ob ein profil in der Datenbank vorhanden ist.
	static public boolean isUser(Profile profile){
	      
	boolean isUser =false;	
	
	DBFunctions databasefunction = new DBFunctions();
	JSONObject json = databasefunction.isUser(profile.getPhoneNumber());		
			
      try {
            String res = json.getString(KEY_SUCCESS);
            if(Integer.parseInt(res) == 1){
           	 Log.d("user ueberpruefung erfolgreich", KEY_SUCCESS);
           	 if(json.optInt("anzahlReihen")!=0){
           		 isUser =true;
           		 profile.setPhoneNumber( json.optString("telefonnummer"));
           		 profile.setId(json.optInt("id"));
           		 
           	 }else{
           		isUser =false;
           	 }		 
            }else{
           	 Log.d("user ueberpruefung nicht erfolgreich", KEY_ERROR);
            }

    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
	return isUser;
	}

	
	//entfernt ein profil aus der datenbank
	static public void removeUser(String email_telefonnummer){
		//als Argument kann  email oder telefonnummer angegeben werden
		
	DBFunctions databasefunction = new DBFunctions();
	JSONObject json = databasefunction.removeUser(email_telefonnummer);		
			
      try {
            String res = json.getString(KEY_SUCCESS);
            if(Integer.parseInt(res) == 1){
           	 Log.d("Profil geloescht", KEY_SUCCESS);
            }else{
           	 Log.d("Profil nicht geloescht", KEY_ERROR);
            }

    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
	}
	

	static public void profilSync(ArrayList<Profile> argProfile){
		
    	for(Profile n:argProfile){
    		if(!(n.isRegistered()))
    			if(ServerDB.isUser(n)){
    		    		Log.d("ServerDB test", "USER X existiert.");
    			}
    			else{
        		Log.d("ServerDB test", "USER X existiert nicht");
    			}
    	}

	}

	
}
