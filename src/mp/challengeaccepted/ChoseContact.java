package mp.challengeaccepted;

import java.util.List;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class ChoseContact extends Activity {
	private Button buttonWriteChallenge;
	private String[] receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_contact);
        readContacts();
        buttonWriteChallenge=(Button) findViewById(R.id.buttonWriteChallenge);
        buttonWriteChallenge.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intentChallengeSO = new Intent(getApplicationContext(),ChallengeSO.class);
        		intentChallengeSO.putExtra("Receiver",receiver);
				startActivity(intentChallengeSO);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chose_contact, menu);
        return true;
    }
    

    private void readContacts()
    {
/*
    {
    	List list=new List();
    }
    */
    //if(list.isEmpty()){
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER}, ContactsContract.Contacts.IN_VISIBLE_GROUP + " = 1", null, ContactsContract.Contacts.DISPLAY_NAME);
        Log.d("AnzahKontakte",String.valueOf(cur.getCount()));
        if(cur.getCount() > 0)
        {
        while(cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.d("Name",name);
            if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{Phone.NUMBER, Phone.TYPE},ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
                    while(pCur.moveToNext())
                    {
                        if(pCur.getInt(pCur.getColumnIndex(Phone.TYPE)) == Phone.TYPE_MOBILE){
                          Log.d("PhoneNumber",pCur.getString(pCur.getColumnIndex(Phone.NUMBER)));
                            break;
                        }
                    }
                    pCur.close();
            }
        }
    }
    
    }
}
        

    

