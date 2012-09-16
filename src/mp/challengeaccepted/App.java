package mp.challengeaccepted;

import android.app.Application;
import android.telephony.TelephonyManager;

public class App extends Application 
{
	private static User user;
	private static String sim;
	private DatabaseOnBoard myDB=null;
	
	@Override
	public void onCreate() 
	{
		setUser(getMyDB().ladeUserProfile());
		//CONTACT UPDATE
		//CHALLENGE UPDATE
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
	
	public boolean checkUser()
	{
		String aktuelleSim=((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).getSimSerialNumber();
		
		if((aktuelleSim.equals(user.getSim()))&&(user.isVerified()==true))
			return true;
		else return false;
	}

	
	public void ContactUpdate()
	{
		
	}


}


