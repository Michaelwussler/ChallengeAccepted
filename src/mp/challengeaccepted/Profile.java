package mp.challengeaccepted;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.util.Log;



public class Profile 
{
	private String name;
	private String phoneNumber = "";
	private int id = -1;  // soll bei der Registrierung von dem Server zurückgegebne werden (MUSS NOCH GEMACHT WERDEN!)
	private String email;
	private boolean verified;//=false;
	private boolean registered =false; // gibt an, ob das Profil auf dem Server existiert


	
	
	public Profile(String telefonnummer) {
		this.phoneNumber = telefonnummer;
	}

	public Profile() {
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param string the phoneNumber to set
	 */
	public void setPhoneNumber(String string) {	
		this.phoneNumber = string;
	}
	

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return verified;
	}
	/**
	 * @param verified the verified to set
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

}
