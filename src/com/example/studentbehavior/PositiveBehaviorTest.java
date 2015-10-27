package com.example.studentbehavior;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositiveBehaviorTest {
	public boolean behaviorRespectForSelfAndOthers = false;
	public boolean behaviorFollowingDirections = false;
	public boolean behaviorPositiveConflictResolution = false;
	public boolean behaviorPeerMediation = false;
	public boolean behaviorHelpingPeerOrStaff = false;
	public boolean behaviorLeadership = false;
	public boolean behaviorDealingWithAdversityPositively = false;
	public boolean behaviorGoingAboveAndBeyond = false;
	
	@Test
	public void testMapSize() {
		PositiveBehavior positiveBehavior = new PositiveBehavior();
		assertNotNull(positiveBehavior.PosBehaviorMap);
		assertEquals(positiveBehavior.PosBehaviorMap.size(), 8);
	}
	
	@Test
	public void testMapItems() {
		PositiveBehavior positiveBehavior = new PositiveBehavior();
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorRespectForSelfAndOthers"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorRespectForSelfAndOthers"), behaviorRespectForSelfAndOthers);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorFollowingDirections"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorFollowingDirections"), behaviorFollowingDirections);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorPositiveConflictResolution"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorPositiveConflictResolution"), behaviorPositiveConflictResolution);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorPeerMediation"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorPeerMediation"), behaviorPeerMediation);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorHelpingPeerOrStaff"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorHelpingPeerOrStaff"), behaviorHelpingPeerOrStaff);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorLeadership"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorLeadership"), behaviorLeadership);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorDealingWithAdversityPositively"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorDealingWithAdversityPositively"), behaviorDealingWithAdversityPositively);
		
		assertNotNull(positiveBehavior.PosBehaviorMap.get("behaviorGoingAboveAndBeyond"));
		assertEquals(positiveBehavior.PosBehaviorMap.get("behaviorGoingAboveAndBeyond"), behaviorGoingAboveAndBeyond);
	}

}
