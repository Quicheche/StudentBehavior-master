package com.example.studentbehavior;

import static org.junit.Assert.*;

import org.junit.Test;
import android.view.Menu;

public class ViewReportByNameActivityTest {

	@Test
	public void test() {
		ViewReportByNameActivity activity = new ViewReportByNameActivity();
		Menu menu = new MyMenu();
		activity.onCreateOptionsMenu(menu);
		fail("Not yet implemented");
	}

	public class MyMenu implements Menu {
		
	}
}
