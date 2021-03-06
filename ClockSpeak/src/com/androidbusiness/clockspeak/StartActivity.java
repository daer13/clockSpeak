package com.androidbusiness.clockspeak;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.androidbusiness.clockspeak.listeners.UpdaterService;


public class StartActivity extends Activity {

	public static final String TIMER = "timer";
	private static final String TAG = StartActivity.class.getSimpleName();
	private ToggleButton tgButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		Spinner sp = (Spinner) findViewById(R.id.list_minutes);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_minutes, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
//		Button btn_start = (Button)findViewById(R.id.btn_start);
//		btn_start.setOnClickListener(new CustomOnClickListener());
//		Button btn_close = (Button)findViewById(R.id.btn_stop);
//		btn_close.setOnClickListener(new CustomOnClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}
	

	
	public void toggleClicked(View v) {
		Log.i("TAG","toggle");
		tgButton = (ToggleButton) findViewById(R.id.btn_toggle);
		if (tgButton.isChecked()){
			bottonActionStart(v);
		}else{
			bottonActionStop(v);
		}
		
	}

	public void bottonActionStart(View view){
		Log.d(TAG, "Start botton Init");
		//startActivity(new Intent("com.androidbusiness.clockspeak.listeners.StartApplication"));
		//startService(new Intent("com.androidbusiness.clockspeak.listeners.UpdaterService"));
		Intent serviceIntent = new Intent(this,UpdaterService.class); 
		Spinner spinner = (Spinner)findViewById(R.id.list_minutes);
		String selectedItem = (String)spinner.getSelectedItem();
		serviceIntent.putExtra(TIMER, Integer.valueOf(selectedItem));
		startService(serviceIntent);
		Log.d(TAG, "Start botton End");
	}
	
	public void bottonActionStop(View view){
		Log.d(TAG, "Stop botton Init");
//		stopService(new Intent("com.androidbusiness.clockspeak.listeners.UpdaterService"));
		stopService(new Intent(this, UpdaterService.class));
		Log.d(TAG, "Stop botton Stop");
		finish();
	}

	
}
