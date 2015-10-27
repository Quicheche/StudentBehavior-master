package com.example.studentbehavior;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Activity to display the signup.
 */
public class SignUpActivity extends Activity {

	ArrayList<String> usernames = new ArrayList<String>();

	final String comfirmationTitle = "Password Confirmation";
	final String comfirmationMessage = "Password Confirmation incorrect. \nPlease enter again.";

	final String usernameTitle = "Username Already Exisit";
	final String usernameMessage = "Username Already Exisit.\nPlease try another user name.";
	
	final String emptyPasswordTitle = "Empty Password!";
	final String emptyPasswordMessage = "Please enter your password.";

	boolean isDoneReadingUsernames = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		getAllUserName();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_activity);
		initSignUpButton();
	}

	private void initSignUpButton() {
		final Button button = (Button) findViewById(R.id.sign_up_button);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signUpButtonOnClick();
			}
		});
	}

	public void signUpButtonOnClick() {

		if (!isDoneReadingUsernames) {
			try {
				Thread.sleep(10000);
				signUpButtonOnClick();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		EditText usernameText = (EditText) findViewById(R.id.username_sign_up);
		String username = usernameText.getText().toString();

		EditText passwordText = (EditText) findViewById(R.id.password_sign_up);
		String password = passwordText.getText().toString();

		EditText passwordConfirmText = (EditText) findViewById(R.id.password_confirm_sign_up);
		String passwordConfirm = passwordConfirmText.getText().toString();

		if (password.equals("")) {
			showFixUserInputDialog(emptyPasswordTitle, emptyPasswordMessage);
			passwordText.setText("");
			passwordConfirmText.setText("");
			usernameText.setText("");
			return;
		}

		if (!passwordConfirm.equals(password)) {
			showFixUserInputDialog(comfirmationTitle, comfirmationMessage);
			passwordText.setText("");
			passwordConfirmText.setText("");
			usernameText.setText("");
			return;
		}

		if (usernames.contains(username)) {
			showFixUserInputDialog(usernameTitle, usernameMessage);
			usernameText.setText("");
			passwordText.setText("");
			passwordConfirmText.setText("");
			return;
		}

		ParseObject user = new ParseObject("User");
		user.put("username", username);
		user.put("password", password);
		user.saveInBackground();
		Toast logoutNotification = Toast.makeText(SignUpActivity.this, "Signed up Successfully!", Toast.LENGTH_SHORT);
		logoutNotification.show();
		finish();
	}

	private void getAllUserName() {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("User");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> userList, ParseException e) {
				if (e == null) {
					for (ParseObject user : userList) {
						usernames.add(user.getString("username"));
					}
					isDoneReadingUsernames = true;
				}
			}
		});
	}

	private void showFixUserInputDialog(String title, String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle(title);
		alert.setMessage(message);

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		alert.show();
	}

}
