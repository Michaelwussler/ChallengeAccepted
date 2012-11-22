package mp.challengeaccepted.db;
import java.util.ArrayList;

import mp.challengeaccepted.App;
import mp.challengeaccepted.Challenge;
import mp.challengeaccepted.DatabaseHandlerProfile;
import mp.challengeaccepted.DatabaseHandlerUser;
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
	
	
	//erstellt eine neue challenge
	static public Challenge newChallenge(Challenge challenge){
		
		DBFunctions databasefunction = new DBFunctions();
		
		JSONObject json = databasefunction.challengeNew(challenge.getTitle(), challenge.getDescription(), challenge.getReceiver().getPhoneNumber(), challenge.getSender().getPhoneNumber(), challenge.getChannel(), String.valueOf(1), challenge.getTimestamp().toString());
		
			try {
	            String res = json.getString(KEY_SUCCESS);
	            if(Integer.parseInt(res) == 1){
	           	 Log.d("Challenge erfolgreich erstellt", KEY_SUCCESS);
	           	 if(json.optInt("Challenge")!=0){ 
	           		 challenge.setServerId(json.optInt("serverid"));
	        
	           	 }else{
	           		challenge.setServerId(-1);
	           	 }		  
	            }else{
	           	 Log.d("Challenge nicht erfolgreich erstellt", KEY_ERROR);
	            } 

	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
			return challenge;
	}


	//fügt einer challenge den Beweis hinzu
	static public Challenge addProof(User user, Challenge challenge, String proof){
		
		DBFunctions databasefunction = new DBFunctions();
		
		JSONObject json = databasefunction.addProof(user, challenge.getServerId(), proof);		
				
	      try {
	            String res = json.getString(KEY_SUCCESS);
	            if(Integer.parseInt(res) == 1){
	           	 Log.d("Beweis hinzugefuegt", KEY_SUCCESS);
	     		 challenge.setProof(proof);
	            }else{
	           	 Log.d("Beweis konnte nicht hinzugefuegt werden", KEY_ERROR);
	     		 challenge.setProof("kein Beweis");
	            }

	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	      return challenge;
		}	
	
	//fügt einer challenge den Beweis hinzu
	static public ArrayList<Challenge> getUpdatedChallenges(User user){
		
		
		DBFunctions databasefunction = new DBFunctions();
		JSONObject json = databasefunction.getUpdated(user.getPhoneNumber());		
		ArrayList<Challenge> tmpChallenges = new ArrayList<Challenge>();
	 
		try{
						
			JSONArray the_json_array = json.getJSONArray("");

			    int size = the_json_array.length();
			    
			    ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
			    for (int i = 0; i < size; i++) {
			        JSONObject another_json_object = the_json_array.getJSONObject(i);     
			        arrays.add(another_json_object);
			    }

			JSONObject[] jsons = new JSONObject[arrays.size()];
			arrays.toArray(jsons);

			
//				int reihen = json.optInt("anzahlReihen");
//				JSONArray json2=json.getJSONArray("result2");
//
//				if(reihen!=0){
//					for(int i=1; i<=reihen; i++){ 
						
						
//						
//			           	 String serverid = json2.getJSONObject(i).getString("serverid");
//			           			 //getJSONObject("laden"+String.valueOf(i)); 
//			           	 String title = json.getJSONObject("laden"+String.valueOf(i)).getString("title");
//			           	 String description = json.getJSONObject("laden"+String.valueOf(i)).getString("description");
//			           	 String receiver = json.getJSONObject("laden"+String.valueOf(i)).getString("receiver");
//			           	 String sender = json.getJSONObject("laden"+String.valueOf(i)).getString("sender");
//			           	 String status = json.getJSONObject("laden"+String.valueOf(i)).getString("status");
//			           	 String proof = json.getJSONObject("laden"+String.valueOf(i)).getString("proof");
//			           	 Log.i("schauen", serverid + "," + title + "," + description+ "," + receiver+ "," + sender+ "," + status+ "," + proof);
//			           	// Log.i("schauen", serverid);
//						
////			           	 tmpChallenges.add(new Challenge(serverid, title, description, receiver, sender, proof, status));
//		           	 } 
//				}
//				else{Log.d("keine Challenges zum updaten gefunden", "alle uptodate"); 
//				}
	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	//	Challenge.log_print(tmpChallenges.get(0));
		
		return tmpChallenges; 
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
