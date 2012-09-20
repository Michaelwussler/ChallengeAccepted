package mp.challengeaccepted;

import java.util.ArrayList;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.TelephonyManager;
import android.util.Log;

public class App extends Application 
{

	private static User user=new User();
	private ArrayList<Profile> contacts=new ArrayList<Profile>();
	private ArrayList<Challenge> challenges=new ArrayList<Challenge>();
	private Challenge erstellteChallenge=new Challenge();

	
	public ArrayList<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(ArrayList<Challenge> challenges) {
		this.challenges = challenges;
	}

	@Override
	public void onCreate() 
	{

		DatabaseHandlerUser dbuser = new DatabaseHandlerUser(getApplicationContext());
		Log.d("App","onCreate");
		
		user = dbuser.getUser();
		if((user.isVerified()==true)&&(user.getSim().equals(((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber())))
		{	
			ladeProfile();
			challenges=ladeChallenges();
		}

		super.onCreate();
	}

	private ArrayList<Challenge> ladeChallenges() {
		return challenges;
		// TODO Auto-generated method stub
		
	}

	public void ladeProfile() {		
	
		ArrayList<Profile> arg = new ArrayList<Profile>();
		
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER}, ContactsContract.Contacts.IN_VISIBLE_GROUP + " = 1", null, ContactsContract.Contacts.DISPLAY_NAME);
        Log.d("AnzahKontakte",String.valueOf(cur.getCount()));
        if(cur.getCount() > 0)
        {
        while(cur.moveToNext()) {
        	Profile tmp = new Profile();
        	
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.d("Name",name);
            tmp.setName(name);
            if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{Phone.NUMBER, Phone.TYPE},ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
                    while(pCur.moveToNext())
                    {
                        if(pCur.getInt(pCur.getColumnIndex(Phone.TYPE)) == Phone.TYPE_MOBILE){
                          Log.d("PhoneNumber",pCur.getString(pCur.getColumnIndex(Phone.NUMBER)));
                          tmp.setPhoneNumber(pCur.getString(pCur.getColumnIndex(Phone.NUMBER)));
                            break;
                        }
                    }
                    pCur.close();
            }
            arg.add(tmp);
        }
        DatabaseHandlerProfile dbp = new DatabaseHandlerProfile(getApplicationContext());
        
        for(Profile n:arg){ 
        	dbp.addProfile(n);
        	}
    }
    
    }
		
	

	private User ladeUserProfil() {  
		// TASK PETER HIER NE ORDENTLICH FUNKTION ZUR AUSLESE DER USER_DATEN DER DATENBANK
		user=new User();//"Michael Wuﬂler","015156150728","michaelwussler@freenet.de",true,((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber());
		return user;
	}
  

	/**
	 * @return the user
	 */
	public  User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	

	public boolean verifyUser(User usertemp) {
		usertemp.setVerified(true);	
		return true;
	}

	public ArrayList<Profile> loadContacts() {
		ArrayList<Profile> ausgabe=new ArrayList<Profile>();
		ausgabe.add(new Profile("Peter"));
		ausgabe.add(new Profile("Michael"));
		ausgabe.add(new Profile("Anna"));
		ausgabe.add(new Profile("Karina"));
		ausgabe.add(new Profile("Peter"));
		ausgabe.add(new Profile("Michael"));
		ausgabe.add(new Profile("Anna"));
		ausgabe.add(new Profile("Karina"));
		ausgabe.add(new Profile("Peter"));
		ausgabe.add(new Profile("Michael"));
		ausgabe.add(new Profile("Anna"));
		ausgabe.add(new Profile("Karina"));
		ausgabe.add(new Profile("Peter"));
		ausgabe.add(new Profile("Michael"));
		ausgabe.add(new Profile("Anna"));
		ausgabe.add(new Profile("Karina"));
		
		return ausgabe;
	}

	/**
	 * @return the erstellteChallenge
	 */
	public Challenge getErstellteChallenge() {
		return erstellteChallenge;
	}

	/**
	 * @param erstellteChallenge the erstellteChallenge to set
	 */
	public void setErstellteChallenge(Challenge erstellteChallenge) {
		this.erstellteChallenge = erstellteChallenge;
	}




	
	
	



}


