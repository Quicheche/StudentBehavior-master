package com.example.studentbehavior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



/**
 * Displays the welcome acitivy.
 */
public class WelcomeActivity extends Activity {

	String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		username = getIntent().getExtras().getString("username");
		setContentView(R.layout.activity_welcome);
		initCreateButton();
		initViewNameButton();
		initViewDateButton();
		initViewLineChartButton();
		initViewBarChartButton();
	}

	private void initCreateButton() {
		Button createButton = (Button) findViewById(R.id.button4);
		final Intent intent = new Intent(this, ReportActivity.class);
		intent.putExtra("username", username);
		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		});
	}
	
	private void initViewNameButton(){
		Button createButton = (Button) findViewById(R.id.button1);
		final Intent intent = new Intent(this, ViewReportByNameActivity.class);
		intent.putExtra("username", username);
		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toast();
				startActivity(intent);
			}
		});
	}
	
	private void initViewDateButton(){
		Button createButton = (Button) findViewById(R.id.button2);
		final Intent intent = new Intent(this, ViewReportByDateActivity.class);
		intent.putExtra("username", username);
		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toast();
				startActivity(intent);
			}
		});
	}
	
	private void initViewLineChartButton(){
		Button createButton = (Button) findViewById(R.id.button3);
		final Intent intent = new Intent(this, LineChartActivity.class);
		intent.putExtra("username", username);
		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toast();
				startActivity(intent);
			}
		});
	}
	
	private void initViewBarChartButton(){
		Button createButton = (Button) findViewById(R.id.button5);
		final Intent intent = new Intent(this, BarChartActivity.class);
		intent.putExtra("username", username);
		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toast();
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}
	
	private void toast(){
		Toast logoutNotification = Toast.makeText(WelcomeActivity.this, "Fetching data...", Toast.LENGTH_SHORT);
		logoutNotification.show();
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
