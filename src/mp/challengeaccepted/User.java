package mp.challengeaccepted;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class User extends Profile 
{

	private String sim = "leer";
	
	public User()
	{
	}
	
	public User(String name, String email, String telefonnummer, Boolean verifiziert, String sim)
	{
		this.setName(name);
		this.setEmail(email);
		this.setPhoneNumber(telefonnummer);
		this.setVerified(verifiziert);
		Log.i("HIER VERIFIZIERT????", Boolean.toString(verifiziert));
		this.setSim(sim);
	}
	
	
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
	
	public void logUser(User user){
		
		Log.d("Name",user.getName());
		Log.d("Nummer",user.getPhoneNumber());
		Log.d("EMail",user.getEmail());
		Log.d("Verifiziert",Integer.toString(user.getId()));
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}
	



	

}
