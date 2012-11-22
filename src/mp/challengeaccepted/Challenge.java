package mp.challengeaccepted;
import java.io.File;
import java.util.Date;

import android.util.Log;



public class Challenge 
{

	private int id; 
	private int serverId;
	private String title="Title";
	private String description="Description";
	private String proof="Proof";
	private Profile receiver;
	private Profile sender;
	private int status;
	private String channel;
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

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public Challenge(String title, String description, Profile receiver, Profile sender, String proof, int status)
	{
		this.title=title;
		this.description=description;
		this.receiver=receiver;
		this.sender=sender;
		this.proof=proof;
		this.status=status;
		this.timestamp= new Date(System.currentTimeMillis());
		this.channel = "empty";
	}
	
	public Challenge(String serverid, String title, String description, String receiver, String sender, String proof, String status)
	{
		this.serverId=Integer.valueOf(serverid);
		this.title=title;
		this.description=description;
		this.receiver.setPhoneNumber(receiver);
		this.sender.setPhoneNumber(sender);
		this.proof=proof;
		this.status=Integer.valueOf(status);
		this.channel = "empty";
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

	static public void log_print(Challenge challenge){
		
		Log.i("ServerID", String.valueOf(challenge.getServerId()));
		Log.i("title", challenge.getTitle());
		Log.i("description", challenge.getDescription());
		Log.i("receiver", challenge.getReceiver().getPhoneNumber());
		Log.i("sender", challenge.getSender().getPhoneNumber());
		Log.i("proof", challenge.getProof());
		Log.i("status", String.valueOf(challenge.getStatus()));
		Log.i("channel", challenge.getChannel());

	}
	
}
