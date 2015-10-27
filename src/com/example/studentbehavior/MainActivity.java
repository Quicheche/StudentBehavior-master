package com.example.studentbehavior;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;



/**
 * Main activity.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		try {
			Parse.initialize(this, "Q60Xc0o3UZOBLeB8mO83gX19LNV25xwr4LtaX34n",
					"o4JPB1gTW3Vov6UASIRMeuqVeB0Kg6uwy2G3ufXs");
		} catch (Exception e) {
		}

		setContentView(R.layout.activity_main);
	}

	public void onSignUpButtonClick(View view) {

		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);

	}

	public void onLoginButtonClick(View view) {

		final EditText usernameText = (EditText) findViewById(R.id.usernameText);
		final String username = usernameText.getText().toString();

		final EditText passwordText = (EditText) findViewById(R.id.passwordText);
		final String password = passwordText.getText().toString();

		final Intent intent = new Intent(this, WelcomeActivity.class);

		if (username.equals("") || password.equals("")) {
			showFailLoginError("Failed to login",
					"username or password cannot be empty.");

		} else {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
			query.findInBackground(new FindCallback<ParseObject>() {
				@Override
				public void done(List<ParseObject> userList, ParseException e) {
					if (e == null) {
						for (ParseObject user : userList) {
							if (user.get("username").equals(username)
									&& user.getString("password").equals(
											password)) {
								Toast logoutNotification = Toast.makeText(MainActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT);
								logoutNotification.show();
								intent.putExtra("username", username);
								startActivity(intent);
								return;
							}
						}
						usernameText.setText("");
						passwordText.setText("");
						showFailLoginError("Failed to login",
								"Incorrect username or password.\nPlease try again.");
					}
				}
			});
		}

	}

	private void showFailLoginError(String errorTitle, String error) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle(errorTitle);
		alert.setMessage(error);

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
