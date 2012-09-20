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
	
	ArrayList<Challenge> neueChallenges=new ArrayList<Challenge>();
	ArrayList<Challenge> akzeptierteChallenges=new ArrayList<Challenge>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        
//
//        Log.d("Start","OnCreate");
//
//        //PROFILE TEST
//        Profile pr = new Profile();
//        pr.setName("Peter Raiser");
//        pr.setEmail("praiser@gmx.de");
//        pr.setPhoneNumber("015771356010");
//        
//        Profile gs = new Profile();
//        gs.setName("GerdSchneider");
//        gs.setEmail("gs@gmx.de");
//        gs.setPhoneNumber("911");
//        
//        Profile mw = new Profile();
//        mw.setName("Michi");
//        mw.setEmail("michi@gmx.de");
//        mw.setPhoneNumber("123123123");
//        
//        ServerDB.isUser(gs);
//        ServerDB.isUser(mw);
//        ServerDB.isUser(pr);
//        
//        DatabaseHandlerUser dbuser = new DatabaseHandlerUser(this);
//        dbuser.addUser(new User());
//        dbuser.getUser();
        
//        DatabaseHandlerProfile dbhelperProfile = new DatabaseHandlerProfile(this);
//        
//        dbhelperProfile.deleteProfile();
//        
//        dbhelperProfile.addProfile(pr);
//        dbhelperProfile.addProfile(gs);
//        dbhelperProfile.addProfile(mw);
//        
//        dbhelperProfile.profilSync(ServerDB.profilSync(dbhelperProfile.getAllUnregistered()));
//        
//        //dbhelperProfile.register(pr);
//        
//        //dbhelperProfile.print("015771356010");
//        //dbhelperProfile.print("911");
//        
//        Log.i("TEst ob isRegistred tut: für 0157...", Boolean.toString(dbhelperProfile.isRegistered(pr)));
//        Log.i("TEst ob isRegistred tut: für 911...", Boolean.toString(dbhelperProfile.isRegistered(gs)));
//      //  dbhelperProfile.register(gs);
//        Log.i("TEst ob isRegistred tut: für 123123123...", Boolean.toString(dbhelperProfile.isRegistered(mw)));
//
//        ArrayList<Profile> registeredProfiles = dbhelperProfile.getAllRegistered();
//       
//        for(Profile n: registeredProfiles){
//        	Log.i("Name des registrierten USERS", n.getName());
//        }
//        
//        ArrayList<Profile> UnregisteredProfiles = dbhelperProfile.getAllUnregistered();
//
//        
//        for(Profile n: UnregisteredProfiles){
//        	Log.i("Name des nichtregistrierten USERS", n.getName());
//        }
//         
        
        setContentView(R.layout.activity_start); 

 
        Log.d("Start","OnCreateStartActivity");
        
        if((((App) getApplication()).getUser().isVerified()==false)||(((App)getApplication()).getUser().getSim().equals(((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber())==false))
        {	
        	showDialog(1); 
        } 
        	

        buttonChallengeSo=(Button) findViewById(R.id.buttonChallengeSO);
        buttonChallengeSo.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),ChoseContact.class));
			}
		});
        buttonMyChallenges=(Button) findViewById(R.id.buttonMyChallenges);
        buttonMyChallenges.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				startActivity(new Intent(getApplicationContext(),ArchivActivity.class));

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
   	   
       final ProfileDialog dialog = new ProfileDialog(this);
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
       
       dialog.setButtonOkay((Button)dialog.findViewById(R.id.buttonOkay));
       dialog.getButtonOkay().setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) 
		{
			User usertemp=new User();
			usertemp.setName(((EditText)dialog.findViewById(R.id.editTextName)).getText().toString());
			usertemp.setPhoneNumber(((EditText)dialog.findViewById(R.id.editTextNumber)).getText().toString());
			usertemp.setEmail(((EditText)dialog.findViewById(R.id.editTextEmail)).getText().toString());
			usertemp.setSim(((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber());
			
			if(((App)getApplication()).verifyUser(usertemp)==true)
			{
				DatabaseHandlerUser dbuser =  new DatabaseHandlerUser(getApplicationContext());
				
				dbuser.addUser(usertemp);
				((App)getApplication()).setUser(usertemp);
				((App)getApplication()).ladeProfile();
				dialog.dismiss();
				
				DatabaseHandlerProfile dbprofile = new DatabaseHandlerProfile(getApplicationContext());
				Log.i("Name", dbprofile.getAllUnregistered().get(2).getName());
				Log.i("telefon", dbprofile.getAllUnregistered().get(2).getPhoneNumber());
				Log.i("Name", dbprofile.getAllUnregistered().get(7).getName());
				Log.i("telefon", dbprofile.getAllUnregistered().get(7).getPhoneNumber());
				Log.i("Name", dbprofile.getAllUnregistered().get(24).getName());
				Log.i("telefon", dbprofile.getAllUnregistered().get(24).getPhoneNumber());
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Vertification failed\nPlease correct your setting", Toast.LENGTH_LONG).show();
			}
		}
	});


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
