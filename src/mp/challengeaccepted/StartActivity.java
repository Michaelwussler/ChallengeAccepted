package mp.challengeaccepted;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.NavUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;

public class StartActivity extends Activity 
{
	Button buttonChallengeSo;
	Button buttonMyChallenges;
	Button buttonChannels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.ladeUserProfile();
        if(App.getUser().isVerified()==false)
        {	
        	showDialog(1);
        }   
        setContentView(R.layout.activity_start); 
        buttonChallengeSo=(Button) findViewById(R.id.buttonChallengeSO);
        buttonChallengeSo.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),ChoseContact.class));
			}
		});
        buttonMyChallenges=(Button) findViewById(R.id.buttonMyChallenges);
        buttonMyChallenges.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//showDialog(1);
				
			}
		});
        buttonChannels=(Button) findViewById(R.id.buttonChannels);
    } 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start, menu);
        return true;
    }

    
 

    
    
  //@Override
    protected Dialog onCreateDialog(int id) 
    {
    	
        ProfileDialog dialog = new ProfileDialog(this);
  	   dialog.setContentView(R.layout.activity_profilesetting);
  	   dialog.setTitle("Profile settings"); 

  	   dialog.setYourname((EditText)dialog.findViewById(R.id.editTextName));
 		dialog.setYournumber((EditText)dialog.findViewById(R.id.editTextNumber));
 	    dialog.setYourmail((EditText)dialog.findViewById(R.id.editTextEmail));
   	   String temp=((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getLine1Number();
       if(temp!=null)
       {
    	   if(temp.length()!=0)
    	   {
    		   dialog.getYournumber().setText(temp);
    	   }
       }
       return dialog;
    	

    }
    
   

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
    
    
    
    
}
