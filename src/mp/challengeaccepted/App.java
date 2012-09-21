package mp.challengeaccepted;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.NoRouteToHostException;
import java.util.ArrayList;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
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

		user = ladeUserProfil();
		
		
		//Hiermit wird die Profildatenbank geleert!
//		DatabaseHandlerProfile dbprofile = new DatabaseHandlerProfile(getApplicationContext());
//		dbprofile.deleteProfile();
		
		if((user.isVerified()==true)&&(user.getSim().equals(((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber())))
		{	
			
			ladeProfile(); //muss an einen Service ausgelagert werden
			challenges=ladeChallenges(); // muss auch an einen Service ausgelagert werden
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

        if(cur.getCount() > 0)
        {
        while(cur.moveToNext()) {
        	Profile tmp = new Profile();
        	
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            tmp.setName(name);
            if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{Phone.NUMBER, Phone.TYPE},ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
                    while(pCur.moveToNext())
                    {
                        if(pCur.getInt(pCur.getColumnIndex(Phone.TYPE)) == Phone.TYPE_MOBILE){
                          tmp.setPhoneNumber(normPhoneNumber(pCur.getString(pCur.getColumnIndex(Phone.NUMBER))));
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
		User usertmp = new User();
		DatabaseHandlerUser dbuser = new DatabaseHandlerUser(getApplicationContext());
		usertmp = dbuser.getUser();
		return usertmp;
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

	//normiert die eingegeben Telefonnummer und gibt diese ZurŸck
	public String normPhoneNumber(String nichtNormierteNummer) {

			
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber standardtisierteNummer = phoneUtil.parse(nichtNormierteNummer, "DE");
			String a = standardtisierteNummer.toString().substring(14);
			String b = a.substring(0,a.indexOf(" "));
			String c = a.substring(a.lastIndexOf(" ")+1);
			String d = b+c;
			String normierteNummer = d;
	//		Log.i("formatierte NUmmer", normierteNummer);
			return normierteNummer;
		} catch (NumberParseException e) {
		  System.err.println("NumberParseException was thrown: " + e.toString());
		  return "0000";
		}
		

	}
	

// funktion um die db auf die sim karte zu schreiben
    public static void backupDatabase() throws IOException {
    //Open your local db as the input stream
    String inFileName = "/data/data/mp.challengeaccepted/databases/challange";
    File dbFile = new File(inFileName);
    FileInputStream fis = new FileInputStream(dbFile);

    String outFileName = Environment.getExternalStorageDirectory()+"/challenge.sqlite";
    //Open the empty db as the output stream
    OutputStream output = new FileOutputStream(outFileName);
    //transfer bytes from the inputfile to the outputfile
    byte[] buffer = new byte[1024];
    int length;
    while ((length = fis.read(buffer))>0){
        output.write(buffer, 0, length);
    }
    //Close the streams
    output.flush();
    output.close();
    fis.close();
}
	
	
	



}


