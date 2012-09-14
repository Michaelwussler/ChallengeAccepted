package mp.challengeaccepted.db;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
	 
import android.util.Log;
	 
	public class DBFunctions  {
	 
	    private JSONParser jsonParser;
	 
	    private static String URL = "http://www.challenge.net16.net/"; 
	    
	    private static String anmelden_tag = "anmelden";
	    private static String profilsync_tag = "profilsync";
	    private static String challengesync_tag = "challengesync";
	    private static String challengenew_tag = "challengenew";
	    
	    // Konstructor
	    public DBFunctions(){
	        jsonParser = new JSONParser();
	    }
	 
	    /**
	     * Funktion um einen neues profil (das des USERS) zu erstellen
	     * */
	    public JSONObject anmelden(String vorname, String nachname, String email, String telefonnummer){
	    	// erstellt einen neues profil in der db und gibt dieses als JSON zurück um es in der localDB zu speichern

	        // Building Parameters
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("tag", anmelden_tag));
	        params.add(new BasicNameValuePair("vorname", vorname));
	        params.add(new BasicNameValuePair("nachname", nachname));
	        params.add(new BasicNameValuePair("email", email));
	        params.add(new BasicNameValuePair("telefonnummer", telefonnummer));

	        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	        // Rückgabe des json Objekts
	        return json;
	    }
	    
	    
	    /**
	     * Funktion um die Profile auf der lokalenDB mit denen vom Server zu syncen
	     * */
//	    public Magnetkarte magnetkarteAusgabe(JSONObject json){
//	    	// gibt eine Magnetkarte zurück, welche alle geladenen Kartenpunkte enthält
//
//	    	Magnetkarte ausgabe = new Magnetkarte(); //List der ganzen aufgerufenen Strecken
//
//	    	try {
//	 		
//	    		int anzahlReihen = json.optInt("anzahlReihen");
//	    		
////			    Map<Integer, List<Kartenpunkt>> kpMap = new HashMap<Integer, List<Kartenpunkt>>();					
//	    		
//				for(int i=1; i<=anzahlReihen; i++)	//die einzelnen KP werden aus dem jsonObjekt ausgelesen
//	        	{
//					JSONObject json_kpTemp = json.getJSONObject("laden"+Integer.toString(i)); //ließt die einzelnen Zeilen aus
//				
//					Kartenpunkt kpTemp = new Kartenpunkt(json_kpTemp.optInt("latitude"), json_kpTemp.optInt("longitude"), json_kpTemp.optInt("magneticvertical"), json_kpTemp.optInt("magnetichorizontal"), json_kpTemp.getInt("anzahl")); //erstellt einen neuen KP
//					
//					ausgabe.add(kpTemp);
//				}
////				Log.d("wkMap Größe", Integer.toString(wpMap.size()));
////				Log.d("wkMap nach for-Schleife", wpMap.toString());	
//								
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}    	
//	    	        
//	        // Rückgabe des json Objekts
//	        return ausgabe;
//	    }
//
//	    
//	    /**
//	     * Funktion zum Laden der Magnetkarte zu den gegebenen Koordinaten+Umkreis
//	     *  
//	     * */    
//	    public Magnetkarte magnetkarteLaden(int latitude, int longitude, int umkreis){
//	    
//	    	JSONObject json = magnetkarteAufrufen(latitude, longitude, umkreis);
//	    	Magnetkarte result = magnetkarteAusgabe(json);
//	    	return result;
//	    	
//	    }
//	    
//	    /**
//	     * Funktion für den magnetkarteSpeichern Request an die MYSQL database
//	     * */   
//	    public JSONObject magnetkarteSpeichern(Magnetkarte magnetkartearg){
//	        // Building Parameters
//       	 Log.d("Magnetkarte speichern erfolgreich", "scheint bis hier zu gehen");
//
//	        List<NameValuePair> params = new ArrayList<NameValuePair>();
//	        	params.add(new BasicNameValuePair("tag", magnetkartespeichern_tag));
//	        	params.add(new BasicNameValuePair("anzahlpunkte", Integer.toString(magnetkartearg.getListe().size())));
//	        
//	        	// Parameter der kartenpunkte übergeben
//	        	
//	        	for(int i=0; i<magnetkartearg.getListe().size(); i++)
//	        	{
//	        		Kartenpunkt arg = magnetkartearg.getListe().get(i);
//	        		
//	            	params.add(new BasicNameValuePair("arglatitude"+Integer.toString(i), Integer.toString(arg.getLatitude())));
//	            	params.add(new BasicNameValuePair("arglongitude"+Integer.toString(i), Integer.toString(arg.getLongitude())));
//	            	params.add(new BasicNameValuePair("argmagneticvertical"+Integer.toString(i), Double.toString(arg.getMagneticHorizontal())));
//	            	params.add(new BasicNameValuePair("argmagnetichorizontal"+Integer.toString(i), Double.toString(arg.getMagneticVertical())));
//
//	            	params.add(new BasicNameValuePair("arganzahl"+Integer.toString(i), Integer.toString(arg.getAnzahl())));
//	         	 Log.d("Magnetkarte parametrisieren erfolgreich", Integer.toString(i));
//
//	        	}
//	          	 Log.d("Magnetkarte parametrisieren erfolgreich", "scheint bis hier zu gehen");
//
//	        JSONObject json = jsonParser.getJSONFromUrl(locationURL, params);
//	        // Rückgabe des json Objekts
//	        return json;
//	    }
	    

	    /**
	     * Hilfsklasse zum Konvertieren von Integer nach Boolean
	     */
		public boolean convertIntToBoolean(int intValue)	
		{
		return (intValue != 0);
		}	
		
	}
