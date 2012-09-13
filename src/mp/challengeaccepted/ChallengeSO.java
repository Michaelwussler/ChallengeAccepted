package mp.challengeaccepted;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ChallengeSO extends Activity {
	
	private LinearLayout LLTitle;
	private LinearLayout LLDescription;
	private LinearLayout LLProof;
	private CheckBox checkBoxShare;
	private Button buttonSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_so);
        LLTitle=(LinearLayout)findViewById(R.id.LLTitle);
        LLTitle.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showDialog(1);				
			}
		});
        LLDescription=(LinearLayout)findViewById(R.id.LLDescription);
        LLDescription.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showDialog(2);	
				
			}
		});
        LLProof=(LinearLayout)findViewById(R.id.LLProof);
        LLProof.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showDialog(3);
				
			}
		});
        checkBoxShare=(CheckBox)findViewById(R.id.checkBoxShare);
        
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Challenge temp=new Challenge(getChallengeTitle(), getDescription(), getReceiver(), getEmitter(), getProof(), getChallengeStatus());
				// DIE CHALLENGE VERSENDEN
				// DIE CHALLENGE ABSPEICHERN
				
				
			}

			private Status getChallengeStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			private String getProof() {
				// TODO Auto-generated method stub
				return null;
			}

			private String getEmitter() {
				// TODO Auto-generated method stub
				return null;
			}

			private String getReceiver() {
				// TODO Auto-generated method stub
				return null;
			}

			private String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			private String getChallengeTitle() {
				// TODO Auto-generated method stub
				return null;
			}
		});
        
    
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_challenge_so, menu);
        return true;
    }

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=new Dialog(this);

		if(id==1)
		{
			dialog.setContentView(R.layout.activity_dialog_challenge_edit);
			dialog.setTitle("Edit title");

			final EditText eingabe=(EditText)dialog.findViewById(R.id.editTextEingabe);
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(100);
			eingabe.setFilters(FilterArray);
			
			dialog.setOnDismissListener(new OnDismissListener() {
				
				public void onDismiss(DialogInterface dialog) 
				{
					((TextView)LLTitle.getChildAt(1)).setText(eingabe.getText().toString());
				}
			});
		}
		
		if(id==2)
		{
			dialog.setContentView(R.layout.activity_dialog_challenge_edit);
			final EditText eingabe=(EditText)dialog.findViewById(R.id.editTextEingabe);

			dialog.setTitle("Edit description");
			dialog.setOnDismissListener(new OnDismissListener() {
				
				public void onDismiss(DialogInterface dialog) 
				{
					((TextView)LLDescription.getChildAt(1)).setText(eingabe.getText().toString());
				}
			});
		}
		
		if(id==3)
		{
			dialog.setContentView(R.layout.activity_dialog_challenge_edit);
			final EditText eingabe=(EditText)dialog.findViewById(R.id.editTextEingabe);

			dialog.setTitle("Edit proof");
			dialog.setOnDismissListener(new OnDismissListener() {
				
				public void onDismiss(DialogInterface dialog) 
				{
					((TextView)LLProof.getChildAt(1)).setText(eingabe.getText().toString());
				}
			});
		}
		return dialog;
	}
    
    
    

    
}
