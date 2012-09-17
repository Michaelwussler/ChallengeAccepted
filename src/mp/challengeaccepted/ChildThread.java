package mp.challengeaccepted;

import org.json.JSONException;
import org.json.JSONObject;

import mp.challengeaccepted.db.DBFunctions;
import mp.challengeaccepted.db.ServerDB;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class ChildThread extends Thread {

    private static final String INNER_TAG = "ChildThread";
    static Handler mChildHandler;
    
    public void run() {

        this.setName("child");

        // Loop wird prepared
        Looper.prepare();

        mChildHandler = new Handler() {

            public void handleMessage(Message msg, Handler handler) {

                Log.i(INNER_TAG, "Got an incoming message from the main thread - " + (String)msg.obj);

                /*
                 * Do some expensive operation there. For example, you need
                 * to constantly send some data to the server.
                 */
                try {

                    /*
                     * Mocking an expensive operation. It takes 100 ms to
                     * complete.
                     */
                	 //TEST     
          	      String KEY_SUCCESS = "success";
          		  String KEY_ERROR = "error";
          		    
     //     		  ServerDB.isUser("1231");
          	    
          	      //ENDE TEST
                    sleep(100);

                    /*
                     * Send the processing result back to the main thread.
                     */
                    Message toMain = handler.obtainMessage();
                    toMain.obj = "This is " + this.getLooper().getThread().getName() +
                        ".  Did you send me \"" + (String)msg.obj + "\"?";
                    handler.sendMessage(toMain);
                    Log.i(INNER_TAG, "Send a message to the main thread - " + (String)toMain.obj);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        Log.i(INNER_TAG, "Child handler is bound to - " + mChildHandler.getLooper().getThread().getName());

        /*
         * Start looping the message queue of this thread.
         */
        Looper.loop();
    }
}
