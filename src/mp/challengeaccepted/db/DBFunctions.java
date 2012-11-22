package mp.challengeaccepted.db;

import java.util.ArrayList;
import java.util.List;

import mp.challengeaccepted.Profile;
import mp.challengeaccepted.User;

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
	    private static String getupdatedchallenges_tag = "getupdatedchallenges";
	    private static String addproof_tag = "addproof";
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
	    
	    public JSONObject challengeNew(String title, String description, String receiver, String sender, String channel, String status, String time){
	    	// erstellt einen challenge Table für den Benutzer

	        // Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", challengenew_tag));
	        params.add(new BasicNameValuePair("title", title));
	        params.add(new BasicNameValuePair("description", description));
	        params.add(new BasicNameValuePair("receiver", receiver));
	        params.add(new BasicNameValuePair("sender", sender));
	        params.add(new BasicNameValuePair("channel", channel));
	        params.add(new BasicNameValuePair("status", status));
	        params.add(new BasicNameValuePair("time", time));
	        
	        
	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }	    
	    
//	    public JSONObject challengeSync(Challenge challenge){
//	    	// erstellt einen challenge Table für den Benutzer
//
//	        // Building Parameters
//	        List<NameValuePair> params = new ArrayList<NameValuePair>();
//	        params.add(new BasicNameValuePair("tag", challengesync_tag));
//	        params.add(new BasicNameValuePair("title", title));
//	        params.add(new BasicNameValuePair("description", description));
//	        params.add(new BasicNameValuePair("receiver", receiver));
//	        params.add(new BasicNameValuePair("sender", sender));
//	        params.add(new BasicNameValuePair("channel", channel));
//	        params.add(new BasicNameValuePair("status", status));
//	        params.add(new BasicNameValuePair("time", time));
//	        
//	        
//	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
//	        // Rückgabe des json Objekts 
//	        return json;
//	    }	    
	    
	    public JSONObject addProof(User user, int serverID, String proof){

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", addproof_tag));
        params.add(new BasicNameValuePair("user", user.getPhoneNumber()));
        params.add(new BasicNameValuePair("id", String.valueOf(serverID)));
        params.add(new BasicNameValuePair("proof", proof));
        
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        // Rückgabe des json Objekts
        return json;
	    }	   	   
	    
	    public JSONObject getUpdated(String phonenumber){

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getupdatedchallenges_tag));
        params.add(new BasicNameValuePair("user", phonenumber));
        
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        // Rückgabe des json Objekts
        return json;
	    }	   	   
	    /** 
	     * Hilfsklasse zum Konvertieren von Integer nach Boolean
	     */
		public boolean convertIntToBoolean(int intValue)	
		{
		return (intValue != 0);
		}	
		
	}
