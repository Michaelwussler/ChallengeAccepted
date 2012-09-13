package mp.challengeaccepted;
import java.io.File;
import java.util.Date;



public class Challenge 
{
	private String title;
	private String description;
	private String proof;
	private String receiver;
	private String emitter;
	private boolean channelChallenge;
	private Status status;
	private Date timestamp; 
	private File file;
	

	
	public Challenge(String title, String description, String receiver, String emitter, String proof, Status status)
	{
		this.title=title;
		this.description=description;
		this.receiver=receiver;
		this.emitter=emitter;
		this.proof=proof;
		this.status=status;
	}
	
	public boolean allesEingegeben()
	{
		if(title==null)
		{return false;}
		if(description==null)
		{return false;}
		if(receiver==null)
		{return false;}
		if(emitter==null)
		{return false;}
		if(proof==null)
		{return false;}
		if(status==null)
		{return false;}
		return true;
	}
}
