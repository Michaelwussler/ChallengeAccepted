package mp.challengeaccepted;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileDialog extends Dialog {
	private EditText yourname;
	private EditText yournumber;
	private EditText yourmail;
	private Button buttonOkay;



		public EditText getYourname() {
		return yourname;
	}


	public void setYourname(EditText yourname) {
		this.yourname = yourname;
	}


	public EditText getYournumber() {
		return yournumber;
	}


	public void setYournumber(EditText yournumber) {
		this.yournumber = yournumber;
	}


	public EditText getYourmail() {
		return yourmail;
	}


	public void setYourmail(EditText yourmail) {
		this.yourmail = yourmail;
	}


		public ProfileDialog(Context context) {
			
		super(context);
	}


		public void onBackPressed()
		{
			Log.d("Back","pressed");
			if(yourname.getText().toString().length()!=0)
			{
				if(yournumber.getText().toString().length()!=0)
				{
					if(yourmail.getText().toString().length()!=0)
					{
						Log.d("onBackPressed","inSchleife");
						super.onBackPressed();
					}
					else
					{
						Toast.makeText(this.getContext(), "Please insert your Email-address", Toast.LENGTH_SHORT).show();

					}
			
				}
				else
				{
					Toast.makeText(this.getContext(), "Please insert your phone number", Toast.LENGTH_SHORT).show();
				}
			}
			else
			{
				Toast.makeText(this.getContext(), "Please insert your name", Toast.LENGTH_SHORT).show();
			}
			
		}


		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{


super.onCreate(savedInstanceState);
		}


		/**
		 * @return the buttonOkay
		 */
		public Button getButtonOkay() {
			return buttonOkay;
		}


		/**
		 * @param buttonOkay the buttonOkay to set
		 */
		public void setButtonOkay(Button buttonOkay) {
			this.buttonOkay = buttonOkay;
		}

	
		
		

}
