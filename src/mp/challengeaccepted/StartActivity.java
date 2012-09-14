package mp.challengeaccepted;


import org.json.JSONException;
import org.json.JSONObject;

import mp.challengeaccepted.db.DBFunctions;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.telephony.TelephonyManager;

public class StartActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        String temp=getMyPhoneNumber();
        Log.d("Challenge","accepted");
        if(temp==null) 
        {Log.d("Telefonnumer","Kann nicht gelesen werden");}
        else{ Log.d("Telefonnummer",temp);}
                
        setContentView(R.layout.activity_challenge_so); 
        
	     //TEST     
	      String KEY_SUCCESS = "success";
		  String KEY_ERROR = "error";
		    
	      DBFunctions databasefunctions = new DBFunctions();
	      JSONObject json = databasefunctions.anmelden("Peter", "Raiser", "PRaiser", "015771356010");
	      
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
	    
	      //ENDE TEST
    } 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start, menu);
        return true;
    }

    
    private String getMyPhoneNumber() 
    { 
        return ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)) 
                .getLine1Number(); 
    } 

    
    private String eingabeEigeneTelefonnummer()
    {
    	;
		return null;
    }
    
    
}
