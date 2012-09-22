package mp.challengeaccepted;
import java.io.File;
import java.util.Date;



public class Challenge 
{

	private int id; 
	private String title="Title";
	private String description="Description";
	private String proof="Proof";
	private Profile receiver;
	private Profile sender;
	private int status;
	private boolean channelChallenge;
	
	private Date timestamp; 
	private File file;
	
	
	public final static int New=1;
	public final static int Accepted=2;
	public final static int Denied=3;
	public final static int Completed=4;
	public final static int ProofConfirmation=5;
	public final static int ProofDenied=6;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProof() {
		return proof;
	}

	public void setProof(String proof) {
		this.proof = proof;
	}

	public Profile getReceiver() {
		return receiver;
	}

	public void setReceiver(Profile receiver) {
		this.receiver = receiver;
	}

	public Profile getSender() {
		return sender;
	}

	public void setSender(Profile sender) {
		this.sender = sender;
	}

	public boolean isChannelChallenge() {
		return channelChallenge;
	}

	public void setChannelChallenge(boolean channelChallenge) {
		this.channelChallenge = channelChallenge;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	public Challenge(String title, String description, Profile receiver, Profile sender, String proof, int status)
	{
		this.title=title;
		this.description=description;
		this.receiver=receiver;
		this.sender=sender;
		this.proof=proof;
		this.status=status;
	}
	
	public Challenge() {
		// TODO Auto-generated constructor stub
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
		if(status==-1)
		{return 6;}
		if(timestamp==null)
		{return 7;}
		if(file==null)
		{return 8;}
		return 0;
	}
}
