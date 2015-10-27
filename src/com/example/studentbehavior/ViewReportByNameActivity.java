package com.example.studentbehavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ViewReportByNameActivity extends Activity {

	static final int RESULT_DELETED_REPORT = 10;
	private static final int REPORT_DETAILS_REQUEST = 5;

	private String username;
	private ArrayAdapter<String> adapter;
	private HashMap<String, ArrayList<String>> groupedByNameReports = new HashMap<String, ArrayList<String>>();
	private HashMap<String, Report> reportNameMap = new HashMap<String, Report>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		username = getIntent().getExtras().getString("username");
		setContentView(R.layout.activity_view_report_by_name);
		refreshReports();
		initClearButton();
		initSearchButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_report_by_name, menu);
		return true;
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

	public void refreshReports() {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Report");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseReportList, ParseException e) {
				groupedByNameReports.clear();
				reportNameMap.clear();
				if (e == null) {
					for (ParseObject parseReport : parseReportList) {
						Report report = new Report(parseReport);
						if (username.equals(report.username)) {
							addIntoGroupedReports(report.studentName,
									report.reportCreatedDate);
							reportNameMap.put(report.studentName + "   "
									+ report.reportCreatedDate, report);
						}
					}
					updateView();

				} else {

				}
			}
		});
	}
	
	private void initClearButton(){
		Button clearButton = (Button) findViewById(R.id.clear);
		final EditText et = (EditText) findViewById(R.id.input);
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				et.setText("");
				updateView();
				
			}
		});
	}
	
	private void initSearchButton() {

		Button searchButton = (Button) findViewById(R.id.search);

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText et = (EditText) findViewById(R.id.input);
				String search = et.getText().toString();
				ArrayList<String> searchResult = new ArrayList<String>();
				for(String key : groupedByNameReports.keySet()){
					if (key.contains(search)) {
						searchResult.addAll(groupedByNameReports.get(key));
					}
				}
		   		if(searchResult.isEmpty())
		   			showNoResultDialog();
		   		else updateView(searchResult);
		   			
			}
		});
	}
	
	private void showNoResultDialog() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("No result found!");
		alert.setMessage("Cannot find a report. \nPlease refine your search condition.");

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		alert.show();
	}
	
	private void updateView() {
		Set<String> reports;
		reports = groupedByNameReports.keySet();

		ArrayList<String> local = new ArrayList<String>();
		for (String s : reports) {
			local.add(s);
		}
		Collections.sort(local, new ReportNameComparator());
		updateView(local);
	}
	
	protected void updateView(ArrayList<String> reports) {
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, reports);

		ListView listView = (ListView) findViewById(R.id.report_list);
		listView.setAdapter(adapter);

		final ArrayList<String> sortedReports = new ArrayList<String>(reports);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String report = sortedReports.get(position);
				if (!report.contains("   ")) {
					updateView(groupedByNameReports.get(report));
				} else {
					Report report2 = reportNameMap.get(sortedReports
							.get(position));
					Intent detailPage = new Intent(ViewReportByNameActivity.this,
							PastReportDetail.class);
					detailPage.putExtra(getString(R.string.past_report_data),
							report2);
					ViewReportByNameActivity.this.startActivityForResult(detailPage,
							REPORT_DETAILS_REQUEST);
				}
			}
		});
	}

	private void addIntoGroupedReports(String reportName, String reportDate) {
		if (groupedByNameReports.containsKey(reportName)) {
			groupedByNameReports.get(reportName).add(
					reportName + "   " + reportDate);
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(reportName + "   " + reportDate);
			groupedByNameReports.put(reportName, temp);
		}

	}
	
	@Override
	public void onResume() {
		super.onResume();
		onCreate(null);
	}
	
	
	public class ReportNameComparator implements Comparator<String> {
		@Override
		public int compare(String reportOne, String reportTwo) {
			// Compare and return lexographic ordering of names
			return reportOne.compareToIgnoreCase(reportTwo);
		}
	}

}
