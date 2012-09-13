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
	
	public int allesEingegeben()
	{
		if(title==null)
		{return 1;}
		if(description==null)
		{return 2;}
		if(receiver==null)
		{return 3;}
		if(emitter==null)
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
