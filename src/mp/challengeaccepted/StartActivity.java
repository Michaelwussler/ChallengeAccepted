package mp.challengeaccepted;




import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import org.json.JSONException;
import org.json.JSONObject;
import mp.challengeaccepted.db.DBFunctions;
import mp.challengeaccepted.db.ServerDB;
import mp.challengeaccepted.ChildThread;


public class StartActivity extends Activity 
{
	private static final String TAG = "ThreadMessaging";
	private Handler mMainHandler;
    
	Button buttonChallengeSo;
	Button buttonMyChallenges;
	Button buttonChannels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        
        
        
        
        App.ladeUserProfile();
        if(App.getUser().isVerified()==false)
=======
        Log.d("Start","OnCreate");
        
        if(((App) getApplication()).getUser().isVerified()==false)
>>>>>>> origin/Michael
        {	
        	
        	ArrayList<Profile> testlist = new ArrayList<Profile>();
        	testlist.add(new Profile("01"));
        	testlist.add(new Profile("02"));
        	testlist.add(new Profile("04"));
        	testlist.add(new Profile("1231"));
       
        	
        	ServerDB.profilSync(testlist);

        		Log.d("ServerDB test", "User TEL wird ausgelesen"+ testlist.get(0).getPhoneNumber());    		
        		Log.d("ServerDB test", "User ID wird ausgelesen"+ testlist.get(0).getId());    		

        		Log.d("ServerDB test", "User TEL wird ausgelesen"+ testlist.get(1).getPhoneNumber());    		
        		Log.d("ServerDB test", "User ID wird ausgelesen"+ testlist.get(1).getId()); 
        		
        		Log.d("ServerDB test", "User TEL wird ausgelesen"+ testlist.get(2).getPhoneNumber());    		
        		Log.d("ServerDB test", "User ID wird ausgelesen"+ testlist.get(2).getId()); 
        		
        		Log.d("ServerDB test", "User TEL wird ausgelesen"+ testlist.get(3).getPhoneNumber());    		
        		Log.d("ServerDB test", "User ID wird ausgelesen"+ testlist.get(3).getId()); 
			     	
    		App.getUser().setVerified(true);
    		
    		
   
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
<<<<<<< HEAD
				showDialog(1);
=======
				
>>>>>>> origin/Michael
				
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
   	   Log.d("YourNameCREATE DIALOG","created");
        ProfileDialog dialog = new ProfileDialog(this);
  	   dialog.setContentView(R.layout.activity_profilesetting);
  	   dialog.setTitle("Profile settings"); 
  	   dialog.setYourname(((EditText)dialog.findViewById(R.id.editTextName)));
  	   Log.d("YourName",String.valueOf(dialog.getYourname()));
  	   dialog.setYournumber(((EditText)dialog.findViewById(R.id.editTextNumber)));
  	dialog.setYourmail(((EditText)dialog.findViewById(R.id.editTextEmail)));

  	   String temp=((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
       if(temp!=null)
       {
    	   if(temp.length()!=0)
    	   {
    		   ((EditText)dialog.findViewById(R.id.editTextNumber)).setText(temp);
    	   }
       }
       return dialog;
    	 

    }
    //ZU Test2
//    @Override
//    protected void onDestroy() {
//
//        Log.i(TAG, "Stop looping the child thread's message queue");
//
//        /*
//         * Remember to stop the looper
//         */
//        ChildThread.mChildHandler.getLooper().quit();
//
//        super.onDestroy();
//    }
   

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
    
   
    
    
}
