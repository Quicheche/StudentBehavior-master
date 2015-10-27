package com.example.studentbehavior;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyReportTest {
	public boolean strategy_plannedIgnoring = false;
	public boolean strategy_behaviorLog = false;
	public boolean strategy_reteachReviewExpectations = false;
	public boolean strategy_restorativeAction = false;
	public boolean strategy_apologyVerbalAndOrWritten = false;
	public boolean strategy_scholarPairingTimeOut = false;
	public boolean strategy_timeOut = false;
	public boolean strategy_ageAppropriateWritingActivity = false;
	public boolean strategy_behaviorProcessingForm = false;
	public boolean strategy_teacherScholarConversationOutsideClassroom = false;
	public boolean strategy_conversationWithFamily = false;
	public boolean strategy_conference = false;
	public boolean strategy_lossOfPriveleges = false;
	
	@Test
	public void testStrategyMapSize() {
		StrategyReport strategyMap = new StrategyReport();
		assertEquals(strategyMap.StrategyMap.size(), 13);
	}
	
	@Test
	public void testStrategyItems() {
		StrategyReport strategyMap = new StrategyReport();
		assertNotNull(strategyMap.StrategyMap.get("strategy_plannedIgnoring"));
		assertEquals(strategyMap.StrategyMap.get("strategy_plannedIgnoring"), strategy_plannedIgnoring);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_behaviorLog"));
		assertEquals(strategyMap.StrategyMap.get("strategy_behaviorLog"), strategy_behaviorLog);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_reteachReviewExpectations"));
		assertEquals(strategyMap.StrategyMap.get("strategy_reteachReviewExpectations"), strategy_reteachReviewExpectations);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_restorativeAction"));
		assertEquals(strategyMap.StrategyMap.get("strategy_restorativeAction"), strategy_restorativeAction);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_apologyVerbalAndOrWritten"));
		assertEquals(strategyMap.StrategyMap.get("strategy_apologyVerbalAndOrWritten"), strategy_apologyVerbalAndOrWritten);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_scholarPairingTimeOut"));
		assertEquals(strategyMap.StrategyMap.get("strategy_scholarPairingTimeOut"), strategy_scholarPairingTimeOut);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_timeOut"));
		assertEquals(strategyMap.StrategyMap.get("strategy_timeOut"), strategy_timeOut);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_ageAppropriateWritingActivity"));
		assertEquals(strategyMap.StrategyMap.get("strategy_ageAppropriateWritingActivity"), strategy_ageAppropriateWritingActivity);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_behaviorProcessingForm"));
		assertEquals(strategyMap.StrategyMap.get("strategy_behaviorProcessingForm"), strategy_behaviorProcessingForm);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_teacherScholarConversationOutsideClassroom"));
		assertEquals(strategyMap.StrategyMap.get("strategy_teacherScholarConversationOutsideClassroom"), strategy_teacherScholarConversationOutsideClassroom);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_conversationWithFamily"));
		assertEquals(strategyMap.StrategyMap.get("strategy_conversationWithFamily"), strategy_conversationWithFamily);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_conference"));
		assertEquals(strategyMap.StrategyMap.get("strategy_conference"), strategy_conference);
		
		assertNotNull(strategyMap.StrategyMap.get("strategy_lossOfPriveleges"));
		assertEquals(strategyMap.StrategyMap.get("strategy_lossOfPriveleges"), strategy_lossOfPriveleges);
	}


}
