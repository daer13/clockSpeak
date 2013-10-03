package com.androidbusiness.clockspeak;


import com.androidbusiness.clockspeak.listeners.CustomOnClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StartActivity extends Activity {

	private static final String TAG = StartActivity.class.getSimpleName();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		Spinner sp = (Spinner) findViewById(R.id.list_minutes);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_minutes, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
		Button btn_start = (Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new CustomOnClickListener());
		Button btn_close = (Button)findViewById(R.id.btn_stop);
		btn_close.setOnClickListener(new CustomOnClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	
}
