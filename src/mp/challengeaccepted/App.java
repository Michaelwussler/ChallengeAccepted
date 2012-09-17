package mp.challengeaccepted;

import android.app.Application;
import android.telephony.TelephonyManager;
import android.util.Log;

public class App extends Application 
{
	private static User user=new User();
	private static String sim;
	private static DatabaseOnBoard myDB=new DatabaseOnBoard();
	
	@Override
	public void onCreate() 
	{
		Log.d("App","OnCreate");
		//setUser(getMyDB().ladeUserProfile());
		//CONTACT UPDATE
		//CHALLENGE SYNCRONISATION
		super.onCreate();
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
	public static User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public static void setUser(User user) {
		App.user = user;
	}
	
	public static boolean checkUser()
	{
	//	String aktuelleSim=((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber();
		Log.d("CheckUser",String.valueOf(getUser().getName()));
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

	
	public void ContactUpdate()
	{
		
	}


}


