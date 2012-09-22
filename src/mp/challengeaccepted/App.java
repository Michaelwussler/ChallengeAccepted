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
import android.widget.Toast;

public class App extends Application 
{

	private static User user=new User();
	private ArrayList<Profile> profiles=new ArrayList<Profile>();
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
			
			Thread t1=new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					setProfiles(ladeProfile());
					challenges=ladeChallenges(); // muss auch an einen Service ausgelagert werden

				}
			});
			t1.run();
		}

		super.onCreate();
	}

	ArrayList<Challenge> ladeChallenges() {
		return challenges;
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Profile> ladeProfile() {		
	
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
		return arg;
    
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

	/**
	 * @return the profiles
	 */
	public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}

	public void editChallenge(Challenge temp) {
		// TASK PETER DIESE FUNKTION WIRD IMMER AUFGERUFEN WENN EINE CHALLENGE IN IRGENDEINER ART BEARBEITET WIRD, ZUM BEISPIEL BEIM ERSTELLEN; ANNEHMEN; ETC
		// HIER MUSS DIE CHALLENGE IN DER LOKALEN DATENBANK UND IN DER SERVERDATENBANK ERSTELLT BZW AKTUALISIERT WERDEN
	}
	
	public ArrayList<Challenge> getChallengeWithStatus(int status)
	{
		//TASK PETER hier brauche ich eine FUnktion, die mir alle Challenges des entsprechenden status zurückgibt
		ArrayList<Challenge> temp=new ArrayList<Challenge>();
		if(status==Challenge.New)
		{temp.add(new Challenge("New", "das allerneueste", (Profile)getUser(), getProfiles().get(3), "der Beweis", Challenge.New));
		return temp;
		}
		if(status==Challenge.Accepted)
		{temp.add(new Challenge("Accepted", "das akzeptierte", (Profile)getUser(), getProfiles().get(8), "der Beweis", Challenge.Accepted));
		return temp;
		}
		if(status==Challenge.Denied)
		{temp.add(new Challenge("Abgelehnt", "das abgelehnte", (Profile)getUser(), getProfiles().get(3), "der Beweis", Challenge.Denied));
		return temp;
		}
		if(status==Challenge.ProofConfirmation)
		{temp.add(new Challenge("Beweis geschickt", "ich will das bestätigt haben", (Profile)getUser(), getProfiles().get(12), "der Beweis", Challenge.ProofConfirmation));
		return temp;
		}
		if(status==Challenge.ProofDenied)
		{temp.add(new Challenge("Beweis abgelehnt", "der abgelehnte Beweis", (Profile)getUser(), getProfiles().get(15), "der Beweis", Challenge.ProofDenied));
		return temp;
		}
		if(status==Challenge.Completed)
		{temp.add(new Challenge("Completed", "Mission complet, extrem viel Text, bhadbdhdbajhdbjkhawbdjhbawbjhbsjh<dbfklhwefhbjhawefgjklawefgvcfhawefljkshaefljkhh jhjh bhb jhbehwjhafb hbeafbjhkawbk bkjhab jhebawfhbjha bjhaw jhba bkjefawbkfjhbawkefhkjaw afbjhew", (Profile)getUser(), getProfiles().get(1), "der Beweis", Challenge.Completed));
		return temp;
		}
		
		if(status==10)
		{		
			temp.add(new Challenge("Beweis abgelehnt", "der abgelehnte Beweis", (Profile)getUser(), getProfiles().get(15), "der Beweis", Challenge.ProofDenied));
			temp.add(new Challenge("Beweis geschickt", "ich will das bestätigt haben", (Profile)getUser(), getProfiles().get(12), "der Beweis", Challenge.ProofConfirmation));
			temp.add(new Challenge("Accepted", "das akzeptierte", (Profile)getUser(), getProfiles().get(8), "der Beweis", Challenge.Accepted));
		}
		
		
		return temp;
		
	}

	public ArrayList<Challenge> getChallengeForMe() {
		// TASK PETER HIER ALLE CHALLENGES ZURÜCKGEBEN, DIE MICH ALS RECEIVER HABEN
		ArrayList<Challenge> temp=new ArrayList<Challenge>();
		
		temp.add(new Challenge("New", "ich bin Receiver", (Profile)getUser(), getProfiles().get(3), "der Beweis", Challenge.New));
		temp.add(new Challenge("Noch besser", "ich bin Receiver", (Profile)getUser(), getProfiles().get(3), "der Beweis", Challenge.New));
		temp.add(new Challenge("Voll gut", "ich bin Receiver", (Profile)getUser(), getProfiles().get(3), "der Beweis", Challenge.New));
		temp.add(new Challenge("Dummer Scheiß", "ich bin Receiver", (Profile)getUser(), getProfiles().get(3), "der Beweis", Challenge.New));

		return temp;
		
	}

	public ArrayList<Profile> getProfilesWithAccount() {
	//TASK PETER HIER NUR DIE PROFILE MIT ACCOUNT ZURÜCKGEBEN
		return getProfiles();
		
	}
	



}


