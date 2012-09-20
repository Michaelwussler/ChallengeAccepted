package mp.challengeaccepted;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ProfileSettingActivity extends Activity {
	
	private EditText yourname;
	private EditText yournumber;
	private EditText yourmail;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesetting);
        yourname.findViewById(R.id.editTextName);
        yournumber.findViewById(R.id.editTextNumber);
        yourmail.findViewById(R.id.editTextEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inputnumber, menu);
        return true;
    }

	@Override
	protected void onPause() {
		super.onPause();
	}
    
	
	
	
	

    

    
}
