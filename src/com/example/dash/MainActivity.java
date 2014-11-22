package com.example.dash;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText input1 = (EditText) findViewById(R.id.editText1);
		final EditText input2 = (EditText) findViewById(R.id.editText2);
		final ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
		Button setApp = (Button) findViewById(R.id.button1);
		setApp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				if (tb.isChecked()) {
					//finish();
					int time1 = Integer.parseInt(input1.getText().toString());
					int time2 = Integer.parseInt(input2.getText().toString());
					
					int interval = time1*1000;
					Handler handler = new Handler();
					Runnable runnable = new Runnable() {
						public void run() {
							disableWifi();
						}
					};
					
					int interval1 = time2*1000;
					Runnable runnable1 = new Runnable(){
						public void run(){
							enableWifi();
						}
					};
					
					handler.postAtTime(runnable, System.currentTimeMillis() + interval);
					handler.postDelayed(runnable, interval);
					
					
					int time3 = 5;
					int interval2 = time3*1000;
					
					
					int i = 1;
					while(i <= 10){
						
						handler.postAtTime(runnable1, System.currentTimeMillis() + interval + i*interval1 + (i-1)*interval2);
						handler.postDelayed(runnable1, interval + i*interval1 + (i-1)*interval2);
						
						handler.postAtTime(runnable, System.currentTimeMillis() + interval + i*interval1 + i*interval2);
						handler.postDelayed(runnable, interval + i*interval1 + i*interval2);
						
						i++;
					}
				}
				else{
					conveyMessage();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void enableWifi() {
		WifiManager w = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		w.setWifiEnabled(true);

		Toast.makeText(this, "Wifi Enabled ",Toast.LENGTH_SHORT).show();
	}

	private void disableWifi() {
		WifiManager w = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		w.setWifiEnabled(false);

		Toast.makeText(this, "Wifi Disabled ",Toast.LENGTH_SHORT).show();
	}
	
	private void conveyMessage(){
		Toast.makeText(this, "Set App to On position", Toast.LENGTH_SHORT).show();
	}

}
