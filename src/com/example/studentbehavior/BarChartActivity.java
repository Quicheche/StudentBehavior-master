package com.example.studentbehavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.example.studentbehavior.LineChartActivity.ReportNameComparator;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
import android.widget.ListView;


/**
 * Display the bar chart
 */
public class BarChartActivity extends Activity {

	private String username;
	private ArrayAdapter<String> adapter;
	private HashMap<String, ArrayList<String>> groupedByDateReports = new HashMap<String, ArrayList<String>>();
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
				groupedByDateReports.clear();
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
		reports = groupedByDateReports.keySet();

		ArrayList<String> local = new ArrayList<String>();
		for (String s : reports) {
			local.add(s);
		}
		Collections.sort(local, new ReportDateComparator());
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
				Intent intent = new Intent(BarChartActivity.this,
						BarChart.class);
				Report r;
				ArrayList<String> grades = new ArrayList<String>();
				for(String s: groupedByDateReports.get(report)){
					r = reportNameMap.get(s);
					grades.add(r.studentGrade);
				}
				intent.putExtra("grades", grades);
				intent.putExtra("date", report);
				BarChartActivity.this.startActivity(intent);
			}
		});
	}

	private void addIntoGroupedReports(String reportName, String reportDate) {
		if (groupedByDateReports.containsKey(reportDate)) {
			groupedByDateReports.get(reportDate).add(
					reportName + "   " + reportDate);
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(reportName + "   " + reportDate);
			groupedByDateReports.put(reportDate, temp);
		}

	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
