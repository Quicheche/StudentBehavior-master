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
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;



/**
 * Displays the line chart.
 */
public class LineChartActivity extends Activity {

	private String username;
	private ArrayAdapter<String> adapter;
	private HashMap<String, ArrayList<String>> groupedByNameReports = new HashMap<String, ArrayList<String>>();
	private HashMap<String, Report> reportNameMap = new HashMap<String, Report>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line_chart);
		username = getIntent().getExtras().getString("username");
		refreshReports();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.line_chart, menu);
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
				HashMap<String,String> grades = new HashMap<String, String>();
				Report r;
				for(String s: groupedByNameReports.get(report)){
					r = reportNameMap.get(s);
					grades.put(r.reportCreatedDate,r.studentGrade);
				}
				if (grades.size() > 1){
					Intent intent = new Intent(LineChartActivity.this, LineChart.class);
					intent.putExtra("grades", grades);
					intent.putExtra("name", report);
					LineChartActivity.this.startActivity(intent);
				} else {
					Report temp = reportNameMap.get(groupedByNameReports.get(report).get(0));
					showGradeMessage(report, temp.studentGrade, temp.reportCreatedDate);
				}
			}
		});
	}
	
	private void showGradeMessage(String name, String grade, String date) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Grades");
		alert.setMessage(name + " only have one report: \n" + grade + "     on " + date);

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		alert.show();
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
	}

	public class ReportNameComparator implements Comparator<String> {
		@Override
		public int compare(String reportOne, String reportTwo) {
			// Compare and return lexographic ordering of names
			return reportOne.compareToIgnoreCase(reportTwo);
		}
	}

}
