package mp.challengeaccepted;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class User extends Profile 
{

	private String sim = "leer";
	
	public User()
	{
		setId(17);
		setName("Michael");
		setPhoneNumber("015156150728");
		setEmail("michaelwussler@freenet.de");
	}
	
	public User(String name, String email, String telefonnummer)
	{
		this.setName(name);
		this.setEmail(email);
		this.setPhoneNumber(telefonnummer);
		this.setId(17);
	}
	
	
	public User(String name, String phoneNumber, String email, boolean verified, String sim)
	{
		setName(name);
		setPhoneNumber(phoneNumber);
		setEmail(email);
		setVerified(verified);
		setSim("8949226070633163788");
		Log.d("SIM",getSim()); 
		 
	}
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public void verifyUser() {
		// Hier die FUnktion um den User zu vertifizieren
		setVerified(true);	
<<<<<<< HEAD
	}
	
	public void logUser(User user){
		
		Log.d("Name",user.getName());
		Log.d("Nummer",user.getPhoneNumber());
		Log.d("EMail",user.getEmail());
		Log.d("Verifiziert",Integer.toString(user.getId()));
=======
>>>>>>> Michael
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
