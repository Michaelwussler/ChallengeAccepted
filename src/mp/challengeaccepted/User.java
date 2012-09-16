package mp.challengeaccepted;

import android.content.Context;
import android.telephony.TelephonyManager;

public class User extends Profile 
{

	private String sim;
	
	public void setUserName(String string) {
		// TODO Auto-generated method stub
		setName(string);
	}

	public void setUserPhoneNumber(String string) {
		// TODO Auto-generated method stub
		setPhoneNumber(string);
	}

	public void setUserEmail(String string) {
		// TODO Auto-generated method stub
		setEmail(string);
	}
	
	
	

	public void verifyUser() {
		// Hier die FUnktion um den User zu vertifizieren
		setVerified(true);
		
	}

	/**
	 * @return the sim
	 */
	public String getSim() {
		return sim;
	}

	/**
	 * @param sim the sim to set
	 */
	public void setSim(String sim) {
		this.sim = sim;
	}
	




	

}
