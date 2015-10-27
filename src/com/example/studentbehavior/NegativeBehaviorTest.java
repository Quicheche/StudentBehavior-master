package com.example.studentbehavior;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class NegativeBehaviorTest {
	public boolean behavior_refusalToFollowDirectionsOrParticipate = false;
	public boolean behavior_disruptionOfClassOrActivity = false;
	public boolean behavior_disrespectOfStaffOrScholars = false;
	public boolean behavior_inappropriateLanguageOrGestures = false;
	public boolean behavior_inappropriatePhysicalContactOrFighting = false;
	public boolean behavior_teasingOrInstigatingConflict = false;
	public boolean behavior_runningInCommonSpaces = false;
	public boolean behavior_leavingSupervisionUnattended = false;
	public boolean behavior_failingToFollowRules = false;
	
	@Test
	public void testMapSize() {
		NegativeBehavior nb = new NegativeBehavior();
		assertNotNull(nb.NegBehaviorMap);
		assertEquals(nb.NegBehaviorMap.size(), 9);
	}
	
	@Test
	public void testItems() {
		NegativeBehavior nb = new NegativeBehavior();
		
		assertNotNull(nb.NegBehaviorMap.get("academic_completesHomeworkAndAssignments"));
		assertEquals(nb.NegBehaviorMap.get("academic_completesHomeworkAndAssignments"), behavior_refusalToFollowDirectionsOrParticipate);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_disruptionOfClassOrActivity"));
		assertEquals(nb.NegBehaviorMap.get("behavior_disruptionOfClassOrActivity"), behavior_disruptionOfClassOrActivity);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_disrespectOfStaffOrScholars"));
		assertEquals(nb.NegBehaviorMap.get("behavior_disrespectOfStaffOrScholars"), behavior_disrespectOfStaffOrScholars);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_inappropriateLanguageOrGestures"));
		assertEquals(nb.NegBehaviorMap.get("behavior_inappropriateLanguageOrGestures"), behavior_inappropriateLanguageOrGestures);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_teasingOrInstigatingConflict"));
		assertEquals(nb.NegBehaviorMap.get("behavior_teasingOrInstigatingConflict"), behavior_teasingOrInstigatingConflict);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_runningInCommonSpaces"));
		assertEquals(nb.NegBehaviorMap.get("behavior_runningInCommonSpaces"), behavior_runningInCommonSpaces);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_leavingSupervisionUnattended"));
		assertEquals(nb.NegBehaviorMap.get("behavior_leavingSupervisionUnattended"), behavior_leavingSupervisionUnattended);
		
		assertNotNull(nb.NegBehaviorMap.get("behavior_leavingSupervisionUnattended"));
		assertEquals(nb.NegBehaviorMap.get("behavior_leavingSupervisionUnattended"), behavior_leavingSupervisionUnattended);
	}

}
