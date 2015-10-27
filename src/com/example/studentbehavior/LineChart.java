package com.example.studentbehavior;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;


/**
 * Creates a line chart.
 */
public class LineChart extends Activity {

	String student;
	HashMap<String, String> grades;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.line_chart);

		student = getIntent().getExtras().getString("name");
		grades = (HashMap<String, String>) getIntent().getExtras()
				.get("grades");

		ArrayList<String> dates = new ArrayList<String>();
		for (String d : grades.keySet()) {
			dates.add(d);
		}
		Collections.sort(dates, new ReportDateComparator());

		for (int i = 0; i < grades.size(); i++) {
			Log.d("string", "" + dates.get(i));
			Log.d("string", "" + grades.get(dates.get(i)));
		}

		MyLineChartData[] mydata = new MyLineChartData[grades.size()];
		int count = 0;
		String[] sortDates = new String[grades.size()];
		String[] mappedGrades = new String[grades.size()];
		for (String d : dates) {
			mydata[count] = new MyLineChartData(d, grades.get(d));
			sortDates[count] = d;
			mappedGrades[count] = grades.get(d);
			count++;
		}

		GraphViewSeries exampleSeries = new GraphViewSeries(mydata);
		GraphView graphView = new LineGraphView(this // context
				, student + "'s Grades Graph" // heading
		);
		graphView.addSeries(exampleSeries); // data
		graphView.setManualYAxisBounds(-1, -12);
		((LineGraphView) graphView).setDrawDataPoints(true);
		((LineGraphView) graphView).setDataPointsRadius(5f);
		graphView.setHorizontalLabels(new String[] { sortDates[0],
				sortDates[grades.size() - 1] });
		graphView.setVerticalLabels(new String[] { "1st", "2nd", "3rd", "4th",
				"5th", "6th", "7th", "8th", "9th", "10rd", "11rd", "12th" });

		LinearLayout layout = (LinearLayout) findViewById(R.id.line_chart);
		layout.addView(graphView);
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

	class MyLineChartData implements GraphViewDataInterface {

		Date date;
		int grade;

		public MyLineChartData(String date, String grade) {
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
			try {
				this.date = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			this.grade = Integer.parseInt(grade.replaceAll("\\D", ""));
		}

		@Override
		public double getX() {
			int year = date.getYear() * 365;
			int month = date.getMonth() * 30;
			int day = date.getDay();
			return year + month + day;
		}

		@Override
		public double getY() {
			return -grade;
		}

	}
}
