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
					int time1 = Integer.parseInt(input1.getText().toString());
					int time2 = Integer.parseInt(input2.getText().toString());
					methodOff(time1);
					methodOn(time2);
					// int i = 1;
					// while (i <= 3) {
					// methodOff(2);
					// methodOn(time2);
					// i++;
					// }
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
	
	private void methodOn(final int time) {
		final int interval = time * 1000; // 1 Second
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			public void run() {
				enableWifi(time);
			}
		};
		handler.postAtTime(runnable, System.currentTimeMillis() + interval);
		handler.postDelayed(runnable, interval);
	}

	private void methodOff(final int time) {
		final int interval = time * 1000; // 1 Second
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			public void run() {
				disableWifi(time);
			}
		};
		handler.postAtTime(runnable, System.currentTimeMillis() + interval);
		handler.postDelayed(runnable, interval);
	}


	private void enableWifi(int time) {
		WifiManager w = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		w.setWifiEnabled(true);

		Toast.makeText(this, "Wifi Enabled " + Integer.toString(time),
				Toast.LENGTH_SHORT).show();
	}

	private void disableWifi(int time) {
		// wifi code
		WifiManager w = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		w.setWifiEnabled(false);

		Toast.makeText(this, "Wifi Disabled " + Integer.toString(time),
				Toast.LENGTH_SHORT).show();
	}
	
	private void conveyMessage(){
		Toast.makeText(this, "Set App to On position", Toast.LENGTH_SHORT).show();
	}

}
