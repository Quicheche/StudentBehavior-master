package com.example.studentbehavior;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;

/**
 * Creates a bar chart
 */
public class BarChart extends Activity {

	String date;
	ArrayList<String> grades;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bar_chart);

		date = getIntent().getExtras().getString("date");
		grades = (ArrayList<String>) getIntent().getExtras()
				.get("grades");
		
		HashMap<Integer, Integer> gradeNumMap = new HashMap<Integer,Integer>();
		for(int i = 1; i < 13; i++){
			gradeNumMap.put(i, 0);
		}
		int temp;
		
		for (String s: grades){
			temp = Integer.parseInt(s.replaceAll("\\D", ""));
			if(gradeNumMap.containsKey(temp))
				gradeNumMap.put(temp, gradeNumMap.get(temp) + 1);
		}
		
		GraphViewData[] data = new GraphViewData[12];
		
		int num = 0;
		int max = 0;
		for(int i = 1; i< 13; i++){
			num = gradeNumMap.get(i);
			data[i-1] = new GraphViewData(i,num);
			if (max < num)
				max = num;
		}
		
		String[] numLabel = new String[max + 1];
		for(int i = max; i > -1; i--){
			numLabel[max - i] = i + "";
		}
		
		GraphViewSeries exampleSeries = new GraphViewSeries(data);
		
		BarGraphView graphView = new BarGraphView(this, "Grade Graph on " + date);
		graphView.addSeries(exampleSeries);
		graphView.getGraphViewStyle().setVerticalLabelsWidth(25);
		
		graphView.setVerticalLabels(numLabel);
		graphView.setHorizontalLabels(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"});
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.bar_chart);
		layout.addView(graphView);
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar_chart, menu);
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
}
