package com.example.studentbehavior;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReportDateComparatorTest {

	@Test
	public void testBig() {
		ReportDateComparator comparator = new ReportDateComparator();
		String date1 = "12-11-2014";
		String date2 = "12-10-2014";
		assertEquals(comparator.compare(date1, date2), 1);
	}

	@Test
	public void testEqual() {
		ReportDateComparator comparator = new ReportDateComparator();
		String date1 = "12-10-2014";
		String date2 = "12-10-2014";
		assertEquals(comparator.compare(date1, date2), 1);
	}
	
	@Test
	public void testSmall() {
		ReportDateComparator comparator = new ReportDateComparator();
		String date1 = "12-10-2014";
		String date2 = "12-11-2014";
		assertEquals(comparator.compare(date1, date2), 1);
	}
}
