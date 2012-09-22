package mp.challengeaccepted;




import java.io.IOException;
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
	private static TextView Title = null;
	private static TextView Sender = null;
	private Handler mMainHandler;
    private int challengeIndex=0;
	
	Button buttonChallengeSo;
	Button buttonMyChallenges;
	Button buttonChannels;
	
	
	ArrayList<Challenge> neueChallenges=new ArrayList<Challenge>();
	ArrayList<Challenge> akzeptierteChallenges=new ArrayList<Challenge>();
	private ImageView buttonLinks;
	private ArrayList<Challenge> challenges;
	private ImageView buttonRechts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	challenges=((App)getApplication()).getChallengeForMe();

        setContentView(R.layout.activity_start); 
        Title=(TextView)findViewById(R.id.textViewTitle);
        Sender=(TextView)findViewById(R.id.textViewChallenger);
        
        
        
        if(challenges.size()>0)
        {
        	Title.setText(challenges.get(0).getTitle());
        	Sender.setText(challenges.get(0).getSender().getName());
        	//Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
        }
        
        
        
        buttonLinks=(ImageView)findViewById(R.id.imageViewLinks);
        buttonRechts=(ImageView)findViewById(R.id.imageViewRechts);
        
        buttonLinks.setOnClickListener(new OnClickListener() {
    		
    		public void onClick(View v) {
    			if(challenges.size()!=0)
    			{
    			if(challengeIndex==0)
    				{challengeIndex=challenges.size()-1;}
    			else{challengeIndex-=1;}
    			Title.setText(challenges.get(challengeIndex).getTitle());
            //	Description.setText(challenges.get(challengeIndex).getDescription());
            //	Proof.setText(challenges.get(challengeIndex).getProof());
            //	Receiver.setText(challenges.get(challengeIndex).getReceiver().getName());
            	Sender.setText(challenges.get(challengeIndex).getSender().getName());
            //	Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
    		}}
    	});
           
           
           buttonRechts.setOnClickListener(new OnClickListener() {
       		
    		public void onClick(View v) {
    			if(challenges.size()!=0)
    			{
    			if(challengeIndex==challenges.size()-1)
    				{challengeIndex=0;}
    			else{challengeIndex+=1;}
    			Title.setText(challenges.get(challengeIndex).getTitle());
            	//Description.setText(challenges.get(challengeIndex).getDescription());
            	//Proof.setText(challenges.get(challengeIndex).getProof());
            	//Receiver.setText(challenges.get(challengeIndex).getReceiver().getName());
            	Sender.setText(challenges.get(challengeIndex).getSender().getName());
            	//Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
    		}
    		}
    	});
           
        
        Log.d("Start","OnCreateStartActivity");

        if((((App) getApplication()).getUser().isVerified()==false||((App)getApplication()).getUser().getSim().equals(((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber())==false))
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

        buttonLinks=(ImageView)findViewById(R.id.imageViewLinks);
       
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
				Log.i("Hier der Path", dbuser.getPath());
				dbuser.addUser(usertemp);
				((App)getApplication()).setUser(usertemp);

				Thread t1=new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						((App)getApplication()).setProfiles(((App)getApplication()).ladeProfile());
						((App)getApplication()).setChallenges(((App)getApplication()).ladeChallenges()); // muss auch an einen Service ausgelagert werden

					}
				});
				t1.run();
				
				dialog.dismiss();

			}
			else
			{
				Toast.makeText(getApplicationContext(), "Vertification failed\nPlease correct your setting", Toast.LENGTH_LONG).show();
			}
			
			 try {
					((App)getApplication()).backupDatabase();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
    
    public void redraw()
    {
    	challenges=((App)getApplication()).getChallengeForMe();
    	if(challenges.size()>0)
        {
        	Title.setText(challenges.get(0).getTitle());
        	//Description.setText(challenges.get(0).getDescription());
        	//Proof.setText(challenges.get(0).getProof());
        	//Receiver.setText(challenges.get(0).getReceiver().getName());
        	Sender.setText(challenges.get(0).getSender().getName());
        }
    }
    
    
}
