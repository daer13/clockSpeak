package com.androidbusiness.clockspeak;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StartActivity extends Activity {

	private static final String TAG = StartActivity.class.getSimpleName();
	private StartApplication application;
	
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

	public void bottonActionStart(View view){
		Log.d(TAG, "Start botton Init");
		//startActivity(new Intent("com.androidbusiness.clockspeak.listeners.StartApplication"));
		startService(new Intent("com.androidbusiness.clockspeak.listeners.UpdaterService"));
		Log.d(TAG, "Start botton End");
	}
	
	public void bottonActionStop(View view){
		Log.d(TAG, "Stop botton Init");
//		stopService(new Intent("com.androidbusiness.clockspeak.listeners.UpdaterService"));
		Log.d(TAG, "Stop botton Stop");
	}
}
