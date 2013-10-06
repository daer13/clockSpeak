package com.androidbusiness.clockspeak.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.androidbusiness.clockspeak.StartApplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

public class UpdaterService extends Service implements TextToSpeech.OnInitListener {

	private final String TAG = UpdaterService.class.getSimpleName();
	
	static final int DELAY = 60000;
	private boolean runFlag = false;
	private Updater updater;
	private TextToSpeech tts;
	
	private StartApplication application;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}



	@Override
	public void onCreate() {
		super.onCreate();
		application = (StartApplication) getApplication();
		updater = new Updater();
		tts = new TextToSpeech(this, this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		
		runFlag = false;
		application.setServiceRunningFlag(false);
		updater.interrupt();
		updater = null;
		
		Log.d(TAG, "onDestryed");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		if(!runFlag){
			runFlag = true;
			application.setServiceRunningFlag(true);
			updater.start();
		}
		
		Log.d(TAG, "onStarted");
		return START_STICKY;
	}

	private void setLanguageToSpeak() {
		int result = tts.setLanguage(Locale.getDefault());
		if (result == TextToSpeech.LANG_MISSING_DATA
				|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
			Log.e("TTS", "This Language is not supported");
		} else {
			speakOut("Prueba");
		}
	}

	private void speakOut(String text) {
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	
	private void setMVSpanish(){
		Locale.setDefault(new Locale("es", "ES"));
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			this.setMVSpanish();
			this.setLanguageToSpeak();
		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}
	
	private class Updater extends Thread{
		public Updater(){
			super("UpdaterService-UpdaterThread");
		}
		
		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while (updaterService.runFlag) {
				Log.d(TAG, "UpdaterThread running");
				try{
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Log.d(TAG, "HORA -> "+sdf.format(date));
					speakOut(sdf.format(date));
//					TextView textClock = (TextView)findViewById(R.id.txt_clock);
//					textClock.setText(sdf.format(date));
					
					Thread.sleep(DELAY); 
				}catch(InterruptedException e){
					updaterService.runFlag = false;
					application.setServiceRunningFlag(true);
				}
			}
		}
	}
}

