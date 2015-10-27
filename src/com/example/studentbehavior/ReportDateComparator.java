package com.example.studentbehavior;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;



/**
 * Comparator for report date
 */
public class ReportDateComparator implements Comparator<String> {
	@Override
	public int compare(String reportOne, String reportTwo) {
		
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

		Date date1 = null;
		Date date2 = null;
		
		try {
			date1 = df.parse(reportOne);
			date2 = df.parse(reportTwo);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date1.compareTo(date2);

	}
}