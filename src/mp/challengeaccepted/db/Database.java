package mp.challengeaccepted.db;

import mp.challengeaccepted.App;
import mp.challengeaccepted.Challenge;
import mp.challengeaccepted.DatabaseHandlerChallenge;
import android.content.Context;

public class Database {
	
	//gibt die ServerID der neuen Challenge zurueck Ÿbernimmt alle funktionen zum erstellen und syncen einer challenge
	public static int createChallenge(Challenge challenge, Context context){
		
		DatabaseHandlerChallenge dbchallenge = new DatabaseHandlerChallenge(context);
		int id = dbchallenge.addChallenge(challenge);
		int serverID=0;
		if(id!=0){
			Challenge challenge_with_serverID = ServerDB.newChallenge(challenge);
			serverID=challenge_with_serverID.getServerId();
			if(serverID!=-1){
				dbchallenge.setUptodate(1, id);
				dbchallenge.setServerID(serverID, id);
				
				//PUSHAKTION -> Receiver sync
			}
			else{
			App.uptodate=0;
			}
		}
		return serverID;
	}
	
	public static void syncChallenges(){
		
	}
	
	
	//accepted die challange und synct das update
	public static void acceptChallenge(){
		
	}
	
	
}
