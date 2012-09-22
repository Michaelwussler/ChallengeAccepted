package mp.challengeaccepted;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ArchivCompletedActivity extends Activity {

    private ArrayList<Challenge> challenges;
	private TextView Title;
	private TextView Proof;
	private TextView Receiver;
	private TextView Description;
	private TextView Sender;
	private TextView Anzahl;
	private ImageView buttonLinks;
	private ImageView buttonRechts;
	private int challengeIndex=0;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archiv_completed);
        challenges=((App)getApplication()).getChallengeWithStatus(Challenge.Completed); //Akzeptierte wie als auch die im Proof-Mode
       Title=(TextView)findViewById(R.id.textViewTitle);
       Description=(TextView)findViewById(R.id.textViewDescription);
       Proof=(TextView)findViewById(R.id.textViewProof);
       Receiver=(TextView)findViewById(R.id.textViewReceiver);
       Sender=(TextView)findViewById(R.id.TextViewSender);
       Anzahl=(TextView)findViewById(R.id.textViewAnzahl);
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
           	Description.setText(challenges.get(challengeIndex).getDescription());
           	Proof.setText(challenges.get(challengeIndex).getProof());
           	Receiver.setText(challenges.get(challengeIndex).getReceiver().getName());
           	Sender.setText(challenges.get(challengeIndex).getSender().getName());
           	Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
   			}
   			
   		}
   	});
          
          
          buttonRechts.setOnClickListener(new OnClickListener() {
      		
   		public void onClick(View v) {
   			if(challenges.size()!=0)
   			{
   			if(challengeIndex==challenges.size()-1)
   				{challengeIndex=0;}
   			else{challengeIndex+=1;}
   			Title.setText(challenges.get(challengeIndex).getTitle());
           	Description.setText(challenges.get(challengeIndex).getDescription());
           	Proof.setText(challenges.get(challengeIndex).getProof());
           	Receiver.setText(challenges.get(challengeIndex).getReceiver().getName());
           	Sender.setText(challenges.get(challengeIndex).getSender().getName());
           	Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
   			}
   		}
   	});
          
        if(challenges.size()>0)
        {
        	Title.setText(challenges.get(challengeIndex).getTitle());
        	Description.setText(challenges.get(challengeIndex).getDescription());
        	Proof.setText(challenges.get(challengeIndex).getProof());
        	Receiver.setText(challenges.get(challengeIndex).getReceiver().getName());
        	Sender.setText(challenges.get(challengeIndex).getSender().getName());
        	Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_archiv_completed, menu);
        return true;
    }
    
    public void redraw()
    {
    	challenges=((App)getApplication()).getChallengeWithStatus(Challenge.Completed);
    	if(challenges.size()>0)
        {
        	Title.setText(challenges.get(0).getTitle());
        	Description.setText(challenges.get(0).getDescription());
        	Proof.setText(challenges.get(0).getProof());
        	Receiver.setText(challenges.get(0).getReceiver().getName());
        	Sender.setText(challenges.get(0).getSender().getName());
        	Anzahl.setText(String.valueOf(challengeIndex+1)+"/"+String.valueOf(challenges.size()));
        }
    }

    
}
