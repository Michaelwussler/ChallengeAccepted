package mp.challengeaccepted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.support.v4.app.NavUtils;


    
    public class ChoseContact extends Activity { 
    	 
    	private ArrayList<Map<String, String>> mPeopleList; 
    	private SimpleAdapter mAdapter; 
    	private AutoCompleteTextView mTxtPhoneNo; 
    	 
    	@Override 
    	public void onCreate(Bundle savedInstanceState) { 
    	    super.onCreate(savedInstanceState); 
    	    setContentView(R.layout.activity_chose_contact); 
    	    
    	    mPeopleList = new ArrayList<Map<String, String>>(); 
    	    PopulatePeopleList(); 
    	    
    	    mTxtPhoneNo = (AutoCompleteTextView) findViewById(R.id.autoCompleteContact); 
    	    
    	    mAdapter = new SimpleAdapter(this, mPeopleList, R.layout.autotext, 
    	            new String[] { "Name", "Phone", "Type" }, new int[] { 
    	                    R.id.ccontName, R.id.ccontNo, R.id.ccontType }); 
    	    
    	    mTxtPhoneNo.setAdapter(mAdapter); 
    	    mTxtPhoneNo.setOnItemClickListener(new OnItemClickListener() 
    	    {

				public void onItemClick(AdapterView<?> av, View arg1,
						int index, long arg3) {
				
			    		Log.d("OnItemClick","jetzt");
			    	    Map<String, String> map = (Map<String, String>) av.getItemAtPosition(index); 
			    	
			    	    
			    	    
			       	 Log.d("Map keys", String.valueOf(map.get("Name")));       
			       	 		//Hier der INTENT FÜR DIE NÄCHSTE SEITE
			    	 Profile temp=new Profile();
			    	 temp.setName(map.get("Name"));
			    	 temp.setPhoneNumber(map.get("phone"));
			    	 contactclicked(temp);
			       	 mTxtPhoneNo.setText(map.get("Name")); 					
				}

			
				

    	    	
			});

    	   
    	    
            
            LinearLayout layoutScroll=(LinearLayout)findViewById(R.id.LayoutScrollView);
    	    ArrayList<Profile> contacts=((App)getApplication()).loadContacts();
    	    for(final Profile n:contacts)
    	    {
    	    	 Button temp=new Button(this);
    	    	    temp.setText(n.getName());
    	    	    temp.setOnClickListener(new OnClickListener() {
						
						public void onClick(View arg0) {
							contactclicked(n);
						}
					});
    	            layoutScroll.addView(temp);
    	    }
            
    	 
    	} 
    	 
    	public void PopulatePeopleList() { 
    	    mPeopleList.clear(); 
    	    Cursor people = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null); 
    	    while (people.moveToNext()) { 
    	        String contactName = people.getString(people 
    	                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); 
    	        String contactId = people.getString(people 
    	                .getColumnIndex(ContactsContract.Contacts._ID)); 
    	        String hasPhone = people 
    	                .getString(people 
    	                        .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
    	 
    	        if ((Integer.parseInt(hasPhone) > 0)){ 
    	            // You know have the number so now query it like this 
    	            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null); 
    	            while (phones.moveToNext()){ 
    	                //store numbers and display a dialog letting the user select which. 
    	                String phoneNumber = phones.getString( 
    	                phones.getColumnIndex( 
    	                ContactsContract.CommonDataKinds.Phone.NUMBER)); 
    	                String numberType = phones.getString(phones.getColumnIndex( 
    	                ContactsContract.CommonDataKinds.Phone.TYPE)); 
    	                Map<String, String> NamePhoneType = new HashMap<String, String>(); 
    	                NamePhoneType.put("Name", contactName); 
    	                NamePhoneType.put("Phone", phoneNumber); 
    	                if(numberType.equals("0")) 
    	                    NamePhoneType.put("Type", "Work"); 
    	                    else 
    	                    if(numberType.equals("1")) 
    	                    NamePhoneType.put("Type", "Home"); 
    	                    else if(numberType.equals("2")) 
    	                    NamePhoneType.put("Type",  "Mobile"); 
    	                    else 
    	                    NamePhoneType.put("Type", "Other"); 
    	                    //Then add this map to the list. 
    	                    mPeopleList.add(NamePhoneType); 
    	            } 
    	            phones.close(); 
    	        } 
    	    } 
    	    people.close(); 
    	    startManagingCursor(people); 
    	} 
    	 
    	public void onItemClick(AdapterView<?> av, View v, int index, long arg){ 
    		Log.d("OnItemClick","jetzt");
    	    Map<String, String> map = (Map<String, String>) av.getItemAtPosition(index); 
    	//    Iterator<String> myVeryOwnIterator = map.keySet().iterator(); 
    	//    while(myVeryOwnIterator.hasNext()) { 
    	//        String key=(String)myVeryOwnIterator.next(); 
    	//        String temp=(String)map.get(key); 
    	//        String value=temp.substring(temp.indexOf("Phone="));
    	 Log.d("Map keys", String.valueOf(map.keySet()));       
    	      //  mTxtPhoneNo.setText(value); 
    	    } 
    	
    	 
    	 

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//7//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
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
    
    
    
    private void contactclicked(Profile profile)
    {
    	Log.d("Name",profile.getName());
    	((App)getApplication()).getErstellteChallenge().setReceiver(profile);
		Intent intentChallengeSO = new Intent(getApplicationContext(),ChallengeSO.class);
		startActivity(intentChallengeSO);
    }
}
        

    

