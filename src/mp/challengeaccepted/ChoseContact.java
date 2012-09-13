package mp.challengeaccepted;

import java.util.List;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class ChoseContact extends Activity {
	private Button buttonWriteChallenge;
	private String[] receiver;
	private ContentResolver cr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_contact);
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
    
    
    /* KOPIERTES ZEUGS ZUR AUSLESE
    private List readContacts ()
    {
    	List list=new List();
    }
    if(list.isEmpty()){
        cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, 
                              new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER},  // Diese Spalten sollen ausgelesen werden
                              ContactsContract.Contacts.IN_VISIBLE_GROUP + " = 1", 
                              null, 
                              ContactsContract.Contacts.DISPLAY_NAME);
        
        if(cur.getCount() > 0){
        while(cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        
                if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{Phone.NUMBER, Phone.TYPE},ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
                    while(pCur.moveToNext()){
                        if(pCur.getInt(pCur.getColumnIndex(Phone.TYPE)) == Phone.TYPE_MOBILE){
                            list.add(new Kontakt(name, pCur.getString(pCur.getColumnIndex(Phone.NUMBER))));
                            break;
                        }
                    }
                    pCur.close();
            }
            }
    }
    
    */
        

    
}
