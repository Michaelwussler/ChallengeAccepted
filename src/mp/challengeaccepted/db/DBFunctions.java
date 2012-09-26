package mp.challengeaccepted.db;

import java.util.ArrayList;
import java.util.List;

import mp.challengeaccepted.Profile;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
	 
import android.util.Log;
	 
	public class DBFunctions  {
	 
	    private JSONParser jsonParser;
	 
	    private static String URL = "http://www.challenge.net16.net/"; 
	    
	    private static String anmelden_tag = "anmelden";
	    private static String isuser_tag = "isuser";
	    private static String removeuser_tag = "removeuser";
	    private static String profilsync_tag = "profilsync";
	    private static String challengesync_tag = "challengesync";
	    private static String challengenew_tag = "challengenew";
	    private static String areusers_tag = "areusers";
	    private static String createtable_tag = "createtable";
	    private static String droptable_tag = "droptable"; 
	    
	    // Konstructor
	    public DBFunctions(){
	        jsonParser = new JSONParser();
	    }
	 
	    /**
	     * Funktion um einen neues profil (das des USERS) zu erstellen
	     * */
	    public JSONObject anmelden(String name, String email, String telefonnummer){
	    	// erstellt einen neues profil in der db und gibt dieses als JSON zurück um es in der localDB zu speichern

	        // Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", anmelden_tag));
	        params.add(new BasicNameValuePair("name", name));
	        params.add(new BasicNameValuePair("email", email));
	        params.add(new BasicNameValuePair("telefonnummer", telefonnummer));

	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
	    /**
	     * Funktion um die existenz eines Profils zu überprüfen anhand der Telelfonnummer
	     * */    
	    public JSONObject isUser(String telefonnummer){
	    	// Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", isuser_tag));
	        params.add(new BasicNameValuePair("telefonnummer", telefonnummer));

	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
	    /**
	     * Funktion um ein Array von Profilen auf seine Eistenz in der DB zu überprüfen
	     * */    
	    public JSONObject areUsers(ArrayList<Profile> argProfile){
	    	// Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", areusers_tag));
	    	params.add(new BasicNameValuePair("anzahlProfile", Integer.toString(argProfile.size())));
	        
	        int i=0;
			for(Profile n:argProfile){
		        params.add(new BasicNameValuePair("arg"+Integer.toString(i), n.getPhoneNumber()));
		        i++;
			}
			
	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    	
	    }
	   
	    /**
	     * Funktion um ein Profil zu löschen anhand der Telelfonnummer oder email
	     * */    
	    public JSONObject removeUser(String email_telefonnummer){
	    	// Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", removeuser_tag));
	        params.add(new BasicNameValuePair("email_telefonnummer", email_telefonnummer));

	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
	    /**
	     * Funktion um einen neue Challenge zu erstellen
	     * */
	    public JSONObject erstelleChallenge(String name, String email, String telefonnummer){
	    	// erstellt einen neues profil in der db und gibt dieses als JSON zurück um es in der localDB zu speichern

	        // Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", anmelden_tag));
	        params.add(new BasicNameValuePair("name", name));
	        params.add(new BasicNameValuePair("email", email));
	        params.add(new BasicNameValuePair("telefonnummer", telefonnummer));

	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
	    /**
	     * Funktion um ein neues User Table anzulegen
	     * */
	    public JSONObject createTable(String telefonnummer){
	    	// erstellt einen challenge Table für den Benutzer

	        // Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", createtable_tag));
	        params.add(new BasicNameValuePair("telefonnummer", telefonnummer));
	        
	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
	    public JSONObject dropTable(String telefonnummer){
	    	// erstellt einen challenge Table für den Benutzer

	        // Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", droptable_tag));
	        params.add(new BasicNameValuePair("telefonnummer", telefonnummer));
	        
	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
//	    /**
//	     * Funktion um die Profile auf der lokalenDB mit denen vom Server zu syncen
//	     * */
//	    public ArrayList<Profile> profil_sync(ArrayList<Profile> aktuelleNichtBestaetigteProfile) {
//	    	//aktuelleNichtBestaetigteProfile enthält die in der lokalendB gespeicherten Profile (oder auf jedenfall alle Telefonnummern, die nicht bei Challange registreit sind )
//	    
//	    	ArrayList<Profile> ausgabe = new ArrayList<Profile>();
//	    	//ausgabe gibt die zusaetzlichen Profile, die noch nicht abgerufen wurden weiter, die die Datenbank 
//	    	
//	    	// Building Parameters
//	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
//	    	params.add(new BasicNameValuePair("tag", profilsync_tag));
//        	params.add(new BasicNameValuePair("anzahlProfile", Integer.toString(aktuelleNichtBestaetigteProfile.size())));
//        
//        	// Parameter der kartenpunkte übergeben
//        	
//        	for(int i=0; i<aktuelleNichtBestaetigteProfile.size(); i++)
//        	{
//        		Profile arg = aktuelleNichtBestaetigteProfile.get(i);        		
//            	params.add(new BasicNameValuePair("profil"+Integer.toString(i), arg.getPhoneNumber()));
//        	}
//
//        	
//        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
//        
//        try {
//        	 Log.d("Hier?", Integer.toString(123467890));
//
//	    		int anzahlProfile = aktuelleNichtBestaetigteProfile.size();
//	    		
//				for(int i=0; i<1/*anzahlProfile*/; i++)
//	        	{
//					JSONObject json_profilTemp = json.getJSONObject("argprofil"+Integer.toString(i)); //ließt die einzelnen Zeilen aus
//		        	 Log.d("Hier?", Integer.toString(123467890));
//					Profile kpTemp = new Profile(json_profilTemp.optString("telefonnummer")); //erstellt einen neuen Profil eintrag
//					
//					ausgabe.add(kpTemp);
//				}
//								
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}    	
	    	        
	        // Rückgabe des json Objekts
//	        return ausgabe;	    
//	    }
	    
	   

	    /**
	     * Hilfsklasse zum Konvertieren von Integer nach Boolean
	     */
		public boolean convertIntToBoolean(int intValue)	
		{
		return (intValue != 0);
		}	
		
	}
