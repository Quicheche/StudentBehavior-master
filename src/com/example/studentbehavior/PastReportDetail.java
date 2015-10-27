package com.example.studentbehavior;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;



/**
 * Display past report, encluding email funcitonality.
 */
public class PastReportDetail extends Activity {
	
	protected Report pastReport;
	private HashMap<Integer,String>idStringMap = new HashMap<Integer,String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.past_reports_details);

		// Retrieve brother data from intent.
		int s = R.string.past_report_data;
		String content = getString(s);
		pastReport = (Report)getIntent().getParcelableExtra(content);
		
		
		//pastReport = (Report) getIntent().getParcelableExtra(getString(R.string.past_report_data));
		
		createHash();
		for (Integer i: idStringMap.keySet()){
			setTextfield(i);
		}
	

		final Button button = (Button) findViewById(R.id.email_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	getUserEmailPrompt();
            }
        });
	
       final Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new AlertDialog.Builder(PastReportDetail.this)
            		.setTitle("Confirm Deletion")
            		.setMessage("Delete this report?")
            		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		            	@Override
		            	public void onClick(DialogInterface dialog, int which) {
		            		deleteReport();
		            		
			            	// Notify the user that deletion is complete.
			            	Toast logoutNotification = Toast.makeText(PastReportDetail.this, "Report deleted.", Toast.LENGTH_SHORT);
			            	logoutNotification.show();
			            	PastReportDetail.this.finish();
			            	
			            }

						private Object getActivity() {
							// TODO Auto-generated method stub
							return null;
						}
			        })
	            	.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		            	public void onClick(DialogInterface dialog, int which) {
		            		// do nothing
		            	}
		            })
	            	.setIcon(android.R.drawable.ic_dialog_alert)
	            	.show();
            }
        });	
         
	}
	private void createHash(){
		idStringMap.put(R.id.name,pastReport.studentName);
		idStringMap.put(R.id.grade, pastReport.studentGrade);
		idStringMap.put(R.id.date, pastReport.reportCreatedDate);
		idStringMap.put(R.id.behavior_location,pastReport.behaviorSetting);
		
		idStringMap.put(R.id.behavior_report_details,pastReport.behaviorSummary);
		idStringMap.put(R.id.behavior_report_comments,pastReport.behaviorComment);
		idStringMap.put(R.id.academic_location,pastReport.academicSetting);
		idStringMap.put(R.id.academic_report_details,pastReport.academicSummary);
		idStringMap.put(R.id.academic_report_comments,pastReport.academicComment);
		idStringMap.put(R.id.strategies_report_details,pastReport.strategySummary);
		idStringMap.put(R.id.strategies_report_comments,pastReport.strategyComment);
	}
	private void setTextfield(int m){
		TextView view = (TextView) findViewById(m);
		String content = idStringMap.get(m);
		view.setText(content);
	}
	
	private void deleteReport() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Report");
    	query.whereEqualTo("objectId", pastReport.objectID);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseReportList, ParseException e) {
				if (e == null)
					try {
						if (parseReportList.size() > 0) {
							parseReportList.get(0).delete();
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				
			}
		});
	}
	
	protected void sendEmail(String emailAddress) {
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("message/rfc822");


	      emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"PUT YOUR EMAIL ADDRESS HERE"});
	      emailIntent.putExtra(Intent.EXTRA_CC, new String[] {"PUT YOUR CC ADDRESS HERE"});
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "New Student Behavior Report");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, pastReport.createEmailReportString());

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(this, 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.past_report_list, menu);
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
	
	private void getUserEmailPrompt() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Send to Email");
		alert.setMessage("If you would like to receive a copy of this report by email, please enter your email address below.");

		alert.setPositiveButton("Send email", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  sendEmail("");
		  }
		});

		alert.setNegativeButton("Don't email", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
	}
}