package com.example.studentbehavior;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReportTest {

	@Test
	public void test() {
		Report report = new Report();
		assertNotNull(report.PB);
		assertNotNull(report.NB);
		assertNotNull(report.AR);
	}

	@Test
	public void testFields() {
		Report report = new Report();
		assertEquals(report.studentName, "");
		assertEquals(report.studentGrade, "");
		assertEquals(report.reportCreatedDate, "");
		assertEquals(report.behaviorSummary, "");
	}
}
