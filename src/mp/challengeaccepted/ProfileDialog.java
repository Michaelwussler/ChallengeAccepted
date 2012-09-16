package mp.challengeaccepted;

import android.app.Dialog;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileDialog extends Dialog {


		public ProfileDialog(Context context) {
			
		super(context);
	}

		private EditText yourname;
		private EditText yournumber;
		private EditText yourmail;

	
		
		public void onBackPressed()
		{
			Log.d("Back","pressed");
			if(yourname.getText().toString().length()!=0)
			{
				if(yournumber.getText().toString().length()!=0)
				{
					if(yourmail.getText().toString().length()!=0)
					{
						App.getUser().setUserName(yourname.getText().toString());
						App.getUser().setUserPhoneNumber(yournumber.getText().toString());
						App.getUser().setUserEmail(yourmail.getText().toString());
						App.getUser().verifyUser();
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
		
	

	   
	   
}
