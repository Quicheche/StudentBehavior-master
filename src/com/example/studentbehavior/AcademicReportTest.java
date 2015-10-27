package com.example.studentbehavior;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AcademicReportTest {

	@Test
	public void testAcademicMapSize() {
		AcademicReport academicReport = new AcademicReport();
		assertNotNull(academicReport.AcademicMap);
		assertEquals(academicReport.AcademicMap.size(), 12);
	}

	@Test
	public void testAcademicMapItems() {
		boolean academic_respectsLearningForSelfAndOthers = false;
		boolean academic_followsDirections = false;
		boolean academic_consistentlyPreparedAndOrganized = false;
		boolean academic_completesHomeworkAndAssignments = false;
		boolean academic_staysOnTask = false;
		boolean academic_peerTutoring = false;
		boolean academic_struggles = false;
		boolean academic_disruptionOfClassLessonActivity = false;
		boolean academic_refusalToFollowDirectionsAndParticipate = false;
		boolean academic_unPreparedAndDisorganized = false;
		boolean academic_failureToCompleteHomeworkAssignment = false;
		boolean academic_questionableAcademicIntegrity = false;
		AcademicReport academicReport = new AcademicReport();
		assertNotNull(academicReport.AcademicMap.get("academic_respectsLearningForSelfAndOthers"));
		assertEquals(academicReport.AcademicMap.get("academic_respectsLearningForSelfAndOthers"), academic_respectsLearningForSelfAndOthers);
		
		assertNotNull(academicReport.AcademicMap.get("academic_followsDirections"));
		assertEquals(academicReport.AcademicMap.get("academic_followsDirections"), academic_followsDirections);
		
		assertNotNull(academicReport.AcademicMap.get("academic_consistentlyPreparedAndOrganized"));
		assertEquals(academicReport.AcademicMap.get("academic_consistentlyPreparedAndOrganized"), academic_consistentlyPreparedAndOrganized);
		
		assertNotNull(academicReport.AcademicMap.get("academic_completesHomeworkAndAssignments"));
		assertEquals(academicReport.AcademicMap.get("academic_completesHomeworkAndAssignments"), academic_completesHomeworkAndAssignments);
		
		assertNotNull(academicReport.AcademicMap.get("academic_staysOnTask"));
		assertEquals(academicReport.AcademicMap.get("academic_staysOnTask"), academic_staysOnTask);
		
		assertNotNull(academicReport.AcademicMap.get("academic_peerTutoring"));
		assertEquals(academicReport.AcademicMap.get("academic_peerTutoring"), academic_peerTutoring);
		
		assertNotNull(academicReport.AcademicMap.get("academic_struggles"));
		assertEquals(academicReport.AcademicMap.get("academic_struggles"), academic_struggles);
		
		assertNotNull(academicReport.AcademicMap.get("academic_disruptionOfClassLessonActivity"));
		assertEquals(academicReport.AcademicMap.get("academic_disruptionOfClassLessonActivity"), academic_disruptionOfClassLessonActivity);
		
		assertNotNull(academicReport.AcademicMap.get("academic_refusalToFollowDirectionsAndParticipate"));
		assertEquals(academicReport.AcademicMap.get("academic_refusalToFollowDirectionsAndParticipate"), academic_refusalToFollowDirectionsAndParticipate);
		
		assertNotNull(academicReport.AcademicMap.get("academic_unPreparedAndDisorganized"));
		assertEquals(academicReport.AcademicMap.get("academic_unPreparedAndDisorganized"), academic_unPreparedAndDisorganized);
		
		assertNotNull(academicReport.AcademicMap.get("academic_failureToCompleteHomeworkAssignment"));
		assertEquals(academicReport.AcademicMap.get("academic_failureToCompleteHomeworkAssignment"), academic_failureToCompleteHomeworkAssignment);
		
		assertNotNull(academicReport.AcademicMap.get("academic_questionableAcademicIntegrity"));
		assertEquals(academicReport.AcademicMap.get("academic_questionableAcademicIntegrity"), academic_questionableAcademicIntegrity);
	}
}
