package mp.challengeaccepted;

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
        if(temp.length()==0) 
        {Log.d("Telefonnumer","Kann nicht gelesen werden");}
        else{ Log.d("Telefonnummer",temp);}
        setContentView(R.layout.activity_challenge_so); 
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
