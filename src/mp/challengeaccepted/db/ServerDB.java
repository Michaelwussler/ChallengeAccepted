package mp.challengeaccepted.db;
import java.util.ArrayList;

import mp.challengeaccepted.App;
import mp.challengeaccepted.DatabaseHandlerProfile;
import mp.challengeaccepted.Profile;
import mp.challengeaccepted.User;

import org.json.JSONArray;
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
	 
	//funktion zum überprüfen, ob ein Array von Profilen in der Datenbank vorhanden ist.
	static public ArrayList<Profile> areUsers(ArrayList<Profile> argProfile){
		ArrayList<Profile> tmpProfile = new ArrayList<Profile>();
		DBFunctions databasefunction = new DBFunctions();
	
		JSONObject json = databasefunction.areUsers(argProfile);		
	
		try{
	           	 for(int i=0; i<argProfile.size(); i++){
	           		 int j=0;
//	   	    	  Log.i("Kukuk", json.getJSONObject("challenges").getString("result"+String.valueOf(i)));
		           	 if(json.getJSONObject("challenges").getString("result"+String.valueOf(i)).equals(argProfile.get(i).getPhoneNumber())){
		           		 tmpProfile.add(argProfile.get(i));
		           		 tmpProfile.get(j).setVerified(true);
		           		 Log.i("Was wird hier übergeben?", argProfile.get(i).getPhoneNumber());	
		           		 j++;
		           	 }		
		           	 else{
		           		 	Log.d("user ueberpruefung nicht erfolgreich", KEY_ERROR);
		           	 }
	           	 }
	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
		
		return tmpProfile;
		
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
	
	//Übergibt alle nicht regitrierten Profile und gibt die zurück, die existieren auf der Server DB
	static public ArrayList<Profile> profilSync(ArrayList<Profile> alleNichtRegistriertenProfile){
		ArrayList<Profile> tmp = new ArrayList<Profile>();
		
    	for(Profile n:alleNichtRegistriertenProfile){
    			Log.i("Telefonnummer KUCKENE!!!!!", n.getPhoneNumber());
    			if(ServerDB.isUser(n)){
    		    		Log.d("ServerDB test", "USER X existiert.");
    		    		tmp.add(n);
    			}
    			else{
        		Log.d("ServerDB test", "USER X existiert nicht");
    			}
    	}
    	
    	return tmp;
	}
	
	
	//erstellt ein table mit challenges für einen nutzer
	static public void createTable(User user){
		
		DBFunctions databasefunction = new DBFunctions();
		JSONObject json = databasefunction.createTable(user.getPhoneNumber());		
				
	      try {
	            String res = json.getString(KEY_SUCCESS);
	            if(Integer.parseInt(res) == 1){
	           	 Log.d("table erzeugt", KEY_SUCCESS);
	            }else{
	           	 Log.d("table nicht erzeugt", KEY_ERROR);
	            }

	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
		}
	
	//löscht ein table mit challenges für einen nutzer
	static public void dropTable(User user){
		
		DBFunctions databasefunction = new DBFunctions();
		JSONObject json = databasefunction.dropTable(user.getPhoneNumber());		
				
	      try {
	            String res = json.getString(KEY_SUCCESS);
	            if(Integer.parseInt(res) == 1){
	           	 Log.d("table geloescht", KEY_SUCCESS);
	            }else{
	           	 Log.d("table nicht geloescht", KEY_ERROR);
	            }

	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
		}
	
	//JSONARRAY to ArrayList converter
	static public ArrayList<String> convert(JSONArray input) throws JSONException{
	ArrayList<String> list = new ArrayList<String>();     
		if (input != null) { 
		   int len = input.length();
		   for (int i=0;i<len;i++){ 
		    list.add(input.get(i).toString());
		   } 
		} 
	return list;
	}
	
}
