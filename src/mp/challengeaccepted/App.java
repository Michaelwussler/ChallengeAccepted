package mp.challengeaccepted;

import android.app.Application;

public class App extends Application 
{
	private static Profile user;
	private DatabaseOnBoard myDB=null;
	
	@Override
	public void onCreate() 
	{
		ladeUserProfile();
		//USER UPDATE
		//CHALLENGE UPDATE
		super.onCreate();
	}



	/**
	 * @return the user
	 */
	public static Profile getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public static void setUser(Profile user) {
		App.user = user;
	}

	public static void setUserName(String string) {
		// TODO Auto-generated method stub
		user.setName(string);
	}

	public static void setUserPhoneNumber(String string) {
		// TODO Auto-generated method stub
		user.setPhoneNumber(string);
	}

	public static void setUserEmail(String string) {
		// TODO Auto-generated method stub
		user.setEmail(string);
	}
	
	
	public static void ladeUserProfile() {
		if(user==null)
		{user=new Profile();} //NUR VORÜBERGEHEND
		
	}
	
	

	public static void verifyUser() {
		// Hier die FUnktion um den User zu vertifizieren
		user.setVerified(true);
		
	}
	
}


