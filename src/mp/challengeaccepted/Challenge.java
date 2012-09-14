package mp.challengeaccepted;
import java.io.File;
import java.util.Date;



public class Challenge 
{
	private int id; 
	private String title;
	private String description;
	private String proof;
<<<<<<< HEAD
	private Profil receiver;
	private Profil emitter;
=======
	private String receiver;
	private String sender;
>>>>>>> 672aad7ddac1f38bd6f084f92354051b6ee9eff4
	private boolean channelChallenge;
	private Status status;
	private Date timestamp; 
	private File file;
	

	
<<<<<<< HEAD
	public Challenge(String title, String description, Profil receiver, Profil emitter, String proof, Status status)
=======
	public Challenge(String title, String description, String receiver, String sender, String proof, Status status)
>>>>>>> 672aad7ddac1f38bd6f084f92354051b6ee9eff4
	{
		this.title=title;
		this.description=description;
		this.receiver=receiver;
		this.sender=sender;
		this.proof=proof;
		this.status=status;
	}
	
	public int allesEingegeben()
	{
		if(title==null)
		{return 1;}
		if(description==null)
		{return 2;}
		if(receiver==null)
		{return 3;}
		if(sender==null)
		{return 4;}
		if(proof==null)
		{return 5;}
		if(status==null)
		{return 6;}
		if(timestamp==null)
		{return 7;}
		if(file==null)
		{return 8;}
		return 0;
	}
}
