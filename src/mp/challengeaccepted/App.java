package mp.challengeaccepted;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;

public class App extends Application 
{
	private User user;
	private DatabaseOnBoard myDB;//=new DatabaseOnBoard();
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
		Log.d("App","onCreate");
		user=ladeUserProfil();
		if((user.isVerified()==true)&&(user.getSim().equals(((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber())))
		{	
			contacts=ladeProfile();
			challenges=ladeChallenges();
		}

		super.onCreate();
	}

	private ArrayList<Challenge> ladeChallenges() {
		return challenges;
		// TODO Auto-generated method stub
		
	}

	private ArrayList<Profile> ladeProfile() {
		return contacts;
		// TODO Auto-generated method stub
		
	}

	private User ladeUserProfil() {  
		// TASK PETER HIER NE ORDENTLICH FUNKTION ZUR AUSLESE DER USER_DATEN DER DATENBANK
		user=new User("Michael Wuﬂler","015156150728","michaelwussler@freenet.de",true,((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber());
		return user;
	}
  
	/**
	 * @return the myDB
	 */
	public DatabaseOnBoard getMyDB() {
		return myDB;
	}

	/**
	 * @param myDB the myDB to set
	 */
	public void setMyDB(DatabaseOnBoard myDB) {
		this.myDB = myDB;
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
	
	public static boolean checkUser()
	{
	//	String aktuelleSim=((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber();
	//	myDB.speichern(getUser());
	//	myDB.ladeUserProfile();	
	//	if((aktuelleSim.equals(user.getSim()))&&(user.isVerified()==true))
			{

			return true;
			}
	//	else {
	//		return false;
	//	}
	}

	public boolean verifyUser(User usertemp) {
		// TODO HIER DIE VERTIFIKATION DES USERS
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


